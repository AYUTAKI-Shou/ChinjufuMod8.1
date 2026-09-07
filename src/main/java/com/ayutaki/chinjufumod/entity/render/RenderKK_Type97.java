package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KK_Type97Entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKK_Type97 extends KK_Render<KK_Type97Entity> {

	public RenderKK_Type97(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKK_Type97(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
