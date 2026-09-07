package com.ayutaki.chinjufumod.event;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Board_Eraser;
import com.ayutaki.chinjufumod.network.Server_CleanEraser;
import com.ayutaki.chinjufumod.network.Server_GenEraser;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

public class LeftClickEvent_CM {

	/* This method only fires on the Client side. */
	@SubscribeEvent
	public void cleanEraser(LeftClickEmpty event) {
		Player player = event.getEntity();
		if (player != null) {
			
			ItemStack mainStack = player.getMainHandItem();
			ItemStack offStack = player.getOffhandItem();
			int offLife = offStack.getDamageValue();
			
			if (offStack.getItem() instanceof Board_Eraser && offLife >= 1 && mainStack.getItem() == Items.STICK) {
				
				Level world = player.level();
				player.playSound(SoundEvents_CM.CLEAN_ERASER.get(), 1.0F, 1.0F + (0.1F * world.random.nextFloat()));
				Vec3 pos = player.getEyePosition(1.0F).add(0.0D, -0.15D, 0.0D).subtract(player.getLookAngle().multiply(-0.75D, -0.75D, -0.75D));
				world.addParticle((ParticleOptions) ParticleTypes_CM.CHALK_PT.get(), pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);

				boolean life11 = offLife > 10;
				if (life11) { ChinjufuMod.CHANNEL.send(new Server_CleanEraser(), PacketDistributor.SERVER.noArg()); }
				if (!life11) { ChinjufuMod.CHANNEL.send(new Server_GenEraser(), PacketDistributor.SERVER.noArg()); }
			}
		}
	}
}
