package com.ayutaki.chinjufumod.entity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.Config_CM;
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

public class AmmoEntity_Large extends AbstractAmmo_Entity {
	
	public AmmoEntity_Large(EntityType<? extends AmmoEntity_Large> type, Level worldIn) {
		super(type, worldIn);
	}

	public AmmoEntity_Large(Level worldIn, double x, double y, double z, ItemStack stack, @Nullable ItemStack stackS) {
		super(EntityTypes_CM.AMMO_L.get(), x, y, z, worldIn, stack, stackS);
	}

	public AmmoEntity_Large(Level worldIn, LivingEntity shooter, ItemStack stack, @Nullable ItemStack stackS) {
		super(EntityTypes_CM.AMMO_L.get(), shooter, worldIn, stack, stackS);
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
			/** Config value **/
			boolean blast = Config_CM.INSTANCE.blastBlockBreak.get();
			this.createExplosion(this, this.position(), 2.5F, false, (blast == true)? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.KEEP); }

		this.playSound(this.getHitGroundSoundEvent(), 2.0F, 0.8F / (this.random.nextFloat() * 0.2F + 0.9F));
		this.level().broadcastEntityEvent(this, (byte)3);
	}
}
