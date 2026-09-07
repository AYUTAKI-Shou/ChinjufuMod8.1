package com.ayutaki.chinjufumod.items.food;

import com.ayutaki.chinjufumod.items.addtab.Food_Teatime;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class Sushi_Shouyu extends Food_Teatime {

	public Sushi_Shouyu(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		/** Have sub items. **/
		setHasSubtypes(true);
		setAlwaysEdible();
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "item_food_sushishouyu_salmon";
		case 2:
			return "item." + "item_food_sushishouyu_fish";
		case 3:
			return "item." + "item_food_sushishouyu_beef";
		case 4:
			return "item." + "item_food_sushishouyu_tamago";
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

	/* Effects after Eating. */
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		/** add Potion Effect. must **/
		if (!worldIn.isRemote) {
			int k = stack.getMetadata();

			/* 1 second = 20 ticks */
			if (k == 1) { playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60, 0)); }
			if (k == 2) { playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1000, 0)); }
			if (k == 3) { playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1000, 0)); }
			/* 1 second = 20 ticks 玉子焼き調理分加算 */
			if (k == 4) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0)); }
		}
	}
}
