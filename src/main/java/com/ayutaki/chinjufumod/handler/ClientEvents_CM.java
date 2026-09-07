package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.items.armor.model.AkatsukiModel;
import com.ayutaki.chinjufumod.items.armor.model.BattleshipModel;
import com.ayutaki.chinjufumod.items.armor.model.GisouModel;
import com.ayutaki.chinjufumod.items.armor.model.I401_Model;
import com.ayutaki.chinjufumod.items.armor.model.IkkousenModel;
import com.ayutaki.chinjufumod.items.armor.model.Ise_Model;
import com.ayutaki.chinjufumod.items.armor.model.KasumiOuter;
import com.ayutaki.chinjufumod.items.armor.model.NagatoModel;
import com.ayutaki.chinjufumod.items.armor.model.RJModel;
import com.ayutaki.chinjufumod.items.armor.model.Ro500_Outer;
import com.ayutaki.chinjufumod.items.armor.model.Santa_Model;
import com.ayutaki.chinjufumod.items.armor.model.SendaiModel;
import com.ayutaki.chinjufumod.items.armor.model.SubmarineModel;
import com.ayutaki.chinjufumod.items.armor.model.ToneModel;
import com.ayutaki.chinjufumod.items.armor.model.UKIWA_Model;
import com.ayutaki.chinjufumod.items.armor.model.YUKATA_Model;
import com.ayutaki.chinjufumod.items.armor.model.YuraModel;
import com.ayutaki.chinjufumod.items.armor.model.ZuihouModel;
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
import com.ayutaki.chinjufumod.tileentity.render.BlackBoard_TERenderer;
import com.ayutaki.chinjufumod.tileentity.render.WoodBoard_TERenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents_CM {

	@SubscribeEvent
	public static void registerBlockEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(BlockEntity_CM.BLACKBOARD.get(), BlackBoard_TERenderer::new);
		event.registerBlockEntityRenderer(BlockEntity_CM.WOODBOARD.get(), WoodBoard_TERenderer::new);
	}

	@SubscribeEvent
	public static void registerFactories(ParticleFactoryRegisterEvent event) {
		ParticleEngine manager = Minecraft.getInstance().particleEngine;
		manager.register(ParticleTypes_CM.FALLSAKURA.get(), SakuraParticle.Provider::new);
		manager.register(ParticleTypes_CM.FALLKAEDE.get(), KaedeParticle.Provider::new);
		manager.register(ParticleTypes_CM.FALLICHOH.get(), IchohParticle.Provider::new);
		manager.register(ParticleTypes_CM.FALLKARE.get(), AutumnParticle.Provider::new);
		
		manager.register(ParticleTypes_CM.AMMO_PT.get(), Particle_Ammo.Provider::new);
		manager.register(ParticleTypes_CM.SHOOT_PT.get(), Particle_ShootK.Provider::new);
		manager.register(ParticleTypes_CM.SHOOTL_PT.get(), Particle_ShootL.Provider::new);
		manager.register(ParticleTypes_CM.SHOOTM_PT.get(), Particle_ShootM.Provider::new);
		manager.register(ParticleTypes_CM.MARK_PT.get(), Particle_Mark.Provider::new);
		
		manager.register(ParticleTypes_CM.CHALK_PT.get(), Chalk_Particle.Provider::new);
	}
	
	@SubscribeEvent
	public static void registerEntityRenderingHandler(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ArmorLayer_CM.GISOU_INNER, () -> LayerDefinition.create(GisouModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.GISOU_OUTER, () -> LayerDefinition.create(GisouModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.AKATSUKI_INNER, () -> LayerDefinition.create(AkatsukiModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.AKATSUKI_OUTER, () -> LayerDefinition.create(AkatsukiModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.KASUMI_OUTER, () -> LayerDefinition.create(KasumiOuter.createOuter(), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SENDAI_INNER, () -> LayerDefinition.create(SendaiModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SENDAI_OUTER, () -> LayerDefinition.create(SendaiModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YURA_INNER, () -> LayerDefinition.create(YuraModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YURA_OUTER, () -> LayerDefinition.create(YuraModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.TONE_INNER, () -> LayerDefinition.create(ToneModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.TONE_OUTER, () -> LayerDefinition.create(ToneModel.createOuter(), 64, 120));

		event.registerLayerDefinition(ArmorLayer_CM.RJ_INNER, () -> LayerDefinition.create(RJModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_OUTER, () -> LayerDefinition.create(RJModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ZUIHOU_INNER, () -> LayerDefinition.create(ZuihouModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ZUIHOU_OUTER, () -> LayerDefinition.create(ZuihouModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.IKKOU_INNER, () -> LayerDefinition.create(IkkousenModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.IKKOU_OUTER, () -> LayerDefinition.create(IkkousenModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_INNER, () -> LayerDefinition.create(RJModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RJ_OUTER, () -> LayerDefinition.create(RJModel.createOuter(), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SENKAN_INNER, () -> LayerDefinition.create(BattleshipModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SENKAN_OUTER, () -> LayerDefinition.create(BattleshipModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ISE_INNER, () -> LayerDefinition.create(Ise_Model.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.ISE_OUTER, () -> LayerDefinition.create(Ise_Model.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.NAGATO_INNER, () -> LayerDefinition.create(NagatoModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.NAGATO_OUTER, () -> LayerDefinition.create(NagatoModel.createOuter(), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.SUBMARINE_INNER, () -> LayerDefinition.create(SubmarineModel.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SUBMARINE_OUTER, () -> LayerDefinition.create(SubmarineModel.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.UKIWA_INNER, () -> LayerDefinition.create(UKIWA_Model.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.I401_INNER, () -> LayerDefinition.create(I401_Model.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.I401_OUTER, () -> LayerDefinition.create(I401_Model.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RO500_OUTER, () -> LayerDefinition.create(Ro500_Outer.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.RO500_OUTER_C, () -> LayerDefinition.create(UKIWA_Model.ro500_OuterC(), 64, 120));
		
		event.registerLayerDefinition(ArmorLayer_CM.YUKATA_INNER, () -> LayerDefinition.create(YUKATA_Model.createInner(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.YUKATA_OUTER, () -> LayerDefinition.create(YUKATA_Model.createOuter(), 64, 120));
		event.registerLayerDefinition(ArmorLayer_CM.SANTA_INNER, () -> LayerDefinition.create(Santa_Model.createInner(), 64, 32));
		event.registerLayerDefinition(ArmorLayer_CM.SANTA_OUTER, () -> LayerDefinition.create(Santa_Model.createOuter(), 64, 32));
	}
}
