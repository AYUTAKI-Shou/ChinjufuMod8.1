package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MizuokeMilk_Item extends MilkBucketItem {

	public MizuokeMilk_Item(Item.Properties props) {
		super(props);
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {

		if (entityLiving instanceof ServerPlayer serverplayerentity) {
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this)); 
		}

		stack.consume(1, entityLiving);
		if (!worldIn.isClientSide) { entityLiving.removeAllEffects(); }
		
		Player playerIn = entityLiving instanceof Player ? (Player)entityLiving : null;

		ItemStack take = new ItemStack(Items_Teatime.MIZUOKE.get(), 1);
		if (stack.isEmpty()) { return take; }
		else if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }
		
		return stack;
	} //for 1.20.6 from MilkBucketItem

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_mizuoke_milk").withStyle(ChatFormatting.GRAY));
	}
}
