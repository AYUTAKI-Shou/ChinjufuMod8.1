package com.ayutaki.chinjufumod.blocks.ranma;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.state.TypeLR;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseRanma extends Abstract_WaterLogged {
	public static final MapCodec<BaseRanma> CODEC = simpleCodec(BaseRanma::new);
	@Override
	public MapCodec<? extends BaseRanma> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<TypeLR> TYPE = EnumProperty.create("type", TypeLR.class);
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty VER = IntegerProperty.create("ver", 1, 2);
	
	/* Collision */
	private static final VoxelShape S1_SOUTH = Block.box(0.0D, 0.0D, 6.5D, 16.0D, 16.0D, 9.5D);
	private static final VoxelShape S1_WEST = Block.box(6.5D, 0.0D, 0.0D, 9.5D, 16.0D, 16.0D);
	private static final VoxelShape S1_NORTH = Block.box(0.0D, 0.0D, 6.5D, 16.0D, 16.0D, 9.5D);
	private static final VoxelShape S1_EAST = Block.box(6.5D, 0.0D, 0.0D, 9.5D, 16.0D, 16.0D);

	private static final VoxelShape S2_SOUTH = Block.box(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape S2_WEST = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
	private static final VoxelShape S2_NORTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
	private static final VoxelShape S2_EAST = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public BaseRanma(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(TYPE, TypeLR.DEFAULT)
				.setValue(VER, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		if (hStack.isEmpty() && playerIn.isCrouching()) {
			CMEvents.soundWoodPlace(worldIn, pos);
			worldIn.setBlock(pos, state.cycle(VER), 3);
			return InteractionResult.SUCCESS; }
		
		return InteractionResult.PASS;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
	}
	
	/* HORIZONTAL Property */
	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Connect the blocks. */
	private boolean canConnectTo(BlockGetter worldIn, BlockPos pos, Direction direct, Direction targetDirection) {
		BlockState state = worldIn.getBlockState(pos.relative(direct));

		if(state.getBlock() == this) {
			Direction sofaDirection = state.getValue(H_FACING);
			return sofaDirection.equals(targetDirection);
		}
		return false;
	}
	
	private BlockState getConnectState(BlockState state, BlockGetter worldIn, BlockPos pos, Direction dir) {
		boolean left = canConnectTo(worldIn, pos, dir.getClockWise(), dir) || canConnectTo(worldIn, pos, dir.getClockWise(), dir.getClockWise());
		boolean right = canConnectTo(worldIn, pos, dir.getCounterClockWise(), dir) || canConnectTo(worldIn, pos, dir.getCounterClockWise(), dir.getCounterClockWise());

		if(left && right) {
			return state.setValue(TYPE, TypeLR.BOTH);
		}

		else if(left) {
			return state.setValue(TYPE, TypeLR.RIGHT);
		}

		else if(right) {
			return state.setValue(TYPE, TypeLR.LEFT);
		}
		return state.setValue(TYPE, TypeLR.DEFAULT);
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return this.getConnectState(state, worldIn, pos, state.getValue(H_FACING));
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, VER, TYPE, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		int i = state.getValue(VER);
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default:
			return (i == 1)? S1_NORTH : S2_NORTH;
		case SOUTH:
			return (i == 1)? S1_SOUTH : S2_SOUTH;
		case WEST:
			return (i == 1)? S1_WEST : S2_WEST;
		case EAST:
			return (i == 1)? S1_EAST : S2_EAST;
		}
	}
}
