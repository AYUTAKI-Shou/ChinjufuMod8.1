package com.ayutaki.chinjufumod.blocks.kamoislab;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class KamoiPlaster_Kaede extends Base_KamoiPlaster {

	public KamoiPlaster_Kaede(String name) {
		super(name);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		if (this == KamoiPlaster_Blocks.KAMOI_white_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_white, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_orange_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_orange, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_magenta_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_magenta, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_lightb_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_lightb, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_yellow_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_yellow, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_lime_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_lime, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_pink_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_pink, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_gray_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_gray, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_lightg_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_lightg, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_cyan_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_cyan, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_purple_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_purple, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_blue_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_blue, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_brown_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_brown, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_green_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_green, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_red_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_red, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_black_kaede) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_black, 1, 0)); }
		
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Seasonal.PILLARSLAB_s, 1, 1);
	}
}
