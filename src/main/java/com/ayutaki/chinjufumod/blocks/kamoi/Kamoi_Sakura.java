package com.ayutaki.chinjufumod.blocks.kamoi;

import com.ayutaki.chinjufumod.blocks.kamoislab.Base_KamoiPlank;
import com.ayutaki.chinjufumod.blocks.kamoislab.Base_KamoiPlaster;
import com.ayutaki.chinjufumod.blocks.kamoislab.Kamoi_DirtWall;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.Seasonal_Slab150;
import com.ayutaki.chinjufumod.items.fuel.Shikkui_Slab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.KamoiPlanks_Blocks;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Kamoi_Sakura extends Base_Kamoi {

	public Kamoi_Sakura(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		boolean stone = (hItem instanceof Shikkui_Slab);
		boolean wood = (hItem == Items.OAK_SLAB || hItem == Items.SPRUCE_SLAB || hItem == Items.BIRCH_SLAB || 
				hItem == Items.JUNGLE_SLAB || hItem == Items.ACACIA_SLAB || hItem == Items.DARK_OAK_SLAB || 
				hItem == Items.MANGROVE_SLAB || hItem == Items.CHERRY_SLAB ||
				hItem instanceof Seasonal_Slab150);
		
		if (!playerIn.isCrouching()) {
			if (stone) {
				CMEvents.consume1_seStoneP(worldIn, pos, playerIn, hand);
				
				if (hItem == Items_Wablock.DIRTWALL_SH.get()) {
					worldIn.setBlock(pos, KamoiPlaster_Blocks.KAMOI_dirt_sakura.get().defaultBlockState()
							.setValue(Kamoi_DirtWall.H_FACING, state.getValue(H_FACING))
							.setValue(Kamoi_DirtWall.STAGE_1_4, state.getValue(STAGE_1_4)), 3); }
				
				else {
					worldIn.setBlock(pos, this.takeShikkui(hItem).defaultBlockState()
							.setValue(Base_KamoiPlaster.H_FACING, state.getValue(H_FACING))
							.setValue(Base_KamoiPlaster.STAGE_1_4, state.getValue(STAGE_1_4)), 3); }

				return ItemInteractionResult.SUCCESS; }
			
			if (wood) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);

				worldIn.setBlock(pos, this.takeWood(hItem).defaultBlockState()
						.setValue(Base_KamoiPlank.H_FACING, state.getValue(H_FACING))
						.setValue(Base_KamoiPlank.STAGE_1_4, state.getValue(STAGE_1_4)), 3);
				return ItemInteractionResult.SUCCESS; }
		}

		/** Form changes. **/
		if (playerIn.isCrouching() && hStack.isEmpty()) {
			CMEvents.soundWoodPlace(worldIn, pos);
			worldIn.setBlock(pos, state.cycle(STAGE_1_4), 3);
			return ItemInteractionResult.SUCCESS;
		}
		
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	private Block takeShikkui (Item hItem) {
		if (hItem == Items_Wablock.SHIKKUI_SH_white.get()) { return KamoiPlaster_Blocks.KAMOI_white_sakura.get(); } //0
		if (hItem == Items_Wablock.SHIKKUI_SH_orange.get()) { return KamoiPlaster_Blocks.KAMOI_orange_sakura.get(); } //1
		if (hItem == Items_Wablock.SHIKKUI_SH_magenta.get()) { return KamoiPlaster_Blocks.KAMOI_magenta_sakura.get(); } //2
		if (hItem == Items_Wablock.SHIKKUI_SH_lightb.get()) { return KamoiPlaster_Blocks.KAMOI_lightb_sakura.get(); } //3
		if (hItem == Items_Wablock.SHIKKUI_SH_yellow.get()) { return KamoiPlaster_Blocks.KAMOI_yellow_sakura.get(); } //4
		if (hItem == Items_Wablock.SHIKKUI_SH_lime.get()) { return KamoiPlaster_Blocks.KAMOI_lime_sakura.get(); } //5
		if (hItem == Items_Wablock.SHIKKUI_SH_pink.get()) { return KamoiPlaster_Blocks.KAMOI_pink_sakura.get(); } //6
		if (hItem == Items_Wablock.SHIKKUI_SH_gray.get()) { return KamoiPlaster_Blocks.KAMOI_gray_sakura.get(); } //7
		if (hItem == Items_Wablock.SHIKKUI_SH_lightg.get()) { return KamoiPlaster_Blocks.KAMOI_lightg_sakura.get(); } //8
		if (hItem == Items_Wablock.SHIKKUI_SH_cyan.get()) { return KamoiPlaster_Blocks.KAMOI_cyan_sakura.get(); } //9
		if (hItem == Items_Wablock.SHIKKUI_SH_purple.get()) { return KamoiPlaster_Blocks.KAMOI_purple_sakura.get(); } //10
		if (hItem == Items_Wablock.SHIKKUI_SH_blue.get()) { return KamoiPlaster_Blocks.KAMOI_blue_sakura.get(); } //11
		if (hItem == Items_Wablock.SHIKKUI_SH_brown.get()) { return KamoiPlaster_Blocks.KAMOI_brown_sakura.get(); } //12
		if (hItem == Items_Wablock.SHIKKUI_SH_green.get()) { return KamoiPlaster_Blocks.KAMOI_green_sakura.get(); } //13
		if (hItem == Items_Wablock.SHIKKUI_SH_red.get()) { return KamoiPlaster_Blocks.KAMOI_red_sakura.get(); } //14
		else { return KamoiPlaster_Blocks.KAMOI_black_sakura.get(); } //15
	}

	private Block takeWood (Item hItem) {
		if (hItem == Items.OAK_SLAB) { return KamoiPlanks_Blocks.KAMOI_oak_sakura.get(); }
		if (hItem == Items.SPRUCE_SLAB) { return KamoiPlanks_Blocks.KAMOI_spru_sakura.get(); }
		if (hItem == Items.BIRCH_SLAB) { return KamoiPlanks_Blocks.KAMOI_bir_sakura.get(); }
		if (hItem == Items.JUNGLE_SLAB) { return KamoiPlanks_Blocks.KAMOI_jun_sakura.get(); }
		if (hItem == Items.ACACIA_SLAB) { return KamoiPlanks_Blocks.KAMOI_aca_sakura.get(); }
		if (hItem == Items.DARK_OAK_SLAB) { return KamoiPlanks_Blocks.KAMOI_doak_sakura.get(); }
		if (hItem == Items.MANGROVE_SLAB) { return KamoiPlanks_Blocks.KAMOI_mangrove_sakura.get(); }
		if (hItem == Items.CHERRY_SLAB) { return KamoiPlanks_Blocks.KAMOI_cherry_sakura.get(); }

		if (hItem == Items_Seasonal.SAKURA_slabhalf.get()) { return KamoiPlanks_Blocks.KAMOI_saku_sakura.get(); }
		if (hItem == Items_Seasonal.KAEDE_slabhalf.get()) { return KamoiPlanks_Blocks.KAMOI_kae_sakura.get(); }
		else { return KamoiPlanks_Blocks.KAMOI_ich_sakura.get(); }
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Seasonal.PILLARSLAB_saku.get());
	}
}
