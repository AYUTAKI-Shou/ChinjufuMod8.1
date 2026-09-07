package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Dish_Pasta extends TTab_DishEat {

	public Dish_Pasta(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setUnlocalizedName(name);
	}

	/* onItemUseFinish */
	@Override
	protected ItemStack remainStack() {
		return new ItemStack(Items_Teatime.Item_SARA, 1, 0);
	}

	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		if (this == Items_Teatime.PASTASEAFOOD) {
			/** Block×1.2, Item×1.0 **/
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3500, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3500, 0)); }
		
		if (this != Items_Teatime.PASTASEAFOOD) { 
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3000, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3000, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		if (this == Items_Teatime.PASTATOMATO) { return Dish_Blocks.PASTATOMATO; }
		if (this == Items_Teatime.PASTACHEESE) { return Dish_Blocks.PASTACHEESE; }
		if (this == Items_Teatime.PASTAKINOKO) { return Dish_Blocks.PASTAKINOKO; }
		else { return Dish_Blocks.PASTASEAFOOD; }
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		return this.takeBlock().getDefaultState().withProperty(BaseStage4_FaceDown.H_FACING, direction)
				.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(1));
	}

	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand); }
}
