package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KB_SuiseiEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKB_Suisei extends KK_Render<KB_SuiseiEntity> {

	public RenderKB_Suisei(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKB_Suisei(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
