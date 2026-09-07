package com.ayutaki.chinjufumod.items.sakuteki;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.base.IG_Armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public abstract class Abstract_Device extends IG_Armor {
	protected final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(7);
	protected final TimeUnit milliS = TimeUnit.MILLISECONDS;
	
	public Abstract_Device(Item.Properties props) {
		super(props);
	}

	protected abstract boolean GISOU(PlayerEntity playerIn);

	protected abstract float pitchSE();
	
	protected void coolDown(World worldIn, PlayerEntity playerIn) {
		worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents_CM.SONAR, SoundCategory.PLAYERS, 0.25F, this.pitchSE());
		playerIn.getCooldowns().addCooldown(this, 100);
	}

	protected abstract boolean waterCheck(LivingEntity entityLiving);
	
	@SuppressWarnings("deprecation")
	protected boolean rangeInt(LivingEntity entityLiving, PlayerEntity playerIn, int distance) {
		return entityLiving.isAlive() && !entityLiving.removed &&
				playerIn.position().closerThan(entityLiving.position(), distance + 1.0D);
	}
	
	protected boolean checkGlow(LivingEntity entityLiving) {
		return this.waterCheck(entityLiving) && !entityLiving.hasEffect(Effects.GLOWING);
	}
	
	protected EffectInstance glowTIME(int time){
		return new EffectInstance(Effects.GLOWING, time);
	}
	
	protected void GISOU_Not4(World worldIn, PlayerEntity playerIn) {
		CMEvents.soundError(worldIn, playerIn);
		playerIn.displayClientMessage(new TranslationTextComponent("text.chinjufumod.gisou_not4"), true);
	}
}
