package com.ayutaki.chinjufumod.gui;

import java.util.Objects;

import com.ayutaki.chinjufumod.handler.MenuTypes_CM;
import com.ayutaki.chinjufumod.tileentity.AbstractReizouTileEntity;
import com.ayutaki.chinjufumod.tileentity.Reizou_TileEntity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ReizouMenu extends AbstractContainerMenu {

	private final Container chestSlots;
	
	public ReizouMenu(final int windowId, final Inventory inventory, final FriendlyByteBuf data) {
		this(MenuTypes_CM.REIZOU_MENU.get(), windowId, inventory, getContainer(inventory, data));
	}
	
	public ReizouMenu(MenuType<?> type, final int windowId, final Inventory inventory, final Container tileSlots) {
		super(type, windowId);
		this.chestSlots = tileSlots;
		int containerRows = 5;
		this.chestSlots.startOpen(inventory.player);
		int i = (containerRows - 4) * 18;

		for (int j = 0; j < containerRows; ++j) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(tileSlots, k + j * 9, 8 + k * 18, 18 + j * 18) {
					
					public void onTake(Player player, ItemStack stack) {
						super.onTake(player, stack);
						 if(player instanceof ServerPlayer serverPlayer) {
							  ((AbstractReizouTileEntity)container).expFromGUI(serverPlayer); }
					}
				});
			}
		}

		for (int l = 0; l < 3; ++l) {
			 for (int j1 = 0; j1 < 9; ++j1) {
				 this.addSlot(new Slot(inventory, j1 + l * 9 + 9, 8 + j1 * 18, 104 + l * 18 + i));
			 }
		 }

		 for (int i1 = 0; i1 < 9; ++i1) {
			 this.addSlot(new Slot(inventory, i1, 8 + i1 * 18, 162 + i));
		 }
	}

	private static Container getContainer(final Inventory inventory, final FriendlyByteBuf data) {
		Objects.requireNonNull(inventory, "inventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final BlockEntity tileAtPos = inventory.player.level.getBlockEntity(data.readBlockPos());
		if(tileAtPos instanceof Reizou_TileEntity) {
			return (Reizou_TileEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}
	
	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack hStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		int containerRows = 5;
		
		if (slot.hasItem()) {
			ItemStack hStack1 = slot.getItem();
			hStack = hStack1.copy();
			
			if (index < containerRows * 9) {
				if (!this.moveItemStackTo(hStack1, containerRows * 9, this.slots.size(), true)) {
					return ItemStack.EMPTY; }
			}
			else if (!this.moveItemStackTo(hStack1, 0, containerRows * 9, false)) {
				return ItemStack.EMPTY;
			}
			
			if (hStack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}
			else {
				slot.setChanged();
			}
		}
		return hStack;
	}

	@Override
	public boolean stillValid(Player player) {
		return this.chestSlots.stillValid(player);
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		this.chestSlots.stopOpen(playerIn);
	}
	
	public Container getContainer() {
		return this.chestSlots;
	}
}
