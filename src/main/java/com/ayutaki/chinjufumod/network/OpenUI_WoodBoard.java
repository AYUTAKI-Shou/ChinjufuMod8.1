package com.ayutaki.chinjufumod.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public final class OpenUI_WoodBoard {
	public UUID playerUUID;
	public BlockPos boardPos;
	
	public OpenUI_WoodBoard(UUID uuid, BlockPos pos) {
		this.playerUUID = uuid;
		this.boardPos = pos;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUUID(playerUUID);
		buffer.writeBlockPos(boardPos);
	}
	
	public static OpenUI_WoodBoard decode(FriendlyByteBuf buffer) {
		return new OpenUI_WoodBoard(buffer.readUUID(), buffer.readBlockPos());
	}
	
	/* ClientPlayNetHandler */
	public static void handle(OpenUI_WoodBoard message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		Minecraft mClient = Minecraft.getInstance();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			LocalPlayer player = mClient.player;
			if (!player.getUUID().equals(message.playerUUID)) { return; }
			
			context.enqueueWork(() -> {
				Level worldIn = player.level;
				BlockPos pos = message.boardPos;
				BlockState state = worldIn.getBlockState(pos);
				BlockEntity tileEntity = worldIn.getBlockEntity(pos);
						
				if (!(tileEntity instanceof WoodBoard_TileEntity)) {
					tileEntity = new WoodBoard_TileEntity(pos, state);
					tileEntity.setLevel(worldIn); }
				
				NetworkEvent_CM.setWoodBoardScreen((WoodBoard_TileEntity)tileEntity);
				//ChinjufuMod.LOGGER.info("ChinjufuMod edit WoodBoard START.");
			});
		} 
		context.setPacketHandled(true);
	}
}
