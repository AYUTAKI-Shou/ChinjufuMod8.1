package com.ayutaki.chinjufumod.blocks.season;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.dish.BaseStage4_FDP;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Kakigouri extends BaseStage4_FDP {

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 0.3125D, 0.6D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.4D, -0.5D, 0.4D, 0.6D, 0.01D, 0.6D);

	public Kakigouri(String name) {
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
					boolean PLANE = (this == Seasonal_Blocks.KAKIGOURI_block);
					
					if (i == 1) {
						/* 1 second = 20 ticks かき氷は ×30=600 */
						if (PLANE) { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 400, 0)); }
						else { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(takeEffect(), 600, 0)); } }
					
					if (i == 2) {
						if (PLANE) { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 500, 0)); }
						else { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(takeEffect(), 780, 0)); } }
					
					if (i == 3) {
						if (PLANE) { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 600, 0)); }
						else { ((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(takeEffect(), 900, 0)); } }
				}
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Potion takeEffect() {
		if (this == Seasonal_Blocks.KAKIGOURI_apple) { return MobEffects.RESISTANCE; }
		if (this == Seasonal_Blocks.KAKIGOURI_cherry) { return MobEffects.STRENGTH; }
		if (this == Seasonal_Blocks.KAKIGOURI_citrus) { return MobEffects.FIRE_RESISTANCE; }
		if (this == Seasonal_Blocks.KAKIGOURI_grape) { return MobEffects.NIGHT_VISION; }
		if (this == Seasonal_Blocks.KAKIGOURI_tea) { return MobEffects.HASTE; }
		else { return MobEffects.HASTE; }
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
		
		if (i == 1) { stack.add(new ItemStack(cloneItem(), 1, 0)); }
		if (i != 1) { stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 7)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}

	private Item cloneItem() {
		if (this == Seasonal_Blocks.KAKIGOURI_block) { return Items_Seasonal.KAKIGOURI_block; }
		if (this == Seasonal_Blocks.KAKIGOURI_apple) { return Items_Seasonal.KAKIGOURI_apple; }
		if (this == Seasonal_Blocks.KAKIGOURI_cherry) { return Items_Seasonal.KAKIGOURI_cherry; }
		if (this == Seasonal_Blocks.KAKIGOURI_citrus) { return Items_Seasonal.KAKIGOURI_citrus; }
		if (this == Seasonal_Blocks.KAKIGOURI_grape) { return Items_Seasonal.KAKIGOURI_grape; }
		else { return Items_Seasonal.KAKIGOURI_tea; }
	}
}
