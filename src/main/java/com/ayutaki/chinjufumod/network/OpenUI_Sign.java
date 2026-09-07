package com.ayutaki.chinjufumod.network;

import java.util.UUID;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class OpenUI_Sign implements CustomPacketPayload {

	public static final Type<OpenUI_Sign> TYPE = new Type<>(ChinjufuMod.id("open_ui_sign"));
	public static final StreamCodec<FriendlyByteBuf, OpenUI_Sign> CODEC = StreamCodec
			.ofMember(OpenUI_Sign::encode, OpenUI_Sign::decode);
	
	public UUID playerUUID;
	public BlockPos boardPos;
	
	public OpenUI_Sign(UUID uuid, BlockPos pos) {
		this.playerUUID = uuid;
		this.boardPos = pos;
	}
	
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUUID(playerUUID);
		buffer.writeBlockPos(boardPos);
	}
	
	public static OpenUI_Sign decode(FriendlyByteBuf buffer) {
		return new OpenUI_Sign(buffer.readUUID(), buffer.readBlockPos());
	}
	
	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
	
	/* ClientPlayNetHandler */
	public static void handle(OpenUI_Sign message, IPayloadContext context) {
		context.enqueueWork(() -> {
			openGUI(message);
		});
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void openGUI(OpenUI_Sign message) {
		Minecraft mClient = Minecraft.getInstance();
		LocalPlayer player = mClient.player;
		
		if (!player.getUUID().equals(message.playerUUID)) { return; }

		Level worldIn = player.level();
		BlockPos pos = message.boardPos;
		BlockState state = worldIn.getBlockState(pos);
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
				
		if (!(tileEntity instanceof SignBlockEntity)) {
			tileEntity = new SignBlockEntity(pos, state);
			tileEntity.setLevel(worldIn); }
		
		NetworkEvent_CM.setSignScreen((SignBlockEntity)tileEntity, player);
	}
}
