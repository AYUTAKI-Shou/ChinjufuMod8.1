package com.ayutaki.chinjufumod.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public abstract class TileEntity_Tickable extends TileEntity implements ITickable {

	public static int TICK_RATE = 2;
	public static int LASER_RATE = 2;
	private boolean needsUpdate;
	private byte ticker;
	
	public abstract void tickLogic();
	public abstract void tickAction(boolean client);
	
	@Override
	public void update() {
		if(!this.world.isRemote && this.ticker++ % TICK_RATE == 0) {
			this.tickLogic();
			this.ticker = 0;
		}
		
		if(this.world.getWorldInfo().getWorldTotalTime() % LASER_RATE == 0)
			this.tickAction(this.world.isRemote);
	}
	
	public boolean requiresUpdate() {
		return this.needsUpdate;
	}
	
	public void setUpdateRequired() {
		this.needsUpdate = true;
	}
	
	public void setUpdateComplete() {
		this.needsUpdate = false;
	}
}
