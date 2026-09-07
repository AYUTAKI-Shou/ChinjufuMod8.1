package com.ayutaki.chinjufumod.blocks.kamoi;

import com.ayutaki.chinjufumod.blocks.kamoislab.Base_KamoiPlank;
import com.ayutaki.chinjufumod.blocks.kamoislab.Base_KamoiPlaster;
import com.ayutaki.chinjufumod.blocks.kamoislab.Kamoi_DirtWall;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.Seasonal_Slab150;
import com.ayutaki.chinjufumod.items.fuel.Shikkui_Slab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_WallPanel;
import com.ayutaki.chinjufumod.registry.KamoiPlanks_Blocks;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;

import net.minecraft.block.AbstractBlock;
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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Kamoi_Jungle extends Base_Kamoi {

	public Kamoi_Jungle(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		boolean stone = (hItem instanceof Shikkui_Slab);
		boolean wood = (hItem == Items.OAK_SLAB || hItem == Items.SPRUCE_SLAB || hItem == Items.BIRCH_SLAB || 
				hItem == Items.JUNGLE_SLAB || hItem == Items.ACACIA_SLAB || hItem == Items.DARK_OAK_SLAB || hItem instanceof Seasonal_Slab150);

		if (!playerIn.isCrouching()) {
			if (stone) {
				CMEvents.consume1_seStoneP(worldIn, pos, playerIn, hand);
				
				if (hItem == Items_Wablock.DIRTWALL_SH) {
					worldIn.setBlock(pos, KamoiPlaster_Blocks.KAMOI_dirt_jun.defaultBlockState()
							.setValue(Kamoi_DirtWall.H_FACING, state.getValue(H_FACING))
							.setValue(Kamoi_DirtWall.STAGE_1_4, state.getValue(STAGE_1_4)), 3); }

				else {
					worldIn.setBlock(pos, this.takeShikkui(hItem).defaultBlockState()
							.setValue(Base_KamoiPlaster.H_FACING, state.getValue(H_FACING))
							.setValue(Base_KamoiPlaster.STAGE_1_4, state.getValue(STAGE_1_4)), 3); }
				return ActionResultType.SUCCESS; }

			if (wood) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				
				worldIn.setBlock(pos, this.takeWood(hItem).defaultBlockState()
						.setValue(Base_KamoiPlank.H_FACING, state.getValue(H_FACING))
						.setValue(Base_KamoiPlank.STAGE_1_4, state.getValue(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; }
		}

		/** Form changes. **/
		if (playerIn.isCrouching() && hStack.isEmpty()) {
			CMEvents.soundWoodPlace(worldIn, pos);
			worldIn.setBlock(pos, state.cycle(STAGE_1_4), 3);
			return ActionResultType.SUCCESS;
		}
		
		return ActionResultType.PASS;
	}

	private Block takeShikkui(Item hItem) {
		if (hItem == Items_Wablock.SHIKKUI_SH_white) { return KamoiPlaster_Blocks.KAMOI_white_jun; } //0
		if (hItem == Items_Wablock.SHIKKUI_SH_orange) { return KamoiPlaster_Blocks.KAMOI_orange_jun; } //1
		if (hItem == Items_Wablock.SHIKKUI_SH_magenta) { return KamoiPlaster_Blocks.KAMOI_magenta_jun; } //2
		if (hItem == Items_Wablock.SHIKKUI_SH_lightb) { return KamoiPlaster_Blocks.KAMOI_lightb_jun; } //3
		if (hItem == Items_Wablock.SHIKKUI_SH_yellow) { return KamoiPlaster_Blocks.KAMOI_yellow_jun; } //4
		if (hItem == Items_Wablock.SHIKKUI_SH_lime) { return KamoiPlaster_Blocks.KAMOI_lime_jun; } //5
		if (hItem == Items_Wablock.SHIKKUI_SH_pink) { return KamoiPlaster_Blocks.KAMOI_pink_jun; } //6
		if (hItem == Items_Wablock.SHIKKUI_SH_gray) { return KamoiPlaster_Blocks.KAMOI_gray_jun; } //7
		if (hItem == Items_Wablock.SHIKKUI_SH_lightg) { return KamoiPlaster_Blocks.KAMOI_lightg_jun; } //8
		if (hItem == Items_Wablock.SHIKKUI_SH_cyan) { return KamoiPlaster_Blocks.KAMOI_cyan_jun; } //9
		if (hItem == Items_Wablock.SHIKKUI_SH_purple) { return KamoiPlaster_Blocks.KAMOI_purple_jun; } //10
		if (hItem == Items_Wablock.SHIKKUI_SH_blue) { return KamoiPlaster_Blocks.KAMOI_blue_jun; } //11
		if (hItem == Items_Wablock.SHIKKUI_SH_brown) { return KamoiPlaster_Blocks.KAMOI_brown_jun; } //12
		if (hItem == Items_Wablock.SHIKKUI_SH_green) { return KamoiPlaster_Blocks.KAMOI_green_jun; } //13
		if (hItem == Items_Wablock.SHIKKUI_SH_red) { return KamoiPlaster_Blocks.KAMOI_red_jun; } //14
		else { return KamoiPlaster_Blocks.KAMOI_black_jun; } //15
	}

	private Block takeWood(Item hItem) {
		if (hItem == Items.OAK_SLAB) { return KamoiPlanks_Blocks.KAMOI_oak_jun; }
		if (hItem == Items.SPRUCE_SLAB) { return KamoiPlanks_Blocks.KAMOI_spru_jun; }
		if (hItem == Items.BIRCH_SLAB) { return KamoiPlanks_Blocks.KAMOI_bir_jun; }
		if (hItem == Items.JUNGLE_SLAB) { return KamoiPlanks_Blocks.KAMOI_jun_jun; }
		if (hItem == Items.ACACIA_SLAB) { return KamoiPlanks_Blocks.KAMOI_aca_jun; }
		if (hItem == Items.DARK_OAK_SLAB) { return KamoiPlanks_Blocks.KAMOI_doak_jun; }
		if (hItem == Items_Seasonal.SAKURA_slabhalf) { return KamoiPlanks_Blocks.KAMOI_saku_jun; }
		if (hItem == Items_Seasonal.KAEDE_slabhalf) { return KamoiPlanks_Blocks.KAMOI_kae_jun; }
		else { return KamoiPlanks_Blocks.KAMOI_ich_jun; }
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_WallPanel.PILLARSLAB_jun);
	}
}
