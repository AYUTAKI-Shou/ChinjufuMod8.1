package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Inagi extends Abstract_WaterLogged {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	public static final EnumProperty<DoubleBlockHalf> HALF = EnumProperty.create("half", DoubleBlockHalf.class);

	/* Collision */
	private static final VoxelShape BOT_SOUTH = Block.box(2.0D, 7.0D, 7.25D, 14.0D, 16.0D, 8.75D);
	private static final VoxelShape BOT_WEST = Block.box(7.25D, 7.0D, 2.0D, 8.75D, 16.0D, 14.0D);
	private static final VoxelShape BOT_NORTH = Block.box(2.0D, 7.0D, 7.25D, 14.0D, 16.0D, 8.75D);
	private static final VoxelShape BOT_EAST = Block.box(7.25D, 7.0D, 2.0D, 8.75D, 16.0D, 14.0D);

	private static final VoxelShape TOP_SOUTH = Block.box(2.0D, 0.0D, 7.25D, 14.0D, 5.5D, 8.75D);
	private static final VoxelShape TOP_WEST = Block.box(7.25D, 0.0D, 2.0D, 8.75D, 5.5D, 14.0D);
	private static final VoxelShape TOP_NORTH = Block.box(2.0D, 0.0D, 7.25D, 14.0D, 5.5D, 8.75D);
	private static final VoxelShape TOP_EAST = Block.box(7.25D, 0.0D, 2.0D, 8.75D, 5.5D, 14.0D);

	private static final VoxelShape S4_SOUTH = Block.box(0.0D, 3.0D, 7.25D, 16.0D, 4.5D, 8.75D);
	private static final VoxelShape S4_WEST = Block.box(7.25D, 3.0D, 0.0D, 8.75D, 4.5D, 16.0D);
	private static final VoxelShape S4_NORTH = Block.box(0.0D, 3.0D, 7.25D, 16.0D, 4.5D, 8.75D);
	private static final VoxelShape S4_EAST = Block.box(7.25D, 3.0D, 0.0D, 8.75D, 4.5D, 16.0D);

	public Inagi(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);
		/** 1=Empty, 2=Wet, 3=Wet, 4=Dry **/
		
		BlockState lowFace = this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(H_FACING, state.getValue(H_FACING));
		BlockState upFace = this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(H_FACING, state.getValue(H_FACING));
		int gHC = hStack.getCount();

		if (state.getValue(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { //!WATERLOGGED
			/** Set rice plant **/
			if (i == 1) {
				if (hItem == Items_Teatime.INE.get() && gHC >= 8) {
					/* Consume 8 Items. */
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					
					if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
						worldIn.setBlock(pos, upFace.setValue(STAGE_1_4, Integer.valueOf(2)), 3);
						worldIn.setBlock(pos.below(), lowFace.setValue(STAGE_1_4, Integer.valueOf(2)), 3); } }
				
				if (hItem == Items_Teatime.INE.get() && gHC < 8) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
				
				if (hItem != Items_Teatime.INE.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			/** Too early to collect **/
			if (i == 2 || i == 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }	
			
			/** Can harvest **/
			if (i == 4) {
				if (hStack.isEmpty() && hItem != Items_Teatime.INE.get()) {
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.INE_D.get(), 8);
					CMEvents.addEXP(2, worldIn, pos);
		
					if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
						worldIn.setBlock(pos, upFace.setValue(STAGE_1_4, Integer.valueOf(1)), 3);
						worldIn.setBlock(pos.below(), lowFace.setValue(STAGE_1_4, Integer.valueOf(1)), 3); }
		
					if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
						worldIn.setBlock(pos, lowFace.setValue(STAGE_1_4, Integer.valueOf(1)), 3);
						worldIn.setBlock(pos.above(), upFace.setValue(STAGE_1_4, Integer.valueOf(1)), 3); } }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
			}
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		/** pos.up() = Replaceable block. **/
		if (pos.getY() < worldIn.getMaxBuildHeight() - 1 && worldIn.getBlockState(pos.above()).canBeReplaced(context)) {
			return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
					.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
					.setValue(STAGE_1_4, Integer.valueOf(1)).setValue(HALF, DoubleBlockHalf.LOWER); }

		else { return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack stack) {
		FluidState fluidUp = worldIn.getFluidState(pos.above());

		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(H_FACING, state.getValue(H_FACING))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidUp.getType() == Fluids.WATER)), 3);
	}
	
	/* Limit the place. */
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		BlockState downState = worldIn.getBlockState(downPos);
		
		if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
			return this.mayPlaceOn(downState, worldIn, downPos); }

		else {
			if (state.getBlock() != this) return super.canSurvive(state, worldIn, pos);
			return downState.getBlock() == this && downState.getValue(HALF) == DoubleBlockHalf.LOWER; }
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
	
	public long getSeed(BlockState state, BlockPos pos) {
		return Mth.getSeed(pos.getX(), pos.below(state.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		BlockState state1 = super.updateShape(state, facing, newState, worldIn, pos, newPos);
		if (!state1.isAir()) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		DoubleBlockHalf half = state.getValue(HALF);
		if (facing.getAxis() == Direction.Axis.Y && half == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
			return (newState.getBlock() == Crop_Blocks.INAGI.get()) &&
					newState.getValue(HALF) != half ? state.setValue(H_FACING, newState.getValue(H_FACING)).setValue(STAGE_1_4, newState.getValue(STAGE_1_4)) : Blocks.AIR.defaultBlockState(); }

		else {
			return (half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(worldIn, pos)) ? Blocks.AIR
					.defaultBlockState() : super.updateShape(state, facing, newState, worldIn, pos, newPos); }
	}
	
	/* TickRandom */
	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_4);
		DoubleBlockHalf half = state.getValue(HALF);
		
		BlockState lowFace = this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER).setValue(H_FACING, state.getValue(H_FACING));
		BlockState upFace = this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(H_FACING, state.getValue(H_FACING));
		
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		switch (half) {
		case LOWER:
		default:
			if (state.getValue(WATERLOGGED) && i != 1) {
				if (rand.nextInt(4) == 0) { 
					CMEvents.drop1_ROTTENFOOD(worldIn, pos);
					worldIn.setBlock(pos, lowFace.setValue(STAGE_1_4, Integer.valueOf(1)), 3);
					worldIn.setBlock(pos.above(), upFace.setValue(STAGE_1_4, Integer.valueOf(1)), 3); } }
			
			else { }
			break;

		case UPPER:
			if (!state.getValue(WATERLOGGED) && !worldIn.isRainingAt(pos.above()) && i != 1 && i != 4) {
				if (rand.nextInt(4) == 0) {
					worldIn.setBlock(pos, upFace.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3);
					worldIn.setBlock(pos.below(), lowFace.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); } }

			if (state.getValue(WATERLOGGED) && i != 1) {
				if (rand.nextInt(4) == 0) {
					CMEvents.drop1_ROTTENFOOD(worldIn, pos);
					worldIn.setBlock(pos, upFace.setValue(STAGE_1_4, Integer.valueOf(1)), 3);
					worldIn.setBlock(pos.below(), lowFace.setValue(STAGE_1_4, Integer.valueOf(1)), 3); } }
			
			else { }
			break;
		} // switch
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		int i = state.getValue(STAGE_1_4);

		switch (direction) {
		case NORTH:
		default:
			return (state.getValue(HALF) != DoubleBlockHalf.UPPER && i == 1)? Shapes.empty() :
							((state.getValue(HALF) != DoubleBlockHalf.UPPER && i != 1)? BOT_NORTH :
							((state.getValue(HALF) == DoubleBlockHalf.UPPER && i == 1)? S4_NORTH : TOP_NORTH));
		case SOUTH:
			return (state.getValue(HALF) != DoubleBlockHalf.UPPER && i == 1)? Shapes.empty() :
							((state.getValue(HALF) != DoubleBlockHalf.UPPER && i != 1)? BOT_SOUTH :
							((state.getValue(HALF) == DoubleBlockHalf.UPPER && i == 1)? S4_SOUTH : TOP_SOUTH));
		case WEST:
			return (state.getValue(HALF) != DoubleBlockHalf.UPPER && i == 1)? Shapes.empty() :
							((state.getValue(HALF) != DoubleBlockHalf.UPPER && i != 1)? BOT_WEST :
							((state.getValue(HALF) == DoubleBlockHalf.UPPER && i == 1)? S4_WEST : TOP_WEST));
		case EAST:
			return (state.getValue(HALF) != DoubleBlockHalf.UPPER && i == 1)? Shapes.empty() :
							((state.getValue(HALF) != DoubleBlockHalf.UPPER && i != 1)? BOT_EAST :
							((state.getValue(HALF) == DoubleBlockHalf.UPPER && i == 1)? S4_EAST : TOP_EAST));
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, HALF, STAGE_1_4, WATERLOGGED);
	}
	
	/* Clone Item in Creative. */
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.INAGI.get());
	}
	
	/* Only for INAGI. */
	@Override
	public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		BlockState upState = worldIn.getBlockState(pos.above());
		BlockState downState = worldIn.getBlockState(pos.below());
		int i = state.getValue(STAGE_1_4);

		if (downState.getBlock() == this && !playerIn.isCreative()) {
			worldIn.destroyBlock(pos.below(), false);
			if (i == 4) { CMEvents.addEXP(2, worldIn, pos); }
		}

		if (upState.getBlock() == this && !playerIn.isCreative()) {
			worldIn.destroyBlock(pos.above(), false);
			if (i == 4) { CMEvents.addEXP(2, worldIn, pos); }
		}

		if (playerIn.isCreative()) { worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 35); }
		super.playerWillDestroy(worldIn, pos, state, playerIn);
	}
	
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}
	
	@Override
	public void playerDestroy(Level worldIn, Player playerIn, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		super.playerDestroy(worldIn, playerIn, pos, Blocks.AIR.defaultBlockState(), blockEntity, stack);
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(new TranslatableComponent("tips.block_inagi").withStyle(ChatFormatting.GRAY));
	}
}
