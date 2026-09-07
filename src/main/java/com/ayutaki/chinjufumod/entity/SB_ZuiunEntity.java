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

public class SB_ZuiunEntity extends AbstractKK_Entity {

	private ItemStack projectile = new ItemStack(Items_Weapon.ZUIUN);
	private double baseDamage = 7.0D; /** 瑞雲12型 爆装+7 **/
	
	public SB_ZuiunEntity(World worldIn) {
		super(worldIn);
	}

	public SB_ZuiunEntity(World worldIn, EntityLivingBase entity, ItemStack stack) {
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
				this.playSound(SoundEvents_CM.KK_STOP, 2.0F, 1.0F);
				dropAndKill(); }

			else { this.redBAKUGEKI(result); }
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
