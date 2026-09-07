package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KK_TenzanEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKK_Tenzan extends KK_Render<KK_TenzanEntity> {

	public RenderKK_Tenzan(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKK_Tenzan(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
