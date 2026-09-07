package com.ayutaki.chinjufumod.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Particle_Wax extends SpriteTexturedParticle {
	
	private Particle_Wax(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double speedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, speedIn);
		float f = this.rand.nextFloat() * 0.1F + 0.2F;
		this.particleRed = f;
		this.particleGreen = f;
		this.particleBlue = f;
		this.setSize(0.02F, 0.02F);
		this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
		this.motionX *= (double)0.02F;
		this.motionY *= (double)0.02F;
		this.motionZ *= (double)0.02F;
		this.maxAge = (int)(10.0D / (Math.random() * 0.8D + 0.2D));
	}

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}

	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.maxAge-- <= 0) {
			this.setExpired();
		} 
		else {
			this.move(this.motionX, this.motionY, this.motionZ);
			this.motionX *= 0.49D;
			this.motionY *= 0.49D;
			this.motionZ *= 0.49D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite Isprite) {
			this.spriteSet = Isprite;
		}

		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			Particle_Wax spriteParticle = new Particle_Wax(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			spriteParticle.selectSpriteRandomly(this.spriteSet);
			spriteParticle.setColor(0.91F, 0.55F, 0.08F);
			return spriteParticle;
		}
	}
}
