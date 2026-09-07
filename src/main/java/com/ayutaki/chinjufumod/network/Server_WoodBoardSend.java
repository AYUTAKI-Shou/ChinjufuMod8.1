package com.ayutaki.chinjufumod.network;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

public final class Server_WoodBoardSend {
	private BlockPos boardPos;
	private String line1st, line2nd, line3rd, line4th, line5th, line6th, line7th;
	
	public Server_WoodBoardSend(BlockPos pos, String line0, String line1, String line2, String line3, String line4, String line5, String line6) {
		this.boardPos = pos;
		this.line1st = line0;
		this.line2nd = line1;
		this.line3rd = line2;
		this.line4th = line3;
		this.line5th = line4;
		this.line6th = line5;
		this.line7th = line6;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBlockPos(boardPos);
		buffer.writeUtf(line1st);
		buffer.writeUtf(line2nd);
		buffer.writeUtf(line3rd);
		buffer.writeUtf(line4th);
		buffer.writeUtf(line5th);
		buffer.writeUtf(line6th);
		buffer.writeUtf(line7th);
	}
	
	public static Server_WoodBoardSend decode(FriendlyByteBuf buffer) {
		return new Server_WoodBoardSend(buffer.readBlockPos(), buffer.readUtf(384), buffer.readUtf(384), buffer.readUtf(384), 
				buffer.readUtf(384), buffer.readUtf(384), buffer.readUtf(384), buffer.readUtf(384));
	}
	
	/* ServerPlayNetHandler */
	@SuppressWarnings("deprecation")
	public static void handle(Server_WoodBoardSend message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
			context.enqueueWork(() -> {
				ServerPlayer player = context.getSender();
				ServerLevel world = player.getLevel();
				BlockPos pos = message.boardPos;
				
				if (world.hasChunkAt(pos)) {
					BlockState state = world.getBlockState(pos);
					BlockEntity tileEntity = world.getBlockEntity(pos);
					
					if (!(tileEntity instanceof WoodBoard_TileEntity)) { return; }
					
					WoodBoard_TileEntity board = (WoodBoard_TileEntity)tileEntity;
					if (board.getPlayerWhoMayEdit() != player.getUUID()) { return; } //!board.isEditable() Exclude for Multiverse.
					
					board.setMessage(0, (Component)new TextComponent(message.line1st));
					board.setMessage(1, (Component)new TextComponent(message.line2nd));
					board.setMessage(2, (Component)new TextComponent(message.line3rd));
					board.setMessage(3, (Component)new TextComponent(message.line4th));
					board.setMessage(4, (Component)new TextComponent(message.line5th));
					board.setMessage(5, (Component)new TextComponent(message.line6th));
					board.setMessage(6, (Component)new TextComponent(message.line7th));
					board.setChanged();
					world.sendBlockUpdated(pos, state, state, 3);
					NetworkEvent_CM.clientWoodBoardUpdate(board.getBlockPos(), board.boardText[0], board.boardText[1], board.boardText[2], 
							board.boardText[3], board.boardText[4], board.boardText[5], board.boardText[6]);
					//ChinjufuMod.LOGGER.info("ChinjufuMod edit WoodBoard SERVER.");
				} 
			});
			context.setPacketHandled(true);
		} 
	}
}
