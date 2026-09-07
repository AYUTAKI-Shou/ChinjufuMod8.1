package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseTana_Stage05 extends Base_Stage05 {
	/* Collision */
	private static final VoxelShape AABB_TANA = Shapes.or(Block.box(0.0D, 2.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 2.0D),
			Block.box(0.0D, 0.0D, 14.0D, 2.0D, 2.0D, 16.0D),
			Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D),
			Block.box(14.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D));
	
	public BaseTana_Stage05(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state)) { tick.scheduleTick(pos, this, 100); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 100); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 100);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Hakkou_Blocks.HAKKOU_TARU.get().defaultBlockState()
					.setValue(Taru_Hakkou.STAGE_0_5, Integer.valueOf(3))
					.setValue(Taru_Hakkou.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		int i = state.getValue(STAGE_0_5);
		if (i < 5 && !hasWater(worldIn, pos)) {
			if (rand.nextInt(4) == 0) {
				worldIn.setBlock(pos, state.setValue(STAGE_0_5, Integer.valueOf(i + 1)), 3); } }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_TANA;
	}
}
