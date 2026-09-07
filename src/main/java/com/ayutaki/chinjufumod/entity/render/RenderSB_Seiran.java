package com.ayutaki.chinjufumod.entity.render;

import com.ayutaki.chinjufumod.entity.SB_SeiranEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderSB_Seiran extends KK_Render<SB_SeiranEntity> {

	public RenderSB_Seiran(EntityRendererProvider.Context renderManager, float f, boolean flag) {
		super(renderManager);
	}
	
	public RenderSB_Seiran(EntityRendererProvider.Context renderManager) {
		this(renderManager, 1.0F, false);
	}
}
