package com.ayutaki.chinjufumod.blocks.unitblock;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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
	private static final VoxelShape TTTT = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	private static final VoxelShape FFFF = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(7.0D, 1.0D, 7.0D, 9.0D, 14.0D, 9.0D),
			Block.box(7.0D, 0.0D, 1.0D, 9.0D, 1.0D, 15.0D),
			Block.box(1.0D, 0.0D, 7.0D, 7.0D, 1.0D, 9.0D),
			Block.box(9.0D, 0.0D, 7.0D, 15.0D, 1.0D, 9.0D));

	private static final VoxelShape TTFF = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(2.5D, 0.0D, 11.0D, 5.0D, 14.0D, 13.5D));
	private static final VoxelShape FTFT = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(11.0D, 0.0D, 11.0D, 13.5D, 14.0D, 13.5D));
	private static final VoxelShape TFTF = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(2.5D, 0.0D, 2.5D, 5.0D, 14.0D, 5.0D));
	private static final VoxelShape FFTT = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(11.0D, 0.0D, 2.5D, 13.5D, 14.0D, 5.0D));

	private static final VoxelShape FTFF = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 13.0D, 3.0D, 14.0D, 15.0D),
			Block.box(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D));
	private static final VoxelShape FFTF = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D),
			Block.box(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D));
	private static final VoxelShape TFFF = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D),
			Block.box(1.0D, 0.0D, 13.0D, 3.0D, 14.0D, 15.0D));
	private static final VoxelShape FFFT = VoxelShapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D),
			Block.box(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D));
	
	public BaseClothTable(AbstractBlock.Properties props) {
		super(props);
		registerDefaultState(this.defaultBlockState().setValue(STAGE_0_15, Integer.valueOf(0))
				.setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false))
				.setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}


	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);

		Direction facing = playerIn.getDirection().getOpposite();
		BlockState upState = worldIn.getBlockState(pos.above());
		FluidState upFluid = worldIn.getFluidState(pos.above());
		
		Item hItem = hStack.getItem();
		boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getType() == Fluids.EMPTY);
		boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
		
		if (success) {
			if (hItem == Items_Chinjufu.SHOUHOU_empty && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos.above(), Furniture_Blocks.NOTEBOOK.defaultBlockState()
						.setValue(NoteBook.H_FACING, facing)
						.setValue(NoteBook.HAS_BOOK, Boolean.valueOf(false))
						.setValue(NoteBook.DOWN, Boolean.valueOf(false))
						.setValue(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
			
			if (hItem == Items.BOOK && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos.above(), Furniture_Blocks.DESKBOOK_1.defaultBlockState()
						.setValue(DeskBook1.H_FACING, facing)
						.setValue(DeskBook1.STAGE_1_4, Integer.valueOf(1))
						.setValue(DeskBook1.DOWN, Boolean.valueOf(false))
						.setValue(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
			
			return ActionResultType.SUCCESS; }
		
		return ActionResultType.PASS;
	}
	
	/* Gives a value when placed. */
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Connect the blocks. */
	private boolean canConnectTo(IWorld worldIn, BlockPos source, Direction direction) {
		BlockState state = worldIn.getBlockState(source.relative(direction));
		return state.getBlock() == this;
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST);
		return state.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
	}
	
	/* Gives a value when placed. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean east = state.getValue(EAST).booleanValue();
		boolean north = state.getValue(NORTH).booleanValue();
		boolean south = state.getValue(SOUTH).booleanValue();
		boolean west = state.getValue(WEST).booleanValue();

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
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_15, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	}

	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 5; }

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 20; }
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.takeItem());
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
