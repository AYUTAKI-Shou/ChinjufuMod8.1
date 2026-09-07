package com.ayutaki.chinjufumod.entity.render.state;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ToamiRender_State extends EntityRenderState {
	public float xRot;
	public float yRot;
	public boolean isReturning;
	public final ItemStackRenderState item = new ItemStackRenderState();
}
