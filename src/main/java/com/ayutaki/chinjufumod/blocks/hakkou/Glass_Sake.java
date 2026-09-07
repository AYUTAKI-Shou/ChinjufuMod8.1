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

public class Glass_Sake extends Base_Glass {
	/** 1=生酒 2 3, 4=日本酒 5 6, 7=熟成酒 8 9, 10=ミード 11 12, 13=熟成ミード 14 15 **/
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.425D, 0.0D, 0.425D, 0.575D, 0.2D, 0.575D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.425D, -0.5D, 0.425D, 0.575D, 0.01D, 0.575D);

	public Glass_Sake(String name) {
		super(name);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		/** 1=生酒 2 3, 4=日本酒 5 6, 7=熟成酒 8 9, 10=ミード 11 12, 13=熟成ミード 14 15 **/
		int i = ((Integer)state.getValue(STAGE_1_15)).intValue();

		if (hStack.isEmpty()) {
			boolean KARA = (i == 3 || i == 6 || i == 9 || i == 12 || i == 15);
			if (KARA) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
			
			if (!KARA) {
				CMEvents.soundDrink(worldIn, pos);

				/** add Potion Effect. **/
				if (!worldIn.isRemote) {
					if (i >= 1 && i <= 8) { takeSAKE(playerIn, state); }
					if (i >= 10 && i <= 14) { takeMEAD(playerIn, state); } }
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_15, Integer.valueOf(i + 1)), 3); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/** 1=生酒 2 3, 4=日本酒 5 6, 7=熟成酒 8 9, 10=ミード 11 12, 13=熟成ミード 14 15 **/
	private void takeSAKE(EntityPlayer playerIn, IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_15)).intValue();
		boolean base = (i == 4 || i == 5);
		boolean aged = (i == 7 || i == 8);
		
		int eTIME = (i == 1 || i == 4 || i == 7)? 1200 : 1500;
		double eTIME2 = aged? 1 : 1.15;
		int eLEVEL = aged? 2 : (base? 1 : 0);
		
		((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, eTIME, eLEVEL));
		((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (eTIME * eTIME2), 0));
	}
	
	private void takeMEAD(EntityPlayer playerIn, IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_15)).intValue();
		boolean base = (i == 10 || i == 11);
		
		int eTIME = (i == 10 || i == 13)? 1200 : 1500;
		double eTIME2 = base? 1.15 : 1;
		int eLEVEL = base? 0 : 1;
		((EntityLivingBase) playerIn).removePotionEffect(MobEffects.POISON);
		((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.STRENGTH, eTIME, eLEVEL));
		((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, (int) (eTIME * eTIME2), 0));
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
		stack.add(cloneStack(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}
	
	private ItemStack cloneStack(IBlockState state) {
		/** 1=生酒 2 3, 4=日本酒 5 6, 7=熟成酒 8 9, 10=ミード 11 12, 13=熟成ミード 14 15 **/
		int i = ((Integer)state.getValue(STAGE_1_15)).intValue();

		if (i == 1) { return new ItemStack(Items_Teatime.SAKEGLASS, 1, 1); }
		if (i == 4) { return new ItemStack(Items_Teatime.SAKEGLASS, 1, 2); }
		if (i == 7) { return new ItemStack(Items_Teatime.SAKEGLASS, 1, 3); }
		if (i == 10) { return new ItemStack(Items_Teatime.WINEGLASS, 1, 5); }
		if (i == 13) { return new ItemStack(Items_Teatime.WINEGLASS, 1, 6); }
		else { return new ItemStack(Items_Teatime.Item_DISH, 1, 7); }
	}
}
