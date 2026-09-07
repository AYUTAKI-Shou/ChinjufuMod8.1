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

public class OpenUI_MirrorScreen {
	public UUID playerUUID;
	
	public OpenUI_MirrorScreen(UUID uuid) {
		this.playerUUID = uuid;
	}
	
	public void encode(PacketBuffer buffer) {
		buffer.writeUniqueId(playerUUID);
	}
	
	public static OpenUI_MirrorScreen decode(PacketBuffer buffer) {
		return new OpenUI_MirrorScreen(buffer.readUniqueId());
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
		
		if (!player.getUniqueID().equals(message.playerUUID)) { return; }
		Minecraft.getInstance().displayGuiScreen(new MirrorScreen(player));
	}
}
