package com.ayutaki.chinjufumod.gui;

import com.ayutaki.chinjufumod.handler.MenuTypes_CM;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NoteMenu extends Container {
	private final IInventory lectern;
	private final IIntArray lecternData;

	public NoteMenu(int windowId, PlayerInventory inventory, PacketBuffer data) {
		this(windowId, new Inventory(1), new IntArray(1));
	}
	
	public NoteMenu(int windowId, IInventory container, IIntArray array) {
		super(MenuTypes_CM.NOTE_MENU.get(), windowId);
		assertInventorySize(container, 1);
		assertIntArraySize(array, 1);
		this.lectern = container;
		this.lecternData = array;
		this.addSlot(new Slot(container, 0, 0, 0) {
			public void onSlotChanged() {
				super.onSlotChanged();
				NoteMenu.this.onCraftMatrixChanged(this.inventory);
			}
		});
		this.trackIntArray(array);
	}

	public boolean enchantItem(PlayerEntity playerIn, int id) {
		if (id >= 100) {
			int k = id - 100;
			this.updateProgressBar(0, k);
			return true;
		} 
		
		else {
			switch(id) {
			case 1:
				int j = this.lecternData.get(0);
				this.updateProgressBar(0, j - 1);
				return true;
				
			case 2:
				int i = this.lecternData.get(0);
				this.updateProgressBar(0, i + 1);
				return true;
				
			case 3:
				if (!playerIn.isAllowEdit()) { return false; }

				ItemStack hStack = this.lectern.removeStackFromSlot(0);
				this.lectern.markDirty();
				if (!playerIn.inventory.addItemStackToInventory(hStack)) { playerIn.dropItem(hStack, false); }

				return true;
			default:
				return false;
			}
		}
	}

	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);
		this.detectAndSendChanges();
	}

	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.lectern.isUsableByPlayer(playerIn);
	}

	@OnlyIn(Dist.CLIENT)
	public ItemStack getBook() {
		return this.lectern.getStackInSlot(0);
	}

	@OnlyIn(Dist.CLIENT)
	public int getPage() {
		return this.lecternData.get(0);
	}
}
