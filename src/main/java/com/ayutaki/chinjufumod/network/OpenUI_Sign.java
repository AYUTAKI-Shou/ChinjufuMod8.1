package com.ayutaki.chinjufumod.network;

import java.util.UUID;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class OpenUI_Sign {
	public static final StreamCodec<FriendlyByteBuf, OpenUI_Sign> CODEC = StreamCodec
			.ofMember(OpenUI_Sign::encode, OpenUI_Sign::decode);
	
	public UUID playerUUID;
	public BlockPos boardPos;
	
	public OpenUI_Sign(UUID uuid, BlockPos pos) {
		this.playerUUID = uuid;
		this.boardPos = pos;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUUID(playerUUID);
		buffer.writeBlockPos(boardPos);
	}
	
	public static OpenUI_Sign decode(FriendlyByteBuf buffer) {
		return new OpenUI_Sign(buffer.readUUID(), buffer.readBlockPos());
	}
	
	/* ClientPlayNetHandler */
	public static void handle(OpenUI_Sign message, CustomPayloadEvent.Context context) {
		ServerPlayer player = context.getSender();
		
		if (context.isClientSide()) {
			if (!player.getUUID().equals(message.playerUUID)) return;
			
			context.enqueueWork(() -> {
				Level worldIn = player.level();
				BlockPos pos = message.boardPos;
				BlockState state = worldIn.getBlockState(pos);
				BlockEntity tileEntity = worldIn.getBlockEntity(pos);
						
				if (!(tileEntity instanceof SignBlockEntity)) {
					tileEntity = new SignBlockEntity(pos, state);
					tileEntity.setLevel(worldIn); }
				
				NetworkEvent_CM.setSignScreen((SignBlockEntity)tileEntity, player);
				//ChinjufuMod.LOGGER.info("ChinjufuMod edit Sign START.");
			});
		}
		context.setPacketHandled(true);
	}
}
