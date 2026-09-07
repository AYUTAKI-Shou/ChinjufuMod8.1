package com.ayutaki.chinjufumod.blocks.furnace;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.tileentity.Oven_TileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/* Abstract を用意する */
public class Kitchen_Oven_B extends AbstractOvenBlock {
	/* Collision */
	private static final VoxelShape TOP = Block.makeCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_BOX = VoxelShapes.or(TOP, Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D));

	public Kitchen_Oven_B(Block.Properties props) {
		super(props);
	}
	
	///IForgeBlock//////////
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new Oven_TileEntity();
	}

	/* 生成する TileEntity */
	public TileEntity createNewTileEntity(IBlockReader world) {
		return new Oven_TileEntity();
	}

	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof Oven_TileEntity) {
			playerIn.openContainer((INamedContainerProvider)tileentity);
			playerIn.addStat(Stats.INTERACT_WITH_FURNACE);
		}
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}
}
