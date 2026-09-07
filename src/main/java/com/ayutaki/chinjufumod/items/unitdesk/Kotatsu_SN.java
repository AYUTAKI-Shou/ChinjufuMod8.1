package com.ayutaki.chinjufumod.items.unitdesk;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Kotatsu_SN extends TN_SubUnitDesk {

	public Kotatsu_SN(String name) {
		super(name, Unit_Blocks.KOTATSU);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 200;
	}
	
	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_kotatsu";
		case 1:
			return "item." + "block_kotatsu_spruce";
		case 2:
			return "item." + "block_kotatsu_birch";
		case 3:
			return "item." + "block_kotatsu_jungle";
		case 4:
			return "item." + "block_kotatsu_acacia";
		case 5:
			return "item." + "block_kotatsu_darkoak";
		case 6:
			return "item." + "block_kotatsu_sakura";
		case 7:
			return "item." + "block_kotatsu_kaede";
		case 8:
			return "item." + "block_kotatsu_ichoh";
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
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Unit_Blocks.KOTATSU;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_kotatsu.name"));
	}
}
