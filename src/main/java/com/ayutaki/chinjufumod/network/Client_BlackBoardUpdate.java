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

public class Client_BlackBoardUpdate {
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
		buffer.writeString(line1st);
		buffer.writeString(line2nd);
		buffer.writeString(line3rd);
		buffer.writeString(line4th);
		buffer.writeString(line5th);
		buffer.writeString(line6th);
	}
	
	public static Client_BlackBoardUpdate decode(PacketBuffer buffer) {
		return new Client_BlackBoardUpdate(buffer.readBlockPos(), buffer.readString(), buffer.readString(), buffer.readString(), 
				buffer.readString(), buffer.readString(), buffer.readString());
	}
	
	@SuppressWarnings("deprecation")
	public static void handle(Client_BlackBoardUpdate message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		
		if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
			context.enqueueWork(() -> {
				Minecraft mClient = Minecraft.getInstance();
				ClientWorld world = (ClientWorld) mClient.world;
				BlockPos pos = message.boardPos;
				
				if (world.isBlockLoaded(pos)) {
					TileEntity tileEntity = world.getTileEntity(pos);
					
					if (!(tileEntity instanceof BlackBoard_TileEntity)) { return; }
					
					BlackBoard_TileEntity board = (BlackBoard_TileEntity)tileEntity;
					board.setText(0, (ITextComponent)new StringTextComponent(message.line1st));
					board.setText(1, (ITextComponent)new StringTextComponent(message.line2nd));
					board.setText(2, (ITextComponent)new StringTextComponent(message.line3rd));
					board.setText(3, (ITextComponent)new StringTextComponent(message.line4th));
					board.setText(4, (ITextComponent)new StringTextComponent(message.line5th));
					board.setText(5, (ITextComponent)new StringTextComponent(message.line6th));
					//ChinjufuMod.LOGGER.info("ChinjufuMod edit BlackBoard CLIENT.");
				} 
			});
			context.setPacketHandled(true);
		} 
	}
}
