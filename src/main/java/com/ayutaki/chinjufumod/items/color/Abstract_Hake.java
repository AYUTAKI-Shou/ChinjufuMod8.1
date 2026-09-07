package com.ayutaki.chinjufumod.items.color;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.base.IG_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public abstract class Abstract_Hake extends IG_Wadeco {

	public Abstract_Hake(Item.Properties props) {
		super(props.durability(128));
	}

	/* Abstract */
	public abstract InteractionResult useOn(UseOnContext context);
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	
	public static void consumeAndBreak(int damage, ItemStack hStack, Player playerIn, UseOnContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		worldIn.playSound(playerIn, pos, SoundEvents_CM.PAINT.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
		
		boolean mode = playerIn.getAbilities().instabuild;
		hStack.hurtAndBreak(mode? 0 : damage, playerIn, user -> {
			ItemStack take = new ItemStack(Items_Wadeco.HAKE.get(), 1);
			if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }
			user.broadcastBreakEvent(context.getHand()); } ); //BreakAnimation 無しで筆を返すことも可能
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_hake_color").withStyle(ChatFormatting.GRAY));
	}
}
