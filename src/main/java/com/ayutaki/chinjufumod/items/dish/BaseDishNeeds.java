package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BaseDishNeeds extends Not_Fuel {

	public BaseDishNeeds(Block block, Item.Properties props) {
		super(block, props);
	}
	
	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand hand) {
			/** It works only when you're hungry. **/
			if (playerIn.getFoodData().needsFood()) {
				playerIn.startUsingItem(hand);
				return InteractionResult.CONSUME; }
			
			return InteractionResult.FAIL;
	}
	 
	/* Branch the process. */
	@Override
	 public InteractionResult useOn(UseOnContext context) {
		Player playerIn = context.getPlayer();

		if (context.getClickedFace() == Direction.UP && (playerIn.isCrouching() || playerIn.isPassenger())) {
			return this.place(new BlockPlaceContext(context)); }

		else {
			return this.use(context.getLevel(), context.getPlayer(), context.getHand()); } //for 1.21.4
	 }

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.block_simpledish").withStyle(ChatFormatting.GRAY));
	}
}
