package com.ayutaki.chinjufumod.tileentity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.furniture.Base_NoteBook;
import com.ayutaki.chinjufumod.gui.NoteMenu;
import com.ayutaki.chinjufumod.handler.TileEntity_CM;

import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class NoteTileEntity extends TileEntity implements IClearable, INamedContainerProvider {
	
	private final IInventory bookAccess = new IInventory() {
		public int getContainerSize() { return 1; }

		public boolean isEmpty() { return NoteTileEntity.this.book.isEmpty(); }

		public ItemStack getItem(int page) {
			return page == 0 ? NoteTileEntity.this.book : ItemStack.EMPTY; }

		public ItemStack removeItem(int page, int count) {
			if (page == 0) {
				ItemStack hStack = NoteTileEntity.this.book.split(count);
				if (NoteTileEntity.this.book.isEmpty()) { NoteTileEntity.this.onBookItemRemove(); }
				return hStack; } 
			
			else { return ItemStack.EMPTY; }
		}

		public ItemStack removeItemNoUpdate(int page) {
			if (page == 0) {
				ItemStack hStack = NoteTileEntity.this.book;
				NoteTileEntity.this.book = ItemStack.EMPTY;
				NoteTileEntity.this.onBookItemRemove();
				return hStack; } 
			
			else { return ItemStack.EMPTY; }
		}

		public void setItem(int size, ItemStack stack) { }

		public int getMaxStackSize() { return 1; }

		public void setChanged() { NoteTileEntity.this.setChanged(); }

		public boolean stillValid(PlayerEntity player) {
			if (NoteTileEntity.this.level.getBlockEntity(NoteTileEntity.this.worldPosition) != NoteTileEntity.this) {
				return false; } 
			
			else {
				return player.distanceToSqr((double)NoteTileEntity.this.worldPosition.getX() + 0.5D, (double)NoteTileEntity.this.worldPosition.getY() + 0.5D, (double)NoteTileEntity.this.worldPosition.getZ() + 0.5D) > 64.0D ? false : NoteTileEntity.this.hasBook(); }
		}

		public boolean canPlaceItem(int size_1, ItemStack stack) { return false; }

		public void clearContent() { }
	};
	
	private final IIntArray dataAccess = new IIntArray() {
		public int get(int page) {
			return page == 0 ? NoteTileEntity.this.page : 0; }

		public void set(int page, int count) {
			if (page == 0) { NoteTileEntity.this.setPage(count); }
		}

		public int getCount() { return 1; }
	};
	
	private ItemStack book = ItemStack.EMPTY;
	private int page;
	private int pageCount;

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

	private void onBookItemRemove() {
		this.page = 0;
		this.pageCount = 0;
		Base_NoteBook.resetBookState(this.getLevel(), this.getBlockPos(), this.getBlockState(), false);
	}

	public void setBook(ItemStack stack, @Nullable PlayerEntity player) {
		this.book = this.resolveBook(stack, player);
		this.page = 0;
		this.pageCount = WrittenBookItem.getPageCount(this.book);
		this.setChanged();
	}

	private void setPage(int page) {
		int i = MathHelper.clamp(page, 0, this.pageCount - 1);
		if (i != this.page) {
			this.page = i;
			this.setChanged();
			Base_NoteBook.signalPageChange(this.getLevel(), this.getBlockPos(), this.getBlockState());	
		}
	}

	public int getPage() {
		return this.page;
	}

	private ItemStack resolveBook(ItemStack stack, @Nullable PlayerEntity player) {
		if (this.level instanceof ServerWorld && stack.getItem() == Items.WRITTEN_BOOK) {
			WrittenBookItem.resolveBookComponents(stack, this.createCommandSourceStack(player), player); }
		return stack;
	}

	private CommandSource createCommandSourceStack(@Nullable PlayerEntity player) {
		String s;
		ITextComponent itext;
		if (player == null) {
			s = "Lectern";
			itext = new StringTextComponent("Lectern"); } 
		
		else {
			s = player.getName().getString();
			itext = player.getDisplayName(); }

		Vector3d vector3d = Vector3d.atCenterOf(this.worldPosition);
		return new CommandSource(ICommandSource.NULL, vector3d, Vector2f.ZERO, (ServerWorld)this.level, 2, s, itext, this.level.getServer(), player);
	}

	public boolean onlyOpCanSetNbt() {
		return true;
	}

	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		if (nbt.contains("Book", 10)) {
			this.book = this.resolveBook(ItemStack.of(nbt.getCompound("Book")), (PlayerEntity)null); } 
		
		else { this.book = ItemStack.EMPTY; }

		this.pageCount = WrittenBookItem.getPageCount(this.book);
		this.page = MathHelper.clamp(nbt.getInt("Page"), 0, this.pageCount - 1);
	}

	public CompoundNBT save(CompoundNBT nbt) {
		super.save(nbt);
		if (!this.getBook().isEmpty()) {
			nbt.put("Book", this.getBook().save(new CompoundNBT()));
			nbt.putInt("Page", this.page); }

		return nbt;
	}

	public void clearContent() {
		this.setBook(ItemStack.EMPTY);
	}

	public Container createMenu(int page, PlayerInventory inventory, PlayerEntity player) {
		return new NoteMenu(page, this.bookAccess, this.dataAccess);
	}

	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.lectern");
	}
}
