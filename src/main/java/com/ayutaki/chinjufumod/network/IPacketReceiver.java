package com.ayutaki.chinjufumod.network;

import io.netty.buffer.ByteBuf;

public interface IPacketReceiver {

	void decodePacketdata(ByteBuf buffer);
}
