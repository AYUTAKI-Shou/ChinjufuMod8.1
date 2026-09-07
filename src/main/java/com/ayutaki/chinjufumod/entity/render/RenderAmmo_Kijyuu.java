package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.AmmoEntity_Kijyuu;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderAmmo_Kijyuu extends AmmoRenderer_Kiyuu<AmmoEntity_Kijyuu, ArrowRenderState> {
	
	public RenderAmmo_Kijyuu(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(ArrowRenderState entityIn) {
		return null;
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}
}
