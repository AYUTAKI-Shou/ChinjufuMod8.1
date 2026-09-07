package com.ayutaki.chinjufumod.network;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public final class Server_CleanEraser {

	public Server_CleanEraser() { }
	
	public void encode(FriendlyByteBuf buffer) { }
	
	public Server_CleanEraser(FriendlyByteBuf buffer) { }
	
	public static Server_CleanEraser decode(FriendlyByteBuf buffer) {
		return new Server_CleanEraser();
	}
	
	/* ServerPlayNetHandler */
	public static void handle(Server_CleanEraser message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
			context.enqueueWork(() -> {
				ServerPlayer player = context.getSender();
				ItemStack offStack = player.getOffhandItem();
				
				offStack.hurtAndBreak(-10, player, user -> { offStack.shrink(1); } );
			});
			context.setPacketHandled(true);
			//ChinjufuMod.LOGGER.info("ChinjufuMod clean Eraser SERVER.");
		} 
	}
}
