package com.ayutaki.chinjufumod.gui;

import javax.annotation.Nullable;

import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SignEditScreen_CM extends AbstractSignEdit_CM {
	public static final float MAGIC_SCALE_NUMBER = 62.500004F;
	public static final float MAGIC_TEXT_SCALE = 0.9765628F;
	private static final Vector3f TEXT_SCALE = new Vector3f(0.9765628F, 0.9765628F, 0.9765628F);
	@Nullable
	private SignRenderer.SignModel signModel;

	public SignEditScreen_CM(SignBlockEntity blockEntity, boolean front, boolean flag) {
		super(blockEntity, front, flag);
	}

	@Override
	protected void init() {
		super.init();
		this.signModel = SignRenderer.createSignModel(this.minecraft.getEntityModels(), this.woodType);
	}

	@Override
	protected void offsetSign(GuiGraphics matrix, BlockState state) {
		super.offsetSign(matrix, state);
		boolean flag = state.getBlock() instanceof StandingSignBlock;
		if (!flag) {
			matrix.pose().translate(0.0F, 35.0F, 0.0F);
		}
	}

	@Override
	protected void renderSignBackground(GuiGraphics matrix, BlockState state) {
		if (this.signModel != null) {
			boolean flag = state.getBlock() instanceof StandingSignBlock;
			matrix.pose().translate(0.0F, 31.0F, 0.0F);
			matrix.pose().scale(62.500004F, 62.500004F, -62.500004F);
			Material material = Sheets.getSignMaterial(this.woodType);
			VertexConsumer vertexconsumer = material.buffer(matrix.bufferSource(), this.signModel::renderType);
			this.signModel.stick.visible = flag;
			this.signModel.root.render(matrix.pose(), vertexconsumer, 15728880, OverlayTexture.NO_OVERLAY);
		}
	}

	@Override
	protected Vector3f getSignTextScale() {
		return TEXT_SCALE;
	}
}
