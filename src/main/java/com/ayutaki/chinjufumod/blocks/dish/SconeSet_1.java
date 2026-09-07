package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.blocks.base.BaseStage8_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SconeSet_1 extends BaseStage8_FaceWater {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.5D, 13.0D);
	
	public SconeSet_1(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_8);

		if (hStack.isEmpty()) {
			CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, takeItem(state));

			if (i == 8) {
				worldIn.setBlock(pos, Dish_Blocks.SCONESET_kara.get().defaultBlockState()
						.setValue(BaseFacingWater.H_FACING, state.getValue(H_FACING)), 3); }
			else { //i != 8
				worldIn.setBlock(pos, state.setValue(STAGE_1_8, Integer.valueOf(i + 1)), 3); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeItem(BlockState state) {
		int i = state.getValue(STAGE_1_8);
		
		if (i == 1) { return Items_Teatime.EGGSAND.get(); }
		if (i == 2) { return Items_Teatime.CHICKENSAND.get(); }
		if (i == 3) { return Items_Teatime.EGGSAND.get(); }
		if (i == 4) { return Items_Teatime.CHICKENSAND.get(); }
		if (i == 5) { return Items_Teatime.SCONE.get(); }
		if (i == 6) { return Items_Teatime.SCONE.get(); }
		if (i == 7) { return Items_Teatime.CAKE.get(); }
		else { return Items_Teatime.CAKE.get(); }
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (state.getValue(WATERLOGGED)) { worldIn.scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.SCONESET_kara.get().defaultBlockState()
					.setValue(SconeSet_kara.H_FACING, state.getValue(H_FACING))
					.setValue(SconeSet_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}
}
