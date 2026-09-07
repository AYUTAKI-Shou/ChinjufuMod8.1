package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Ramen extends BaseStage4_FDP {

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.3625D, 0.0D, 0.3625D, 0.6375D, 0.1875D, 0.6375D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.3625D, -0.5D, 0.3625D, 0.6375D, 0.01D, 0.6375D);

	public Ramen(String name) {
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
				/** Same as DONBURI_KATSU **/
				if (i == 1) {
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 4200, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3, 0)); }

				if (i == 2) {
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3, 0)); }

				if (i == 3) {
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3, 0)); }

				if (i == 4) {
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 4200, 0)); }
			}

			if (i != 4) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 4) {
				worldIn.setBlockState(pos, Dish_Blocks.UDON_SU.getDefaultState()
						.withProperty(Udon.H_FACING, state.getValue(H_FACING))
						.withProperty(Udon.DOWN, state.getValue(DOWN))
						.withProperty(Udon.STAGE_1_4, Integer.valueOf(4))); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}


	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		return flag? AABB : AABB_DOWN;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.RAMEN, 1, cloneMeta())); }
		if (i != 1) { stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 6)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.RAMEN, 1, cloneMeta());
	}

	private int cloneMeta() {
		if (this == Dish_Blocks.RAMEN_SHOUYU) { return 0; }
		if (this == Dish_Blocks.RAMEN_MISO) { return 1; }
		else { return 2; }
	}
}
