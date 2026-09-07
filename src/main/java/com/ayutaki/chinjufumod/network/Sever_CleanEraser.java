package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.network.AbstractMessage.AbstractServerMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class Sever_CleanEraser extends AbstractServerMessage<Sever_CleanEraser> implements IPacket<Sever_CleanEraser> {

	public Sever_CleanEraser() { }

	@Override
	public Sever_CleanEraser decode(PacketBuffer buffer) {
		return this;
	}
	
	@Override
	public void encode(Sever_CleanEraser message, PacketBuffer buffer) { }
	
	@Override
	public void handle(Sever_CleanEraser message, EntityPlayer player) {
		ItemStack offStack = player.getHeldItemOffhand();
		offStack.damageItem(-10, player);
		//ChinjufuMod.LOGGER.info("ChinjufuMod clean Eraser SERVER.");
	}
}
