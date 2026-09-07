package com.ayutaki.chinjufumod.network;

import java.util.function.Supplier;

import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

public final class Client_WoodBoardUpdate {
	private BlockPos boardPos;
	private String line1st, line2nd, line3rd, line4th, line5th, line6th, line7th;
	
	public Client_WoodBoardUpdate(BlockPos pos, String line0, String line1, String line2, String line3, String line4, String line5, String line6) {
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
		buffer.writeUtf(line1st);
		buffer.writeUtf(line2nd);
		buffer.writeUtf(line3rd);
		buffer.writeUtf(line4th);
		buffer.writeUtf(line5th);
		buffer.writeUtf(line6th);
		buffer.writeUtf(line7th);
	}
	
	public static Client_WoodBoardUpdate decode(PacketBuffer buffer) {
		return new Client_WoodBoardUpdate(buffer.readBlockPos(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), 
				buffer.readUtf(), buffer.readUtf(), buffer.readUtf(), buffer.readUtf());
	}
	
	@SuppressWarnings("deprecation")
	public static void handle(Client_WoodBoardUpdate message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Minecraft mClient = Minecraft.getInstance();
				ClientWorld world = (ClientWorld) mClient.level;
				BlockPos pos = message.boardPos;
				
				if (world.hasChunkAt(pos)) {
					TileEntity tileEntity = world.getBlockEntity(pos);
					
					if (!(tileEntity instanceof WoodBoard_TileEntity)) { return; }
					
					WoodBoard_TileEntity board = (WoodBoard_TileEntity)tileEntity;
					board.setMessage(0, (ITextComponent)new StringTextComponent(message.line1st));
					board.setMessage(1, (ITextComponent)new StringTextComponent(message.line2nd));
					board.setMessage(2, (ITextComponent)new StringTextComponent(message.line3rd));
					board.setMessage(3, (ITextComponent)new StringTextComponent(message.line4th));
					board.setMessage(4, (ITextComponent)new StringTextComponent(message.line5th));
					board.setMessage(5, (ITextComponent)new StringTextComponent(message.line6th));
					board.setMessage(6, (ITextComponent)new StringTextComponent(message.line7th));
					//ChinjufuMod.LOGGER.info("ChinjufuMod edit WoodBoard CLIENT.");
				} 
			});
			context.setPacketHandled(true);
		} 
	}
}
