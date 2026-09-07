package com.ayutaki.chinjufumod.items.sakuteki;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class SouganKyou extends Item {

	public SouganKyou(Item.Properties props) {
		super(props);
	}
	
	/* Power to be charged. */
	public static float getPowerForTime(int charge) {
		float f = (float)charge / 20.0F;
		f = (f + 2.0F) / 3.0F;
		if (f > 1.0F) { f = 1.0F; }
		return f;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	/* Action when using. */
	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPYGLASS;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hSstack = playerIn.getItemInHand(hand);
		worldIn.playSeededSound(playerIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 0.8F, 0.8F, 0); //for 1.20.6
		playerIn.startUsingItem(hand);
		return InteractionResultHolder.success(hSstack);
	}
}
