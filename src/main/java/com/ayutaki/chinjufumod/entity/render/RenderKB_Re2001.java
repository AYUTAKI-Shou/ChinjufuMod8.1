package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KB_Re2001Entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKB_Re2001 extends KK_Render<KB_Re2001Entity> {

	public RenderKB_Re2001(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKB_Re2001(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
