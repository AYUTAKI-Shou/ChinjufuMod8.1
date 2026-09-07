package com.ayutaki.chinjufumod.items.armor.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class I401_Outer extends BaseArmor {
	
	private ModelRenderer ANTENA;
	
	private ModelRenderer ERI;
	private ModelRenderer GYORAI;
	private ModelRenderer GYORAI2;
	private ModelRenderer BELT;
	
	public I401_Outer(float scale) {
		super(scale);
		float ag = (float)Math.PI / 180;

		ANTENA = new ModelRenderer(this, 0, 100);
		ANTENA.addBox(-4.0F, -9.0F, -4.0F, 8, 8, 8, 0.3F);
		ANTENA.setPos(0F, 0F, 0F);
		
		ERI = new ModelRenderer(this, 32, 32);
		ERI.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, scale + 0.2F);
		ERI.setPos(0F, 0F, 0F);

		GYORAI = new ModelRenderer(this, 0, 50);
		GYORAI.addBox(-4.5F, 9.0F, 4.5F, 9, 2, 2, 0.5F);
		GYORAI.setPos(0F, 0F, 0F);
		GYORAI.yRot = 90 * ag;
		GYORAI2 = new ModelRenderer(this, 0, 50);
		GYORAI2.addBox(-5.5F, 12.0F, 4.5F, 9, 2, 2, 0.5F);
		GYORAI2.setPos(0F, 0F, 0F);
		GYORAI2.yRot = 90 * ag;
		
		BELT = new ModelRenderer(this, 0, 60);
		BELT.addBox(-2.0F, -2.1F, -2.5F, 1, 13, 5, 0.45F);
		BELT.setPos(0F, 0F, 0F);
		BELT.zRot = -38 * ag;
		
		head.addChild(ANTENA);
		
		body.addChild(ERI);
		body.addChild(GYORAI);
		body.addChild(GYORAI2);
		
		body.addChild(BELT);
	}
}
