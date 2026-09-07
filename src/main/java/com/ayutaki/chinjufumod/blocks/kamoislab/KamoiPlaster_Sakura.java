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

public class KamoiPlaster_Sakura extends Base_KamoiPlaster {

	public KamoiPlaster_Sakura(String name) {
		super(name);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		if (this == KamoiPlaster_Blocks.KAMOI_white_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_white, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_orange_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_orange, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_magenta_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_magenta, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_lightb_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_lightb, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_yellow_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_yellow, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_lime_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_lime, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_pink_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_pink, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_gray_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_gray, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_lightg_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_lightg, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_cyan_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_cyan, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_purple_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_purple, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_blue_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_blue, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_brown_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_brown, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_green_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_green, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_red_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_red, 1, 0)); }
		if (this == KamoiPlaster_Blocks.KAMOI_black_sakura) { stack.add(new ItemStack(Items_Wablock.SHIKKUI_SH_black, 1, 0)); }
		
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Seasonal.PILLARSLAB_s, 1, 0);
	}
}
