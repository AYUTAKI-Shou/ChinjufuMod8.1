package com.ayutaki.chinjufumod.tileentity.render;

import java.util.List;

import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WoodBoard_TERenderer extends TileEntitySpecialRenderer<WoodBoard_TileEntity> {
	private int MAX = 7;
	
	public void render(WoodBoard_TileEntity tileEntity, double x, double y, double z, float ticks, int dSage, float alpha) {
		GlStateManager.pushMatrix();

		int blockMeta = tileEntity.getBlockMetadata();
		float f2 = 0.0F;

		if (blockMeta == 1) {
			f2 = 90.0F; }
		
		if (blockMeta == 2) {
			f2 = 180.0F; }

		if (blockMeta == 3) {
			f2 = -90.0F; }

		GlStateManager.translate((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
		GlStateManager.rotate(-f2, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, -0.3125F, -0.4375F);

		if (dSage >= 0) {
			this.bindTexture(DESTROY_STAGES[dSage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 2.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888); }
		else { }

		float f = 0.6666667F;
		GlStateManager.enableRescaleNormal();
		GlStateManager.pushMatrix();
		GlStateManager.scale(f, -f, -f);

		GlStateManager.popMatrix();
		this.renderBoardText(tileEntity, dSage);

		GlStateManager.depthMask(true);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();

		if (dSage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}
	
	public void renderBoardText(WoodBoard_TileEntity tileEntity, int dSage) {
		FontRenderer fontrenderer = this.getFontRenderer();
		double posX = -0.36575D;
		double posY = 0.465D; //0.33333334F 0.023333333
		double posZ = 0.001D;
		GlStateManager.translate(posX, posY, posZ);
		
		float fontSize = 0.0103F;
		GlStateManager.scale(fontSize, -fontSize, fontSize);
		GlStateManager.glNormal3f(0.0F, 0.0F, -0.010416667F);
		GlStateManager.depthMask(false);

		if (dSage < 0) {
			for (int j = 0; j < MAX; ++j) {
				if (tileEntity.boardText[j] != null) {
					
					int colorIn = tileEntity.textColor.getMetadata();
					int getColor = TakeValue_CM.txtColorValue(colorIn);
					double colorD = 0.6D;
					int red = (int)((getColor >> 0 & 255) * colorD);
					int green = (int)((getColor >> 8 & 255) * colorD);
					int blue = (int)((getColor >> 16 & 255) * colorD);
					int darkColor = ((0 & 255) << 24 | (blue & 255) << 16 | (green & 255) << 8 | (red & 255) << 0);

					boolean hasGlow = tileEntity.hasGlowText();
					int txtColor = hasGlow? getColor : darkColor;
					
					int worldLight = this.getWorld().getCombinedLight(tileEntity.getPos(), 0);
					int lightX = hasGlow? 240 : (worldLight % 65536);
					int lightY = hasGlow? 240 : (worldLight / 65536);
					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)lightX, (float)lightY);
					
					int chara = 72; //90 = 15chara
					ITextComponent itextcomponent = tileEntity.boardText[j];
					List<ITextComponent> list = GuiUtilRenderComponents.splitText(itextcomponent, chara, fontrenderer, false, true);
					String s = list != null && !list.isEmpty() ? ((ITextComponent)list.get(0)).getFormattedText() : "";

					int space = 12;
					float spaceFix = 0.8667F;
					float ALIGN = 0.0F; //-fontrenderer.getStringWidth(s) / 2
					
					if (j == tileEntity.lineBeingEdited) {
						s = s + "_";
						fontrenderer.drawString(s, ALIGN, (float)(j * space * spaceFix - 20), txtColor, false); }
					
					else {
						fontrenderer.drawString(s, ALIGN, (float)(j * space * spaceFix - 20), txtColor, false); }
				}
			}
		}
	}
}
