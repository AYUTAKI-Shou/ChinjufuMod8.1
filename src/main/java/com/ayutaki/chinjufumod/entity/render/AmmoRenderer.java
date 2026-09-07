package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.ArrowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AmmoRenderer<T extends AbstractAmmo_Entity, S extends ArrowRenderState> extends EntityRenderer<T, S> {
	private final ArrowModel model;

	public AmmoRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new ArrowModel(context.bakeLayer(ModelLayers.ARROW));
	}

	public void render(S stateIn, PoseStack stackIn, MultiBufferSource bufferIn, int packedLightIn) {
		stackIn.pushPose();
		stackIn.mulPose(Axis.YP.rotationDegrees(stateIn.yRot - 90.0F));
		stackIn.mulPose(Axis.ZP.rotationDegrees(stateIn.xRot));
		VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(stateIn)));
		this.model.setupAnim(stateIn);
		this.model.renderToBuffer(stackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY);
		stackIn.popPose();
		super.render(stateIn, stackIn, bufferIn, packedLightIn);
	}

	protected abstract ResourceLocation getTextureLocation(S stateIn);

	public void extractRenderState(T entityIn, S stateIn, float f) {
		super.extractRenderState(entityIn, stateIn, f);
		stateIn.xRot = entityIn.getXRot(f);
		stateIn.yRot = entityIn.getYRot(f);
		stateIn.shake = (float)entityIn.shakeTime - f;
	}
}
