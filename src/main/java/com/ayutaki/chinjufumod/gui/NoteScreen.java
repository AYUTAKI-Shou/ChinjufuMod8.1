package com.ayutaki.chinjufumod.gui;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.BookViewScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerListener;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NoteScreen extends BookViewScreen implements MenuAccess<NoteMenu> {
	private final NoteMenu menu;
	private final ContainerListener listener = new ContainerListener() {
		public void slotChanged(AbstractContainerMenu container, int count, ItemStack hStack) {
			NoteScreen.this.bookChanged();
		}

		public void dataChanged(AbstractContainerMenu container, int count, int count2) {
			if (count == 0) { NoteScreen.this.pageChanged(); }
		}
	};

	public NoteScreen(NoteMenu iMenu, Inventory inventory, Component text) {
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
			this.addRenderableWidget(new Button(this.width / 2 - 100, 196, 98, 20, CommonComponents.GUI_DONE, (p_99033_) -> {
				this.onClose();
			}));
			this.addRenderableWidget(new Button(this.width / 2 + 2, 196, 98, 20, new TranslatableComponent("lectern.take_book"), (p_99024_) -> {
				this.sendButtonClick(3);
			}));
		} else {
			super.createMenuControls();
		}

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

	void bookChanged() {
		ItemStack hStack = this.menu.getBook();
		this.setBookAccess(BookViewScreen.BookAccess.fromItem(hStack));
	}

	void pageChanged() {
		this.setPage(this.menu.getPage());
	}

	protected void closeScreen() {
		this.minecraft.player.closeContainer();
	}
}
