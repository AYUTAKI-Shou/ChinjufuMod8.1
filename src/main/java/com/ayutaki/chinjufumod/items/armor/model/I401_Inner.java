package com.ayutaki.chinjufumod.items.armor.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class I401_Inner extends BaseArmor {

	private ModelRenderer TOP1;
	private ModelRenderer TOP2;
	private ModelRenderer TSUTSU;
	private ModelRenderer RAIL;
	
	private ModelRenderer JOINT;
	
	private ModelRenderer TAIL;
	private ModelRenderer TAIL_TOP;
	
	private ModelRenderer TAIL2;
	private ModelRenderer TAIL3;
	private ModelRenderer TAIL4;
	private ModelRenderer TAIL5;
	private ModelRenderer TAIL6;
	
	public I401_Inner(float scale) {
		super(scale);
		float ag = (float)Math.PI / 180;
		
		TOP1 = new ModelRenderer(this, 34, 40);
		TOP1.addBox(0.5F, 7.25F, -5.5F, 3, 2, 1, 0.0F);
		TOP1.setPos(0F, 0F, 0F);
		TOP1.yRot = 90 * ag;
		TOP2 = new ModelRenderer(this, 30, 40);
		TOP2.addBox(-0.5F, 8.25F, -5.5F, 1, 1, 1, 0.0F);
		TOP2.setPos(0F, 0F, 0F);
		TOP2.yRot = 90 * ag;
		
		TSUTSU = new ModelRenderer(this, 0, 40);
		TSUTSU.addBox(-6.5F, 9.0F, -6.5F, 12, 2, 2, 0.0F);
		TSUTSU.setPos(0F, 0F, 0F);
		TSUTSU.yRot = 90 * ag;
		
		RAIL = new ModelRenderer(this, 0, 50);
		RAIL.addBox(-5.5F, 10.55F, -6.0F, 22, 1, 1, -0.2F);
		RAIL.setPos(0F, 0F, 0F);
		RAIL.yRot = 90 * ag;
		
		JOINT = new ModelRenderer(this, 0, 80);
		JOINT.addBox(-5.45F, 8.75F, -5.5F, 9, 1, 1, -0.1F);
		JOINT.setPos(0F, 0F, 0F);
		JOINT.xRot = 45 * ag;
		
		TAIL_TOP = new ModelRenderer(this, 0, 70);
		TAIL_TOP.addBox(-3.0F, 9.0F, 1.5F, 6, 1, 3, 0.15F);
		TAIL_TOP.setPos(0F, 0F, 0F);
		
		TAIL = new ModelRenderer(this, 0, 60);
		TAIL.addBox(-3.0F, 8.49F, 2.0F, 6, 8, 2, -0.2F);
		TAIL.setPos(0F, 0F, 0F);
		
		TAIL2 = new ModelRenderer(this, 18, 60);
		TAIL2.addBox(9.49F, 6.83F, 2.0F, 4, 1, 2, -0.21F);
		TAIL2.setPos(0F, 0F, 0F);
		TAIL2.zRot = 65 * ag;
		TAIL3 = new ModelRenderer(this, 18, 60);
		TAIL3.addBox(-13.49F, 6.83F, 2.0F, 4, 1, 2, -0.21F);
		TAIL3.setPos(0F, 0F, 0F);
		TAIL3.zRot = -65 * ag;
		
		TAIL4 = new ModelRenderer(this, 30, 60);
		TAIL4.addBox(9.85F, 10.73F, 2.0F, 2, 1, 2, -0.21F);
		TAIL4.setPos(0F, 0F, 0F);
		TAIL4.zRot = 46 * ag;
		TAIL5 = new ModelRenderer(this, 30, 60);
		TAIL5.addBox(-11.85F, 10.73F, 2.0F, 2, 1, 2, -0.21F);
		TAIL5.setPos(0F, 0F, 0F);
		TAIL5.zRot = -46 * ag;
		
		TAIL6 = new ModelRenderer(this, 38, 60);
		TAIL6.addBox(10.905F, 10.905F, 2.0F, 1, 1, 2, -0.21F);
		TAIL6.setPos(0F, 0F, 0F);
		TAIL6.zRot = 45 * ag;
		

		body.addChild(TOP1);
		body.addChild(TOP2);
		body.addChild(TSUTSU);
		body.addChild(RAIL);
		
		body.addChild(JOINT);
		
		body.addChild(TAIL_TOP);
		body.addChild(TAIL);
		body.addChild(TAIL2);
		body.addChild(TAIL3);
		body.addChild(TAIL4);
		body.addChild(TAIL5);
		body.addChild(TAIL6);
	}
}
