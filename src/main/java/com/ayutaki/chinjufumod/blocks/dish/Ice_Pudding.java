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

public class Ice_Pudding extends BaseStage4_FDP {

	private static final AxisAlignedBB ICE_BOX = new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 0.25D, 0.6D);
	private static final AxisAlignedBB ICE_DOWN = new AxisAlignedBB(0.4D, -0.5D, 0.4D, 0.6D, 0.01D, 0.6D);
	private static final AxisAlignedBB PUDDING_BOX = new AxisAlignedBB(0.41875D, 0.0D, 0.41875D, 0.58125D, 0.13125D, 0.58125D);
	private static final AxisAlignedBB PUDDING_DOWN = new AxisAlignedBB(0.41875D, -0.5D, 0.41875D, 0.58125D, 0.01D, 0.58125D);

	public Ice_Pudding(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(1.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 4
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
				
				/** add Potion Effect. **/
				if (!worldIn.isRemote) {
					boolean icecream = (this == Dish_Blocks.ICECREAM || this == Dish_Blocks.ICECREAM_GREEN || 
							this == Dish_Blocks.ICECREAM_RED || this == Dish_Blocks.ICECREAM_CACAO);
					
					int iTIME = (i == 1)? 1300 : ((i == 2)? 1440 : 1580); //3600*1.2
					int pTIME = (i == 1)? 1450 : ((i == 2)? 1600 : 1750); //4000*1.2
					int eTIME = icecream? iTIME : pTIME;
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.LUCK, eTIME, 1)); }
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		boolean icecream = (this == Dish_Blocks.ICECREAM || this == Dish_Blocks.ICECREAM_GREEN || 
				this == Dish_Blocks.ICECREAM_RED || this == Dish_Blocks.ICECREAM_CACAO);

		if (icecream) { return notDown? ICE_BOX : ICE_DOWN; }
		else { return notDown? PUDDING_BOX : PUDDING_DOWN; }
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 1) { stack.add(new ItemStack(Items_Teatime.ICECREAM, 1, cloneInt())); }
		else { stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 7)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.ICECREAM, 1, cloneInt());
	}
	
	private int cloneInt() {
		if (this == Dish_Blocks.ICECREAM_GREEN) { return 1; }
		if (this == Dish_Blocks.ICECREAM_RED) { return 2; }
		if (this == Dish_Blocks.ICECREAM_CACAO) { return 3; }
		if (this == Dish_Blocks.CUSTARD_PUDDING) { return 4; }
		if (this == Dish_Blocks.GREENTEA_PUDDING) { return 5; }
		if (this == Dish_Blocks.REDTEA_PUDDING) { return 6; }
		if (this == Dish_Blocks.CACAO_PUDDING) { return 7; }
		else { return 0; }
	}
}
