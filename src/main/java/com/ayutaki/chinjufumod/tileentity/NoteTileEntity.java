package com.ayutaki.chinjufumod.tileentity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.furniture.Base_NoteBook;
import com.ayutaki.chinjufumod.gui.NoteMenu;
import com.ayutaki.chinjufumod.handler.TileEntity_CM;

import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class NoteTileEntity extends TileEntity implements IClearable, INamedContainerProvider {
	
	private final IInventory bookAccess = new IInventory() {
		public int getSizeInventory() { return 1; }

		public boolean isEmpty() { return NoteTileEntity.this.book.isEmpty(); }

		public ItemStack getStackInSlot(int page) {
			return page == 0 ? NoteTileEntity.this.book : ItemStack.EMPTY; }

		public ItemStack decrStackSize(int page, int count) {
			if (page == 0) {
				ItemStack hStack = NoteTileEntity.this.book.split(count);
				if (NoteTileEntity.this.book.isEmpty()) { NoteTileEntity.this.bookRemoved(); }
				return hStack; } 
			
			else { return ItemStack.EMPTY; }
		}

		public ItemStack removeStackFromSlot(int page) {
			if (page == 0) {
				ItemStack hStack = NoteTileEntity.this.book;
				NoteTileEntity.this.book = ItemStack.EMPTY;
				NoteTileEntity.this.bookRemoved();
				return hStack; } 
			
			else { return ItemStack.EMPTY; }
		}

		public void setInventorySlotContents(int page, ItemStack stack) { }

		public int getInventoryStackLimit() { return 1; }

		public void markDirty() { NoteTileEntity.this.markDirty(); }

		public boolean isUsableByPlayer(PlayerEntity player) {
			if (NoteTileEntity.this.world.getTileEntity(NoteTileEntity.this.pos) != NoteTileEntity.this) {
				return false; } 
			
			else {
				return player.getDistanceSq((double)NoteTileEntity.this.pos.getX() + 0.5D, (double)NoteTileEntity.this.pos.getY() + 0.5D, (double)NoteTileEntity.this.pos.getZ() + 0.5D) > 64.0D ? false : NoteTileEntity.this.hasBook(); }
		}

		public boolean isItemValidForSlot(int page, ItemStack stack) { return false; }

		public void clear() { }
	};
	
	private final IIntArray dataAccess = new IIntArray() {
		public int get(int page) {
			return page == 0 ? NoteTileEntity.this.page : 0; }

		public void set(int page, int value) {
			if (page == 0) { NoteTileEntity.this.setPage(value); }
		}

		public int size() { return 1; }
	};
	
	private ItemStack book = ItemStack.EMPTY;
	private int page;
	private int pages;

	public NoteTileEntity() {
		super(TileEntity_CM.NOTE);
	}

	public ItemStack getBook() {
		return this.book;
	}

	public boolean hasBook() {
		Item item = this.book.getItem();
		return item == Items.WRITABLE_BOOK || item == Items.WRITTEN_BOOK;
	}

	public void setBook(ItemStack stack) {
		this.setBook(stack, (PlayerEntity)null);
	}

	private void bookRemoved() {
		this.page = 0;
		this.pages = 0;
		Base_NoteBook.resetBookState(this.getWorld(), this.getPos(), this.getBlockState(), false);
	}

	public void setBook(ItemStack stack, @Nullable PlayerEntity player) {
		this.book = this.ensureResolved(stack, player);
		this.page = 0;
		this.pages = WrittenBookItem.func_220049_j(this.book);
		this.markDirty();
	}

	private void setPage(int pageIn) {
		int i = MathHelper.clamp(pageIn, 0, this.pages - 1);
		if (i != this.page) {
			this.page = i;
			this.markDirty();
			Base_NoteBook.signalPageChange(this.getWorld(), this.getPos(), this.getBlockState());
		}
	}

	public int getPage() {
		return this.page;
	}

	public int getComparatorSignalLevel() {
		float f = this.pages > 1 ? (float)this.getPage() / ((float)this.pages - 1.0F) : 1.0F;
		return MathHelper.floor(f * 14.0F) + (this.hasBook() ? 1 : 0);
	}

	private ItemStack ensureResolved(ItemStack stack, @Nullable PlayerEntity player) {
		if (this.world instanceof ServerWorld && stack.getItem() == Items.WRITTEN_BOOK) {
			WrittenBookItem.resolveContents(stack, this.createCommandSource(player), player); }
		return stack;
	}

	private CommandSource createCommandSource(@Nullable PlayerEntity player) {
		String s;
		ITextComponent itext;
		if (player == null) {
			s = "Lectern";
			itext = new StringTextComponent("Lectern"); } 
		
		else {
			s = player.getName().getString();
			itext = player.getDisplayName(); }

		Vec3d vec3d = new Vec3d((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D);
		return new CommandSource(ICommandSource.DUMMY, vec3d, Vec2f.ZERO, (ServerWorld)this.world, 2, s, itext, this.world.getServer(), player);
	}

	public boolean onlyOpsCanSetNbt() {
		return true;
	}

	public void read(CompoundNBT nbt) {
		super.read(nbt);
		if (nbt.contains("Book", 10)) {
			this.book = this.ensureResolved(ItemStack.read(nbt.getCompound("Book")), (PlayerEntity)null); } 
		
		else { this.book = ItemStack.EMPTY; }

		this.pages = WrittenBookItem.func_220049_j(this.book);
		this.page = MathHelper.clamp(nbt.getInt("Page"), 0, this.pages - 1);
	}

	public CompoundNBT write(CompoundNBT nbt) {
		super.write(nbt);
		if (!this.getBook().isEmpty()) {
			nbt.put("Book", this.getBook().write(new CompoundNBT()));
			nbt.putInt("Page", this.page); }

		return nbt;
	}

	public void clear() {
		this.setBook(ItemStack.EMPTY);
	}

	public Container createMenu(int page, PlayerInventory inventory, PlayerEntity player) {
		return new NoteMenu(page, this.bookAccess, this.dataAccess);
	}

	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.lectern");
	}
}
