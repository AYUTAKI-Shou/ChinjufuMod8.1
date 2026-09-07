package com.ayutaki.chinjufumod.network;

import java.util.function.Supplier;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class Server_CleanEraser {

	public Server_CleanEraser() { }
	
	public void encode(PacketBuffer buffer) { }
	
	public Server_CleanEraser(PacketBuffer buffer) { }
	
	public static Server_CleanEraser decode(PacketBuffer buffer) {
		return new Server_CleanEraser();
	}
	
	/* ServerPlayNetHandler */
	public static void handle(Server_CleanEraser message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
			context.enqueueWork(() -> {
				ServerPlayerEntity player = context.getSender();
				ItemStack offStack = player.getHeldItemOffhand();
				
				offStack.damageItem(-10, player, user -> { offStack.shrink(1); } );
			});
			context.setPacketHandled(true);
			//ChinjufuMod.LOGGER.info("ChinjufuMod clean Eraser SERVER.");
		} 
	}
}
