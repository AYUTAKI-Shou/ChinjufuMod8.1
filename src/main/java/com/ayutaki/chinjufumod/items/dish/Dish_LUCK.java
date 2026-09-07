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
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Dish_LUCK extends TNot_AlwaysEat {

	public Dish_LUCK(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		/** Have sub items. **/
		setHasSubtypes(true);
	}
	
	@Override
	protected ItemStack remainStack() { 
		return new ItemStack(Items_Teatime.Item_DISH, 1, 7); }
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_food_icecream_1";
		case 1:
			return "item." + "block_food_icecream_greentea";
		case 2:
			return "item." + "block_food_icecream_redtea";
		case 3:
			return "item." + "block_food_icecream_cacao";
		case 4:
			return "item." + "block_food_pudding_custard";
		case 5:
			return "item." + "block_food_pudding_greentea";
		case 6:
			return "item." + "block_food_pudding_redtea";
		case 7:
			return "item." + "block_food_pudding_cacao";
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
	protected void addEffect(ItemStack hStack, EntityPlayer playerIn) {
		/** Effect time of standard potions = 3600tick, 3min. **/
		int k = hStack.getMetadata();
		boolean icecream = (k <= 3);
		
		int eTIME = icecream? 3600 : 4000;
		playerIn.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.LUCK, eTIME, 1)));
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.ICECREAM;
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
		if (k == 1) { return Dish_Blocks.ICECREAM_GREEN; }
		if (k == 2) { return Dish_Blocks.ICECREAM_RED; }
		if (k == 3) { return Dish_Blocks.ICECREAM_CACAO; }
		if (k == 4) { return Dish_Blocks.CUSTARD_PUDDING; }
		if (k == 5) { return Dish_Blocks.GREENTEA_PUDDING; }
		if (k == 6) { return Dish_Blocks.REDTEA_PUDDING; }
		if (k == 7) { return Dish_Blocks.CACAO_PUDDING; }
		else { return Dish_Blocks.ICECREAM; }
	}
}
