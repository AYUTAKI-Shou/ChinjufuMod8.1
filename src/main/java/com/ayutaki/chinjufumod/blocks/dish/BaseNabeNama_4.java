package com.ayutaki.chinjufumod.blocks.dish;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseNabeNama_4 extends BaseStage4_Face {

	protected static final int COOK_TIME = 1200;
	
	public BaseNabeNama_4(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
		
		//setTickRandomly(true);
	}

	/* Cooking */
	protected boolean cookingIn(World worldIn, BlockPos pos) {
		Block downBlock = worldIn.getBlockState(pos.down()).getBlock();
		return (downBlock == Blocks.LIT_FURNACE || downBlock == Kitchen_Blocks.LIT_KITSTOVE || 
				downBlock == Kitchen_Blocks.LIT_KITOVEN || downBlock == Kitchen_Blocks.LIT_KITOVEN_B || 
				downBlock == Kitchen_Blocks.LIT_IRORI);
	}
	
	@Override
	public void observedNeighborChange(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.observedNeighborChange(state, worldIn, pos, blockIn, pos);
		if (cookingIn(worldIn, pos)) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
	} // Need. This runs with every change.
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, COOK_TIME);
	} // This runs with every change.
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.21875D, 0.0D, 0.21875D, 0.78125D, 0.25D, 0.78125D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
