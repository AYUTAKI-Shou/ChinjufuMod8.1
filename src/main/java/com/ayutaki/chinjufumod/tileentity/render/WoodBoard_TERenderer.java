package com.ayutaki.chinjufumod.tileentity.render;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.school.WoodBoard;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.NativeImage;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.text.ITextComponent;

public class WoodBoard_TERenderer extends TileEntityRenderer<WoodBoard_TileEntity> {
	private int MAX = 7;
	
	public WoodBoard_TERenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	public void render(WoodBoard_TileEntity tileEntity, float ticks, MatrixStack matrix, IRenderTypeBuffer bufferIn, int light, int overLay) {
		BlockState state = tileEntity.getBlockState();
		matrix.push();
		matrix.translate(0.5D, 0.5D, 0.5D);
		float f4 = -state.get(WoodBoard.H_FACING).getHorizontalAngle();
		matrix.rotate(Vector3f.YP.rotationDegrees(f4));
		matrix.translate(0.0D, -0.3125D, -0.4375D);
		
		matrix.push();
		float f = 0.6666667F;
		matrix.scale(f, -f, -f);

		matrix.pop();
		FontRenderer fontRenderer = this.renderDispatcher.getFontRenderer();
		double posX = -0.36575D;
		double posY = 0.465D; //0.33333334F 0.023333333
		double posZ = 0.001D;
		matrix.translate(posX, posY, posZ);
		float fontSize = 0.0103F;
		matrix.scale(fontSize, -fontSize, fontSize);
		
		int getId = tileEntity.getTxtColor().getId();
		int getColor = TakeValue_CM.txtColorValue(getId);
		
		double colorD = 0.45D;
		int red = (int)((double)NativeImage.getRed(getColor) * colorD);
		int green = (int)((double)NativeImage.getGreen(getColor) * colorD);
		int blue = (int)((double)NativeImage.getBlue(getColor) * colorD);
		int darkColor = NativeImage.getCombined(0, blue, green, red);

		int txtColor = tileEntity.hasGlowText()? getColor : darkColor;
		int txtLight = tileEntity.hasGlowText()? 15728880 : light;
		
		int chara = 72; //90 = 15chara
		int space = 12;
		float spaceFix = 0.8667F;
		for(int i = 0; i < MAX; ++i) {
			String s = tileEntity.getRenderText(i, (iText) -> {
				List<ITextComponent> list = RenderComponentsUtil.splitText(iText, chara, fontRenderer, false, true);
				return list.isEmpty() ? "" : list.get(0).getFormattedText();
			});
			if (s != null) {
				float ALIGN = 0; // LEFT (float)(-fontRenderer.getStringWidth(s) / 2) CENTER
				fontRenderer.renderString(s, ALIGN, (float)(i * space * spaceFix - 20), txtColor, false, matrix.getLast().getMatrix(), bufferIn, false, 0, txtLight);
			}
		}
		matrix.pop();
	}
}
