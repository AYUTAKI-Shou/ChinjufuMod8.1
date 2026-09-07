package com.ayutaki.chinjufumod.entity;

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
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
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

public class ToamiEntity extends AbstractST_Entity implements ItemSupplier {

	public static final EntityDataAccessor<Boolean> RETURN_TO = SynchedEntityData.defineId(ToamiEntity.class, EntityDataSerializers.BOOLEAN);
	private ItemStack projectile = new ItemStack(Items_Teatime.TOAMI.get());

	public ToamiEntity(EntityType<ToamiEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public ToamiEntity(LivingEntity entityIn, Level world, ItemStack stack) {
		super(EntityTypes_CM.TOAMI.get(), entityIn, world, stack);
		this.projectile = stack.copy();
	}

	public ToamiEntity(Level wroldIn, double x, double y, double z, ItemStack stack) {
		super(EntityTypes_CM.TOAMI.get(), x, y, z, wroldIn, stack);
	}
	
	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_ITEM_STACK, ItemStack.EMPTY);
		builder.define(RETURN_TO, false);
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

		if (!this.level().isClientSide()) {
			Entity thrower = getOwner();
			
			if (isReturning()) { 
				if (thrower == null && tickCount > 60) { dropAndKill(); }
				
				if (thrower != null) { returnAndKill(); } }
			
			if (!isReturning()) {
				if (thrower == null && tickCount > 60) { dropAndKill(); }
				
				if (thrower != null && tickCount >= 15) { setReturning(true); } }
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
		if (thrower != null && level() instanceof ServerLevel server && !playerIn.getAbilities().instabuild) { 
			hStack.hurtAndBreak(1, server, null, null); } //for 1.21.4
		
		ItemEntity hItem = new ItemEntity(this.level(), thrower.getX(), thrower.getY(), thrower.getZ(), hStack);
		this.level().addFreshEntity(hItem);
		discard();
	}

	private void dropAndKill() {
		ItemStack hStack = getItemStack();
		
		Entity thrower = getOwner();
		Player playerIn = (Player)thrower;
		if (thrower != null && level() instanceof ServerLevel server && !playerIn.getAbilities().instabuild) { 
			hStack.hurtAndBreak(1, server, null, null); } //for 1.21.4
		
		ItemEntity hItem = new ItemEntity(this.level(), getX(), getY(), getZ(), hStack);
		this.level().addFreshEntity(hItem);
		discard();
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
			Entity thrower = getOwner();
			
			if (isReturning()) { return; }
			
			if (!isReturning()) { 
				if (!this.level().isClientSide() && result.getEntity() instanceof LivingEntity) {
					if (result.getEntity() instanceof Cod) {
						result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
						result.getEntity().discard();
						thrower.spawnAtLocation(server, new ItemStack(Items.COD)); }
					
					if (result.getEntity() instanceof Salmon) {
						result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
						result.getEntity().discard();
						thrower.spawnAtLocation(server, new ItemStack(Items.SALMON)); }
					
					if (result.getEntity() instanceof TropicalFish) {
						result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
						result.getEntity().discard();
						thrower.spawnAtLocation(server, new ItemStack(Items.TROPICAL_FISH)); }
					
					if (result.getEntity() instanceof Pufferfish) {
						result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
						result.getEntity().discard();
						thrower.spawnAtLocation(server, new ItemStack(Items.PUFFERFISH));
						((LivingEntity) thrower).addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0)); }
					
					if (result.getEntity() instanceof Squid) {
						result.getEntity().playSound(SoundEvents.BUCKET_FILL_FISH, 2.0F, 1.0F);
						result.getEntity().discard();
						thrower.spawnAtLocation(server, new ItemStack(Items_Teatime.IKA.get())); }
					
					if (!(result.getEntity() instanceof Cod) && !(result.getEntity() instanceof Salmon) &&
							!(result.getEntity() instanceof TropicalFish) && !(result.getEntity() instanceof Pufferfish) &&
							!(result.getEntity() instanceof Squid)) {
						
						result.getEntity().playSound(SoundEvents.GENERIC_HURT, 2.0F, 1.0F);
						((LivingEntity) result.getEntity()).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 1));
						setReturning(true); }
				}
			}
		}
	}
	
	/** Collision to Block. **/
	@Override
	protected void onHitBlock_ST(BlockHitResult result) {
		BlockState state = level().getBlockState(result.getBlockPos());
		Block block = state.getBlock();
		if (state.canBeReplaced() || block == Blocks.KELP ||
				block == Crop_Blocks.SHIKAKE_AMI.get() || block == Crop_Blocks.YOUSHOKU_AMI.get()) { 
			return; }

		if (!state.canBeReplaced() && block != Blocks.KELP &&
				block != Crop_Blocks.SHIKAKE_AMI.get() && block != Crop_Blocks.YOUSHOKU_AMI.get()) { 
			//test sound... level().playSound(null, result.getBlockPos(), SoundEvents.STONE_BREAK, SoundCategory.BLOCKS, 3.0F, 0.8F);
			setReturning(true); }
	}
	
	@Override
	protected double getDefaultGravity() {
		return 0.0D;
	}

	public final double getGravity_ST() {
		return this.getDefaultGravity();
	}

	protected void applyGravity_ST() {
		double d0 = this.getGravity_ST();
		if (d0 != 0.0) {
			this.setDeltaMovement(this.getDeltaMovement().add(0.0, -d0, 0.0));
		}
	}

	public boolean isReturning() {
		return this.entityData.get(RETURN_TO);
	}

	protected void setReturning(boolean flag) {
		this.entityData.set(RETURN_TO, flag);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("isReturning", this.isReturning());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setReturning(compound.getBoolean("isReturning"));
	}
}
