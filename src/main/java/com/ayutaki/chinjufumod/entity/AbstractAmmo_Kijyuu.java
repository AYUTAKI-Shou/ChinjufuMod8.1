package com.ayutaki.chinjufumod.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractAmmo_Kijyuu extends Projectile {
	
	private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(AbstractAmmo_Kijyuu.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(AbstractAmmo_Kijyuu.class, EntityDataSerializers.BYTE);
	@SuppressWarnings("unused")
	private static final int FLAG_CRIT = 1;

	@Nullable
	private BlockState lastState;
	protected boolean inGround;
	protected int inGroundTime;
	public AbstractAmmo_Kijyuu.Pickup pickup = AbstractAmmo_Kijyuu.Pickup.DISALLOWED;
	public int shakeTime;
	private int life;
	private double baseDamage = 1.0D; /***/
	private int knockback;
	private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
	@Nullable
	private IntOpenHashSet piercingIgnoreEntityIds;
	@Nullable
	private List<Entity> piercedAndKilledEntities;
	private ItemStack pickupItemStack = this.getDefaultPickupItem();
	private final IntOpenHashSet ignoredEntities = new IntOpenHashSet();

	@SuppressWarnings("unused")
	private Entity cachedOwner; /**/
	private boolean leftOwner; /**/
	private boolean hasBeenShot; /**/
	
	protected AbstractAmmo_Kijyuu(EntityType<? extends AbstractAmmo_Kijyuu> type, Level worldIn) {
		super(type, worldIn);
	}

	protected AbstractAmmo_Kijyuu(EntityType<? extends AbstractAmmo_Kijyuu> type, Level worldIn, ItemStack stack) {
		this(type, worldIn);
		this.pickupItemStack = stack.copy();
		this.setCustomName(stack.get(DataComponents.CUSTOM_NAME));
		Unit unit = stack.remove(DataComponents.INTANGIBLE_PROJECTILE);
		if (unit != null) {
			this.pickup = AbstractAmmo_Kijyuu.Pickup.CREATIVE_ONLY;
		}
	}

	protected AbstractAmmo_Kijyuu(EntityType<? extends AbstractAmmo_Kijyuu> type, double x, double y, double z, Level worldIn, ItemStack stack) {
		this(type, worldIn, stack);
		this.setPos(x, y, z);
	}

	protected AbstractAmmo_Kijyuu(EntityType<? extends AbstractAmmo_Kijyuu> type, LivingEntity shooter, Level worldIn, ItemStack stack) {
		this(type, shooter.getX(), shooter.getEyeY() - 0.1F, shooter.getZ(), worldIn, stack);
		this.setOwner(shooter);
	}

	public void setSoundEvent(SoundEvent soundIn) {
		this.soundEvent = soundIn;
	}

	/*@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		double d0 = this.getBoundingBox().getSize() * 10.0;
		if (Double.isNaN(d0)) {
			d0 = 1.0;
		}

		d0 *= 64.0 * getViewScale();
		return distance < d0 * d0;
	} */

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(ID_FLAGS, (byte)0);
		builder.define(PIERCE_LEVEL, (byte)0);
	}

	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
		super.shoot(x, y, z, velocity, inaccuracy);
		this.life = 0;
	}

	@Override
	public void lerpTo(double x, double y, double z, float yaw, float pitch, int i) {
		this.setPos(x, y, z);
		this.setRot(yaw, pitch);
	}

	@Override
	public void lerpMotion(double x, double y, double z) {
		super.lerpMotion(x, y, z);
		this.life = 0;
	}

	@Override
	public void tick() {
		// super.tick(); **
		if (!this.hasBeenShot) {
			this.gameEvent(GameEvent.PROJECTILE_SHOOT, this.getOwner());
			this.hasBeenShot = true; } /**/

		if (!this.leftOwner) { this.leftOwner = this.checkLeftOwner(); } /**/
		
		boolean flag = this.isNoPhysics();
		Vec3 vec3 = this.getDeltaMovement();
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			double d0 = vec3.horizontalDistance();
			this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * 180.0F / (float)Math.PI));
			this.setXRot((float)(Mth.atan2(vec3.y, d0) * 180.0F / (float)Math.PI));
			this.yRotO = this.getYRot();
			this.xRotO = this.getXRot();
		}

		BlockPos blockpos = this.blockPosition();
		BlockState blockstate = this.level().getBlockState(blockpos);
		if (!blockstate.isAir() && !flag) {
			VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
			if (!voxelshape.isEmpty()) {
				Vec3 vec31 = this.position();

				for (AABB aabb : voxelshape.toAabbs()) {
					if (aabb.move(blockpos).contains(vec31)) {
						this.inGround = true;
						break;
					}
				}
			}
		}

		if (this.shakeTime > 0) {
			this.shakeTime--;
		}

		if (this.isInWaterOrRain() || blockstate.is(Blocks.POWDER_SNOW) || this.isInFluidType((fluidType, height) -> this.canFluidExtinguish(fluidType))) {
			this.clearFire();
		}

		/** inGround remove **/
		if (this.inGround && !flag) {
			this.level().broadcastEntityEvent(this, (byte)3); /***/
			this.discard();
		} 
		
		else {
			this.inGroundTime = 0;
			Vec3 vec32 = this.position();
			Vec3 vec33 = vec32.add(vec3);
			HitResult hitresult = this.level().clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
			if (hitresult.getType() != HitResult.Type.MISS) {
				vec33 = hitresult.getLocation();
			}

			while (!this.isRemoved()) {
				EntityHitResult entityhitresult = this.findHitEntity(vec32, vec33);
				if (entityhitresult != null) {
					hitresult = entityhitresult;
				}

				if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
					Entity entity = ((EntityHitResult)hitresult).getEntity();
					Entity owner = this.getOwner();
					if (entity instanceof Player && owner instanceof Player && !((Player)owner).canHarmPlayer((Player)entity)) {
						hitresult = null;
						entityhitresult = null;
					}
				}

				if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !flag) {
					switch (net.minecraftforge.event.ForgeEventFactory.onProjectileImpactResult(this, hitresult)) {
						case SKIP_ENTITY:
							if (hitresult.getType() != HitResult.Type.ENTITY) { // If there is no entity, we just return default behaviour
								this.onHit(hitresult);
								this.hasImpulse = true;
								break;
							}
							ignoredEntities.add(entityhitresult.getEntity().getId());
							entityhitresult = null; // Don't process any further
							break;
						case STOP_AT_CURRENT_NO_DAMAGE:
							this.level().broadcastEntityEvent(this, (byte)3); /***/
							this.discard();
							entityhitresult = null; // Don't process any further
							break;
						case STOP_AT_CURRENT:
							this.setPierceLevel((byte) 0);
						case DEFAULT:
							this.onHit(hitresult);
							this.hasImpulse = true;
							break;
					}
				}

				if (entityhitresult == null || this.getPierceLevel() <= 0) {
					break;
				}

				hitresult = null;
			}

			if (this.isRemoved())
				return;

			vec3 = this.getDeltaMovement();
			double d5 = vec3.x;
			double d6 = vec3.y;
			double d1 = vec3.z;
			/* addParticle */

			double d7 = this.getX() + d5;
			double d2 = this.getY() + d6;
			double d3 = this.getZ() + d1;
			double d4 = vec3.horizontalDistance();
			if (flag) {
				this.setYRot((float)(Mth.atan2(-d5, -d1) * 180.0F / (float)Math.PI));
			} else {
				this.setYRot((float)(Mth.atan2(d5, d1) * 180.0F / (float)Math.PI));
			}

			this.setXRot((float)(Mth.atan2(d6, d4) * 180.0F / (float)Math.PI));
			this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
			this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
			float f = 0.99F;
			if (this.isInWater()) {
				for (int j = 0; j < 4; j++) {
					//float f1 = 0.25F;
					this.level().addParticle(ParticleTypes.BUBBLE, d7 - d5 * 0.25, d2 - d6 * 0.25, d3 - d1 * 0.25, d5, d6, d1); }

				f = this.getWaterInertia();
			}

			this.setDeltaMovement(vec3.scale((double)f));
			if (!flag) { this.applyGravity(); }

			this.setPos(d7, d2, d3);
			this.checkInsideBlocks();
		}
	}

	private boolean checkLeftOwner() {
		Entity owner = this.getOwner();
		if (owner != null) {
			for (Entity entity1 : this.level()
				.getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0), entity0 -> !entity0.isSpectator() && entity0.isPickable())) {
				if (entity1.getRootVehicle() == owner.getRootVehicle()) { return false; }
			}
		}

		return true;
	} /**/
	
	@Override
	protected double getDefaultGravity() {
		return 0.05;
	}

	private boolean shouldFall() {
		return this.inGround && this.level().noCollision(new AABB(this.position(), this.position()).inflate(0.06));
	}

	private void startFalling() {
		this.inGround = false;
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F)));
		this.life = 0;
	}

	@Override
	public void move(MoverType mover, Vec3 vector3d) {
		super.move(mover, vector3d);
		if (mover != MoverType.SELF && this.shouldFall()) {
			this.startFalling();
		}
	}

	protected void tickDespawn() {
		this.life++;
		if (this.life >= 1200) {
			this.level().broadcastEntityEvent(this, (byte)3); /***/
			this.discard();
		}
	}

	private void resetPiercedEntities() {
		if (this.piercedAndKilledEntities != null) {
			this.piercedAndKilledEntities.clear();
		}

		if (this.piercingIgnoreEntityIds != null) {
			this.piercingIgnoreEntityIds.clear();
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		//super.onHitEntity(result); Don't refer to arrow.
		Entity entity = result.getEntity();
		float f = (float)this.getDeltaMovement().length();
		int i = Mth.ceil(Mth.clamp((double)f * this.baseDamage, 0.0, 2.147483647E9));
		if (this.getPierceLevel() > 0) {
			if (this.piercingIgnoreEntityIds == null) {
				this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
			}

			if (this.piercedAndKilledEntities == null) {
				this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
			}

			if (this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1) {
				this.level().broadcastEntityEvent(this, (byte)3); /***/
				this.discard();
				return;
			}

			this.piercingIgnoreEntityIds.add(entity.getId());
		}

		if (this.isCritArrow()) {
			long j = (long)this.random.nextInt(i / 2 + 2);
			i = (int)Math.min(j + (long)i, 2147483647L);
		}

		Entity owner = this.getOwner();
		DamageSource damagesource;
		if (owner == null) {
			damagesource = this.damageSources().thrown(this, this); // 1.18->1.20
		} else {
			damagesource = this.damageSources().thrown(this, owner); // 1.18->1.20
			if (owner instanceof LivingEntity) {
				((LivingEntity)owner).setLastHurtMob(entity);
			}
		}

		boolean flag = entity.getType() == EntityType.ENDERMAN;
		int k = entity.getRemainingFireTicks();
		if (this.isOnFire() && !flag) {
			entity.igniteForSeconds(5);
		}

		if (entity.hurt(damagesource, (float)i)) {
			if (flag) {
				return;
			}

			if (entity instanceof LivingEntity livingentity) {
				if (!this.level().isClientSide() && this.getPierceLevel() <= 0) {
					livingentity.setArrowCount(livingentity.getArrowCount() + 1);
				}

				if (this.knockback > 0) {
					double d0 = Math.max(0.0, 1.0 - livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
					Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale((double)this.knockback * 0.6 * d0);
					if (vec3.lengthSqr() > 0.0) {
						livingentity.push(vec3.x, 0.1, vec3.z);
					}
				}

				if (!this.level().isClientSide() && owner instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingentity, owner);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)owner, livingentity);
				}

				this.doPostHurtEffects(livingentity);
				if (owner != null && livingentity != owner && livingentity instanceof Player && owner instanceof ServerPlayer && !this.isSilent()) {
					((ServerPlayer)owner).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
				}

				if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
					this.piercedAndKilledEntities.add(livingentity);
				}
			}

			this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
			if (this.getPierceLevel() <= 0) {
				this.level().broadcastEntityEvent(this, (byte)3); /***/
				this.discard();
			}
		} else {
			entity.setRemainingFireTicks(k);
			this.deflect(ProjectileDeflection.REVERSE, entity, this.getOwner(), false);
			this.setDeltaMovement(this.getDeltaMovement().scale(0.2));
			if (!this.level().isClientSide() && this.getDeltaMovement().lengthSqr() < 1.0E-7) {
				if (this.pickup == AbstractAmmo_Kijyuu.Pickup.ALLOWED) {
					this.spawnAtLocation(this.getPickupItem(), 0.1F);
				}
				
				this.level().broadcastEntityEvent(this, (byte)3); /***/
				this.discard();
			}
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		this.lastState = this.level().getBlockState(result.getBlockPos());
		super.onHitBlock(result);
		Vec3 vec3 = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
		this.setDeltaMovement(vec3);
		Vec3 vec31 = vec3.normalize().scale(0.05F);
		this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
		this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
		this.inGround = true;
		this.shakeTime = 7;
		this.setCritArrow(false);
		this.setPierceLevel((byte)0);
		this.setSoundEvent(SoundEvents_CM.AM_HIT.get());
		this.resetPiercedEntities();
	}

	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents_CM.AM_HIT.get();
	}

	protected final SoundEvent getHitGroundSoundEvent() {
		return this.soundEvent;
	}

	protected void doPostHurtEffects(LivingEntity entityIn) { }

	@Nullable
	protected EntityHitResult findHitEntity(Vec3 vec3_1, Vec3 vec3_2) {
		return ProjectileUtil.getEntityHitResult(this.level(), this, vec3_1, vec3_2, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0), this::canHitEntity);
	}

	@Override
	protected boolean canHitEntity(Entity entityIn) {
		return super.canHitEntity(entityIn) && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(entityIn.getId())) && !this.ignoredEntities.contains(entityIn.getId());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putShort("life", (short)this.life);
		if (this.lastState != null) {
			compound.put("inBlockState", NbtUtils.writeBlockState(this.lastState));
		}

		compound.putByte("shake", (byte)this.shakeTime);
		compound.putBoolean("inGround", this.inGround);
		compound.putByte("pickup", (byte)this.pickup.ordinal());
		compound.putDouble("damage", this.baseDamage);
		compound.putBoolean("crit", this.isCritArrow());
		compound.putByte("PierceLevel", this.getPierceLevel());
		compound.putString("SoundEvent", BuiltInRegistries.SOUND_EVENT.getKey(this.soundEvent).toString());
		compound.put("item", this.pickupItemStack.save(this.registryAccess()));
	}

	@SuppressWarnings("removal")
	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.life = compound.getShort("life");
		if (compound.contains("inBlockState", 10)) {
			this.lastState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compound.getCompound("inBlockState"));
		}

		this.shakeTime = compound.getByte("shake") & 255;
		this.inGround = compound.getBoolean("inGround");
		if (compound.contains("damage", 99)) {
			this.baseDamage = compound.getDouble("damage");
		}

		this.pickup = AbstractAmmo_Kijyuu.Pickup.byOrdinal(compound.getByte("pickup"));
		this.setCritArrow(compound.getBoolean("crit"));
		this.setPierceLevel(compound.getByte("PierceLevel"));
		if (compound.contains("SoundEvent", 8)) {
			this.soundEvent = BuiltInRegistries.SOUND_EVENT.getOptional(new ResourceLocation(compound.getString("SoundEvent"))).orElse(this.getDefaultHitGroundSoundEvent());
		}

		if (compound.contains("item", 10)) {
			this.setPickupItemStack(ItemStack.parse(this.registryAccess(), compound.getCompound("item")).orElse(this.getDefaultPickupItem())); } 
		else { this.setPickupItemStack(this.getDefaultPickupItem()); }
	}

	@Override
	public void setOwner(@Nullable Entity entityIn) {
		super.setOwner(entityIn);
		if (entityIn instanceof Player && this.pickup == AbstractAmmo_Kijyuu.Pickup.DISALLOWED) {
			this.pickup = AbstractAmmo_Kijyuu.Pickup.ALLOWED;
		}
	}

	@Override
	public void playerTouch(Player entityIn) {
		if (!this.level().isClientSide() && (this.inGround || this.isNoPhysics()) && this.shakeTime <= 0) {
			if (this.tryPickup(entityIn)) {
				entityIn.take(this, 1);
				this.discard();
			}
		}
	}

	protected boolean tryPickup(Player entityIn) {
		return false;
	}

	protected ItemStack getPickupItem() {
		return this.pickupItemStack.copy();
	}

	protected abstract ItemStack getDefaultPickupItem();

	@Override
	protected Entity.MovementEmission getMovementEmission() {
		return Entity.MovementEmission.NONE;
	}

	public ItemStack getPickupItemStackOrigin() {
		return this.pickupItemStack;
	}

	public void setBaseDamage(double damage) {
		this.baseDamage = damage;
	}

	public double getBaseDamage() {
		return this.baseDamage;
	}

	public void setKnockback(int knockBack) {
		this.knockback = knockBack;
	}

	public int getKnockback() {
		return this.knockback;
	}

	@Override
	public boolean isAttackable() {
		return this.getType().is(EntityTypeTags.REDIRECTABLE_PROJECTILE);
	}

	public void setCritArrow(boolean flag) {
		this.setFlag(1, flag);
	}

	public void setPierceLevel(byte value) {
		this.entityData.set(PIERCE_LEVEL, value);
	}

	private void setFlag(int value, boolean flag) {
		byte b0 = this.entityData.get(ID_FLAGS);
		if (flag) {
			this.entityData.set(ID_FLAGS, (byte)(b0 | value));
		} else {
			this.entityData.set(ID_FLAGS, (byte)(b0 & ~value));
		}
	}

	protected void setPickupItemStack(ItemStack stack) {
		if (!stack.isEmpty()) { this.pickupItemStack = stack; } 
		else { this.pickupItemStack = this.getDefaultPickupItem(); }
	}

	public boolean isCritArrow() {
		byte b0 = this.entityData.get(ID_FLAGS);
		return (b0 & 1) != 0;
	}

	public byte getPierceLevel() {
		return this.entityData.get(PIERCE_LEVEL);
	}

	public void setEnchantmentEffectsFromEntity(LivingEntity entityIn, float value) {
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, entityIn);
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, entityIn);
		this.setBaseDamage((double)(value * 2.0F) + this.random.triangle((double)this.level().getDifficulty().getId() * 0.11, 0.57425));

		if (i > 0) { this.setBaseDamage(this.getBaseDamage() + (double)i * 0.5 + 0.5); }

		if (j > 0) { this.setKnockback(j); }

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, entityIn) > 0) {
			this.igniteForSeconds(100); }
	}

	protected float getWaterInertia() {
		return 0.6F;
	}

	public void setNoPhysics(boolean flag) {
		this.noPhysics = flag;
		this.setFlag(2, flag);
	}

	public boolean isNoPhysics() {
		return !this.level().isClientSide() ? this.noPhysics : (this.entityData.get(ID_FLAGS) & 2) != 0;
	}

	@Override
	public boolean isPickable() {
		return super.isPickable() && !this.inGround;
	}

	@Override
	public SlotAccess getSlot(int i) {
		return i == 0 ? SlotAccess.of(this::getPickupItemStackOrigin, this::setPickupItemStack) : super.getSlot(i);
	}

	public static enum Pickup {
		DISALLOWED,
		ALLOWED,
		CREATIVE_ONLY;

		public static AbstractAmmo_Kijyuu.Pickup byOrdinal(int i) {
			if (i < 0 || i > values().length) { i = 0; }
			return values()[i];
		}
	}
	
	/** from Snow ball **/
	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			this.level().addParticle((ParticleOptions) ParticleTypes_CM.MARK_PT.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D); }
	}
}
