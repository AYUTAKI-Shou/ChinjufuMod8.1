package com.ayutaki.chinjufumod.items.wallpanel;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Abstract_SubBlockFace4;
import com.ayutaki.chinjufumod.registry.WallBrick_Blocks;

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

public class PillarSlab_WPT extends Abstract_SubBlockFace4 {

	public PillarSlab_WPT(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.WALLPANEL);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 150;
	}
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_kamoi_oak";
		case 1:
			return "item." + "block_kamoi_spruce";
		case 2:
			return "item." + "block_kamoi_birch";
		case 3:
			return "item." + "block_kamoi_jungle";
		case 4:
			return "item." + "block_kamoi_acacia";
		case 5:
			return "item." + "block_kamoi_darkoak";
		}
	}

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
		if (k == 0) { return WallBrick_Blocks.PILLARSLAB_oak; }
		if (k == 1) { return WallBrick_Blocks.PILLARSLAB_spru; }
		if (k == 2) { return WallBrick_Blocks.PILLARSLAB_bir; }
		if (k == 3) { return WallBrick_Blocks.PILLARSLAB_jun; }
		if (k == 4) { return WallBrick_Blocks.PILLARSLAB_aca; }
		else { return WallBrick_Blocks.PILLARSLAB_doak; }
	}
	
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
	}
	
	/* Tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.wp_stage4.name"));
	}
}
