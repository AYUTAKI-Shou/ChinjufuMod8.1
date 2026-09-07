package com.ayutaki.chinjufumod.entity;

import com.ayutaki.chinjufumod.handler.EntityTypes_CM;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

public class AmmoEntity_Kijyuu extends AbstractAmmo_Kijyuu {
	
	public AmmoEntity_Kijyuu(EntityType<? extends AmmoEntity_Kijyuu> type, Level worldIn) {
		super(type, worldIn);
	}

	public AmmoEntity_Kijyuu(Level worldIn, double x, double y, double z, ItemStack stack) {
		super(EntityTypes_CM.AMMO_K.get(), x, y, z, worldIn, stack);
	}

	public AmmoEntity_Kijyuu(Level worldIn, LivingEntity shooter, ItemStack stack) {
		super(EntityTypes_CM.AMMO_K.get(), shooter, worldIn, stack);
	}

	@SuppressWarnings("unused")
	private void setPotionContents(PotionContents content) { }

	@Override
	protected void setPickupItemStack(ItemStack stack) {
		super.setPickupItemStack(stack);
	}

	public void addEffect(MobEffectInstance instance) { }

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder); }

	public int getColor() { return -1; }

	/* Pickup AIR */
	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(Items.AIR);
	}

	@Override
	public void handleEntityEvent(byte value) {
		super.handleEntityEvent(value);
	}
	
	/* Flying render 1.18->1.20 */
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		Entity entity = this.getOwner();
		return new ClientboundAddEntityPacket(this, entity == null ? 0 : entity.getId());
	} // for 20.2
}
