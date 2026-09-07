package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.blocks.dish.Abstract_FoodStage3;
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

public class Yunomi_TT extends TTab_DishDrink {

	public Yunomi_TT(String name) {
		super(name, Dish_Blocks.JPTEACUP);
		setUnlocalizedName(name);
	}
	
	/* onItemUseFinish */
	@Override
	protected ItemStack remainStack(ItemStack stack) {
		return new ItemStack(Items_Teatime.Item_DISH, 1, 1);
	}

	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2000, 0));
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.JPTEACUP;
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		return this.takeBlock().getDefaultState().withProperty(Abstract_FoodStage3.H_FACING, direction)
				.withProperty(Abstract_FoodStage3.STAGE_1_3, Integer.valueOf(1));
	}
}
