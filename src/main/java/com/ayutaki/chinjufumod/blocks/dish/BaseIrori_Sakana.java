package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furnace.Irori;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BaseIrori_Sakana extends Abstract_WaterLogged {

	protected static final int COOK_TIME = 1000;
	/* Property */
	public static final IntegerProperty STAGE_0_15 = IntegerProperty.create("stage", 0, 15);

	/* Collision */
	private static final VoxelShape AABB_BOX = VoxelShapes.or(Block.makeCuboidShape(6.0D, -5.0D, 10.75D, 10.0D, 3.5D, 14.5D),
			Block.makeCuboidShape(6.0D, -5.0D, 1.5D, 10.0D, 3.5D, 5.25D),
			Block.makeCuboidShape(1.5D, -5.0D, 6.0D, 5.25D, 3.5D, 10.0D),
			Block.makeCuboidShape(10.5D, -5.0D, 6.0D, 14.75D, 3.5D, 10.0D));

	public BaseIrori_Sakana(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState()
				.with(STAGE_0_15, Integer.valueOf(0))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.down());
		Block downBlock = downState.getBlock();
		return downBlock instanceof Irori;
	}

	/* Connect the blocks. */
	protected boolean cookingIn(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();
		return (block instanceof Irori && state.get(Irori.LIT));
	}

	protected boolean waterIn(BlockState state) {
		return state.get(WATERLOGGED);
	}
	
	protected boolean waterOUT(BlockState state) {
		return !state.get(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos, Direction.DOWN)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME); } 

			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
		
		return !state.isValidPosition(worldIn, pos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos, Direction.DOWN)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME); } 

			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_15, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.AIR);
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (cookingIn(worldIn, pos, Direction.DOWN)) {

			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false); }

			if (rand.nextDouble() < 0.1D) {
				/** which. position x y z, speed x y z **/
				worldIn.addParticle(ParticleTypes.SMOKE, d0 + d4, d1 + d6 - 0.3D, d2 + d4, 0.0D, 0.0D, 0.0D); }
		}
	}

	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}
}
