package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Bottle_Sake extends Base_Bottle {

	public Bottle_Sake(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hItem == Items_Teatime.DRINKGLASS) {
				/** Collect with an Item **/
				CMEvents.consumeN_Hand(1, playerIn, hand);
				worldIn.playSound(null, pos, SoundEvents_CM.SAKE, SoundCategory.PLAYERS, 1.0F, 1.0F);
				CMEvents.take1Item(playerIn, hand, this.takeItem());

				worldIn.setBlock(pos, state.setValue(Base_Bottle.STAGE_1_5, Integer.valueOf(i + 1)), 3); }
			
			if (hItem != Items_Teatime.DRINKGLASS) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Hakkou_Blocks.NAMASAKEBOT) { return Items_Teatime.NAMASAKEGLASS; }
		if (this == Hakkou_Blocks.SAKEBOT) { return Items_Teatime.SAKEGLASS; }
		if (this == Hakkou_Blocks.JUKUSAKEBOT) { return Items_Teatime.JUKUSAKEGLASS; }
		if (this == Hakkou_Blocks.CIDERBOT) { return Items_Teatime.CIDERGLASS; }
		if (this == Hakkou_Blocks.JUKUCIDERBOT) { return Items_Teatime.JUKUCIDERGLASS; }
		if (this == Hakkou_Blocks.WINEBOT) { return Items_Teatime.WINEGLASS; }
		if (this == Hakkou_Blocks.JUKUWINEBOT) { return Items_Teatime.JUKUWINEGLASS; }
		if (this == Hakkou_Blocks.MEADBOT) { return Items_Teatime.MEADGLASS; }
		else { return Items_Teatime.JUKUMEADGLASS; }
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_bot_sake").withStyle(TextFormatting.GRAY));
	}
}
