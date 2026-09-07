package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Taru_ShuboMoromi extends BaseTaru_Stage05 {

	public Taru_ShuboMoromi(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_0_5);

		/** Can harvest **/
		if (i == 5) {
			if (hItem == Items.BOWL) {
				/** Collect with an Item **/
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, this.takeItem());
				if (this == Hakkou_Blocks.MOROMI_TARU) { CMEvents.addEXP(1, worldIn, pos); }
				
				worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOU_TARU.getDefaultState()
						.with(Taru_Hakkou.STAGE_0_5, Integer.valueOf(0))); }
				
			if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Hakkou_Blocks.SHUBO_TARU) { return Items_Teatime.SHUBO; }
		else { return Items_Teatime.MOROMI; }
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		if (this == Hakkou_Blocks.SHUBO_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_shubo").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Hakkou_Blocks.MOROMI_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_moromi").applyTextStyle(TextFormatting.GRAY)); }
	}
}
