package com.ayutaki.chinjufumod.network;

import java.util.UUID;

import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class OpenUI_BlackBoard {
	public static final StreamCodec<FriendlyByteBuf, OpenUI_BlackBoard> CODEC = StreamCodec
			.ofMember(OpenUI_BlackBoard::encode, OpenUI_BlackBoard::decode);
	
	public UUID playerUUID;
	public BlockPos boardPos;
	
	public OpenUI_BlackBoard(UUID uuid, BlockPos pos) {
		this.playerUUID = uuid;
		this.boardPos = pos;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUUID(playerUUID);
		buffer.writeBlockPos(boardPos);
	}
	
	public static OpenUI_BlackBoard decode(FriendlyByteBuf buffer) {
		return new OpenUI_BlackBoard(buffer.readUUID(), buffer.readBlockPos());
	}
	
	/* ClientPlayNetHandler */
	public static void handle(OpenUI_BlackBoard message, CustomPayloadEvent.Context context) {
		Minecraft mClient = Minecraft.getInstance();
		
		if (context.isClientSide()) {
			LocalPlayer player = mClient.player;
			if (!player.getUUID().equals(message.playerUUID)) return;
			
			context.enqueueWork(() -> {
				Level worldIn = player.level();
				BlockPos pos = message.boardPos;
				BlockState state = worldIn.getBlockState(pos);
				BlockEntity tileEntity = worldIn.getBlockEntity(pos);
						
				if (!(tileEntity instanceof BlackBoard_TileEntity)) {
					tileEntity = new BlackBoard_TileEntity(pos, state);
					tileEntity.setLevel(worldIn); }
				
				NetworkEvent_CM.setBlackBoardScreen((BlackBoard_TileEntity)tileEntity);
				//ChinjufuMod.LOGGER.info("ChinjufuMod edit BlackBoard START.");
			});
		}
		context.setPacketHandled(true);
	}
}
