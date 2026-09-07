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
		checkContainerSize(container, 1);
		checkContainerDataCount(array, 1);
		this.lectern = container;
		this.lecternData = array;
		this.addSlot(new Slot(container, 0, 0, 0) {
			public void setChanged() {
				super.setChanged();
				NoteMenu.this.slotsChanged(this.container); }
		});
		this.addDataSlots(array);
	}

	public boolean clickMenuButton(PlayerEntity playerIn, int id) {
		if (id >= 100) {
			int k = id - 100;
			this.setData(0, k);
			return true;
		} 
		
		else {
			switch(id) {
			case 1:
				int j = this.lecternData.get(0);
				this.setData(0, j - 1);
				return true;
				
			case 2:
				int i = this.lecternData.get(0);
				this.setData(0, i + 1);
				return true;
				
			case 3:
				if (!playerIn.mayBuild()) { return false; }

				ItemStack hStack = this.lectern.removeItemNoUpdate(0);
				this.lectern.setChanged();
				if (!playerIn.inventory.add(hStack)) { playerIn.drop(hStack, false); }
				return true;

			default:
				return false;
			}
		}
	}

	public void setData(int id, int data) {
		super.setData(id, data);
		this.broadcastChanges();
	}

	public boolean stillValid(PlayerEntity playerIn) {
		return this.lectern.stillValid(playerIn);
	}

	@OnlyIn(Dist.CLIENT)
	public ItemStack getBook() {
		return this.lectern.getItem(0);
	}

	@OnlyIn(Dist.CLIENT)
	public int getPage() {
		return this.lecternData.get(0);
	}
	
	public IInventory getContainer() {
		return this.lectern;
	}
}