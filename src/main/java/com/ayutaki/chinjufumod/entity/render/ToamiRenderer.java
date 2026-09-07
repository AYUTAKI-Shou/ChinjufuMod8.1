package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.ToamiEntity;
import com.ayutaki.chinjufumod.entity.render.state.ToamiRender_State;
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
public class ToamiRenderer<T extends ToamiEntity & ItemSupplier> extends EntityRenderer<T, ToamiRender_State> {
	private final ItemModelResolver itemModelResolver;
	@SuppressWarnings("unused")
	private final float scale;
	private final boolean fullBright;

	public ToamiRenderer(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
		this.itemModelResolver = renderManager.getItemModelResolver();
		this.scale = f;
		this.fullBright = flag;
	}

	public ToamiRenderer(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}

	@Override
	protected int getBlockLightLevel(T entityIn, BlockPos pos) {
		return this.fullBright ? 15 : super.getBlockLightLevel(entityIn, pos);
	}

	public void render(ToamiRender_State entityIn, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		float time = entityIn.ageInTicks / 10.0F;
		matrixStack.pushPose();
		matrixStack.scale(time, time, time);
		matrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
		matrixStack.mulPose(Axis.XN.rotationDegrees(0.0F)); 
		
		entityIn.item.render(matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
		matrixStack.popPose();
		super.render(entityIn, matrixStack, buffer, packedLight);
	}

	public ToamiRender_State createRenderState() {
		return new ToamiRender_State();
	}

	public void extractRenderState(T entityIn, ToamiRender_State renderState, float f) {
		super.extractRenderState(entityIn, renderState, f);
		this.itemModelResolver.updateForNonLiving(renderState.item, entityIn.getItem(), ItemDisplayContext.GROUND, entityIn);
		renderState.isReturning = entityIn.isReturning();
	}
}
