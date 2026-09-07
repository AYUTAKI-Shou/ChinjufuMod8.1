package com.ayutaki.chinjufumod.network;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public final class Client_BlackBoardUpdate {
	private BlockPos boardPos;
	private String line1st, line2nd, line3rd, line4th, line5th, line6th;
	
	public Client_BlackBoardUpdate(BlockPos pos, String line0, String line1, String line2, String line3, String line4, String line5) {
		this.boardPos = pos;
		this.line1st = line0;
		this.line2nd = line1;
		this.line3rd = line2;
		this.line4th = line3;
		this.line5th = line4;
		this.line6th = line5;
	}
	
	public void encode(PacketBuffer buffer) {
		buffer.writeBlockPos(boardPos);
		buffer.writeUtf(line1st);
		buffer.writeUtf(line2nd);
		buffer.writeUtf(line3rd);
		buffer.writeUtf(line4th);
		buffer.writeUtf(line5th);
		buffer.writeUtf(line6th);
	}
	
	public static Client_BlackBoardUpdate decode(PacketBuffer buffer) {
		return new Client_BlackBoardUpdate(buffer.readBlockPos(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), 
				buffer.readUtf(), buffer.readUtf(), buffer.readUtf());
	}
	
	@SuppressWarnings("deprecation")
	public static void handle(Client_BlackBoardUpdate message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Minecraft mClient = Minecraft.getInstance();
				ClientWorld world = (ClientWorld) mClient.level;
				BlockPos pos = message.boardPos;
				
				if (world.hasChunkAt(pos)) {
					TileEntity tileEntity = world.getBlockEntity(pos);
					
					if (!(tileEntity instanceof BlackBoard_TileEntity)) { return; }
					
					BlackBoard_TileEntity board = (BlackBoard_TileEntity)tileEntity;
					board.setMessage(0, (ITextComponent)new StringTextComponent(message.line1st));
					board.setMessage(1, (ITextComponent)new StringTextComponent(message.line2nd));
					board.setMessage(2, (ITextComponent)new StringTextComponent(message.line3rd));
					board.setMessage(3, (ITextComponent)new StringTextComponent(message.line4th));
					board.setMessage(4, (ITextComponent)new StringTextComponent(message.line5th));
					board.setMessage(5, (ITextComponent)new StringTextComponent(message.line6th));
					//ChinjufuMod.LOGGER.info("ChinjufuMod edit BlackBoard CLIENT.");
				} 
			});
			context.setPacketHandled(true);
		} 
	}
}
