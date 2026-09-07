package com.ayutaki.chinjufumod.items.chinjufu;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.addtab.Chinjufu_SubBlockFace1;
import com.ayutaki.chinjufumod.registry.School_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SchoolChair_CT extends Chinjufu_SubBlockFace1 {

	public SchoolChair_CT(String name) {
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
			return "item." + "block_schoolchair";
		case 1:
			return "item." + "block_schoolchair_s";
		case 2:
			return "item." + "block_schoolchair_b";
		case 3:
			return "item." + "block_schoolchair_j";
		case 4:
			return "item." + "block_schoolchair_a";
		case 5:
			return "item." + "block_schoolchair_d";
		case 6:
			return "item." + "block_schoolchair_saku";
		case 7:
			return "item." + "block_schoolchair_kae";
		case 8:
			return "item." + "block_schoolchair_ich";
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
	protected Block int2Block(int k) {
		if (k == 0) { return School_Blocks.SCHOOLCHAIR; }
		if (k == 1) { return School_Blocks.SCHOOLCHAIR_s; }
		if (k == 2) { return School_Blocks.SCHOOLCHAIR_b; }
		if (k == 3) { return School_Blocks.SCHOOLCHAIR_j; }
		if (k == 4) { return School_Blocks.SCHOOLCHAIR_a; }
		if (k == 5) { return School_Blocks.SCHOOLCHAIR_d; }
		if (k == 6) { return School_Blocks.SCHOOLCHAIR_saku; }
		if (k == 7) { return School_Blocks.SCHOOLCHAIR_kae; }
		else { return School_Blocks.SCHOOLCHAIR_ich; }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_isu.name"));
	}
}
