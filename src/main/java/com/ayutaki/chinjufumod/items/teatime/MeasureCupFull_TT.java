package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MeasureCupFull_TT extends IG_Teatime {

	public MeasureCupFull_TT(Item.Properties props) {
		super(props);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.BOTTLE_EMPTY, SoundSource.PLAYERS, 1.0F, 1.0F);
		CMEvents.take1Item(playerIn, hand, Items_Teatime.KEIRYO_CUP.get());
		CMEvents.consumeN_Hand(1, playerIn, hand);
		
		return InteractionResultHolder.success(hStack);
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		if (this == Items_Teatime.KEIRYO_CUP_full.get()) {
			itemTip.add(new TranslatableComponent("tips.item_measurecup_full").withStyle(ChatFormatting.GRAY)); }
		if (this == Items_Teatime.KANSUI.get()) {
			itemTip.add(new TranslatableComponent("tips.item_kansui").withStyle(ChatFormatting.GRAY)); }
	}
}
