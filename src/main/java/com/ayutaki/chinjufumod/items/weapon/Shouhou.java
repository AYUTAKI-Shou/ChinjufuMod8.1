package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Shouhou extends Item {

	public Shouhou(Item.Properties props) {
		super(props);
	}
	
	/* RightClick Action */
	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);

		if (!worldIn.isClientSide) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 1.2F, 1.0F);
			
			worldIn.addFreshEntity(new ExperienceOrb(worldIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(), 100));
			hStack.shrink(1);
			
			return InteractionResult.SUCCESS.heldItemTransformedTo(hStack);
		}
		return InteractionResult.SUCCESS.heldItemTransformedTo(hStack);
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_shouhou").withStyle(ChatFormatting.GRAY));
	}
}
