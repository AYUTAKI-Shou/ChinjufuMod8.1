package com.ayutaki.chinjufumod.gui;

import javax.annotation.Nullable;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MirrorScreen extends EffectRenderingInventoryScreen<InventoryMenu> implements RecipeUpdateListener {
	private float xMouse;
	private float yMouse;
	private final RecipeBookComponent recipeBookComponent = new RecipeBookComponent();
	private boolean widthTooNarrow;
	private boolean buttonClicked;

	public MirrorScreen(Player player) {
		super(player.inventoryMenu, player.getInventory(), Component.translatable("container.crafting"));
		this.titleLabelX = 97;
	}

	@Override
	public void containerTick() {
		this.recipeBookComponent.tick();
	}

	@Override
	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
		this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
		this.addRenderableWidget(new ImageButton(this.leftPos + 104, this.height / 2 - 22, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, p_308204_ -> {
			this.recipeBookComponent.toggleVisibility();
			this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
			p_308204_.setPosition(this.leftPos + 104, this.height / 2 - 22);
			this.buttonClicked = true;
		}));
		this.addWidget(this.recipeBookComponent);
	}

	@Override
	protected void renderLabels(GuiGraphics matrix, int xIn, int yIn) {
		matrix.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
	}

	@Override
	public void render(GuiGraphics matrix, int xIn, int yIn, float tick) {
		if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
			this.renderBackground(matrix, xIn, yIn, tick);
			this.recipeBookComponent.render(matrix, xIn, yIn, tick); } 
		else {
			super.render(matrix, xIn, yIn, tick);
			this.recipeBookComponent.render(matrix, xIn, yIn, tick);
			this.recipeBookComponent.renderGhostRecipe(matrix, this.leftPos, this.topPos, false, tick); }

		this.renderTooltip(matrix, xIn, yIn);
		this.recipeBookComponent.renderTooltip(matrix, this.leftPos, this.topPos, xIn, yIn);
		this.xMouse = (float)xIn;
		this.yMouse = (float)yIn;
	}

	@Override
	protected void renderBg(GuiGraphics matrix, float tick, int xIn, int yIn) {
		int i = this.leftPos;
		int j = this.topPos;
		matrix.blit(INVENTORY_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
		renderEntityInInventoryFollowsMouse(matrix, i + 26, j + 8, i + 75, j + 78, 30, 0.0625F, this.xMouse, this.yMouse, this.minecraft.player);
	}

	public static void renderEntityInInventoryFollowsMouse(GuiGraphics matrix, int xIn, int yIn, int x, int y, int p_299741_, float p_275604_, float floatX, float floatY, LivingEntity entity) {
		float f = (float)(xIn + x) / 2.0F;
		float f1 = (float)(yIn + y) / 2.0F;
		matrix.enableScissor(xIn, yIn, x, y);
		float f2 = (float)Math.atan((double)((f - floatX) / 40.0F));
		float f3 = (float)Math.atan((double)((f1 - floatY) / 40.0F));
		Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);
		Quaternionf quaternionf1 = new Quaternionf().rotateX(f3 * 20.0F * (float) (Math.PI / 180.0));
		quaternionf.mul(quaternionf1);
		float f4 = entity.yBodyRot;
		float f5 = entity.getYRot();
		float f6 = entity.getXRot();
		float f7 = entity.yHeadRotO;
		float f8 = entity.yHeadRot;
		entity.yBodyRot = 180.0F + f2 * 20.0F;
		entity.setYRot(180.0F + f2 * 40.0F);
		entity.setXRot(-f3 * 20.0F);
		entity.yHeadRot = entity.getYRot();
		entity.yHeadRotO = entity.getYRot();
		float f9 = entity.getScale();
		Vector3f vector3f = new Vector3f(0.0F, entity.getBbHeight() / 2.0F + p_275604_ * f9, 0.0F);
		float f10 = (float)p_299741_ / f9;
		renderEntityInInventory(matrix, f, f1, f10, vector3f, quaternionf, quaternionf1, entity);
		entity.yBodyRot = f4;
		entity.setYRot(f5);
		entity.setXRot(f6);
		entity.yHeadRotO = f7;
		entity.yHeadRot = f8;
		matrix.disableScissor();
	}

	@SuppressWarnings("deprecation")
	public static void renderEntityInInventory(GuiGraphics matrix, float xIn, float yIn, float sizeIn, Vector3f vec3f, Quaternionf quaternion, @Nullable Quaternionf quaternion1, LivingEntity entity) {
		matrix.pose().pushPose();
		matrix.pose().translate((double)xIn, (double)yIn, 50.0);
		matrix.pose().scale(sizeIn, sizeIn, -sizeIn);
		matrix.pose().translate(vec3f.x, vec3f.y, vec3f.z);
		matrix.pose().mulPose(quaternion);
		Lighting.setupForEntityInInventory();
		EntityRenderDispatcher renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
		if (quaternion1 != null) {
			quaternion1.conjugate();
			renderDispatcher.overrideCameraOrientation(quaternion1); }

		renderDispatcher.setRenderShadow(false);
		RenderSystem.runAsFancy(
			() -> renderDispatcher.render(entity, 0.0, 0.0, 0.0, 0.0F, 1.0F, matrix.pose(), matrix.bufferSource(), 15728880)
		);
		matrix.flush();
		renderDispatcher.setRenderShadow(true);
		matrix.pose().popPose();
		Lighting.setupFor3DItems();
	}

	@Override
	public boolean keyPressed(int keyX, int keyY, int keyZ) {
		return this.recipeBookComponent.keyPressed(keyX, keyY, keyZ) ? true : super.keyPressed(keyX, keyY, keyZ);
	}

	@Override
	public boolean charTyped(char chara, int i) {
		return this.recipeBookComponent.charTyped(chara, i) ? true : super.charTyped(chara, i);
	}

	@Override
	protected boolean isHovering(int xIn, int yIn, int width, int height, double x, double y) {
		return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(xIn, yIn, width, height, x, y);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int state) {
		if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, state)) {
			this.setFocused(this.recipeBookComponent);
			return true; } 
		else {
			return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? false : super.mouseClicked(mouseX, mouseY, state);
		}
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int state) {
		if (this.buttonClicked) {
			this.buttonClicked = false;
			return true; } 
		else {
			return super.mouseReleased(mouseX, mouseY, state);
		}
	}

	@Override
	protected boolean hasClickedOutside(double x, double y, int xIn, int yIn, int mouse) {
		boolean flag = x < (double)xIn || y < (double)yIn
			|| x >= (double)(xIn + this.imageWidth)
			|| y >= (double)(yIn + this.imageHeight);
		return this.recipeBookComponent.hasClickedOutside(x, y, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, mouse) && flag;
	}

	@Override
	protected void slotClicked(Slot slotIn, int slotId, int mouse, ClickType type) {
		super.slotClicked(slotIn, slotId, mouse, type);
		this.recipeBookComponent.slotClicked(slotIn);
	}

	@Override
	public void recipesUpdated() {
		this.recipeBookComponent.recipesUpdated();
	}

	@Override
	public RecipeBookComponent getRecipeBookComponent() {
		return this.recipeBookComponent;
	}
}
