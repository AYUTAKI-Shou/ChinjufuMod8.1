package com.ayutaki.chinjufumod.entity;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.OminousItemSpawner;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public abstract class AbstractAmmo_Entity extends Projectile {
	
	private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(AbstractAmmo_Entity.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(AbstractAmmo_Entity.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Boolean> IN_GROUND = SynchedEntityData.defineId(AbstractAmmo_Entity.class, EntityDataSerializers.BOOLEAN);
	@SuppressWarnings("unused")
	private static final int FLAG_CRIT = 1;
	
	@Nullable
	private BlockState lastState;
	protected int inGroundTime;
	public AbstractAmmo_Entity.Pickup pickup = AbstractAmmo_Entity.Pickup.DISALLOWED;
	public int shakeTime;
	private int life;
	/** Arrow = 2.0D, Craft Order and LUCK = 2.0D **/
	private double ammoDamage = 2.0D;
	private int knockback;
	private int igniteSeconds;
	private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
	@Nullable
	private IntOpenHashSet piercingIgnoreEntityIds;
	@Nullable
	private List<Entity> piercedAndKilledEntities;
	private ItemStack pickupItemStack = this.getDefaultPickupItem();
	@Nullable
	private ItemStack firedFromWeapon = null;
	private final IntOpenHashSet ignoredEntities = new IntOpenHashSet();
	
	@SuppressWarnings("unused")
	private Entity cachedOwner; /**/
	private boolean leftOwner; /**/
	private boolean hasBeenShot; /**/

	protected AbstractAmmo_Entity(EntityType<? extends AbstractAmmo_Entity> type, Level worldIn) {
		super(type, worldIn);
	}

	protected AbstractAmmo_Entity(EntityType<? extends AbstractAmmo_Entity> type, double x, double y, double z, Level worldIn, ItemStack stack, @Nullable ItemStack stackS) {
		this(type, worldIn);
		this.pickupItemStack = stack.copy();
		this.setCustomName(stack.get(DataComponents.CUSTOM_NAME));
		Unit unit = stack.remove(DataComponents.INTANGIBLE_PROJECTILE);
		if (unit != null) { this.pickup = AbstractAmmo_Entity.Pickup.CREATIVE_ONLY; }
		
		this.setPos(x, y, z);
		
		if (stackS != null && worldIn instanceof ServerLevel server) {
			if (stackS.isEmpty()) { throw new IllegalArgumentException("Invalid weapon firing an arrow"); }

			this.firedFromWeapon = stackS.copy();
			int i = EnchantmentHelper.getPiercingCount(server, stackS, this.pickupItemStack);
			if (i > 0) { this.setPierceLevel((byte)i); }
		}
	}

	protected AbstractAmmo_Entity(EntityType<? extends AbstractAmmo_Entity> type, LivingEntity shooter, Level worldIn, ItemStack stack, @Nullable ItemStack stackS) {
		this(type, shooter.getX(), shooter.getEyeY() - 0.1F, shooter.getZ(), worldIn, stack, stackS);
		this.setOwner(shooter);
	}

	public void setSoundEvent(SoundEvent soundIn) {
		this.soundEvent = soundIn;
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		double d0 = this.getBoundingBox().getSize() * 10.0;
		if (Double.isNaN(d0)) {
			d0 = 1.0;
		}

		d0 *= 64.0 * getViewScale();
		return distance < d0 * d0;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(ID_FLAGS, (byte)0);
		builder.define(PIERCE_LEVEL, (byte)0);
		builder.define(IN_GROUND, false);
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
		if (this.isInGround() && Mth.lengthSquared(x, y, z) > 0.0) {
			this.setInGround(false);
		}
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
		super.onSyncedDataUpdated(data);
		if (!this.firstTick && this.shakeTime <= 0 && data.equals(IN_GROUND) && this.isInGround()) {
			this.shakeTime = 7;
		}
	}

	@Override
	public void tick() {
		
		boolean flag = !this.isNoPhysics();
		Vec3 vec3 = this.getDeltaMovement();
		BlockPos blockpos = this.blockPosition();
		BlockState blockstate = this.level().getBlockState(blockpos);
		if (!blockstate.isAir() && flag) {
			VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
			if (!voxelshape.isEmpty()) {
				Vec3 vec31 = this.position();

				for (AABB aabb : voxelshape.toAabbs()) {
					if (aabb.move(blockpos).contains(vec31)) {
						this.setInGround(true);
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
		if (this.isInGround() && flag) {
			this.hitProcess();
			this.discard();
		} 
		
		else {
			if (!this.hasBeenShot) {
				this.gameEvent(GameEvent.PROJECTILE_SHOOT, this.getOwner());
				this.hasBeenShot = true; } /**/

			if (!this.leftOwner) { this.leftOwner = this.checkLeftOwner(); } /**/
			
			this.inGroundTime = 0;
			Vec3 vec32 = this.position();
			if (this.isInWater()) {
				this.applyInertia(this.getWaterInertia());
				this.addBubbleParticles(vec32);
			}

			if (this.isCritArrow()) {
				for (int i = 0; i < 4; i++) {
					this.level()
						.addParticle(ParticleTypes.CRIT, vec32.x + vec3.x * (double)i / 4.0, vec32.y + vec3.y * (double)i / 4.0, vec32.z + vec3.z * (double)i / 4.0, -vec3.x, -vec3.y + 0.2, -vec3.z);
				}
			}

			float f;
			if (!flag) {
				f = (float)(Mth.atan2(-vec3.x, -vec3.z) * 180.0F / (float)Math.PI);
			} else {
				f = (float)(Mth.atan2(vec3.x, vec3.z) * 180.0F / (float)Math.PI);
			}

			float f1 = (float)(Mth.atan2(vec3.y, vec3.horizontalDistance()) * 180.0F / (float)Math.PI);
			this.setXRot(lerpRotation(this.getXRot(), f1));
			this.setYRot(lerpRotation(this.getYRot(), f));
			if (flag) {
				BlockHitResult blockhitresult = this.level().clipIncludingBorder(new ClipContext(vec32, vec32.add(vec3), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
				this.stepMoveAndHit(blockhitresult);
			} else {
				this.setPos(vec32.add(vec3));
				this.applyEffectsFromBlocks();
			}

			if (!this.isInWater()) {
				this.applyInertia(0.99F);
			}

			if (flag && !this.isInGround()) {
				this.applyGravity();
			}

			//super.tick();
		}
	}

	private boolean checkLeftOwner() {
		Entity entity = this.getOwner();
		if (entity != null) {
			AABB aabb = this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0);
			return entity.getRootVehicle().getSelfAndPassengers().filter(EntitySelector.CAN_BE_PICKED).noneMatch(p_359340_ -> aabb.intersects(p_359340_.getBoundingBox()));
		} 
		
		else { return true; }
	} /**/
	
	private void stepMoveAndHit(BlockHitResult result) {
		while (this.isAlive()) {
			Vec3 vec3 = this.position();
			EntityHitResult entityhitresult = this.findHitEntity(vec3, result.getLocation());
			Vec3 vec31 = Objects.requireNonNullElse(entityhitresult, result).getLocation();
			this.setPos(vec31);
			this.applyEffectsFromBlocks(vec3, vec31);
			if (this.portalProcess != null && this.portalProcess.isInsidePortalThisTick()) {
				this.handlePortal(); }

			if (entityhitresult == null) {
				if (this.isAlive() && result.getType() != HitResult.Type.MISS) {
					this.hitTargetOrDeflectSelf(result);
					this.hasImpulse = true; }
				break;
			} 
			
			else if (this.isAlive() && !this.noPhysics) {
				ProjectileDeflection projectiledeflection = this.hitTargetOrDeflectSelf(entityhitresult);
				this.hasImpulse = true;
				if (this.getPierceLevel() > 0 && projectiledeflection == ProjectileDeflection.NONE) {
					continue; }
				break;
			}
		}
	}

	private void applyInertia(float f) {
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.scale((double)f));
	}

	private void addBubbleParticles(Vec3 vector3d) {
		Vec3 vec3 = this.getDeltaMovement();

		for (int i = 0; i < 4; i++) {
			//float f = 0.25F;
			this.level().addParticle(ParticleTypes.BUBBLE, vector3d.x - vec3.x * 0.25, vector3d.y - vec3.y * 0.25, vector3d.z - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
		}
	}

	@Override
	protected double getDefaultGravity() {
		return 0.05;
	}

	private boolean shouldFall() {
		return this.isInGround() && this.level().noCollision(new AABB(this.position(), this.position()).inflate(0.06));
	}

	private void startFalling() {
		this.setInGround(false);
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(
			vec3.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F))
		);
		this.life = 0;
	}

	protected boolean isInGround() {
		return this.entityData.get(IN_GROUND);
	}

	protected void setInGround(boolean flag) {
		this.entityData.set(IN_GROUND, flag);
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
			this.hitProcess();
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
	protected void onItemBreak(Item itemIn) {
		this.firedFromWeapon = null;
	}

	@Override
	public void onInsideBubbleColumn(boolean flag) {
		if (!this.isInGround()) {
			super.onInsideBubbleColumn(flag);
		}
	}

	@Override
	public void push(double x, double y, double z) {
		if (!this.isInGround()) {
			super.push(x, y, z);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		Entity entity = result.getEntity();
		float f = (float)this.getDeltaMovement().length();
		double d0 = this.ammoDamage;
		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().thrown(this, (Entity)(entity1 != null ? entity1 : this));
		
		if (this.getWeaponItem() != null && this.level() instanceof ServerLevel server) {
			d0 = (double)EnchantmentHelper.modifyDamage(server, this.getWeaponItem(), entity, damagesource, (float)d0);
		}

		int j = Mth.ceil(Mth.clamp((double)f * d0, 0.0, 2.147483647E9));
		if (this.getPierceLevel() > 0) {
			if (this.piercingIgnoreEntityIds == null) {
				this.piercingIgnoreEntityIds = new IntOpenHashSet(5); }

			if (this.piercedAndKilledEntities == null) {
				this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5); }

			if (this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1) {
				this.hitProcess();
				this.discard();
				return; }

			this.piercingIgnoreEntityIds.add(entity.getId());
		}

		if (this.isCritArrow()) {
			long k = (long)this.random.nextInt(j / 2 + 2);
			j = (int)Math.min(k + (long)j, 2147483647L);
		}

		if (entity1 instanceof LivingEntity livingentity1) {
			livingentity1.setLastHurtMob(entity);
		}

		boolean flag = entity.getType() == EntityType.ENDERMAN;
		int i = entity.getRemainingFireTicks();
		if (this.isOnFire() && !flag) {
			entity.igniteForSeconds((this.igniteSeconds == 100)? 100.0F : 5.0F);
		}

		if (entity.hurtOrSimulate(damagesource, (float)j)) {
			if (flag) { return; }

			if (entity instanceof LivingEntity livingentity) {
				if (!this.level().isClientSide() && this.getPierceLevel() <= 0) {
					livingentity.setArrowCount(livingentity.getArrowCount() + 1);
				}

				this.doKnockback(livingentity, damagesource);
				if (this.level() instanceof ServerLevel server1) {
					EnchantmentHelper.doPostAttackEffectsWithItemSource(server1, livingentity, damagesource, this.getWeaponItem());
				}

				this.doPostHurtEffects(livingentity);
				if (livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
					((ServerPlayer)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
				}

				if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
					this.piercedAndKilledEntities.add(livingentity);
				}

				if (!this.level().isClientSide() && entity1 instanceof ServerPlayer serverplayer) {
					if (this.piercedAndKilledEntities != null) {
						CriteriaTriggers.KILLED_BY_ARROW.trigger(serverplayer, this.piercedAndKilledEntities, this.firedFromWeapon);
					} else if (!entity.isAlive()) {
						CriteriaTriggers.KILLED_BY_ARROW.trigger(serverplayer, List.of(entity), this.firedFromWeapon);
					}
				}
			}

			this.playSound(this.soundEvent, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
			if (this.getPierceLevel() <= 0) {
				this.hitProcess();
				this.discard();
			}
		} 
		
		else {
			entity.setRemainingFireTicks(i);
			this.deflect(ProjectileDeflection.REVERSE, entity, this.getOwner(), false);
			this.setDeltaMovement(this.getDeltaMovement().scale(0.2));
			if (this.level() instanceof ServerLevel server2 && this.getDeltaMovement().lengthSqr() < 1.0E-7) {
				if (this.pickup == AbstractAmmo_Entity.Pickup.ALLOWED) {
					this.spawnAtLocation(server2, this.getPickupItem(), 0.1F);
				}

				this.hitProcess();
				this.discard();
			}
		}
	}

	protected void doKnockback(LivingEntity entityIn, DamageSource damage) {
		double d0 = (double)(
			this.firedFromWeapon != null && this.level() instanceof ServerLevel server
				? EnchantmentHelper.modifyKnockback(server, this.firedFromWeapon, entityIn, damage, 0.0F) : 0.0F);
		
		double k = this.knockback;
		if (d0 > 0.0) {
			double d1 = Math.max(0.0, 1.0 - entityIn.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
			Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(k * 0.6 * d1); //for 1.21.4
			if (vec3.lengthSqr() > 0.0) {
				entityIn.push(vec3.x, 0.1, vec3.z);
			}
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		this.lastState = this.level().getBlockState(result.getBlockPos());
		super.onHitBlock(result);
		ItemStack itemstack = this.getWeaponItem();
		if (this.level() instanceof ServerLevel server && itemstack != null) {
			this.hitBlockEnchantmentEffects(server, result, itemstack);
		}

		Vec3 vec31 = this.getDeltaMovement();
		Vec3 vec32 = new Vec3(Math.signum(vec31.x), Math.signum(vec31.y), Math.signum(vec31.z));
		Vec3 vec3 = vec32.scale(0.05F);
		this.setPos(this.position().subtract(vec3));
		this.setDeltaMovement(Vec3.ZERO);
		this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
		this.setInGround(true);
		this.shakeTime = 7;
		this.setCritArrow(false);
		this.setPierceLevel((byte)0);
		this.setSoundEvent(SoundEvents_CM.AM_IMPACT.get());
		this.resetPiercedEntities();
	}

	protected void hitBlockEnchantmentEffects(ServerLevel worldIn, BlockHitResult result, ItemStack stack) {
		Vec3 vec3 = result.getBlockPos().clampLocationWithin(result.getLocation());
		EnchantmentHelper.onHitBlock(
			worldIn, stack, this.getOwner() instanceof LivingEntity livingentity ? livingentity : null, this, null, vec3, worldIn.getBlockState(result.getBlockPos()), p_344325_ -> this.firedFromWeapon = null);
	}

	@Override
	public ItemStack getWeaponItem() {
		return this.firedFromWeapon;
	}

	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents_CM.AM_IMPACT.get();
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
		return entityIn instanceof Player && this.getOwner() instanceof Player player && !player.canHarmPlayer((Player)entityIn)
			? false
			: super.canHitEntity(entityIn) && (this.piercingIgnoreEntityIds == null || !this.piercingIgnoreEntityIds.contains(entityIn.getId())) && !this.ignoredEntities.contains(entityIn.getId());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putShort("life", (short)this.life);
		if (this.lastState != null) {
			compound.put("inBlockState", NbtUtils.writeBlockState(this.lastState));
		}

		compound.putByte("shake", (byte)this.shakeTime);
		compound.putBoolean("inGround", this.isInGround());
		compound.putByte("pickup", (byte)this.pickup.ordinal());
		compound.putDouble("damage", this.ammoDamage);
		compound.putBoolean("crit", this.isCritArrow());
		compound.putByte("PierceLevel", this.getPierceLevel());
		compound.putString("SoundEvent", BuiltInRegistries.SOUND_EVENT.getKey(this.soundEvent).toString());
		compound.put("item", this.pickupItemStack.save(this.registryAccess()));
		if (this.firedFromWeapon != null) {
			compound.put("weapon", this.firedFromWeapon.save(this.registryAccess(), new CompoundTag())); }
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.life = compound.getShort("life");
		if (compound.contains("inBlockState", 10)) {
			this.lastState = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compound.getCompound("inBlockState"));
		}

		this.shakeTime = compound.getByte("shake") & 255;
		this.setInGround(compound.getBoolean("inGround"));
		if (compound.contains("damage", 99)) {
			this.ammoDamage = compound.getDouble("damage");
		}

		this.pickup = AbstractAmmo_Entity.Pickup.byOrdinal(compound.getByte("pickup"));
		this.setCritArrow(compound.getBoolean("crit"));
		this.setPierceLevel(compound.getByte("PierceLevel"));
		if (compound.contains("SoundEvent", 8)) {
			this.soundEvent = BuiltInRegistries.SOUND_EVENT.getOptional(ResourceLocation.parse(compound.getString("SoundEvent"))).orElse(this.getDefaultHitGroundSoundEvent());
		}

		if (compound.contains("item", 10)) {
			this.setPickupItemStack(ItemStack.parse(this.registryAccess(), compound.getCompound("item")).orElse(this.getDefaultPickupItem())); } 
		else { this.setPickupItemStack(this.getDefaultPickupItem()); }

		if (compound.contains("weapon", 10)) {
			this.firedFromWeapon = ItemStack.parse(this.registryAccess(), compound.getCompound("weapon")).orElse(null); } 
		else { this.firedFromWeapon = null; }
	}

	@Override
	public void setOwner(@Nullable Entity entityIn) {
		super.setOwner(entityIn);

		this.pickup = switch (entityIn) {
			case Player player when this.pickup == AbstractAmmo_Entity.Pickup.DISALLOWED -> AbstractAmmo_Entity.Pickup.ALLOWED;
			case OminousItemSpawner ominousitemspawner -> AbstractAmmo_Entity.Pickup.DISALLOWED;
			case null, default -> this.pickup;
		};
	}

	@Override
	public void playerTouch(Player entityIn) {
		if (!this.level().isClientSide() && (this.isInGround() || this.isNoPhysics()) && this.shakeTime <= 0) {
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
		this.ammoDamage = damage;
	}

	public double getBaseDamage() {
		return this.ammoDamage;
	}

	public void setKnockback(int knockBack) {
		this.knockback = knockBack;
	}

	public int getKnockback() {
		return this.knockback;
	}
	
	public void setIgniteSeconds(int seconds) {
		this.igniteSeconds = seconds;
	}

	public int getIgniteSeconds() {
		return this.igniteSeconds;
	}
	
	@Override
	public boolean isAttackable() {
		return this.getType().is(EntityTypeTags.REDIRECTABLE_PROJECTILE);
	}

	public void setCritArrow(boolean flag) {
		this.setFlag(1, flag);
	}

	private void setPierceLevel(byte value) {
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

	public void setBaseDamageFromMob(float f) {
		this.setBaseDamage((double)(f * 2.0F) + this.random.triangle((double)this.level().getDifficulty().getId() * 0.11, 0.57425));
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
		return super.isPickable() && !this.isInGround();
	}

	@Override
	public SlotAccess getSlot(int i) {
		return i == 0 ? SlotAccess.of(this::getPickupItemStackOrigin, this::setPickupItemStack) : super.getSlot(i);
	}

	@Override
	protected boolean shouldBounceOnWorldBorder() {
		return true;
	}

	public static enum Pickup {
		DISALLOWED,
		ALLOWED,
		CREATIVE_ONLY;

		public static AbstractAmmo_Entity.Pickup byOrdinal(int i) {
			if (i < 0 || i > values().length) { i = 0; }
			return values()[i];
		}
	}
	
	/* Overwrite in each case. -> */
	public void hitProcess() { }
	
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
			for (int i = 0; i < 8; i++) {
				this.level().addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
			}
		}
	}
}
