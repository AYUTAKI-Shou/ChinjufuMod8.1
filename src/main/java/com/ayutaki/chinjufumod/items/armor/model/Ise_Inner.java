package com.ayutaki.chinjufumod.items.armor.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Ise_Inner extends BaseArmor {

	private ModelRenderer RIGHT_SKIRT;
	private ModelRenderer LEFT_SKIRT;
	
	private ModelRenderer RIGHT_WA;
	private ModelRenderer LEFT_WA;
	private ModelRenderer RIGHT_GUARD;
	private ModelRenderer LEFT_GUARD;
	
	public Ise_Inner(float scale) {
		super(scale);

		RIGHT_SKIRT = new ModelRenderer(this, 0, 32);
		RIGHT_SKIRT.addBox(-2.09F, -1.0F, -2.0F, 4, 12, 4, scale);
		RIGHT_SKIRT.setRotationPoint(0F, 0F, 0F);
		LEFT_SKIRT = new ModelRenderer(this, 16, 32);
		LEFT_SKIRT.addBox(-1.91F, -1.0F, -1.99F, 4, 12, 4, scale);
		LEFT_SKIRT.setRotationPoint(0F, 0F, 0F);
		
		RIGHT_WA = new ModelRenderer(this, 0, 48);
		RIGHT_WA.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
		RIGHT_WA.setRotationPoint(0F, 0F, 0F);
		LEFT_WA = new ModelRenderer(this, 0, 48);
		LEFT_WA.mirror = true;
		LEFT_WA.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, scale);
		LEFT_WA.setRotationPoint(0F, 0F, 0F);
		
		RIGHT_GUARD = new ModelRenderer(this, 0, 64);
		RIGHT_GUARD.addBox(-2.3F, 0.0F, -2.0F, 4, 12, 4, scale);
		RIGHT_GUARD.setRotationPoint(0F, 0F, 0F);
		LEFT_GUARD = new ModelRenderer(this, 0, 64);
		LEFT_GUARD.mirror = true;
		LEFT_GUARD.addBox(-1.7F, 0.0F, -2.0F, 4, 12, 4, scale);
		LEFT_GUARD.setRotationPoint(0F, 0F, 0F);

		bipedRightLeg.addChild(RIGHT_SKIRT);
		bipedLeftLeg.addChild(LEFT_SKIRT);
		
		bipedRightLeg.addChild(RIGHT_WA);
		bipedLeftLeg.addChild(LEFT_WA);
		bipedRightLeg.addChild(RIGHT_GUARD);
		bipedLeftLeg.addChild(LEFT_GUARD);
	}
}
