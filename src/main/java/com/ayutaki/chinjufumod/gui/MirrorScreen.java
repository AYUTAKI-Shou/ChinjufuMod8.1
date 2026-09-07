package com.ayutaki.chinjufumod.gui;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DisplayEffectsScreen;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MirrorScreen extends DisplayEffectsScreen<PlayerContainer> implements IRecipeShownListener {
	private static final ResourceLocation RECIPE_BUTTON_TEXTURE = new ResourceLocation("textures/gui/recipe_button.png");
	/** The old x position of the mouse pointer */
	private float oldMouseX;
	/** The old y position of the mouse pointer */
	private float oldMouseY;
	private final RecipeBookGui recipeBookGui = new RecipeBookGui();
	private boolean removeRecipeBookGui;
	private boolean widthTooNarrow;
	private boolean buttonClicked;

	public MirrorScreen(PlayerEntity player) {
		super(player.container, player.inventory, new TranslationTextComponent("container.crafting"));
		this.passEvents = true;
	}

	public void tick() {
		this.recipeBookGui.tick();
	}

	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookGui.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.container);
		this.removeRecipeBookGui = true;
		this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
		this.children.add(this.recipeBookGui);
		this.setFocusedDefault(this.recipeBookGui);
		this.addButton(new ImageButton(this.guiLeft + 104, this.height / 2 - 22, 20, 18, 0, 0, 19, RECIPE_BUTTON_TEXTURE, (p_214086_1_) -> {
			this.recipeBookGui.initSearchBar(this.widthTooNarrow);
			this.recipeBookGui.toggleVisibility();
			this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
			((ImageButton)p_214086_1_).setPosition(this.guiLeft + 104, this.height / 2 - 22);
			this.buttonClicked = true;
		}));
	}

	protected void drawGuiContainerForegroundLayer(int mouseX, int yIn) {
		this.font.drawString(this.title.getFormattedText(), 97.0F, 8.0F, 4210752);
	}

	public void render(int xIn, int yIn, float tick) {
		this.renderBackground();
		this.hasActivePotionEffects = !this.recipeBookGui.isVisible();
		if (this.recipeBookGui.isVisible() && this.widthTooNarrow) {
			this.drawGuiContainerBackgroundLayer(tick, xIn, yIn);
			this.recipeBookGui.render(xIn, yIn, tick); } 
		
		else {
			this.recipeBookGui.render(xIn, yIn, tick);
			super.render(xIn, yIn, tick);
			this.recipeBookGui.renderGhostRecipe(this.guiLeft, this.guiTop, false, tick); }

		this.renderHoveredToolTip(xIn, yIn);
		this.recipeBookGui.renderTooltip(this.guiLeft, this.guiTop, xIn, yIn);
		this.oldMouseX = (float)xIn;
		this.oldMouseY = (float)yIn;
		this.func_212932_b(this.recipeBookGui);
	}

	protected void drawGuiContainerBackgroundLayer(float tick, int xIn, int yIn) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(i, j, 0, 0, this.xSize, this.ySize);
		drawEntityOnScreen(i + 51, j + 75, 30, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.minecraft.player);
	}

	public static void drawEntityOnScreen(int xIn, int yIn, int sizeIn, float x, float y, LivingEntity entity) {
		float f = (float)Math.atan((double)(x / 40.0F));
		float f1 = (float)Math.atan((double)(y / 40.0F));
		RenderSystem.pushMatrix();
		RenderSystem.translatef((float)xIn, (float)yIn, 1050.0F);
		RenderSystem.scalef(1.0F, 1.0F, -1.0F);
		MatrixStack matrixstack = new MatrixStack();
		matrixstack.translate(0.0D, 0.0D, 1000.0D);
		matrixstack.scale((float)sizeIn, (float)sizeIn, (float)sizeIn);
		Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
		Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
		quaternion.multiply(quaternion1);
		matrixstack.rotate(quaternion);
		float f2 = entity.renderYawOffset;
		float f3 = entity.rotationYaw;
		float f4 = entity.rotationPitch;
		float f5 = entity.prevRotationYawHead;
		float f6 = entity.rotationYawHead;
		entity.renderYawOffset = 180.0F + f * 20.0F;
		entity.rotationYaw = 180.0F + f * 40.0F;
		entity.rotationPitch = -f1 * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		entity.prevRotationYawHead = entity.rotationYaw;
		EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
		quaternion1.conjugate();
		rendererManager.setCameraOrientation(quaternion1);
		rendererManager.setRenderShadow(false);
		IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
		rendererManager.renderEntityStatic(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, irendertypebuffer$impl, 15728880);
		irendertypebuffer$impl.finish();
		rendererManager.setRenderShadow(true);
		entity.renderYawOffset = f2;
		entity.rotationYaw = f3;
		entity.rotationPitch = f4;
		entity.prevRotationYawHead = f5;
		entity.rotationYawHead = f6;
		RenderSystem.popMatrix();
	}

	protected boolean isPointInRegion(int xIn, int yIn, int width, int height, double x, double y) {
		return (!this.widthTooNarrow || !this.recipeBookGui.isVisible()) && super.isPointInRegion(xIn, yIn, width, height, x, y);
	}

	public boolean mouseClicked(double mouseX, double mouseY, int state) {
		if (this.recipeBookGui.mouseClicked(mouseX, mouseY, state)) {
			return true; } 
		else {
			return this.widthTooNarrow && this.recipeBookGui.isVisible() ? false : super.mouseClicked(mouseX, mouseY, state);
		}
	}

	public boolean mouseReleased(double mouseX, double mouseY, int state) {
		if (this.buttonClicked) {
			this.buttonClicked = false;
			return true; } 
		else {
			return super.mouseReleased(mouseX, mouseY, state);
		}
	}

	protected boolean hasClickedOutside(double x, double y, int xIn, int yIn, int mouse) {
		boolean flag = x < (double)xIn || y < (double)yIn || x >= (double)(xIn + this.xSize) || y >= (double)(yIn + this.ySize);
		return this.recipeBookGui.func_195604_a(x, y, this.guiLeft, this.guiTop, this.xSize, this.ySize, mouse) && flag;
	}

	protected void handleMouseClick(Slot slotIn, int slotId, int mouse, ClickType type) {
		super.handleMouseClick(slotIn, slotId, mouse, type);
		this.recipeBookGui.slotClicked(slotIn);
	}

	public void recipesUpdated() {
		this.recipeBookGui.recipesUpdated();
	}

	public void removed() {
		if (this.removeRecipeBookGui) {
			this.recipeBookGui.removed(); }
		super.removed();
	}

	public RecipeBookGui getRecipeGui() {
		return this.recipeBookGui;
	}
}
