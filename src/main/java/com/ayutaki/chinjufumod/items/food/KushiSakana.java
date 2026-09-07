package com.ayutaki.chinjufumod.items.food;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.addtab.Food_Teatime;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KushiSakana extends Food_Teatime {

	public KushiSakana(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		
		/** 食べている最中は盆を外したモデルを使う **/
		this.addPropertyOverride(new ResourceLocation("eat"), new IItemPropertyGetter() {

			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
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
}
