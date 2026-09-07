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

public class Client_WoodBoardUpdate {
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
		buffer.writeString(line1st);
		buffer.writeString(line2nd);
		buffer.writeString(line3rd);
		buffer.writeString(line4th);
		buffer.writeString(line5th);
		buffer.writeString(line6th);
		buffer.writeString(line7th);
	}
	
	public static Client_WoodBoardUpdate decode(PacketBuffer buffer) {
		return new Client_WoodBoardUpdate(buffer.readBlockPos(), buffer.readString(), buffer.readString(), buffer.readString(), 
				buffer.readString(), buffer.readString(), buffer.readString(), buffer.readString());
	}
	
	@SuppressWarnings("deprecation")
	public static void handle(Client_WoodBoardUpdate message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Minecraft mClient = Minecraft.getInstance();
				ClientWorld world = (ClientWorld) mClient.world;
				BlockPos pos = message.boardPos;
				
				if (world.isBlockLoaded(pos)) {
					TileEntity tileEntity = world.getTileEntity(pos);
					
					if (!(tileEntity instanceof WoodBoard_TileEntity)) { return; }
					
					WoodBoard_TileEntity board = (WoodBoard_TileEntity)tileEntity;
					board.setText(0, (ITextComponent)new StringTextComponent(message.line1st));
					board.setText(1, (ITextComponent)new StringTextComponent(message.line2nd));
					board.setText(2, (ITextComponent)new StringTextComponent(message.line3rd));
					board.setText(3, (ITextComponent)new StringTextComponent(message.line4th));
					board.setText(4, (ITextComponent)new StringTextComponent(message.line5th));
					board.setText(5, (ITextComponent)new StringTextComponent(message.line6th));
					board.setText(6, (ITextComponent)new StringTextComponent(message.line7th));
					//ChinjufuMod.LOGGER.info("ChinjufuMod edit WoodBoard CLIENT.");
				} 
			});
			context.setPacketHandled(true);
		} 
	}
}
