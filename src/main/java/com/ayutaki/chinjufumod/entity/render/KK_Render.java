package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.AbstractKK_Entity;
import com.ayutaki.chinjufumod.entity.render.state.KKRender_State;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class KK_Render<T extends AbstractKK_Entity & ItemSupplier> extends EntityRenderer<T, KKRender_State> {
	private final ItemModelResolver itemModelResolver;
	private final float scale;
	private final boolean fullBright;

	public KK_Render(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
		this.itemModelResolver = renderManager.getItemModelResolver();
		this.scale = f;
		this.fullBright = flag;
	}

	public KK_Render(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}

	@Override
	protected int getBlockLightLevel(T entityIn, BlockPos pos) {
		return this.fullBright ? 15 : super.getBlockLightLevel(entityIn, pos);
	}

	public void render(KKRender_State entityIn, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		matrixStack.pushPose();
		matrixStack.scale(this.scale, this.scale, this.scale);
		matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
		
		if (entityIn.isReturning != true) { matrixStack.mulPose(Axis.YP.rotationDegrees(0.0F)); }
		if (entityIn.isReturning == true) { matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F)); }
		
		entityIn.item.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
		matrixStack.popPose();
		super.render(entityIn, matrixStack, buffer, packedLight);
	}

	public KKRender_State createRenderState() {
		return new KKRender_State();
	}

	public void extractRenderState(T entityIn, KKRender_State renderState, float f) {
		super.extractRenderState(entityIn, renderState, f);
		this.itemModelResolver.updateForNonLiving(renderState.item, entityIn.getItem(), ItemDisplayContext.GROUND, entityIn);
		renderState.isReturning = entityIn.isReturning();
	}
}
