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
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MirrorScreen extends DisplayEffectsScreen<PlayerContainer> implements IRecipeShownListener {
	private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");
	private float xMouse;
	private float yMouse;
	private final RecipeBookGui recipeBookComponent = new RecipeBookGui();
	private boolean recipeBookComponentInitialized;
	private boolean widthTooNarrow;
	private boolean buttonClicked;

	/* Inventory_Screen */
	public MirrorScreen(PlayerEntity player) {
		super(player.inventoryMenu, player.inventory, new TranslationTextComponent("container.crafting"));
		this.passEvents = true;
		this.titleLabelX = 97;
	}

	public void tick() {
		this.recipeBookComponent.tick();
	}

	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.recipeBookComponentInitialized = true;
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.widthTooNarrow, this.width, this.imageWidth);
		this.children.add(this.recipeBookComponent);
		this.setInitialFocus(this.recipeBookComponent);
		this.addButton(new ImageButton(this.leftPos + 104, this.height / 2 - 22, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (p_214086_1_) -> {
			this.recipeBookComponent.initVisuals(this.widthTooNarrow);
			this.recipeBookComponent.toggleVisibility();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.widthTooNarrow, this.width, this.imageWidth);
			((ImageButton)p_214086_1_).setPosition(this.leftPos + 104, this.height / 2 - 22);
			this.buttonClicked = true;
		}));
	}

	protected void renderLabels(MatrixStack matrix, int xIn, int yIn) {
		this.font.draw(matrix, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
	}

	public void render(MatrixStack matrix, int xIn, int yIn, float tick) {
		this.renderBackground(matrix);
		this.doRenderEffects = !this.recipeBookComponent.isVisible();
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBg(matrix, tick, xIn, yIn);
			this.recipeBookComponent.render(matrix, xIn, yIn, tick);
		} else {
			this.recipeBookComponent.render(matrix, xIn, yIn, tick);
			super.render(matrix, xIn, yIn, tick);
			this.recipeBookComponent.renderGhostRecipe(matrix, this.leftPos, this.topPos, false, tick);
		}

		this.renderTooltip(matrix, xIn, yIn);
		this.recipeBookComponent.renderTooltip(matrix, this.leftPos, this.topPos, xIn, yIn);
		this.xMouse = (float)xIn;
		this.yMouse = (float)yIn;
	}

	@SuppressWarnings("deprecation")
	protected void renderBg(MatrixStack matrix, float tick, int xIn, int yIn) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(INVENTORY_LOCATION);
		int i = this.leftPos;
		int j = this.topPos;
		this.blit(matrix, i, j, 0, 0, this.imageWidth, this.imageHeight);
		renderEntityInInventory(i + 51, j + 75, 30, (float)(i + 51) - this.xMouse, (float)(j + 75 - 50) - this.yMouse, this.minecraft.player);
	}

	@SuppressWarnings("deprecation")
	public static void renderEntityInInventory(int xIn, int yIn, int sizeIn, float x, float y, LivingEntity entity) {
		float f = (float)Math.atan((double)(x / 40.0F));
		float f1 = (float)Math.atan((double)(y / 40.0F));
		RenderSystem.pushMatrix();
		RenderSystem.translatef((float)xIn, (float)yIn, 1050.0F);
		RenderSystem.scalef(1.0F, 1.0F, -1.0F);
		MatrixStack matrix = new MatrixStack();
		matrix.translate(0.0D, 0.0D, 1000.0D);
		matrix.scale((float)sizeIn, (float)sizeIn, (float)sizeIn);
		Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
		Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
		quaternion.mul(quaternion1);
		matrix.mulPose(quaternion);
		float f2 = entity.yBodyRot;
		float f3 = entity.yRot;
		float f4 = entity.xRot;
		float f5 = entity.yHeadRotO;
		float f6 = entity.yHeadRot;
		entity.yBodyRot = 180.0F + f * 20.0F;
		entity.yRot = 180.0F + f * 40.0F;
		entity.xRot = -f1 * 20.0F;
		entity.yHeadRot = entity.yRot;
		entity.yHeadRotO = entity.yRot;
		EntityRendererManager rendererManager = Minecraft.getInstance().getEntityRenderDispatcher();
		quaternion1.conj();
		rendererManager.overrideCameraOrientation(quaternion1);
		rendererManager.setRenderShadow(false);
		IRenderTypeBuffer.Impl irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
		RenderSystem.runAsFancy(() -> {
			rendererManager.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrix, irendertypebuffer$impl, 15728880);
		});
		irendertypebuffer$impl.endBatch();
		rendererManager.setRenderShadow(true);
		entity.yBodyRot = f2;
		entity.yRot = f3;
		entity.xRot = f4;
		entity.yHeadRotO = f5;
		entity.yHeadRot = f6;
		RenderSystem.popMatrix();
	}

	protected boolean isHovering(int xIn, int yIn, int width, int height, double x, double y) {
		return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(xIn, yIn, width, height, x, y);
	}

	public boolean mouseClicked(double mouseX, double mouseY, int state) {
		if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, state)) {
			this.setFocused(this.recipeBookComponent);
			return true; } 
		else {
			return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? false : super.mouseClicked(mouseX, mouseY, state);
		}
	}

	public boolean mouseReleased(double mouseX, double mouseY, int state) {
		if (this.buttonClicked) {
			this.buttonClicked = false;
			return true; } 
		else {
			return super.mouseReleased(mouseX, mouseY, state); }
	}

	protected boolean hasClickedOutside(double x, double y, int xIn, int yIn, int mouse) {
		boolean flag = x < (double)xIn || y < (double)yIn || x >= (double)(xIn + this.imageWidth) || y >= (double)(yIn + this.imageHeight);
		return this.recipeBookComponent.hasClickedOutside(x, y, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, mouse) && flag;
	}

	protected void slotClicked(Slot slotIn, int slotId, int mouse, ClickType type) {
		super.slotClicked(slotIn, slotId, mouse, type);
		this.recipeBookComponent.slotClicked(slotIn);
	}

	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

	public void removed() {
		if (this.recipeBookComponentInitialized) {
			this.recipeBookComponent.removed(); }
		super.removed();
	}

	public RecipeBookGui getRecipeBookComponent() {
		return this.recipeBookComponent;
	}
}
