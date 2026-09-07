package com.ayutaki.chinjufumod.blocks.kitchen;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Kit_TanaYunomi_1 extends Base_Tana7 {

	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public Kit_TanaYunomi_1(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_7);

		if (hItem != Items_Teatime.YUNOMI && hItem != Items_Teatime.KYUSU_kara) {
			if (hStack.isEmpty()) {
				
				if (i == 7) {
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.KYUSU_kara);
					worldIn.setBlockState(pos, state.with(STAGE_1_7, Integer.valueOf(i - 1))); }
				
				else { //i != 7
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.YUNOMI);
					
					if (i == 1) { worldIn.setBlockState(pos, Kitchen_Blocks.KIT_TANA.getDefaultState()
							.with(Kit_Tana.H_FACING, state.get(H_FACING))); }
					else { //i != 1
						worldIn.setBlockState(pos, state.with(STAGE_1_7, Integer.valueOf(i - 1))); } } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (hItem == Items_Teatime.YUNOMI) {
			if (i >= 6) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			else { //i < 6
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);			
				worldIn.setBlockState(pos, state.with(STAGE_1_7, Integer.valueOf(i + 1))); }
		}
		
		if (hItem == Items_Teatime.KYUSU_kara) {
			if (i == 6) {
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);			
				worldIn.setBlockState(pos, state.with(STAGE_1_7, Integer.valueOf(i + 1))); }
			else { //i != 6
				CMEvents.textNotHave(worldIn, pos, playerIn); } }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.KIT_TANA);
	}
}
