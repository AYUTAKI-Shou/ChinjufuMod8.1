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

public class Dish_Udon extends TTab_DishEat {

	public Dish_Udon(String name, int amount, float saturation, boolean isWolfFood) {
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
		if (this == Items_Teatime.UDON_SU) { }
		
		if (this != Items_Teatime.UDON_SU) {
			/** Block×1.2, Item×1.0 **/
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3000, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3000, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		if (this == Items_Teatime.UDON_SU) { return Dish_Blocks.UDON_SU; }
		if (this == Items_Teatime.UDON_NIKU) { return Dish_Blocks.UDON_NIKU; }
		else { return Dish_Blocks.UDON_TSUKIMI; }
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
