package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.IG_Chinjufu;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class Shouhou extends IG_Chinjufu {

	public Shouhou(Item.Properties props) {
		super(props);
	}
	
	/* RightClick Action */
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);

		if (!worldIn.isClientSide) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 1.2F, 1.0F);
			
			worldIn.addFreshEntity(new ExperienceOrb(worldIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(), 100));
			hStack.shrink(1);
			
			return InteractionResultHolder.success(hStack);
		}
		return InteractionResultHolder.success(hStack);
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_shouhou").withStyle(ChatFormatting.GRAY));
	}
}
