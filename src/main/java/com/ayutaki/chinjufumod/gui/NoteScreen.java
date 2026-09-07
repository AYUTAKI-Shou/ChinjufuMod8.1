package com.ayutaki.chinjufumod.gui;

import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NoteScreen extends ReadBookScreen implements IHasContainer<NoteMenu> {
	private final NoteMenu menu;
	private final IContainerListener listener = new IContainerListener() {
		public void refreshContainer(Container container, NonNullList<ItemStack> list) {
			NoteScreen.this.bookChanged();
		}

		public void slotChanged(Container container, int count, ItemStack hStack) {
			NoteScreen.this.bookChanged();
		}

		public void setContainerData(Container container, int count, int count2) {
			if (count == 0) { NoteScreen.this.pageChanged(); }
		}
	};

	public NoteScreen(NoteMenu iMenu, PlayerInventory inventory, ITextComponent text) {
		this.menu = iMenu;
	}

	public NoteMenu getMenu() {
		return this.menu;
	}

	protected void init() {
		super.init();
		this.menu.addSlotListener(this.listener);
	}

	public void onClose() {
		this.minecraft.player.closeContainer();
		super.onClose();
	}

	public void removed() {
		super.removed();
		this.menu.removeSlotListener(this.listener);
	}

	protected void createMenuControls() {
		if (this.minecraft.player.mayBuild()) {
			this.addButton(new Button(this.width / 2 - 100, 196, 98, 20, DialogTexts.GUI_DONE, (p_214181_1_) -> {
				this.minecraft.setScreen((Screen)null);
			}));
			this.addButton(new Button(this.width / 2 + 2, 196, 98, 20, new TranslationTextComponent("lectern.take_book"), (p_214178_1_) -> {
				this.sendButtonClick(3);
			}));
		} 
		else { super.createMenuControls(); }
	}

	protected void pageBack() {
		this.sendButtonClick(1);
	}

	protected void pageForward() {
		this.sendButtonClick(2);
	}

	protected boolean forcePage(int count) {
		if (count != this.menu.getPage()) {
			this.sendButtonClick(100 + count);
			return true;
		} 
		else { return false; }
	}

	private void sendButtonClick(int count) {
		this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, count);
	}

	public boolean isPauseScreen() {
		return false;
	}

	private void bookChanged() {
		ItemStack hStack = this.menu.getBook();
		this.setBookAccess(ReadBookScreen.IBookInfo.fromItem(hStack));
	}

	private void pageChanged() {
		this.setPage(this.menu.getPage());
	}
}
