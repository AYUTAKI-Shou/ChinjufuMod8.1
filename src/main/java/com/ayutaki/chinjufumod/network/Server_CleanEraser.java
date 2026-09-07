package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class Server_CleanEraser implements CustomPacketPayload {

	public static final Type<Server_CleanEraser> TYPE = new Type<>(ChinjufuMod.id("server_clean_eraser"));
	public static final StreamCodec<FriendlyByteBuf, Server_CleanEraser> CODEC = StreamCodec
			.ofMember(Server_CleanEraser::encode, Server_CleanEraser::decode);

	public Server_CleanEraser() { }
	
	public void encode(FriendlyByteBuf buffer) { }
	
	public static Server_CleanEraser decode(FriendlyByteBuf buffer) {
		return new Server_CleanEraser();
	}
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
	
	/* ServerPlayNetHandler */
	public static void handle(Server_CleanEraser message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Player player = context.player();
			ItemStack offStack = player.getOffhandItem();
			
			if (player.level() instanceof ServerLevel server) {
				offStack.hurtAndBreak(-10, server, player instanceof ServerPlayer user ? user : null,
						consumer -> { offStack.shrink(1); });
			}
		});
	}
}
