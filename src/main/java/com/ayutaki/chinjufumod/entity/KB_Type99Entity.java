package com.ayutaki.chinjufumod.entity;

import com.ayutaki.chinjufumod.handler.EntityTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.horse.Donkey;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class KB_Type99Entity extends AbstractKK_Entity {

	private ItemStack projectile = new ItemStack(Items_Weapon.TYPE99.get());
	private double baseDamage = 5.0D; /** 99式 爆装+5 **/
	
	public KB_Type99Entity(EntityType<KB_Type99Entity> type, Level worldIn) {
		super(type, worldIn);
	}

	public KB_Type99Entity(LivingEntity entityIn, Level world, ItemStack stack) {
		super(EntityTypes_CM.TYPE99.get(), entityIn, world, stack);
		this.projectile = stack.copy();
	}

	public KB_Type99Entity(Level wroldIn, double x, double y, double z, ItemStack stack) {
		super(EntityTypes_CM.TYPE99.get(), x, y, z, wroldIn, stack);
	}

	protected Item getDefaultItem() {
		return this.projectile.getItem();
	}

	/** Collision to Entity. **/
	@Override
	protected void onHitEntity_KK(EntityHitResult result) {
		Entity thrower = getOwner();
		
		if (!level().isClientSide() && result.getEntity() instanceof LivingEntity && result.getEntity() != thrower) {
			/** Do not attack Friendly Mobs. **/
			boolean friendly = (result.getEntity() instanceof Villager || result.getEntity() instanceof Horse || 
					result.getEntity() instanceof Donkey || result.getEntity() instanceof TamableAnimal ||
					result.getEntity() instanceof IronGolem);
			
			if (friendly) {
				playSound(SoundEvents_CM.KK_STOP.get(), 2.0F, 1.0F);
				dropAndKill(); }

			else { this.redBAKUGEKI(result); }
		}
	}

	/* Reflects durability value with "stack.copy()". */
	protected ItemStack getItemStack() {
		return projectile.copy();
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putDouble("damage", this.baseDamage);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("damage", 99)) {
			this.baseDamage = compound.getDouble("damage"); }
	}
	
	public void setBaseDamage(double damage) {
		this.baseDamage = damage;
	}
	
	public double getBaseDamage() {
		return this.baseDamage;
	}
}
