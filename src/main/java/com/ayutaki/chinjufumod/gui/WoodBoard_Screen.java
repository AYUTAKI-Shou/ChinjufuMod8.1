package com.ayutaki.chinjufumod.gui;

import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.network.NetworkEvent_CM;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.block.Block;
import net.minecraft.client.gui.RenderComponentsUtil;
import net.minecraft.client.gui.fonts.TextInputUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
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

	public WoodBoard_Screen(WoodBoard_TileEntity tileEntity) {
		super(new TranslationTextComponent("chinjufumod:board.edit"));
		this.BOARD = tileEntity;
	}

	protected void init() {
		this.minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, I18n.format("gui.done"), (p_214266_1_) -> {
			this.close();
		}));
		this.BOARD.setEditable(false);
		int chara = 72; //90 = 15chara
		this.BOARD_FIELD = new TextInputUtil(this.minecraft, () -> {
			return this.BOARD.getText(this.line).getString();
		}, (p_214265_1_) -> {
			this.BOARD.setText(this.line, new StringTextComponent(p_214265_1_));
		}, chara);
	}

	public void removed() {
		this.minecraft.keyboardListener.enableRepeatEvents(false);
		NetworkEvent_CM.serverWoodBoardSend(this.BOARD.getPos(), this.BOARD.boardText[0].getString(), this.BOARD.boardText[1].getString(), this.BOARD.boardText[2].getString(), 
				this.BOARD.boardText[3].getString(), this.BOARD.boardText[4].getString(), this.BOARD.boardText[5].getString(), this.BOARD.boardText[6].getString());
		this.BOARD.setEditable(true);
	}

	public void tick() {
		++this.frame;
		if (!this.BOARD.getType().isValidBlock(this.BOARD.getBlockState().getBlock())) {
			this.close();
		}
	}

	private void close() {
		this.BOARD.markDirty();
		this.minecraft.displayGuiScreen((Screen)null);
	}

	public boolean charTyped(char chara, int i) {
		this.BOARD_FIELD.func_216894_a(chara);
		return true;
	}

	public void onClose() {
		this.close();
	}

	public boolean keyPressed(int key, int key2, int key3) {
		if (key == 265) {
			this.line = (this.line - 1 + MAX) % MAX; //this.line - 1 & 3;
			this.BOARD_FIELD.func_216899_b();
			return true;
		} 
		else if (key != 264 && key != 257 && key != 335) {
			return this.BOARD_FIELD.func_216897_a(key) ? true : super.keyPressed(key, key2, key3);
		} 
		else {
			this.line = (this.line + 1) % MAX; //this.line + 1 & 3;
			this.BOARD_FIELD.func_216899_b();
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
	
	private void drawWoodBoardLayer(float ticks, int xIn, int yIn) {
		Block block = this.BOARD.getBlockState().getBlock();
		ResourceLocation GUI_TEXTURES = new ResourceLocation(ChinjufuMod.MOD_ID, "textures/gui/board/" + getWood(block) + ".png");
		
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(GUI_TEXTURES);
		int posX = (this.width - this.xSize) / 2;
		int fixX = 1;
		int posY = 50; //fix
		this.blit(posX + fixX, posY, 0, 0, this.xSize, this.ySize);
	}
	
	public void render(int xIn, int yIn, float ticks) {
		RenderHelper.setupGuiFlatDiffuseLighting();
		this.renderBackground();
		this.drawWoodBoardLayer(ticks, xIn, yIn);
		this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 40, 16777215);
		MatrixStack matrix = new MatrixStack();
		matrix.push();
		matrix.translate((double)(this.width / 2), 0.0D, 50.0D);
		float f = 93.75F;
		matrix.scale(f, -f, f);
		double titleY = -1.35D;
		matrix.translate(0.0D, titleY, 0.0D);

		boolean flag1 = this.frame / 6 % 2 == 0;
		float f1 = 0.6666667F;
		matrix.push();
		matrix.scale(f1, -f1, -f1);
		IRenderTypeBuffer.Impl bufferIn = this.minecraft.getRenderTypeBuffers().getBufferSource();

		matrix.pop();
		double posX = -0.445D;
		double posY = 0.47D;
		double posZ = 0.0251D;
		matrix.translate(posX, posY, posZ);
		float fontSize = 0.0125F;
		matrix.scale(fontSize, -fontSize, fontSize);
		
		String[] boardText = new String[MAX];
		for(int i0 = 0; i0 < MAX; ++i0) {
			boardText[i0] = this.BOARD.getRenderText(i0, (p_228192_1_) -> {
				List<ITextComponent> list = RenderComponentsUtil.splitText(p_228192_1_, 90, this.minecraft.fontRenderer, false, true);
				return list.isEmpty() ? "" : list.get(0).getFormattedText();
			});
		}

		int space = 12;
		float spaceFix = 0.9F;
		int getId = this.BOARD.getTxtColor().getId();
		int getColor = TakeValue_CM.txtColorValue(getId);
		int getCursor = this.BOARD_FIELD.func_216896_c();
		int getSelect = this.BOARD_FIELD.func_216898_d();
		int lineH = this.line * space;
		Matrix4f matrix4f = matrix.getLast().getMatrix();
		
		for(int i1 = 0; i1 < MAX; ++i1) {
			String s1 = boardText[i1];
			if (s1 != null) {
				float ALIGN = 0; // LEFT (float)(-this.minecraft.fontRenderer.getStringWidth(s1) / 2); CENTER
				this.minecraft.fontRenderer.renderString(s1, ALIGN, (float)(i1 * space * spaceFix - 20), getColor, false, matrix4f, bufferIn, false, 0, 15728880);
				if (i1 == this.line && getCursor >= 0 && flag1) {
					int width1 = this.minecraft.fontRenderer.getStringWidth(s1.substring(0, Math.max(Math.min(getCursor, s1.length()), 0)));
					int ALIGN1 = width1 - 0; // (width1 - this.minecraft.fontRenderer.getStringWidth(s1) / 2) * i1; CENTER
					if (getCursor >= s1.length()) {
						this.minecraft.fontRenderer.renderString("_", (float)ALIGN1, (float)lineH * spaceFix - 20, getColor, false, matrix4f, bufferIn, false, 0, 15728880);
					}
				}
			}
		}

		bufferIn.finish();

		for(int i3 = 0; i3 < MAX; ++i3) {
			String s3 = boardText[i3];
			if (s3 != null && i3 == this.line && getCursor >= 0) {
				int width3 = this.minecraft.fontRenderer.getStringWidth(s3.substring(0, Math.max(Math.min(getCursor, s3.length()), 0)));
				int ALIGN3 = width3 - 0; // - this.minecraft.fontRenderer.getStringWidth(s3) / 2) * i3;
				if (flag1 && getCursor < s3.length()) {
					fill(matrix4f, ALIGN3, lineH - 1, ALIGN3 + 1, lineH + 9, -16777216 | getColor);
				}

				if (getSelect != getCursor) {
					int minCS = Math.min(getCursor, getSelect);
					int maxCS = Math.max(getCursor, getSelect);
					int widthMin = this.minecraft.fontRenderer.getStringWidth(s3.substring(0, minCS)) - 0; //this.minecraft.fontRenderer.getStringWidth(s3) / 2) * i1;
					int widthMax = this.minecraft.fontRenderer.getStringWidth(s3.substring(0, maxCS)) - 0; //this.minecraft.fontRenderer.getStringWidth(s3) / 2) * i1;
					int mathMin = Math.min(widthMin, widthMax);
					int mathMax = Math.max(widthMin, widthMax);
					Tessellator tessellator = Tessellator.getInstance();
					BufferBuilder bufferbuilder = tessellator.getBuffer();
					RenderSystem.disableTexture();
					RenderSystem.enableColorLogicOp();
					RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
					bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
					bufferbuilder.pos(matrix4f, (float)mathMin, (float)(lineH + 9), 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.pos(matrix4f, (float)mathMax, (float)(lineH + 9), 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.pos(matrix4f, (float)mathMax, (float)lineH, 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.pos(matrix4f, (float)mathMin, (float)lineH, 0.0F).color(0, 0, 255, 255).endVertex();
					bufferbuilder.finishDrawing();
					WorldVertexBufferUploader.draw(bufferbuilder);
					RenderSystem.disableColorLogicOp();
					RenderSystem.enableTexture();
				}
			}
		}

		matrix.pop();
		RenderHelper.setupGui3DDiffuseLighting();
		super.render(xIn, yIn, ticks);
	}
}
