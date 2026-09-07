package com.ayutaki.chinjufumod.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TansuScreen extends AbstractContainerScreen<TansuMenu> {
	//net.minecraft.client.gui.screens.inventory.Shulker
	private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.parse("chinjufumod:textures/gui/tansu_45.png");
	
	public TansuScreen(TansuMenu menu, Inventory inv, Component chat) {
		super(menu, inv, chat);
		//this.passEvents = false;
		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 175;
		this.imageHeight = 203;
	}
	
	@Override
	public void render(GuiGraphics gui, final int mouseX, final int mouseY, final float partialTicks) {
		super.render(gui, mouseX, mouseY, partialTicks);
		this.renderTooltip(gui, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
		int x = (this.width - this.imageWidth) / 2;
		int y = (this.height - this.imageHeight) / 2;
		gui.blit(RenderType::guiTextured, BACKGROUND_TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
	}

	/* Inventory title height. */
	@Override
	protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) {
		gui.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
		gui.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, 110, 4210752, false);
	}
}
