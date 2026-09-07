package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class Server_GenEraser implements CustomPacketPayload {

	public static final Type<Server_GenEraser> TYPE = new Type<>(ChinjufuMod.id("server_gen_eraser"));
	public static final StreamCodec<FriendlyByteBuf, Server_GenEraser> CODEC = StreamCodec
			.ofMember(Server_GenEraser::encode, Server_GenEraser::decode);

	public Server_GenEraser() { }
	
	public void encode(FriendlyByteBuf buffer) { }
	
	public static Server_GenEraser decode(FriendlyByteBuf buffer) {
		return new Server_GenEraser();
	}
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
	
	/* ServerPlayNetHandler */
	public static void handle(Server_GenEraser message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Player player = context.player();
			ItemStack offStack = player.getOffhandItem();
			
			ItemStack take = new ItemStack(Items_Chinjufu.BOARD_ERASER.get(), 1);
			if (!player.getInventory().add(take)) { player.drop(take, false); }
			offStack.shrink(1);
		});
	}
}
