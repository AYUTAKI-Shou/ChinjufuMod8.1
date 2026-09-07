package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.tileentity.OfficeDesk_TileEntity;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class OfficeDesk extends AbstractOfficeDesk<OfficeDesk_TileEntity> {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
	public static final IntegerProperty STAGE_1_3 = IntegerProperty.create("stage", 1, 3);
	
	/* Collision */
	private static final VoxelShape SOUTH_R = Block.makeCuboidShape(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_R = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape WEST_R = Block.makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape NORTH_R = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_C = Block.makeCuboidShape(0.0D, 11.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape SOUTH_L = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_L = Block.makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape WEST_L = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape NORTH_L = Block.makeCuboidShape(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public OfficeDesk(Block.Properties props, Supplier<TileEntityType<? extends OfficeDesk_TileEntity>> entityType) {
		super(props, entityType);
		/** Default state **/
		this.setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(OPEN, Boolean.valueOf(false))
				.with(TYPE, ChestType.SINGLE)
				.with(STAGE_1_3, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	public static TileEntityMerger.Type func_226919_h_(BlockState state) {
		return TileEntityMerger.Type.SINGLE;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL; // BlockRenderType.MODEL でブロックのモデルを表示
	}
	
	///IForgeBlock//////////
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new OfficeDesk_TileEntity();
	}
	
	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		int i = state.get(STAGE_1_3);
		Direction direction = state.get(H_FACING);
		Direction facing = playerIn.getHorizontalFacing().getOpposite();
		
		BlockState northState = worldIn.getBlockState(pos.north());
		BlockState southState = worldIn.getBlockState(pos.south());
		BlockState eastState = worldIn.getBlockState(pos.east());
		BlockState westState = worldIn.getBlockState(pos.west());
		BlockState upState = worldIn.getBlockState(pos.up());
		
		IFluidState northFluid = worldIn.getFluidState(pos.north());
		IFluidState southFluid = worldIn.getFluidState(pos.south());
		IFluidState eastFluid = worldIn.getFluidState(pos.east());
		IFluidState westFluid = worldIn.getFluidState(pos.west());
		IFluidState upFluid = worldIn.getFluidState(pos.up());
		
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean book = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
		boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getFluid() == Fluids.EMPTY);
		
		if (i == 1) {
			boolean success = (hItem.isIn(ItemTags.CARPETS) || book);
			
			if (success) {
				if (hItem == Items_Chinjufu.SHOUHOU_empty && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), Furniture_Blocks.NOTEBOOK.getDefaultState()
							.with(NoteBook.H_FACING, facing)
							.with(NoteBook.HAS_BOOK, Boolean.valueOf(false))
							.with(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3); }

				if (hItem == Items.BOOK && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), Furniture_Blocks.DESKBOOK_1.getDefaultState()
							.with(DeskBook1.H_FACING, facing)
							.with(DeskBook1.STAGE_1_4, Integer.valueOf(1))
							.with(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3); }

				if (hItem.isIn(ItemTags.CARPETS)) {
					BlockState carpetState = takeBlock(hItem).getDefaultState()
							.with(DeskCloth.H_FACING, direction)
							.with(DeskCloth.STAGE_1_4, Integer.valueOf(takeMeta(hItem)));
							
					switch (direction) {
					case NORTH :
					default:
						if (southState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.south(), carpetState.with(DeskCloth.WATERLOGGED, Boolean.valueOf(southFluid.getFluid() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!south_Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
				
					case SOUTH :
						if (northState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.north(), carpetState.with(DeskCloth.WATERLOGGED, Boolean.valueOf(northFluid.getFluid() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!north_Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
				
					case EAST :
						if (westState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.west(), carpetState.with(DeskCloth.WATERLOGGED, Boolean.valueOf(westFluid.getFluid() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!west_Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
						
					case WEST :
						if (eastState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.east(), carpetState.with(DeskCloth.WATERLOGGED, Boolean.valueOf(eastFluid.getFluid() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!east_Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
					} // switch
				}
				return ActionResultType.SUCCESS;
			}

			else { return ActionResultType.PASS; }
		}
		
		else {
			if (book && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				Block book2 = (hItem == Items.BOOK)? Furniture_Blocks.DESKBOOK_2 : Furniture_Blocks.NOTEBOOK_2;
				Block book3 = (hItem == Items.BOOK)? Furniture_Blocks.DESKBOOK_3 : Furniture_Blocks.NOTEBOOK_3;

				Block bookBlock = (i == 2)? book2 : book3;
				worldIn.setBlockState(pos.up(), bookBlock.getDefaultState()
						.with(DeskBook2.H_FACING, direction)
						.with(DeskBook2.STAGE_1_4, Integer.valueOf(1))
						.with(DeskBook2.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3);
				
				return ActionResultType.SUCCESS; }
			
			else {
				if (worldIn.isRemote) { return ActionResultType.SUCCESS; }

				if (!worldIn.isRemote) {
					if (state.get(WATERLOGGED)) { 
						CMEvents.textIsWaterlogged(worldIn, pos, playerIn);
						return ActionResultType.SUCCESS; }
					
					else { //!WATERLOGGED
						TileEntity tile = worldIn.getTileEntity(pos);

						switch (direction) {
						case NORTH:
						default:
							if (northState.getMaterial().isReplaceable()) {
								if (northState.getBlock() instanceof FlowingFluidBlock) {
									CMEvents.textIsBlocked(worldIn, pos, playerIn);
									return ActionResultType.SUCCESS; }
								
								else { //!north_Fluid
									if (tile instanceof OfficeDesk_TileEntity) {
										NetworkHooks.openGui((ServerPlayerEntity)playerIn, (OfficeDesk_TileEntity)tile, pos); }
								} }
							
							else { //!north_Replaceable
								CMEvents.textIsBlocked(worldIn, pos, playerIn);
								return ActionResultType.SUCCESS; }
							break;

						case SOUTH:
							if (southState.getMaterial().isReplaceable()) {
								if (southState.getBlock() instanceof FlowingFluidBlock) {
									CMEvents.textIsBlocked(worldIn, pos, playerIn);
									return ActionResultType.SUCCESS; }
								
								else { //!south_Fluid
									if (tile instanceof OfficeDesk_TileEntity) {
										NetworkHooks.openGui((ServerPlayerEntity)playerIn, (OfficeDesk_TileEntity)tile, pos); }
								} }
							
							else { //!south_Replaceable
								CMEvents.textIsBlocked(worldIn, pos, playerIn);
								return ActionResultType.SUCCESS; }
							break;

						case EAST:
							if (eastState.getMaterial().isReplaceable()) {
								if (eastState.getBlock() instanceof FlowingFluidBlock) {
									CMEvents.textIsBlocked(worldIn, pos, playerIn);
									return ActionResultType.SUCCESS; }
								
								else { //!east_Fluid
									if (tile instanceof OfficeDesk_TileEntity) {
										NetworkHooks.openGui((ServerPlayerEntity)playerIn, (OfficeDesk_TileEntity)tile, pos); }
								} }
							
							else { //!east_Replaceable
								CMEvents.textIsBlocked(worldIn, pos, playerIn);
								return ActionResultType.SUCCESS; }
							break;
							
						case WEST:
							if (westState.getMaterial().isReplaceable()) {
								if (westState.getBlock() instanceof FlowingFluidBlock) {
									CMEvents.textIsBlocked(worldIn, pos, playerIn);
									return ActionResultType.SUCCESS; }
								
								else { //!west_Fluid
									if (tile instanceof OfficeDesk_TileEntity) {
										NetworkHooks.openGui((ServerPlayerEntity)playerIn, (OfficeDesk_TileEntity)tile, pos); }
								} }
							
							else { //!west_Replaceable
								CMEvents.textIsBlocked(worldIn, pos, playerIn);
								return ActionResultType.SUCCESS; }
							break;
						} // switch
					} // !state.get(WATERLOGGED)
				}
				return ActionResultType.CONSUME;
			}
		}
	}
	
	private Block takeBlock(Item hItem) {
		boolean cloth03 = (hItem == Items.WHITE_CARPET) || (hItem == Items.ORANGE_CARPET) || (hItem == Items.MAGENTA_CARPET) || (hItem == Items.LIGHT_BLUE_CARPET);
		boolean cloth47 = (hItem == Items.YELLOW_CARPET) || (hItem == Items.LIME_CARPET) || (hItem == Items.PINK_CARPET) || (hItem == Items.GRAY_CARPET);
		boolean cloth811 = (hItem == Items.LIGHT_GRAY_CARPET) || (hItem == Items.CYAN_CARPET) || (hItem == Items.PURPLE_CARPET) || (hItem == Items.BLUE_CARPET);
		
		if (cloth03) { return Furniture_Blocks.DESKCLOTH_03; }
		if (cloth47) { return Furniture_Blocks.DESKCLOTH_47; }
		if (cloth811) { return Furniture_Blocks.DESKCLOTH_811; }
		else { return Furniture_Blocks.DESKCLOTH_1215; }
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof OfficeDesk_TileEntity) {
				InventoryHelper.dropItems(worldIn, pos, ((OfficeDesk_TileEntity) tileEntity).getItems());
				worldIn.updateComparatorOutputLevel(pos, this);
			}
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	protected Stat<ResourceLocation> getOpenStat() {
		return Stats.CUSTOM.get(Stats.OPEN_CHEST);
	}

	@Nullable
	public static IInventory getContainer(OfficeDesk block, BlockState state, World worldIn, BlockPos pos, boolean flag) {
		return (IInventory)worldIn.getTileEntity(pos);
	}

	public TileEntityMerger.ICallbackWrapper<? extends OfficeDesk_TileEntity> combine(BlockState state, World worldIn, BlockPos pos, boolean flag) {
		return TileEntityMerger.ICallback::func_225537_b_;
	}

	@OnlyIn(Dist.CLIENT)
	public static TileEntityMerger.ICallback<OfficeDesk_TileEntity, Float2FloatFunction> opennessCombiner(final IChestLid lis) {
		return new TileEntityMerger.ICallback<OfficeDesk_TileEntity, Float2FloatFunction>() {
			public Float2FloatFunction func_225539_a_(OfficeDesk_TileEntity tileEntity_1, OfficeDesk_TileEntity tileEntity_2) {
				return (p_226921_2_) -> {
					return Math.max(tileEntity_1.getLidAngle(p_226921_2_), tileEntity_2.getLidAngle(p_226921_2_));
				};
			}

			public Float2FloatFunction func_225538_a_(OfficeDesk_TileEntity tileEntity) {
				return tileEntity::getLidAngle;
			}

			public Float2FloatFunction func_225537_b_() {
				return lis::getLidAngle;
			}
		};
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new OfficeDesk_TileEntity();
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState state, World worldIn, BlockPos pos) {
		return Container.calcRedstoneFromInventory(getContainer(this, state, worldIn, pos, false));
	}
	
	@Nullable
	private Direction candidatePartnerFacing(BlockItemUseContext context, Direction direct) {
		BlockState state = context.getWorld().getBlockState(context.getPos().offset(direct));
		return state.getBlock() == this && state.get(TYPE) == ChestType.SINGLE ? state.get(H_FACING) : null;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		PlayerEntity playerIn = context.getPlayer();
		
		Direction facing = playerIn.getHorizontalFacing();
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		BlockState State1 = this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(STAGE_1_3, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER));
				
		boolean WE = worldIn.getBlockState(new BlockPos(x + 1, y, z)).isReplaceable(context) && worldIn.getBlockState(new BlockPos(x - 1, y, z)).isReplaceable(context);
		boolean NS = worldIn.getBlockState(new BlockPos(x, y, z + 1)).isReplaceable(context) && worldIn.getBlockState(new BlockPos(x, y, z - 1)).isReplaceable(context);

		if (facing == Direction.NORTH && WE) { return State1; }
		if (facing == Direction.SOUTH && WE) { return State1; }
		if (facing == Direction.EAST && NS) { return State1; }
		if (facing == Direction.WEST && NS) { return State1; }

		else { 
			CMEvents.textNoPlace(context.getWorld(), context.getPos(), context.getPlayer());
			return null; }
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		Direction facing = placer.getHorizontalFacing();
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		BlockState State2 = this.getDefaultState().with(H_FACING, state.get(H_FACING)).with(STAGE_1_3, Integer.valueOf(2));
		BlockState State3 = this.getDefaultState().with(H_FACING, state.get(H_FACING)).with(STAGE_1_3, Integer.valueOf(3));

		switch (facing) {
		case NORTH :
		default :
			worldIn.setBlockState(new BlockPos(x - 1, y, z), State2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
			worldIn.setBlockState(new BlockPos(x + 1, y, z), State3.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
			break;

		case SOUTH :
			worldIn.setBlockState(new BlockPos(x - 1, y, z), State3.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
			worldIn.setBlockState(new BlockPos(x + 1, y, z), State2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
			break;

		case EAST :
			worldIn.setBlockState(new BlockPos(x, y, z - 1), State2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
			worldIn.setBlockState(new BlockPos(x, y, z + 1), State3.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
			break;
			
		case WEST :
			worldIn.setBlockState(new BlockPos(x, y, z - 1), State3.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
			worldIn.setBlockState(new BlockPos(x, y, z + 1), State2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
			break;
		} // direction
		
		int i = state.get(STAGE_1_3);
		if (i != 1) {
			if (stack.hasDisplayName()) {
				TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof OfficeDesk_TileEntity) {
				((OfficeDesk_TileEntity)tileEntity).setCustomName(stack.getDisplayName()); }
			}
		}
	}

	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(H_FACING, rotation.rotate(state.get(H_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(H_FACING)));
	}

	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_3, WATERLOGGED, OPEN, TYPE);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		int i = state.get(STAGE_1_3);

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
	
	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType path) {
		return false;
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	@Override
	public int getHarvestLevel(BlockState state) {
		return 0;
	}
	
	/* Destroy a DoubleBlock from DoublePlantBlock.class */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		Direction direction = state.get(H_FACING);
		int i = state.get(STAGE_1_3);
		
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
	}
}
