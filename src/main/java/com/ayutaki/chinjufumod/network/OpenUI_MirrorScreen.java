package com.ayutaki.chinjufumod.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.ayutaki.chinjufumod.gui.MirrorScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

public final class OpenUI_MirrorScreen {
	public UUID playerUUID;
	
	public OpenUI_MirrorScreen(UUID uuid) {
		this.playerUUID = uuid;
	}
	
	public void encode(PacketBuffer buffer) {
		buffer.writeUUID(playerUUID);
	}
	
	public static OpenUI_MirrorScreen decode(PacketBuffer buffer) {
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
		ClientPlayerEntity player = mClient.player;
		
		if (!player.getUUID().equals(message.playerUUID)) { return; }
		Minecraft.getInstance().setScreen(new MirrorScreen(player));
	}
}
