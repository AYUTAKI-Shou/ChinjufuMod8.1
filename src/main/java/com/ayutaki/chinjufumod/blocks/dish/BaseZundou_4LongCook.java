package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseZundou_4LongCook extends Base_CookPanStage4 {

	protected static final int COOK_TIME = 1000;

	public BaseZundou_4LongCook(String name) {
		super(name);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* TickRandom */
	protected boolean HOT(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		return (i == 2 || i == 4);
	}
	
	@Override
	public void observedNeighborChange(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.observedNeighborChange(state, worldIn, pos, blockIn, pos);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (cookingIn(worldIn, pos) && i != 4) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
		
		if (cookingOUT(worldIn, pos) && HOT(state)) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
		
		else { }
	} // Need. This runs with every change.
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (cookingIn(worldIn, pos) && i != 4) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
		
		if (cookingOUT(worldIn, pos) && HOT(state)) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
	} // This runs with every change.

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (cookingIn(worldIn, pos) && i != 4) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			
			if (i == 1) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(2))); }
			
			if (i == 2 || i == 3) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(4))); } }
		
		if (cookingOUT(worldIn, pos) && HOT(state)) { 
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 1))); }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
	}
	
	/* Steam effect. */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		World par1World = worldIn;
		int par2 = x;
		int par3 = y;
		int par4 = z;
		Random par5Random = rand;
		
		if (cookingIn(worldIn, pos)) {
			if (HOT(state)) {
				for (int la = 0; la < 1; ++la) {
					double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
					double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d3 = 0.12D;
					double d4 = 0.17D;
					par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3, d2, 0.0D, 0.0D, 0.0D); }
			}
			
			/** add Sound **/
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }
		}
	}
}
