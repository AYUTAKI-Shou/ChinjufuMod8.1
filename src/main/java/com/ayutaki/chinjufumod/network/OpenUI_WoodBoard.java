package com.ayutaki.chinjufumod.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public final class OpenUI_WoodBoard {
	public UUID playerUUID;
	public BlockPos boardPos;
	
	public OpenUI_WoodBoard(UUID uuid, BlockPos pos) {
		this.playerUUID = uuid;
		this.boardPos = pos;
	}
	
	public void encode(PacketBuffer buffer) {
		buffer.writeUUID(playerUUID);
		buffer.writeBlockPos(boardPos);
	}
	
	public static OpenUI_WoodBoard decode(PacketBuffer buffer) {
		return new OpenUI_WoodBoard(buffer.readUUID(), buffer.readBlockPos());
	}
	
	/* ClientPlayNetHandler */
	public static void handle(OpenUI_WoodBoard message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		Minecraft mClient = Minecraft.getInstance();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			ClientPlayerEntity player = mClient.player;
			if (!player.getUUID().equals(message.playerUUID)) { return; }
			
			context.enqueueWork(() -> {
				World worldIn = player.level;
				BlockPos pos = message.boardPos;
				TileEntity tileEntity = worldIn.getBlockEntity(pos);
						
				if (!(tileEntity instanceof WoodBoard_TileEntity)) {
					tileEntity = new WoodBoard_TileEntity();
					tileEntity.setLevelAndPosition(worldIn, pos); }
				
				NetworkEvent_CM.setWoodBoardScreen((WoodBoard_TileEntity)tileEntity);
				//ChinjufuMod.LOGGER.info("ChinjufuMod edit WoodBoard START.");
			});
		} 
		context.setPacketHandled(true);
	}
}
