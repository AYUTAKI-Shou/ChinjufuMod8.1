package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KK_RyuseiEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKK_Ryusei extends KK_Render<KK_RyuseiEntity> {

	public RenderKK_Ryusei(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKK_Ryusei(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
