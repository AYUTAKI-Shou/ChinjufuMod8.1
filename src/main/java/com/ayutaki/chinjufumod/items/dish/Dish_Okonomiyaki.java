package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Dish_Okonomiyaki extends TTab_DishEat {

	public Dish_Okonomiyaki(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_food_okonomiyaki_1";
		case 1:
			return "item." + "block_food_okonomis_1";
		case 2:
			return "item." + "block_food_okonomic_1";
		case 3:
			return "item." + "block_food_okonomisoba_1";
		case 4:
			return "item." + "block_food_okonomisobas_1";
		case 5:
			return "item." + "block_food_okonomisobac_1";
		case 6:
			return "item." + "block_food_yakisoba_1";
		case 7:
			return "item." + "block_food_yakisobashio_1";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
		}
	}

	/* onItemUseFinish */
	@Override
	protected ItemStack remainStack() {
		return new ItemStack(Items_Teatime.Item_SARA, 1, 0);
	}

	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		int k = stack.getMetadata();
		
		if (k == 0 || k == 1 || k == 6 || k == 7) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 2, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3000, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3000, 0)); }

		if (k == 2) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2600, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2600, 0)); }

		if (k == 3 || k == 4) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3000, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3000, 0)); }
		
		if (k == 5) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 2, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2600, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2600, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.OKONOMIYAKI;
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		return this.intBlock(k).getDefaultState().withProperty(BaseStage4_FaceDown.H_FACING, direction)
				.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(1));
	}

	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand); }

	private Block intBlock(int k) {
		if (k == 0) { return Dish_Blocks.OKONOMIYAKI; }
		if (k == 1) { return Dish_Blocks.OKONOMIS; }
		if (k == 2) { return Dish_Blocks.OKONOMIC; }
		if (k == 3) { return Dish_Blocks.OKONOMISOBA; }
		if (k == 4) { return Dish_Blocks.OKONOMISOBAS; }
		if (k == 5) { return Dish_Blocks.OKONOMISOBAC; }
		if (k == 6) { return Dish_Blocks.YAKISOBA; }
		else { return Dish_Blocks.YAKISOBASHIO; }
	}
}
