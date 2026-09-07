package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
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
import net.minecraftforge.common.ToolType;

public class ShouyuSara_1 extends Abstract_FoodStage9Water {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(6.0D, 0.0D, 9.0D, 10.0D, 0.5D, 13.0D);
	private static final VoxelShape AABB_WEST = Block.box(3.0D, 0.0D, 6.0D, 7.0D, 0.5D, 10.0D);
	private static final VoxelShape AABB_NORTH = Block.box(6.0D, 0.0D, 3.0D, 10.0D, 0.5D, 7.0D);
	private static final VoxelShape AABB_EAST = Block.box(9.0D, 0.0D, 6.0D, 13.0D, 0.5D, 10.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(6.0D, -8.0D, 9.0D, 10.0D, 0.1D, 13.0D);
	private static final VoxelShape DOWN_WEST = Block.box(3.0D, -8.0D, 6.0D, 7.0D, 0.1D, 10.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(6.0D, -8.0D, 3.0D, 10.0D, 0.1D, 7.0D);
	private static final VoxelShape DOWN_EAST = Block.box(9.0D, -8.0D, 6.0D, 13.0D, 0.1D, 10.0D);

	public ShouyuSara_1(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_9);
		boolean sushi = (hItem == Items_Teatime.SUSHI_S || hItem == Items_Teatime.SUSHI_F || hItem == Items_Teatime.SUSHI_B || hItem == Items_Teatime.SUSHI_T);
		boolean shouyu = (hItem == Items_Teatime.SHOUYU_bot_14 || hItem == Items_NoTab.SHOUYU_bot_24 || hItem == Items_NoTab.SHOUYU_bot_34 || hItem == Items_NoTab.SHOUYU_bot_44);
		
		if (state.getValue(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { //!WATERLOGGED
			/** Empty **/
			if (i == 9) {
				if (shouyu) {
					if (hItem == Items_Teatime.SHOUYU_bot_14) { CMEvents.Soysauce_to2(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_24) { CMEvents.Soysauce_to3(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_34) { CMEvents.Soysauce_to4(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_44) { CMEvents.Soysauce_toBottle(worldIn, pos, playerIn, hand); }
					
					worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(1)), 3); }
				
				if (!shouyu) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			/** Not empty **/
			else { //i != 9
				if (sushi) {
					if (hItem == Items_Teatime.SUSHI_S) {
						/** Collect with an Item **/
						CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI_S); }
					
					if (hItem == Items_Teatime.SUSHI_F) {
						CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI_F); }
					
					if (hItem == Items_Teatime.SUSHI_B) {
						CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI_B); }
					
					if (hItem == Items_Teatime.SUSHI_T) {
						CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI_T); }
		
					worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(i + 1)), 3); }
				
				if (!sushi) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_9);
		
		if (waterIn(state, worldIn, pos) && i != 9) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(9)), 3); }
	
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_food_shouyusara_1").withStyle(TextFormatting.GRAY));
	}
}
