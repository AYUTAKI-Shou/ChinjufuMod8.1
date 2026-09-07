package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

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
import net.minecraft.world.server.ServerWorld;

public class SushiGeta_full extends BaseFood_Stage4WA {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(1.0D, 0.0D, 7.0D, 14.0D, 2.5D, 12.5D);
	private static final VoxelShape AABB_WEST = Block.box(3.5D, 0.0D, 1.0D, 9.0D, 2.5D, 14.0D);
	private static final VoxelShape AABB_NORTH = Block.box(2.0D, 0.0D, 3.5D, 15.0D, 2.5D, 9.0D);
	private static final VoxelShape AABB_EAST = Block.box(7.0D, 0.0D, 2.0D, 12.5D, 2.5D, 15.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(1.0D, -8.0D, 7.0D, 14.0D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_WEST = Block.box(3.5D, -8.0D, 1.0D, 9.0D, 0.1D, 14.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(2.0D, -8.0D, 3.5D, 15.0D, 0.1D, 9.0D);
	private static final VoxelShape DOWN_EAST = Block.box(7.0D, -8.0D, 2.0D, 12.5D, 0.1D, 15.0D);

	public SushiGeta_full(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);

		if (hStack.isEmpty()) {
			CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, this.takeItem());
			
			if (i == 4) {
				worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_kara.defaultBlockState()
						.setValue(SushiGeta_kara1_5.H_FACING, state.getValue(H_FACING))
						.setValue(SushiGeta_kara1_5.DOWN, state.getValue(DOWN))
						.setValue(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(1)), 3); }
			
			else { //i != 4
				worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_kara.defaultBlockState()
						.setValue(SushiGeta_kara1_5.H_FACING, state.getValue(H_FACING))
						.setValue(SushiGeta_kara1_5.DOWN, state.getValue(DOWN))
						.setValue(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(i + 2)), 3); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.SUSHIGETA_salmon) { return Items_Teatime.SHOUYUSUSHI_S; }
		if (this == Dish_Blocks.SUSHIGETA_fish) { return Items_Teatime.SHOUYUSUSHI_F; }
		if (this == Dish_Blocks.SUSHIGETA_beef) { return Items_Teatime.SHOUYUSUSHI_B; }
		else { return Items_Teatime.SHOUYUSUSHI_T; }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_kara.defaultBlockState()
						.setValue(SushiGeta_kara1_5.H_FACING, state.getValue(H_FACING))
						.setValue(SushiGeta_kara1_5.DOWN, state.getValue(DOWN))
						.setValue(SushiGeta_kara1_5.STAGE_1_5, Integer.valueOf(5))
						.setValue(SushiGeta_kara1_5.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.SUSHIGETA_kara);
	}
}
