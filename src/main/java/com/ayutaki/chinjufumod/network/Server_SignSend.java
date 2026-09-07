package com.ayutaki.chinjufumod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class Server_SignSend {
	public static final StreamCodec<FriendlyByteBuf, Server_SignSend> CODEC = StreamCodec
			.ofMember(Server_SignSend::encode, Server_SignSend::decode);
	
	private BlockPos signPos;
	private boolean front;
	private String line1st, line2nd, line3rd, line4th;
	
	public Server_SignSend(BlockPos pos, boolean flag, String line0, String line1, String line2, String line3) {
		this.signPos = pos;
		this.front = flag;
		this.line1st = line0;
		this.line2nd = line1;
		this.line3rd = line2;
		this.line4th = line3;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(signPos);
		buffer.writeBoolean(front);
		buffer.writeUtf(line1st);
		buffer.writeUtf(line2nd);
		buffer.writeUtf(line3rd);
		buffer.writeUtf(line4th);
	}
	
	public static Server_SignSend decode(FriendlyByteBuf buffer) {
		return new Server_SignSend(buffer.readBlockPos(), buffer.readBoolean(), 
				buffer.readUtf(384), buffer.readUtf(384), buffer.readUtf(384), buffer.readUtf(384));
	}
	
	/* ServerPlayNetHandler */
	@SuppressWarnings("deprecation")
	public static void handle(Server_SignSend message, CustomPayloadEvent.Context context) {
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			ServerLevel world = player.serverLevel();
			BlockPos pos = message.signPos;
			
			if (world.hasChunkAt(pos)) {
				BlockEntity tileEntity = world.getBlockEntity(pos);
				
				if (!(tileEntity instanceof SignBlockEntity)) { return; }
				
				SignBlockEntity sign = (SignBlockEntity)tileEntity;
				sign.updateText(signText -> signText.setMessage(0, Component.literal(message.line1st)), message.front);
				sign.updateText(signText -> signText.setMessage(1, Component.literal(message.line2nd)), message.front);
				sign.updateText(signText -> signText.setMessage(2, Component.literal(message.line3rd)), message.front);
				sign.updateText(signText -> signText.setMessage(3, Component.literal(message.line4th)), message.front);

				sign.setChanged();
				sign.getLevel().sendBlockUpdated(sign.getBlockPos(), sign.getBlockState(), sign.getBlockState(), 3);
				//ChinjufuMod.LOGGER.info("ChinjufuMod edit Sign SERVER.");
			} 
		});
		context.setPacketHandled(true);
	}
}
