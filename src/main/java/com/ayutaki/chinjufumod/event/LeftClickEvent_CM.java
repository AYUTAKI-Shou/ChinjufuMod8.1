package com.ayutaki.chinjufumod.event;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Board_Eraser;
import com.ayutaki.chinjufumod.network.Sever_CleanEraser;
import com.ayutaki.chinjufumod.network.Sever_GenEraser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LeftClickEvent_CM {

	/* This method only fires on the Client side. */
	@SubscribeEvent
	public void cleanEraser(LeftClickEmpty event) {
		EntityPlayer player = event.getEntityPlayer();
		if (player != null) {
			
			ItemStack mainStack = player.getHeldItemMainhand();
			ItemStack offStack = player.getHeldItemOffhand();
			int offLife = offStack.getItemDamage();
			
			if (offStack.getItem() instanceof Board_Eraser && offLife >= 1 && mainStack.getItem() == Items.STICK) {
				
				World world = player.world;
				player.playSound(SoundEvents_CM.CLEAN_ERASER, 1.0F, 1.0F + (0.1F * world.rand.nextFloat()));
				this.chalkParticle(player);
				
				boolean life11 = offLife > 10;
				if (life11) { ChinjufuMod.CHANNEL.sendToServer(new Sever_CleanEraser()); }
				if (!life11) { ChinjufuMod.CHANNEL.sendToServer(new Sever_GenEraser()); }
			}
		}
	}
	
	public void chalkParticle(EntityPlayer player) {
		double d0 = (double)(-MathHelper.sin(player.rotationYaw * 0.017453292F));
		double d1 = (double)MathHelper.cos(player.rotationYaw * 0.017453292F);

		double fixY = 0.25D;
		double posX = player.posX + d0;
		double posY = player.posY + (double)player.height * 0.5D + fixY;
		double posZ = player.posZ + d1;
		
		ChinjufuMod.PROXY.spawnParticle(ParticleTypes_CM.CHALK_PT, posX, posY, posZ, d0, 0.0D + fixY, d1);
	}
	/*World world = player.world;
	world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, player.posX + d0, player.posY + (double)player.height * 0.5D, player.posZ + d1, d0, 0.0D, d1); */
}
