package com.ayutaki.chinjufumod.entity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.Config_CM;
import com.ayutaki.chinjufumod.entity.helper.Vector3;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
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
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractKK_Entity extends ThrowableEntity_CM implements ItemSupplier {
	protected static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(AbstractKK_Entity.class, EntityDataSerializers.ITEM_STACK);
	protected static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(AbstractKK_Entity.class, EntityDataSerializers.BYTE);
	public static final EntityDataAccessor<Boolean> RETURN_TO = SynchedEntityData.defineId(AbstractKK_Entity.class, EntityDataSerializers.BOOLEAN);

	@Nullable
	private Entity lastDeflectedBy;
	
	public AbstractKK_Entity(EntityType<? extends AbstractKK_Entity> type, Level wroldIn) {
		super(type, wroldIn);
	}

	public AbstractKK_Entity(EntityType<? extends AbstractKK_Entity> type, double x, double y, double z, Level wroldIn, ItemStack stack) {
		super(type, x, y, z, wroldIn);
		//this.setItem(stack);
	}

	public AbstractKK_Entity(EntityType<? extends AbstractKK_Entity> type, LivingEntity thrower, Level wroldIn, ItemStack stack) {
		this(type, thrower.getX(), thrower.getEyeY() - 0.1F, thrower.getZ(), wroldIn, stack);
		this.setOwner(thrower);
	}
	
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_ITEM_STACK, ItemStack.EMPTY);
		builder.define(ID_FLAGS, (byte)0);
		builder.define(RETURN_TO, false);
	}

	/* Flying render 1.20->1.21.4 */
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entityS) {
		Entity entity = this.getOwner();
		return new ClientboundAddEntityPacket(this, entityS, entity == null ? 0 : entity.getId());
	}
	
	@Override
	public boolean isInWall() {
		return true;
	}
	
	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	} // for 20.6
	
	/* Drop item and Kill entity. */
	protected abstract ItemStack getItemStack();

	protected void dropAndKill() {
		ItemStack hStack = getItemStack();
		
		Entity thrower = getOwner();
		Player playerIn = (Player)thrower;
		if (thrower != null && level() instanceof ServerLevel server && !playerIn.getAbilities().instabuild) { 
			hStack.hurtAndBreak(1, server, null, null); } //for 1.21.4
		
		ItemEntity hItem = new ItemEntity(this.level(), getX(), getY(), getZ(), hStack);
		this.level().addFreshEntity(hItem);
		discard();
	}
	
	@Override
	public void tick() {
		super.tick();
		this.setNoGravity(true);
		
		/* ThrowableProjectile */
		/** Go through and move forward. **/
		this.handleFirstTickBubbleColumn();
		this.applyGravity();
		this.applyInertia();
		Vec3 vec3;
		vec3 = this.position().add(this.getDeltaMovement()); //for 1.21.4
		this.setPos(vec3);
		this.updateRotation();
		this.applyEffectsFromBlocks();
		
		HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
		if (hitresult.getType() != HitResult.Type.MISS && this.isAlive() && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult)) {
			this.hitTargetOrDeflectSelf(hitresult);
		} //neo

		/* AbstractKK */
		/** PROPELLER Sound **/
		if (!Config_CM.INSTANCE.lowSound.get()) {
			if (this.tickCount % 3 == 0 ) { this.playSound(SoundEvents_CM.KK_PROPELLER.get(), 1.5F, 1.0F); } }
		
		if (!level().isClientSide()) {
			Entity thrower = getOwner();
			
			/** Return move **/
			if (isReturning()) {
				if (thrower == null && tickCount > 200) { dropAndKill(); }
				
				if (thrower != null) {
					Vec3 motion = Vector3.fromEntityCenter(thrower).subtract(Vector3.fromEntityCenter(this)).normalize();
					setDeltaMovement(motion);
					
					if (distanceToSqr(thrower) < 2) { dropAndKill(); } }
			}
			
			/** Flying move **/
			if (!isReturning()) {
				if (thrower == null && tickCount > 200) { dropAndKill(); }
				
				if (thrower != null && tickCount >= 60) { setReturning(true); }
			}
		}
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
		this.onHit_KK(result); //---> AbstractKK
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
	
	private void applyInertia() {
		Vec3 vec3 = this.getDeltaMovement();
		Vec3 vec31 = this.position();
		float f;
		if (this.isInWater()) {
			for (int i = 0; i < 4; i++) {
				//float f1 = 0.25F;
				this.level().addParticle(ParticleTypes.BUBBLE, vec31.x - vec3.x * 0.25, vec31.y - vec3.y * 0.25, vec31.z - vec3.z * 0.25, vec3.x, vec3.y, vec3.z); }
			f = 0.8F; 
		}
		
		else { f = 0.99F; }
		this.setDeltaMovement(vec3.scale((double)f));
	}

	private void handleFirstTickBubbleColumn() {
		if (this.firstTick) {
			for (BlockPos pos : BlockPos.betweenClosed(this.getBoundingBox())) {
				BlockState state = this.level().getBlockState(pos);
				if (state.is(Blocks.BUBBLE_COLUMN)) {
					state.entityInside(this.level(), pos, this); }
			}
		}
	}
	
	/* Collision processing. AbstractKK */
	protected void onHit_KK(HitResult result) {
		HitResult.Type hitresult$type = result.getType();
		if (hitresult$type == HitResult.Type.ENTITY) {
			EntityHitResult entityHit = (EntityHitResult)result;
			Entity entity = entityHit.getEntity();
			if (entity.getType().is(EntityTypeTags.REDIRECTABLE_PROJECTILE) && entity instanceof Projectile projectile) {
				projectile.deflect(ProjectileDeflection.AIM_DEFLECT, this.getOwner(), this.getOwner(), true); }

			this.onHitEntity_KK(entityHit);
			this.level().gameEvent(GameEvent.PROJECTILE_LAND, result.getLocation(), GameEvent.Context.of(this, null));
		} 
		
		else if (hitresult$type == HitResult.Type.BLOCK) {
			BlockHitResult blockHit = (BlockHitResult)result;
			this.onHitBlock_KK(blockHit);
			BlockPos pos = blockHit.getBlockPos();
			this.level().gameEvent(GameEvent.PROJECTILE_LAND, pos, GameEvent.Context.of(this, this.level().getBlockState(pos)));
		}
	}

	/** Collision to Entity. **/
	protected abstract void onHitEntity_KK(EntityHitResult result);
	
	public abstract double getBaseDamage();

	public abstract void setBaseDamage(double damage);
	
	/** Luck **/
	protected float playerLuck() {
		Player playerIn = this.getPlayerOwner();
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}
	
	protected int playerLevel() {
		Player playerIn = this.getPlayerOwner();
		return (playerIn != null)? playerIn.experienceLevel : 0;
	}
	
	protected double addLEVEL() {
		int pLevel = this.playerLevel();
		return (pLevel >= 25)? 3.0D : ((pLevel >= 19 && pLevel < 25)? 2.0D : ((pLevel >= 12 && pLevel < 19)? 1.0D : 0.0D));
	}
	
	@Nullable
	protected Player getPlayerOwner() {
		Entity thrower = this.getOwner();
		return thrower instanceof Player ? (Player)thrower : null;
	}
	
	/** Attack Entity **/
	@SuppressWarnings("deprecation")
	protected void redBAKUGEKI(EntityHitResult result) {
		double FIT = this.isCarrier()? 2.5D : 0.0D;
		double dealDamage = this.getBaseDamage() + FIT + this.addLEVEL();

		boolean LUCK = this.playerLuck() > 0.0F;
		int A = LUCK? 2 : 4;
		int B = LUCK? 4 : 8;
		
		float criticalA = (this.random.nextInt(A) == 0)? 1.5F : 1.0F;
		float criticalB = (this.random.nextInt(B) == 0)? 1.5F : 1.0F;
		float criKANBAKU = this.isCarrier()? criticalA : criticalB;
		
		result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), (float)dealDamage * criKANBAKU);
		boolean sound = (criKANBAKU > 1.0F);
		this.playSound(sound? SoundEvents_CM.KK_ATACK2.get() : SoundEvents_CM.KK_ATACK.get(), 2.0F, 1.0F);
	}
	
	@SuppressWarnings("deprecation")
	protected void blueKOUGEKI(EntityHitResult result, float capA, float capB) {
		boolean LUCK = this.playerLuck() > 0.0F;
		
		if (this.isCarrier()) {
			double dealDamage = this.getBaseDamage() + 2.5D + this.addLEVEL();
			float addKANKOU = (this.random.nextInt(1) == 0)? 1.5F : 0.8F;
			
			int A = LUCK? 2 : 4;
			float critical = (this.random.nextInt(A) == 0)? 1.5F : 1.0F;
			float total = (float)dealDamage * addKANKOU * critical;
			float cap = (float)dealDamage + capA;
			
			result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), (total > cap)? cap : total);
			boolean sound = (addKANKOU > 1.0F) && (critical > 1.0F);
			this.playSound(sound? SoundEvents_CM.KK_ATACK2.get() : SoundEvents_CM.KK_ATACK.get(), 2.0F, 1.0F); }
		
		else { //!Carrier
			double dealDamage = this.getBaseDamage() + this.addLEVEL();
			float addKANKOU = (this.random.nextInt(1) == 0)? 1.5F : 0.8F;
			
			int B = LUCK? 4 : 8;
			float critical = (this.random.nextInt(B) == 0)? 1.5F : 1.0F;
			float total = (float)dealDamage * addKANKOU * critical;
			float cap = (float)dealDamage + capB;
			
			result.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), (total > cap)? cap : total);
			boolean sound = (addKANKOU > 1.0F) && (critical > 1.0F);
			this.playSound(sound? SoundEvents_CM.KK_ATACK2.get() : SoundEvents_CM.KK_ATACK.get(), 2.0F, 1.0F); }
	}
	
	/** Collision to Block. **/
	protected void onHitBlock_KK(BlockHitResult result) { 
		BlockState state = level().getBlockState(result.getBlockPos());
		Block block = state.getBlock();
		/** Go through the blocks. **/
		if (block instanceof Block) { return; }
	}

	@Override
	protected double getDefaultGravity() {
		return 0.0D;
	}

	/* Effect of water on Entity Speed. */
	@Override
	public boolean isInWater() {
		return false;
	}

	/* RETURN_TO */
	public boolean isReturning() {
		return this.entityData.get(RETURN_TO);
	}

	protected void setReturning(boolean flag) {
		this.entityData.set(RETURN_TO, flag);
	}

	/* Carrier */
	public void setCarrier(boolean flag) {
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
	
	public boolean isCarrier() {
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
		compound.putBoolean("isReturning", this.isReturning());
		compound.put("Item", this.getItem().save(this.registryAccess()));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setReturning(compound.getBoolean("isReturning"));

		if (compound.contains("Item", 10)) {
			this.setItem(ItemStack.parse(this.registryAccess(), compound.getCompound("Item")).orElseGet(() -> new ItemStack(this.getDefaultItem())));
		} else {
			this.setItem(new ItemStack(this.getDefaultItem()));
		}
	}
}
