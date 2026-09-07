package com.ayutaki.chinjufumod.network;

import com.ayutaki.chinjufumod.ChinjufuMod;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/* Borrowed from LaserMod by ProPercivalalb. */
public abstract class AbstractMessage<T extends AbstractMessage<T> & IPacket<T>> implements IMessage, IMessageHandler <T, IMessage> {
	/**
	 * If message is sent to the wrong side, an exception will be thrown during handling
	 * @return True if the message is allowed to be handled on the given side
	 */
	protected boolean isValidOnSide(Side side) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fromBytes(ByteBuf buffer) {
		((T)this).decode(new PacketBuffer(buffer));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void toBytes(ByteBuf buffer) {
		((T)this).encode(((T)this), new PacketBuffer(buffer));
	}
	
	@Override
	public final IMessage onMessage(final T message, final MessageContext context) {
		if (!message.isValidOnSide(context.side))
			throw new RuntimeException("Invalid side " + context.side.name() + " for " + message.getClass().getSimpleName());
		
		IThreadListener thread = ChinjufuMod.PROXY.getThreadFromContext(context);
		// pretty much copied straight from vanilla code, see {@link PacketThreadUtil#checkThreadAndEnqueue}
		thread.addScheduledTask(new Runnable() {
			public void run() {
				message.handle(message, ChinjufuMod.PROXY.getPlayerEntity(context)); }
		});
		return null;
	}
	
	/**
	 * Messages that can only be sent from the server to the client should use this class
	 */
	public static abstract class AbstractClientMessage<T extends AbstractMessage<T> & IPacket<T>> extends AbstractMessage<T> {
		@Override
		protected final boolean isValidOnSide(Side side) {
			return side.isClient(); }
	}

	/**
	 * Messages that can only be sent from the client to the server should use this class
	 */
	public static abstract class AbstractServerMessage<T extends AbstractMessage<T> & IPacket<T>> extends AbstractMessage<T> {
		@Override
		protected final boolean isValidOnSide(Side side) {
			return side.isServer(); }
	}
}
