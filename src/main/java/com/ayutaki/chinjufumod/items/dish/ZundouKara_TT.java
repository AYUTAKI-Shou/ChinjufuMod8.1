package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.Teatime_noFuel;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class ZundouKara_TT extends Teatime_noFuel {

	public ZundouKara_TT(Block block, Item.Properties props) {
		super(block, props);
	}

	private void ZUNDOU_toMilk(ItemStack stack, Player playerIn, LivingEntity entity, InteractionHand hand) {
		entity.playSound(SoundEvents.COW_MILK, 2.0F, 1.0F);
		CMEvents.take1Item(playerIn, hand, Items_Teatime.ZUNDOU_MILK.get());
		stack.shrink(1); }
	
	/* Get the MIZUOKE_Milk. ShearsItem, CowEntity */
	@Override
	public net.minecraft.world.InteractionResult interactLivingEntity(ItemStack stack, net.minecraft.world.entity.player.Player playerIn, LivingEntity entity, net.minecraft.world.InteractionHand hand) {
		boolean mode = playerIn.getAbilities().instabuild;

		if (entity.level.isClientSide) return InteractionResult.PASS;

		if (stack.getItem() == Items_Teatime.ZUNDOU.get()) {

			if (entity instanceof Cow && !mode && !entity.isBaby()) {

				this.ZUNDOU_toMilk(stack, playerIn, entity, hand);
				return InteractionResult.SUCCESS; }

			if (entity instanceof Goat && !mode && !entity.isBaby()) {

				this.ZUNDOU_toMilk(stack, playerIn, entity, hand);
				return InteractionResult.SUCCESS; }
			
			return InteractionResult.PASS;
		}
		return InteractionResult.PASS;
	}
}
