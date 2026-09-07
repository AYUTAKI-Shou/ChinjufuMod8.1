package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CafeTable extends BaseUnitBlock {
	/* Collision */
	private static final VoxelShape TTTT = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	private static final VoxelShape FFFF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(7.0D, 1.0D, 7.0D, 9.0D, 14.0D, 9.0D),
			Block.box(7.0D, 0.0D, 1.0D, 9.0D, 1.0D, 15.0D),
			Block.box(1.0D, 0.0D, 7.0D, 7.0D, 1.0D, 9.0D),
			Block.box(9.0D, 0.0D, 7.0D, 15.0D, 1.0D, 9.0D));

	private static final VoxelShape TTFF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(2.5D, 0.0D, 11.0D, 5.0D, 14.0D, 13.5D));
	private static final VoxelShape FTFT = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(11.0D, 0.0D, 11.0D, 13.5D, 14.0D, 13.5D));
	private static final VoxelShape TFTF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(2.5D, 0.0D, 2.5D, 5.0D, 14.0D, 5.0D));
	private static final VoxelShape FFTT = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(11.0D, 0.0D, 2.5D, 13.5D, 14.0D, 5.0D));

	private static final VoxelShape FTFF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 13.0D, 3.0D, 14.0D, 15.0D),
			Block.box(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D));
	private static final VoxelShape FFTF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D),
			Block.box(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D));
	private static final VoxelShape TFFF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D),
			Block.box(1.0D, 0.0D, 13.0D, 3.0D, 14.0D, 15.0D));
	private static final VoxelShape FFFT = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D),
			Block.box(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D));
	
	public CafeTable(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {	
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		boolean TEATABLE = (this == Unit_Blocks.TEATABLE.get());
		boolean carpets = (hItem == Items.WHITE_CARPET) || (hItem == Items.ORANGE_CARPET) || (hItem == Items.MAGENTA_CARPET) || (hItem == Items.LIGHT_BLUE_CARPET) ||
				(hItem == Items.YELLOW_CARPET) || (hItem == Items.LIME_CARPET) || (hItem == Items.PINK_CARPET) || (hItem == Items.GRAY_CARPET) ||
				(hItem == Items.LIGHT_GRAY_CARPET) || (hItem == Items.CYAN_CARPET) || (hItem == Items.PURPLE_CARPET) || (hItem == Items.BLUE_CARPET) ||
				(hItem == Items.BROWN_CARPET) || (hItem == Items.GREEN_CARPET) || (hItem == Items.RED_CARPET) || (hItem == Items.BLACK_CARPET);

		if (TEATABLE) {
			return super.useItemOn(stack, state, worldIn, pos, playerIn, hand, hit); }
		
		else {
			if (carpets) {
				CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand);
				
				Block clothTable = (state.getValue(WHICH))? takeSub() : takeBlock();
				worldIn.setBlock(pos, clothTable.defaultBlockState()
						.setValue(BaseClothTable.STAGE_0_15, Integer.valueOf(takeMeta(hItem)))
						.setValue(BaseClothTable.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				
				return ItemInteractionResult.SUCCESS; }
			
			else { return super.useItemOn(stack, state, worldIn, pos, playerIn, hand, hit); }
		}
	}
	
	private Block takeBlock() {
		if (this == Unit_Blocks.CAFETABLE.get()) { return Unit_Blocks.CLOTHTABLE_oak.get(); }
		if (this == Unit_Blocks.CAFETABLE_spruce.get()) { return Unit_Blocks.CLOTHTABLE_spruce.get(); }
		if (this == Unit_Blocks.CAFETABLE_birch.get()) { return Unit_Blocks.CLOTHTABLE_birch.get(); }
		if (this == Unit_Blocks.CAFETABLE_jungle.get()) { return Unit_Blocks.CLOTHTABLE_jungle.get(); }
		if (this == Unit_Blocks.CAFETABLE_acacia.get()) { return Unit_Blocks.CLOTHTABLE_acacia.get(); }
		if (this == Unit_Blocks.CAFETABLE_darkoak.get()) { return Unit_Blocks.CLOTHTABLE_darkoak.get(); }
		if (this == Unit_Blocks.CAFETABLE_mangrove.get()) { return Unit_Blocks.CLOTHTABLE_mangrove.get(); }
		if (this == Unit_Blocks.CAFETABLE_cherry.get()) { return Unit_Blocks.CLOTHTABLE_cherry.get(); }

		if (this == Unit_Blocks.CAFETABLE_sakura.get()) { return Unit_Blocks.CLOTHTABLE_sakura.get(); }
		if (this == Unit_Blocks.CAFETABLE_kaede.get()) { return Unit_Blocks.CLOTHTABLE_kaede.get(); }
		else { return Unit_Blocks.CLOTHTABLE_ichoh.get(); }
	}
	
	private Block takeSub() {
		if (this == Unit_Blocks.CAFETABLE.get()) { return Unit_Blocks.CLOTHTABLE_oaksub.get(); }
		if (this == Unit_Blocks.CAFETABLE_spruce.get()) { return Unit_Blocks.CLOTHTABLE_sprucesub.get(); }
		if (this == Unit_Blocks.CAFETABLE_birch.get()) { return Unit_Blocks.CLOTHTABLE_birchsub.get(); }
		if (this == Unit_Blocks.CAFETABLE_jungle.get()) { return Unit_Blocks.CLOTHTABLE_junglesub.get(); }
		if (this == Unit_Blocks.CAFETABLE_acacia.get()) { return Unit_Blocks.CLOTHTABLE_acaciasub.get(); }
		if (this == Unit_Blocks.CAFETABLE_darkoak.get()) { return Unit_Blocks.CLOTHTABLE_darkoaksub.get(); }
		if (this == Unit_Blocks.CAFETABLE_mangrove.get()) { return Unit_Blocks.CLOTHTABLE_mangrovesub.get(); }
		if (this == Unit_Blocks.CAFETABLE_cherry.get()) { return Unit_Blocks.CLOTHTABLE_cherrysub.get(); }

		if (this == Unit_Blocks.CAFETABLE_sakura.get()) { return Unit_Blocks.CLOTHTABLE_sakurasub.get(); }
		if (this == Unit_Blocks.CAFETABLE_kaede.get()) { return Unit_Blocks.CLOTHTABLE_kaedesub.get(); }
		else { return Unit_Blocks.CLOTHTABLE_ichohsub.get(); }
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
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
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
}
