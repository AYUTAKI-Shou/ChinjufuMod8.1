package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class Server_BlackBoardSend {
	public static final StreamCodec<FriendlyByteBuf, Server_BlackBoardSend> CODEC = StreamCodec
			.ofMember(Server_BlackBoardSend::encode, Server_BlackBoardSend::decode);
	
	private BlockPos boardPos;
	private String line1st, line2nd, line3rd, line4th, line5th, line6th;
	
	public Server_BlackBoardSend(BlockPos pos, String line0, String line1, String line2, String line3, String line4, String line5) {
		this.boardPos = pos;
		this.line1st = line0;
		this.line2nd = line1;
		this.line3rd = line2;
		this.line4th = line3;
		this.line5th = line4;
		this.line6th = line5;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(boardPos);
		buffer.writeUtf(line1st);
		buffer.writeUtf(line2nd);
		buffer.writeUtf(line3rd);
		buffer.writeUtf(line4th);
		buffer.writeUtf(line5th);
		buffer.writeUtf(line6th);
	}
	
	public static Server_BlackBoardSend decode(FriendlyByteBuf buffer) {
		return new Server_BlackBoardSend(buffer.readBlockPos(), buffer.readUtf(384), buffer.readUtf(384), buffer.readUtf(384), 
				buffer.readUtf(384), buffer.readUtf(384), buffer.readUtf(384));
	}
	
	/* ServerPlayNetHandler */
	@SuppressWarnings("deprecation")
	public static void handle(Server_BlackBoardSend message, CustomPayloadEvent.Context context) {
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			ServerLevel world = player.serverLevel();
			BlockPos pos = message.boardPos;
			
			if (world.hasChunkAt(pos)) {
				BlockEntity tileEntity = world.getBlockEntity(pos);
				
				if (!(tileEntity instanceof BlackBoard_TileEntity)) { return; }
				
				BlackBoard_TileEntity board = (BlackBoard_TileEntity)tileEntity;
				board.updateText(boardText -> boardText.setMessage(0, Component.literal(message.line1st)));
				board.updateText(boardText -> boardText.setMessage(1, Component.literal(message.line2nd)));
				board.updateText(boardText -> boardText.setMessage(2, Component.literal(message.line3rd)));
				board.updateText(boardText -> boardText.setMessage(3, Component.literal(message.line4th)));
				board.updateText(boardText -> boardText.setMessage(4, Component.literal(message.line5th)));
				board.updateText(boardText -> boardText.setMessage(5, Component.literal(message.line6th)));
				board.markUpdated();
				//ChinjufuMod.LOGGER.info("ChinjufuMod edit BlackBoard SERVER.");
			} 
		});
		context.setPacketHandled(true);
	}
}
