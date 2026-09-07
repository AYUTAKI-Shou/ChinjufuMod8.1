package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.KK_MosquitoEntity;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class KK_Mosquito extends Abstract_BowKansaiki {

	public KK_Mosquito(Item.Properties props) {
		super(props);
	}
	
	/* Called when the player stops using an Item (stops holding the right mouse button). */
	@Override
	public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof Player) {
			Player playerIn = (Player)entityLiving;
			boolean mode = playerIn.getAbilities().instabuild;
			ItemStack fuel = playerIn.getProjectile(stack);
			
			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerIn, i, !fuel.isEmpty() || mode);

			if (i < 0) return;

			if (!fuel.isEmpty() || mode) {
				if (fuel.isEmpty()) { fuel = new ItemStack(Items_Weapon.KK_FUEL.get()); }
				
				float charge = getPowerForTime(i);
				if (!((double)charge < 0.1D)) {

					if (!worldIn.isClientSide) {
						KK_MosquitoEntity kansaiki = new KK_MosquitoEntity(playerIn, worldIn, stack);
						int POWER = 8; // Add the speed and distance of the Entity.
						
						boolean carrier = ShipTypes_CM.typeCarrier(playerIn);
						this.setDamageMove(stack, kansaiki, playerIn, POWER, carrier);
						if (carrier) { kansaiki.setCarrier(true); }
						
						worldIn.addFreshEntity(kansaiki);
						
						if (mode) { playerIn.getInventory().removeItem(stack); } // mode
						
						if (!mode) {
							fuel.shrink(1);
							stack.shrink(1); } // !mode
						
						playerIn.awardStat(Stats.ITEM_USED.get(this));
					} // !worldIn.isClientSide
				} // charge
			} // !fuel.isEmpty() 
			
		} // PlayerEntity
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_kk").withStyle(ChatFormatting.GRAY)); 
		itemTip.add(new TranslatableComponent("tips.item_kk_8f").withStyle(ChatFormatting.BLUE)); }
}
