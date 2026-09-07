package com.ayutaki.chinjufumod.network;

import java.util.UUID;

import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class OpenUI_WoodBoard {
	public static final StreamCodec<FriendlyByteBuf, OpenUI_WoodBoard> CODEC = StreamCodec
			.ofMember(OpenUI_WoodBoard::encode, OpenUI_WoodBoard::decode);
	
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
	public static void handle(OpenUI_WoodBoard message, CustomPayloadEvent.Context context) {
		Minecraft mClient = Minecraft.getInstance();
		
		if (context.isClientSide()) {
			LocalPlayer player = mClient.player;
			if (!player.getUUID().equals(message.playerUUID)) return;
			
			context.enqueueWork(() -> {
				Level worldIn = player.level();
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
