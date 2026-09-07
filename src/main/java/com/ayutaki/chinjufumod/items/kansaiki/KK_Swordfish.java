package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;

import com.ayutaki.chinjufumod.entity.KK_SwordfishEntity;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class KK_Swordfish extends Abstract_BowKansaiki implements ProjectileItem {
	
	public KK_Swordfish(Item.Properties props) {
		super(props);
	}

	/* Called when the player stops using an Item (stops holding the right mouse button). */
	@Override
	public boolean releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) { ///for 1.21.4
		if (entityLiving instanceof Player) {
			Player playerIn = (Player)entityLiving;
			boolean mode = playerIn.getAbilities().instabuild;
			ItemStack fuel = playerIn.getProjectile(stack);
			
			int i = this.getUseDuration(stack, entityLiving) - timeLeft;
			i = net.neoforged.neoforge.event.EventHooks.onArrowLoose(stack, worldIn, playerIn, i, !fuel.isEmpty() || mode); //neo
			if (i < 0) return false;

			if (!fuel.isEmpty() || mode) {
				if (fuel.isEmpty()) { fuel = new ItemStack(Items_Weapon.KK_FUEL.get()); }
				
				float charge = getPowerForTime(i);
				if (!((double)charge < 0.1D)) {

					if (worldIn instanceof ServerLevel server) {
						KK_SwordfishEntity kansaiki = new KK_SwordfishEntity(playerIn, server, stack);
						int POWER = 5; // Add the speed and distance of the Entity.
						
						boolean carrier = ShipTypes_CM.typeCarrier(playerIn);
						this.setDamageMove(stack, worldIn, kansaiki, playerIn, POWER, carrier);
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
		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_kk").withStyle(ChatFormatting.GRAY)); 
		itemTip.add(Component.translatable("tips.item_kk_5f").withStyle(ChatFormatting.BLUE)); }

	@Override
	public Projectile asProjectile(Level worldIn, Position pos, ItemStack stack, Direction direct) {
		return new KK_SwordfishEntity(worldIn, pos.x(), pos.y(), pos.z(), stack);
	}
}
