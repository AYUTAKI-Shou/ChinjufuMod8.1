package com.ayutaki.chinjufumod.blocks.unitblock;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectWater;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class WrittenMakimono extends Base_ConnectWater {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_5 = IntegerProperty.create("stage", 1, 5);
	public static final BooleanProperty LOST = BooleanProperty.create("lost");
	
	/* Collision */
	private static final VoxelShape AABB_1S = Block.box(6.75D, 0.0D, 4.0D, 9.25D, 2.5D, 12.0D);
	private static final VoxelShape AABB_1W = Block.box(4.0D, 0.0D, 6.75D, 12.0D, 2.5D, 9.25D);
	private static final VoxelShape AABB_1N = Block.box(6.75D, 0.0D, 4.0D, 9.25D, 2.5D, 12.0D);
	private static final VoxelShape AABB_1E = Block.box(4.0D, 0.0D, 6.75D, 12.0D, 2.5D, 9.25D);
	
	private static final VoxelShape AABB_2S = Block.box(4.25D, 0.0D, 4.0D, 9.25D, 2.5D, 12.0D);
	private static final VoxelShape AABB_2W = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 2.5D, 9.25D);
	private static final VoxelShape AABB_2N = Block.box(6.75D, 0.0D, 4.0D, 11.75D, 2.5D, 12.0D);
	private static final VoxelShape AABB_2E = Block.box(4.0D, 0.0D, 6.75D, 12.0D, 2.5D, 11.75D);
	
	private static final VoxelShape AABB_3S = Block.box(4.25D, 0.0D, 4.0D, 11.75D, 2.5D, 12.0D);
	private static final VoxelShape AABB_3W = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 2.5D, 11.75D);
	private static final VoxelShape AABB_3N = Block.box(4.25D, 0.0D, 4.0D, 11.75D, 2.5D, 12.0D);
	private static final VoxelShape AABB_3E = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 2.5D, 11.75D);
	
	private static final VoxelShape AABB_4S = Block.box(4.25D, 0.0D, 4.0D, 11.75D, 5.0D, 12.0D);
	private static final VoxelShape AABB_4W = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 5.0D, 11.75D);
	private static final VoxelShape AABB_4N = Block.box(4.25D, 0.0D, 4.0D, 11.75D, 5.0D, 12.0D);
	private static final VoxelShape AABB_4E = Block.box(4.0D, 0.0D, 4.25D, 12.0D, 5.0D, 11.75D);

	private static final VoxelShape DOWN_SOUTH = Block.box(4.25D, -8.0D, 4.0D, 11.75D, 0.1D, 12.0D);
	private static final VoxelShape DOWN_WEST = Block.box(4.0D, -8.0D, 4.25D, 12.0D, 0.1D, 11.75D);
	private static final VoxelShape DOWN_NORTH = Block.box(4.25D, -8.0D, 4.0D, 11.75D, 0.1D, 12.0D);
	private static final VoxelShape DOWN_EAST = Block.box(4.0D, -8.0D, 4.25D, 12.0D, 0.1D, 11.75D);

	public WrittenMakimono(AbstractBlock.Properties props) {
		super(props);
		registerDefaultState(this.defaultBlockState().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_5, Integer.valueOf(1))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(LOST, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(DOWN, Base_ConnectWater.connectHalf(worldIn, pos, Direction.DOWN))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		if (connectWater(worldIn, pos, Direction.DOWN)) {
			worldIn.getBlockTicks().scheduleTick(pos, Unit_Blocks.WRITTEN_MAKIMONO, Fluids.WATER.getTickDelay(worldIn)); }
		
		if (waterIn(state, worldIn, pos)) { worldIn.getBlockTicks().scheduleTick(pos, Unit_Blocks.WRITTEN_MAKIMONO, 300); }
		
		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.setValue(DOWN, down);
	}

	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.getBlockTicks().scheduleTick(pos, Unit_Blocks.WRITTEN_MAKIMONO, 300); }
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.getBlockTicks().scheduleTick(pos, Unit_Blocks.WRITTEN_MAKIMONO, 300);
			worldIn.setBlock(pos, state.setValue(LOST, Boolean.valueOf(true)), 3); }
		
		else { }
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, STAGE_1_5, WATERLOGGED, LOST);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);
		int i = state.getValue(STAGE_1_5);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notDown? ((i == 1)? AABB_1N : ((i == 2)? AABB_2N : ((i == 3)? AABB_3N : AABB_4N))) : DOWN_NORTH;
		case SOUTH:
			return notDown? ((i == 1)? AABB_1S : ((i == 2)? AABB_2S : ((i == 3)? AABB_3S : AABB_4S))) : DOWN_SOUTH;
		case WEST:
			return notDown? ((i == 1)? AABB_1W : ((i == 2)? AABB_2W : ((i == 3)? AABB_3W : AABB_4W))) : DOWN_WEST;
		case EAST:
			return notDown? ((i == 1)? AABB_1E : ((i == 2)? AABB_2E : ((i == 3)? AABB_3E : AABB_4E))) : DOWN_EAST;
		}
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.BOOK);
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
