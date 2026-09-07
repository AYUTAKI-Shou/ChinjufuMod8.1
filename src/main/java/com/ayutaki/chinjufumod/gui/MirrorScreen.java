package com.ayutaki.chinjufumod.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MirrorScreen extends InventoryEffectRenderer implements IRecipeShownListener {
	private float oldMouseX;
	private float oldMouseY;
	private GuiButtonImage recipeButton;
	private final GuiRecipeBook recipeBookGui = new GuiRecipeBook();
	private boolean widthTooNarrow;
	private boolean buttonClicked;

	public MirrorScreen(EntityPlayer player) {
		super(player.inventoryContainer);
		this.allowUserInput = true;
	}

	public void updateScreen() {
		this.recipeBookGui.tick();
	}

	public void initGui() {
		this.buttonList.clear();
		super.initGui();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookGui.func_194303_a(this.width, this.height, this.mc, this.widthTooNarrow, ((ContainerPlayer)this.inventorySlots).craftMatrix);
		this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
		this.recipeButton = new GuiButtonImage(10, this.guiLeft + 104, this.height / 2 - 22, 20, 18, 178, 0, 19, INVENTORY_BACKGROUND);
		this.buttonList.add(this.recipeButton);
	}

	protected void drawGuiContainerForegroundLayer(int xIn, int yIn) {
		this.fontRenderer.drawString(I18n.format("container.crafting"), 97, 8, 4210752);
	}

	public void drawScreen(int xIn, int yIn, float tick) {
		this.drawDefaultBackground();
		this.hasActivePotionEffects = !this.recipeBookGui.isVisible();

		if (this.recipeBookGui.isVisible() && this.widthTooNarrow) {
			this.drawGuiContainerBackgroundLayer(tick, xIn, yIn);
			this.recipeBookGui.render(xIn, yIn, tick); }
		
		else {
			this.recipeBookGui.render(xIn, yIn, tick);
			super.drawScreen(xIn, yIn, tick);
			this.recipeBookGui.renderGhostRecipe(this.guiLeft, this.guiTop, false, tick); }

		this.renderHoveredToolTip(xIn, yIn);
		this.recipeBookGui.renderTooltip(this.guiLeft, this.guiTop, xIn, yIn);
		this.oldMouseX = (float)xIn;
		this.oldMouseY = (float)yIn;
	}

	protected void drawGuiContainerBackgroundLayer(float tick, int xIn, int yIn) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
		drawEntityOnScreen(i + 51, j + 75, 30, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.mc.player);
	}

	public static void drawEntityOnScreen(int xIn, int yIn, int sizeIn, float x, float y, EntityLivingBase entity) {
		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)xIn, (float)yIn, 50.0F);
		GlStateManager.scale((float)(-sizeIn), (float)sizeIn, (float)sizeIn);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		float f = entity.renderYawOffset;
		float f1 = entity.rotationYaw;
		float f2 = entity.rotationPitch;
		float f3 = entity.prevRotationYawHead;
		float f4 = entity.rotationYawHead;
		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-((float)Math.atan((double)(y / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		entity.renderYawOffset = (float)Math.atan((double)(x / 40.0F)) * 20.0F;
		entity.rotationYaw = (float)Math.atan((double)(x / 40.0F)) * 40.0F;
		entity.rotationPitch = -((float)Math.atan((double)(y / 40.0F))) * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		entity.prevRotationYawHead = entity.rotationYaw;
		GlStateManager.translate(0.0F, 0.0F, 0.0F);
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		rendermanager.setPlayerViewY(180.0F);
		rendermanager.setRenderShadow(false);
		rendermanager.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
		rendermanager.setRenderShadow(true);
		entity.renderYawOffset = f;
		entity.rotationYaw = f1;
		entity.rotationPitch = f2;
		entity.prevRotationYawHead = f3;
		entity.rotationYawHead = f4;
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	protected boolean isPointInRegion(int xIn, int yIn, int width, int height, int x, int y) {
		return (!this.widthTooNarrow || !this.recipeBookGui.isVisible()) && super.isPointInRegion(xIn, yIn, width, height, x, y);
	}

	protected void mouseClicked(int mouseX, int mouseY, int state) throws IOException {
		if (!this.recipeBookGui.mouseClicked(mouseX, mouseY, state)) {
			if (!this.widthTooNarrow || !this.recipeBookGui.isVisible()) {
				super.mouseClicked(mouseX, mouseY, state); }
		}
	}

	protected void mouseReleased(int mouseX, int mouseY, int state) {
		if (this.buttonClicked) {
			this.buttonClicked = false; }
		else {
			super.mouseReleased(mouseX, mouseY, state); }
	}

	protected boolean hasClickedOutside(int x, int y, int xIn, int yIn) {
		boolean flag = x < xIn || y < yIn || x >= xIn + this.xSize || y >= yIn + this.ySize;
		return this.recipeBookGui.hasClickedOutside(x, y, this.guiLeft, this.guiTop, this.xSize, this.ySize) && flag;
	}

	protected void actionPerformed(GuiButton button) throws IOException {
		if (button.id == 10) {
			this.recipeBookGui.initVisuals(this.widthTooNarrow, ((ContainerPlayer)this.inventorySlots).craftMatrix);
			this.recipeBookGui.toggleVisibility();
			this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
			this.recipeButton.setPosition(this.guiLeft + 104, this.height / 2 - 22);
			this.buttonClicked = true;
		}
	}

	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (!this.recipeBookGui.keyPressed(typedChar, keyCode)) {
			super.keyTyped(typedChar, keyCode); }
	}

	protected void handleMouseClick(Slot slotIn, int slotId, int mouse, ClickType type) {
		super.handleMouseClick(slotIn, slotId, mouse, type);
		this.recipeBookGui.slotClicked(slotIn);
	}

	public void recipesUpdated() {
		this.recipeBookGui.recipesUpdated();
	}

	public void onGuiClosed() {
		this.recipeBookGui.removed();
		super.onGuiClosed();
	}

	public GuiRecipeBook func_194310_f() {
		return this.recipeBookGui;
	}
}
