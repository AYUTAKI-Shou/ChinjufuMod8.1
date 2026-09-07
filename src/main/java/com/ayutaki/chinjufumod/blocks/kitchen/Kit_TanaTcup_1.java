package com.ayutaki.chinjufumod.blocks.kitchen;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.AbstractBlock;
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

public class Kit_TanaTcup_1 extends Base_Tana7 {

	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	private static final VoxelShape AABB_WEST = Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public Kit_TanaTcup_1(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_7);

		if (hItem != Items_Teatime.TCUP_kara && hItem != Items_Teatime.TEAPOT_kara) {
			if (hStack.isEmpty()) {
				
				if (i == 7) {
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.TEAPOT_kara);
					worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(i - 1)), 3); }
				
				else { //i != 7
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.TCUP_kara);

					if (i == 1) { 
						worldIn.setBlock(pos, Kitchen_Blocks.KIT_TANA.defaultBlockState()
							.setValue(Kit_Tana.H_FACING, state.getValue(H_FACING)), 3); }
					else { //i != 1
						worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(i - 1)), 3); } } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (hItem == Items_Teatime.TCUP_kara) {
			if (i >= 6) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			else { //i < 6
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(i + 1)), 3); } }
		
		if (hItem == Items_Teatime.TEAPOT_kara) {
			if (i == 6) {
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(i + 1)), 3); }
			else { //i != 6
				CMEvents.textNotHave(worldIn, pos, playerIn); } }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.KIT_TANA);
	}
}
