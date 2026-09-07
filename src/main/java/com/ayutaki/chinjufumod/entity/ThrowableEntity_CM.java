package com.ayutaki.chinjufumod.entity;

import javax.annotation.Nullable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

public abstract class ThrowableEntity_CM extends Projectile implements TraceableEntity {
	@Nullable
	private Entity cachedOwner;
	private boolean leftOwner;
	private boolean hasBeenShot;

	protected ThrowableEntity_CM(EntityType<? extends ThrowableEntity_CM> type, Level worldIn) {
		super(type, worldIn);
	}

	protected ThrowableEntity_CM(EntityType<? extends ThrowableEntity_CM> type, double x, double y, double z, Level worldIn) {
		this(type, worldIn);
		this.setPos(x, y, z);
	}

	@Override
	public void tick() {
		/* Projectile */
		if (!this.hasBeenShot) {
			this.gameEvent(GameEvent.PROJECTILE_SHOOT, this.getOwner());
			this.hasBeenShot = true;
		}

		if (!this.leftOwner) {
			this.leftOwner = this.checkLeftOwner();
		}
	}

	private boolean checkLeftOwner() {
		Entity thrower = this.getOwner();
		if (thrower != null) {
			AABB aabb = this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0);
			return thrower.getRootVehicle().getSelfAndPassengers().filter(EntitySelector.CAN_BE_PICKED)
					.noneMatch(predi -> aabb.intersects(predi.getBoundingBox())); } 
		
		else { return true; }
	}
}
