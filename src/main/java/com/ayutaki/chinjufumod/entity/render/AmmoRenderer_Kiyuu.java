package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Kijyuu;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AmmoRenderer_Kiyuu<T extends AbstractAmmo_Kijyuu, S extends ArrowRenderState> extends EntityRenderer<T, S> {

	public AmmoRenderer_Kiyuu(EntityRendererProvider.Context context) {
		super(context);
	}

	public void render(S stateIn, PoseStack stackIn, MultiBufferSource bufferIn, int packedLightIn) { }

	protected abstract ResourceLocation getTextureLocation(S stateIn);

	public void extractRenderState(T entityIn, S stateIn, float f) { }
}
