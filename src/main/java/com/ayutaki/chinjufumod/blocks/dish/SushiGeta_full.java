package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

public class SushiGeta_full extends BaseFood_Stage4Water {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(1.0D, 0.0D, 7.0D, 14.0D, 2.5D, 12.5D);
	private static final VoxelShape AABB_WEST = Block.box(3.5D, 0.0D, 1.0D, 9.0D, 2.5D, 14.0D);
	private static final VoxelShape AABB_NORTH = Block.box(2.0D, 0.0D, 3.5D, 15.0D, 2.5D, 9.0D);
	private static final VoxelShape AABB_EAST = Block.box(7.0D, 0.0D, 2.0D, 12.5D, 2.5D, 15.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(1.0D, -8.0D, 7.0D, 14.0D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_WEST = Block.box(3.5D, -8.0D, 1.0D, 9.0D, 0.1D, 14.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(2.0D, -8.0D, 3.5D, 15.0D, 0.1D, 9.0D);
	private static final VoxelShape DOWN_EAST = Block.box(7.0D, -8.0D, 2.0D, 12.5D, 0.1D, 15.0D);
	
	public SushiGeta_full(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);

		if (hStack.isEmpty()) {
			CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, this.takeItem());
			
			if (i == 4) {
				worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_kara.get().defaultBlockState()
						.setValue(SushiGeta_kara1_5.H_FACING, state.getValue(H_FACING))
						.setValue(SushiGeta_kara1_5.DOWN, state.getValue(DOWN))
						.setValue(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(1)), 3); }
			
			else { //i != 4
				worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_kara.get().defaultBlockState()
						.setValue(SushiGeta_kara1_5.H_FACING, state.getValue(H_FACING))
						.setValue(SushiGeta_kara1_5.DOWN, state.getValue(DOWN))
						.setValue(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(i + 2)), 3); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.SUSHIGETA_salmon.get()) { return Items_Teatime.SHOUYUSUSHI_S.get(); }
		if (this == Dish_Blocks.SUSHIGETA_fish.get()) { return Items_Teatime.SHOUYUSUSHI_F.get(); }
		if (this == Dish_Blocks.SUSHIGETA_beef.get()) { return Items_Teatime.SHOUYUSUSHI_B.get(); }
		else { return Items_Teatime.SHOUYUSUSHI_T.get(); }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		
		if (waterIn(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_kara.get().defaultBlockState()
					.setValue(SushiGeta_kara1_5.H_FACING, state.getValue(H_FACING))
					.setValue(SushiGeta_kara1_5.DOWN, state.getValue(DOWN))
					.setValue(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(5))
					.setValue(SushiGeta_kara1_5.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notDown? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return notDown? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return notDown? AABB_WEST : DOWN_WEST;
		case EAST:
			return notDown? AABB_EAST : DOWN_EAST;
		}
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.SUSHIGETA_kara.get());
	}
}
