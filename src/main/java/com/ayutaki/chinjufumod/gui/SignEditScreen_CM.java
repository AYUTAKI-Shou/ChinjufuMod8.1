package com.ayutaki.chinjufumod.gui;

import javax.annotation.Nullable;

import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SignEditScreen_CM extends AbstractSignEdit_CM {
	public static final float MAGIC_SCALE_NUMBER = 62.500004F;
	public static final float MAGIC_TEXT_SCALE = 0.9765628F;
	private static final Vector3f TEXT_SCALE = new Vector3f(0.9765628F, 0.9765628F, 0.9765628F);
	@Nullable
	private Model signModel;

	public SignEditScreen_CM(SignBlockEntity blockEntity, boolean front, boolean flag) {
		super(blockEntity, front, flag);
	}

	@Override
	protected void init() {
		super.init();
		 boolean flag = this.SIGN.getBlockState().getBlock() instanceof StandingSignBlock;
		 this.signModel = SignRenderer.createSignModel(this.minecraft.getEntityModels(), this.woodType, flag);
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
	protected void renderSignBackground(GuiGraphics matrix) {
		if (this.signModel != null) {
			matrix.pose().translate(0.0F, 31.0F, 0.0F);
			matrix.pose().scale(62.500004F, 62.500004F, -62.500004F);
			matrix.drawSpecial(p_371725_ -> {
				Material material = Sheets.getSignMaterial(this.woodType);
				VertexConsumer vertexconsumer = material.buffer(p_371725_, this.signModel::renderType);
				this.signModel.renderToBuffer(matrix.pose(), vertexconsumer, 15728880, OverlayTexture.NO_OVERLAY);
			});
		}
	}

	@Override
	protected Vector3f getSignTextScale() {
		return TEXT_SCALE;
	}
}
