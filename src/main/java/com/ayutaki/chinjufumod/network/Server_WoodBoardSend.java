package com.ayutaki.chinjufumod.network;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public class Server_WoodBoardSend {
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
	
	public void encode(PacketBuffer buffer) {
		buffer.writeBlockPos(boardPos);
		buffer.writeString(line1st);
		buffer.writeString(line2nd);
		buffer.writeString(line3rd);
		buffer.writeString(line4th);
		buffer.writeString(line5th);
		buffer.writeString(line6th);
		buffer.writeString(line7th);
	}
	
	public static Server_WoodBoardSend decode(PacketBuffer buffer) {
		return new Server_WoodBoardSend(buffer.readBlockPos(), buffer.readString(384), buffer.readString(384), buffer.readString(384), 
				buffer.readString(384), buffer.readString(384), buffer.readString(384), buffer.readString(384));
	}
	
	/* ServerPlayNetHandler */
	@SuppressWarnings("deprecation")
	public static void handle(Server_WoodBoardSend message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.SERVER) {
			context.enqueueWork(() -> {
				ServerPlayerEntity player = context.getSender();
				ServerWorld world = player.getServerWorld();
				BlockPos pos = message.boardPos;
				
				if (world.isBlockLoaded(pos)) {
					BlockState state = world.getBlockState(pos);
					TileEntity tileEntity = world.getTileEntity(pos);
					
					if (!(tileEntity instanceof WoodBoard_TileEntity)) { return; }
					
					WoodBoard_TileEntity board = (WoodBoard_TileEntity)tileEntity;
					if (board.getPlayer() != player) { return; } //!board.isEditable() Exclude for Multiverse.
					
					board.setText(0, (ITextComponent)new StringTextComponent(message.line1st));
					board.setText(1, (ITextComponent)new StringTextComponent(message.line2nd));
					board.setText(2, (ITextComponent)new StringTextComponent(message.line3rd));
					board.setText(3, (ITextComponent)new StringTextComponent(message.line4th));
					board.setText(4, (ITextComponent)new StringTextComponent(message.line5th));
					board.setText(5, (ITextComponent)new StringTextComponent(message.line6th));
					board.setText(6, (ITextComponent)new StringTextComponent(message.line7th));
					board.markDirty();
					world.notifyBlockUpdate(pos, state, state, 3);
					NetworkEvent_CM.clientWoodBoardUpdate(board.getPos(), board.boardText[0], board.boardText[1], board.boardText[2], 
							board.boardText[3], board.boardText[4], board.boardText[5], board.boardText[6]);
					//ChinjufuMod.LOGGER.info("ChinjufuMod edit WoodBoard SERVER.");
				} 
			});
			context.setPacketHandled(true);
		} 
	}
}
