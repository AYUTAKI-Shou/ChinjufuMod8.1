package com.ayutaki.chinjufumod.items.jpdeco;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Abstract_SubBlockFace4;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

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

public class Bonsai_DN extends Abstract_SubBlockFace4 {

	public Bonsai_DN(String name) {
		super(name);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_bonsai_oak";
		case 1:
			return "item." + "block_bonsai_spruce";
		case 2:
			return "item." + "block_bonsai_birch";
		case 3:
			return "item." + "block_bonsai_jungle";
		case 4:
			return "item." + "block_bonsai_acacia";
		case 5:
			return "item." + "block_bonsai_darkoak";
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
		if (k == 0) { return Garden_Blocks.BONSAI_oak; }
		if (k == 1) { return Garden_Blocks.BONSAI_spru; }
		if (k == 2) { return Garden_Blocks.BONSAI_bir; }
		if (k == 3) { return Garden_Blocks.BONSAI_jun; }
		if (k == 4) { return Garden_Blocks.BONSAI_aca; }
		else { return Garden_Blocks.BONSAI_doak; }
	}
	
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_bonsai.name"));
	}
}
