package com.ayutaki.chinjufumod.blocks.unitblock;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BaseClothTable extends Abstract_WaterLogged {
	/* Property */
	public static final IntegerProperty STAGE_0_15 = IntegerProperty.create("color", 0, 15);
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");

	/* Collision */
	private static final VoxelShape TTTT = Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	private static final VoxelShape FFFF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(7.0D, 1.0D, 7.0D, 9.0D, 14.0D, 9.0D),
			Block.makeCuboidShape(7.0D, 0.0D, 1.0D, 9.0D, 1.0D, 15.0D),
			Block.makeCuboidShape(1.0D, 0.0D, 7.0D, 7.0D, 1.0D, 9.0D),
			Block.makeCuboidShape(9.0D, 0.0D, 7.0D, 15.0D, 1.0D, 9.0D));

	private static final VoxelShape TTFF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(2.5D, 0.0D, 11.0D, 5.0D, 14.0D, 13.5D));
	private static final VoxelShape FTFT = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(11.0D, 0.0D, 11.0D, 13.5D, 14.0D, 13.5D));
	private static final VoxelShape TFTF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(2.5D, 0.0D, 2.5D, 5.0D, 14.0D, 5.0D));
	private static final VoxelShape FFTT = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(11.0D, 0.0D, 2.5D, 13.5D, 14.0D, 5.0D));

	private static final VoxelShape FTFF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(1.0D, 0.0D, 13.0D, 3.0D, 14.0D, 15.0D),
			Block.makeCuboidShape(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D));
	private static final VoxelShape FFTF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D),
			Block.makeCuboidShape(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D));
	private static final VoxelShape TFFF = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D),
			Block.makeCuboidShape(1.0D, 0.0D, 13.0D, 3.0D, 14.0D, 15.0D));
	private static final VoxelShape FFFT = VoxelShapes.or(Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D),
			Block.makeCuboidShape(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D));
	
	public BaseClothTable(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false))
				.with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		Direction facing = playerIn.getHorizontalFacing().getOpposite();
		BlockState upState = worldIn.getBlockState(pos.up());
		IFluidState upFluid = worldIn.getFluidState(pos.up());
		
		Item hItem = hStack.getItem();
		boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getFluid() == Fluids.EMPTY);
		boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);

		if (success) {
			if (hItem == Items_Chinjufu.SHOUHOU_empty && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos.up(), Furniture_Blocks.NOTEBOOK.getDefaultState()
						.with(NoteBook.H_FACING, facing)
						.with(NoteBook.HAS_BOOK, Boolean.valueOf(false))
						.with(NoteBook.DOWN, Boolean.valueOf(false))
						.with(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3); }

			if (hItem == Items.BOOK && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos.up(), Furniture_Blocks.DESKBOOK_1.getDefaultState()
						.with(DeskBook1.H_FACING, facing)
						.with(DeskBook1.STAGE_1_4, Integer.valueOf(1))
						.with(DeskBook1.DOWN, Boolean.valueOf(false))
						.with(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3); }
			
			return ActionResultType.SUCCESS; }

		return ActionResultType.PASS;
	}
	
	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	
	/* Connect the blocks. */
	private boolean canConnectTo(IWorld worldIn, BlockPos source, Direction direction) {
		BlockState state = worldIn.getBlockState(source.offset(direction));
		return state.getBlock() == this;
	}

	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }
		
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST);
		return state.with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean east = state.get(EAST).booleanValue();
		boolean north = state.get(NORTH).booleanValue();
		boolean south = state.get(SOUTH).booleanValue();
		boolean west = state.get(WEST).booleanValue();

		if (east == false && north == false && south == false && west == false) { return FFFF; }
		
		if (east == true && north == true && south == false && west == false) { return TTFF; }
		if (east == false && north == true && south == false && west == true) { return FTFT; }
		if (east == true && north == false && south == true && west == false) { return TFTF; }
		if (east == false && north == false && south == true && west == true) { return FFTT; }

		if (east == false && north == true && south == false && west == false) { return FTFF; }
		if (east == true && north == false && south == false && west == false) { return TFFF; }
		if (east == false && north == false && south == false && west == true) { return FFFT; }
		if (east == false && north == false && south == true && west == false) { return FFTF; }

		else { return TTTT; }
	}
	
	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_15, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	}
	
	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) { return false; }

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) { return false; }

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) { return false; }
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(takeItem());
	}
	
	private Item takeItem() {
		if ((this == Unit_Blocks.CLOTHTABLE_oak) || (this == Unit_Blocks.CLOTHTABLE_oaksub)) { return Items_Chinjufu.CAFETABLE; }
		if ((this == Unit_Blocks.CLOTHTABLE_spruce) || (this == Unit_Blocks.CLOTHTABLE_sprucesub)) { return Items_Chinjufu.CAFETABLE_spruce; }
		if ((this == Unit_Blocks.CLOTHTABLE_birch) || (this == Unit_Blocks.CLOTHTABLE_birchsub)) { return Items_Chinjufu.CAFETABLE_birch; }
		if ((this == Unit_Blocks.CLOTHTABLE_jungle) || (this == Unit_Blocks.CLOTHTABLE_junglesub)) { return Items_Chinjufu.CAFETABLE_jungle; }
		if ((this == Unit_Blocks.CLOTHTABLE_acacia) || (this == Unit_Blocks.CLOTHTABLE_acaciasub)) { return Items_Chinjufu.CAFETABLE_acacia; }
		if ((this == Unit_Blocks.CLOTHTABLE_darkoak) || (this == Unit_Blocks.CLOTHTABLE_darkoaksub)) { return Items_Chinjufu.CAFETABLE_darkoak; }
		if ((this == Unit_Blocks.CLOTHTABLE_sakura) || (this == Unit_Blocks.CLOTHTABLE_sakurasub)) { return Items_Chinjufu.CAFETABLE_sakura; }
		if ((this == Unit_Blocks.CLOTHTABLE_kaede) || (this == Unit_Blocks.CLOTHTABLE_kaedesub)) { return Items_Chinjufu.CAFETABLE_kaede; }
		else { return Items_Chinjufu.CAFETABLE_ichoh; }
	}
}
