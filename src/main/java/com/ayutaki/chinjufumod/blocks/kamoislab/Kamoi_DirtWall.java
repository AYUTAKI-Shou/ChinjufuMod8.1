package com.ayutaki.chinjufumod.blocks.kamoislab;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_WallPane;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Kamoi_DirtWall extends Abstract_KamoiStone {

	public Kamoi_DirtWall(String name) {
		super(name);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack());
		stack.add(new ItemStack(Items_Wablock.DIRTWALL_SH, 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_oak) {
			return new ItemStack(Items_WallPane.PILLARSLAB, 1, 0); }
		
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_spru) {
			return new ItemStack(Items_WallPane.PILLARSLAB, 1, 1); }

		if (this == KamoiPlaster_Blocks.KAMOI_dirt_bir) {
			return new ItemStack(Items_WallPane.PILLARSLAB, 1, 2); }

		if (this == KamoiPlaster_Blocks.KAMOI_dirt_jun) {
			return new ItemStack(Items_WallPane.PILLARSLAB, 1, 3); }

		if (this == KamoiPlaster_Blocks.KAMOI_dirt_aca) {
			return new ItemStack(Items_WallPane.PILLARSLAB, 1, 4); }

		if (this == KamoiPlaster_Blocks.KAMOI_dirt_doak) {
			return new ItemStack(Items_WallPane.PILLARSLAB, 1, 5); }

		if (this == KamoiPlaster_Blocks.KAMOI_dirt_sakura) {
			return new ItemStack(Items_Seasonal.PILLARSLAB_s, 1, 0); }

		if (this == KamoiPlaster_Blocks.KAMOI_dirt_kaede) {
			return new ItemStack(Items_Seasonal.PILLARSLAB_s, 1, 1); }

		else { return new ItemStack(Items_Seasonal.PILLARSLAB_s, 1, 2); }
	}
}
