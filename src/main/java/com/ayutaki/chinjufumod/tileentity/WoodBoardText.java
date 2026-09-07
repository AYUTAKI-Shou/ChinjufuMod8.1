package com.ayutaki.chinjufumod.tileentity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.Util;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;

public class WoodBoardText {
	private static final int MAX = 7;
	private final Component[] messages;
	private final Component[] filteredMessages;
	private final DyeColor color;
	private final boolean hasGlowingText;
	@Nullable
	private FormattedCharSequence[] renderMessages;
	private boolean renderMessagedFiltered;
	
	private static final Codec<Component[]> LINES_CODEC = ComponentSerialization.FLAT_CODEC
		.listOf().comapFlatMap(
			function -> Util.fixedSize((List<Component>)function, MAX)
					.map(iText -> new Component[]{iText.get(0), iText.get(1), iText.get(2), iText.get(3), iText.get(4), iText.get(5), iText.get(6)}),
			iFilteredText -> List.of(iFilteredText[0], iFilteredText[1], iFilteredText[2], iFilteredText[3], iFilteredText[4], iFilteredText[5], iFilteredText[6]) );
	
	public static final Codec<WoodBoardText> DIRECT_CODEC = RecordCodecBuilder.create(
		instance -> instance.group(
					LINES_CODEC.fieldOf("messages").forGetter(iTxt -> iTxt.messages),
					LINES_CODEC.lenientOptionalFieldOf("filtered_messages").forGetter(WoodBoardText::filteredMessages),
					DyeColor.CODEC.fieldOf("color").orElse(DyeColor.WHITE).forGetter(txtColor -> txtColor.color),
					Codec.BOOL.fieldOf("has_glowing_text").orElse(false).forGetter(txtGlow -> txtGlow.hasGlowingText))
				.apply(instance, WoodBoardText::load)
	);

	public WoodBoardText() {
		this(emptyMessages(), emptyMessages(), DyeColor.WHITE, false);
	}

	public WoodBoardText(Component[] iText, Component[] iFilteredText, DyeColor dye, boolean hasGlow) {
		this.messages = iText;
		this.filteredMessages = iFilteredText;
		this.color = dye;
		this.hasGlowingText = hasGlow;
	}

	private static Component[] emptyMessages() {
		return new Component[]{CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY, 
				CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY};
	}//Component.literal(""),

	private static WoodBoardText load(Component[] iText, Optional<Component[]> iFilteredText, DyeColor dye, boolean hasGlow) {
		return new WoodBoardText(iText, iFilteredText.orElse(Arrays.copyOf(iText, iText.length)), dye, hasGlow);
	}

	public boolean hasGlowText() {
		return this.hasGlowingText;
	}

	public WoodBoardText setGlowText(boolean hasGlow) {
		return hasGlow == this.hasGlowingText ? this : new WoodBoardText(this.messages, this.filteredMessages, this.color, hasGlow);
	}

	public DyeColor getColor() {
		return this.color;
	}

	public WoodBoardText setColor(DyeColor dye) {
		return dye == this.getColor() ? this : new WoodBoardText(this.messages, this.filteredMessages, dye, this.hasGlowingText);
	}

	public Component getMessage(int i, boolean filtered) {
		return this.getMessages(filtered)[i];
	}

	public WoodBoardText setMessage(int i, Component iText) {
		return this.setMessage(i, iText, iText);
	}

	public WoodBoardText setMessage(int i, Component iText, Component iFilteredText) {
		Component[] acomponent = Arrays.copyOf(this.messages, this.messages.length);
		Component[] acomponent1 = Arrays.copyOf(this.filteredMessages, this.filteredMessages.length);
		acomponent[i] = iText;
		acomponent1[i] = iFilteredText;
		return new WoodBoardText(acomponent, acomponent1, this.color, this.hasGlowingText);
	}

	public boolean hasMessage(Player playerIn) {
		return Arrays.stream(this.getMessages(playerIn.isTextFilteringEnabled())).anyMatch(component -> !component.getString().isEmpty());
	}

	public Component[] getMessages(boolean filtered) {
		return filtered ? this.filteredMessages : this.messages;
	}

	public FormattedCharSequence[] getRenderMessages(boolean filtered, Function<Component, FormattedCharSequence> function) {
		if (this.renderMessages == null || this.renderMessagedFiltered != filtered) {
			this.renderMessagedFiltered = filtered;
			this.renderMessages = new FormattedCharSequence[MAX];

			for (int i = 0; i < MAX; i++) {
				this.renderMessages[i] = function.apply(this.getMessage(i, filtered)); }
		}
		return this.renderMessages;
	}

	private Optional<Component[]> filteredMessages() {
		for (int i = 0; i < MAX; i++) {
			if (!this.filteredMessages[i].equals(this.messages[i])) {
				return Optional.of(this.filteredMessages); }
		}
		return Optional.empty();
	}

	public boolean hasAnyClickCommands(Player playerIn) {
		for (Component component : this.getMessages(playerIn.isTextFilteringEnabled())) {
			Style style = component.getStyle();
			ClickEvent clickevent = style.getClickEvent();
			if (clickevent != null && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
				return true; }
		}
		return false;
	}
}
