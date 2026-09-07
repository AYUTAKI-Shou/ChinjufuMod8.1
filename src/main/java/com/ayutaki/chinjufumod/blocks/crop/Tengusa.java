package com.ayutaki.chinjufumod.blocks.crop;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.SandBlock;
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
	
	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_7);

		/** Too early to collect **/
		if (i != 7) { //i != 7
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
		return state.getBlock() instanceof SandBlock || state.getBlock() instanceof GravelBlock;
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		FluidState fluid = worldIn.getFluidState(pos);
		boolean canPlace = (fluid.getType() == Fluids.WATER) && fluid.getAmount() == 8;
		return this.mayPlaceOn(worldIn.getBlockState(downPos), worldIn, downPos) && canPlace;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		float temp = worldIn.getBiome(pos).value().getBaseTemperature();
		boolean canPlace = (fluid.getType() == Fluids.WATER) && fluid.getAmount() == 8 && (temp >= 0.5F);
		
		return canPlace ? super.getStateForPlacement(context) : null;
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		BlockState blockState = super.updateShape(state, facing, newState, worldIn, pos, newPos);
		
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return !state.canSurvive(worldIn, pos)? Blocks.AIR.defaultBlockState() : blockState;
	}
	
	/* TickRandom. Slow growth rate. rand.nextInt(12) == 0 */
	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		int i = state.getValue(STAGE_0_7);
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
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_7, WATERLOGGED);
	}
	
	public boolean canPlaceLiquid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return false;
	}

	public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluid) {
		return false;
	}
	
	/* Create Blockstate */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.CROP_TENGUSA.get());
	}
}
