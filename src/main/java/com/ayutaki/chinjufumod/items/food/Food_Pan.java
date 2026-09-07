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
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Food_Pan extends Food_Teatime {

	public Food_Pan(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		default:
		case 1:
			return "item." + "item_food_pananko";
		case 2:
			return "item." + "item_food_pancustard";
		case 3:
			return "item." + "item_food_panapple";
		case 4:
			return "item." + "item_food_pancherry";
		case 5:
			return "item." + "item_food_pancitrus";
		case 6:
			return "item." + "item_food_pangrape";
		case 7:
			return "item." + "item_food_pangreentea";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
		}
	}

	/* Effects after Eating. */
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		/** add Potion Effect. must **/
		if (!worldIn.isRemote) {
			int k = stack.getMetadata();
			/** pananko, pancustard**/
			if (k == 1 || k == 2) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 500, 0)); }
			
			/** panapple **/
			if (k == 3) { playerIn.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 800, 0)); }
			
			/** pancherry **/
			if (k == 4) { playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 800, 0)); }
			
			/** pancitrus **/
			if (k == 5) { playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 800, 0)); }
			
			/** pangrape **/
			if (k == 6) { playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 800, 0)); }
			
			/** pangreentea **/
			if (k == 7) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 800, 0)); }
		}
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1) { itemTip.add(I18n.format("tips.item_food_pananko.name")); }
	}
}
