package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Tamagoyaki_set extends BaseStage4_FDP {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.1875, 0.0, 0.125, 0.6875, 0.1875, 0.875);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.1875, 0.0, 0.125, 0.6875, 0.1875, 0.875);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.1875, 0.0, 0.125, 0.6875, 0.1875, 0.875);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.1875, 0.0, 0.125, 0.6875, 0.1875, 0.875);

	private static final AxisAlignedBB DOWN_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.1875, -0.5, 0.125, 0.6875, 0.01, 0.875);
	private static final AxisAlignedBB DOWN_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.1875, -0.5, 0.125, 0.6875, 0.01, 0.875);
	private static final AxisAlignedBB DOWN_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.1875, -0.5, 0.125, 0.6875, 0.01, 0.875);
	private static final AxisAlignedBB DOWN_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.1875, -0.5, 0.125, 0.6875, 0.01, 0.875);

	public Tamagoyaki_set(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hStack.isEmpty()) {
			CMEvents.soundEat(worldIn, pos);
			
			/** add Potion Effect. **/
			if (!worldIn.isRemote) {
				int eTIME = (this == Dish_Blocks.TAMAGOYAKITEI)? 6600 : 6700;
				int eTIME2 = (this == Dish_Blocks.TAMAGOYAKITEI)? 4200 : 4300;
				
				if (i == 1) {
					/** 6000/20=300 seconds, SATURATION 2 =1 piece of MEAT **/
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, eTIME, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0)); }

				if (i == 2) {
					/** Instant HEAL, 0, 0) **/
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0)); }

				if (i == 3) {
					/** REGENERATION 3600/20=180 seconds **/
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, eTIME2, 0)); }

				if (i == 4) {
					/** DAMAGE_RESISTANCE 3600/20=180 seconds, +600tick **/
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, eTIME2, 1)); }
			}

			if (i != 4) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 4) {
				worldIn.setBlockState(pos, Dish_Blocks.FOODKARA_TEI.getDefaultState()
						.withProperty(FoodKara_Tei.H_FACING, state.getValue(H_FACING))
						.withProperty(FoodKara_Tei.DOWN, state.getValue(DOWN))
						.withProperty(FoodKara_Tei.STAGE_1_4, Integer.valueOf(1))); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return flag? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return flag? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return flag? AABB_WEST : DOWN_WEST;
		case EAST:
			return flag? AABB_EAST : DOWN_EAST;
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(cloneItem(), 1, 0)); }
		if (i != 1) {
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 3));
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 4));
			stack.add(new ItemStack(Items_Teatime.Item_SARA, 2, 0));
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 1)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}

	private Item cloneItem() {
		if (this == Dish_Blocks.TAMAGOYAKITEI_TAKE) { return Items_Teatime.TAMAGOYAKITEI_TAKE; }
		if (this == Dish_Blocks.TAMAGOYAKITEI_KURI) { return Items_Teatime.TAMAGOYAKITEI_KURI; }
		if (this == Dish_Blocks.TAMAGOYAKITEI_SEKI) { return Items_Teatime.TAMAGOYAKITEI_SEKI; }
		else { return Items_Teatime.TAMAGOYAKITEI; }
	}
}
