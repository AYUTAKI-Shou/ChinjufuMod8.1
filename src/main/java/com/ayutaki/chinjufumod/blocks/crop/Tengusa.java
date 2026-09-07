package com.ayutaki.chinjufumod.blocks.crop;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Tengusa extends Abstract_WaterLogged {
	public static final MapCodec<Tengusa> CODEC = simpleCodec(Tengusa::new);
	@Override
	public MapCodec<? extends Tengusa> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_0_7 = IntegerProperty.create("stage", 0, 7);
	
	/* Collision */
	private static final VoxelShape[] SHAPES = new VoxelShape[]{ Block.box(2.0D, -1.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.box(2.0D, -1.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.box(2.0D, -1.0D, 2.0D, 14.0D, 7.0D, 14.0D),
			Block.box(2.0D, -1.0D, 2.0D, 14.0D, 7.0D, 14.0D),
			Block.box(1.5D, -1.0D, 1.5D, 14.5D, 8.0D, 14.5D),
			Block.box(1.5D, -1.0D, 1.5D, 14.5D, 8.0D, 14.5D),
			Block.box(1.0D, -1.0D, 1.0D, 15.0D, 10.0D, 15.0D),
			Block.box(1.0D, -1.0D, 1.0D, 15.0D, 10.0D, 15.0D) };

	public Tengusa(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_0_7, Integer.valueOf(0))
				.setValue(WATERLOGGED, Boolean.valueOf(true)));
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_7);

		if (i != 7) {
			if (hItem == Items.BONE_MEAL) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
				
				if (i < 6) { worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 2)), 3); }
				if (i == 6) { worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 1)), 3); } }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	
	/* Limit the place. */
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.getBlock() == Blocks.SAND || state.getBlock() == Blocks.GRAVEL;
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		FluidState fluid = worldIn.getFluidState(pos);
		boolean canPlace = fluid.is(FluidTags.WATER) && fluid.getAmount() == 8;
		
		return this.mayPlaceOn(worldIn.getBlockState(downPos), worldIn, downPos) && canPlace;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		float temp = worldIn.getBiome(pos).value().getBaseTemperature();
		boolean canPlace = fluid.is(FluidTags.WATER) && fluid.getAmount() == 8 && (temp >= 0.5F);
		
		return canPlace ? super.getStateForPlacement(context) : null;
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		BlockState blockState = super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
		
		if (!blockState.isAir()) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return !state.canSurvive(worldIn, pos) ? Blocks.AIR.defaultBlockState() : blockState;
	}
	
	/* TickRandom. Slow growth rate. rand.nextInt(12) == 0 */
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_0_7);

		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		if (i != 7 && worldIn.getRawBrightness(pos, 0) >= 9 && state.getValue(WATERLOGGED)) {
			if (rand.nextInt(8) == 0) {
				worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 1)), 2); } }

		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(STAGE_0_7)];
	}
	
	public boolean canPlaceLiquid(@Nullable Player playerIn, BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return false;
	}

	public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluid) {
		return false;
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_7, WATERLOGGED);
	}
	
	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(Items_Teatime.CROP_TENGUSA.get());
	}
}
