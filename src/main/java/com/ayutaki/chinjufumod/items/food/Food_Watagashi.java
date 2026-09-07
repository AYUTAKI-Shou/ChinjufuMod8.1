package com.ayutaki.chinjufumod.items.food;

import com.ayutaki.chinjufumod.items.addtab.Food_Seasonal;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class Food_Watagashi extends Food_Seasonal {

	public Food_Watagashi(String name, int amount, float saturation, boolean isWolfFood) {
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
			return "item." + "item_food_watagashi";
		case 2:
			return "item." + "item_food_watagashi_y";
		case 3:
			return "item." + "item_food_watagashi_p";
		case 6:
			return "item." + "item_food_watagashi_o";
		case 4:
			return "item." + "item_food_watagashi_r";
		case 5:
			return "item." + "item_food_watagashi_g";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
		}
	}

	/* Finish RightClick Action */
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EntityPlayer playerIn = (EntityPlayer)entityLiving;
		playerIn.getFoodStats().addStats(this, stack);

		if (entityLiving instanceof EntityPlayer) {
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			/** add Potion Effect. **/
			if (!worldIn.isRemote) { this.onFoodEaten(stack, worldIn, playerIn); }
			playerIn.addStat(StatList.getObjectUseStats(this));

			if (playerIn instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)playerIn, stack);
			}
		}

		/** add Item **/
		if (playerIn == null || !playerIn.capabilities.isCreativeMode) {
			ItemStack take = new ItemStack(Items.STICK, 1, 0);
			if (stack.isEmpty()) { return take; }
			else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }

			stack.shrink(1);
		}
		return stack;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		int k = stack.getMetadata();
		/* 1 second = 20 ticks 見直し_260419 */
		if (k == 1) { playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 800, 0)); }
		/** apple **/
		if (k == 2) { playerIn.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1200, 0)); }
		/** cherry **/
		if (k == 3) { playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1200, 0)); }
		/** citrus **/
		if (k == 6) { playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1200, 0)); }
		/** grape **/
		if (k == 4) { playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1200, 0)); }
		/** tea **/
		if (k == 5) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0)); }
	}
}
