package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.network.AbstractMessage.AbstractServerMessage;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class Sever_GenEraser extends AbstractServerMessage<Sever_GenEraser> implements IPacket<Sever_GenEraser> {

	public Sever_GenEraser() { }

	@Override
	public Sever_GenEraser decode(PacketBuffer buffer) {
		return this;
	}
	
	@Override
	public void encode(Sever_GenEraser message, PacketBuffer buffer) { }
	
	@Override
	public void handle(Sever_GenEraser message, EntityPlayer player) {
		ItemStack offStack = player.getHeldItemOffhand();
		ItemStack take = new ItemStack(Items_Chinjufu.BOARD_ERASER, 1, 0);
		if (!player.inventory.addItemStackToInventory(take)) { player.dropItem(take, false); }
		offStack.shrink(1);
		//ChinjufuMod.LOGGER.info("ChinjufuMod gen Eraser SERVER.");
	}
}
