package com.ayutaki.chinjufumod.entity;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class KK_SwordfishEntity extends AbstractKK_Entity {

	private ItemStack projectile = new ItemStack(Items_Weapon.SWORDFISH);
	private double baseDamage = 5.0D; /** Swordfish 火力 +2, 雷装 +3 **/
	
	public KK_SwordfishEntity(World worldIn) {
		super(worldIn);
	}

	public KK_SwordfishEntity(World worldIn, EntityLivingBase entity, ItemStack stack) {
		super(worldIn, entity, stack);
		this.projectile = stack.copy();
	}

	@Override
	protected void onHitEntity(RayTraceResult result) { 
		if (!world.isRemote && result.entityHit != null && result.entityHit instanceof EntityLivingBase && result.entityHit != getThrower()) {

			/** Do not attack Friendly Mobs. **/
			boolean friendly = (result.entityHit instanceof EntityVillager || result.entityHit instanceof EntityHorse || 
					result.entityHit instanceof EntityTameable || result.entityHit instanceof EntityIronGolem);
			
			if (friendly) {
				playSound(SoundEvents_CM.KK_STOP, 2.0F, 1.0F);
				dropAndKill(); }

			else { this.blueKOUGEKI(result, 9.5F, 4.0F); }
		}
	}

	protected ItemStack getItemStack() {
		return this.projectile.copy();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setDouble("damage", this.baseDamage);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		if (compound.hasKey("damage", 99)) {
			this.baseDamage = compound.getDouble("damage"); }
	}

	public void setBaseDamage(double damage) {
		this.baseDamage = damage;
	}
	
	public double getBaseDamage() {
		return this.baseDamage;
	}
}
