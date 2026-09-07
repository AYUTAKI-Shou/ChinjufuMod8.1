package com.ayutaki.chinjufumod.tileentity;

import java.util.UUID;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class BlackBoard_TileEntity extends BlockEntity {
	private static final int MAX = 6;
	private static final String[] TEXT_FIELD = new String[]{"Text1", "Text2", "Text3", "Text4", "Text5", "Text6"};
	private static final String[] FILTERED_TEXT_FIELD = new String[]{"FilteredText1", "FilteredText2", "FilteredText3", "FilteredText4", "FilteredText5", "FilteredText6"};
	public final Component[] boardText = new Component[]{TextComponent.EMPTY, TextComponent.EMPTY, TextComponent.EMPTY, 
			TextComponent.EMPTY, TextComponent.EMPTY, TextComponent.EMPTY};
	private final Component[] filteredText = new Component[]{TextComponent.EMPTY, TextComponent.EMPTY, TextComponent.EMPTY, 
			TextComponent.EMPTY, TextComponent.EMPTY, TextComponent.EMPTY};
	private boolean isEditable = true;
	@Nullable
	private UUID playerWhoMayEdit;
	@Nullable
	private FormattedCharSequence[] renderText;
	private boolean renderTextFiltered;
	private DyeColor color = DyeColor.WHITE;
	private boolean hasGlowingText;
	private boolean isWaxed;
	
	public BlackBoard_TileEntity(BlockPos pos, BlockState state) {
		super(BlockEntity_CM.BLACKBOARD.get(), pos, state);
	}

	protected void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);

		for (int i = 0; i < MAX; ++i) {
			Component iText = this.boardText[i];
			String s = Component.Serializer.toJson(iText);
			compound.putString(TEXT_FIELD[i], s);
			Component filteredText = this.filteredText[i];
			
			if (!filteredText.equals(iText)) {
				compound.putString(FILTERED_TEXT_FIELD[i], Component.Serializer.toJson(filteredText)); }
		}
		compound.putString("Color", this.color.getName());
		compound.putBoolean("GlowingText", this.hasGlowingText);
		compound.putBoolean("is_waxed", this.isWaxed);
	}

	public void load(CompoundTag compound) {
		this.isEditable = false;
		super.load(compound);
		this.color = DyeColor.byName(compound.getString("Color"), DyeColor.WHITE);

		for (int i = 0; i < MAX; ++i) {
			String s = compound.getString(TEXT_FIELD[i]);
			Component iText = this.loadLine(s);
			this.boardText[i] = iText;
			String s1 = FILTERED_TEXT_FIELD[i];
			if (compound.contains(s1, 8)) {
				this.filteredText[i] = this.loadLine(compound.getString(s1)); } 
			
			else {
				this.filteredText[i] = iText; }
		}
		this.renderText = null;
		this.hasGlowingText = compound.getBoolean("GlowingText");
		this.isWaxed = compound.getBoolean("is_waxed");
	}

	private Component loadLine(String s) {
		Component iText = this.deserializeTextSafe(s);
		if (this.level instanceof ServerLevel) {
			try {
				return ComponentUtils.updateForEntity(this.createCommandSourceStack((ServerPlayer)null), iText, (Entity)null, 0); }
			
			catch (CommandSyntaxException commandsyntaxexception) { }
		}
		return iText;
	}

	private Component deserializeTextSafe(String s) {
		try {
			Component iText = Component.Serializer.fromJson(s);
			if (iText != null) {
				return iText; }
		}
		catch (Exception exception) { }

		return TextComponent.EMPTY;
	}

	public Component getMessage(int i, boolean flag) {
		return this.getMessages(flag)[i];
	}

	public void setMessage(int i, Component iText) {
		this.setMessage(i, iText, iText);
	}

	public void setMessage(int i, Component iText, Component iFilteredText) {
		this.boardText[i] = iText;
		this.filteredText[i] = iFilteredText;
		this.renderText = null;
	}

	public FormattedCharSequence[] getRenderMessages(boolean flag, Function<Component, FormattedCharSequence> iText) {
		if (this.renderText == null || this.renderTextFiltered != flag) {
			this.renderTextFiltered = flag;
			this.renderText = new FormattedCharSequence[MAX];

			for (int i = 0; i < MAX; ++i) {
				this.renderText[i] = iText.apply(this.getMessage(i, flag)); }
		}
		return this.renderText;
	}

	private Component[] getMessages(boolean flag) {
		return flag ? this.filteredText : this.boardText;
	}

	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	public boolean onlyOpCanSetNbt() {
		return true;
	}

	public boolean isEditable() {
		return this.isEditable;
	}

	public void setEditable(boolean flag) {
		this.isEditable = flag;
		if (!flag) {
			this.playerWhoMayEdit = null;
		}
	}

	public void setAllowedPlayerEditor(UUID uuid) {
		this.playerWhoMayEdit = uuid;
	}

	@Nullable
	public UUID getPlayerWhoMayEdit() {
		return this.playerWhoMayEdit;
	}

	public boolean executeClickCommands(ServerPlayer playerIn) {
		for(Component iText : this.getMessages(playerIn.isTextFilteringEnabled())) {
			Style style = iText.getStyle();
			ClickEvent clickevent = style.getClickEvent();
			if (clickevent != null && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
				playerIn.getServer().getCommands().performCommand(this.createCommandSourceStack(playerIn), clickevent.getValue());
			}
		}
		return true;
	}

	public CommandSourceStack createCommandSourceStack(@Nullable ServerPlayer playerIn) {
		String s = playerIn == null ? "BlackBoard" : playerIn.getName().getString();
		Component iText = (Component)(playerIn == null ? new TextComponent("BlackBoard") : playerIn.getDisplayName());
		return new CommandSourceStack(CommandSource.NULL, Vec3.atCenterOf(this.worldPosition), Vec2.ZERO, (ServerLevel)this.level, 2, s, iText, this.level.getServer(), playerIn);
	}

	public void clearText() {
		Component empty = (Component)TextComponent.EMPTY;
		this.setMessage(0, empty);
		this.setMessage(1, empty);
		this.setMessage(2, empty);
		this.setMessage(3, empty);
		this.setMessage(4, empty);
		this.setMessage(5, empty);
		this.hasGlowingText = false;
		markUpdated();
		//ChinjufuMod.LOGGER.info("ChinjufuMod clear BlackBoard TileEntity.");
	}
	
	public DyeColor getTxtColor() {
		return this.color;
	}

	public boolean setTxtColor(DyeColor dye) {
		if (dye != this.getTxtColor()) {
			this.color = dye;
			this.markUpdated();
			return true; } 
		
		else { return false; }
	}

	public boolean hasGlowText() {
		return this.hasGlowingText;
	}

	public boolean setGlowText(boolean flag) {
		if (this.hasGlowingText != flag) {
			this.hasGlowingText = flag;
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

	private void markUpdated() {
		this.setChanged();
		this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
	}
}
