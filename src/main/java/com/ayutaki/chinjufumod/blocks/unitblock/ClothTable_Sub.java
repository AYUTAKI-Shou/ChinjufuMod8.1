package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ClothTable_Sub extends BaseClothTable {

	public ClothTable_Sub(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		if (hStack.isEmpty()) {
			if (playerIn.isCrouching()) {
				CMEvents.soundWoodPlace(worldIn, pos);
				worldIn.setBlock(pos, takeMain().defaultBlockState()
						.setValue(ClothTable.STAGE_0_15, state.getValue(STAGE_0_15))
						.setValue(ClothTable.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
			
			else { //!isCrouching
				CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, takeItem(state), 1);
				worldIn.setBlock(pos, takeBlock().defaultBlockState()
						.setValue(CafeTable.WHICH, Boolean.valueOf(true))
						.setValue(CafeTable.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
			
			return InteractionResult.SUCCESS; }
		
		else { return super.useItemOn(stack, state, worldIn, pos, playerIn, hand, hit); }
	}
	
	private Block takeMain() {
		if (this == Unit_Blocks.CLOTHTABLE_oaksub.get()) { return Unit_Blocks.CLOTHTABLE_oak.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_sprucesub.get()) { return Unit_Blocks.CLOTHTABLE_spruce.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_birchsub.get()) { return Unit_Blocks.CLOTHTABLE_birch.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_junglesub.get()) { return Unit_Blocks.CLOTHTABLE_jungle.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_acaciasub.get()) { return Unit_Blocks.CLOTHTABLE_acacia.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_darkoaksub.get()) { return Unit_Blocks.CLOTHTABLE_darkoak.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_mangrovesub.get()) { return Unit_Blocks.CLOTHTABLE_mangrove.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_cherrysub.get()) { return Unit_Blocks.CLOTHTABLE_cherry.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_paleoaksub.get()) { return Unit_Blocks.CLOTHTABLE_paleoak.get(); }
		
		if (this == Unit_Blocks.CLOTHTABLE_sakurasub.get()) { return Unit_Blocks.CLOTHTABLE_sakura.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_kaedesub.get()) { return Unit_Blocks.CLOTHTABLE_kaede.get(); }
		else { return Unit_Blocks.CLOTHTABLE_ichoh.get(); }
	}
	
	private Block takeBlock() {
		if (this == Unit_Blocks.CLOTHTABLE_oaksub.get()) { return Unit_Blocks.CAFETABLE.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_sprucesub.get()) { return Unit_Blocks.CAFETABLE_spruce.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_birchsub.get()) { return Unit_Blocks.CAFETABLE_birch.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_junglesub.get()) { return Unit_Blocks.CAFETABLE_jungle.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_acaciasub.get()) { return Unit_Blocks.CAFETABLE_acacia.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_darkoaksub.get()) { return Unit_Blocks.CAFETABLE_darkoak.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_mangrovesub.get()) { return Unit_Blocks.CAFETABLE_mangrove.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_cherrysub.get()) { return Unit_Blocks.CAFETABLE_cherry.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_paleoaksub.get()) { return Unit_Blocks.CAFETABLE_paleoak.get(); }
		
		if (this == Unit_Blocks.CLOTHTABLE_sakurasub.get()) { return Unit_Blocks.CAFETABLE_sakura.get(); }
		if (this == Unit_Blocks.CLOTHTABLE_kaedesub.get()) { return Unit_Blocks.CAFETABLE_kaede.get(); }
		else { return Unit_Blocks.CAFETABLE_ichoh.get(); }
	}
	
	private Item takeItem(BlockState state) {
		int i = state.getValue(STAGE_0_15);
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
