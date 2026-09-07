package com.ayutaki.chinjufumod.items.color;

import java.util.List;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class Base_Hake extends Item {

	public Base_Hake(Item.Properties props) {
		super(props.durability(128));
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack stack_1, ItemStack stack_2) {
		return false;
	} // for 1.20.6
	
	public static void consumeAndBreak(int damage, ItemStack hStack, Player playerIn, UseOnContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		worldIn.playSound(playerIn, pos, SoundEvents_CM.PAINT.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
		
		boolean mode = playerIn.getAbilities().instabuild;
		hStack.hurtAndBreak(mode? 0 : damage, playerIn.getRandom(), playerIn instanceof ServerPlayer user ? user : null, () -> {
					playerIn.broadcastBreakEvent(LivingEntity.getSlotForHand(context.getHand()));
					hStack.shrink(1);
					ItemStack take = new ItemStack(Items_Wadeco.HAKE.get(), 1);
					if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }}); 
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_hake_color").withStyle(ChatFormatting.GRAY));
	} // for 1.20.6
}
