package com.ayutaki.chinjufumod.blocks.season;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class SnowMan extends Abstract_SnowMan {
	/* Property */
	/** 1=normai, 2=carrot, 3=Roma, 4=blank **/
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);

	public SnowMan(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(HALF, DoubleBlockHalf.LOWER)
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(DOWN, Boolean.valueOf(false))
				.with(WATERLOGGED, false));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		
		/** 1=normai, 2=carrot, 3=Roma, 4=blank **/
		int i = state.get(STAGE_1_4);
		DoubleBlockHalf half = state.get(HALF);
		
		BlockState upState = this.getDefaultState()
				.with(H_FACING, state.get(H_FACING))
				.with(HALF, DoubleBlockHalf.UPPER)
				.with(STAGE_1_4, Integer.valueOf(i))
				.with(WATERLOGGED, state.get(WATERLOGGED));
		BlockState downState = this.getDefaultState()
				.with(H_FACING, state.get(H_FACING))
				.with(HALF, DoubleBlockHalf.LOWER)
				.with(DOWN, state.get(DOWN))
				.with(WATERLOGGED, state.get(WATERLOGGED));
		
		boolean wool =(hItem == Items.WHITE_WOOL || hItem == Items.ORANGE_WOOL || hItem == Items.MAGENTA_WOOL || hItem == Items.LIGHT_BLUE_WOOL || 
				hItem == Items.YELLOW_WOOL || hItem == Items.LIME_WOOL || hItem == Items.PINK_WOOL || hItem == Items.GRAY_WOOL || 
				hItem == Items.LIGHT_GRAY_WOOL || hItem == Items.CYAN_WOOL || hItem == Items.PURPLE_WOOL || hItem == Items.BLUE_WOOL || 
				hItem == Items.BROWN_WOOL || hItem == Items.GREEN_WOOL || hItem == Items.RED_WOOL || hItem == Items.BLACK_WOOL);

		switch (half) {
		case LOWER:
		default:
			if (state.get(DOWN)) { }
			
			else { //!DOWN
				if (hItem == Items.SNOW) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), upState.with(DOWN, Boolean.valueOf(true)), 3);
					worldIn.setBlockState(pos, state.with(DOWN, Boolean.valueOf(true)), 3); }
				
				else { CMEvents.textNotHave(worldIn, pos, playerIn); } }
			break;

		case UPPER:
			switch (i) {
			case 1:
			default:
				if (hItem == Items.CARROT) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(2)), 3);
					worldIn.setBlockState(pos.down(), downState.with(STAGE_1_4, Integer.valueOf(2)), 3); }
				
				else { CMEvents.textNotHave(worldIn, pos, playerIn); }
				break;

			/** 1=normai, 2=carrot, 3=Roma, 4=blank **/
			/** White=1, Orange=2, Magenta=3, LightBlue=4, Yellow=5, Lime=6, Pink=7, Gray=8, **/
			/** LightGray=9, Cyan=10, Purple=11, Blue=12, Brown=13, Green=14, Red=15, Black=16 **/
			case 2:
				if (hItem == Items_Teatime.FOOD_TOMATO) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(3)), 3);
					worldIn.setBlockState(pos.down(), downState.with(STAGE_1_4, Integer.valueOf(3)), 3); }
				
				if (hItem == Items.BUCKET) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(4)), 3);
					worldIn.setBlockState(pos.down(), downState.with(STAGE_1_4, Integer.valueOf(4)), 3); }
				
				if (wool) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Seasonal_Blocks.SNOWMAN_COLOR.getDefaultState()
							.with(SnowMan_Color.H_FACING, state.get(H_FACING))
							.with(SnowMan_Color.HALF, DoubleBlockHalf.UPPER)
							.with(SnowMan_Color.DOWN, state.get(DOWN))
							.with(SnowMan_Color.WATERLOGGED, state.get(WATERLOGGED))
							.with(SnowMan_Color.STAGE_1_16, Integer.valueOf(takeMeta(hItem))), 3);
					worldIn.setBlockState(pos.down(), Seasonal_Blocks.SNOWMAN_COLOR.getDefaultState()
							.with(SnowMan_Color.H_FACING, state.get(H_FACING))
							.with(SnowMan_Color.HALF, DoubleBlockHalf.LOWER)
							.with(SnowMan_Color.DOWN, state.get(DOWN))
							.with(SnowMan_Color.WATERLOGGED, state.get(WATERLOGGED))
							.with(SnowMan_Color.STAGE_1_16, Integer.valueOf(takeMeta(hItem))), 3); }
				
				if (hItem != Items_Teatime.FOOD_TOMATO && hItem != Items.BUCKET && !wool) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); }
				break;

			case 3:
				if (hStack.isEmpty()) {
					CMEvents.soundSnowBreak(worldIn, pos);
					CMEvents.mode1Through_takeItem(playerIn, hand, Items_Teatime.FOOD_TOMATO);
				
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(2)), 3);
					worldIn.setBlockState(pos.down(), downState.with(STAGE_1_4, Integer.valueOf(2)), 3); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
				break;
				
			case 4:
				if (hStack.isEmpty()) {
					CMEvents.soundSnowBreak(worldIn, pos);
					CMEvents.mode1Through_takeItem(playerIn, hand, Items.BUCKET);
				
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(2)), 3);
					worldIn.setBlockState(pos.down(), downState.with(STAGE_1_4, Integer.valueOf(2)), 3); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
				break;
			} // switch STAGE_1_4
			break;
		} // switch LOWER-UPPER
		
		return ActionResultType.SUCCESS;
	}
	
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
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		Block blockIn = worldIn.getBlockState(pos).getBlock();
	
		/** pos.up() = Replaceable block. **/
		if (pos.getY() < 255 && worldIn.getBlockState(pos.up()).isReplaceable(context)) {
			
			if (blockIn == Blocks.SNOW) {
				return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
						.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
						.with(STAGE_1_4, Integer.valueOf(1))
						.with(DOWN, Boolean.valueOf(true)); }
			
			else {
				return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
						.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
						.with(STAGE_1_4, Integer.valueOf(1))
						.with(DOWN, Boolean.valueOf(false)); }
		}

		else { return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		IFluidState fluidUp = worldIn.getFluidState(pos.up());

		worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER)
				.with(H_FACING, state.get(H_FACING))
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(DOWN, state.get(DOWN))
				.with(WATERLOGGED, Boolean.valueOf(fluidUp.isTagged(FluidTags.WATER))), 3);
	}
	
	/* Create Blockstate */
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, HALF, STAGE_1_4, WATERLOGGED);
	}
}
