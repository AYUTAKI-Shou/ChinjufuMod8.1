package com.ayutaki.chinjufumod.items.slidedoor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.doors.Shouji_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Shouji_SN extends TN_SubSlideDoor {

	public Shouji_SN(String name) {
		super(name);
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
			return "item." + "block_shouji_sakura";
		case 1:
			return "item." + "block_shouji_kaede";
		case 2:
			return "item." + "block_shouji_ichoh";
		case 3:
			return "item." + "block_shoujib_sakura";
		case 4:
			return "item." + "block_shoujib_kaede";
		case 5:
			return "item." + "block_shoujib_ichoh";
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
		}
	}
	
	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return Shouji_Blocks.SHOUJI_SAKU; }
		if (k == 1) { return Shouji_Blocks.SHOUJI_KAE; }
		if (k == 2) { return Shouji_Blocks.SHOUJI_ICH; }
		if (k == 3) { return Shouji_Blocks.SHOUJIB_SAKU; }
		if (k == 4) { return Shouji_Blocks.SHOUJIB_KAE; }
		else { return Shouji_Blocks.SHOUJIB_ICH; }
	}

	/* tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_shouji.name"));
	}
}
