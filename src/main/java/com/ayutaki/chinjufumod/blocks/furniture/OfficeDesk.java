package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.BaseFood_Stage4Water;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.tileentity.OfficeDesk_TileEntity;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class OfficeDesk extends AbstractOfficeDesk<OfficeDesk_TileEntity> implements SimpleWaterloggedBlock {
	public static final MapCodec<OfficeDesk> CODEC = simpleCodec(props -> new OfficeDesk(() -> BlockEntity_CM.OFFICEDESK.get(), props));
	@Override
	public MapCodec<? extends OfficeDesk> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
	public static final IntegerProperty STAGE_1_3 = IntegerProperty.create("stage", 1, 3);
	public static final int EVENT_SET_OPEN_COUNT = 1;

	/* Collision */
	private static final VoxelShape SOUTH_R = Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_R = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape WEST_R = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape NORTH_R = Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_C = Block.box(0.0D, 11.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape SOUTH_L = Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_L = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape WEST_L = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape NORTH_L = Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	public OfficeDesk(Supplier<BlockEntityType<? extends OfficeDesk_TileEntity>> entityType, BlockBehaviour.Properties props) {
		super(props, entityType);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(TYPE, ChestType.SINGLE)
				.setValue(STAGE_1_3, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	public BlockEntityType<? extends OfficeDesk_TileEntity> blockEntityType() {
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
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		int i = state.getValue(STAGE_1_3);
		Direction direction = state.getValue(H_FACING);
		Direction facing = playerIn.getDirection().getOpposite();
		
		BlockState northState = worldIn.getBlockState(pos.north());
		BlockState southState = worldIn.getBlockState(pos.south());
		BlockState eastState = worldIn.getBlockState(pos.east());
		BlockState westState = worldIn.getBlockState(pos.west());
		BlockState upState = worldIn.getBlockState(pos.above());
		
		FluidState northFluid = worldIn.getFluidState(pos.north());
		FluidState southFluid = worldIn.getFluidState(pos.south());
		FluidState eastFluid = worldIn.getFluidState(pos.east());
		FluidState westFluid = worldIn.getFluidState(pos.west());
		FluidState upFluid = worldIn.getFluidState(pos.above());
		
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		boolean book = (hItem == Items_Chinjufu.SHOUHOU_empty.get() || hItem == Items.BOOK);
		boolean upAble = (upState.canBeReplaced() && upFluid.getType() == Fluids.EMPTY);
		
		if (i == 1) {
			boolean carpets = (hItem == Items.WHITE_CARPET) || (hItem == Items.ORANGE_CARPET) || (hItem == Items.MAGENTA_CARPET) || (hItem == Items.LIGHT_BLUE_CARPET) ||
										(hItem == Items.YELLOW_CARPET) || (hItem == Items.LIME_CARPET) || (hItem == Items.PINK_CARPET) || (hItem == Items.GRAY_CARPET) ||
										(hItem == Items.LIGHT_GRAY_CARPET) || (hItem == Items.CYAN_CARPET) || (hItem == Items.PURPLE_CARPET) || (hItem == Items.BLUE_CARPET) ||
										(hItem == Items.BROWN_CARPET) || (hItem == Items.GREEN_CARPET) || (hItem == Items.RED_CARPET) || (hItem == Items.BLACK_CARPET);
			boolean success = (carpets || book);
			
			if (success) {
				if (hItem == Items_Chinjufu.SHOUHOU_empty.get() && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Furniture_Blocks.NOTEBOOK.get().defaultBlockState()
							.setValue(NoteBook.H_FACING, facing)
							.setValue(NoteBook.HAS_BOOK, Boolean.valueOf(false))
							.setValue(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
				
				
				if (hItem == Items.BOOK && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Furniture_Blocks.DESKBOOK_1.get().defaultBlockState()
							.setValue(DeskBook1.H_FACING, facing)
							.setValue(DeskBook1.STAGE_1_4, Integer.valueOf(1))
							.setValue(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }

				if (carpets) {
					BlockState carpetState = takeBlock(hItem).defaultBlockState()
							.setValue(DeskCloth.H_FACING, direction)
							.setValue(DeskCloth.STAGE_1_4, Integer.valueOf(takeMeta(hItem)));
					
					switch (direction) {
					case NORTH :
					default:
						if (southState.canBeReplaced()) {
							worldIn.setBlock(pos.south(), carpetState.setValue(DeskCloth.WATERLOGGED, Boolean.valueOf(southFluid.getType() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable()
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
				
					case SOUTH :
						if (northState.canBeReplaced()) {
							worldIn.setBlock(pos.north(), carpetState.setValue(DeskCloth.WATERLOGGED, Boolean.valueOf(northFluid.getType() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable()
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
				
					case EAST :
						if (westState.canBeReplaced()) {
							worldIn.setBlock(pos.west(), carpetState.setValue(DeskCloth.WATERLOGGED, Boolean.valueOf(westFluid.getType() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable()
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
						
					case WEST :
						if (eastState.canBeReplaced()) {
							worldIn.setBlock(pos.east(), carpetState.setValue(DeskCloth.WATERLOGGED, Boolean.valueOf(eastFluid.getType() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable()
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
					} // switch
				}
				return InteractionResult.SUCCESS;
			}
				
			else { return InteractionResult.PASS; }
		}
		
		else {
			if (book && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				Block book2 = (hItem == Items.BOOK)? Furniture_Blocks.DESKBOOK_2.get() : Furniture_Blocks.NOTEBOOK_2.get();
				Block book3 = (hItem == Items.BOOK)? Furniture_Blocks.DESKBOOK_3.get() : Furniture_Blocks.NOTEBOOK_3.get();

				Block bookBlock = (i == 2)? book2 : book3;
				worldIn.setBlock(pos.above(), bookBlock.defaultBlockState()
						.setValue(BaseFood_Stage4Water.H_FACING, direction)
						.setValue(BaseFood_Stage4Water.STAGE_1_4, Integer.valueOf(1))
						.setValue(BaseFood_Stage4Water.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3);
				
				return InteractionResult.SUCCESS; }

			else { 
				if (worldIn.isClientSide) { return InteractionResult.SUCCESS; }

				if (!worldIn.isClientSide) {
					if (state.getValue(WATERLOGGED)) { 
						CMEvents.textIsWaterlogged(worldIn, pos, playerIn);
						return InteractionResult.SUCCESS; }
					
					else { //!WATERLOGGED
						BlockEntity tile = worldIn.getBlockEntity(pos);
												
						switch (direction) {
						case NORTH:
						default:
							if (northState.canBeReplaced()) {
								if (northState.getBlock() instanceof LiquidBlock) {
									CMEvents.textIsBlocked(worldIn, pos, playerIn);
									return InteractionResult.SUCCESS; }
								
								else { //!Fluid
									if (tile instanceof OfficeDesk_TileEntity) {
										// NetworkHooks.openScreen((ServerPlayer)playerIn, (OfficeDesk_TileEntity)tile, pos);
										playerIn.openMenu((OfficeDesk_TileEntity)tile); } // for 20.2
								} }
							
							else { //!Replaceable()
								CMEvents.textIsBlocked(worldIn, pos, playerIn);
								return InteractionResult.SUCCESS; }
							break;

						case SOUTH:
							if (southState.canBeReplaced()) {
								if (southState.getBlock() instanceof LiquidBlock) {
									CMEvents.textIsBlocked(worldIn, pos, playerIn);
									return InteractionResult.SUCCESS; }
								
								else { //!Fluid
									if (tile instanceof OfficeDesk_TileEntity) { playerIn.openMenu((OfficeDesk_TileEntity)tile); }
								} }
							
							else { //!Replaceable()
								CMEvents.textIsBlocked(worldIn, pos, playerIn);
								return InteractionResult.SUCCESS; }
							break;

						case EAST:
							if (eastState.canBeReplaced()) {
								if (eastState.getBlock() instanceof LiquidBlock) {
									CMEvents.textIsBlocked(worldIn, pos, playerIn);
									return InteractionResult.SUCCESS; }
								
								else { //!Fluid
									if (tile instanceof OfficeDesk_TileEntity) { playerIn.openMenu((OfficeDesk_TileEntity)tile); }
								} }
							
							else { //!Replaceable()
								CMEvents.textIsBlocked(worldIn, pos, playerIn);
								return InteractionResult.SUCCESS; }
							break;
							
						case WEST:
							if (westState.canBeReplaced()) {
								if (westState.getBlock() instanceof LiquidBlock) {
									CMEvents.textIsBlocked(worldIn, pos, playerIn);
									return InteractionResult.SUCCESS; }
								
								else { //!Fluid
									if (tile instanceof OfficeDesk_TileEntity) { playerIn.openMenu((OfficeDesk_TileEntity)tile); }
								} }
							
							else { //!Replaceable()
								CMEvents.textIsBlocked(worldIn, pos, playerIn);
								return InteractionResult.SUCCESS; }
							break;
						} // switch
					} // !state.getValue(WATERLOGGED)
				}
				return InteractionResult.CONSUME;
			}
		}
	}
	
	private Block takeBlock(Item hItem) {
		boolean cloth03 = (hItem == Items.WHITE_CARPET) || (hItem == Items.ORANGE_CARPET) || (hItem == Items.MAGENTA_CARPET) || (hItem == Items.LIGHT_BLUE_CARPET);
		boolean cloth47 = (hItem == Items.YELLOW_CARPET) || (hItem == Items.LIME_CARPET) || (hItem == Items.PINK_CARPET) || (hItem == Items.GRAY_CARPET);
		boolean cloth811 = (hItem == Items.LIGHT_GRAY_CARPET) || (hItem == Items.CYAN_CARPET) || (hItem == Items.PURPLE_CARPET) || (hItem == Items.BLUE_CARPET);
		
		if (cloth03) { return Furniture_Blocks.DESKCLOTH_03.get(); }
		if (cloth47) { return Furniture_Blocks.DESKCLOTH_47.get(); }
		if (cloth811) { return Furniture_Blocks.DESKCLOTH_811.get(); }
		else { return Furniture_Blocks.DESKCLOTH_1215.get(); }
	}
	
	private int takeMeta(Item hItem) {
		boolean meta1 = (hItem == Items.WHITE_CARPET) || (hItem == Items.YELLOW_CARPET) || (hItem == Items.LIGHT_GRAY_CARPET) || (hItem == Items.BROWN_CARPET);
		boolean meta2 = (hItem == Items.ORANGE_CARPET) || (hItem == Items.LIME_CARPET) || (hItem == Items.CYAN_CARPET) || (hItem == Items.GREEN_CARPET);
		boolean meta3 = (hItem == Items.MAGENTA_CARPET) || (hItem == Items.PINK_CARPET) || (hItem == Items.PURPLE_CARPET) || (hItem == Items.RED_CARPET);

		if (meta1) { return 1; }
		if (meta2) { return 2; }
		if (meta3) { return 3; }
		else { return 4; }
	}
	
	protected Stat<ResourceLocation> getOpenChestStat() {
		return Stats.CUSTOM.get(Stats.OPEN_CHEST);
	}
	
	@Nullable
	public static Container getContainer(OfficeDesk block, BlockState state, Level worldIn, BlockPos pos, boolean flag) {
		return (Container) worldIn.getBlockEntity(pos);
	}
	
	@Override
	public DoubleBlockCombiner.NeighborCombineResult<? extends OfficeDesk_TileEntity> combine(BlockState state, Level worldIn, BlockPos pos, boolean flag) {
		return null;
	}

	public static DoubleBlockCombiner.Combiner<OfficeDesk_TileEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lis) {
		return new DoubleBlockCombiner.Combiner<OfficeDesk_TileEntity, Float2FloatFunction>() {
			public Float2FloatFunction acceptDouble(OfficeDesk_TileEntity tileEntity_1, OfficeDesk_TileEntity tileEntity_2) {
				return p_51638_ -> Math.max(tileEntity_1.getOpenNess(p_51638_), tileEntity_2.getOpenNess(p_51638_));
			}

			public Float2FloatFunction acceptSingle(OfficeDesk_TileEntity tileEntity) {
				return tileEntity::getOpenNess;
			}

			public Float2FloatFunction acceptNone() {
				return lis::getOpenNess;
			}
		};
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new OfficeDesk_TileEntity(pos, state);
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
		Player playerIn = context.getPlayer();
		
		Direction facing = playerIn.getDirection();
		int x = (int) pos.getX();
		int y = (int) pos.getY();
		int z = (int) pos.getZ();
		
		BlockState State1 = this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(STAGE_1_3, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
				
		boolean WE = worldIn.getBlockState(new BlockPos(x + 1, y, z)).canBeReplaced(context) && worldIn.getBlockState(new BlockPos(x - 1, y, z)).canBeReplaced(context);
		boolean NS = worldIn.getBlockState(new BlockPos(x, y, z + 1)).canBeReplaced(context) && worldIn.getBlockState(new BlockPos(x, y, z - 1)).canBeReplaced(context);

		if (facing == Direction.NORTH && WE) { return State1; }
		if (facing == Direction.SOUTH && WE) { return State1; }
		if (facing == Direction.EAST && NS) { return State1; }
		if (facing == Direction.WEST && NS) { return State1; }

		else { 
			CMEvents.textNoPlace(context.getLevel(), context.getClickedPos(), context.getPlayer());
			return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		Direction facing = placer.getDirection();
		int x = (int) pos.getX();
		int y = (int) pos.getY();
		int z = (int) pos.getZ();
	
		BlockState State2 = this.defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)).setValue(STAGE_1_3, Integer.valueOf(2));
		BlockState State3 = this.defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)).setValue(STAGE_1_3, Integer.valueOf(3));
		
		switch (facing) {
		case NORTH :
		default :
			worldIn.setBlock(new BlockPos(x - 1, y, z), State2.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getType() == Fluids.WATER)), 3);
			worldIn.setBlock(new BlockPos(x + 1, y, z), State3.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getType() == Fluids.WATER)), 3);
			break;

		case SOUTH :
			worldIn.setBlock(new BlockPos(x - 1, y, z), State3.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getType() == Fluids.WATER)), 3);
			worldIn.setBlock(new BlockPos(x + 1, y, z), State2.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getType() == Fluids.WATER)), 3);
			break;

		case EAST :
			worldIn.setBlock(new BlockPos(x, y, z - 1), State2.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getType() == Fluids.WATER)), 3);
			worldIn.setBlock(new BlockPos(x, y, z + 1), State3.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getType() == Fluids.WATER)), 3);
			break;
			
		case WEST :
			worldIn.setBlock(new BlockPos(x, y, z - 1), State3.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getType() == Fluids.WATER)), 3);
			worldIn.setBlock(new BlockPos(x, y, z + 1), State2.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getType() == Fluids.WATER)), 3);
			break;
		} // direction
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
	protected boolean isPathfindable(BlockState p_51522_, PathComputationType p_51525_) {
		return false;
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_3, WATERLOGGED, OPEN, TYPE);
	}
	
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL; // BlockRenderType.MODEL でブロックのモデルを表示
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		int i = state.getValue(STAGE_1_3);
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default:
			return (i == 2)? NORTH_R : ((i ==3)? NORTH_L : AABB_C);
			
		case SOUTH:
			return (i == 2)? SOUTH_R : ((i ==3)? SOUTH_L : AABB_C);

		case EAST:
			return (i == 2)? EAST_R : ((i ==3)? EAST_L : AABB_C);

		case WEST:
			return (i == 2)? WEST_R : ((i ==3)? WEST_L : AABB_C);
		}
	}

	/* Controls drops when broken. End the process with "break;".*/
	@Override
	public BlockState playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		int i = state.getValue(STAGE_1_3);
		Direction direction = state.getValue(H_FACING);

		int x = (int) pos.getX();
		int y = (int) pos.getY();
		int z = (int) pos.getZ();

		if (i == 1) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				break;
			} // direction
		}
	
		if (i == 2) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x - 2, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 2, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z - 2), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 2), false);
				break;
			} // direction
		}
	
		if (i == 3) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 2, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x - 2, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 2), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z - 2), false);
				break;
			} // direction
		}
		return super.playerWillDestroy(worldIn, pos, state, playerIn);
	}
}
