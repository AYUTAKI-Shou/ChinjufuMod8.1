package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.SB_ZuiunEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderSB_Zuiun extends KK_Render<SB_ZuiunEntity> {

	public RenderSB_Zuiun(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderSB_Zuiun(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
