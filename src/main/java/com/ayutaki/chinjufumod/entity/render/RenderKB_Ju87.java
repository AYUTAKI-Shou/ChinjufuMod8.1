package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KB_Ju87Entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKB_Ju87 extends KK_Render<KB_Ju87Entity> {

	public RenderKB_Ju87(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKB_Ju87(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
