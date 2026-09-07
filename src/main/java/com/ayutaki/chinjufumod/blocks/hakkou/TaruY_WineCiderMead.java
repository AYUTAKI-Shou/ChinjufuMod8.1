package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TaruY_WineCiderMead extends BaseTaru_Yoh {
	/* 1,2,3=Early, 4,5=Complete, 6=Aged The progress is too fast, so there are 6 stages. */
	public TaruY_WineCiderMead(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_6);

		/** Can harvest **/
		if (i >= 4) {
			if (hItem == Items_Teatime.SAKEBOTTLE.get()) {
				/** Collect with an Item **/
				CMEvents.consumeN_Hand(1, playerIn, hand);

				if (i == 4 || i == 5) {
					CMEvents.takeSAKEBottle_Fill(worldIn, pos, playerIn, hand, this.takeBottle()); }
		
				if (i == 6) {
					CMEvents.takeSAKEBottle_Fill(worldIn, pos, playerIn, hand, this.takeAged()); }
				
				CMEvents.addEXP(1, worldIn, pos);
				worldIn.setBlock(pos, Hakkou_Blocks.COCOA_TARU.get().defaultBlockState()
						.setValue(BaseTaru_Yoh.AXIS, state.getValue(AXIS))
						.setValue(BaseTaru_Yoh.STAGE_1_6, Integer.valueOf(6)), 3);
			}
			
			if (hItem != Items_Teatime.SAKEBOTTLE.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i <= 3
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeBottle() {
		if (this == Hakkou_Blocks.RINGOSHU_TARU.get()) { return Items_Teatime.CIDERBOT.get(); }
		if (this == Hakkou_Blocks.BUDOUSHU_TARU.get()) { return Items_Teatime.WINEBOT.get(); }
		else { return Items_Teatime.MEADBOT.get(); }
	}
	
	private Item takeAged() {
		if (this == Hakkou_Blocks.RINGOSHU_TARU.get()) { return Items_Teatime.JUKUCIDERBOT.get(); }
		if (this == Hakkou_Blocks.BUDOUSHU_TARU.get()) { return Items_Teatime.JUKUWINEBOT.get(); }
		else { return Items_Teatime.JUKUMEADBOT.get(); }
	}
	
	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		int i = state.getValue(STAGE_1_6);
		if (i == 6) { }
		
		else { //i != 6
			if (rand.nextInt(6) == 0) {
				worldIn.setBlock(pos, state.setValue(STAGE_1_6, Integer.valueOf(i + 1)), 3); }
		}
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		if (this == Hakkou_Blocks.RINGOSHU_TARU.get()) { blockTip.add(Component.translatable("tips.block_taru_ringoshu").withStyle(ChatFormatting.GRAY)); }
		if (this == Hakkou_Blocks.BUDOUSHU_TARU.get()) { blockTip.add(Component.translatable("tips.block_taru_budoushu").withStyle(ChatFormatting.GRAY)); }
		if (this == Hakkou_Blocks.HACHIMITSU_TARU.get()) { blockTip.add(Component.translatable("tips.block_taru_hachimitsushu").withStyle(ChatFormatting.GRAY)); }
	}
}
