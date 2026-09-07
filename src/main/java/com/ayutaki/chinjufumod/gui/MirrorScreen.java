package com.ayutaki.chinjufumod.gui;

import javax.annotation.Nullable;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.mojang.blaze3d.platform.Lighting;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenPosition;
import net.minecraft.client.gui.screens.inventory.AbstractRecipeBookScreen;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.client.gui.screens.recipebook.CraftingRecipeBookComponent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MirrorScreen extends AbstractRecipeBookScreen<InventoryMenu> {

	private float xMouse;
	private float yMouse;
	private boolean buttonClicked;
	private final EffectsInInventory effects;

	public MirrorScreen(Player player) {
		super(player.inventoryMenu, new CraftingRecipeBookComponent(player.inventoryMenu), player.getInventory(), Component.translatable("container.crafting"));
		this.titleLabelX = 97;
		this.effects = new EffectsInInventory(this);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected ScreenPosition getRecipeBookButtonPosition() {
		return new ScreenPosition(this.leftPos + 104, this.height / 2 - 22);
	}

	@Override
	protected void onRecipeBookButtonClick() {
		this.buttonClicked = true;
	}

	@Override
	protected void renderLabels(GuiGraphics matrix, int xIn, int yIn) {
		matrix.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
	}

	@Override
	public void render(GuiGraphics matrix, int xIn, int yIn, float tick) {
		super.render(matrix, xIn, yIn, tick);
		this.effects.render(matrix, xIn, yIn, tick);
		this.xMouse = (float)xIn;
		this.yMouse = (float)yIn;
	}

	@Override
	public boolean showsActiveEffects() {
		return this.effects.canSeeEffects();
	}

	@Override
	protected boolean isBiggerResultSlot() {
		return false;
	}

	@Override
	protected void renderBg(GuiGraphics matrix, float tick, int xIn, int yIn) {
		int i = this.leftPos;
		int j = this.topPos;
		matrix.blit(RenderType::guiTextured, INVENTORY_LOCATION, i, j, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
		renderEntityInInventoryFollowsMouse(matrix, i + 26, j + 8, i + 75, j + 78, 30, 0.0625F, this.xMouse, this.yMouse, this.minecraft.player);
	}

	public static void renderEntityInInventoryFollowsMouse(GuiGraphics matrix, int x1, int y1, int x2, int y2, int scale, float yOffset, float mouseX, float mouseY, LivingEntity entity) {
		float f = (float)(x1 + x2) / 2.0F;
		float f1 = (float)(y1 + y2) / 2.0F;
		float f2 = (float)Math.atan((double)((f - mouseX) / 40.0F));
		float f3 = (float)Math.atan((double)((f1 - mouseY) / 40.0F));
		// Forge: Allow passing in direct angle components instead of mouse position
		renderEntityInInventoryFollowsAngle(matrix, x1, y1, x2, y2, scale, yOffset, f2, f3, entity);
	}

	public static void renderEntityInInventoryFollowsAngle(GuiGraphics matrix, int xIn, int yIn, int x, int y, int p_294663_, float tick, float angleXComponent, float angleYComponent, LivingEntity entity) {
		float f = (float)(xIn + x) / 2.0F;
		float f1 = (float)(yIn + y) / 2.0F;
		matrix.enableScissor(xIn, yIn, x, y);
		float f2 = angleXComponent;
		float f3 = angleYComponent;
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
		Vector3f vector3f = new Vector3f(0.0F, entity.getBbHeight() / 2.0F + tick * f9, 0.0F);
		float f10 = (float)p_294663_ / f9;
		renderEntityInInventory(matrix, f, f1, f10, vector3f, quaternionf, quaternionf1, entity);
		entity.yBodyRot = f4;
		entity.setYRot(f5);
		entity.setXRot(f6);
		entity.yHeadRotO = f7;
		entity.yHeadRot = f8;
		matrix.disableScissor();
	}

	public static void renderEntityInInventory(GuiGraphics matrix, float x, float y, float scale, Vector3f translate, Quaternionf pose, @Nullable Quaternionf cameraOrientation, LivingEntity entity) {
		matrix.pose().pushPose();
		matrix.pose().translate((double)x, (double)y, 50.0);
		matrix.pose().scale(scale, scale, -scale);
		matrix.pose().translate(translate.x, translate.y, translate.z);
		matrix.pose().mulPose(pose);
		matrix.flush();
		Lighting.setupForEntityInInventory();
		EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
		if (cameraOrientation != null) {
			entityrenderdispatcher.overrideCameraOrientation(cameraOrientation.conjugate(new Quaternionf()).rotateY((float) Math.PI));
		}

		entityrenderdispatcher.setRenderShadow(false);
		matrix.drawSpecial(p_370280_ -> entityrenderdispatcher.render(entity, 0.0, 0.0, 0.0, 1.0F, matrix.pose(), p_370280_, 15728880));
		matrix.flush();
		entityrenderdispatcher.setRenderShadow(true);
		matrix.pose().popPose();
		Lighting.setupFor3DItems();
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (this.buttonClicked) {
			this.buttonClicked = false;
			return true; } 
		else {
			return super.mouseReleased(mouseX, mouseY, button); }
	}
}
