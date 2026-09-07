package com.ayutaki.chinjufumod.items.chinjufu;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.addtab.Chinjufu_SubBlockFace1;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Bench_CT extends Chinjufu_SubBlockFace1 {

	public Bench_CT(String name) {
		super(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 150;
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_bench";
		case 1:
			return "item." + "block_bench_spru";
		case 2:
			return "item." + "block_bench_bir";
		case 3:
			return "item." + "block_bench_jun";
		case 4:
			return "item." + "block_bench_aca";
		case 5:
			return "item." + "block_bench_doak";
		case 6:
			return "item." + "block_bench_saku";
		case 7:
			return "item." + "block_bench_kae";
		case 8:
			return "item." + "block_bench_ich";
		case 9:
			return "item." + "block_sofa_leather";
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 8));
			items.add(new ItemStack(this, 1, 9));
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return Furniture_Blocks.BENCH; }
		if (k == 1) { return Furniture_Blocks.BENCH_spru; }
		if (k == 2) { return Furniture_Blocks.BENCH_bir; }
		if (k == 3) { return Furniture_Blocks.BENCH_jun; }
		if (k == 4) { return Furniture_Blocks.BENCH_aca; }
		if (k == 5) { return Furniture_Blocks.BENCH_doak; }
		if (k == 6) { return Furniture_Blocks.BENCH_saku; }
		if (k == 7) { return Furniture_Blocks.BENCH_kae; }
		if (k == 8) { return Furniture_Blocks.BENCH_ich; }
		else { return Furniture_Blocks.SOFA_leather; }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_isu.name"));
	}
}
