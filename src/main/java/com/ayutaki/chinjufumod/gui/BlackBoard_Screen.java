package com.ayutaki.chinjufumod.gui;

import java.util.stream.IntStream;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.network.NetworkEvent_CM;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.fonts.TextInputUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class BlackBoard_Screen extends Screen {
	private int MAX = 6;
	private final BlackBoard_TileEntity BOARD;
	private int frame;
	private int line;
	private TextInputUtil BOARD_FIELD;
	private final String[] boardText;

	public BlackBoard_Screen(BlackBoard_TileEntity tileEntity) {
		super(new TranslationTextComponent("chinjufumod:board.edit"));
		this.boardText = IntStream.range(0, MAX).mapToObj(tileEntity::getMessage).map(ITextComponent::getString).toArray((p_243354_0_) -> {
			return new String[p_243354_0_];
		});
		this.BOARD = tileEntity;
	}//.range(0, 4)

	protected void init() {
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, DialogTexts.GUI_DONE, (p_238847_1_) -> {
			this.onDone();
		}));
		this.BOARD.setEditable(false);
		this.BOARD_FIELD = new TextInputUtil(() -> {
			return this.boardText[this.line];
		}, (p_238850_1_) -> {
			this.boardText[this.line] = p_238850_1_;
			this.BOARD.setMessage(this.line, new StringTextComponent(p_238850_1_));
		}, TextInputUtil.createClipboardGetter(this.minecraft), TextInputUtil.createClipboardSetter(this.minecraft), (p_238848_1_) -> {
			int chara = 72; //90 = 15chara
			return this.minecraft.font.width(p_238848_1_) <= chara;
		});
	}

	public void removed() {
		this.minecraft.keyboardHandler.setSendRepeatsToGui(false);
		NetworkEvent_CM.serverBlackBoardSend(this.BOARD.getBlockPos(), this.boardText[0], this.boardText[1], this.boardText[2], 
				this.boardText[3], this.boardText[4], this.boardText[5]);
		this.BOARD.setEditable(true);
	}

	public void tick() {
		++this.frame;
		if (!this.BOARD.getType().isValid(this.BOARD.getBlockState().getBlock())) {
			this.onDone();
		}
	}

	private void onDone() {
		this.BOARD.setChanged();
		this.minecraft.setScreen((Screen)null);
	}

	public boolean charTyped(char chara, int modifi) {
		this.BOARD_FIELD.charTyped(chara);
		return true;
	}

	public void onClose() {
		this.onDone();
	}

	public boolean keyPressed(int key, int scan, int modifi) {
		if (key == 265) {
			this.line = (this.line - 1 + MAX) % MAX; //this.line - 1 & 3;
			this.BOARD_FIELD.setCursorToEnd();
			return true;
		} 
		else if (key != 264 && key != 257 && key != 335) {
			return this.BOARD_FIELD.keyPressed(key) ? true : super.keyPressed(key, scan, modifi);
		} 
		else {
			this.line = (this.line + 1) % MAX; //this.line + 1 & 3;
			this.BOARD_FIELD.setCursorToEnd();
			return true;
		}
	}

	private int xSize = 96;
	private int ySize = 96;
	
	@SuppressWarnings("deprecation")
	private void drawBlackBoardLayer(MatrixStack matrix, float ticks, int xIn, int yIn) {
		ResourceLocation GUI_TEXTURES = new ResourceLocation(ChinjufuMod.MOD_ID, "textures/gui/board/black.png");
		
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(GUI_TEXTURES);
		int posX = (this.width - this.xSize) / 2;
		int posY = 50; //fix
		this.blit(matrix, posX, posY, 0, 0, this.xSize, this.ySize);
	}
	
	public void render(MatrixStack matrix, int xIn, int yIn, float ticks) {
		RenderHelper.setupForFlatItems();
		this.renderBackground(matrix);
		this.drawBlackBoardLayer(matrix, ticks, xIn, yIn);
		drawCenteredString(matrix, this.font, this.title, this.width / 2, 40, 16777215);
		matrix.pushPose();
		matrix.translate((double)(this.width / 2), 0.0D, 50.0D);
		float f = 93.75F;
		matrix.scale(f, -f, f);
		double titleY = -1.35D;
		matrix.translate(0.0D, titleY, 0.0D);
	
		boolean flag1 = this.frame / 6 % 2 == 0;
		matrix.pushPose();
		float f1 = 0.6666667F;
		matrix.scale(f1, -f1, -f1);
		IRenderTypeBuffer.Impl bufferIn = this.minecraft.renderBuffers().bufferSource();

		matrix.popPose();
		double posX = -0.49333334D;
		double posY = 0.4925D;
		double posZ = 0.0251D;
		matrix.translate(posX, posY, posZ);
		float fontSize = 0.0139F;
		matrix.scale(fontSize, -fontSize, fontSize);
		
		int space = 12;
		float spaceFix = 0.9988883F;
		int getId = this.BOARD.getTxtColor().getId();
		int getColor = TakeValue_CM.txtColorValue(getId);
		int cursorIn = this.BOARD_FIELD.getCursorPos();
		int selectIn = this.BOARD_FIELD.getSelectionPos();
		int lineH = this.line * space;
		Matrix4f matrix4f = matrix.last().pose();

		for(int i1 = 0; i1 < MAX; ++i1) {
			String s = this.boardText[i1];
			if (s != null) {
				if (this.font.isBidirectional()) {
					s = this.font.bidirectionalShaping(s); }

				float ALIGN = 0; // LEFT (float)(-this.minecraft.font.width(s) / 2); CENTER
				this.minecraft.font.drawInBatch(s, ALIGN, (float)(i1 * space * spaceFix - 20), getColor, false, matrix4f, bufferIn, false, 0, 15728880, false);
				if (i1 == this.line && cursorIn >= 0 && flag1) {
					int j1 = this.minecraft.font.width(s.substring(0, Math.max(Math.min(cursorIn, s.length()), 0)));
					int ALIGN1 = j1 - 0; // LEFT- this.minecraft.font.width(s) / 2; CENTER
					if (cursorIn >= s.length()) {
						this.minecraft.font.drawInBatch("_", (float)ALIGN1, (float)lineH * spaceFix - 20, getColor, false, matrix4f, bufferIn, false, 0, 15728880, false);
					}
				}
			}
		}

		bufferIn.endBatch();

		for(int i3 = 0; i3 < MAX; ++i3) {
			String s1 = this.boardText[i3];
			if (s1 != null && i3 == this.line && cursorIn >= 0) {
				int j3 = this.minecraft.font.width(s1.substring(0, Math.max(Math.min(cursorIn, s1.length()), 0)));
				int ALIGN2 = j3 - 0; // LEFT - this.minecraft.font.width(s1) / 2; CENTER
				if (flag1 && cursorIn < s1.length()) {
					fill(matrix, ALIGN2, lineH - 1, ALIGN2 + 1, lineH + 9, -16777216 | getColor);
				}

				if (selectIn != cursorIn) {
					int l3 = Math.min(cursorIn, selectIn);
					int l1 = Math.max(cursorIn, selectIn);
					int i2 = this.minecraft.font.width(s1.substring(0, l3)) - 0; //ALIGN_LEFT - this.minecraft.font.width(s1) / 2; ALIGN_CENTER
					int j2 = this.minecraft.font.width(s1.substring(0, l1)) - 0; //ALIGN_LEFT - this.minecraft.font.width(s1) / 2; ALIGN_CENTER
					int k2 = Math.min(i2, j2);
					int l2 = Math.max(i2, j2);
					Tessellator tessellator = Tessellator.getInstance();
					BufferBuilder bufferbuilder = tessellator.getBuilder();
					RenderSystem.disableTexture();
					RenderSystem.enableColorLogicOp();
					RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
					bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
					bufferbuilder.vertex(matrix4f, (float)k2, (float)(lineH + 9), 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)l2, (float)(lineH + 9), 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)l2, (float)lineH, 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)k2, (float)lineH, 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.end();
					WorldVertexBufferUploader.end(bufferbuilder);
					RenderSystem.disableColorLogicOp();
					RenderSystem.enableTexture();
				}
			}
		}

		matrix.popPose();
		RenderHelper.setupFor3DItems();
		super.render(matrix, xIn, yIn, ticks);
	}
}
