package com.ayutaki.chinjufumod.blocks.furnace;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.Stove_TileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/* Abstract を用意する */
public class CStove_Top extends AbstractStoveBlock {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public CStove_Top(Block.Properties props) {
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
		return new Stove_TileEntity();
	}

	/* 生成する TileEntity */
	public TileEntity createNewTileEntity(IBlockReader world) {
		return new Stove_TileEntity();
	}

	protected void interactWith(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof Stove_TileEntity) {
			playerIn.openContainer((INamedContainerProvider)tileentity);
			playerIn.addStat(Stats.INTERACT_WITH_FURNACE);
		}
	}

	/* Destroy at the same time. & Drop item. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		BlockState downState = worldIn.getBlockState(pos.down());
		/** False is not Drop. **/
		if (downState.getBlock() == School_Blocks.CSTOVE_bot) {
			worldIn.destroyBlock(pos.down(), false);
		}
		super.onBlockHarvested(worldIn, pos, state, playerIn);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Chinjufu.CSTOVE_bot);
	}
}
