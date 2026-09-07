package com.ayutaki.chinjufumod.gui;

import java.util.stream.IntStream;

import javax.annotation.Nullable;

import org.joml.Vector3f;

import com.ayutaki.chinjufumod.network.NetworkEvent_CM;
import com.mojang.blaze3d.platform.Lighting;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractSignEdit_CM extends Screen {
	protected final SignBlockEntity SIGN;
	private SignText textCodec;
	private final String[] messages;
	private final boolean isFrontText;
	protected final WoodType woodType;
	private int frame;
	private int line;
	@Nullable
	private TextFieldHelper SIGN_FIELD;

	public AbstractSignEdit_CM(SignBlockEntity blockEntity, boolean front, boolean flag) {
		this(blockEntity, front, flag, Component.translatable("sign.edit"));
	}

	public AbstractSignEdit_CM(SignBlockEntity blockEntity, boolean front, boolean flag, Component component) {
		super(component);
		this.SIGN = blockEntity;
		this.textCodec = blockEntity.getText(front);
		this.isFrontText = front;
		this.woodType = SignBlock.getWoodType(blockEntity.getBlockState().getBlock());
		this.messages = IntStream.range(0, 4)
			.mapToObj(p_277214_ -> this.textCodec.getMessage(p_277214_, flag))
			.map(Component::getString)
			.toArray(String[]::new);
	}

	@Override
	protected void init() {
		this.addRenderableWidget(
			Button.builder(CommonComponents.GUI_DONE, p_251194_ -> this.onDone())
				.bounds(this.width / 2 - 100, this.height / 4 + 144, 200, 20)
				.build()
		);
		this.SIGN_FIELD = new TextFieldHelper(
			() -> this.messages[this.line],
			this::setMessage,
			TextFieldHelper.createClipboardGetter(this.minecraft),
			TextFieldHelper.createClipboardSetter(this.minecraft),
			p_280850_ -> this.minecraft.font.width(p_280850_) <= this.SIGN.getMaxTextLineWidth()
		);
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
			&& !this.SIGN.isRemoved()
			&& !this.SIGN.playerIsTooFarAwayToEdit(this.minecraft.player.getUUID());
	}

	@Override
	public boolean keyPressed(int key, int key2, int key3) {
		if (key == 265) {
			this.line = this.line - 1 & 3;
			this.SIGN_FIELD.setCursorToEnd();
			return true; } 
		else if (key == 264 || key == 257 || key == 335) {
			this.line = this.line + 1 & 3;
			this.SIGN_FIELD.setCursorToEnd();
			return true; } 
		else {
			return this.SIGN_FIELD.keyPressed(key) ? true : super.keyPressed(key, key2, key3); }
	}

	@Override
	public boolean charTyped(char chara, int i) {
		this.SIGN_FIELD.charTyped(chara);
		return true;
	}

	@Override
	public void render(GuiGraphics matrix, int xIn, int yIn, float tick) {
		super.render(matrix, xIn, yIn, tick);
		Lighting.setupForFlatItems();
		matrix.drawCenteredString(this.font, this.title, this.width / 2, 40, 16777215);
		this.renderSign(matrix);
		Lighting.setupFor3DItems();
	}

	@Override
	public void renderBackground(GuiGraphics matrix, int xIn, int yIn, float tick) {
		this.renderTransparentBackground(matrix);
	}

	@Override
	public void onClose() {
		this.onDone();
	}

	@Override
	public void removed() {
		NetworkEvent_CM.serverSignSend(this.SIGN.getBlockPos(), this.isFrontText, 
				this.messages[0], this.messages[1], this.messages[2], this.messages[3]);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	protected abstract void renderSignBackground(GuiGraphics matrix);

	protected abstract Vector3f getSignTextScale();

	protected void offsetSign(GuiGraphics matrix, BlockState state) {
		matrix.pose().translate((float)this.width / 2.0F, 90.0F, 50.0F);
	}

	private void renderSign(GuiGraphics matrix) {
		matrix.pose().pushPose();
		this.offsetSign(matrix, this.SIGN.getBlockState());
		matrix.pose().pushPose();
		this.renderSignBackground(matrix);
		matrix.pose().popPose();
		this.renderSignText(matrix);
		matrix.pose().popPose();
	}

	private void renderSignText(GuiGraphics matrix) {
		matrix.pose().translate(0.0F, 0.0F, 4.0F);
		Vector3f vector3f = this.getSignTextScale();
		matrix.pose().scale(vector3f.x(), vector3f.y(), vector3f.z());
		int i = this.textCodec.hasGlowingText() ? this.textCodec.getColor().getTextColor() : SignRenderer.getDarkColor(this.textCodec);
		boolean flag = this.frame / 6 % 2 == 0;
		int j = this.SIGN_FIELD.getCursorPos();
		int k = this.SIGN_FIELD.getSelectionPos();
		int l = 4 * this.SIGN.getTextLineHeight() / 2;
		int i1 = this.line * this.SIGN.getTextLineHeight() - l;

		for (int j1 = 0; j1 < this.messages.length; j1++) {
			String s = this.messages[j1];
			if (s != null) {
				if (this.font.isBidirectional()) {
					s = this.font.bidirectionalShaping(s);
				}

				int k1 = -this.font.width(s) / 2;
				matrix.drawString(this.font, s, k1, j1 * this.SIGN.getTextLineHeight() - l, i, false);
				if (j1 == this.line && j >= 0 && flag) {
					int l1 = this.font.width(s.substring(0, Math.max(Math.min(j, s.length()), 0)));
					int i2 = l1 - this.font.width(s) / 2;
					if (j >= s.length()) {
						matrix.drawString(this.font, "_", i2, i1, i, false);
					}
				}
			}
		}

		for (int k3 = 0; k3 < this.messages.length; k3++) {
			String s1 = this.messages[k3];
			if (s1 != null && k3 == this.line && j >= 0) {
				int l3 = this.font.width(s1.substring(0, Math.max(Math.min(j, s1.length()), 0)));
				int i4 = l3 - this.font.width(s1) / 2;
				if (flag && j < s1.length()) {
					matrix.fill(i4, i1 - 1, i4 + 1, i1 + this.SIGN.getTextLineHeight(), 0xFF000000 | i);
				}

				if (k != j) {
					int j4 = Math.min(j, k);
					int j2 = Math.max(j, k);
					int k2 = this.font.width(s1.substring(0, j4)) - this.font.width(s1) / 2;
					int l2 = this.font.width(s1.substring(0, j2)) - this.font.width(s1) / 2;
					int i3 = Math.min(k2, l2);
					int j3 = Math.max(k2, l2);
					matrix.fill(RenderType.guiTextHighlight(), i3, i1, j3, i1 + this.SIGN.getTextLineHeight(), -16776961);
				}
			}
		}
	}

	private void setMessage(String s) {
		this.messages[this.line] = s;
		this.textCodec = this.textCodec.setMessage(this.line, Component.literal(s));
		this.SIGN.setText(this.textCodec, this.isFrontText);
	}

	private void onDone() {
		this.minecraft.setScreen(null);
	}
}
