package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.gui.BlackBoard_Screen;
import com.ayutaki.chinjufumod.gui.MirrorScreen;
import com.ayutaki.chinjufumod.gui.WoodBoard_Screen;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;

public class NetworkEvent_CM {

	@OnlyIn(Dist.CLIENT)
	public static void setMirrorScreen(Player playerIn) {
		Minecraft.getInstance().setScreen(new MirrorScreen(playerIn)); }
	
	
	/* BlackBoard **/
	@OnlyIn(Dist.CLIENT)
	public static void setBlackBoardScreen(BlackBoard_TileEntity board) {
		Minecraft.getInstance().setScreen(new BlackBoard_Screen(board, true)); }
	
	public static void severBlackBoardSend(BlockPos pos, String line1st, String line2nd, String line3rd, String line4th, String line5th, String line6th) {
		ChinjufuMod.CHANNEL.sendToServer(new Server_BlackBoardSend(pos, line1st, line2nd, line3rd, line4th, line5th, line6th)); }
	
	public static void clientBlackBoardUpdate(BlockPos pos, Component line1st, Component line2nd, Component line3rd, 
			Component line4th, Component line5th, Component line6th) {
		ChinjufuMod.CHANNEL.send(PacketDistributor.ALL.noArg(), new Client_BlackBoardUpdate(pos, 
				line1st.getString(), line2nd.getString(), line3rd.getString(), 
				line4th.getString(), line5th.getString(), line6th.getString())); }

	
	/** WoodBoard **/
	@OnlyIn(Dist.CLIENT)
	public static void setWoodBoardScreen(WoodBoard_TileEntity board) {
		Minecraft.getInstance().setScreen(new WoodBoard_Screen(board, true)); }

	public static void severWoodBoardSend(BlockPos pos, String line1st, String line2nd, String line3rd, String line4th, String line5th, String line6th, String line7th) {
		ChinjufuMod.CHANNEL.sendToServer(new Server_WoodBoardSend(pos, line1st, line2nd, line3rd, line4th, line5th, line6th, line7th)); }
	
	public static void clientWoodBoardUpdate(BlockPos pos, Component line1st, Component line2nd, Component line3rd, 
			Component line4th, Component line5th, Component line6th, Component line7th) {
		ChinjufuMod.CHANNEL.send(PacketDistributor.ALL.noArg(), new Client_WoodBoardUpdate(pos, 
				line1st.getString(), line2nd.getString(), line3rd.getString(), 
				line4th.getString(), line5th.getString(), line6th.getString(), line7th.getString())); }
}
