package com.ayutaki.chinjufumod.entity;

import com.ayutaki.chinjufumod.Config_CM;
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
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class AmmoEntity_Medium extends AbstractAmmo_Entity {
	
	public AmmoEntity_Medium(EntityType<? extends AmmoEntity_Medium> type, Level worldIn) {
		super(type, worldIn);
	}

	public AmmoEntity_Medium(Level worldIn, double x, double y, double z, ItemStack stack) {
		super(EntityTypes_CM.AMMO_M.get(), x, y, z, worldIn, stack);
	}

	public AmmoEntity_Medium(Level worldIn, LivingEntity shooter, ItemStack stack) {
		super(EntityTypes_CM.AMMO_M.get(), shooter, worldIn, stack);
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
	
	/** Small=1.0-1.2, 0.5F, Medium=1.5-1.0, 1.25F, Large=2.0-0.8, 2.5F **/
	@Override
	public void hitProcess() {
		if (!this.level().isClientSide()) {
			/** Config value **/
			boolean blast = Config_CM.INSTANCE.blastBlockBreak.get();
			this.createExplosion(this, this.getX(), this.getY(), this.getZ(), 1.25F, false, (blast == true)? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.KEEP); }

		this.playSound(this.getHitGroundSoundEvent(), 1.5F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
		this.level().broadcastEntityEvent(this, (byte)3);
	}
}
