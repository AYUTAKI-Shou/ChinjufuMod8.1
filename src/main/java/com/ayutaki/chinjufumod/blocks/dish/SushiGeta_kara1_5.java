package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SushiGeta_kara1_5 extends BaseFood_Stage5WA {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(1.0D, 0.0D, 7.0D, 14.0D, 1.5D, 12.5D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(3.5D, 0.0D, 1.0D, 9.0D, 1.5D, 14.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(2.0D, 0.0D, 3.5D, 15.0D, 1.5D, 9.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(7.0D, 0.0D, 2.0D, 12.5D, 1.5D, 15.0D);

	private static final VoxelShape DOWN_SOUTH = Block.makeCuboidShape(1.0D, -8.0D, 7.0D, 14.0D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_WEST = Block.makeCuboidShape(3.5D, -8.0D, 1.0D, 9.0D, 0.1D, 14.0D);
	private static final VoxelShape DOWN_NORTH = Block.makeCuboidShape(2.0D, -8.0D, 3.5D, 15.0D, 0.1D, 9.0D);
	private static final VoxelShape DOWN_EAST = Block.makeCuboidShape(7.0D, -8.0D, 2.0D, 12.5D, 0.1D, 15.0D);

	public SushiGeta_kara1_5(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_5);
		boolean sushi = (hItem == Items_Teatime.SUSHI_S || hItem == Items_Teatime.SUSHI_F || hItem == Items_Teatime.SUSHI_B || hItem == Items_Teatime.SUSHI_T);
		boolean shouyu = (hItem == Items_Teatime.SHOUYU_bot_14 || hItem == Items_NoTab.SHOUYU_bot_24 || hItem == Items_NoTab.SHOUYU_bot_34 || hItem == Items_NoTab.SHOUYU_bot_44);

		if (state.get(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }

		else { //!WATERLOGGED
			/** Soy sauce is empty **/
			if (i == 1) {
				if (shouyu) {
					if (hItem == Items_Teatime.SHOUYU_bot_14) { CMEvents.Soysauce_to2(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_24) { CMEvents.Soysauce_to3(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_34) { CMEvents.Soysauce_to4(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_44) { CMEvents.Soysauce_toBottle(worldIn, pos, playerIn, hand); }
					
					worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(2))); }
				
				if (!shouyu) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			/** Soy sauce is not empty **/
			else { //i != 1
				if (hItem == Items_Teatime.SUSHI_S) {
					/** Collect with an Item **/
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					
					worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_salmon.getDefaultState()
							.with(SushiGeta_full.H_FACING, state.get(H_FACING))
							.with(SushiGeta_full.DOWN, state.get(DOWN))
							.with(SushiGeta_full.STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				
				if (hItem == Items_Teatime.SUSHI_F) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					
					worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_fish.getDefaultState()
							.with(SushiGeta_full.H_FACING, state.get(H_FACING))
							.with(SushiGeta_full.DOWN, state.get(DOWN))
							.with(SushiGeta_full.STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				
				if (hItem == Items_Teatime.SUSHI_B) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					
					worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_beef.getDefaultState()
							.with(SushiGeta_full.H_FACING, state.get(H_FACING))
							.with(SushiGeta_full.DOWN, state.get(DOWN))
							.with(SushiGeta_full.STAGE_1_4, Integer.valueOf(i - 1)), 3); }
				
				if (hItem == Items_Teatime.SUSHI_T) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					
					worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_tamago.getDefaultState()
							.with(SushiGeta_full.H_FACING, state.get(H_FACING))
							.with(SushiGeta_full.DOWN, state.get(DOWN))
							.with(SushiGeta_full.STAGE_1_4, Integer.valueOf(i - 1)), 3); }

				if (!sushi) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_5);
		
		if (waterIn(state, worldIn, pos) && i != 1) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(1))); }

		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();

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
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_food_sushigeta_kara").applyTextStyle(TextFormatting.GRAY));
	}
}
