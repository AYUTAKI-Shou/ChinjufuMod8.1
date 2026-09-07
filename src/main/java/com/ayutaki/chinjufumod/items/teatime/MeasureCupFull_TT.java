package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class MeasureCupFull_TT extends IG_Teatime {

	public MeasureCupFull_TT(Item.Properties props) {
		super(props);
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
	
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.BOTTLE_EMPTY, SoundCategory.PLAYERS, 1.0F, 1.0F);
		CMEvents.take1Item(playerIn, hand, Items_Teatime.KEIRYO_CUP);
		CMEvents.consumeN_Hand(1, playerIn, hand);
		
		return ActionResult.success(hStack);
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		if (this == Items_Teatime.KEIRYO_CUP_full) {
			itemTip.add(new TranslationTextComponent("tips.item_measurecup_full").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.KANSUI) {
			itemTip.add(new TranslationTextComponent("tips.item_kansui").withStyle(TextFormatting.GRAY)); }
	}
}
