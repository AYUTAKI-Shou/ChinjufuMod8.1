package com.ayutaki.chinjufumod.blocks.garden;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class Base_Niwaishi extends Abstract_WaterLogged {
	public static final MapCodec<Base_Niwaishi> CODEC = simpleCodec(Base_Niwaishi::new);
	@Override
	public MapCodec<? extends Base_Niwaishi> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_0_15 = IntegerProperty.create("stage", 0, 15);

	public Base_Niwaishi(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_0_15, Integer.valueOf(0))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_15);

		if (hStack.isEmpty()) {
			if (playerIn.isCrouching()) {
				if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 12|| i == 14) {
					CMEvents.soundStonePlace(worldIn, pos);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
	
				if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11 || i == 13|| i == 15) {
					CMEvents.soundStonePlace(worldIn, pos);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(i - 1)), 3); } }
		}
		
		else { //!empty()
			if (hItem == Items_Wadeco.NOMI.get()) { return InteractionResult.PASS;}
	
			if (hItem != Items_Wadeco.NOMI.get()) { }
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

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_15, WATERLOGGED);
	}
}
