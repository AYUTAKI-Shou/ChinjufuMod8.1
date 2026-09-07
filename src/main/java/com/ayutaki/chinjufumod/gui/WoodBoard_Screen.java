package com.ayutaki.chinjufumod.gui;

import java.util.stream.IntStream;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.network.NetworkEvent_CM;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.block.Block;
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
public class WoodBoard_Screen extends Screen {
	private int MAX = 7;
	private final WoodBoard_TileEntity BOARD;
	private int frame;
	private int line;
	private TextInputUtil BOARD_FIELD;
	private final String[] boardText;

	public WoodBoard_Screen(WoodBoard_TileEntity tileEntity) {
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
		NetworkEvent_CM.serverWoodBoardSend(this.BOARD.getBlockPos(), this.boardText[0], this.boardText[1], this.boardText[2], 
				this.boardText[3], this.boardText[4], this.boardText[5], this.boardText[6]);
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

	public boolean charTyped(char chara, int i) {
		this.BOARD_FIELD.charTyped(chara);
		return true;
	}

	public void onClose() {
		this.onDone();
	}

	public boolean keyPressed(int key, int key2, int key3) {
		if (key == 265) {
			this.line = (this.line - 1 + MAX) % MAX; //this.line - 1 & 3;
			this.BOARD_FIELD.setCursorToEnd();
			return true;
		} 
		else if (key != 264 && key != 257 && key != 335) {
			return this.BOARD_FIELD.keyPressed(key) ? true : super.keyPressed(key, key2, key3);
		} 
		else {
			this.line = (this.line + 1) % MAX; //this.line + 1 & 3;
			this.BOARD_FIELD.setCursorToEnd();
			return true;
		}
	}

	private String getWood(Block block) {
		if (block == School_Blocks.BOARD_SPRUCE) { return "spruce"; }
		if (block == School_Blocks.BOARD_BIRCH) { return "birch"; }
		if (block == School_Blocks.BOARD_JUNGLE) { return "jungle"; }
		if (block == School_Blocks.BOARD_ACACIA) { return "acacia"; }
		if (block == School_Blocks.BOARD_DOAK) { return "darkoak"; }
		if (block == School_Blocks.BOARD_SAKURA) { return "sakura"; }
		if (block == School_Blocks.BOARD_KAEDE) { return "kaede"; }
		if (block == School_Blocks.BOARD_ICHOH) { return "ichoh"; }
		else { return "oak"; }
	}
	
	private int xSize = 104;
	private int ySize = 104;
	
	@SuppressWarnings("deprecation")
	private void drawWoodBoardLayer(MatrixStack matrix, float ticks, int xIn, int yIn) {
		Block block = this.BOARD.getBlockState().getBlock();
		ResourceLocation GUI_TEXTURES = new ResourceLocation(ChinjufuMod.MOD_ID, "textures/gui/board/" + getWood(block) + ".png");
		
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(GUI_TEXTURES);
		int posX = (this.width - this.xSize) / 2;
		int fixX = 1;
		int posY = 50; //fix
		this.blit(matrix, posX + fixX, posY, 0, 0, this.xSize, this.ySize);
	}
	
	public void render(MatrixStack matrix, int xIn, int yIn, float ticks) {
		RenderHelper.setupForFlatItems();
		this.renderBackground(matrix);
		this.drawWoodBoardLayer(matrix, ticks, xIn, yIn);
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
		double posX = -0.445D;
		double posY = 0.47D;
		double posZ = 0.0251D;
		matrix.translate(posX, posY, posZ);
		float fontSize = 0.0125F;
		matrix.scale(fontSize, -fontSize, fontSize);
		
		int space = 12;
		float spaceFix = 0.9F;
		int getId = this.BOARD.getTxtColor().getId();
		int getColor = TakeValue_CM.txtColorValue(getId);
		int getCursor = this.BOARD_FIELD.getCursorPos();
		int getSelect = this.BOARD_FIELD.getSelectionPos();
		int lineH = this.line * space;
		Matrix4f matrix4f = matrix.last().pose();

		for(int i1 = 0; i1 < MAX; ++i1) {
			String s1 = this.boardText[i1];
			if (s1 != null) {
				if (this.font.isBidirectional()) {
					s1 = this.font.bidirectionalShaping(s1); }

				float ALIGN = 0; // LEFT (float)(-this.minecraft.font.width(s1) / 2); CENTER
				this.minecraft.font.drawInBatch(s1, ALIGN, (float)(i1 * space * spaceFix - 20), getColor, false, matrix4f, bufferIn, false, 0, 15728880, false);
				if (i1 == this.line && getCursor >= 0 && flag1) {
					int width1 = this.minecraft.font.width(s1.substring(0, Math.max(Math.min(getCursor, s1.length()), 0)));
					int ALIGN1 = width1 - 0; // LEFT- this.minecraft.font.width(s1) / 2; CENTER
					if (getCursor >= s1.length()) {
						this.minecraft.font.drawInBatch("_", (float)ALIGN1, (float)lineH * spaceFix - 20, getColor, false, matrix4f, bufferIn, false, 0, 15728880, false);
					}
				}
			}
		}

		bufferIn.endBatch();

		for(int i3 = 0; i3 < MAX; ++i3) {
			String s3 = this.boardText[i3];
			if (s3 != null && i3 == this.line && getCursor >= 0) {
				int width3 = this.minecraft.font.width(s3.substring(0, Math.max(Math.min(getCursor, s3.length()), 0)));
				int ALIGN3 = width3 - 0; // LEFT - this.minecraft.font.width(s3) / 2; CENTER
				if (flag1 && getCursor < s3.length()) {
					fill(matrix, ALIGN3, lineH - 1, ALIGN3 + 1, lineH + 9, -16777216 | getColor);
				}

				if (getSelect != getCursor) {
					int minCS = Math.min(getCursor, getSelect);
					int maxCS = Math.max(getCursor, getSelect);
					int widthMin = this.minecraft.font.width(s3.substring(0, minCS)) - 0; //LEFT - this.minecraft.font.width(s3) / 2; CENTER
					int widthMax = this.minecraft.font.width(s3.substring(0, maxCS)) - 0; //LEFT - this.minecraft.font.width(s3) / 2; CENTER
					int mathMin = Math.min(widthMin, widthMax);
					int mathMax = Math.max(widthMin, widthMax);
					Tessellator tessellator = Tessellator.getInstance();
					BufferBuilder bufferbuilder = tessellator.getBuilder();
					RenderSystem.disableTexture();
					RenderSystem.enableColorLogicOp();
					RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
					bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
					bufferbuilder.vertex(matrix4f, (float)mathMin, (float)(lineH + 9), 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)mathMax, (float)(lineH + 9), 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)mathMax, (float)lineH, 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)mathMin, (float)lineH, 0.0F).color(0, 0, 255, 255).endVertex();
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
