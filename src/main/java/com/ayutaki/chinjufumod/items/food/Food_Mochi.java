package com.ayutaki.chinjufumod.items.food;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.addtab.Food_Teatime;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Food_Mochi extends Food_Teatime {

	public Food_Mochi(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_food_mochinori";
		case 1:
			return "item." + "item_food_mochikinako";
		case 2:
			return "item." + "item_food_mochianko";
		case 3:
			return "item." + "item_food_mochiohagi";
		case 4:
			return "item." + "item_food_mochisakura";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
		}
	}

	/* Effects after Eating. */
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		/** add Potion Effect. must **/
		if (!worldIn.isRemote) {
			int k = stack.getMetadata();
			boolean ANKO = (k <= 1);
			boolean SAKURA = (k == 4);
			
			Potion EFFECT = SAKURA? MobEffects.STRENGTH : MobEffects.HASTE;
			int eTIME = ANKO? 500 : 750;
			playerIn.addPotionEffect(new PotionEffect(EFFECT, eTIME, 0)); }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 0) { itemTip.add(I18n.format("tips.item_food_mochinori.name")); }
		if (k == 1) { itemTip.add(I18n.format("tips.item_food_mochikinako.name")); }
		if (k == 2) { itemTip.add(I18n.format("tips.item_food_mochianko.name")); }
		if (k == 3) { itemTip.add(I18n.format("tips.item_food_mochiohagi.name")); }
		if (k == 4) { itemTip.add(I18n.format("tips.item_food_mochisakura.name")); }
	}
}
