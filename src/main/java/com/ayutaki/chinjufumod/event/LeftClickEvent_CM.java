package com.ayutaki.chinjufumod.event;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Board_Eraser;
import com.ayutaki.chinjufumod.network.Server_CleanEraser;
import com.ayutaki.chinjufumod.network.Server_GenEraser;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LeftClickEvent_CM {

	/* This method only fires on the Client side. */
	@SubscribeEvent
	public void cleanEraser(LeftClickEmpty event) {
		PlayerEntity player = event.getPlayer();
		if (player != null) {
			
			ItemStack mainStack = player.getMainHandItem();
			ItemStack offStack = player.getOffhandItem();
			int offLife = offStack.getDamageValue();
			
			if (offStack.getItem() instanceof Board_Eraser && offLife >= 1 && mainStack.getItem() == Items.STICK) {
				
				World world = player.level;					
				player.playSound(SoundEvents_CM.CLEAN_ERASER, 1.0F, 1.0F + (0.1F * world.random.nextFloat()));
				Vector3d pos = player.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(player.getLookAngle().multiply(-0.75D, -0.75D, -0.75D));
				world.addParticle(ParticleTypes_CM.CHALK_PT, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
				
				boolean life11 = offLife > 10;
				if (life11) { ChinjufuMod.CHANNEL.sendToServer(new Server_CleanEraser()); }
				else { //!life11
					ChinjufuMod.CHANNEL.sendToServer(new Server_GenEraser()); }
			}
		}
	}
}
