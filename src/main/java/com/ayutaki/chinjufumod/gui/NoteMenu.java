package com.ayutaki.chinjufumod.gui;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class NoteMenu extends AbstractContainerMenu {
	@SuppressWarnings("unused")
	private static final int DATA_COUNT = 1;
	@SuppressWarnings("unused")
	private static final int SLOT_COUNT = 1;
	public static final int BUTTON_PREV_PAGE = 1;
	public static final int BUTTON_NEXT_PAGE = 2;
	public static final int BUTTON_TAKE_BOOK = 3;
	public static final int BUTTON_PAGE_JUMP_RANGE_START = 100;
	private final Container lectern;
	private final ContainerData lecternData;

	public NoteMenu(int windowId, final Inventory inventory) {
		this(windowId, new SimpleContainer(1), new SimpleContainerData(1));
	}

	public NoteMenu(int windowId, Container container, ContainerData array) {
		super(MenuType.LECTERN, windowId);
		checkContainerSize(container, 1);
		checkContainerDataCount(array, 1);
		this.lectern = container;
		this.lecternData = array;
		this.addSlot(new Slot(container, 0, 0, 0) {
			@Override
			public void setChanged() {
				super.setChanged();
				NoteMenu.this.slotsChanged(this.container);
			}
		});
		this.addDataSlots(array);
	}

	@Override
	public boolean clickMenuButton(Player playerIn, int id) {
		if (id >= 100) {
			int k = id - 100;
			this.setData(0, k);
			return true;
		} 
		
		else {
			switch (id) {
				case 1:
					int j = this.lecternData.get(0);
					this.setData(0, j - 1);
					return true;
				case 2:
					int i = this.lecternData.get(0);
					this.setData(0, i + 1);
					return true;
				case 3:
					if (!playerIn.mayBuild()) {
						return false;
					}

					ItemStack itemstack = this.lectern.removeItemNoUpdate(0);
					this.lectern.setChanged();
					if (!playerIn.getInventory().add(itemstack)) {
						playerIn.drop(itemstack, false);
					}

					return true;
				default:
					return false;
			}
		}
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int count) {
		return ItemStack.EMPTY;
	}

	@Override
	public void setData(int nPage, int count) {
		super.setData(nPage, count);
		this.broadcastChanges();
	}

	@Override
	public boolean stillValid(Player playerIn) {
		return this.lectern.stillValid(playerIn);
	}

	public ItemStack getBook() {
		return this.lectern.getItem(0);
	}

	public int getPage() {
		return this.lecternData.get(0);
	}
}
