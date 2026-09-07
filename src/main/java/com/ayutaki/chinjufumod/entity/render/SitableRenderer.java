package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.entity.render.state.SitableState;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SitableRenderer extends EntityRenderer<SitableEntity, SitableState> {
	
	public SitableRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	public ResourceLocation getTextureLocation(SitableState seatEntity) {
		return null;
	}

	@Override
	protected void renderNameTag(SitableState entity, Component component, PoseStack stack, MultiBufferSource source, int light) { } //for 1.20.6

	@Override
	public SitableState createRenderState() {
		return new SitableState();
	}
}
