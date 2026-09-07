package com.ayutaki.chinjufumod.network;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class Server_GenEraser {

	public Server_GenEraser() { }
	
	public void encode(PacketBuffer buffer) { }
	
	public Server_GenEraser(PacketBuffer buffer) { }
	
	public static Server_GenEraser decode(PacketBuffer buffer) {
		return new Server_GenEraser();
	}
	
	/* ServerPlayNetHandler */
	public static void handle(Server_GenEraser message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
			context.enqueueWork(() -> {
				ServerPlayerEntity player = context.getSender();
				ItemStack offStack = player.getHeldItemOffhand();
				
				ItemStack take = new ItemStack(Items_Chinjufu.BOARD_ERASER, 1);
				if (!player.inventory.addItemStackToInventory(take)) { player.dropItem(take, false); }
				offStack.shrink(1);
			});
			context.setPacketHandled(true);
			//ChinjufuMod.LOGGER.info("ChinjufuMod gen Eraser SERVER.");
		} 
	}
}
