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

public class Kamoi_DarkOak extends Base_Kamoi {

	public Kamoi_DarkOak(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean stone = (hItem instanceof Shikkui_Slab);
		boolean wood = (hItem == Items.OAK_SLAB || hItem == Items.SPRUCE_SLAB || hItem == Items.BIRCH_SLAB || 
				hItem == Items.JUNGLE_SLAB || hItem == Items.ACACIA_SLAB || hItem == Items.DARK_OAK_SLAB || hItem instanceof Seasonal_Slab150);

		if (!playerIn.isSneaking()) {
			if (stone) {
				CMEvents.consume1_seStoneP(worldIn, pos, playerIn, hand);
				
				if (hItem == Items_Wablock.DIRTWALL_SH) {
					worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_dirt_doak.getDefaultState()
							.with(Kamoi_DirtWall.H_FACING, state.get(H_FACING))
							.with(Kamoi_DirtWall.STAGE_1_4, state.get(STAGE_1_4)), 3); }

				else {
					worldIn.setBlockState(pos, this.takeShikkui(hItem).getDefaultState()
							.with(Base_KamoiPlaster.H_FACING, state.get(H_FACING))
							.with(Base_KamoiPlaster.STAGE_1_4, state.get(STAGE_1_4)), 3); }
				return ActionResultType.SUCCESS; }

			if (wood) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);

				worldIn.setBlockState(pos, this.takeWood(hItem).getDefaultState()
						.with(Base_KamoiPlank.H_FACING, state.get(H_FACING))
						.with(Base_KamoiPlank.STAGE_1_4, state.get(STAGE_1_4)), 3);
				return ActionResultType.SUCCESS; }
		}

		/** Form changes. **/
		if (playerIn.isSneaking() && hStack.isEmpty()) {
			CMEvents.soundWoodPlace(worldIn, pos);
			worldIn.setBlockState(pos, state.cycle(STAGE_1_4));
			return ActionResultType.SUCCESS;
		}
		
		return ActionResultType.PASS;
	}

	private Block takeShikkui (Item hItem) {
		if (hItem == Items_Wablock.SHIKKUI_SH_white) { return KamoiPlaster_Blocks.KAMOI_white_doak; } //0
		if (hItem == Items_Wablock.SHIKKUI_SH_orange) { return KamoiPlaster_Blocks.KAMOI_orange_doak; } //1
		if (hItem == Items_Wablock.SHIKKUI_SH_magenta) { return KamoiPlaster_Blocks.KAMOI_magenta_doak; } //2
		if (hItem == Items_Wablock.SHIKKUI_SH_lightb) { return KamoiPlaster_Blocks.KAMOI_lightb_doak; } //3
		if (hItem == Items_Wablock.SHIKKUI_SH_yellow) { return KamoiPlaster_Blocks.KAMOI_yellow_doak; } //4
		if (hItem == Items_Wablock.SHIKKUI_SH_lime) { return KamoiPlaster_Blocks.KAMOI_lime_doak; } //5
		if (hItem == Items_Wablock.SHIKKUI_SH_pink) { return KamoiPlaster_Blocks.KAMOI_pink_doak; } //6
		if (hItem == Items_Wablock.SHIKKUI_SH_gray) { return KamoiPlaster_Blocks.KAMOI_gray_doak; } //7
		if (hItem == Items_Wablock.SHIKKUI_SH_lightg) { return KamoiPlaster_Blocks.KAMOI_lightg_doak; } //8
		if (hItem == Items_Wablock.SHIKKUI_SH_cyan) { return KamoiPlaster_Blocks.KAMOI_cyan_doak; } //9
		if (hItem == Items_Wablock.SHIKKUI_SH_purple) { return KamoiPlaster_Blocks.KAMOI_purple_doak; } //10
		if (hItem == Items_Wablock.SHIKKUI_SH_blue) { return KamoiPlaster_Blocks.KAMOI_blue_doak; } //11
		if (hItem == Items_Wablock.SHIKKUI_SH_brown) { return KamoiPlaster_Blocks.KAMOI_brown_doak; } //12
		if (hItem == Items_Wablock.SHIKKUI_SH_green) { return KamoiPlaster_Blocks.KAMOI_green_doak; } //13
		if (hItem == Items_Wablock.SHIKKUI_SH_red) { return KamoiPlaster_Blocks.KAMOI_red_doak; } //14
		else { return KamoiPlaster_Blocks.KAMOI_black_doak; } //15
	}

	private Block takeWood (Item hItem) {
		if (hItem == Items.OAK_SLAB) { return KamoiPlanks_Blocks.KAMOI_oak_doak; }
		if (hItem == Items.SPRUCE_SLAB) { return KamoiPlanks_Blocks.KAMOI_spru_doak; }
		if (hItem == Items.BIRCH_SLAB) { return KamoiPlanks_Blocks.KAMOI_bir_doak; }
		if (hItem == Items.JUNGLE_SLAB) { return KamoiPlanks_Blocks.KAMOI_jun_doak; }
		if (hItem == Items.ACACIA_SLAB) { return KamoiPlanks_Blocks.KAMOI_aca_doak; }
		if (hItem == Items.DARK_OAK_SLAB) { return KamoiPlanks_Blocks.KAMOI_doak_doak; }
		if (hItem == Items_Seasonal.SAKURA_slabhalf) { return KamoiPlanks_Blocks.KAMOI_saku_doak; }
		if (hItem == Items_Seasonal.KAEDE_slabhalf) { return KamoiPlanks_Blocks.KAMOI_kae_doak; }
		else { return KamoiPlanks_Blocks.KAMOI_ich_doak; }
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_WallPanel.PILLARSLAB_doak);
	}
}
