package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.tileentity.Tansu_TileEntity;
import com.mojang.serialization.MapCodec;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Tansu extends AbstractTansu<Tansu_TileEntity> implements SimpleWaterloggedBlock {
	public static final MapCodec<Tansu> CODEC = simpleCodec(props -> new Tansu(() -> BlockEntity_CM.TANSU.get(), props));
	@Override
	public MapCodec<? extends Tansu> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
	public static final int EVENT_SET_OPEN_COUNT = 1;

	public Tansu(Supplier<BlockEntityType<? extends Tansu_TileEntity>> entityType, BlockBehaviour.Properties props) {
		super(props, entityType);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(TYPE, ChestType.SINGLE));
	}

	public BlockEntityType<? extends Tansu_TileEntity> blockEntityType() {
		return this.blockEntityType.get();
	}
	
	public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
		return DoubleBlockCombiner.BlockType.SINGLE;
	}
	
	@Nullable
	private Direction candidatePartnerFacing(BlockPlaceContext context, Direction direct) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(direct));
		return state.is(this) && state.getValue(TYPE) == ChestType.SINGLE ? state.getValue(H_FACING) : null;
	}
	
	@Override
	protected void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean flag) {
		Containers.dropContentsOnDestroy(state, newState, worldIn, pos);
		super.onRemove(state, worldIn, pos, newState, flag);
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player playerIn, BlockHitResult hit) {
		if (worldIn.isClientSide) { return InteractionResult.SUCCESS; }
		
		else {
			if (state.getValue(WATERLOGGED)) { 
				CMEvents.textIsWaterlogged(worldIn, pos, playerIn);
				return InteractionResult.SUCCESS; }
			
			else { //!WATERLOGGED
				Direction direction = state.getValue(H_FACING);
				BlockEntity tile = worldIn.getBlockEntity(pos);
				
				BlockState northState = worldIn.getBlockState(pos.north());
				BlockState southState = worldIn.getBlockState(pos.south());
				BlockState eastState = worldIn.getBlockState(pos.east());
				BlockState westState = worldIn.getBlockState(pos.west());

				switch (direction) {
				case NORTH :
				default :
					if (northState.canBeReplaced()) {
						if (northState.getBlock() instanceof LiquidBlock) {
							CMEvents.textIsBlocked(worldIn, pos, playerIn);
							return InteractionResult.SUCCESS; }
						
						else { //!Fluid
							if (tile instanceof Tansu_TileEntity) {
								// NetworkHooks.openScreen((ServerPlayer)playerIn, (Tansu_TileEntity)tile, pos);
								playerIn.openMenu((Tansu_TileEntity)tile); }
						} }
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn);
						return InteractionResult.SUCCESS; }
					break;

				case SOUTH:
					if (southState.canBeReplaced()) {
						if (southState.getBlock() instanceof LiquidBlock) {
							CMEvents.textIsBlocked(worldIn, pos, playerIn);
							return InteractionResult.SUCCESS; }
						
						else { //!Fluid
							if (tile instanceof Tansu_TileEntity) { playerIn.openMenu((Tansu_TileEntity)tile); }
						} }
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn);
						return InteractionResult.SUCCESS; }
					break;

				case EAST:
					if (eastState.canBeReplaced()) {
						if (eastState.getBlock() instanceof LiquidBlock) {
							CMEvents.textIsBlocked(worldIn, pos, playerIn);
							return InteractionResult.SUCCESS; }
						
						else { //!Fluid
							if (tile instanceof Tansu_TileEntity) { playerIn.openMenu((Tansu_TileEntity)tile); }
						} }
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn);
						return InteractionResult.SUCCESS; }
					break;
					
				case WEST:
					if (westState.canBeReplaced()) {
						if (westState.getBlock() instanceof LiquidBlock) {
							CMEvents.textIsBlocked(worldIn, pos, playerIn);
							return InteractionResult.SUCCESS; }
						
						else { //!Fluid
							if (tile instanceof Tansu_TileEntity) { playerIn.openMenu((Tansu_TileEntity)tile); }
						} }
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn);
						return InteractionResult.SUCCESS; }
					break;
				} // direction
			}
		}
		return InteractionResult.CONSUME;
	}
	
	protected Stat<ResourceLocation> getOpenChestStat() {
		return Stats.CUSTOM.get(Stats.OPEN_CHEST);
	}
	
	@Nullable
	public static Container getContainer(Tansu block, BlockState state, Level worldIn, BlockPos pos, boolean flag) {
		return (Container) worldIn.getBlockEntity(pos);
	}
	
	@Override
	public DoubleBlockCombiner.NeighborCombineResult<? extends Tansu_TileEntity> combine(BlockState state, Level worldIn, BlockPos pos, boolean flag) {
		return null;
	}

	public static DoubleBlockCombiner.Combiner<Tansu_TileEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lis) {
		return new DoubleBlockCombiner.Combiner<Tansu_TileEntity, Float2FloatFunction>() {
			public Float2FloatFunction acceptDouble(Tansu_TileEntity tileEntity_1, Tansu_TileEntity tileEntity_2) {
				return p_51638_ -> Math.max(tileEntity_1.getOpenNess(p_51638_), tileEntity_2.getOpenNess(p_51638_));
			}

			public Float2FloatFunction acceptSingle(Tansu_TileEntity tileEntity) {
				return tileEntity::getOpenNess;
			}

			public Float2FloatFunction acceptNone() {
				return lis::getOpenNess;
			}
		};
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new Tansu_TileEntity(pos, state);
	}
	
	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState state, Level worldIn, BlockPos pos) {
		return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, state, worldIn, pos, false));
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
	}
	
	/* HORIZONTAL Property */
	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}
	
	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, OPEN, WATERLOGGED, TYPE);
	}
	
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL; // BlockRenderType.MODEL でブロックのモデルを表示
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return Shapes.block();
	}
}
