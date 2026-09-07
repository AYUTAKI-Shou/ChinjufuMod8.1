package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.Donburi;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dish_Donburi extends TTab_DishEat {

	public Dish_Donburi(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setUnlocalizedName(name);
	}

	/* onItemUseFinish */
	@Override
	protected ItemStack remainStack() {
		return new ItemStack(Items_Teatime.Item_DISH, 1, 6);
	}

	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		if (this == Items_Teatime.DONBURI_MESHI) { }
		
		if (this == Items_Teatime.DONBURI_KATSU) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3500, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3500, 0)); }
		
		if (this != Items_Teatime.DONBURI_MESHI && this != Items_Teatime.DONBURI_KATSU) {
			/** Block×1.2, Item×1.0 **/
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3000, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3000, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		if (this == Items_Teatime.DONBURI_MESHI) { return Dish_Blocks.DONBURI_MESHI; }
		if (this == Items_Teatime.DONBURI_GYU) { return Dish_Blocks.DONBURI_GYU; }
		if (this == Items_Teatime.DONBURI_KATSU) { return Dish_Blocks.DONBURI_KATSU; }
		if (this == Items_Teatime.DONBURI_OYAKO) { return Dish_Blocks.DONBURI_OYAKO; }
		else { return Dish_Blocks.DONBURI_KAISEN; }
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		return this.takeBlock().getDefaultState().withProperty(Donburi.H_FACING, direction)
				.withProperty(Donburi.STAGE_1_4, Integer.valueOf(1));
	}

	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand); }
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		super.addInformation(stack, worldIn, itemTip, advanced);
		if (this == Items_Teatime.DONBURI_GYU) { itemTip.add(I18n.format("tips.block_food_dongyu_1.name")); }
		if (this == Items_Teatime.DONBURI_KATSU) { itemTip.add(I18n.format("tips.block_food_donkatsu_1.name")); }
		if (this == Items_Teatime.DONBURI_OYAKO) { itemTip.add(I18n.format("tips.block_food_donoyako_1.name")); }
		if (this == Items_Teatime.DONBURI_KAISEN) { itemTip.add(I18n.format("tips.block_food_donkaisen_1.name")); }
	}
}
