package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BaseZundou_4Cook extends Abstract_CookPan {
	public static final MapCodec<BaseZundou_4Cook> CODEC = simpleCodec(BaseZundou_4Cook::new);
	@Override
	public MapCodec<? extends BaseZundou_4Cook> codec() { return CODEC; }

	protected static final int COOK_TIME = 500;
	/* Property */
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
	
	public BaseZundou_4Cook(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_4);
			if (cookingIn(worldIn, pos) && i != 4) {
				tick.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5))); }
			
			boolean EVEN = (i == 2 || i == 4);
			if (cookingOUT(worldIn, pos) && EVEN) { 
				tick.scheduleTick(pos, this, COOK_TIME); }
		}
		
		if (waterIn(state)) { tick.scheduleTick(pos, this, 60); }

		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) { 
			int i = state.getValue(STAGE_1_4);
			if (cookingIn(worldIn, pos) && i != 4) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	
			boolean EVEN = (i == 2 || i == 4);
			if (cookingOUT(worldIn, pos) && EVEN) { 
				worldIn.scheduleTick(pos, this, COOK_TIME); }
			
			else { } }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_4);
			/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
			
			if (cookingIn(worldIn, pos) && i != 4) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				
				if (i == 1) {
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(2)), 3); }
				
				if (i == 2 || i == 3) {
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3); } }
			
			boolean EVEN = (i == 2 || i == 4);
			if (cookingOUT(worldIn, pos) && EVEN) { 
				worldIn.scheduleTick(pos, this, COOK_TIME);
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				
			else { }
		}
		
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU.get().defaultBlockState()
					.setValue(H_FACING, state.getValue(H_FACING))
					.setValue(Zundou.STAGE_1_2, Integer.valueOf(2))
					.setValue(Zundou.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_4, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (cookingIn(worldIn, pos)) {
			if (rand.nextDouble() < 0.1D) {
				worldIn.playLocalSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU.get(), SoundSource.BLOCKS, 0.5F, 0.7F, false); }
		}

		int i = state.getValue(STAGE_1_4);
		if (i == 2 || i == 4) {
			if (rand.nextDouble() < 0.25D) {
				/** which, position x y z, speed x y z **/
				worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
		}
	}
}
