package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseTaru_Stage08 extends Abstract_WaterLogged {
	public static final MapCodec<BaseTaru_Stage08> CODEC = simpleCodec(BaseTaru_Stage08::new);
	@Override
	public MapCodec<? extends BaseTaru_Stage08> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_0_8 = IntegerProperty.create("stage", 0, 8);
	/* Collision */
	private static final VoxelShape AABB_BOX = Shapes.or(Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.25D, 8.0D, 0.25D, 15.75D, 12.0D, 15.75D),
			Block.box(0.5D, 4.0D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.75D, 0.0D, 0.75D, 15.25D, 4.0D, 15.25D));

	public BaseTaru_Stage08(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_0_8, Integer.valueOf(0))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		int i = state.getValue(STAGE_0_8);
		if (i <= 4) {
			if (rand.nextInt(4) == 0) {
				if (i < 4) {
					worldIn.setBlock(pos, state.setValue(STAGE_0_8, Integer.valueOf(i + 1)), 3); }

				if (i == 4) {
					worldIn.setBlock(pos, state.setValue(STAGE_0_8, Integer.valueOf(i + 1)), 3);
					CMEvents.addEXP(1, worldIn, pos); } }
		}
		else { }
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_8, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}
	
	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }

}
