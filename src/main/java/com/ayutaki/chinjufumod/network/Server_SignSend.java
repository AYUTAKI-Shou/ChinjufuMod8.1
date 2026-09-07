package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class Server_SignSend implements CustomPacketPayload {

	public static final Type<Server_SignSend> TYPE = new Type<>(ChinjufuMod.id("server_sign_send"));
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
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
	
	/* ServerPlayNetHandler */
	@SuppressWarnings("deprecation")
	public static void handle(Server_SignSend message, IPayloadContext context) {
		context.enqueueWork(() -> {
			Player player = context.player();
			Level world = player.level();
			BlockPos pos = message.signPos;
			
			if (world.hasChunkAt(pos)) {
				BlockEntity tileEntity = world.getBlockEntity(pos);
				
				if (!(tileEntity instanceof SignBlockEntity)) { return; }
				
				SignBlockEntity sign = (SignBlockEntity)tileEntity;
				sign.updateText(boardText -> boardText.setMessage(0, Component.literal(message.line1st)), message.front);
				sign.updateText(boardText -> boardText.setMessage(1, Component.literal(message.line2nd)), message.front);
				sign.updateText(boardText -> boardText.setMessage(2, Component.literal(message.line3rd)), message.front);
				sign.updateText(boardText -> boardText.setMessage(3, Component.literal(message.line4th)), message.front);

				sign.setChanged();
				sign.getLevel().sendBlockUpdated(sign.getBlockPos(), sign.getBlockState(), sign.getBlockState(), 3);
				//ChinjufuMod.LOGGER.info("ChinjufuMod edit Sign SERVER.");
			} 
		});
	}
}
