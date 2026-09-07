package com.ayutaki.chinjufumod.blocks.window;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.ItemCurtain;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WindowTall extends Abstract_WaterLogged {
	public static final MapCodec<WindowTall> CODEC = simpleCodec(WindowTall::new);
	@Override
	public MapCodec<? extends WindowTall> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	
	/* Collision */
	private static final VoxelShape FRAME_SOUTH = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 0.01D, 9.0D);
	private static final VoxelShape FRAME_WEST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 0.01D, 16.0D);
	private static final VoxelShape FRAME_NORTH = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 0.01D, 9.0D);
	private static final VoxelShape FRAME_EAST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 0.01D, 16.0D);

	private static final VoxelShape CLOSEB_SOUTH = Shapes.or(FRAME_SOUTH, Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D));
	private static final VoxelShape CLOSEB_WEST = Shapes.or(FRAME_WEST, Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D));
	private static final VoxelShape CLOSEB_NORTH = Shapes.or(FRAME_NORTH, Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D));
	private static final VoxelShape CLOSEB_EAST = Shapes.or(FRAME_EAST, Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D));
	
	private static final VoxelShape OPENBR_SOUTH = Shapes.or(FRAME_SOUTH, Block.box(13.8D, 0.0D, 7.0D, 15.8D, 16.0D, 23.0D));
	private static final VoxelShape OPENBR_WEST = Shapes.or(FRAME_WEST, Block.box(-7.0D, 0.0D, 13.8D, 9.0D, 16.0D, 15.8D));
	private static final VoxelShape OPENBR_NORTH = Shapes.or(FRAME_NORTH, Block.box(0.2D, 0.0D, -7.0D, 2.2D, 16.0D, 9.0D));
	private static final VoxelShape OPENBR_EAST = Shapes.or(FRAME_EAST, Block.box(7.0D, 0.0D, 0.2D, 23.0D, 16.0D, 2.2D));

	private static final VoxelShape OPENBL_SOUTH = Shapes.or(FRAME_SOUTH, Block.box(0.2D, 0.0D, 7.0D, 2.2D, 16.0D, 23.0D));
	private static final VoxelShape OPENBL_WEST = Shapes.or(FRAME_WEST, Block.box(-7.0D, 0.0D, 0.2D, 9.0D, 16.0D, 2.2D));
	private static final VoxelShape OPENBL_NORTH = Shapes.or(FRAME_NORTH, Block.box(13.8D, 0.0D, -7.0D, 15.8D, 16.0D, 9.0D));
	private static final VoxelShape OPENBL_EAST = Shapes.or(FRAME_EAST, Block.box(7.0D, 0.0D, 13.8D, 23.0D, 16.0D, 15.8D));
	
	private static final VoxelShape CLOSE_SOUTH = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
	private static final VoxelShape CLOSE_WEST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);
	private static final VoxelShape CLOSE_NORTH = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
	private static final VoxelShape CLOSE_EAST = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);

	private static final VoxelShape OPENR_SOUTH = Block.box(13.8D, 0.0D, 7.0D, 15.8D, 16.0D, 23.0D);
	private static final VoxelShape OPENR_WEST = Block.box(-7.0D, 0.0D, 13.8D, 9.0D, 16.0D, 15.8D);
	private static final VoxelShape OPENR_NORTH = Block.box(0.2D, 0.0D, -7.0D, 2.2D, 16.0D, 9.0D);
	private static final VoxelShape OPENR_EAST = Block.box(7.0D, 0.0D, 0.2D, 23.0D, 16.0D, 2.2D);

	private static final VoxelShape OPENL_SOUTH = Block.box(0.2D, 0.0D, 7.0D, 2.2D, 16.0D, 23.0D);
	private static final VoxelShape OPENL_WEST = Block.box(-7.0D, 0.0D, 0.2D, 9.0D, 16.0D, 2.2D);
	private static final VoxelShape OPENL_NORTH = Block.box(13.8D, 0.0D, -7.0D, 15.8D, 16.0D, 9.0D);
	private static final VoxelShape OPENL_EAST = Block.box(7.0D, 0.0D, 13.8D, 23.0D, 16.0D, 15.8D);
	
	public WindowTall(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(HINGE, DoorHingeSide.LEFT)
				.setValue(POWERED, Boolean.valueOf(false))
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof ItemCurtain) { return InteractionResult.PASS; }

		else {
			state = state.cycle(OPEN);
			worldIn.setBlock(pos, state, 10);
	
			if (state.getValue(OPEN) == true) { CMEvents.soundWin_OpenL(worldIn, pos); }
			if (state.getValue(OPEN) == false) { CMEvents.soundWin_CloseL(worldIn, pos); }
	
			return InteractionResult.SUCCESS;
		}
	}

	public void setOpen(BlockState state, Level worldIn, BlockPos pos, boolean open) {
		BlockState state1 = worldIn.getBlockState(pos);
		if (state1.is(this) && state1.getValue(OPEN) != open) {
			worldIn.setBlock(pos, state1.setValue(OPEN, Boolean.valueOf(open)), 10);
			this.moveSound(worldIn, pos, open);
		}
	}
	
	/* Get POWER. for 1.21.4 */
	@Override
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block block, @Nullable Orientation p_369522_, boolean open) {
		boolean flag = worldIn.hasNeighborSignal(pos) || worldIn.hasNeighborSignal(pos.relative(state.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));

		if (block != this && flag != state.getValue(POWERED)) {
			if (flag != state.getValue(OPEN)) { this.moveSound(worldIn, pos, flag); }
			worldIn.setBlock(pos, state.setValue(POWERED, Boolean.valueOf(flag)).setValue(OPEN, Boolean.valueOf(flag)), 2);
		}
	}
	
	/* Play Sound */
	private void moveSound(Level worldIn, BlockPos pos, boolean open) {
		if (open) { CMEvents.soundWin_OpenL(worldIn, pos); }
		else { CMEvents.soundWin_CloseL(worldIn, pos); }
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		Player playerIn = context.getPlayer();

		/** pos.up() = Replaceable block. for 1.21.4 **/
		if (pos.getY() < worldIn.getMaxY() && worldIn.getBlockState(pos.above()).canBeReplaced(context)) {
			boolean flag = worldIn.hasNeighborSignal(pos) || worldIn.hasNeighborSignal(pos.above());

			DoorHingeSide SNEAK = playerIn.isCrouching()? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
			return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
					.setValue(HINGE, SNEAK).setValue(POWERED, Boolean.valueOf(flag))
					.setValue(OPEN, Boolean.valueOf(flag)).setValue(HALF, DoubleBlockHalf.LOWER); }

		else { return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack stack) {
		FluidState fluidUp = worldIn.getFluidState(pos.above());

		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(H_FACING, state.getValue(H_FACING))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidUp.getType() == Fluids.WATER)), 3);
	}
	
	/* Limit the place. */
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		BlockState downState = worldIn.getBlockState(downPos);

		/** Lower part is true. **/
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) { return true; }

		/** Upper part is this block. **/
		else { return downState.is(this); }
	}
	
	/* HORIZONTAL Property */
	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return mirror == Mirror.NONE ? state : state.rotate(mirror.getRotation(state.getValue(H_FACING))).cycle(HINGE);
	}
	
	@SuppressWarnings("deprecation")
	public long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		BlockState state1 = super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
		if (!state1.isAir()) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		DoubleBlockHalf half = state.getValue(HALF);
		if (facing.getAxis() == Direction.Axis.Y && half == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
			return (newState.is(this) && newState.getValue(HALF) != half) ? state
					.setValue(H_FACING, newState.getValue(H_FACING)).setValue(OPEN, newState.getValue(OPEN))
					.setValue(HINGE, newState.getValue(HINGE)).setValue(POWERED, newState.getValue(POWERED)) : Blocks.AIR.defaultBlockState();
		}
		else {
			return (half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(worldIn, pos)) ? Blocks.AIR
					.defaultBlockState() : super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
		}
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		DoubleBlockHalf half = state.getValue(HALF);
		boolean flagopen = !state.getValue(OPEN);
		boolean flagright = (state.getValue(HINGE) == DoorHingeSide.RIGHT);

		switch (half) {
		case LOWER:
		default:
			switch (direction) {
			case NORTH:
			default:
				return flagopen? CLOSEB_NORTH : (flagright? OPENBL_NORTH : OPENBR_NORTH);
			case SOUTH:
				return flagopen? CLOSEB_SOUTH : (flagright? OPENBL_SOUTH : OPENBR_SOUTH);
			case WEST:
				return flagopen? CLOSEB_WEST : (flagright? OPENBL_WEST : OPENBR_WEST);
			case EAST:
				return flagopen? CLOSEB_EAST : (flagright? OPENBL_EAST : OPENBR_EAST);
			}

		case UPPER:
			switch (direction) {
			case NORTH:
			default:
				return flagopen? CLOSE_NORTH : (flagright? OPENL_NORTH : OPENR_NORTH);
			case SOUTH:
				return flagopen? CLOSE_SOUTH : (flagright? OPENL_SOUTH : OPENR_SOUTH);
			case WEST:
				return flagopen? CLOSE_WEST : (flagright? OPENL_WEST : OPENR_WEST);
			case EAST:
				return flagopen? CLOSE_EAST : (flagright? OPENL_EAST : OPENR_EAST);
			}
		} // switch LOWER-UPPER
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
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
	
	@Override
	public boolean isPathfindable(BlockState state, PathComputationType type) {
		switch (type) {
		case LAND:
			return state.getValue(OPEN);
		case WATER:
			return false;
		case AIR:
			return state.getValue(OPEN);
		default:
			return false;
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HALF, H_FACING, OPEN, HINGE, POWERED, WATERLOGGED);
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_window").withStyle(ChatFormatting.GRAY));
	}
}
