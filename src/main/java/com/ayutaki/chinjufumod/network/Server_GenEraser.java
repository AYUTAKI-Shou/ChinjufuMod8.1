package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class Server_GenEraser {
	public static final StreamCodec<FriendlyByteBuf, Server_GenEraser> CODEC = StreamCodec
			.ofMember(Server_GenEraser::encode, Server_GenEraser::decode);

	public Server_GenEraser() { }
	
	public void encode(FriendlyByteBuf buffer) { }
	
	public static Server_GenEraser decode(FriendlyByteBuf buffer) {
		return new Server_GenEraser();
	}
	
	/* ServerPlayNetHandler */
	public static void handle(Server_GenEraser message, CustomPayloadEvent.Context context) {
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
