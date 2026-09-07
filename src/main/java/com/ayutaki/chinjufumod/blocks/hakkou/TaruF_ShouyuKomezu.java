package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TaruF_ShouyuKomezu extends BaseTaru_Stage08 {

	/** 0-4=unfinished, 5=1st time, 6=2nd time, 7=3rd time, 8=last **/
	public TaruF_ShouyuKomezu(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_8);

		/** Can harvest **/
		if (i >= 5) {
			if (hItem == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, this.takeItem());
	
				if (i == 8) {
					worldIn.setBlock(pos, Hakkou_Blocks.HAKKOU_TARU.defaultBlockState()
							.setValue(Taru_Hakkou.STAGE_0_5, Integer.valueOf(0)), 3); }
				else { //i != 8
					worldIn.setBlock(pos, state.setValue(STAGE_0_8, Integer.valueOf(i + 1)), 3); }
			}
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i <= 4
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Hakkou_Blocks.SHOUYU_TARU) { return Items_Teatime.SHOUYU_bot_14; }
		else { return Items_Teatime.KOMEZU_bot_12; }
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		if (this == Hakkou_Blocks.SHOUYU_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_shouyu").withStyle(TextFormatting.GRAY)); }
		if (this == Hakkou_Blocks.KOMEZU_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_komezu").withStyle(TextFormatting.GRAY)); }
	}
}
