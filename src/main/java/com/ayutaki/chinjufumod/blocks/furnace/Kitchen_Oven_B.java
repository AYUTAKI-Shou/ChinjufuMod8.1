package com.ayutaki.chinjufumod.blocks.furnace;

import com.ayutaki.chinjufumod.tileentity.Oven_TileEntity;

import net.minecraft.block.AbstractBlock;
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

/* Make 'Abstract' beforehand. */
public class Kitchen_Oven_B extends AbstractOvenBlock {

	/* Collision */
	private static final VoxelShape TOP = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_BOX = VoxelShapes.or(TOP, Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D));

	public Kitchen_Oven_B(AbstractBlock.Properties props) {
		super(props);
	}

	/* Generate TileEntity. */
	public TileEntity newBlockEntity(IBlockReader world) {
		return new Oven_TileEntity();
	}

	protected void openContainer(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		TileEntity tileentity = worldIn.getBlockEntity(pos);
		if (tileentity instanceof Oven_TileEntity) {
			playerIn.openMenu((INamedContainerProvider)tileentity);
			playerIn.awardStat(Stats.INTERACT_WITH_FURNACE);
		}
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}
}
