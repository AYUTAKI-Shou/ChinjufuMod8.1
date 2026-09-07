package com.ayutaki.chinjufumod.items.weapon;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Kijyuu;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class Base_Kijyu extends BowItem {

	public int SHOOTCOUNT = 0;
	
	public Base_Kijyu(Item.Properties props) {
		super(props.repairable(Items.IRON_INGOT)); ///for 1.21.4
	}
	
	/* Power to be charged. */
	public static float getPowerForTime(int charge) {
		float f = (float)charge / 20.0F;
		f = (f + 2.0F) / 3.0F;
		if (f > 1.0F) { f = 1.0F; }
		return f;
	}

	/* Time to continue the action. 3 burst × 3 */
	@Override
	public int getUseDuration(ItemStack hStack, LivingEntity entityIn) {
		return 64;
	}

	/* Action when using. for 1.21.4 */
	@Override
	public ItemUseAnimation getUseAnimation(ItemStack hStack) {
		return ItemUseAnimation.BOW;
	}

	/* Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see {@link #onItemUse}. */
	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean flag = !playerIn.getProjectile(hStack).isEmpty();
		
		this.SHOOTCOUNT = 0; // Count reset.
		InteractionResult ret = net.neoforged.neoforge.event.EventHooks.onArrowNock(hStack, worldIn, playerIn, hand, flag); //neo
		if (ret != null) return ret;

		if (!playerIn.getAbilities().instabuild && !flag) {
			worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.EMPTY_AMMO.get(), SoundSource.PLAYERS, 0.8F, 0.6F);
			playerIn.displayClientMessage(Component.translatable("text.chinjufumod.rightclick.empty_ammo"), true);
			return InteractionResult.FAIL; }
		
		else {
			if (!playerIn.getCooldowns().isOnCooldown(hStack)) {
				worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.SET_GUN.get(), SoundSource.PLAYERS, 0.8F, 0.8F);
				playerIn.startUsingItem(hand);
				return InteractionResult.CONSUME; }
			
			else { return InteractionResult.FAIL; }
		}
	}

	public AbstractAmmo_Kijyuu customAmmo(AbstractAmmo_Kijyuu arrow) {
		return arrow;
	}
}
