package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BaseZundou_4Cook extends Abstract_CookPan {

	protected static final int COOK_TIME = 500;
	/* Property */
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

	public BaseZundou_4Cook(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterOUT(state)) {
			int i = state.get(STAGE_1_4);
			if (cookingIn(worldIn, pos) && i != 4) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	
			boolean EVEN = (i == 2 || i == 4);
			if (cookingOUT(worldIn, pos) && EVEN) { 
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME); }
			
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) {
			int i = state.get(STAGE_1_4);
			if (cookingIn(worldIn, pos) && i != 4) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	
			boolean EVEN = (i == 2 || i == 4);
			if (cookingOUT(worldIn, pos) && EVEN) { 
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME); }
			
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterOUT(state)) {
			int i = state.get(STAGE_1_4);
			/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
			if (cookingIn(worldIn, pos) && i != 4) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				
				if (i == 1) {
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(2)), 3); }
				
				if (i == 2 || i == 3) {
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(4)), 3); } }
			
			/** OFF **/
			boolean EVEN = (i == 2 || i == 4);
			if (cookingOUT(worldIn, pos) && EVEN) { 
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME);
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i - 1)), 3); }
			
		else { }
	}
	
	if (waterIn(state)) {
		worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
		
		CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU.getDefaultState()
					.with(Zundou.H_FACING, state.get(H_FACING))
					.with(Zundou.STAGE_1_2, Integer.valueOf(2))
					.with(Zundou.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}
	
	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_4, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (cookingIn(worldIn, pos)) {

			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }
		}

		int i = state.get(STAGE_1_4);
		if (i == 2 || i == 4) {
			if (rand.nextDouble() < 0.25D) {
				/** which. position x y z, speed x y z **/
				worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
		}
	}
}
