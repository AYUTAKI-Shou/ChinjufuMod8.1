package com.ayutaki.chinjufumod.items.food;

import com.ayutaki.chinjufumod.items.addtab.Food_Teatime;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class Onigiri extends Food_Teatime {

	public Onigiri(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_food_onigiri";
		case 1:
			return "item." + "item_food_onigirishake";
		case 3:
			return "item." + "item_food_onigiritakenoko";
		case 4:
			return "item." + "item_food_onigirikuri";
		case 5:
			return "item." + "item_food_onigirisekihan";
		case 2:
			return "item." + "item_food_futomaki";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 2));
		}
	}

	/* Effects after Eating. */
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		/** add Potion Effect. must **/
		if (!worldIn.isRemote) {
			int k = stack.getMetadata();
			
			/* 1 second = 20 ticks */
			if (k == 1) { playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 0)); }
			if (k == 2) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0)); }
			if (k == 3 || k == 4 || k == 5) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 500, 0)); }
			else { }
		}
	}
}
