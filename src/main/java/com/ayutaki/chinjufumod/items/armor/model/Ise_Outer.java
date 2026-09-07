package com.ayutaki.chinjufumod.items.armor.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Ise_Outer extends BaseArmor {

	private ModelRenderer RIBBON;
	
	private ModelRenderer ENTOTSU;
	private ModelRenderer JOINT_ISE;
	private ModelRenderer INNER;
	private ModelRenderer DOUGI;
	private ModelRenderer DOUMAE;
	
	private ModelRenderer RIGHT_SODE;
	private ModelRenderer LEFT_SODE;
	private ModelRenderer RIGHT_DSODE;
	private ModelRenderer LEFT_DSODE;
	private ModelRenderer KANPAN;
	
	private ModelRenderer RIGHT_SOX;
	private ModelRenderer LEFT_SOX;


	public Ise_Outer(float scale) {
		super(scale);

		RIBBON = new ModelRenderer(this, 0, 100);
		RIBBON.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.3F);
		RIBBON.setRotationPoint(0F, 0F, 0F);
		
		ENTOTSU = new ModelRenderer(this, 0, 32);
		ENTOTSU.addBox(-11.0F, -9.0F, 5.51F, 22, 26, 1, 0.0F);
		ENTOTSU.setRotationPoint(0F, 0F, 0F);
		JOINT_ISE = new ModelRenderer(this, 0, 60);
		JOINT_ISE.addBox(-1.5F, 7.5F, 2.5F, 3, 2, 3, 0.0F);
		JOINT_ISE.setRotationPoint(0F, 0F, 0F);

		INNER = new ModelRenderer(this, 38, 84);
		INNER.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.3F);
		INNER.setRotationPoint(0F, 0F, 0F);
		DOUGI = new ModelRenderer(this, 38, 68);
		DOUGI.addBox(-4.0F, 0.0F, -2.0F, 8, 9, 4, 0.375F);
		DOUGI.setRotationPoint(0F, 0F, 0F);
		DOUMAE = new ModelRenderer(this, 38, 100);
		DOUMAE.addBox(-4.5F, 0.0F, -2.0F, 9, 12, 4, scale);
		DOUMAE.setRotationPoint(0F, 0F, 0F);
		
		RIGHT_SODE = new ModelRenderer(this, 0, 84);
		RIGHT_SODE.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.3F);
		RIGHT_SODE.setRotationPoint(0F, 0F, 0F);
		LEFT_SODE = new ModelRenderer(this, 0, 84);
		LEFT_SODE.mirror = true;
		LEFT_SODE.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.3F);
		LEFT_SODE.setRotationPoint(0F, 0F, 0F);
		
		RIGHT_DSODE = new ModelRenderer(this, 0, 68);
		RIGHT_DSODE.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.4F);
		RIGHT_DSODE.setRotationPoint(0F, 0F, 0F);
		LEFT_DSODE = new ModelRenderer(this, 16, 68);
		LEFT_DSODE.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.4F);
		LEFT_DSODE.setRotationPoint(0F, 0F, 0F);
	
		KANPAN = new ModelRenderer(this, 46, 32);
		KANPAN.addBox(1.25F, -2.75F, -3.0F, 2, 14, 6, 0.3F);
		KANPAN.setRotationPoint(0F, 0F, 0F);
		
		RIGHT_SOX = new ModelRenderer(this, 16, 84);
		RIGHT_SOX.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.15F);
		RIGHT_SOX.setRotationPoint(0F, 0F, 0F);
		LEFT_SOX = new ModelRenderer(this, 16, 84);
		LEFT_SOX.mirror = true;
		LEFT_SOX.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.15F);
		LEFT_SOX.setRotationPoint(0F, 0F, 0F);

		
		bipedHead.addChild(RIBBON);
		
		bipedBody.addChild(ENTOTSU);
		bipedBody.addChild(JOINT_ISE);
		bipedBody.addChild(INNER);
		bipedBody.addChild(DOUGI);
		bipedBody.addChild(DOUMAE);

		bipedRightArm.addChild(RIGHT_SODE);
		bipedLeftArm.addChild(LEFT_SODE);
		bipedRightArm.addChild(RIGHT_DSODE);
		bipedLeftArm.addChild(LEFT_DSODE);
		bipedLeftArm.addChild(KANPAN);
		
		bipedRightLeg.addChild(RIGHT_SOX);
		bipedLeftLeg.addChild(LEFT_SOX);
	}
}
