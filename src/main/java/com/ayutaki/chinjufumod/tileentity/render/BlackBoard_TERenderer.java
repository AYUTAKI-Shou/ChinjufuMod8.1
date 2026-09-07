package com.ayutaki.chinjufumod.tileentity.render;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.school.BlackBoard;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackBoard_TERenderer implements BlockEntityRenderer<BlackBoard_TileEntity> {
	private int MAX = 6;
	private final Font font;

	public BlackBoard_TERenderer(BlockEntityRendererProvider.Context renderer) {
		this.font = renderer.getFont();
	}

	public void render(BlackBoard_TileEntity tileEntity, float ticks, PoseStack matrix, MultiBufferSource bufferIn, int light, int overLay) {
		BlockState state = tileEntity.getBlockState();
		matrix.pushPose();
		matrix.translate(0.5D, 0.5D, 0.5D);
		float f4 = -state.getValue(BlackBoard.H_FACING).toYRot();
		matrix.mulPose(Vector3f.YP.rotationDegrees(f4));
		matrix.translate(0.0D, -0.3125D, -0.4375D);
		
		matrix.pushPose();
		matrix.popPose();
		
		double posX = -0.49333334D; //-0.46666667D;
		double posY = 0.5D; //0.33333334F 0.023333333
		double posZ = 0.0315D; //0.046666667
		matrix.translate(posX, posY, posZ);
		float fontSize = 0.0139F; //0.010416667F
		matrix.scale(fontSize, -fontSize, fontSize);
		
		int chara = 72; //90 = 15chara
		FormattedCharSequence[] getRenderMessages = tileEntity.getRenderMessages(Minecraft.getInstance().isTextFilteringEnabled(), (iTxt) -> {
			List<FormattedCharSequence> list = this.font.split(iTxt, chara);
			return list.isEmpty() ? FormattedCharSequence.EMPTY : list.get(0); });
		
		int getId = tileEntity.getTxtColor().getId();
		int getColor = TakeValue_CM.txtColorValue(getId);
		
		double colorD = 0.45D;
		int red = (int)((double)NativeImage.getR(getColor) * colorD);
		int green = (int)((double)NativeImage.getG(getColor) * colorD);
		int blue = (int)((double)NativeImage.getB(getColor) * colorD);
		int darkColor = NativeImage.combine(0, blue, green, red);
		
		int txtColor = tileEntity.hasGlowText()? getColor : darkColor;
		int txtLight = tileEntity.hasGlowText()? 15728880 : light;

		int space = 12;
		float spaceFix = 0.9988883F;
		for (int i = 0; i < MAX; ++i) {
			FormattedCharSequence charSequence = getRenderMessages[i];
			float ALIGN = 0; // LEFT (float)(-this.font.width(charSequence) / 2); CENTER
			this.font.drawInBatch(charSequence, ALIGN, (float)(i * space * spaceFix - 20), txtColor, false, matrix.last().pose(), bufferIn, false, 0, txtLight);
		}
		matrix.popPose();
	}
}
