package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.Teatime_noFuel;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;

/* BucketItem を参照。extends は BlockNamedItem とする */
public class ZundouKara_TT extends Teatime_noFuel {

	public ZundouKara_TT(Block block, Item.Properties props) {
		super(block, props);
	}

	/* 牛乳を汲む ShearsItem, CowEntity */
	@Override
	public net.minecraft.util.ActionResultType interactLivingEntity(ItemStack stack, net.minecraft.entity.player.PlayerEntity playerIn, LivingEntity entity, net.minecraft.util.Hand hand) {
		boolean mode = playerIn.abilities.instabuild;

		if (entity.level.isClientSide) return net.minecraft.util.ActionResultType.PASS;

		if (stack.getItem() == Items_Teatime.ZUNDOU) {

			if (entity instanceof CowEntity && !mode && !entity.isBaby()) {

				entity.playSound(SoundEvents.COW_MILK, 2.0F, 1.0F);
				CMEvents.take1Item(playerIn, hand, Items_Teatime.ZUNDOU_MILK);
				/* 消費を最後に回す */
				stack.shrink(1);
				return net.minecraft.util.ActionResultType.SUCCESS;
			}

			return net.minecraft.util.ActionResultType.PASS;
		}
		return net.minecraft.util.ActionResultType.PASS;
	}
}
