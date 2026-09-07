package com.ayutaki.chinjufumod.entity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.EntityTypes_CM;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class AmmoEntity_Small extends AbstractAmmo_Entity {
	
	public AmmoEntity_Small(EntityType<? extends AmmoEntity_Small> type, Level worldIn) {
		super(type, worldIn);
	}

	public AmmoEntity_Small(Level worldIn, double x, double y, double z, ItemStack stack, @Nullable ItemStack stackS) {
		super(EntityTypes_CM.AMMO_S.get(), x, y, z, worldIn, stack, stackS);
	}

	public AmmoEntity_Small(Level worldIn, LivingEntity shooter, ItemStack stack, @Nullable ItemStack stackS) {
		super(EntityTypes_CM.AMMO_S.get(), shooter, worldIn, stack, stackS);
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
	
	/* Flying render 1.20->1.21.4 */
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entityS) {
		Entity entity = this.getOwner();
		return new ClientboundAddEntityPacket(this, entityS, entity == null ? 0 : entity.getId());
	}
	
	/** Small=1.0-1.2, 0.5F, Medium=1.5-1.0, 1.25F, Large=2.0-0.8, 2.5F **/
	@Override
	public void hitProcess() {
		if (!this.level().isClientSide()) {
			this.createExplosion(this, this.position(), 0.5F, false, Explosion.BlockInteraction.KEEP); }

		this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
		this.level().broadcastEntityEvent(this, (byte)3);
	}
}
