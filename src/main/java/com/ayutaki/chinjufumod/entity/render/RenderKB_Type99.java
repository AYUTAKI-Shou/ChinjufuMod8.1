package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KB_Type99Entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKB_Type99 extends KK_Render<KB_Type99Entity> {

	public RenderKB_Type99(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKB_Type99(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
