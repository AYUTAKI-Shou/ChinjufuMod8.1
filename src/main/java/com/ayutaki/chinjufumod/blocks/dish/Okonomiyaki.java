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

public class Okonomiyaki extends BaseStage4_FDP {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(3.5D * cw, 0.0D * cw, 2.5D * cw, 12.5D * cw, 3.0D * cw, 11.5D * cw);
	private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(4.5D * cw, 0.0D * cw, 3.5D * cw, 13.5D * cw, 3.0D * cw, 12.5D * cw);
	private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(3.5D * cw, 0.0D * cw, 4.5D * cw, 12.5D * cw, 3.0D * cw, 13.5D * cw);
	private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(2.5D * cw, 0.0D * cw, 3.5D * cw, 11.5D * cw, 3.0D * cw, 12.5D * cw);

	private static final AxisAlignedBB DOWN_SOUTH = new AxisAlignedBB(3.5D * cw, -8.0D * cw, 2.5D * cw, 12.5D * cw, 0.1D * cw, 11.5D * cw);
	private static final AxisAlignedBB DOWN_WEST = new AxisAlignedBB(4.5D * cw, -8.0D * cw, 3.5D * cw, 13.5D * cw, 0.1D * cw, 12.5D * cw);
	private static final AxisAlignedBB DOWN_NORTH = new AxisAlignedBB(3.5D * cw, -8.0D * cw, 4.5D * cw, 12.5D * cw, 0.1D * cw, 13.5D * cw);
	private static final AxisAlignedBB DOWN_EAST = new AxisAlignedBB(2.5D * cw, -8.0D * cw, 3.5D * cw, 11.5D * cw, 0.1D * cw, 12.5D * cw);
	
	public Okonomiyaki(String name) {
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

		/** Hand is Empty. **/
		if (hStack.isEmpty()) {
			CMEvents.soundEat(worldIn, pos);

			/** add Potion Effect. **/
			if (!worldIn.isRemote) {
				boolean okonomi = (this == Dish_Blocks.OKONOMIC || this == Dish_Blocks.OKONOMIYAKI || this == Dish_Blocks.OKONOMIS);

				if (okonomi) {
					int eTIME = (this == Dish_Blocks.OKONOMIC)? 2600 : 3200;
					int MEAT = (this == Dish_Blocks.OKONOMIC)? 2 : 3;
					
					if (i == 1) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, eTIME, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, MEAT, 0)); }

					if (i == 2) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3, 0)); }

					if (i == 3) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3, 0)); }

					if (i == 4) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, MEAT, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, eTIME, 0)); }
				}
				
				else { //!okonomi
					int eTIME = (this == Dish_Blocks.OKONOMISOBAC)? 2600 : 3200;
					int MEAT = (this == Dish_Blocks.OKONOMISOBAC)? 3 : 4;
					
					if (i == 1) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, eTIME, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3, 0)); }

					if (i == 2) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, MEAT, 0)); }

					if (i == 3) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, MEAT, 0)); }

					if (i == 4) {
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 0, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 3, 0));
						((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, eTIME, 0)); }
				}
			}
			
			if (i != 4) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }

			if (i == 4) {
				worldIn.setBlockState(pos, Dish_Blocks.CORNSOUP.getDefaultState()
						.withProperty(Corn_Soup.H_FACING, state.getValue(H_FACING))
						.withProperty(Corn_Soup.DOWN, state.getValue(DOWN))
						.withProperty(Corn_Soup.STAGE_1_4, Integer.valueOf(4))); }
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

	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.OKONOMIYAKI, 1, cloneMeta())); }
		if (i != 1) { stack.add(new ItemStack(Items_Teatime.Item_SARA, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.OKONOMIYAKI, 1, cloneMeta());
	}

	private int cloneMeta() {
		if (this == Dish_Blocks.OKONOMIYAKI) { return 0; }
		if (this == Dish_Blocks.OKONOMIS) { return 1; }
		if (this == Dish_Blocks.OKONOMIC) { return 2; }
		if (this == Dish_Blocks.OKONOMISOBA) { return 3; }
		if (this == Dish_Blocks.OKONOMISOBAS) { return 4; }
		else { return 5; }
	}
}
