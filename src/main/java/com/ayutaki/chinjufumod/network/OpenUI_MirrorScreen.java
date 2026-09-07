package com.ayutaki.chinjufumod.network;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class OpenUI_MirrorScreen {
	public static final StreamCodec<FriendlyByteBuf, OpenUI_MirrorScreen> CODEC = StreamCodec
			.ofMember(OpenUI_MirrorScreen::encode, OpenUI_MirrorScreen::decode);
	
	public UUID playerUUID;
	
	public OpenUI_MirrorScreen(UUID uuid) { 
		this.playerUUID = uuid;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUUID(playerUUID);
	}
	
	public static OpenUI_MirrorScreen decode(FriendlyByteBuf buffer) {
		return new OpenUI_MirrorScreen(buffer.readUUID());
	}
	
	/* ClientPlayNetHandler */
	public static void handle(OpenUI_MirrorScreen message, CustomPayloadEvent.Context context) {
		
		if (context.isClientSide()) {
			Minecraft mClient = Minecraft.getInstance();
			LocalPlayer player = mClient.player;
			if (!player.getUUID().equals(message.playerUUID)) return;
			
			context.enqueueWork(() -> {
				NetworkEvent_CM.setMirrorScreen(player);
			});
		}
		context.setPacketHandled(true);
	}
}
