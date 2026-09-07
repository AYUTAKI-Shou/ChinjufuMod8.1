package com.ayutaki.chinjufumod.entity;

import javax.annotation.Nullable;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractST_Entity extends ThrowableEntity_CM implements ItemSupplier {
	protected static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(AbstractST_Entity.class, EntityDataSerializers.ITEM_STACK);

	@Nullable
	private Entity lastDeflectedBy;
	
	public AbstractST_Entity(EntityType<? extends AbstractST_Entity> type, Level wroldIn) {
		super(type, wroldIn);
	}

	public AbstractST_Entity(EntityType<? extends AbstractST_Entity> type, double x, double y, double z, Level wroldIn, ItemStack stack) {
		super(type, x, y, z, wroldIn);
		//this.setItem(stack);
	}

	public AbstractST_Entity(EntityType<? extends AbstractST_Entity> type, LivingEntity thrower, Level wroldIn, ItemStack stack) {
		this(type, thrower.getX(), thrower.getEyeY() - 0.1F, thrower.getZ(), wroldIn, stack);
		this.setOwner(thrower);
	}

	/* Flying render 1.20->1.21.4 */
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entityS) {
		Entity entity = this.getOwner();
		return new ClientboundAddEntityPacket(this, entityS, entity == null ? 0 : entity.getId());
	}
	
	@Override
	public boolean isInWall() {
		return false;
	}
	
	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	} // for 20.6
	
	/* Drop item and Kill entity. */
	protected abstract ItemStack getItemStack();

	@Override
	public void tick() {
		super.tick();
		/* ThrowableProjectile */
		/** Go through and move forward. **/
		Vec3 vec3;
		vec3 = this.position().add(this.getDeltaMovement()); //for 1.21.4
		this.setPos(vec3);
		this.updateRotation();
		this.applyEffectsFromBlocks();
		
		HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
		if (hitresult.getType() != HitResult.Type.MISS && this.isAlive() && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult)) {
			this.hitTargetOrDeflectSelf(hitresult);
		}//neo
	}

	/* ThrowableProjectile */
	protected ProjectileDeflection hitTargetOrDeflectSelf(HitResult result) {
		if (result.getType() == HitResult.Type.ENTITY) {
			EntityHitResult entityHit = (EntityHitResult)result;
			Entity entity = entityHit.getEntity();
			ProjectileDeflection deflection = entity.deflection(this);
			if (deflection != ProjectileDeflection.NONE) {
				if (entity != this.lastDeflectedBy && this.deflect(deflection, entity, this.getOwner(), false)) {
					this.lastDeflectedBy = entity; }

				return deflection;
			}
		} 
		
		else if (this.shouldBounceOnWorldBorder() && result instanceof BlockHitResult blockHit && blockHit.isWorldBorderHit()) {
			ProjectileDeflection deflection1 = ProjectileDeflection.REVERSE;
			if (this.deflect(deflection1, null, this.getOwner(), false)) {
				this.setDeltaMovement(this.getDeltaMovement().scale(0.2));
				return deflection1; }
		}
		this.onHit_ST(result);
		return ProjectileDeflection.NONE;
	}

	protected boolean shouldBounceOnWorldBorder() {
		return false;
	}
	
	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		if (this.tickCount < 2 && distance < 12.25) { return false; }
		
		else {
			double d0 = this.getBoundingBox().getSize() * 4.0;
			if (Double.isNaN(d0)) { d0 = 4.0; }

			d0 *= 64.0;
			return distance < d0 * d0; }
	}

	@Override
	public boolean canUsePortal(boolean flag) {
		return true;
	}
	
	/* Collision processing. AbstractKK */
	protected void onHit_ST(HitResult result) {
		HitResult.Type hitresult$type = result.getType();
		if (hitresult$type == HitResult.Type.ENTITY) {
			EntityHitResult entityHit = (EntityHitResult)result;
			Entity entity = entityHit.getEntity();
			if (entity.getType().is(EntityTypeTags.REDIRECTABLE_PROJECTILE) && entity instanceof Projectile projectile) {
				projectile.deflect(ProjectileDeflection.AIM_DEFLECT, this.getOwner(), this.getOwner(), true); }

			this.onHitEntity_ST(entityHit);
			this.level().gameEvent(GameEvent.PROJECTILE_LAND, result.getLocation(), GameEvent.Context.of(this, null));
		} 
		
		else if (hitresult$type == HitResult.Type.BLOCK) {
			BlockHitResult blockHit = (BlockHitResult)result;
			this.onHitBlock_ST(blockHit);
			BlockPos pos = blockHit.getBlockPos();
			this.level().gameEvent(GameEvent.PROJECTILE_LAND, pos, GameEvent.Context.of(this, this.level().getBlockState(pos)));
		}
	}

	/** Collision to Entity. **/
	abstract protected void onHitEntity_ST(EntityHitResult result);

	/** Collision to Block. **/
	abstract protected void onHitBlock_ST(BlockHitResult result);

	/* Effect of water on Entity Speed. */
	@Override
	public boolean isInWater() {
		return this.wasTouchingWater;
	}

	/* DATA_ITEM_STACK || stack.tag*/
	public void setItem(ItemStack stack) {
		if (!stack.is(this.getDefaultItem())) {
			this.getEntityData().set(DATA_ITEM_STACK, Util.make(stack.copy(), (consumer) -> {
				consumer.setCount(1);
			}));
		}
	}
	
	protected abstract Item getDefaultItem();
	
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
		compound.put("Item", this.getItem().save(this.registryAccess()));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Item", 10)) {
			this.setItem(ItemStack.parse(this.registryAccess(), compound.getCompound("Item")).orElseGet(() -> new ItemStack(this.getDefaultItem())));
		} else {
			this.setItem(new ItemStack(this.getDefaultItem()));
		}
	}
}
