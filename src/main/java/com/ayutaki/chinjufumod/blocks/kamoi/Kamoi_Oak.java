package com.ayutaki.chinjufumod.blocks.kamoi;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.kamoislab.Base_KamoiPlank;
import com.ayutaki.chinjufumod.blocks.kamoislab.Base_KamoiPlaster;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.Seasonal_Slab150;
import com.ayutaki.chinjufumod.items.fuel.Shikkui_Slab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_WallPane;
import com.ayutaki.chinjufumod.registry.KamoiPlanks_Blocks;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Kamoi_Oak extends Base_Kamoi {

	public Kamoi_Oak(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItemMainhand();
		Item hItem = hStack.getItem();
		boolean stone = (hItem instanceof Shikkui_Slab);
		boolean wood = (hItem == new ItemStack(Blocks.WOODEN_SLAB).getItem() || hItem instanceof Seasonal_Slab150);

		if (!playerIn.isSneaking()) {
			if (hItem == Items_Wablock.DIRTWALL_SH) {
				CMEvents.consume1_seStoneP(worldIn, pos, playerIn, hand);

				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_dirt_oak.getDefaultState()
						.withProperty(Base_KamoiPlaster.H_FACING, state.getValue(H_FACING))
						.withProperty(Base_KamoiPlaster.STAGE_1_4, state.getValue(STAGE_1_4)));
				return true; }

			if (stone) {
				CMEvents.consume1_seStoneP(worldIn, pos, playerIn, hand);

				worldIn.setBlockState(pos, takeShikkui(hItem).getDefaultState()
						.withProperty(Base_KamoiPlaster.H_FACING, state.getValue(H_FACING))
						.withProperty(Base_KamoiPlaster.STAGE_1_4, state.getValue(STAGE_1_4)));
				return true; }

			if (wood) {
				int k = hStack.getMetadata();
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				
				worldIn.setBlockState(pos, takeWood(hItem, k).getDefaultState()
						.withProperty(Base_KamoiPlank.H_FACING, state.getValue(H_FACING))
						.withProperty(Base_KamoiPlank.STAGE_1_4, state.getValue(STAGE_1_4)));
				return true; }
		}

		/** Form changes. **/
		if (playerIn.isSneaking() && hStack.isEmpty()) {
			CMEvents.soundWoodPlace(worldIn, pos);
			worldIn.setBlockState(pos, state.cycleProperty(STAGE_1_4), 2);
			return true;
		}
		
		return false;
	}
	
	private Block takeShikkui(Item hItem) {
		if (hItem == Items_Wablock.SHIKKUI_SH_white) { return KamoiPlaster_Blocks.KAMOI_white_oak; } //0
		if (hItem == Items_Wablock.SHIKKUI_SH_orange) { return KamoiPlaster_Blocks.KAMOI_orange_oak; } //1
		if (hItem == Items_Wablock.SHIKKUI_SH_magenta) { return KamoiPlaster_Blocks.KAMOI_magenta_oak; } //2
		if (hItem == Items_Wablock.SHIKKUI_SH_lightb) { return KamoiPlaster_Blocks.KAMOI_lightb_oak; } //3
		if (hItem == Items_Wablock.SHIKKUI_SH_yellow) { return KamoiPlaster_Blocks.KAMOI_yellow_oak; } //4
		if (hItem == Items_Wablock.SHIKKUI_SH_lime) { return KamoiPlaster_Blocks.KAMOI_lime_oak; } //5
		if (hItem == Items_Wablock.SHIKKUI_SH_pink) { return KamoiPlaster_Blocks.KAMOI_pink_oak; } //6
		if (hItem == Items_Wablock.SHIKKUI_SH_gray) { return KamoiPlaster_Blocks.KAMOI_gray_oak; } //7
		if (hItem == Items_Wablock.SHIKKUI_SH_lightg) { return KamoiPlaster_Blocks.KAMOI_lightg_oak; } //8
		if (hItem == Items_Wablock.SHIKKUI_SH_cyan) { return KamoiPlaster_Blocks.KAMOI_cyan_oak; } //9
		if (hItem == Items_Wablock.SHIKKUI_SH_purple) { return KamoiPlaster_Blocks.KAMOI_purple_oak; } //10
		if (hItem == Items_Wablock.SHIKKUI_SH_blue) { return KamoiPlaster_Blocks.KAMOI_blue_oak; } //11
		if (hItem == Items_Wablock.SHIKKUI_SH_brown) { return KamoiPlaster_Blocks.KAMOI_brown_oak; } //12
		if (hItem == Items_Wablock.SHIKKUI_SH_green) { return KamoiPlaster_Blocks.KAMOI_green_oak; } //13
		if (hItem == Items_Wablock.SHIKKUI_SH_red) { return KamoiPlaster_Blocks.KAMOI_red_oak; } //14
		else { return KamoiPlaster_Blocks.KAMOI_black_oak; } //15
	}

	private Block takeWood(Item hItem, int k) {
		if (hItem == new ItemStack(Blocks.WOODEN_SLAB).getItem()) {
			
			if (k == 0) { return KamoiPlanks_Blocks.KAMOI_oak_oak; }
			if (k == 1) { return KamoiPlanks_Blocks.KAMOI_spru_oak; }
			if (k == 2) { return KamoiPlanks_Blocks.KAMOI_bir_oak; }
			if (k == 3) { return KamoiPlanks_Blocks.KAMOI_jun_oak; }
			if (k == 4) { return KamoiPlanks_Blocks.KAMOI_aca_oak; }
			else { return KamoiPlanks_Blocks.KAMOI_doak_oak; } }

		if (hItem == Items_Seasonal.SAKURA_slabhalf) { return KamoiPlanks_Blocks.KAMOI_saku_oak; }
		if (hItem == Items_Seasonal.KAEDE_slabhalf) { return KamoiPlanks_Blocks.KAMOI_kae_oak; }
		else { return KamoiPlanks_Blocks.KAMOI_ich_oak; }
	}
		
	/* Clone Item in Creative. */
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_WallPane.PILLARSLAB, 1, 0);
	}
}
