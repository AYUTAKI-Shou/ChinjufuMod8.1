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

public class Tana_ChaNoriPepper extends BaseTana_Stage05 {

	public Tana_ChaNoriPepper(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_0_5);

		/** Can harvest **/
		if (i == 5) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), this.takeInt());
				if (this == Hakkou_Blocks.NORI_TARU) { CMEvents.addEXP(1, worldIn, pos); }
				
				worldIn.setBlock(pos, Hakkou_Blocks.HAKKOU_TARU.defaultBlockState()
						.setValue(Taru_Hakkou.STAGE_0_5, Integer.valueOf(3)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Hakkou_Blocks.KOUCHA_TARU) { return Items_Teatime.CHABA_RED; }
		if (this == Hakkou_Blocks.NORI_TARU) { return Items_Teatime.NORI_I; }
		else { return Items_Teatime.PEPPER_DRY; }
	}
	
	private int takeInt() {
		if (this == Hakkou_Blocks.NORI_TARU) { return 4; }
		else { return 8; }
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		if (this == Hakkou_Blocks.KOUCHA_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_koucha").withStyle(TextFormatting.GRAY)); }
		if (this == Hakkou_Blocks.NORI_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_nori").withStyle(TextFormatting.GRAY)); }
		if (this == Hakkou_Blocks.PEPPER_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_pepper").withStyle(TextFormatting.GRAY)); }
	}
}
