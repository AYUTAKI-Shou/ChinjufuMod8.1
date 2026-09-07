package com.ayutaki.chinjufumod.items.cooktool;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.Abstract_SubStateFace4;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

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

public class FrypanBake1_NT extends Abstract_SubStateFace4 {

	public FrypanBake1_NT(String name) {
		super(name, Dish_Blocks.FRYPAN_BAKE_1);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/**1=玉子焼き, 2=ハンバーグ, 3=トマトソース, 4=きのこソース**/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_food_frypan_b_tamago";
		case 2:
			return "item." + "block_food_frypan_b_eggb";
		case 3:
			return "item." + "block_food_frypan_b_tomatos";
		case 4:
			return "item." + "block_food_frypan_b_kinokos";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.FRYPAN_BAKE_1;
	}
	
	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Metal(worldIn, pos, playerIn, hand);
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1 || k == 2) { itemTip.add(I18n.format("tips.take_plate.name")); }
		if (k == 3 || k == 4) { itemTip.add(I18n.format("tips.block_food_frypan_pasta.name")); }
	}
}
