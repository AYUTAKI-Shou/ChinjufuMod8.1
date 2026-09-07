package com.ayutaki.chinjufumod.items.armor.model;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class Ise_Model {
	
	public static MeshDefinition createInner() {
		MeshDefinition meshD = new MeshDefinition();
		PartDefinition root = meshD.getRoot();
		float scale = 0.4F;
		
		/** Base **/
		root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(16, 16)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedRightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedLeftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

		
		/** Add **/
		bipedRightLeg.addOrReplaceChild("right_skirt", CubeListBuilder.create()
				.texOffs(0, 32)
				.addBox(-2.09F, -1.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_SKIRT
		bipedLeftLeg.addOrReplaceChild("left_skirt", CubeListBuilder.create()
				.texOffs(16, 32)
				.addBox(-1.91F, -1.0F, -1.99F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_SKIRT
		
		bipedRightLeg.addOrReplaceChild("right_wa", CubeListBuilder.create()
				.texOffs(0, 48)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_WA
		bipedLeftLeg.addOrReplaceChild("left_wa", CubeListBuilder.create()
				.texOffs(0, 48)
				.mirror(true)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_WA
		
		bipedRightLeg.addOrReplaceChild("right_guard", CubeListBuilder.create()
				.texOffs(0, 64)
				.addBox(-2.3F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_GUARD
		bipedLeftLeg.addOrReplaceChild("left_guard", CubeListBuilder.create()
				.texOffs(0, 64)
				.mirror(true)
				.addBox(-1.7F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_GUARD
	
		return meshD;
	}

	public static MeshDefinition createOuter() {
		MeshDefinition meshD = new MeshDefinition();
		PartDefinition root = meshD.getRoot();
		float scale = 0.55F;
		
		/** Base **/
		PartDefinition bipedHead = root.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0)
				.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, new CubeDeformation(scale)), 
				PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedBody = root.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(16, 16)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bipedRightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedLeftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedRightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)), 
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bipedLeftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.mirror(true)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		
		
		/** Add **/
		bipedHead.addOrReplaceChild("ribbon", CubeListBuilder.create()
				.texOffs(0, 100)
				.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, new CubeDeformation(0.3F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIBBON
		
		bipedBody.addOrReplaceChild("entotsu", CubeListBuilder.create()
				.texOffs(0, 32)
				.addBox(-11.0F, -9.0F, 5.51F, 22, 26, 1, new CubeDeformation(0.0F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //ENTOTSU
		bipedBody.addOrReplaceChild("joint_ise", CubeListBuilder.create()
				.texOffs(0, 60)
				.addBox(-1.5F, 7.5F, 2.5F, 3, 2, 3, new CubeDeformation(0.0F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //JOINT_ISE

		bipedBody.addOrReplaceChild("inner", CubeListBuilder.create()
				.texOffs(38, 84)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(0.3F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //INNER
		bipedBody.addOrReplaceChild("dougi", CubeListBuilder.create()
				.texOffs(38, 68)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 9, 4, new CubeDeformation(0.375F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //DOUGI
		bipedBody.addOrReplaceChild("doumae", CubeListBuilder.create()
				.texOffs(38, 100)
				.addBox(-4.5F, 0.0F, -2.0F, 9, 12, 4, new CubeDeformation(scale)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //DOUMAE
		
		bipedRightArm.addOrReplaceChild("right_sode", CubeListBuilder.create()
				.texOffs(0, 84)
				.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(0.3F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_SODE
		bipedLeftArm.addOrReplaceChild("left_sode", CubeListBuilder.create()
				.texOffs(0, 84)
				.mirror(true)
				.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_SODE
		
		bipedLeftArm.addOrReplaceChild("kanpan", CubeListBuilder.create()
				.texOffs(46, 32)
				.addBox(1.25F, -2.75F, -3.0F, 2, 14, 6, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //KANPAN
		
		bipedRightArm.addOrReplaceChild("right_dsode", CubeListBuilder.create()
				.texOffs(0, 68)
				.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(0.4F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_DSODE
		bipedLeftArm.addOrReplaceChild("left_dsode", CubeListBuilder.create()
				.texOffs(16, 68)
				.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(0.4F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_DSODE
	
		bipedRightLeg.addOrReplaceChild("right_sox", CubeListBuilder.create()
				.texOffs(16, 84)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(0.15F)), 
				PartPose.offset(0.0F, 0.0F, 0.0F)); //RIGHT_SOX
		bipedLeftLeg.addOrReplaceChild("left_sox", CubeListBuilder.create()
				.texOffs(16, 84)
				.mirror(true)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(0.15F)),
				PartPose.offset(0.0F, 0.0F, 0.0F)); //LEFT_SOX

		return meshD;
	}
}
