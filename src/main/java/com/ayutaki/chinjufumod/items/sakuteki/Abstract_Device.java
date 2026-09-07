package com.ayutaki.chinjufumod.items.sakuteki;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.base.IG_Armor;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public abstract class Abstract_Device extends IG_Armor {
	protected final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(7);
	protected final TimeUnit milliS = TimeUnit.MILLISECONDS;
	
	public Abstract_Device(Item.Properties props) {
		super(props);
	}

	protected abstract boolean GISOU(Player playerIn);
	
	protected abstract float pitchSE();
	
	protected void coolDown(Level worldIn, Player playerIn) {
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.SONAR.get(), SoundSource.PLAYERS, 0.25F, this.pitchSE());
		playerIn.getCooldowns().addCooldown(this, 100);
	}

	protected abstract boolean waterCheck(LivingEntity entityLiving);
	
	protected boolean rangeInt(LivingEntity entityLiving, Player playerIn, int distance) {
		return entityLiving.isAlive() && !entityLiving.isRemoved() &&
				playerIn.position().closerThan(entityLiving.position(), distance + 1.0D);
	}
	
	protected boolean checkGlow(LivingEntity entityLiving) {
		return this.waterCheck(entityLiving) && !entityLiving.hasEffect(MobEffects.GLOWING);
	}
	
	protected MobEffectInstance glowTIME(int time){
		return new MobEffectInstance(MobEffects.GLOWING, time);
	}
	
	protected void GISOU_Not4(Level worldIn, Player playerIn) {
		CMEvents.soundError(worldIn, playerIn);
		playerIn.displayClientMessage(new TranslatableComponent("text.chinjufumod.gisou_not4"), true);
	}
}
