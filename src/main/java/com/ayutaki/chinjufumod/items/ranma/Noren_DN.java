package com.ayutaki.chinjufumod.items.ranma;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Abstract_SubBlockFace1;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Noren_DN extends Abstract_SubBlockFace1 {

	public Noren_DN(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_noren_white";
		case 1:
			return "item." + "block_noren_orange";
		case 2:
			return "item." + "block_noren_magenta";
		case 3:
			return "item." + "block_noren_lightb";
		case 4:
			return "item." + "block_noren_yellow";
		case 5:
			return "item." + "block_noren_lime";
		case 6:
			return "item." + "block_noren_pink";
		case 7:
			return "item." + "block_noren_gray";
		case 8:
			return "item." + "block_noren_lightg";
		case 9:
			return "item." + "block_noren_cyan";
		case 10:
			return "item." + "block_noren_purple";
		case 11:
			return "item." + "block_noren_blue";
		case 12:
			return "item." + "block_noren_brown";
		case 13:
			return "item." + "block_noren_green";
		case 14:
			return "item." + "block_noren_red";
		case 15:
			return "item." + "block_noren_black";
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
			items.add(new ItemStack(this, 1, 10));
			items.add(new ItemStack(this, 1, 11));
			items.add(new ItemStack(this, 1, 12));
			items.add(new ItemStack(this, 1, 13));
			items.add(new ItemStack(this, 1, 14));
			items.add(new ItemStack(this, 1, 15));
		}
	}

	/* onItemUse */
	@Override
	protected Block int2Block(int k) {
		if (k == 0) { return JPDeco_Blocks.NOREN_white; }
		if (k == 1) { return JPDeco_Blocks.NOREN_orange; }
		if (k == 2) { return JPDeco_Blocks.NOREN_magenta; }
		if (k == 3) { return JPDeco_Blocks.NOREN_lightb; }
		if (k == 4) { return JPDeco_Blocks.NOREN_yellow; }
		if (k == 5) { return JPDeco_Blocks.NOREN_lime; }
		if (k == 6) { return JPDeco_Blocks.NOREN_pink; }
		if (k == 7) { return JPDeco_Blocks.NOREN_gray; }
		if (k == 8) { return JPDeco_Blocks.NOREN_lightg; }
		if (k == 9) { return JPDeco_Blocks.NOREN_cyan; }
		if (k == 10) { return JPDeco_Blocks.NOREN_purple; }
		if (k == 11) { return JPDeco_Blocks.NOREN_blue; }
		if (k == 12) { return JPDeco_Blocks.NOREN_brown; }
		if (k == 13) { return JPDeco_Blocks.NOREN_green; }
		if (k == 14) { return JPDeco_Blocks.NOREN_red; }
		else { return JPDeco_Blocks.NOREN_black; }
	}
	
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Cloth(worldIn, pos, playerIn, hand);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_noren.name"));
	}
}
