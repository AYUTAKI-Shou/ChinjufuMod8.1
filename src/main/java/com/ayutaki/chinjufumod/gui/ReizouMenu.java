package com.ayutaki.chinjufumod.gui;

import com.ayutaki.chinjufumod.handler.MenuTypes_CM;
import com.ayutaki.chinjufumod.tileentity.AbstractReizouTileEntity;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class ReizouMenu extends AbstractContainerMenu {
	private final Container chestSlots;
	private final int containerRows;
	
	// net.minecraft.world.inventory.Shulker
	public ReizouMenu(int windowId, Inventory inventory) {
		this(MenuTypes_CM.REIZOU_MENU.get(), windowId, inventory, new SimpleContainer(45));
	}
	
	public ReizouMenu(MenuType<?> type, int windowId, final Inventory inventory, final Container tileSlots) {
		super(type, windowId);
		this.chestSlots = tileSlots;
		this.containerRows = 5;
		this.chestSlots.startOpen(inventory.player);
		int i = (this.containerRows - 4) * 18;

		for(int j = 0; j < this.containerRows; ++j) {
			for(int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(tileSlots, k + j * 9, 8 + k * 18, 18 + j * 18) {
					
					public void onTake(Player player, ItemStack stack) {
						super.onTake(player, stack);
						if(player instanceof ServerPlayer serverPlayer) {
							((AbstractReizouTileEntity)container).expFromGUI(serverPlayer); }
					}
				});
			}
		}

		for(int l = 0; l < 3; ++l) {
			 for(int j1 = 0; j1 < 9; ++j1) {
				 this.addSlot(new Slot(inventory, j1 + l * 9 + 9, 8 + j1 * 18, 104 + l * 18 + i));
			 }
		 }

		 for(int i1 = 0; i1 < 9; ++i1) {
			 this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 162 + i));
		 }
	}
	
	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < this.containerRows * 9) {
				if (!this.moveItemStackTo(stack1, this.containerRows * 9, this.slots.size(), true)) {
					return ItemStack.EMPTY; }
			} 
			
			else if (!this.moveItemStackTo(stack1, 0, this.containerRows * 9, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) { slot.set(ItemStack.EMPTY); }
			
			else { slot.setChanged(); }
		}
		return stack;
	}
	
	@Override
	public boolean stillValid(Player playerIn) {
		return this.chestSlots.stillValid(playerIn);
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		this.chestSlots.stopOpen(playerIn);
	}
	
	public Container getContainer() {
		return this.chestSlots;
	}
	
	public int getRowCount() {
		return this.containerRows;
	}
}
