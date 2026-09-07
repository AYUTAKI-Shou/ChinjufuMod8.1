package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.util.text.ITextComponent;

public class NetworkEvent_CM {

	public static void severBlackBoardSend(BlackBoard_TileEntity board, ITextComponent line0, ITextComponent line1, ITextComponent line2, 
			ITextComponent line3, ITextComponent line4, ITextComponent line5) {
		ChinjufuMod.CHANNEL.sendToServer(new Sever_BlackBoardSend(board, line0, line1, line2, line3, line4, line5)); }
	
	public static void severWoodBoardSend(WoodBoard_TileEntity board, ITextComponent line0, ITextComponent line1, ITextComponent line2, 
			ITextComponent line3, ITextComponent line4, ITextComponent line5, ITextComponent line6) {
		ChinjufuMod.CHANNEL.sendToServer(new Sever_WoodBoardSend(board, line0, line1, line2, line3, line4, line5, line6)); }
}
