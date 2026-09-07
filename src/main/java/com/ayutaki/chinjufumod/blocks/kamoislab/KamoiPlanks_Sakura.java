package com.ayutaki.chinjufumod.blocks.kamoislab;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.KamoiPlanks_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class KamoiPlanks_Sakura extends Base_KamoiPlank {

	public KamoiPlanks_Sakura(String name) {
		super(name);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		if (this == KamoiPlanks_Blocks.KAMOI_oak_sakura) { stack.add(new ItemStack(Blocks.WOODEN_SLAB, 1, 0)); }
		if (this == KamoiPlanks_Blocks.KAMOI_spru_sakura) { stack.add(new ItemStack(Blocks.WOODEN_SLAB, 1, 1)); }
		if (this == KamoiPlanks_Blocks.KAMOI_bir_sakura) { stack.add(new ItemStack(Blocks.WOODEN_SLAB, 1, 2)); }
		if (this == KamoiPlanks_Blocks.KAMOI_jun_sakura) { stack.add(new ItemStack(Blocks.WOODEN_SLAB, 1, 3)); }
		if (this == KamoiPlanks_Blocks.KAMOI_aca_sakura) { stack.add(new ItemStack(Blocks.WOODEN_SLAB, 1, 4)); }
		if (this == KamoiPlanks_Blocks.KAMOI_doak_sakura) { stack.add(new ItemStack(Blocks.WOODEN_SLAB, 1, 5)); }
		if (this == KamoiPlanks_Blocks.KAMOI_saku_sakura) { stack.add(new ItemStack(Items_Seasonal.SAKURA_slabhalf, 1, 0)); }
		if (this == KamoiPlanks_Blocks.KAMOI_kae_sakura) { stack.add(new ItemStack(Items_Seasonal.KAEDE_slabhalf, 1, 0)); }
		if (this == KamoiPlanks_Blocks.KAMOI_ich_sakura) { stack.add(new ItemStack(Items_Seasonal.ICHOH_slabhalf, 1, 0)); }
		
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
