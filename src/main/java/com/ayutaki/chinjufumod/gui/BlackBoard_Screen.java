package com.ayutaki.chinjufumod.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.network.NetworkEvent_CM;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BlackBoard_Screen extends GuiScreen {
	private int MAX = 6;
	private final BlackBoard_TileEntity BOARD;
	private int updateCounter;
	private int editLine;
	private GuiButton doneBtn;

	public BlackBoard_Screen(BlackBoard_TileEntity tileEntity) {
		this.BOARD = tileEntity;
	}

	public void initGui() {
		this.buttonList.clear();
		Keyboard.enableRepeatEvents(true);
		this.doneBtn = this.addButton(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, I18n.format("gui.done")));
		this.BOARD.setEditable(false);
	}

	public void onGuiClosed() {
		if (mc.player != null) {
			Keyboard.enableRepeatEvents(false);
			NetworkEvent_CM.severBlackBoardSend(this.BOARD, this.BOARD.boardText[0], this.BOARD.boardText[1], this.BOARD.boardText[2], 
					this.BOARD.boardText[3], this.BOARD.boardText[4], this.BOARD.boardText[5]);
			this.BOARD.setEditable(true);
		}
	}

	public void updateScreen() {
		++this.updateCounter;
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.enabled) {
			if (button.id == 0) {
				this.BOARD.markDirty();
				this.mc.displayGuiScreen((GuiScreen)null); }
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (keyCode == 200) {
			this.editLine = (this.editLine - 1 + MAX) % MAX; //this.editLine - 1 & 3;
		}

		if (keyCode == 208 || keyCode == 28 || keyCode == 156) {
			this.editLine = (this.editLine + 1) % MAX; //this.editLine + 1 & 3;
		}

		String s = this.BOARD.boardText[this.editLine].getUnformattedText();

		if (keyCode == 14 && !s.isEmpty()) {
			s = s.substring(0, s.length() - 1); }

		int chara = 72; //90 = 15chara
		if (ChatAllowedCharacters.isAllowedCharacter(typedChar) && this.fontRenderer.getStringWidth(s + typedChar) <= chara) {
			s = s + typedChar; }

		this.BOARD.boardText[this.editLine] = new TextComponentString(s);

		if (keyCode == 1) {
			this.actionPerformed(this.doneBtn); }
	}
	
	private int xSize = 96;
	private int ySize = 96;
	
	private void drawBlackBoardLayer(float ticks, int xIn, int yIn) {
		ResourceLocation GUI_TEXTURES = new ResourceLocation(ChinjufuMod.MOD_ID, "textures/gui/board/black.png");
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI_TEXTURES);
		int posX = (this.width - this.xSize) / 2;
		int posY = 65; //fix
		this.drawTexturedModalRect(posX, posY, 0, 0, this.xSize, this.ySize);
	}
	
	public void drawScreen(int xIn, int yIn, float ticks) {
		this.drawDefaultBackground();
		this.drawBlackBoardLayer(ticks, xIn, yIn);
		this.drawCenteredString(this.fontRenderer, I18n.format("chinjufumod:board.edit.name"), this.width / 2, 50, 16777215);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)(this.width / 2), 0.0F, 50.0F);
		float tileSize = 93.75F;
		GlStateManager.scale(-tileSize, -tileSize, -tileSize);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

		int blockMeta = this.BOARD.getBlockMetadata();
		float f2 = 0.0F;

		if (blockMeta == 1) {
			f2 = 90.0F; }
		
		if (blockMeta == 2) {
			f2 = 180.0F; }

		if (blockMeta == 3) {
			f2 = -90.0F; }

		GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, -1.0625F, 0.0F);

		if (this.updateCounter / 6 % 2 == 0) {
			this.BOARD.lineBeingEdited = this.editLine;
		}

		TileEntityRendererDispatcher.instance.render(this.BOARD, -0.5D, -0.65D, -0.5D, 0.0F);
		this.BOARD.lineBeingEdited = -1;
		GlStateManager.popMatrix();
		super.drawScreen(xIn, yIn, ticks);
	}
}
