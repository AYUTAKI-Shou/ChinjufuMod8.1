package com.ayutaki.chinjufumod.network;

import java.util.UUID;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.gui.MirrorScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenUI_MirrorScreen implements CustomPacketPayload {

	public static final Type<OpenUI_MirrorScreen> TYPE = new Type<>(ChinjufuMod.id("open_ui_mirror"));
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
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
	
	/* ClientPlayNetHandler */
	public static void handle(OpenUI_MirrorScreen message, IPayloadContext context) {
		context.enqueueWork(() -> {
			openGUI(message);
		});
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void openGUI(OpenUI_MirrorScreen message) {
		Minecraft mClient = Minecraft.getInstance();
		LocalPlayer player = mClient.player;
		
		if (!player.getUUID().equals(message.playerUUID)) { return; }
		mClient.setScreen(new MirrorScreen(player));
	}
}
