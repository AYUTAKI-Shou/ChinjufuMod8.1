package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CafeTable extends BaseUnitBlock {
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

	public CafeTable(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean TEATABLE = (this == Unit_Blocks.TEATABLE);
		boolean carpets = (hItem.isIn(ItemTags.CARPETS));
		
		if (TEATABLE) {	
			return super.onBlockActivated(state, worldIn, pos, playerIn, hand, hit); }
		
		else {
			if (carpets) {
				CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand);
				
				Block clothTable = (state.get(WHICH))? takeSub() : takeBlock();
				worldIn.setBlockState(pos, clothTable.getDefaultState()
						.with(BaseClothTable.STAGE_0_15, Integer.valueOf(takeMeta(hItem)))
						.with(BaseClothTable.WATERLOGGED, state.get(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
			
			else { return super.onBlockActivated(state, worldIn, pos, playerIn, hand, hit); }
		}
	}
	
	private Block takeBlock() {
		if (this == Unit_Blocks.CAFETABLE) { return Unit_Blocks.CLOTHTABLE_oak; }
		if (this == Unit_Blocks.CAFETABLE_spruce) { return Unit_Blocks.CLOTHTABLE_spruce; }
		if (this == Unit_Blocks.CAFETABLE_birch) { return Unit_Blocks.CLOTHTABLE_birch; }
		if (this == Unit_Blocks.CAFETABLE_jungle) { return Unit_Blocks.CLOTHTABLE_jungle; }
		if (this == Unit_Blocks.CAFETABLE_acacia) { return Unit_Blocks.CLOTHTABLE_acacia; }
		if (this == Unit_Blocks.CAFETABLE_darkoak) { return Unit_Blocks.CLOTHTABLE_darkoak; }
		if (this == Unit_Blocks.CAFETABLE_sakura) { return Unit_Blocks.CLOTHTABLE_sakura; }
		if (this == Unit_Blocks.CAFETABLE_kaede) { return Unit_Blocks.CLOTHTABLE_kaede; }
		else { return Unit_Blocks.CLOTHTABLE_ichoh; }
	}
	
	private Block takeSub() {
		if (this == Unit_Blocks.CAFETABLE) { return Unit_Blocks.CLOTHTABLE_oaksub; }
		if (this == Unit_Blocks.CAFETABLE_spruce) { return Unit_Blocks.CLOTHTABLE_sprucesub; }
		if (this == Unit_Blocks.CAFETABLE_birch) { return Unit_Blocks.CLOTHTABLE_birchsub; }
		if (this == Unit_Blocks.CAFETABLE_jungle) { return Unit_Blocks.CLOTHTABLE_junglesub; }
		if (this == Unit_Blocks.CAFETABLE_acacia) { return Unit_Blocks.CLOTHTABLE_acaciasub; }
		if (this == Unit_Blocks.CAFETABLE_darkoak) { return Unit_Blocks.CLOTHTABLE_darkoaksub; }
		if (this == Unit_Blocks.CAFETABLE_sakura) { return Unit_Blocks.CLOTHTABLE_sakurasub; }
		if (this == Unit_Blocks.CAFETABLE_kaede) { return Unit_Blocks.CLOTHTABLE_kaedesub; }
		else { return Unit_Blocks.CLOTHTABLE_ichohsub; }
	}
	
	private int takeMeta(Item hItem) {
		if (hItem == Items.WHITE_CARPET) { return 0; }
		if (hItem == Items.ORANGE_CARPET) { return 1; }
		if (hItem == Items.MAGENTA_CARPET) { return 2; }
		if (hItem == Items.LIGHT_BLUE_CARPET) { return 3; }
		if (hItem == Items.YELLOW_CARPET) { return 4; }
		if (hItem == Items.LIME_CARPET) { return 5; }
		if (hItem == Items.PINK_CARPET) { return 6; }
		if (hItem == Items.GRAY_CARPET) { return 7; }
		if (hItem == Items.LIGHT_GRAY_CARPET) { return 8; }
		if (hItem == Items.CYAN_CARPET) { return 9; }
		if (hItem == Items.PURPLE_CARPET) { return 10; }
		if (hItem == Items.BLUE_CARPET) { return 11; }
		if (hItem == Items.BROWN_CARPET) { return 12; }
		if (hItem == Items.GREEN_CARPET) { return 13; }
		if (hItem == Items.RED_CARPET) { return 14; }
		else { return 15; }
	}
	
	/* Gives a value when placed. */
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
}
