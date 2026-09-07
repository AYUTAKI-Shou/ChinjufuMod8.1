package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleTypes_CM {

	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, ChinjufuMod.MOD_ID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLSAKURA = register("fallsakura");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLKAEDE = register("fallkaede");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLICHOH = register("fallichoh");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLKARE = register("fallkare");
	
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> AMMO_PT = register("ammo_pt");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHOOT_PT = register("shoot_pt");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHOOTL_PT = register("shoot_pt_l");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHOOTM_PT = register("shoot_pt_m");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MARK_PT = register("mark_pt");
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CHALK_PT = register("chalk_pt");

	///* Register *///
	public static DeferredHolder<ParticleType<?>, SimpleParticleType> register(String name) {
		return PARTICLE_TYPES.register(name, () -> new SimpleParticleType(false));
	}
}
