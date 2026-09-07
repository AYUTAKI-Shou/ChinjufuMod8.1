package com.ayutaki.chinjufumod.items.food;

import com.ayutaki.chinjufumod.items.addtab.Food_Teatime;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class Food_Pizza extends Food_Teatime {

	public Food_Pizza(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setAlwaysEdible();
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_food_pizza";
		case 1:
			return "item." + "item_food_pizzac";
		case 2:
			return "item." + "item_food_pizzat";
		case 3:
			return "item." + "item_food_pizzas";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
		}
	}
	
	/* Effects after Eating. */
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		/** add Potion Effect. must **/
		if (!worldIn.isRemote) {
			int k = stack.getMetadata();
			
			if (k == 1 || k == 2) {
				/* 1 second = 20 ticks */
				playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0));
				playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 1)); }
			
			if (k != 1 && k != 2) {
				playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1400, 0));
				playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 70, 1)); }
		}
	}
}
