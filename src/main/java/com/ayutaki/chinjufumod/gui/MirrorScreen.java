package com.ayutaki.chinjufumod.gui;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = ChinjufuMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MirrorScreen extends EffectRenderingInventoryScreen<InventoryMenu> implements RecipeUpdateListener {
	private static final ResourceLocation RECIPE_BUTTON_LOCATION = new ResourceLocation("textures/gui/recipe_button.png");
	private float xMouse;
	private float yMouse;
	private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
	private boolean recipeBookComponentInitialized;
	private boolean widthTooNarrow;
	private boolean buttonClicked;

	public MirrorScreen(Player player) {
		super(player.inventoryMenu, player.getInventory(), new TranslatableComponent("container.crafting"));
		this.passEvents = true;
		this.titleLabelX = 97;
	}

	public void containerTick() {
		this.recipeBookComponent.tick();
	}

	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.recipeBookComponentInitialized = true;
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		this.addRenderableWidget(new ImageButton(this.leftPos + 104, this.height / 2 - 22, 20, 18, 0, 0, 19, RECIPE_BUTTON_LOCATION, (p_98880_) -> {
			this.recipeBookComponent.toggleVisibility();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
			((ImageButton)p_98880_).setPosition(this.leftPos + 104, this.height / 2 - 22);
			this.buttonClicked = true;
		}));
		this.addWidget(this.recipeBookComponent);
		this.setInitialFocus(this.recipeBookComponent);
	}

	protected void renderLabels(PoseStack matrix, int xIn, int yIn) {
		this.font.draw(matrix, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
	}

	public void render(PoseStack matrix, int xIn, int yIn, float tick) {
		this.renderBackground(matrix);
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBg(matrix, tick, xIn, yIn);
			this.recipeBookComponent.render(matrix, xIn, yIn, tick); }
		
		else {
			this.recipeBookComponent.render(matrix, xIn, yIn, tick);
			super.render(matrix, xIn, yIn, tick);
			this.recipeBookComponent.renderGhostRecipe(matrix, this.leftPos, this.topPos, false, tick); }

		this.renderTooltip(matrix, xIn, yIn);
		this.recipeBookComponent.renderTooltip(matrix, this.leftPos, this.topPos, xIn, yIn);
		this.xMouse = (float)xIn;
		this.yMouse = (float)yIn;
	}

	protected void renderBg(PoseStack matrix, float tick, int xIn, int yIn) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, INVENTORY_LOCATION);
		int i = this.leftPos;
		int j = this.topPos;
		this.blit(matrix, i, j, 0, 0, this.imageWidth, this.imageHeight);
		renderEntityInInventory(i + 51, j + 75, 30, (float)(i + 51) - this.xMouse, (float)(j + 75 - 50) - this.yMouse, this.minecraft.player);
	}

	@SuppressWarnings("deprecation")
	public static void renderEntityInInventory(int xIn, int yIn, int sizeIn, float x, float y, LivingEntity entity) {
		float f = (float)Math.atan((double)(x / 40.0F));
		float f1 = (float)Math.atan((double)(y / 40.0F));
		PoseStack posestack = RenderSystem.getModelViewStack();
		posestack.pushPose();
		posestack.translate((double)xIn, (double)yIn, 1050.0D);
		posestack.scale(1.0F, 1.0F, -1.0F);
		RenderSystem.applyModelViewMatrix();
		PoseStack posestack1 = new PoseStack();
		posestack1.translate(0.0D, 0.0D, 1000.0D);
		posestack1.scale((float)sizeIn, (float)sizeIn, (float)sizeIn);
		Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
		Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
		quaternion.mul(quaternion1);
		posestack1.mulPose(quaternion);
		float f2 = entity.yBodyRot;
		float f3 = entity.getYRot();
		float f4 = entity.getXRot();
		float f5 = entity.yHeadRotO;
		float f6 = entity.yHeadRot;
		entity.yBodyRot = 180.0F + f * 20.0F;
		entity.setYRot(180.0F + f * 40.0F);
		entity.setXRot(-f1 * 20.0F);
		entity.yHeadRot = entity.getYRot();
		entity.yHeadRotO = entity.getYRot();
		Lighting.setupForEntityInInventory();
		EntityRenderDispatcher renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
		quaternion1.conj();
		renderDispatcher.overrideCameraOrientation(quaternion1);
		renderDispatcher.setRenderShadow(false);
		MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
		RenderSystem.runAsFancy(() -> {
			renderDispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880);
		});
		multibuffersource$buffersource.endBatch();
		renderDispatcher.setRenderShadow(true);
		entity.yBodyRot = f2;
		entity.setYRot(f3);
		entity.setXRot(f4);
		entity.yHeadRotO = f5;
		entity.yHeadRot = f6;
		posestack.popPose();
		RenderSystem.applyModelViewMatrix();
		Lighting.setupFor3DItems();
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
			return super.mouseReleased(mouseX, mouseY, state);
		}
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

	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}
}
