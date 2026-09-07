package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.gui.BlackBoard_Screen;
import com.ayutaki.chinjufumod.gui.MirrorScreen;
import com.ayutaki.chinjufumod.gui.SignEditScreen_CM;
import com.ayutaki.chinjufumod.gui.WoodBoard_Screen;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.SignBlockEntity;
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
	
	public static void serverBlackBoardSend(BlockPos pos, String line1st, String line2nd, String line3rd, String line4th, String line5th, String line6th) {
		/** //sendToServer ->PacketDistributor.SERVER.noArg() **/
		ChinjufuMod.CHANNEL.send(new Server_BlackBoardSend(pos, line1st, line2nd, line3rd, line4th, line5th, line6th), PacketDistributor.SERVER.noArg()); }

	
	/** WoodBoard **/
	@OnlyIn(Dist.CLIENT)
	public static void setWoodBoardScreen(WoodBoard_TileEntity board) {
		Minecraft.getInstance().setScreen(new WoodBoard_Screen(board, true)); }
	
	public static void serverWoodBoardSend(BlockPos pos, String line1st, String line2nd, String line3rd, String line4th, String line5th, String line6th, String line7th) {
		/** //sendToServer ->PacketDistributor.SERVER.noArg() **/
		ChinjufuMod.CHANNEL.send(new Server_WoodBoardSend(pos, line1st, line2nd, line3rd, line4th, line5th, line6th, line7th), PacketDistributor.SERVER.noArg()); }

	
	/* Sign **/
	@OnlyIn(Dist.CLIENT)
	public static void setSignScreen(SignBlockEntity sign, Player player) {
		boolean front = sign.isFacingFrontText(player);
		Minecraft.getInstance().setScreen(new SignEditScreen_CM(sign, front, true)); }

	public static void serverSignSend(BlockPos pos, boolean front, String line1st, String line2nd, String line3rd, String line4th) {
		/** //sendToServer ->PacketDistributor.SERVER.noArg() **/
		ChinjufuMod.CHANNEL.send(new Server_SignSend(pos, front, line1st, line2nd, line3rd, line4th), PacketDistributor.SERVER.noArg()); }
}
