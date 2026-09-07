package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KK_SwordfishEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKK_Swordfish extends KK_Render<KK_SwordfishEntity> {

	public RenderKK_Swordfish(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKK_Swordfish(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
