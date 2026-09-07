package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.KB_F4UEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderKB_F4U extends KK_Render<KB_F4UEntity> {

	public RenderKB_F4U(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderKB_F4U(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
