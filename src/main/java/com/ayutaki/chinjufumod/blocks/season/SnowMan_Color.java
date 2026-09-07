package com.ayutaki.chinjufumod.blocks.season;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class SnowMan_Color extends Abstract_SnowMan {
	/* Property */
	public static final IntegerProperty STAGE_1_16 = IntegerProperty.create("stage", 1, 16);
	/** White=1, Orange=2, Magenta=3, LightBlue=4, Yellow=5, Lime=6, Pink=7, Gray=8, **/
	/** LightGray=9, Cyan=10, Purple=11, Blue=12, Brown=13, Green=14, Red=15, Black=16 **/
	
	public SnowMan_Color(AbstractBlock.Properties props) {
		super(props);
		registerDefaultState(this.defaultBlockState().setValue(H_FACING, Direction.NORTH)
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(STAGE_1_16, Integer.valueOf(1))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, false));
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		int i = state.getValue(STAGE_1_16);
		DoubleBlockHalf half = state.getValue(HALF);
		BlockState upState = this.defaultBlockState()
				.setValue(H_FACING, state.getValue(H_FACING))
				.setValue(HALF, DoubleBlockHalf.UPPER)
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED))
				.setValue(STAGE_1_16, Integer.valueOf(i));

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

		/** White=1, Orange=2, Magenta=3, LightBlue=4, Yellow=5, Lime=6, Pink=7, Gray=8, **/
		/** LightGray=9, Cyan=10, Purple=11, Blue=12, Brown=13, Green=14, Red=15, Black=16 **/
		case UPPER:
			if (hStack.isEmpty()) {
				CMEvents.soundSnowBreak(worldIn, pos);
				CMEvents.mode1Through_takeItem(playerIn, hand, takeWool(state));
				
				worldIn.setBlock(pos, Seasonal_Blocks.SNOWMAN.defaultBlockState()
						.setValue(SnowMan.H_FACING, state.getValue(H_FACING))
						.setValue(SnowMan.HALF, DoubleBlockHalf.UPPER)
						.setValue(SnowMan.DOWN, state.getValue(DOWN))
						.setValue(SnowMan.WATERLOGGED, state.getValue(WATERLOGGED))
						.setValue(SnowMan.STAGE_1_4, Integer.valueOf(2)), 3);
				worldIn.setBlock(pos.below(), Seasonal_Blocks.SNOWMAN.defaultBlockState()
						.setValue(SnowMan.H_FACING, state.getValue(H_FACING))
						.setValue(SnowMan.HALF, DoubleBlockHalf.LOWER)
						.setValue(SnowMan.DOWN, state.getValue(DOWN))
						.setValue(SnowMan.WATERLOGGED, state.getValue(WATERLOGGED))
						.setValue(SnowMan.STAGE_1_4, Integer.valueOf(2)), 3);
			} //hStack.isEmpty()
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		} // switch LOWER-UPPER
		
		return ActionResultType.SUCCESS;
	}

	private Item takeWool(BlockState state) {
		int i = state.getValue(STAGE_1_16);
		if (i == 1) { return Items.WHITE_WOOL; }
		if (i == 2) { return Items.ORANGE_WOOL; }
		if (i == 3) { return Items.MAGENTA_WOOL; }
		if (i == 4) { return Items.LIGHT_BLUE_WOOL; }
		if (i == 5) { return Items.YELLOW_WOOL; }
		if (i == 6) { return Items.LIME_WOOL; }
		if (i == 7) { return Items.PINK_WOOL; }
		if (i == 8) { return Items.GRAY_WOOL; }
		if (i == 9) { return Items.LIGHT_GRAY_WOOL; }
		if (i == 10) { return Items.CYAN_WOOL; }
		if (i == 11) { return Items.PURPLE_WOOL; }
		if (i == 12) { return Items.BLUE_WOOL; }
		if (i == 13) { return Items.BROWN_WOOL; }
		if (i == 14) { return Items.GREEN_WOOL; }
		if (i == 15) { return Items.RED_WOOL; }
		else { return Items.BLACK_WOOL; }
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, HALF, STAGE_1_16, WATERLOGGED);
	}
}
