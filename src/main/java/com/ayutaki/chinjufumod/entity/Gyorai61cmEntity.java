package com.ayutaki.chinjufumod.entity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.EntityTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.Util;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class Gyorai61cmEntity extends AbstractST_Entity implements ItemSupplier {
	
	private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(Gyorai61cmEntity.class, EntityDataSerializers.BYTE);
	private ItemStack projectile = new ItemStack(Items_Weapon.GYORAI_61cm.get());
	private double baseDamage = 10.0D;

	public Gyorai61cmEntity(EntityType<Gyorai61cmEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public Gyorai61cmEntity(LivingEntity entityIn, Level world, ItemStack stack) {
		super(EntityTypes_CM.GYORAI61.get(), entityIn, world, stack);
		this.projectile = stack.copy();
	}

	public Gyorai61cmEntity(Level wroldIn, double x, double y, double z, ItemStack stack) {
		super(EntityTypes_CM.GYORAI61.get(), x, y, z, wroldIn, stack);
	}
	
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_ITEM_STACK, ItemStack.EMPTY);
		builder.define(ID_FLAGS, (byte)0);
	} // for 20.6

	/* Flying render 1.20->1.21.4 */
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entityS) {
		Entity entity = this.getOwner();
		return new ClientboundAddEntityPacket(this, entityS, entity == null ? 0 : entity.getId());
	}

	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	} // for 20.6

	@Override
	public void tick() {
		super.tick();
		this.applyGravity_ST();
		
		/** Add Particle **/
		this.level().addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);

		/** Server state control **/
		if (this.level() instanceof ServerLevel && tickCount >= 60) { 
				this.hitProcess();
				this.discard(); }
	}
	
	/* Reflects durability value with "stack.copy()". */
	protected ItemStack getItemStack() {
		return projectile.copy();
	}
	
	/* Collision processing. */
	/** Collision to Entity. **/
	@Override
	protected void onHitEntity_ST(EntityHitResult result) {
		if (this.level() instanceof ServerLevel server && result.getEntity() instanceof LivingEntity) {
			Entity target = result.getEntity();
			Entity thrower = this.getOwner();
			
			/** Attack Mobs. **/
			double dealDamage = this.baseDamage;
			float criticalA = (this.random.nextInt(4) == 0)? 1.5F : 1.0F;
			float criticalB = (this.random.nextInt(8) == 0)? 1.25F : 0.9F;
			float criGYORAI = this.isSuirai()? criticalA : criticalB;

			DamageSource source = this.damageSources().thrown(this, thrower);
			target.hurtServer(server, source, (float)dealDamage * criGYORAI);
			EnchantmentHelper.doPostAttackEffectsWithItemSource(server, target, source, this.getWeaponItem());
			
			this.hitProcess();
			this.discard();
		}
	}
	
	/** Collision to Block. **/
	@Override
	protected void onHitBlock_ST(BlockHitResult result) {
		BlockState state = level().getBlockState(result.getBlockPos());
		Block block = state.getBlock();
		if (state.canBeReplaced() || block == Blocks.KELP) { 
			return; }
		
		if (!state.canBeReplaced() && block != Blocks.KELP) { 
			this.hitProcess();
			this.discard(); }
	}
	
	@Override
	protected double getDefaultGravity() {
		return 0.01D;
	}

	public final double getGravity_ST() {
		return this.wasTouchingWater ? 0.0 : this.getDefaultGravity();
	}

	protected void applyGravity_ST() {
		double d0 = this.getGravity_ST();
		if (d0 != 0.0) {
			this.setDeltaMovement(this.getDeltaMovement().add(0.0, -d0, 0.0));
		}
	}

	public void setSuirai(boolean flag) {
		this.setFlag(1, flag);
	}
	
	private void setFlag(int value, boolean flag) {
		byte b0 = this.entityData.get(ID_FLAGS);
		if (flag) {
			this.entityData.set(ID_FLAGS, (byte)(b0 | value));
		} else {
			this.entityData.set(ID_FLAGS, (byte)(b0 & ~value));
		}
	}
	
	public boolean isSuirai() {
		byte b0 = this.entityData.get(ID_FLAGS);
		return (b0 & 1) != 0;
	}
	
	/* DATA_ITEM_STACK || stack.tag*/
	public void setItem(ItemStack stack) {
		if (!stack.is(this.getDefaultItem())) {
			this.getEntityData().set(DATA_ITEM_STACK, Util.make(stack.copy(), (consumer) -> {
				consumer.setCount(1);
			}));
		}
	}

	protected Item getDefaultItem() {
		return this.projectile.getItem();
	}

	protected ItemStack getItemRaw() {
		return this.getEntityData().get(DATA_ITEM_STACK);
	}
	
	/* Mandatory for "implements ItemSupplier" */
	public ItemStack getItem() {
		ItemStack stack = this.getItemRaw();
		return stack.isEmpty() ? new ItemStack(this.getDefaultItem()) : stack;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putDouble("damage", this.baseDamage);
		compound.putBoolean("suirai", this.isSuirai());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("damage", 99)) {
			this.baseDamage = compound.getDouble("damage"); }
		
		this.setSuirai(compound.getBoolean("suirai"));
	}
	
	public void setBaseDamage(double damage) {
		this.baseDamage = damage;
	}
	
	public double getBaseDamage() {
		return this.baseDamage;
	}
	
	/* Overwrite in each case. -> */
	public void hitProcess() {
		if (!this.level().isClientSide()) {
			float size = this.isSuirai()? 1.5F : 1.0F;
			this.createExplosion(this, this.position(), size, false, Explosion.BlockInteraction.KEEP); }

		this.playSound(SoundEvents_CM.AM_IMPACT.get(), 2.0F, 0.8F / (this.random.nextFloat() * 0.2F + 0.9F));
		this.level().broadcastEntityEvent(this, (byte)3);
	}
	
	public Explosion_CM createExplosion(@Nullable Entity entityIn, Vec3 vec, float sizeIn, boolean fireIn, Explosion.BlockInteraction mode) {
		return this.explode(entityIn, (DamageSource)null, (ExplosionDamageCalculator)null, vec, sizeIn, fireIn, mode);
	}

	public Explosion_CM explode(@Nullable Entity entityIn, @Nullable DamageSource damage, @Nullable ExplosionDamageCalculator context, Vec3 vec, float sizeIn, boolean fireIn, Explosion.BlockInteraction mode) {
		Explosion_CM explosion = new Explosion_CM((ServerLevel) this.level(), this, damage, context, vec, sizeIn, fireIn, mode);
		explosion.explode();
		return explosion;
	}
	
	/** from Snow ball **/
	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			for (int re = 0; re < 4; re++) {
				this.level().addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D); }
		}
	}
}
