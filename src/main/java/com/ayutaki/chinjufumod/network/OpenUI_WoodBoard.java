package com.ayutaki.chinjufumod.network;

import java.util.UUID;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenUI_WoodBoard implements CustomPacketPayload {

	public static final Type<OpenUI_WoodBoard> TYPE = new Type<>(ChinjufuMod.id("open_ui_woodboard"));
	public static final StreamCodec<FriendlyByteBuf, OpenUI_WoodBoard> CODEC = StreamCodec
			.ofMember(OpenUI_WoodBoard::encode, OpenUI_WoodBoard::decode);
	
	public UUID playerUUID;
	public BlockPos boardPos;
	
	public OpenUI_WoodBoard(UUID uuid, BlockPos pos) {
		this.playerUUID = uuid;
		this.boardPos = pos;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUUID(playerUUID);
		buffer.writeBlockPos(boardPos);
	}
	
	public static OpenUI_WoodBoard decode(FriendlyByteBuf buffer) {
		return new OpenUI_WoodBoard(buffer.readUUID(), buffer.readBlockPos());
	}
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
	
	/* ClientPlayNetHandler */
	public static void handle(OpenUI_WoodBoard message, IPayloadContext context) {
		Minecraft mClient = Minecraft.getInstance();
		
		if (context.flow().isClientbound()) {
			LocalPlayer player = mClient.player;
			if (!player.getUUID().equals(message.playerUUID)) return;
			
			context.enqueueWork(() -> {
				Level worldIn = player.level();
				BlockPos pos = message.boardPos;
				BlockState state = worldIn.getBlockState(pos);
				BlockEntity tileEntity = worldIn.getBlockEntity(pos);
						
				if (!(tileEntity instanceof WoodBoard_TileEntity)) {
					tileEntity = new WoodBoard_TileEntity(pos, state);
					tileEntity.setLevel(worldIn); }
				
				NetworkEvent_CM.setWoodBoardScreen((WoodBoard_TileEntity)tileEntity);
				//ChinjufuMod.LOGGER.info("ChinjufuMod edit WoodBoard START.");
			});
		}
	}
}
