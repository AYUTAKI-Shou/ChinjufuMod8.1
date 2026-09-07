package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.network.AbstractMessage.AbstractServerMessage;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class Sever_WoodBoardSend extends AbstractServerMessage<Sever_WoodBoardSend> implements IPacket<Sever_WoodBoardSend> {
	private BlockPos boardPos;
	private ITextComponent line1st, line2nd, line3rd, line4th, line5th, line6th, line7th;

	public Sever_WoodBoardSend() { }

	public Sever_WoodBoardSend(WoodBoard_TileEntity board, ITextComponent line0, ITextComponent line1, ITextComponent line2, 
			ITextComponent line3, ITextComponent line4, ITextComponent line5, ITextComponent line6) {
		this.boardPos = board.getPos();
		this.line1st = line0;
		this.line2nd = line1;
		this.line3rd = line2;
		this.line4th = line3;
		this.line5th = line4;
		this.line6th = line5;
		this.line7th = line6;
	}

	@Override
	public Sever_WoodBoardSend decode(PacketBuffer buffer) {
		this.boardPos = buffer.readBlockPos();
		this.line1st = new TextComponentString(ByteBufUtils.readUTF8String(buffer));
		this.line2nd = new TextComponentString(ByteBufUtils.readUTF8String(buffer));
		this.line3rd = new TextComponentString(ByteBufUtils.readUTF8String(buffer));
		this.line4th = new TextComponentString(ByteBufUtils.readUTF8String(buffer));
		this.line5th = new TextComponentString(ByteBufUtils.readUTF8String(buffer));
		this.line6th = new TextComponentString(ByteBufUtils.readUTF8String(buffer));
		this.line7th = new TextComponentString(ByteBufUtils.readUTF8String(buffer));
		return this;
	}
	
	@Override
	public void encode(Sever_WoodBoardSend message, PacketBuffer buffer) {
		buffer.writeBlockPos(message.boardPos);
		ByteBufUtils.writeUTF8String(buffer, line1st.getFormattedText());
		ByteBufUtils.writeUTF8String(buffer, line2nd.getFormattedText());
		ByteBufUtils.writeUTF8String(buffer, line3rd.getFormattedText());
		ByteBufUtils.writeUTF8String(buffer, line4th.getFormattedText());
		ByteBufUtils.writeUTF8String(buffer, line5th.getFormattedText());
		ByteBufUtils.writeUTF8String(buffer, line6th.getFormattedText());
		ByteBufUtils.writeUTF8String(buffer, line7th.getFormattedText());
	}
	
	@Override
	public void handle(Sever_WoodBoardSend message, EntityPlayer player) {
		World world = player.world;
		TileEntity tileEntity = world.getTileEntity(message.boardPos);
		
		if(!(tileEntity instanceof WoodBoard_TileEntity)) { return; }
		WoodBoard_TileEntity board = (WoodBoard_TileEntity)tileEntity;
		board.boardText[0] = message.line1st;
		board.boardText[1] = message.line2nd;
		board.boardText[2] = message.line3rd;
		board.boardText[3] = message.line4th;
		board.boardText[4] = message.line5th;
		board.boardText[5] = message.line6th;
		board.boardText[6] = message.line7th;
		
		IBlockState state = world.getBlockState(message.boardPos);
		world.notifyBlockUpdate(message.boardPos, state, state, 3);
		board.markDirty();
		//ChinjufuMod.LOGGER.info("ChinjufuMod edit WoodBoard SERVER.");
	}
}
