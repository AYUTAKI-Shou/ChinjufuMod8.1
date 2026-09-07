package com.ayutaki.chinjufumod.gui;

import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NoteScreen extends ReadBookScreen implements IHasContainer<NoteMenu> {
	private final NoteMenu menu;
	private final IContainerListener listener = new IContainerListener() {
		public void sendAllContents(Container container, NonNullList<ItemStack> list) {
			NoteScreen.this.bookChanged();
		}

		public void sendSlotContents(Container container, int count, ItemStack hStack) {
			NoteScreen.this.bookChanged();
		}

		public void sendWindowProperty(Container container, int count, int count2) {
			if (count == 0) { NoteScreen.this.pageChanged(); }
		}
	};

	public NoteScreen(NoteMenu iMenu, PlayerInventory inventory, ITextComponent text) {
		this.menu = iMenu;
	}

	public NoteMenu getContainer() {
		return this.menu;
	}

	protected void init() {
		super.init();
		this.menu.addListener(this.listener);
	}

	public void onClose() {
		this.minecraft.player.closeScreen();
		super.onClose();
	}

	public void removed() {
		super.removed();
		this.menu.removeListener(this.listener);
	}

	protected void addDoneButton() {
		if (this.minecraft.player.isAllowEdit()) {
			this.addButton(new Button(this.width / 2 - 100, 196, 98, 20, I18n.format("gui.done"), (p_214181_1_) -> {
				this.minecraft.displayGuiScreen((Screen)null);
			}));
			this.addButton(new Button(this.width / 2 + 2, 196, 98, 20, I18n.format("lectern.take_book"), (p_214178_1_) -> {
				this.sendButtonClick(3);
			}));
		} 
		else { super.addDoneButton(); }
	}

	protected void previousPage() {
		this.sendButtonClick(1);
	}

	protected void nextPage() {
		this.sendButtonClick(2);
	}

	protected boolean showPage2(int count) {
		if (count != this.menu.getPage()) {
			this.sendButtonClick(100 + count);
			return true;
		} 
		else { return false; }
	}

	private void sendButtonClick(int count) {
		this.minecraft.playerController.sendEnchantPacket(this.menu.windowId, count);
	}

	public boolean isPauseScreen() {
		return false;
	}

	private void bookChanged() {
		ItemStack hStack = this.menu.getBook();
		this.func_214155_a(ReadBookScreen.IBookInfo.func_216917_a(hStack));
	}

	private void pageChanged() {
		this.showPage(this.menu.getPage());
	}
}
