package com.ayutaki.chinjufumod.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Chalk_Particle extends Particle {
	private static final ResourceLocation EXPLOSION_TEXTURE = new ResourceLocation("textures/entity/explosion.png");
	private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F)
			.addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S)
			.addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
	private int life;
	private final int lifeTime;
	private final TextureManager textureManager;
	private final float size;

	public Chalk_Particle(TextureManager textureIn, World worldIn, double x, double y, double z, double vx, double vy, double vz) {
		super(worldIn, x, y, z, 0.0D, 0.0D, 0.0D);
		this.textureManager = textureIn;
		this.lifeTime = 6 + this.rand.nextInt(4);
		float f = this.rand.nextFloat() * 0.1F + 0.6F; //this.rand.nextFloat() * 0.6F + 0.4F;
		this.particleRed = f;
		this.particleGreen = f;
		this.particleBlue = f;
		this.size = 1.0F - (float)vx * 0.5F;
	}

	public void renderParticle(BufferBuilder buffer, Entity entityIn, float ticks, float roX, float roZ, float yz, float xy, float xz) {
		int i = (int)(((float)this.life + ticks) * 15.0F / (float)this.lifeTime);

		if (i <= 15) {
			this.textureManager.bindTexture(EXPLOSION_TEXTURE);
			float f = (float)(i % 4) / 4.0F;
			float f1 = f + 0.24975F;
			float f2 = (float)(i / 4) / 4.0F;
			float f3 = f2 + 0.24975F;
			float f4 = 2.0F * this.size;
			float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)ticks - interpPosX);
			float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)ticks - interpPosY);
			float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)ticks - interpPosZ);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableLighting();
			RenderHelper.disableStandardItemLighting();
			buffer.begin(7, VERTEX_FORMAT);
			buffer.pos((double)(f5 - roX * f4 - xy * f4), (double)(f6 - roZ * f4), (double)(f7 - yz * f4 - xz * f4)).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			buffer.pos((double)(f5 - roX * f4 + xy * f4), (double)(f6 + roZ * f4), (double)(f7 - yz * f4 + xz * f4)).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			buffer.pos((double)(f5 + roX * f4 + xy * f4), (double)(f6 + roZ * f4), (double)(f7 + yz * f4 + xz * f4)).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			buffer.pos((double)(f5 + roX * f4 - xy * f4), (double)(f6 - roZ * f4), (double)(f7 + yz * f4 - xz * f4)).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			Tessellator.getInstance().draw();
			GlStateManager.enableLighting();
		}
	}

	public int getBrightnessForRender(float f) {
		return 61680;
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		++this.life;

		if (this.life == this.lifeTime) {
			this.setExpired(); }
	}

	public int getFXLayer() {
		return 3;
	}

	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		public Particle createParticle(int particleID, World worldIn, double x, double y, double z, double speedX, double speedY, double speedZ, int... i) {
			return new Chalk_Particle(Minecraft.getMinecraft().getTextureManager(), worldIn, x, y, z, speedX, speedY, speedZ); }
	}
}
