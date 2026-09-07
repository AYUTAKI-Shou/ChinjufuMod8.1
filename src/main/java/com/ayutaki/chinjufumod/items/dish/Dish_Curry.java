package com.ayutaki.chinjufumod.items.dish;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.Curry;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dish_Curry extends TTab_DishEat {

	public Dish_Curry(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);

		/** 食べている最中は盆を外したモデルを使う **/
		this.addPropertyOverride(new ResourceLocation("eat"), new IItemPropertyGetter() {

			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_food_curry_1";
		case 1:
			return "item." + "block_food_curry_c1";
		case 2:
			return "item." + "block_food_curry_t1";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
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
		if (k == 2) { //block_food_curry_t1
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3000, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3000, 0)); }
		
		if (k != 2) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 3600, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3600, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.CURRY;
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		return this.intBlock(k).getDefaultState().withProperty(Curry.H_FACING, direction)
				.withProperty(Curry.STAGE_1_4, Integer.valueOf(1));
	}

	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand); }

	private Block intBlock(int k) {
		if (k == 0) { return Dish_Blocks.CURRY; }
		if (k == 1) { return Dish_Blocks.CURRY_C; }
		else { return Dish_Blocks.CURRY_T; }
	}
}
