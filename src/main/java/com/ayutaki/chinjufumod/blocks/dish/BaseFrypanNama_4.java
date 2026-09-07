package com.ayutaki.chinjufumod.blocks.dish;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseFrypanNama_4 extends Base_CookPanStage4 {
	/** 1000tick = Minecraft内 1h = リアル時間 50秒 **/
	protected static final int COOK_TIME = 1000;
	
	public BaseFrypanNama_4(String name) {
		super(name);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* TickRandom */
	@Override
	public void observedNeighborChange(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.observedNeighborChange(state, worldIn, pos, blockIn, pos);
		if (cookingIn(worldIn, pos)) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
		else { }
	} // Need. This runs with every change.
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, COOK_TIME);
	} // This runs with every change.
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
	}
}
