package com.ayutaki.chinjufumod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class Server_CleanEraser {
	public static final StreamCodec<FriendlyByteBuf, Server_CleanEraser> CODEC = StreamCodec
			.ofMember(Server_CleanEraser::encode, Server_CleanEraser::decode);

	public Server_CleanEraser() { }
	
	public void encode(FriendlyByteBuf buffer) { }
	
	public static Server_CleanEraser decode(FriendlyByteBuf buffer) {
		return new Server_CleanEraser();
	}
	
	/* ServerPlayNetHandler */
	public static void handle(Server_CleanEraser message, CustomPayloadEvent.Context context) {
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			ItemStack offStack = player.getOffhandItem();
			
			offStack.hurtAndBreak(-10, player.getRandom(), player instanceof ServerPlayer user ? user : null,
					() -> { offStack.shrink(1); } );
		});
		context.setPacketHandled(true);
		//ChinjufuMod.LOGGER.info("ChinjufuMod clean Eraser SERVER.");
	}
}
