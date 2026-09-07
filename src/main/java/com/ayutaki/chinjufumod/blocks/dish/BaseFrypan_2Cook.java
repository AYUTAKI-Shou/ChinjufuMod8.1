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

public class BaseFrypan_2Cook extends Abstract_CookPan {
	protected static final int COOK_TIME = 1000;
	/* Property */
	public static final IntegerProperty STAGE_1_2 = IntegerProperty.create("stage", 1, 2);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 2.0D, 12.0D);

	public BaseFrypan_2Cook(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_2, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Anti Shadow */
	public int getLightValue(BlockState state) {
		return 1;
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

	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterOUT(state)) {
			int i = state.get(STAGE_1_2);
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) {
			int i = state.get(STAGE_1_2);
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterOUT(state)) {
			int i = state.get(STAGE_1_2);
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(2)));
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				
				if (this == Dish_Blocks.FPKINOKOAK_nama) { CMEvents.addEXP(1, worldIn, pos); } }
			
			else { } //cookingOUT
		}
		
		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState()
					.with(Frypan_kara.H_FACING, state.get(H_FACING))
					.with(Frypan_kara.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_2, WATERLOGGED);
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
			boolean JUU = (this == Dish_Blocks.FPCURRY_nama || this == Dish_Blocks.FPKATSU_nama ||
					this == Dish_Blocks.FPEGGBURG_nama || this == Dish_Blocks.FPTAMAGO_nama);

			if (rand.nextDouble() < 0.1D) {
				if (JUU) { worldIn.playSound(d0, d1, d2, SoundEvents_CM.JUU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }

				else { worldIn.playSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); } }

			int i = state.get(STAGE_1_2);
			if (i ==2 && rand.nextDouble() < 0.25D) {
				/** which. position x y z, speed x y z **/
				worldIn.addParticle(JUU? ParticleTypes.SMOKE : ParticleTypes.POOF, d0 + d4, d1 + d6 - 0.3D, d2 + d4, 0.0D, 0.0D, 0.0D); }
		}
	}
}
