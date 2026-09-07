package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectWater;
import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.furnace.Irori;
import com.ayutaki.chinjufumod.blocks.furnace.Kitchen_Oven;
import com.ayutaki.chinjufumod.blocks.furnace.Kitchen_Oven_B;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BaseNabe extends Base_ConnectWater {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	public static final BooleanProperty COOK = BooleanProperty.create("cook");

	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(1.5D, 0.0D, 3.5D, 10.5D, 4.5D, 12.5D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(3.5D, 0.0D, 1.5D, 12.5D, 4.5D, 10.5D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(5.5D, 0.0D, 3.5D, 14.5D, 4.5D, 12.5D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(3.5D, 0.0D, 5.5D, 12.5D, 4.5D, 14.5D);

	private static final VoxelShape COOK_SOUTH = Block.makeCuboidShape(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	private static final VoxelShape COOK_WEST = Block.makeCuboidShape(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	private static final VoxelShape COOK_NORTH = Block.makeCuboidShape(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	private static final VoxelShape COOK_EAST = Block.makeCuboidShape(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);

	private static final VoxelShape DOWN_SOUTH = Block.makeCuboidShape(1.5D, -8.0D, 3.5D, 10.5D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_WEST = Block.makeCuboidShape(3.5D, -8.0D, 1.5D, 12.5D, 0.1D, 10.5D);
	private static final VoxelShape DOWN_NORTH = Block.makeCuboidShape(5.5D, -8.0D, 3.5D, 14.5D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_EAST = Block.makeCuboidShape(3.5D, -8.0D, 5.5D, 12.5D, 0.1D, 14.5D);

	public BaseNabe(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(COOK, Boolean.valueOf(false))
				.with(DOWN, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(COOK, this.connectCook(worldIn, pos, Direction.DOWN))
				.with(DOWN, Base_ConnectWater.connectHalf(worldIn, pos, Direction.DOWN))
				.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(H_FACING, rotation.rotate(state.get(H_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(H_FACING)));
	}
	
	/* Connect the blocks. */
	protected boolean connectCook(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return (block instanceof FurnaceBlock || block instanceof Kitchen_Oven || block instanceof Kitchen_Oven_B ||
				block instanceof Irori || block instanceof Kit_Cooktop);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (connectWater(worldIn, pos, Direction.DOWN)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		boolean cook = connectCook(worldIn, pos, Direction.DOWN);
		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.with(COOK, cook).with(DOWN, down);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(COOK, DOWN, H_FACING, STAGE_1_4, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		boolean notCook = !((Boolean)state.get(COOK)).booleanValue();
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notCook ? (notDown? AABB_NORTH : DOWN_NORTH) : COOK_NORTH;
		case SOUTH:
			return notCook ? (notDown? AABB_SOUTH : DOWN_SOUTH) : COOK_SOUTH;
		case WEST:
			return notCook ? (notDown? AABB_WEST : DOWN_WEST) : COOK_WEST;
		case EAST:
			return notCook ? (notDown? AABB_EAST : DOWN_EAST) : COOK_EAST;
		}
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
	
	private boolean cookingIn(IBlockReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.down());
		Block downBlock = downState.getBlock();
		return (downBlock instanceof FurnaceBlock && downState.get(FurnaceBlock.LIT) == true) ||
				(downBlock instanceof AbstractOvenBlock && downState.get(AbstractOvenBlock.LIT) == true) ||
				(downBlock instanceof Irori && downState.get(Irori.LIT) == true) ||
				(downBlock instanceof Kit_Cooktop && downState.get(Kit_Cooktop.STAGE_1_3) == 2);
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		boolean notGohan = (this != Dish_Blocks.NABEGOHAN && this != Dish_Blocks.NABEGOHAN_TAKE && 
				this != Dish_Blocks.NABEGOHAN_KURI && this != Dish_Blocks.NABESEKIHAN);
		if (notGohan) {

			if (cookingIn(worldIn, pos)) {
				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.3F, 0.7F, false); }

				if (rand.nextDouble() < 0.25D) {
					/** which. position x y z, speed x y z **/
					worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
			}
		}
	}
}
