package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.particle.AutumnParticle;
import com.ayutaki.chinjufumod.particle.Chalk_Particle;
import com.ayutaki.chinjufumod.particle.IchohParticle;
import com.ayutaki.chinjufumod.particle.KaedeParticle;
import com.ayutaki.chinjufumod.particle.Particle_Ammo;
import com.ayutaki.chinjufumod.particle.Particle_Mark;
import com.ayutaki.chinjufumod.particle.Particle_ShootK;
import com.ayutaki.chinjufumod.particle.Particle_ShootL;
import com.ayutaki.chinjufumod.particle.Particle_ShootM;
import com.ayutaki.chinjufumod.particle.SakuraParticle;
import com.ayutaki.chinjufumod.particle.Particle_Wax;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleManager_CM {

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void registerFactories(ParticleFactoryRegisterEvent event) {
		Minecraft mClient = Minecraft.getInstance();
		ParticleManager manager = mClient.particles;
		
		manager.registerFactory(ParticleTypes_CM.FALLSAKURA, SakuraParticle.Factory::new);
		manager.registerFactory(ParticleTypes_CM.FALLKAEDE, KaedeParticle.Factory::new);
		manager.registerFactory(ParticleTypes_CM.FALLICHOH, IchohParticle.Factory::new);
		manager.registerFactory(ParticleTypes_CM.FALLKARE, AutumnParticle.Factory::new);
		
		manager.registerFactory(ParticleTypes_CM.AMMO_PT, Particle_Ammo.Factory::new);
		manager.registerFactory(ParticleTypes_CM.SHOOT_PT, Particle_ShootK.Factory::new);
		manager.registerFactory(ParticleTypes_CM.SHOOTL_PT, Particle_ShootL.Factory::new);
		manager.registerFactory(ParticleTypes_CM.SHOOTM_PT, Particle_ShootM.Factory::new);
		manager.registerFactory(ParticleTypes_CM.MARK_PT, Particle_Mark.Factory::new);
		
		manager.registerFactory(ParticleTypes_CM.CHALK_PT, Chalk_Particle.Factory::new);
		manager.registerFactory(ParticleTypes_CM.WAX_PT, Particle_Wax.Factory::new);
	}
}
