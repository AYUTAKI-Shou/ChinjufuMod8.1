package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

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

public class Glass_Wine extends Base_Glass {
	/** 1=ワイン 2 3, 4=熟成ワイン 5 6, 7=シードル 8 9, 10=熟成シードル 11 12, 13=甘酒 14 15 **/
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.425D, 0.0D, 0.425D, 0.575D, 0.3125D, 0.575D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.425D, -0.5D, 0.425D, 0.575D, 0.01D, 0.575D);
	private static final AxisAlignedBB AABB2 = new AxisAlignedBB(0.425D, 0.0D, 0.425D, 0.575D, 0.2D, 0.575D);
	private static final AxisAlignedBB AABB_DOWN2 = new AxisAlignedBB(0.425D, -0.5D, 0.425D, 0.575D, 0.01D, 0.575D);

	public Glass_Wine(String name) {
		super(name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		/** 1=ワイン 2 3, 4=熟成ワイン 5 6, 7=シードル 8 9, 10=熟成シードル 11 12, 13=甘酒 14 15 **/
		int i = ((Integer)state.getValue(STAGE_1_15)).intValue();

		if (hStack.isEmpty()) {
			boolean KARA = (i == 3 || i == 6 || i == 9 || i == 12 || i == 15);
			if (KARA) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
			
			if (!KARA) {
				CMEvents.soundDrink(worldIn, pos);
				/** add Potion Effect. **/
				if (!worldIn.isRemote) { takeEffects(playerIn, state); }
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_15, Integer.valueOf(i + 1)), 3);
			}
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/** 1=ワイン 2 3, 4=熟成ワイン 5 6, 7=シードル 8 9, 10=熟成シードル 11 12, 13=甘酒 14 15 **/
	private void takeEffects(EntityPlayer playerIn, IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_15)).intValue();
		boolean AMAZAKE = (i == 13 || i == 14);

		if (AMAZAKE) { 
			((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0)); }
		
		else {
			boolean base = (i == 1 || i == 2 || i == 7 || i == 8);
			
			int eTIME = (i == 1 || i == 4 || i == 7 || i == 10)? 1200 : 1500;
			double eTIME2 = base? 1.15 : 1;
			int eLEVEL = base? 0 : 1;
			
			((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, eTIME, eLEVEL));
			((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (eTIME * eTIME2), 0));}
	}

	
	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();

		/** 1=ワイン 2 3, 4=熟成ワイン 5 6, 7=シードル 8 9, 10=熟成シードル 11 12, 13=甘酒 14 15 **/
		int i = ((Integer)state.getValue(STAGE_1_15)).intValue();

		if (i <= 6) { return flag? AABB : AABB_DOWN; }
		return flag? AABB2 : AABB_DOWN2;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}
	
	private ItemStack cloneStack(IBlockState state) {
		/** 1=ワイン 2 3, 4=熟成ワイン 5 6, 7=シードル 8 9, 10=熟成シードル 11 12, 13=甘酒 14 15 **/
		int i = ((Integer)state.getValue(STAGE_1_15)).intValue();

		if (i == 1) { return new ItemStack(Items_Teatime.WINEGLASS, 1, 1); }
		if (i == 4) { return new ItemStack(Items_Teatime.WINEGLASS, 1, 2); }
		if (i == 7) { return new ItemStack(Items_Teatime.WINEGLASS, 1, 3); }
		if (i == 10) { return new ItemStack(Items_Teatime.WINEGLASS, 1, 4); }
		if (i == 13) { return new ItemStack(Items_Teatime.SAKEGLASS, 1, 4); }
		if (i == 14 || i == 15) { return new ItemStack(Items_Teatime.Item_DISH, 1, 1); }
		else { return new ItemStack(Items_Teatime.Item_DISH, 1, 7); }
	}
}
