package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class TaruY_WineCiderMead extends BaseTaru_Yoh {

	/* 1,2,3=Early, 4,5=Complete, 6=Aged The progress is too fast, so there are 6 stages. */
	public TaruY_WineCiderMead(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_6);

		/** Can harvest **/
		if (i >= 4) {
			if (hItem == Items_Teatime.SAKEBOTTLE) {
				/** Collect with an Item **/
				CMEvents.consumeN_Hand(1, playerIn, hand);

				if (i == 4 || i == 5) {
					CMEvents.takeSAKEBottle_Fill(worldIn, pos, playerIn, hand, this.takeBottle()); }
		
				if (i == 6) {
					CMEvents.takeSAKEBottle_Fill(worldIn, pos, playerIn, hand, this.takeAged()); }
				
				CMEvents.addEXP(1, worldIn, pos);
				worldIn.setBlock(pos, Hakkou_Blocks.COCOA_TARU.defaultBlockState()
						.setValue(BaseTaru_Yoh.AXIS, state.getValue(AXIS))
						.setValue(BaseTaru_Yoh.STAGE_1_6, Integer.valueOf(6)), 3);
			}
			
			if (hItem != Items_Teatime.SAKEBOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i <= 3
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeBottle() {
		if (this == Hakkou_Blocks.RINGOSHU_TARU) { return Items_Teatime.CIDERBOT; }
		if (this == Hakkou_Blocks.BUDOUSHU_TARU) { return Items_Teatime.WINEBOT; }
		else { return Items_Teatime.MEADBOT; }
	}
	
	private Item takeAged() {
		if (this == Hakkou_Blocks.RINGOSHU_TARU) { return Items_Teatime.JUKUCIDERBOT; }
		if (this == Hakkou_Blocks.BUDOUSHU_TARU) { return Items_Teatime.JUKUWINEBOT; }
		else { return Items_Teatime.JUKUMEADBOT; }
	}
	
	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		int i = state.getValue(STAGE_1_6);
		if (i == 6) { }
		
		else { //i != 6
			if (rand.nextInt(6) == 0) {
				worldIn.setBlock(pos, state.setValue(STAGE_1_6, Integer.valueOf(i + 1)), 3); }
		}
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		if (this == Hakkou_Blocks.RINGOSHU_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_ringoshu").withStyle(TextFormatting.GRAY)); }
		if (this == Hakkou_Blocks.BUDOUSHU_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_budoushu").withStyle(TextFormatting.GRAY)); }
		if (this == Hakkou_Blocks.HACHIMITSU_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_hachimitsushu").withStyle(TextFormatting.GRAY)); }
	}
}
