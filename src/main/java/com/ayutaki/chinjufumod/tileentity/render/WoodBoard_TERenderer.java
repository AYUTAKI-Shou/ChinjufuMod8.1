package com.ayutaki.chinjufumod.tileentity.render;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.school.WoodBoard;
import com.ayutaki.chinjufumod.tileentity.WoodBoardText;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WoodBoard_TERenderer implements BlockEntityRenderer<WoodBoard_TileEntity> {
	private static final int MAX = 7;
	private final Font font;

	public WoodBoard_TERenderer(BlockEntityRendererProvider.Context renderer) {
		this.font = renderer.getFont();
	}

	public void render(WoodBoard_TileEntity tileEntity, float ticks, PoseStack matrix, MultiBufferSource bufferIn, int light, int overLay) {
		BlockState state = tileEntity.getBlockState();
		WoodBoard board = (WoodBoard)state.getBlock();

		this.renderBoardWithText(tileEntity, matrix, bufferIn, light, overLay, state, board);
	}

	private void renderBoardWithText(WoodBoard_TileEntity tileEntity, PoseStack matrix, MultiBufferSource bufferIn, int light, int overLay, BlockState state, WoodBoard board) {
		matrix.pushPose();
		this.translate_Board(matrix, -board.getYRotationDegrees(state), state);
		this.render_BoardText(tileEntity.getBlockPos(), tileEntity.getBoardText(), matrix, bufferIn, light, tileEntity.getTextLineHeight(), tileEntity.getMaxTextLineWidth());
		matrix.popPose();
	}

	private void translate_Board(PoseStack matrix, float f, BlockState state) {
		matrix.translate(0.5D, 0.5D, 0.5D);
		matrix.mulPose(Axis.YP.rotationDegrees(f));
		matrix.translate(0.0F, -0.3125F, -0.4375F);
	}
	
	private void render_BoardText(BlockPos pos, WoodBoardText textCodec, PoseStack matrix, MultiBufferSource bufferIn, int light, int overLay, int chara) {
		matrix.pushPose();
		this.translate_BoardText(matrix);
		FormattedCharSequence[] getRenderMessages = textCodec.getRenderMessages(true, function -> {
			List<FormattedCharSequence> list = this.font.split(function, chara);
			return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0);
		});
		
		int getId = textCodec.getColor().getId();
		int getColor = TakeValue_CM.txtColorValue(getId);
		
		double colorD = 0.45D;
		int red = (int)((double)ARGB.red(getColor) * colorD); //FastColor.ARGB32.red -> ARGB.red 21.4
		int green = (int)((double)ARGB.green(getColor) * colorD);
		int blue = (int)((double)ARGB.blue(getColor) * colorD);
		int darkColor = ARGB.color(0, red, green, blue);
		
		int txtColor = textCodec.hasGlowText()? getColor : darkColor;
		int txtLight = textCodec.hasGlowText()? 15728880 : light;

		int space = 12;
		float spaceFix = 0.8667F;
		for (int i = 0; i < MAX; i++) {
			FormattedCharSequence charSequence = getRenderMessages[i];
			float ALIGN = 0; // (float)(-this.font.width(charSequence) / 2); CENTER
			this.font.drawInBatch(charSequence, ALIGN, (float)(i * space * spaceFix - 20), txtColor, false, matrix.last().pose(), bufferIn, Font.DisplayMode.POLYGON_OFFSET, 0, txtLight);
		}
		matrix.popPose();
	}

	private void translate_BoardText(PoseStack matrix) {
		double posX = -0.36575D;
		double posY = 0.465D; //0.33333334F 0.023333333
		double posZ = 0.001D;
		matrix.translate(posX, posY, posZ);
		float fontSize = 0.0103F;
		matrix.scale(fontSize, -fontSize, fontSize);
	}
}
