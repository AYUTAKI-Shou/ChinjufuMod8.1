package com.ayutaki.chinjufumod.items.armor.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class I401_Model {
	
	public static MeshDefinition createInner(CubeDeformation cube) {
		MeshDefinition meshD = HumanoidModel.createMesh(cube, 0.0F);
		PartDefinition root = meshD.getRoot();
		float scale = 0.15F;
		
		/** Base **/
		root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition bipedBody = root.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(16, 16)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
		
		
		/** Add **/
		float ag = (float)Math.PI / 180;
		
		bipedBody.addOrReplaceChild("top1", CubeListBuilder.create()
				.texOffs(34, 40)
				.addBox(0.5F, 7.25F, -5.5F, 3, 2, 1, new CubeDeformation(0.0F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 90 * ag, 0F)); //TOP1
		bipedBody.addOrReplaceChild("top2", CubeListBuilder.create()
				.texOffs(30, 40)
				.addBox(-0.5F, 8.25F, -5.5F, 1, 1, 1, new CubeDeformation(0.0F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 90 * ag, 0F)); //TOP2
		
		bipedBody.addOrReplaceChild("tsutsu", CubeListBuilder.create()
				.texOffs(0, 40)
				.addBox(-6.5F, 9.0F, -6.5F, 12, 2, 2, new CubeDeformation(0.0F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 90 * ag, 0F)); //TSUTSU
		
		bipedBody.addOrReplaceChild("rail", CubeListBuilder.create()
				.texOffs(0, 50)
				.addBox(-5.5F, 10.55F, -6.0F, 22, 1, 1, new CubeDeformation(-0.2F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 90 * ag, 0F)); //RAIL
		
		bipedBody.addOrReplaceChild("joint", CubeListBuilder.create()
				.texOffs(0, 80)
				.addBox(-5.45F, 8.75F, -5.5F, 9, 1, 1, new CubeDeformation(-0.1F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 45 * ag, 0F, 0F)); //JOINT
		
		bipedBody.addOrReplaceChild("tail_top", CubeListBuilder.create()
				.texOffs(0, 70)
				.addBox(-3.0F, 9.0F, 1.5F, 6, 1, 3, new CubeDeformation(0.15F)),
				PartPose.offset(0F, 0F, 0F)); //TAIL_TOP
		
		bipedBody.addOrReplaceChild("tail", CubeListBuilder.create()
				.texOffs(0, 60)
				.addBox(-3.0F, 8.49F, 2.0F, 6, 8, 2, new CubeDeformation(-0.2F)),
				PartPose.offset(0F, 0F, 0F)); //TAIL
		
		bipedBody.addOrReplaceChild("tail2", CubeListBuilder.create()
				.texOffs(18, 60)
				.addBox(9.49F, 6.83F, 2.0F, 4, 1, 2, new CubeDeformation(-0.21F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 65 * ag)); //TAIL2
		bipedBody.addOrReplaceChild("tail3", CubeListBuilder.create()
				.texOffs(18, 60)
				.addBox(-13.49F, 6.83F, 2.0F, 4, 1, 2, new CubeDeformation(-0.21F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -65 * ag)); //TAIL3
		
		bipedBody.addOrReplaceChild("tail4", CubeListBuilder.create()
				.texOffs(30, 60)
				.addBox(9.85F, 10.73F, 2.0F, 2, 1, 2, new CubeDeformation(-0.21F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 46 * ag)); //TAIL4
		bipedBody.addOrReplaceChild("tail5", CubeListBuilder.create()
				.texOffs(30, 60)
				.addBox(-11.85F, 10.73F, 2.0F, 2, 1, 2, new CubeDeformation(-0.21F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -46 * ag)); //TAIL5
		
		bipedBody.addOrReplaceChild("tail6", CubeListBuilder.create()
				.texOffs(38, 60)
				.addBox(10.905F, 10.905F, 2.0F, 1, 1, 2, new CubeDeformation(-0.21F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 45 * ag)); //TAIL6
		
		return meshD;
	}

	public static MeshDefinition createOuter(CubeDeformation cube) {
		MeshDefinition meshD = HumanoidModel.createMesh(cube, 0.0F);
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
		root.addOrReplaceChild("right_arm", CubeListBuilder.create()
				.texOffs(40, 16)
				.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)), 
				PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("left_arm", CubeListBuilder.create()
				.texOffs(40, 16)
				.mirror(true)
				.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)), 
				PartPose.offset(0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("left_leg", CubeListBuilder.create()
				.texOffs(0, 16)
				.mirror(true)
				.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, new CubeDeformation(scale)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		
		
		/** Add **/
		float ag = (float)Math.PI / 180;

		bipedHead.addOrReplaceChild("antena", CubeListBuilder.create()
				.texOffs(0, 100)
				.addBox(-4.0F, -9.0F, -4.0F, 8, 8, 8, new CubeDeformation(0.3F)),
				PartPose.offset(0F, 0F, 0F)); //ANTENA
		
		bipedBody.addOrReplaceChild("eri", CubeListBuilder.create()
				.texOffs(32, 32)
				.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, new CubeDeformation(scale + 0.2F)),
				PartPose.offset(0F, 0F, 0F)); //ERI

		bipedBody.addOrReplaceChild("gyorai", CubeListBuilder.create()
				.texOffs(0, 50)
				.addBox(-4.5F, 9.0F, 4.5F, 9, 2, 2, new CubeDeformation(0.5F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 90 * ag, 0F)); //GYORAI
		bipedBody.addOrReplaceChild("gyorai2", CubeListBuilder.create()
				.texOffs(0, 50)
				.addBox(-5.5F, 12.0F, 4.5F, 9, 2, 2, new CubeDeformation(0.5F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 90 * ag, 0F)); //GYORAI2
		
		bipedBody.addOrReplaceChild("belt", CubeListBuilder.create()
				.texOffs(0, 60)
				.addBox(-2.0F, -2.1F, -2.5F, 1, 13, 5, new CubeDeformation(0.45F)), 
				PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -38 * ag)); //BELT

		return meshD;
	}
}
