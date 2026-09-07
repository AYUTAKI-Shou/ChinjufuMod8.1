package com.ayutaki.chinjufumod.items.food;

import com.ayutaki.chinjufumod.items.addtab.Food_Seasonal;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class Food_Choco extends Food_Seasonal {

	public Food_Choco(String name, int amount, float saturation, boolean isWolfFood) {
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
			return "item." + "item_food_choco";
		case 2:
			return "item." + "item_food_choco_apple";
		case 3:
			return "item." + "item_food_choco_cherry";
		case 7:
			return "item." + "item_food_choco_citrus";
		case 4:
			return "item." + "item_food_choco_grape";
		case 5:
			return "item." + "item_food_choco_greentea";
		case 6:
			return "item." + "item_food_choco_heart";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
		}
	}

	/* Effects after Eating. */
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		/** add Potion Effect. must **/
		if (!worldIn.isRemote) {
			int k = stack.getMetadata();
			/* 1 second = 20 ticks 見直し_260419 */
			if (k == 1) { playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 800, 0)); } //CHOCO
	
			if (k == 2) { playerIn.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 0)); } //_apple
	
			if (k == 3) { playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1200, 0)); } //_cherry
	
			if (k == 7) { playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1200, 0)); } //_citrus
	
			if (k == 4) { playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1200, 0)); } //_grape
	
			if (k == 5) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0)); } //_tea
			/* 生成を1個にするため, 効果時間を３倍 */
			if (k == 6) { playerIn.addPotionEffect(new PotionEffect(MobEffects.LUCK, 3000, 0)); } //_heart 
		}
	}
}
