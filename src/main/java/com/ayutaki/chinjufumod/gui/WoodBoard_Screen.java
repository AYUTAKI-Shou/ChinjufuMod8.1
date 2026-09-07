package com.ayutaki.chinjufumod.gui;

import java.util.stream.IntStream;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.network.NetworkEvent_CM;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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
	private TextFieldHelper BOARD_FIELD;
	private final String[] boardText;

	public WoodBoard_Screen(WoodBoard_TileEntity tileEntity, boolean flag) {
		super(new TranslatableComponent("chinjufumod:board.edit"));
		this.boardText = IntStream.range(0, MAX).mapToObj((p_169818_) -> {
			return tileEntity.getMessage(p_169818_, flag);
		}).map(Component::getString).toArray((p_169814_) -> {
			return new String[p_169814_];
		});
		this.BOARD = tileEntity;
	}//.range(0, 4)

	protected void init() {
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.addRenderableWidget(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, CommonComponents.GUI_DONE, (p_169820_) -> {
			this.onDone();
		}));
		this.BOARD.setEditable(false);
		this.BOARD_FIELD = new TextFieldHelper(() -> {
			return this.boardText[this.line];
		}, (p_169824_) -> {
			this.boardText[this.line] = p_169824_;
			this.BOARD.setMessage(this.line, new TextComponent(p_169824_));
		}, TextFieldHelper.createClipboardGetter(this.minecraft), TextFieldHelper.createClipboardSetter(this.minecraft), (p_169822_) -> {
			int chara = 72; //90 = 15chara
			return this.minecraft.font.width(p_169822_) <= chara;
		});
	}

	public void removed() {
		this.minecraft.keyboardHandler.setSendRepeatsToGui(false);
		NetworkEvent_CM.severWoodBoardSend(this.BOARD.getBlockPos(), this.BOARD.boardText[0].getString(), this.BOARD.boardText[1].getString(), this.BOARD.boardText[2].getString(), 
				this.BOARD.boardText[3].getString(), this.BOARD.boardText[4].getString(), this.BOARD.boardText[5].getString(), this.BOARD.boardText[6].getString());
		this.BOARD.setEditable(true);
	}

	public void tick() {
		++this.frame;
		if (!this.BOARD.getType().isValid(this.BOARD.getBlockState())) {
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
		if (block == School_Blocks.BOARD_SPRUCE.get()) { return "spruce"; }
		if (block == School_Blocks.BOARD_BIRCH.get()) { return "birch"; }
		if (block == School_Blocks.BOARD_JUNGLE.get()) { return "jungle"; }
		if (block == School_Blocks.BOARD_ACACIA.get()) { return "acacia"; }
		if (block == School_Blocks.BOARD_DOAK.get()) { return "darkoak"; }
		if (block == School_Blocks.BOARD_SAKURA.get()) { return "sakura"; }
		if (block == School_Blocks.BOARD_KAEDE.get()) { return "kaede"; }
		if (block == School_Blocks.BOARD_ICHOH.get()) { return "ichoh"; }
		else { return "oak"; }
	}
	
	private int xSize = 104;
	private int ySize = 104;
	
	private void drawWoodBoardLayer(PoseStack matrix, float ticks, int xIn, int yIn) {
		Block block = this.BOARD.getBlockState().getBlock();
		ResourceLocation GUI_TEXTURES = new ResourceLocation(ChinjufuMod.MOD_ID, "textures/gui/board/" + getWood(block) + ".png");
		
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, GUI_TEXTURES);
		int posX = (this.width - this.xSize) / 2;
		int fixX = 1;
		int posY = 50; //fix
		this.blit(matrix, posX + fixX, posY, 0, 0, this.xSize, this.ySize);
	}
	
	public void render(PoseStack matrix, int xIn, int yIn, float ticks) {
		Lighting.setupForFlatItems();
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
		MultiBufferSource.BufferSource bufferIn = this.minecraft.renderBuffers().bufferSource();
	
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

		for (int i1 = 0; i1 < MAX; ++i1) {
			String s1 = this.boardText[i1];
			if (s1 != null) {
				if (this.font.isBidirectional()) {
					s1 = this.font.bidirectionalShaping(s1); }

				float ALIGN = 0; // LEFT (float)(-this.minecraft.font.width(s1) / 2); CENTER
				this.minecraft.font.drawInBatch(s1, ALIGN, (float)(i1 * space * spaceFix - 20), getColor, false, matrix4f, bufferIn, false, 0, 15728880, false);
				if (i1 == this.line && getCursor >= 0 && flag1) {
					int width1 = this.minecraft.font.width(s1.substring(0, Math.max(Math.min(getCursor, s1.length()), 0)));
					int ALIGN1 = width1 - 0; // LEFT this.minecraft.font.width(s1) / 2; CENTER
					if (getCursor >= s1.length()) {
						this.minecraft.font.drawInBatch("_", (float)ALIGN1, (float)i1 * space * spaceFix - 20, getColor, false, matrix4f, bufferIn, false, 0, 15728880, false);
					}
				}
			}
		}

		bufferIn.endBatch();

		for (int i3 = 0; i3 < MAX; ++i3) {
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
					Tesselator tesselator = Tesselator.getInstance();
					BufferBuilder bufferbuilder = tesselator.getBuilder();
					RenderSystem.setShader(GameRenderer::getPositionColorShader);
					RenderSystem.disableTexture();
					RenderSystem.enableColorLogicOp();
					RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
					bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
					bufferbuilder.vertex(matrix4f, (float)mathMin, (float)(lineH + 9), 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)mathMax, (float)(lineH + 9), 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)mathMax, (float)lineH, 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.vertex(matrix4f, (float)mathMin, (float)lineH, 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.end();
					BufferUploader.end(bufferbuilder);
					RenderSystem.disableColorLogicOp();
					RenderSystem.enableTexture();
				}
			}
		}

		matrix.popPose();
		Lighting.setupFor3DItems();
		super.render(matrix, xIn, yIn, ticks);
	}
}
