package com.ayutaki.chinjufumod.items.food;

import com.ayutaki.chinjufumod.items.addtab.Food_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class Food_Cherry extends Food_Teatime {

	public Food_Cherry(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setAlwaysEdible();
	}

	/* This item will remain.. 箱との間で無限取得出来るため削除 */

	/* Finish RightClick Action */
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EntityPlayer playerIn = (EntityPlayer)entityLiving;
		playerIn.getFoodStats().addStats(this, stack);

		if (entityLiving instanceof EntityPlayer) {
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			/** add Potion Effect. must **/
			if (!worldIn.isRemote) { this.onFoodEaten(stack, worldIn, playerIn); }
			playerIn.addStat(StatList.getObjectUseStats(this));

			if (playerIn instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)playerIn, stack);
			}
		}

		/** add Item **/
		if (playerIn == null || !playerIn.capabilities.isCreativeMode) {
			ItemStack take = new ItemStack(Items_Teatime.SEEDS_CHERRY, 1, 0);
			if (stack.isEmpty()) { return take; }
			else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }

			stack.shrink(1);
		}
		return stack;
	}
}
