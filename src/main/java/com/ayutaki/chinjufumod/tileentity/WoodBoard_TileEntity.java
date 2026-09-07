package com.ayutaki.chinjufumod.tileentity;

import java.util.List;
import java.util.UUID;
import java.util.function.UnaryOperator;

import javax.annotation.Nullable;

import org.slf4j.Logger;

import com.ayutaki.chinjufumod.blocks.school.WoodBoard;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DynamicOps;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.network.FilteredText;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class WoodBoard_TileEntity extends BlockEntity {
	private static final int MAX = 7;
	private static final Logger LOGGER = LogUtils.getLogger();
	private static final int CHARA = 72; //90 = 15chara

	@Nullable
	private UUID playerWhoMayEdit;
	private WoodBoardText textCodec = this.createDefaultWoodBoardText();
	private boolean isWaxed;

	public WoodBoard_TileEntity(BlockPos pos, BlockState state) {
		this(BlockEntity_CM.WOODBOARD.get(), pos, state);
	}

	@SuppressWarnings("rawtypes")
	public WoodBoard_TileEntity(BlockEntityType tileEntity, BlockPos pos, BlockState state) {
		super(tileEntity, pos, state);
	}

	protected WoodBoardText createDefaultWoodBoardText() {
		return new WoodBoardText();
	}

	public boolean isFacingBoardText(Player playerIn) {
		if (this.getBlockState().getBlock() instanceof WoodBoard blockIn) {
			double d0 = playerIn.getX() - ((double)this.getBlockPos().getX());
			double d1 = playerIn.getZ() - ((double)this.getBlockPos().getZ());
			float f = blockIn.getYRotationDegrees(this.getBlockState());
			float f1 = (float)(Mth.atan2(d1, d0) * 180.0F / (float)Math.PI) - 90.0F;
			return Mth.degreesDifferenceAbs(f, f1) <= 90.0F; }
		
		else { return false; }
	}

	public WoodBoardText getBoardText() {
		return this.textCodec;
	}

	public int getTextLineHeight() {
		return 10;
	}

	public int getMaxTextLineWidth() {
		return CHARA;
	}

	@Override
	protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
		super.saveAdditional(compound, provider);
		DynamicOps<Tag> dynamicops = provider.createSerializationContext(NbtOps.INSTANCE);
		
		WoodBoardText.DIRECT_CODEC
			.encodeStart(dynamicops, this.textCodec)
			.resultOrPartial(LOGGER::error)
			.ifPresent(tagFront -> compound.put("front_text", tagFront));
		
		compound.putBoolean("is_waxed", this.isWaxed);
	}

	@Override
	protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
		super.loadAdditional(compound, provider);
		DynamicOps<Tag> dynamicops = provider.createSerializationContext(NbtOps.INSTANCE);
		
		if (compound.contains("front_text")) {
			WoodBoardText.DIRECT_CODEC
				.parse(dynamicops, compound.getCompound("front_text"))
				.resultOrPartial(LOGGER::error)
				.ifPresent(tagFront -> this.textCodec = this.loadLines(tagFront)); }

		this.isWaxed = compound.getBoolean("is_waxed");
	}

	private WoodBoardText loadLines(WoodBoardText boardText) {
		for (int i = 0; i < MAX; i++) {
			Component component = this.loadLine(boardText.getMessage(i, false));
			Component component1 = this.loadLine(boardText.getMessage(i, true));
			boardText = boardText.setMessage(i, component, component1); }

		return boardText;
	}

	private Component loadLine(Component component) {
		if (this.level instanceof ServerLevel serverlevel) {
			try {
				return ComponentUtils.updateForEntity(createCommandSourceStack(null, serverlevel, this.worldPosition), component, null, 0); }
			
			catch (CommandSyntaxException commandsyntaxexception) { }
		}
		return component;
	}

	public void updateWoodBoardText(Player playerIn, List<FilteredText> list) {
		if (!this.isWaxed() && playerIn.getUUID().equals(this.getPlayerWhoMayEdit()) && this.level != null) {
			this.updateText(boardText -> this.setMessages(playerIn, list, boardText));
			this.setAllowedPlayerEditor(null);
			this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3); }
		
		else {
			LOGGER.warn("Player {} just tried to change non-editable sign", playerIn.getName().getString()); }
	}

	public boolean updateText(UnaryOperator<WoodBoardText> boardText) {
		WoodBoardText iTxt = this.getBoardText();
		return this.setBoardText(boardText.apply(iTxt));
	}

	private WoodBoardText setMessages(Player playerIn, List<FilteredText> list, WoodBoardText boardText) {
		for (int i = 0; i < MAX; i++) {
			FilteredText iFilteredText = list.get(i);
			Style style = boardText.getMessage(i, playerIn.isTextFilteringEnabled()).getStyle();
			
			if (playerIn.isTextFilteringEnabled()) {
				boardText = boardText.setMessage(i, Component.literal(iFilteredText.filteredOrEmpty()).setStyle(style)); }
			
			else {
				boardText = boardText.setMessage(i, Component.literal(iFilteredText.raw()).setStyle(style), Component.literal(iFilteredText.filteredOrEmpty()).setStyle(style));
			}
		}
		return boardText;
	}

	public boolean canExecuteClickCommands(Player playerIn) {
		return this.isWaxed() && this.getBoardText().hasAnyClickCommands(playerIn);
	}

	public boolean executeClickCommandsIfPresent(Player playerIn, Level worldIn, BlockPos pos) {
		boolean flag = false;

		for (Component component : this.getBoardText().getMessages(playerIn.isTextFilteringEnabled())) {
			Style style = component.getStyle();
			ClickEvent clickevent = style.getClickEvent();
			
			if (clickevent != null && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
				playerIn.getServer().getCommands().performPrefixedCommand(createCommandSourceStack(playerIn, worldIn, pos), clickevent.getValue());
				flag = true; }
		}
		return flag;
	}

	private static CommandSourceStack createCommandSourceStack(@Nullable Player playerIn, Level worldIn, BlockPos pos) {
		String s = playerIn == null ? "WoodBoard" : playerIn.getName().getString();
		Component component = (Component)(playerIn == null ? Component.literal("WoodBoard") : playerIn.getDisplayName());
		return new CommandSourceStack(
			CommandSource.NULL, Vec3.atCenterOf(pos), Vec2.ZERO, (ServerLevel)worldIn, 2, s, component, worldIn.getServer(), playerIn);
	}

	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
		return this.saveCustomOnly(provider);
	}

	public void setAllowedPlayerEditor(@Nullable UUID uuid) {
		this.playerWhoMayEdit = uuid;
	}

	@Nullable
	public UUID getPlayerWhoMayEdit() {
		return this.playerWhoMayEdit;
	}
	
	public void clearText() {
		Component empty = (Component)CommonComponents.EMPTY;
		this.updateText(boardText -> boardText.setMessage(0, empty));
		this.updateText(boardText -> boardText.setMessage(1, empty));
		this.updateText(boardText -> boardText.setMessage(2, empty));
		this.updateText(boardText -> boardText.setMessage(3, empty));
		this.updateText(boardText -> boardText.setMessage(4, empty));
		this.updateText(boardText -> boardText.setMessage(5, empty));
		this.updateText(boardText -> boardText.setMessage(6, empty));
		this.updateText(boardText -> boardText.setGlowText(false));
		markUpdated();
		//ChinjufuMod.LOGGER.info("ChinjufuMod clear WoodBoard TileEntity.");
	}

	public boolean setBoardText(WoodBoardText boardText) {
		if (boardText != this.textCodec) {
			this.textCodec = boardText;
			this.markUpdated();
			return true; }
		else { return false; }
	}
	
	public boolean isWaxed() {
		return this.isWaxed;
	}

	public boolean setWaxed(boolean flag) {
		if (this.isWaxed != flag) {
			this.isWaxed = flag;
			this.markUpdated();
			return true; } 
		else { return false; }
	}

	public void markUpdated() {
		this.setChanged();
		this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
	}
	
	public boolean playerIsTooFarAwayToEdit(UUID uuid) {
		Player player = this.level.getPlayerByUUID(uuid);
		return player == null || !player.canInteractWithBlock(this.getBlockPos(), 4.0);
	}

	public static void tick(Level worldIn, BlockPos pos, BlockState state, WoodBoard_TileEntity tileEntity) {
		UUID uuid = tileEntity.getPlayerWhoMayEdit();
		if (uuid != null) {
			tileEntity.clearInvalidPlayerWhoMayEdit(tileEntity, worldIn, uuid);
		}
	}

	private void clearInvalidPlayerWhoMayEdit(WoodBoard_TileEntity tileEntity, Level worldIn, UUID uuid) {
		if (tileEntity.playerIsTooFarAwayToEdit(uuid)) {
			tileEntity.setAllowedPlayerEditor(null); }
	}

	public SoundEvent getSignInteractionFailedSoundEvent() {
		return SoundEvents.WAXED_SIGN_INTERACT_FAIL;
	}
}
