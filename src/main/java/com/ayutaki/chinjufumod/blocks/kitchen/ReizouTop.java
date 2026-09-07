package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.tileentity.AbstractReizouTileEntity;
import com.ayutaki.chinjufumod.tileentity.ReizouTop_TileEntity;
import com.mojang.serialization.MapCodec;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ReizouTop extends AbstractReizouTop<ReizouTop_TileEntity> {
	public static final MapCodec<ReizouTop> CODEC = simpleCodec(properties -> new ReizouTop(properties, () -> BlockEntity_CM.REIZOU_TOP.get()));
	@Override
	public MapCodec<? extends ReizouTop> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final BooleanProperty RIGHT = BooleanProperty.create("right");
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
	
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 13.4D);
	private static final VoxelShape AABB_WEST = Block.box(2.6D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 2.6D, 16.0D, 10.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 13.4D, 10.0D, 16.0D);

	public ReizouTop(BlockBehaviour.Properties props, Supplier<BlockEntityType<? extends ReizouTop_TileEntity>> entityType) {
		super(props, entityType);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(RIGHT, Boolean.valueOf(false))
				.setValue(POWERED, Boolean.valueOf(false))
				.setValue(TYPE, ChestType.SINGLE));
	}

	public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
		return DoubleBlockCombiner.BlockType.SINGLE;
	}
	
	@SuppressWarnings("unused")
	private Direction candidatePartnerFacing(BlockPlaceContext context, Direction direct) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(direct));
		return state.is(this) && state.getValue(TYPE) == ChestType.SINGLE ? state.getValue(H_FACING) : null;
	}
	
	/* for 1.20.6
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entity, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			BlockEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof ReizouTop_TileEntity) {
				((ReizouTop_TileEntity)tileentity).setCustomName(stack.getHoverName());
			}
		}
	} */
	
	/** Chest -> Furnace **/
	@Override
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean flag) {
		if (!state.is(newState.getBlock())) {
			
			BlockEntity tileEntity = worldIn.getBlockEntity(pos);
			if (tileEntity instanceof AbstractReizouTileEntity reizou) {
				
				if (worldIn instanceof ServerLevel server) {
					Containers.dropContents(worldIn, pos, reizou);
					reizou.dropExp(server, pos); }
			} 
			super.onRemove(state, worldIn, pos, newState, flag);
		}
	}
	
	/* RightClick Action */
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player playerIn, BlockHitResult hit) {
		
		if (worldIn.isClientSide) { return InteractionResult.SUCCESS; }

		if (!worldIn.isClientSide) {
			
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
							if (tile instanceof ReizouTop_TileEntity) {
								// NetworkHooks.openScreen((ServerPlayer)playerIn, (ReizouTop_TileEntity)tile, pos);
								playerIn.openMenu((ReizouTop_TileEntity)tile); } // for 20.2
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
							if (tile instanceof ReizouTop_TileEntity) { playerIn.openMenu((ReizouTop_TileEntity)tile); }
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
							if (tile instanceof ReizouTop_TileEntity) { playerIn.openMenu((ReizouTop_TileEntity)tile); }
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
							if (tile instanceof ReizouTop_TileEntity) { playerIn.openMenu((ReizouTop_TileEntity)tile); }
						} }
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn);
						return InteractionResult.SUCCESS; }
					break;
				} // switch
			} // !state.getValue(WATERLOGGED)
		}
		return InteractionResult.CONSUME;
	}
	
	protected Stat<ResourceLocation> getOpenChestStat() {
		return Stats.CUSTOM.get(Stats.OPEN_CHEST);
	}
	
	@Nullable
	public static Container getContainer(ReizouTop block, BlockState state, Level worldIn, BlockPos pos, boolean flag) {
		return (Container) worldIn.getBlockEntity(pos);
	}

	public DoubleBlockCombiner.NeighborCombineResult<? extends ReizouTop_TileEntity> combine(BlockState state, Level worldIn, BlockPos pos, boolean flag) {
		return null;
	}
	
	public static DoubleBlockCombiner.Combiner<ReizouTop_TileEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lis) {
		return new DoubleBlockCombiner.Combiner<ReizouTop_TileEntity, Float2FloatFunction>() {
			public Float2FloatFunction acceptDouble(ReizouTop_TileEntity tileEntity_1, ReizouTop_TileEntity tileEntity_2) {
				return (p_51638_) -> {
					return Math.max(tileEntity_1.getOpenNess(p_51638_), tileEntity_2.getOpenNess(p_51638_));
				};
			}

			public Float2FloatFunction acceptSingle(ReizouTop_TileEntity tileEntity) {
				return tileEntity::getOpenNess;
			}

			public Float2FloatFunction acceptNone() {
				return lis::getOpenNess;
			}
		};
	}
	
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ReizouTop_TileEntity(pos, state);
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}
	
	@Override
	public int getAnalogOutputSignal(BlockState state, Level worldIn, BlockPos pos) {
		return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, state, worldIn, pos, false));
	}
	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Waterlogged */
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	@Override
	public boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, OPEN, WATERLOGGED, POWERED, TYPE, RIGHT);
	}
	
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL; // BlockRenderType.MODEL でブロックのモデルを表示
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.KIT_REIZOU.get(), 1);
	}
	
	/* Destroy a DoubleBlock from DoublePlantBlock.class */
	@Override
	public BlockState playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		BlockState downState = worldIn.getBlockState(pos.below());
		/** False is not Drop. **/
		if (downState.getBlock() == Kitchen_Blocks.KIT_REIZOU.get()) {
			worldIn.destroyBlock(pos.below(), false);
		}
		return super.playerWillDestroy(worldIn, pos, state, playerIn);
	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level worldIn, BlockState state, BlockEntityType<T> type) {
		return createTicker(worldIn, type, BlockEntity_CM.REIZOU_TOP.get());
	}

	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level worldIn, BlockEntityType<T> TType, BlockEntityType<? extends ReizouTop_TileEntity> entityType) {
		return worldIn.isClientSide ? null : createTickerHelper(TType, entityType, ReizouTop_TileEntity::serverTick);
	}
}
