package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.Abstract_FoodStage3;
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

public class Dish_Chawan extends TTab_DishEat {

	public Dish_Chawan(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setUnlocalizedName(name);
	}

	/* onItemUseFinish */
	@Override
	protected ItemStack remainStack() {
		return new ItemStack(Items_Teatime.Item_DISH, 1, 3);
	}

	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		if (this == Items_Teatime.GOHAN) { }
		if (this != Items_Teatime.GOHAN) { 
			playerIn.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.HASTE, 500, 0))); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		if (this == Items_Teatime.GOHAN) { return Dish_Blocks.GOHAN; }
		if (this == Items_Teatime.GOHAN_KURI) { return Dish_Blocks.GOHAN_KURI; }
		if (this == Items_Teatime.GOHAN_TAKE) { return Dish_Blocks.GOHAN_TAKE; }
		else { return Dish_Blocks.SEKIHAN; }
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		return this.takeBlock().getDefaultState().withProperty(Abstract_FoodStage3.H_FACING, direction)
				.withProperty(Abstract_FoodStage3.STAGE_1_3, Integer.valueOf(1));
	}

	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand); }
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		super.addInformation(stack, worldIn, itemTip, advanced);
		if (this == Items_Teatime.SEKIHAN) { itemTip.add(I18n.format("tips.block_food_sekihan.name")); }
	}
}
