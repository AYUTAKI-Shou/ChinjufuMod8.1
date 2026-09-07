package com.ayutaki.chinjufumod.blocks.season;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class SnowMan extends Abstract_SnowMan {
	public static final MapCodec<SnowMan> CODEC = simpleCodec(SnowMan::new);
	@Override
	public MapCodec<? extends SnowMan> codec() { return CODEC; }

	/** 1=normai, 2=carrot, 3=Roma, 4=bucket **/
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);

	public SnowMan(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, false));
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		/** 1=normai, 2=carrot, 3=Roma, 4=blank **/
		int i = state.getValue(STAGE_1_4);
		DoubleBlockHalf half = state.getValue(HALF);
		
		BlockState upState = Seasonal_Blocks.SNOWMAN.get().defaultBlockState()
				.setValue(H_FACING, state.getValue(H_FACING))
				.setValue(HALF, DoubleBlockHalf.UPPER)
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED))
				.setValue(STAGE_1_4, Integer.valueOf(i));
		BlockState downState = Seasonal_Blocks.SNOWMAN.get().defaultBlockState()
				.setValue(H_FACING, state.getValue(H_FACING))
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED));
		
		boolean wool =(hItem == Items.WHITE_WOOL || hItem == Items.ORANGE_WOOL || hItem == Items.MAGENTA_WOOL || hItem == Items.LIGHT_BLUE_WOOL || 
				hItem == Items.YELLOW_WOOL || hItem == Items.LIME_WOOL || hItem == Items.PINK_WOOL || hItem == Items.GRAY_WOOL || 
				hItem == Items.LIGHT_GRAY_WOOL || hItem == Items.CYAN_WOOL || hItem == Items.PURPLE_WOOL || hItem == Items.BLUE_WOOL || 
				hItem == Items.BROWN_WOOL || hItem == Items.GREEN_WOOL || hItem == Items.RED_WOOL || hItem == Items.BLACK_WOOL);

		switch (half) {
		case LOWER:
		default:
			if (state.getValue(DOWN)) { }
			
			else { //!DOWN
				if (hItem == Items.SNOW) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), upState.setValue(DOWN, Boolean.valueOf(true)), 3);
					worldIn.setBlock(pos, state.setValue(DOWN, Boolean.valueOf(true)), 3); }
				
				else { CMEvents.textNotHave(worldIn, pos, playerIn); } }
			break;

		case UPPER:
			switch (i) {
			case 1:
			default:
				if (hItem == Items.CARROT) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(2)), 3);
					worldIn.setBlock(pos.below(), downState.setValue(STAGE_1_4, Integer.valueOf(2)), 3); }
				
				else { CMEvents.textNotHave(worldIn, pos, playerIn); }
				break;

			/** 1=normai, 2=carrot, 3=Roma, 4=blank **/
			/** White=1, Orange=2, Magenta=3, LightBlue=4, Yellow=5, Lime=6, Pink=7, Gray=8, **/
			/** LightGray=9, Cyan=10, Purple=11, Blue=12, Brown=13, Green=14, Red=15, Black=16 **/
			case 2:
				if (hItem == Items_Teatime.FOOD_TOMATO.get()) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(3)), 3);
					worldIn.setBlock(pos.below(), downState.setValue(STAGE_1_4, Integer.valueOf(3)), 3); }
				
				if (hItem == Items.BUCKET) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3);
					worldIn.setBlock(pos.below(), downState.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }
				
				if (wool) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Seasonal_Blocks.SNOWMAN_COLOR.get().defaultBlockState()
							.setValue(SnowMan_Color.H_FACING, state.getValue(H_FACING))
							.setValue(SnowMan_Color.HALF, DoubleBlockHalf.UPPER)
							.setValue(SnowMan_Color.DOWN, state.getValue(DOWN))
							.setValue(SnowMan_Color.WATERLOGGED, state.getValue(WATERLOGGED))
							.setValue(SnowMan_Color.STAGE_1_16, Integer.valueOf(this.takeMeta(hItem))), 3);
					worldIn.setBlock(pos.below(), Seasonal_Blocks.SNOWMAN_COLOR.get().defaultBlockState()
							.setValue(SnowMan_Color.H_FACING, state.getValue(H_FACING))
							.setValue(SnowMan_Color.HALF, DoubleBlockHalf.LOWER)
							.setValue(SnowMan_Color.DOWN, state.getValue(DOWN))
							.setValue(SnowMan_Color.WATERLOGGED, state.getValue(WATERLOGGED))
							.setValue(SnowMan_Color.STAGE_1_16, Integer.valueOf(this.takeMeta(hItem))), 3); }
				
				if (hItem != Items_Teatime.FOOD_TOMATO.get() && hItem != Items.BUCKET && !wool) {
					CMEvents.textNotHave(worldIn, pos, playerIn); }
				break;

			case 3:
				if (hStack.isEmpty()) {
					CMEvents.soundSnowBreak(worldIn, pos);
					CMEvents.mode1Through_takeItem(playerIn, hand, Items_Teatime.FOOD_TOMATO.get());
				
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(2)), 3);
					worldIn.setBlock(pos.below(), downState.setValue(STAGE_1_4, Integer.valueOf(2)), 3); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
				break;
				
			case 4:
				if (hStack.isEmpty()) {
					CMEvents.soundSnowBreak(worldIn, pos);
					CMEvents.mode1Through_takeItem(playerIn, hand, Items.BUCKET);
				
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(2)), 3);
					worldIn.setBlock(pos.below(), downState.setValue(STAGE_1_4, Integer.valueOf(2)), 3); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
				break;
			} // switch STAGE_1_4
			break;
		} // switch LOWER-UPPER
		
		return InteractionResult.SUCCESS;
	}

	/** White=1, Orange=2, Magenta=3, LightBlue=4, Yellow=5, Lime=6, Pink=7, Gray=8, **/
	/** LightGray=9, Cyan=10, Purple=11, Blue=12, Brown=13, Green=14, Red=15, Black=16 **/
	private int takeMeta(Item hItem) {
		if (hItem == Items.WHITE_WOOL) { return 1; }
		if (hItem == Items.ORANGE_WOOL) { return 2; }
		if (hItem == Items.MAGENTA_WOOL) { return 3; }
		if (hItem == Items.LIGHT_BLUE_WOOL) { return 4; }
		if (hItem == Items.YELLOW_WOOL) { return 5; }
		if (hItem == Items.LIME_WOOL) { return 6; }
		if (hItem == Items.PINK_WOOL) { return 7; }
		if (hItem == Items.GRAY_WOOL) { return 8; }
		if (hItem == Items.LIGHT_GRAY_WOOL) { return 9; }
		if (hItem == Items.CYAN_WOOL) { return 10; }
		if (hItem == Items.PURPLE_WOOL) { return 11; }
		if (hItem == Items.BLUE_WOOL) { return 12; }
		if (hItem == Items.BROWN_WOOL) { return 13; }
		if (hItem == Items.GREEN_WOOL) { return 14; }
		if (hItem == Items.RED_WOOL) { return 15; }
		else { return 16; }
	}
	
	/* Gives a value when placed. */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		Block block = worldIn.getBlockState(pos).getBlock();
		
		/** pos.up() = Replaceable block. for 1.21.4 **/
		if (pos.getY() < worldIn.getMaxY() && worldIn.getBlockState(pos.above()).canBeReplaced(context)) {
			
			if (block == Blocks.SNOW) {
				return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
						.setValue(H_FACING, context.getHorizontalDirection().getOpposite())
						.setValue(STAGE_1_4, Integer.valueOf(1))
						.setValue(DOWN, Boolean.valueOf(true)); }
			
			else {
				return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
						.setValue(H_FACING, context.getHorizontalDirection().getOpposite())
						.setValue(STAGE_1_4, Integer.valueOf(1))
						.setValue(DOWN, Boolean.valueOf(false)); }
		}

		else { return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack stack) {
		FluidState fluidUp = worldIn.getFluidState(pos.above());
		
		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)
				.setValue(H_FACING, state.getValue(H_FACING))
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidUp.getType() == Fluids.WATER)), 3);
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, HALF, STAGE_1_4, WATERLOGGED);
	}
}
