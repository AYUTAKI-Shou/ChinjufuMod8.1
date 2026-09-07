package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Chauke_Scone extends BaseChauke {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(4.5D, 0.0D, 4.5D, 11.5D, 3.0D, 11.5D);
	private static final VoxelShape AABB_DOWN = Block.box(4.5D, -8.0D, 4.5D, 11.5D, 0.1D, 11.5D);

	public Chauke_Scone(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hStack.isEmpty()) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.SCONE.get());
				worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
	}
}
