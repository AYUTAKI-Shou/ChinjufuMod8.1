package com.ayutaki.chinjufumod.gui;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.tileentity.ReizouTop_TileEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ReizouTopScreen extends GuiContainer {
	
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(ChinjufuMod.MOD_ID, "textures/gui/reizou_27.png");
	private final InventoryPlayer playerInventory;
	private final ReizouTop_TileEntity tileEntity;
	
	public ReizouTopScreen(InventoryPlayer playerInventory, ReizouTop_TileEntity chestInventory, EntityPlayer player) {
		super(new ReizouTopMenu(playerInventory, chestInventory, player));
		this.playerInventory = playerInventory;
		this.tileEntity = chestInventory;
		this.xSize = 175;
		this.ySize = 168;
	}
	
	@Override
	public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}

	/* Inventory title height. */
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(this.tileEntity.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, 74, 4210752);
	}
}
