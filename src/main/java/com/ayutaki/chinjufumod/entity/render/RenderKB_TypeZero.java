package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KB_TypeZeroEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKB_TypeZero extends KK_Render<KB_TypeZeroEntity> {

	public RenderKB_TypeZero(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKB_TypeZero(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
