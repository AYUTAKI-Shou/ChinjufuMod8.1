package com.ayutaki.chinjufumod.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.ayutaki.chinjufumod.gui.MirrorScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

public final class OpenUI_MirrorScreen {
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
	public static void handle(OpenUI_MirrorScreen message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			openGUI(message);
		});
		context.setPacketHandled(true);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void openGUI(OpenUI_MirrorScreen message) {
		Minecraft mClient = Minecraft.getInstance();
		LocalPlayer player = mClient.player;
		
		if (!player.getUUID().equals(message.playerUUID)) { return; }
		mClient.setScreen(new MirrorScreen(player));
	}
}
