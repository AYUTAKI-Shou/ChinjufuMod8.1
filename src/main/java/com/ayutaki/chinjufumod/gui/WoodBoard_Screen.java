package com.ayutaki.chinjufumod.gui;

import java.util.stream.IntStream;

import javax.annotation.Nullable;

import org.joml.Vector3f;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.network.NetworkEvent_CM;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.WoodBoardText;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;
import com.mojang.blaze3d.platform.Lighting;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WoodBoard_Screen extends Screen {
	private int MAX = 7;
	private final WoodBoard_TileEntity BOARD;
	private WoodBoardText textCodec;
	private final String[] boardText;
	private int frame;
	private int line;
	@Nullable
	private TextFieldHelper BOARD_FIELD;
	private static float fontSize = 1.1925F; //0.9765628F,
	private static final Vector3f TEXT_SCALE = new Vector3f(fontSize, fontSize, fontSize);

	public WoodBoard_Screen(WoodBoard_TileEntity tileEntity, boolean flag) {
		this(tileEntity, flag, Component.translatable("chinjufumod:board.edit"));
	}

	public WoodBoard_Screen(WoodBoard_TileEntity tileEntity, boolean flag, Component component) {
		super(component);
		this.BOARD = tileEntity;
		this.textCodec = tileEntity.getBoardText();
		this.boardText = IntStream.range(0, MAX)
			.mapToObj(p_277214_ -> this.textCodec.getMessage(p_277214_, flag))
			.map(Component::getString)
			.toArray(String[]::new);
	}

	@Override
	protected void init() {
		this.addRenderableWidget(
			Button.builder(CommonComponents.GUI_DONE, p_251194_ -> this.onDone())
				.bounds(this.width / 2 - 100, this.height / 4 + 144, 200, 20)
				.build());
		this.BOARD_FIELD = new TextFieldHelper(
			() -> this.boardText[this.line],
			this::setMessage,
			TextFieldHelper.createClipboardGetter(this.minecraft),
			TextFieldHelper.createClipboardSetter(this.minecraft),
			p_280850_ -> this.minecraft.font.width(p_280850_) <= this.BOARD.getMaxTextLineWidth());
	}

	@Override
	public void tick() {
		this.frame++;
		if (!this.isValid()) {
			this.onDone();
		}
	}

	private boolean isValid() {
		return this.minecraft != null
			&& this.minecraft.player != null
			&& !this.BOARD.isRemoved()
			&& !this.BOARD.playerIsTooFarAwayToEdit(this.minecraft.player.getUUID());
	}

	private void onDone() {
		this.minecraft.setScreen(null);
	}
	
	@Override
	public boolean charTyped(char chara, int i) {
		this.BOARD_FIELD.charTyped(chara);
		return true;
	}
	
	@Override
	public void onClose() {
		this.onDone();
	}

	@Override
	public void removed() {
		NetworkEvent_CM.serverWoodBoardSend(this.BOARD.getBlockPos(), this.boardText[0], this.boardText[1], this.boardText[2], 
				this.boardText[3], this.boardText[4], this.boardText[5], this.boardText[6]);
	}

	private void setMessage(String message) {
		this.boardText[this.line] = message;
		this.textCodec = this.textCodec.setMessage(this.line, Component.literal(message));
		this.BOARD.setBoardText(this.textCodec);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	@Override
	public boolean keyPressed(int key, int key2, int key3) {
		if (key == 265) {
			this.line = (this.line - 1 + MAX) % MAX; //this.line - 1 & 3;
			this.BOARD_FIELD.setCursorToEnd();
			return true; }
		else if (key == 264 || key == 257 || key == 335) {
			this.line = (this.line + 1) % MAX; //this.line + 1 & 3;
			this.BOARD_FIELD.setCursorToEnd();
			return true; }
		else {
			return this.BOARD_FIELD.keyPressed(key) ? true : super.keyPressed(key, key2, key3);
		}
	}

	@Override
	public void render(GuiGraphics matrix, int xIn, int yIn, float ticks) {
		super.render(matrix, xIn, yIn, ticks);
		Lighting.setupForFlatItems();
		this.drawWoodBoardLayer(matrix, ticks, xIn, yIn);
		matrix.drawCenteredString(this.font, this.title, this.width / 2, 40, 16777215);
		this.renderBoard(matrix);
		Lighting.setupFor3DItems();
	}

	@Override
	public void renderBackground(GuiGraphics matrix, int xIn, int yIn, float ticks) {
		this.renderTransparentBackground(matrix);
	}

	private String getWood(Block block) {
		if (block == School_Blocks.BOARD_SPRUCE.get()) { return "spruce"; }
		if (block == School_Blocks.BOARD_BIRCH.get()) { return "birch"; }
		if (block == School_Blocks.BOARD_JUNGLE.get()) { return "jungle"; }
		if (block == School_Blocks.BOARD_ACACIA.get()) { return "acacia"; }
		if (block == School_Blocks.BOARD_DOAK.get()) { return "darkoak"; }
		if (block == School_Blocks.BOARD_MANGROVE.get()) { return "mangrove"; }
		if (block == School_Blocks.BOARD_CHERRY.get()) { return "cherry"; }		
		if (block == School_Blocks.BOARD_SAKURA.get()) { return "sakura"; }
		if (block == School_Blocks.BOARD_KAEDE.get()) { return "kaede"; }
		if (block == School_Blocks.BOARD_ICHOH.get()) { return "ichoh"; }
		else { return "oak"; }
	}
	
	private int xSize = 104;
	private int ySize = 104;
	
	private void drawWoodBoardLayer(GuiGraphics matrix, float ticks, int xIn, int yIn) {
		Block block = this.BOARD.getBlockState().getBlock();
		@SuppressWarnings("removal")
		ResourceLocation GUI_TEXTURES = new ResourceLocation(ChinjufuMod.MOD_ID, "textures/gui/board/" + getWood(block) + ".png");
		int posX = (this.width - this.xSize) / 2;
		int fixX = 1;
		int posY = 50; //fix
		matrix.blit(GUI_TEXTURES, posX + fixX, posY, 0, 0, this.xSize, this.ySize);
	}
	
	protected Vector3f getTextScale() {
		return TEXT_SCALE;
	}

	protected void offsetBoard(GuiGraphics matrix, BlockState state) {
		matrix.pose().translate((float)this.width / 2.0F, 90.0F, 50.0F);
	}

	private void renderBoard(GuiGraphics matrix) {
		BlockState state = this.BOARD.getBlockState();
		matrix.pose().pushPose();
		this.offsetBoard(matrix, state);
		matrix.pose().pushPose();
		matrix.pose().popPose();
		this.renderWoodBoard_Text(matrix);
		matrix.pose().popPose();
	}

	private void renderWoodBoard_Text(GuiGraphics matrix) {
		float posX = -42.5F;
		float posY = -7.75F;
		matrix.pose().translate(posX, posY, 4.0F);
		Vector3f vector3f = this.getTextScale();
		matrix.pose().scale(vector3f.x(), vector3f.y(), vector3f.z());
		
		boolean flag = this.frame / 6 % 2 == 0;
		
		int space = 12;
		float spaceFix = 0.9F;
		int getId = textCodec.getColor().getId();
		int getColor = TakeValue_CM.txtColorValue(getId);
		int getCursor = this.BOARD_FIELD.getCursorPos();
		int getSelect = this.BOARD_FIELD.getSelectionPos();
		int lineH = this.line * space;

		for (int i1 = 0; i1 < MAX; i1++) {
			String s1 = this.boardText[i1];
			if (s1 != null) {
				if (this.font.isBidirectional()) {
					s1 = this.font.bidirectionalShaping(s1); }

				float ALIGN = 0; // -this.font.width(s) / 2; CENTER
				matrix.drawString(this.font, s1, ALIGN, (float)(i1 * space * spaceFix - 20), getColor, false);
				if (i1 == this.line && getCursor >= 0 && flag) {
					int width1 = this.font.width(s1.substring(0, Math.max(Math.min(getCursor, s1.length()), 0)));
					int ALIGN1 = width1 - 0; // this.font.width(s1) / 2; CENTER
					if (getCursor >= s1.length()) {
						matrix.drawString(this.font, "_", (float)ALIGN1, (float)i1 * space * spaceFix - 20, getColor, false); }
				}
			}
		}

		for (int i3 = 0; i3 < MAX; i3++) {
			String s3 = this.boardText[i3];
			if (s3 != null && i3 == this.line && getCursor >= 0) {
				int width3 = this.font.width(s3.substring(0, Math.max(Math.min(getCursor, s3.length()), 0)));
				int ALIGN3 = width3 - 0; // LEFT this.font.width(s1) / 2; CENTER
				if (flag && getCursor < s3.length()) {
					matrix.fill(ALIGN3, lineH - 1, ALIGN3 + 1, lineH + this.BOARD.getTextLineHeight(), 0xFF000000 | getColor); }

				if (getSelect != getCursor) {
					int minCS = Math.min(getCursor, getSelect);
					int maxCS = Math.max(getCursor, getSelect);
					int widthMin = this.font.width(s3.substring(0, minCS)) - 0; //LEFT this.font.width(s1) / 2; CENTER
					int widthMax = this.font.width(s3.substring(0, maxCS)) - 0; //LEFT this.font.width(s1) / 2; CENTER
					int mathMin = Math.min(widthMin, widthMax);
					int mathMax = Math.max(widthMin, widthMax);
					matrix.fill(RenderType.guiTextHighlight(), mathMin, lineH, mathMax, lineH + this.BOARD.getTextLineHeight(), -16776961); }
			}
		}
	}
}
