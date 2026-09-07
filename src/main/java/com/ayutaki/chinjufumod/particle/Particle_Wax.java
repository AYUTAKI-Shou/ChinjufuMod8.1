package com.ayutaki.chinjufumod.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Particle_Wax extends SpriteTexturedParticle {
	
	private Particle_Wax(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeed, double ySpeed, double zSpeed) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeed, ySpeed, zSpeed);
		float f = this.random.nextFloat() * 0.1F + 0.2F;
		this.rCol = f;
		this.gCol = f;
		this.bCol = f;
		this.setSize(0.02F, 0.02F);
		this.quadSize *= 0.75F;
		this.xd *= (double)0.02F;
		this.yd *= (double)0.02F;
		this.zd *= (double)0.02F;
		this.lifetime = (int)(10.0D / (Math.random() * 0.8D + 0.2D));
	}

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().move(x, y, z));
		this.setLocationFromBoundingbox();
	}

	public void tick() {
		this.xo = this.x;
		this.yo = this.y;
		this.zo = this.z;
		if (this.lifetime-- <= 0) {
			this.remove();
		} 
		else {
			this.move(this.xd, this.yd, this.zd);
			this.xd *= 0.49D;
			this.yd *= 0.49D;
			this.zd *= 0.49D;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite sprite;

		public Factory(IAnimatedSprite Isprite) {
			this.sprite = Isprite;
		}

		public Particle createParticle(BasicParticleType type, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			Particle_Wax spriteParticle = new Particle_Wax(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			spriteParticle.pickSprite(this.sprite);
			spriteParticle.setColor(0.91F, 0.55F, 0.08F);
			return spriteParticle;
		}
	}
}
