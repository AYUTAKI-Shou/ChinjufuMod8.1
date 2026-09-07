package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.blocks.dish.Abstract_FoodStage3;
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

public class Dish_PlateStage3 extends TTab_DishEat {

	public Dish_PlateStage3(String name, int amount, float saturation, boolean isWolfFood) {
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
		/** Block×1.2, Item×1.0 **/
		if (this == Items_Teatime.RICE) { }
		if (this == Items_Teatime.CHICKEN_small) { playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 0)); }
		if (this == Items_Teatime.EGGBURG) { playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 0)); }
		if (this == Items_Teatime.HAKUSAIDUKE) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 500, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		if (this == Items_Teatime.CHICKEN_small) { return Dish_Blocks.CHICKEN_small; }
		if (this == Items_Teatime.EGGBURG) { return Dish_Blocks.EGGBURG; }
		if (this == Items_Teatime.HAKUSAIDUKE) { return Dish_Blocks.HAKUSAIDUKE; }
		else { return Dish_Blocks.RICE; }
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		return this.takeBlock().getDefaultState().withProperty(Abstract_FoodStage3.H_FACING, direction)
				.withProperty(Abstract_FoodStage3.STAGE_1_3, Integer.valueOf(1));
	}

	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand); }
}
