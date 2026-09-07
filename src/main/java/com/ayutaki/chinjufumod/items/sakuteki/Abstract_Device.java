package com.ayutaki.chinjufumod.items.sakuteki;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.addtab.IR_Armor;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public abstract class Abstract_Device extends IR_Armor {
	protected final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(7);
	protected final TimeUnit milliS = TimeUnit.MILLISECONDS;
	
	public Abstract_Device(String name) {
		super(name);
		setUnlocalizedName(name);
		setMaxStackSize(1);
	}

	protected abstract boolean GISOU(EntityPlayer playerIn);
	
	protected abstract float pitchSE();
	
	protected void coolDown(World worldIn, EntityPlayer playerIn) {
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.SONAR, SoundCategory.PLAYERS, 0.25F, this.pitchSE());
		playerIn.getCooldownTracker().setCooldown(this, 100);
	}

	protected abstract boolean waterCheck(EntityLiving entityLiving);

	protected boolean rangeInt(EntityLiving entityLiving, EntityPlayer playerIn, int distance) {
		World worldIn = playerIn.world;
		double x = playerIn.posX;
		double y = playerIn.posY;
		double z = playerIn.posZ;
		return entityLiving.isEntityAlive() && !entityLiving.isDead &&
				worldIn.isAnyPlayerWithinRangeAt(x, y, z, distance + 1.0D);
	}
	
	protected boolean checkGlow(EntityLiving entityLiving) {
		return this.waterCheck(entityLiving) && !entityLiving.isPotionActive(MobEffects.GLOWING);
	}
	
	protected PotionEffect glowTIME(int time){
		return new PotionEffect(MobEffects.GLOWING, time);
	}
	
	protected void GISOU_Not4(World worldIn, EntityPlayer playerIn) {
		CMEvents.soundError(worldIn, playerIn);
		playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.gisou_not4.name", new Object[0]), true);
	}
}
