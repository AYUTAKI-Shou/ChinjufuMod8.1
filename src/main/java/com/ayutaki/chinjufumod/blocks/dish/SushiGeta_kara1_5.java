package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SushiGeta_kara1_5 extends BaseFood_Stage5Water {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(1.0D, 0.0D, 7.0D, 14.0D, 1.5D, 12.5D);
	private static final VoxelShape AABB_WEST = Block.box(3.5D, 0.0D, 1.0D, 9.0D, 1.5D, 14.0D);
	private static final VoxelShape AABB_NORTH = Block.box(2.0D, 0.0D, 3.5D, 15.0D, 1.5D, 9.0D);
	private static final VoxelShape AABB_EAST = Block.box(7.0D, 0.0D, 2.0D, 12.5D, 1.5D, 15.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(1.0D, -8.0D, 7.0D, 14.0D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_WEST = Block.box(3.5D, -8.0D, 1.0D, 9.0D, 0.1D, 14.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(2.0D, -8.0D, 3.5D, 15.0D, 0.1D, 9.0D);
	private static final VoxelShape DOWN_EAST = Block.box(7.0D, -8.0D, 2.0D, 12.5D, 0.1D, 15.0D);
	
	public SushiGeta_kara1_5(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_5);
		boolean sushi = (hItem == Items_Teatime.SUSHI_S.get() || hItem == Items_Teatime.SUSHI_F.get() || hItem == Items_Teatime.SUSHI_B.get() || hItem == Items_Teatime.SUSHI_T.get());
		boolean shouyu = (hItem == Items_Teatime.SHOUYU_bot_14.get() || hItem == Items_NoTab.SHOUYU_bot_24.get() || hItem == Items_NoTab.SHOUYU_bot_34.get() || hItem == Items_NoTab.SHOUYU_bot_44.get());

		if (state.getValue(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { //!WATERLOGGED
			/** Soy sauce is empty **/
			if (i == 1) {
				if (shouyu) {
					if (hItem == Items_Teatime.SHOUYU_bot_14.get()) { CMEvents.Soysauce_to2(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_24.get()) { CMEvents.Soysauce_to3(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_34.get()) { CMEvents.Soysauce_to4(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_44.get()) { CMEvents.Soysauce_toBottle(worldIn, pos, playerIn, hand); }
					
					worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(2)), 3); }
				
				if (!shouyu) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			/** Soy sauce is not empty **/
			if (i != 1) {
				if (hItem == Items_Teatime.SUSHI_S.get()) {
					/** Collect with an Item **/
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					
					worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_salmon.get().defaultBlockState()
							.setValue(SushiGeta_full.H_FACING, state.getValue(H_FACING))
							.setValue(SushiGeta_full.DOWN, state.getValue(DOWN))
							.setValue(SushiGeta_full.STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				
				if (hItem == Items_Teatime.SUSHI_F.get()) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					
					worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_fish.get().defaultBlockState()
							.setValue(SushiGeta_full.H_FACING, state.getValue(H_FACING))
							.setValue(SushiGeta_full.DOWN, state.getValue(DOWN))
							.setValue(SushiGeta_full.STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				
				if (hItem == Items_Teatime.SUSHI_B.get()) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					
					worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_beef.get().defaultBlockState()
							.setValue(SushiGeta_full.H_FACING, state.getValue(H_FACING))
							.setValue(SushiGeta_full.DOWN, state.getValue(DOWN))
							.setValue(SushiGeta_full.STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				
				if (hItem == Items_Teatime.SUSHI_T.get()) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					
					worldIn.setBlock(pos, Dish_Blocks.SUSHIGETA_tamago.get().defaultBlockState()
							.setValue(SushiGeta_full.H_FACING, state.getValue(H_FACING))
							.setValue(SushiGeta_full.DOWN, state.getValue(DOWN))
							.setValue(SushiGeta_full.STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				
				if (!sushi) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_5);
		
		if (i == 1) { }
		else { //i != 1
			if (waterIn(state, worldIn, pos)) {
				worldIn.scheduleTick(pos, this, 60);
				CMEvents.soundBubble(worldIn, pos);
				worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(1)), 3); }
		
			else { }
		}
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notDown? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return notDown? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return notDown? AABB_WEST : DOWN_WEST;
		case EAST:
			return notDown? AABB_EAST : DOWN_EAST;
		}
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(new TranslatableComponent("tips.block_food_sushigeta_kara").withStyle(ChatFormatting.GRAY));
	}
}
