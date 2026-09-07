package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Dish_Kanten extends TNot_AlwaysEat {

	public Dish_Kanten(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		/** Have sub items. **/
		setHasSubtypes(true);
	}
	
	@Override
	protected ItemStack remainStack() { 
		return new ItemStack(Items_Teatime.Item_SARA, 1, 0); }
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_food_kanten_apple";
		case 1:
			return "item." + "block_food_kanten_cherry";
		case 2:
			return "item." + "block_food_kanten_citrus";
		case 3:
			return "item." + "block_food_kanten_grape";
		case 4:
			return "item." + "block_food_kanten_milk";
		case 5:
			return "item." + "block_food_yokan";
		case 6:
			return "item." + "block_food_yokan_matcha";
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
		}
	}

	/* onItemUseFinish */
	@Override
	protected void addEffect(ItemStack hStack, EntityPlayer playerIn) {
		/** Effect time of standard potions = 3600tick, 3min. **/
		int i = hStack.getMetadata();
		if (i == 0) {
			playerIn.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.RESISTANCE, 3600, 0))); }
		
		if (i == 1) {
			playerIn.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.STRENGTH, 3600, 0))); }

		if (i == 2) {
			playerIn.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 3600, 0))); }
		
		if (i == 3) {
			playerIn.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 3600, 0))); }

		if (i == 4) {
			playerIn.curePotionEffects(new ItemStack(Items.MILK_BUCKET)); }
		
		if (i == 5) {
			playerIn.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.HASTE, 3600, 0))); }
		
		if (i == 6) {
			playerIn.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.HASTE, 3800, 0))); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.KANTEN_APPLE;
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
		if (k == 1) { return Dish_Blocks.KANTEN_CHERRY; }
		if (k == 2) { return Dish_Blocks.KANTEN_CITRUS ; }
		if (k == 3) { return Dish_Blocks.KANTEN_GRAPE; }
		if (k == 4) { return Dish_Blocks.KANTEN_MILK; }
		if (k == 5) { return Dish_Blocks.YOKAN; }
		if (k == 6) { return Dish_Blocks.YOKAN_MATCHA; }
		else { return Dish_Blocks.KANTEN_APPLE; }
	}
}
