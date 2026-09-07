package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class ClothTable extends BaseClothTable {

	public ClothTable(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (hStack.isEmpty()) {
			if (playerIn.isSneaking()) {
				CMEvents.soundWoodPlace(worldIn, pos);
				worldIn.setBlockState(pos, takeSub().getDefaultState()
						.with(ClothTable_Sub.STAGE_0_15, state.get(STAGE_0_15))
						.with(ClothTable_Sub.WATERLOGGED, state.get(WATERLOGGED)), 3); }
			
			else { // !Crouching
				CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, takeItem(state), 1);
				worldIn.setBlockState(pos, takeBlock().getDefaultState()
						.with(CafeTable.WHICH, Boolean.valueOf(false))
						.with(CafeTable.WATERLOGGED, state.get(WATERLOGGED)), 3); }
			
			return ActionResultType.SUCCESS; }
		
		else { return super.onBlockActivated(state, worldIn, pos, playerIn, hand, hit); }
	}
	
	private Block takeSub() {
		if (this == Unit_Blocks.CLOTHTABLE_oak) { return Unit_Blocks.CLOTHTABLE_oaksub; }
		if (this == Unit_Blocks.CLOTHTABLE_spruce) { return Unit_Blocks.CLOTHTABLE_sprucesub; }
		if (this == Unit_Blocks.CLOTHTABLE_birch) { return Unit_Blocks.CLOTHTABLE_birchsub; }
		if (this == Unit_Blocks.CLOTHTABLE_jungle) { return Unit_Blocks.CLOTHTABLE_junglesub; }
		if (this == Unit_Blocks.CLOTHTABLE_acacia) { return Unit_Blocks.CLOTHTABLE_acaciasub; }
		if (this == Unit_Blocks.CLOTHTABLE_darkoak) { return Unit_Blocks.CLOTHTABLE_darkoaksub; }
		if (this == Unit_Blocks.CLOTHTABLE_sakura) { return Unit_Blocks.CLOTHTABLE_sakurasub; }
		if (this == Unit_Blocks.CLOTHTABLE_kaede) { return Unit_Blocks.CLOTHTABLE_kaedesub; }
		else { return Unit_Blocks.CLOTHTABLE_ichohsub; }
	}
	
	private Block takeBlock() {
		if (this == Unit_Blocks.CLOTHTABLE_oak) { return Unit_Blocks.CAFETABLE; }
		if (this == Unit_Blocks.CLOTHTABLE_spruce) { return Unit_Blocks.CAFETABLE_spruce; }
		if (this == Unit_Blocks.CLOTHTABLE_birch) { return Unit_Blocks.CAFETABLE_birch; }
		if (this == Unit_Blocks.CLOTHTABLE_jungle) { return Unit_Blocks.CAFETABLE_jungle; }
		if (this == Unit_Blocks.CLOTHTABLE_acacia) { return Unit_Blocks.CAFETABLE_acacia; }
		if (this == Unit_Blocks.CLOTHTABLE_darkoak) { return Unit_Blocks.CAFETABLE_darkoak; }
		if (this == Unit_Blocks.CLOTHTABLE_sakura) { return Unit_Blocks.CAFETABLE_sakura; }
		if (this == Unit_Blocks.CLOTHTABLE_kaede) { return Unit_Blocks.CAFETABLE_kaede; }
		else { return Unit_Blocks.CAFETABLE_ichoh; }
	}
	
	private Item takeItem(BlockState state) {
		int i = state.get(STAGE_0_15);
		if (i == 0) { return Items.WHITE_CARPET; }
		if (i == 1) { return Items.ORANGE_CARPET; }
		if (i == 2) { return Items.MAGENTA_CARPET; }
		if (i == 3) { return Items.LIGHT_BLUE_CARPET; }
		if (i == 4) { return Items.YELLOW_CARPET; }
		if (i == 5) { return Items.LIME_CARPET; }
		if (i == 6) { return Items.PINK_CARPET; }
		if (i == 7) { return Items.GRAY_CARPET; }
		if (i == 8) { return Items.LIGHT_GRAY_CARPET; }
		if (i == 9) { return Items.CYAN_CARPET; }
		if (i == 10) { return Items.PURPLE_CARPET; }
		if (i == 11) { return Items.BLUE_CARPET; }
		if (i == 12) { return Items.BROWN_CARPET; }
		if (i == 13) { return Items.GREEN_CARPET; }
		if (i == 14) { return Items.RED_CARPET; }
		else { return Items.BLACK_CARPET; }
	}
}
