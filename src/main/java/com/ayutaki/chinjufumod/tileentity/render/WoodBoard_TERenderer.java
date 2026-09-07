package com.ayutaki.chinjufumod.tileentity.render;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.school.WoodBoard;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WoodBoard_TERenderer extends TileEntityRenderer<WoodBoard_TileEntity> {
	private int MAX = 7;

	public WoodBoard_TERenderer(TileEntityRendererDispatcher renderer) {
		super(renderer);
	}

	public void render(WoodBoard_TileEntity tileEntity, float ticks, MatrixStack matrix, IRenderTypeBuffer bufferIn, int light, int overLay) {
		BlockState state = tileEntity.getBlockState();
		matrix.pushPose();
		matrix.translate(0.5D, 0.5D, 0.5D);
		float f4 = -state.getValue(WoodBoard.H_FACING).toYRot();
		matrix.mulPose(Vector3f.YP.rotationDegrees(f4));
		matrix.translate(0.0D, -0.3125D, -0.4375D);

		matrix.pushPose();
		float f = 0.6666667F;
		matrix.scale(f, -f, -f);

		matrix.popPose();
		FontRenderer fontRenderer = this.renderer.getFont();
		double posX = -0.36575D;
		double posY = 0.465D; //0.33333334F 0.023333333
		double posZ = 0.001D;
		matrix.translate(posX, posY, posZ);
		float fontSize = 0.0103F;
		matrix.scale(fontSize, -fontSize, fontSize);
		
		int getId = tileEntity.getTxtColor().getId();
		int getColor = TakeValue_CM.txtColorValue(getId);
		
		double colorD = 0.45D;
		int red = (int)((double)NativeImage.getR(getColor) * colorD);
		int green = (int)((double)NativeImage.getG(getColor) * colorD);
		int blue = (int)((double)NativeImage.getB(getColor) * colorD);
		int darkColor = NativeImage.combine(0, blue, green, red);

		int txtColor = tileEntity.hasGlowText()? getColor : darkColor;
		int txtLight = tileEntity.hasGlowText()? 15728880 : light;
		
		int chara = 72; //90 = 15chara
		int space = 12;
		float spaceFix = 0.8667F;
		for(int i = 0; i < MAX; ++i) {
			IReorderingProcessor iReorder = tileEntity.getRenderMessage(i, (iText) -> {
				List<IReorderingProcessor> list = fontRenderer.split(iText, chara);
				return list.isEmpty() ? IReorderingProcessor.EMPTY : list.get(0);
			});
			
			if (iReorder != null) {
				float ALIGN = 0; // LEFT (float)(-fontRenderer.width(iReorder) / 2) CENTER
				fontRenderer.drawInBatch(iReorder, ALIGN, (float)(i * space * spaceFix - 20), txtColor, false, matrix.last().pose(), bufferIn, false, 0, txtLight);
			}
		}
		matrix.popPose();
	}
}
