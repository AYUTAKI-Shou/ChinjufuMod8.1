package com.ayutaki.chinjufumod.blocks.garden;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.state.TypeLR;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IronFence extends Abstract_WaterLogged {
	public static final MapCodec<IronFence> CODEC = simpleCodec(IronFence::new);
	@Override
	public MapCodec<? extends IronFence> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final EnumProperty<TypeLR> TYPE = EnumProperty.create("type", TypeLR.class);
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;

	/* Collision */
	private static final VoxelShape BOT_SOUTH = Block.box(0.0D, 0.0D, 7.5D, 16.0D, 16.0D, 8.5D);
	private static final VoxelShape BOT_WEST = Block.box(7.5D, 0.0D, 0.0D, 8.5D, 16.0D, 16.0D);
	private static final VoxelShape BOT_NORTH = Block.box(0.0D, 0.0D, 7.5D, 16.0D, 16.0D, 8.5D);
	private static final VoxelShape BOT_EAST = Block.box(7.5D, 0.0D, 0.0D, 8.5D, 16.0D, 16.0D);

	private static final VoxelShape TOP_SOUTH = Block.box(0.0D, 0.0D, 7.5D, 16.0D, 27.0D, 8.5D);
	private static final VoxelShape TOP_WEST = Block.box(7.5D, 0.0D, 0.0D, 8.5D, 27.0D, 16.0D);
	private static final VoxelShape TOP_NORTH = Block.box(0.0D, 0.0D, 7.5D, 27.0D, 16.0D, 8.5D);
	private static final VoxelShape TOP_EAST = Block.box(7.5D, 0.0D, 0.0D, 8.5D, 27.0D, 16.0D);
	
	public IronFence(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(TYPE, TypeLR.DEFAULT)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		/** pos.up() = Replaceable block. for 1.21.4 **/
		if (pos.getY() < worldIn.getMaxY() && worldIn.getBlockState(pos.above()).canBeReplaced(context)) {
			return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
					.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
		}

		else { return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack stack) {
		FluidState fluidUp = worldIn.getFluidState(pos.above());
		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)
				.setValue(H_FACING, state.getValue(H_FACING))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidUp.getType() == Fluids.WATER)), 3);
	}
	
	/* Limit the place. */
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		BlockState downState = worldIn.getBlockState(downPos);

		/** Lower part is true. **/
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) { return true; }

		/** Upper part is this block. **/
		else { return downState.getBlock() == this; }
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
	
	@SuppressWarnings("deprecation")
	public long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}
	
	/* Connect the blocks. */
	private boolean canConnectTo(BlockGetter worldIn, BlockPos pos, Direction direction, Direction targetDirection) {
		BlockState state = worldIn.getBlockState(pos.relative(direction));

		if(state.getBlock() == this) {
			Direction fenceDirection = state.getValue(H_FACING);
			return fenceDirection.equals(targetDirection);
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
		BlockState state1 = super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
		if (!state1.isAir()) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		DoubleBlockHalf half = state.getValue(HALF);
		if (facing.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (facing == Direction.UP) || newState.getBlock() == this && newState.getValue(HALF) != half) {
			return (half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(worldIn, pos)) ? Blocks.AIR.defaultBlockState() : this.getConnectState(state, worldIn, pos, state.getValue(H_FACING));
		}
		else {
			return Blocks.AIR.defaultBlockState();
		}
	}
	
	/* Destroy a DoubleBlock from DoublePlantBlock.class */
	@Override
	public BlockState playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		if (!worldIn.isClientSide) {
			if (playerIn.isCreative()) { breakLowerPart(worldIn, pos, state, playerIn); } 
			
			else { dropResources(state, worldIn, pos, (BlockEntity)null, playerIn, playerIn.getMainHandItem()); }
		}
		return super.playerWillDestroy(worldIn, pos, state, playerIn);
	}

	@Override
	public void playerDestroy(Level worldIn, Player playerIn, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		super.playerDestroy(worldIn, playerIn, pos, Blocks.AIR.defaultBlockState(), blockEntity, stack);
	}

	protected static void breakLowerPart(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		DoubleBlockHalf half = state.getValue(HALF);
		if (half == DoubleBlockHalf.UPPER) {
			BlockPos downPos = pos.below();
			BlockState downState = worldIn.getBlockState(downPos);
			if (downState.is(state.getBlock()) && downState.getValue(HALF) == DoubleBlockHalf.LOWER) {
				BlockState downState1 = downState.hasProperty(BlockStateProperties.WATERLOGGED) && downState.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
				worldIn.setBlock(downPos, downState1, 35);
				worldIn.levelEvent(playerIn, 2001, downPos, Block.getId(downState)); }
		}
	}
	
	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, HALF, TYPE, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default:
			return (state.getValue(HALF) == DoubleBlockHalf.LOWER)? BOT_NORTH : TOP_NORTH;
		case SOUTH:
			return (state.getValue(HALF) == DoubleBlockHalf.LOWER)? BOT_SOUTH : TOP_SOUTH;
		case WEST:
			return (state.getValue(HALF) == DoubleBlockHalf.LOWER)? BOT_WEST : TOP_WEST;
		case EAST:
			return (state.getValue(HALF) == DoubleBlockHalf.LOWER)? BOT_EAST : TOP_EAST;
		}
	}
}
