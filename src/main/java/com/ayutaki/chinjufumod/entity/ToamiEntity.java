package com.ayutaki.chinjufumod.entity;

import javax.annotation.Nonnull;

import com.ayutaki.chinjufumod.handler.EntityTypes_CM;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ToamiEntity extends ThrowableProjectile implements ItemSupplier {
	
	private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(ToamiEntity.class, EntityDataSerializers.ITEM_STACK);
	private static final EntityDataAccessor<Integer> RETURN_TO = SynchedEntityData.defineId(ToamiEntity.class, EntityDataSerializers.INT);
	private ItemStack projectile = new ItemStack(Items_Teatime.TOAMI.get());

	public ToamiEntity(EntityType<ToamiEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public ToamiEntity(LivingEntity entityIn, Level world, ItemStack stack) {
		super(EntityTypes_CM.TOAMI.get(), entityIn, world);
		this.projectile = stack.copy();
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_ITEM_STACK, ItemStack.EMPTY);
		builder.define(RETURN_TO, Integer.valueOf(-1));
	} // for 20.6

	/* Flying render 1.18->1.20 */
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	} // for 20.2

	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	} // for 20.6

	@Override
	public void tick() {
		super.tick();

		if (!this.level().isClientSide()) {
			Entity thrower = getOwner();
			
			if (isReturning()) { 
				if (thrower == null && tickCount > 60) { dropAndKill(); }
				
				if (thrower != null) { returnAndKill(); } }
			
			if (!isReturning()) {
				if (thrower == null && tickCount > 60) { dropAndKill(); }
				
				if (thrower != null && tickCount >= 15) { setEntityToReturnTo(getEntityToReturnTo() + 1); } }
		}
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
	
	/* Drop item and Kill entity. */
	private void returnAndKill() {
		ItemStack hStack = getItemStack();
		
		Entity thrower = getOwner();
		Player playerIn = (Player)thrower;
		if (thrower != null && !playerIn.getAbilities().instabuild) { hStack.hurtAndBreak(1, random, null, null); } //for 1.20.6
		
		ItemEntity hItem = new ItemEntity(this.level(), thrower.getX(), thrower.getY(), thrower.getZ(), hStack);
		this.level().addFreshEntity(hItem);
		discard();
	}

	private void dropAndKill() {
		ItemStack hStack = getItemStack();
		
		Entity thrower = getOwner();
		Player playerIn = (Player)thrower;
		if (thrower != null && !playerIn.getAbilities().instabuild) { hStack.hurtAndBreak(1, random, null, null); }
		
		ItemEntity hItem = new ItemEntity(this.level(), getX(), getY(), getZ(), hStack);
		this.level().addFreshEntity(hItem);
		discard();
	}

	/* Reflects durability value with "stack.copy()". */
	private ItemStack getItemStack() {
		return projectile.copy();
	}
	
	/* Collision processing. */
	@Override
	protected void onHit(HitResult result) {
		HitResult.Type hitresult$type = result.getType();
		if (hitresult$type == HitResult.Type.ENTITY) {
			this.onHitEntity((EntityHitResult)result); }
		
		else if (hitresult$type == HitResult.Type.BLOCK) {
			this.onHitBlock((BlockHitResult)result); }
	}
	
	/** Collision to Entity. **/
	@Override
	protected void onHitEntity(@Nonnull EntityHitResult result) {
		super.onHitEntity(result);
		Entity thrower = getOwner();
		
		if (isReturning()) { return; }
		
		if (!isReturning()) { 
			if (!this.level().isClientSide() && result.getEntity() instanceof LivingEntity) {
				if (result.getEntity() instanceof Cod) {
					result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
					result.getEntity().discard();
					thrower.spawnAtLocation(new ItemStack(Items.COD)); }
				
				if (result.getEntity() instanceof Salmon) {
					result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
					result.getEntity().discard();
					thrower.spawnAtLocation(new ItemStack(Items.SALMON)); }
				
				if (result.getEntity() instanceof TropicalFish) {
					result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
					result.getEntity().discard();
					thrower.spawnAtLocation(new ItemStack(Items.TROPICAL_FISH)); }
				
				if (result.getEntity() instanceof Pufferfish) {
					result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
					result.getEntity().discard();
					thrower.spawnAtLocation(new ItemStack(Items.PUFFERFISH));
					((LivingEntity) thrower).addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0)); }
				
				if (result.getEntity() instanceof Squid) {
					result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
					result.getEntity().discard();
					thrower.spawnAtLocation(new ItemStack(Items_Teatime.IKA.get())); }
				
				if (!(result.getEntity() instanceof Cod) && !(result.getEntity() instanceof Salmon) &&
						!(result.getEntity() instanceof TropicalFish) && !(result.getEntity() instanceof Pufferfish) &&
						!(result.getEntity() instanceof Squid)) {
					
					result.getEntity().playSound(SoundEvents.GENERIC_HURT, 2.0F, 1.0F);
					((LivingEntity) result.getEntity()).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 1));
					setEntityToReturnTo(getEntityToReturnTo() + 1); }
			}
		}
	}
	
	/** Collision to Block. **/
	@Override
	protected void onHitBlock(@Nonnull BlockHitResult result) {
		super.onHitBlock(result);
		BlockState state = this.level().getBlockState(result.getBlockPos());
		Block block = state.getBlock();
		if (state.canBeReplaced() || block == Blocks.KELP ||
				block == Crop_Blocks.SHIKAKE_AMI.get() || block == Crop_Blocks.YOUSHOKU_AMI.get()) { 
			return; }

		if (!state.canBeReplaced() && block != Blocks.KELP &&
				block != Crop_Blocks.SHIKAKE_AMI.get() && block != Crop_Blocks.YOUSHOKU_AMI.get()) { 
			//test sound... level().playSound(null, result.getBlockPos(), SoundEvents.STONE_BREAK, SoundCategory.BLOCKS, 3.0F, 0.8F);
			setEntityToReturnTo(getEntityToReturnTo() + 1); }
	}

	@Override
	protected double getDefaultGravity() {
		return 0.0D;
	} //for 1.20.6
	
	/* Effect of water on Entity Speed. */
	@Override
	public boolean isInWater() {
		return false;
	}

	private boolean isReturning() {
		return getEntityToReturnTo() > -1;
	}

	private int getEntityToReturnTo() {
		return entityData.get(RETURN_TO).intValue();
	}

	private void setEntityToReturnTo(int entityID) {
		entityData.set(RETURN_TO, Integer.valueOf(entityID));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		ItemStack stack = this.getItemRaw();
		if (!stack.isEmpty()) {
			compound.put("Item", this.getItem().save(this.registryAccess())); } //for 1.20.6
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Item", 10)) {
			this.setItem(ItemStack.parse(this.registryAccess(), compound.getCompound("Item"))
					.orElseGet(() -> new ItemStack(this.getDefaultItem()))); }
		
		else { this.setItem(new ItemStack(this.getDefaultItem())); }
	}
}
