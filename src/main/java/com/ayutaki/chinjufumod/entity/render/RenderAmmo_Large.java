package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.entity.AmmoEntity_Large;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderAmmo_Large extends AmmoRenderer<AmmoEntity_Large, ArrowRenderState> {
	
	public RenderAmmo_Large(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(ArrowRenderState entityIn) {
		return ResourceLocation.fromNamespaceAndPath(ChinjufuMod.MOD_ID, "textures/entity/projectiles/ammo_large.png");
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}
}
