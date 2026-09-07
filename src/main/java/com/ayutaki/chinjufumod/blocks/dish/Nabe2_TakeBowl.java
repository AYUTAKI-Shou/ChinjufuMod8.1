package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Nabe2_TakeBowl extends BaseNabe_2Cook {

	public Nabe2_TakeBowl(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_2);

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (this == Dish_Blocks.NABEANKO_nama) {
				if (hItem == Items.BOWL) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.ANKO);
					worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
							.with(Nabe_kara.H_FACING, state.get(H_FACING))
							.with(Nabe_kara.STAGE_1_4, Integer.valueOf(4)), 3); }
				
				if (hItem == Items_Teatime.MOCHI) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEZENZAI_M.getDefaultState()
							.with(BaseNabe.H_FACING, state.get(H_FACING))
							.with(BaseNabe.COOK, true)
							.with(BaseNabe.STAGE_1_4, Integer.valueOf(1)), 3); }
				
				int gHC = hStack.getCount();
				if (hItem == Items_Seasonal.KURI_ROAST && gHC >= 8) {
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEZENZAI_K.getDefaultState()
							.with(BaseNabe.H_FACING, state.get(H_FACING))
							.with(BaseNabe.COOK, true)
							.with(BaseNabe.STAGE_1_4, Integer.valueOf(1)), 3); }
			}
			
			else { //!= Dish_Blocks.NABEANKO_nama
				if (hItem == Items.BOWL) {
					/** Collect with an Item **/
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takeItem());
					worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
							.with(Nabe_kara.H_FACING, state.get(H_FACING))
							.with(Nabe_kara.STAGE_1_4, Integer.valueOf(4)), 3); }
				
				if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.NABENIMAME_nama) { return Items_Teatime.NIMAME; }
		else { return Items_Seasonal.KURI_BOIL; }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterOUT(state)) {
			int i = state.get(STAGE_1_2);
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(2))); }
			
			else { } }

		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
					.with(Nabe_kara.H_FACING, state.get(H_FACING))
					.with(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.with(Nabe_kara.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, blockTip, tipFlag);
		blockTip.add(new TranslationTextComponent("tips.take_bowl").applyTextStyle(TextFormatting.GRAY));
	}
}
