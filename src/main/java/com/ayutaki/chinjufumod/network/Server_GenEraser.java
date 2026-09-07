package com.ayutaki.chinjufumod.network;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public final class Server_GenEraser {

	public Server_GenEraser() { }
	
	public void encode(FriendlyByteBuf buffer) { }
	
	public Server_GenEraser(FriendlyByteBuf buffer) { }
	
	public static Server_GenEraser decode(FriendlyByteBuf buffer) {
		return new Server_GenEraser();
	}
	
	/* ServerPlayNetHandler */
	public static void handle(Server_GenEraser message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
			context.enqueueWork(() -> {
				ServerPlayer player = context.getSender();
				ItemStack offStack = player.getOffhandItem();
				
				ItemStack take = new ItemStack(Items_Chinjufu.BOARD_ERASER.get(), 1);
				if (!player.getInventory().add(take)) { player.drop(take, false); }
				offStack.shrink(1);
			});
			context.setPacketHandled(true);
			//ChinjufuMod.LOGGER.info("ChinjufuMod gen Eraser SERVER.");
		} 
	}
}
