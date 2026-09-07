package com.ayutaki.chinjufumod.blocks.window;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CurtainTall extends Abstract_WaterLogged {
	public static final MapCodec<CurtainTall> CODEC = simpleCodec(CurtainTall::new);
	@Override
	public MapCodec<? extends CurtainTall> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	
	/* Collision */
	private static final VoxelShape CLOSE_NORTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	private static final VoxelShape CLOSE_EAST = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape CLOSE_SOUTH = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape CLOSE_WEST = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);

	private static final VoxelShape OPENR_NORTH = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.1D);
	private static final VoxelShape OPENR_EAST = Block.box(13.9D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape OPENR_SOUTH = Block.box(0.0D, 0.0D, 13.9D, 2.0D, 16.0D, 16.0D);
	private static final VoxelShape OPENR_WEST = Block.box(0.0D, 0.0D, 0.0D, 2.1D, 16.0D, 2.0D);

	private static final VoxelShape OPENL_NORTH = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.1D);
	private static final VoxelShape OPENL_EAST = Block.box(13.9D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
	private static final VoxelShape OPENL_SOUTH = Block.box(14.0D, 0.0D, 13.9D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape OPENL_WEST = Block.box(0.0D, 0.0D, 14.0D, 2.1D, 16.0D, 16.0D);
	
	public CurtainTall(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(HINGE, DoorHingeSide.LEFT)
				.setValue(HALF, DoubleBlockHalf.UPPER)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Base_Hake) { return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; }

		else {
			state = state.cycle(OPEN);
			worldIn.setBlock(pos, state, 10);
			CMEvents.soundCurtain(worldIn, pos, 0.8F, 0.85F);
			return ItemInteractionResult.SUCCESS;
		}
	}

	public void setOpen(BlockState state, Level worldIn, BlockPos pos, boolean open) {
		BlockState state1 = worldIn.getBlockState(pos);
		if (state1.is(this) && state1.getValue(OPEN) != open) {
			worldIn.setBlock(pos, state1.setValue(OPEN, Boolean.valueOf(open)), 10);
			CMEvents.soundCurtain(worldIn, pos, 0.8F, 1.0F);
		}
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		Player playerIn = context.getPlayer();

		/** pos.up() = Replaceable block. **/
		if (worldIn.getBlockState(pos.below()).canBeReplaced(context)) {
			
			DoorHingeSide SNEAK = playerIn.isCrouching()? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
			return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection()).setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
					.setValue(HINGE, SNEAK).setValue(OPEN, Boolean.valueOf(false))
					.setValue(HALF, DoubleBlockHalf.UPPER); }

		else { return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack stack) {
		FluidState fluidDown = worldIn.getFluidState(pos.below());

		worldIn.setBlock(pos.below(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(H_FACING, state.getValue(H_FACING))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidDown.getType() == Fluids.WATER)), 3);
	}
	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
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
	
	/* Update BlockState. is(this) */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		BlockState state1 = super.updateShape(state, facing, newState, worldIn, pos, newPos);
		if (!state1.isAir()) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		DoubleBlockHalf half = state.getValue(HALF);
		if (facing.getAxis() == Direction.Axis.Y && half == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
			return (newState.is(this) && newState.getValue(HALF) != half) ? state
					.setValue(H_FACING, newState.getValue(H_FACING)).setValue(OPEN, newState.getValue(OPEN))
					.setValue(HINGE, newState.getValue(HINGE)) : Blocks.AIR.defaultBlockState();
		}
		else {
			return (half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(worldIn, pos)) ? Blocks.AIR
					.defaultBlockState() : super.updateShape(state, facing, newState, worldIn, pos, newPos);
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
				return flagopen? CLOSE_NORTH : (flagright? OPENR_NORTH : OPENL_NORTH);
			case SOUTH:
				return flagopen? CLOSE_SOUTH : (flagright? OPENR_SOUTH : OPENL_SOUTH);
			case WEST:
				return flagopen? CLOSE_WEST : (flagright? OPENR_WEST : OPENL_WEST);
			case EAST:
				return flagopen? CLOSE_EAST : (flagright? OPENR_EAST : OPENL_EAST);
			}

		case UPPER:
			switch (direction) {
			case NORTH:
			default:
				return flagopen? CLOSE_NORTH : (flagright? OPENR_NORTH : OPENL_NORTH);
			case SOUTH:
				return flagopen? CLOSE_SOUTH : (flagright? OPENR_SOUTH : OPENL_SOUTH);
			case WEST:
				return flagopen? CLOSE_WEST : (flagright? OPENR_WEST : OPENL_WEST);
			case EAST:
				return flagopen? CLOSE_EAST : (flagright? OPENR_EAST : OPENL_EAST);
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
		builder.add(HALF, H_FACING, OPEN, HINGE, WATERLOGGED);
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_window").withStyle(ChatFormatting.GRAY));
	}
}
