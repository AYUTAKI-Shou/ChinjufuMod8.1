package com.ayutaki.chinjufumod.tileentity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.gui.NoteMenu;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.WrittenBookItem;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class NoteTileEntity extends BlockEntity implements Clearable, MenuProvider {
	public static final int DATA_PAGE = 0;
	public static final int NUM_DATA = 1;
	public static final int SLOT_BOOK = 0;
	public static final int NUM_SLOTS = 1;
	
	private final Container bookAccess = new Container() {
		public int getContainerSize() { return 1; }

		public boolean isEmpty() { return NoteTileEntity.this.book.isEmpty(); }

		public ItemStack getItem(int nPage) {
			return nPage == 0 ? NoteTileEntity.this.book : ItemStack.EMPTY; }

		public ItemStack removeItem(int nPage, int count) {
			if (nPage == 0) {
				ItemStack hStack = NoteTileEntity.this.book.split(count);
				if (NoteTileEntity.this.book.isEmpty()) { NoteTileEntity.this.onBookItemRemove(); }
				return hStack; } 
			
			else { return ItemStack.EMPTY; }
		}

		public ItemStack removeItemNoUpdate(int nPage) {
			if (nPage == 0) {
				ItemStack hStack = NoteTileEntity.this.book;
				NoteTileEntity.this.book = ItemStack.EMPTY;
				NoteTileEntity.this.onBookItemRemove();
				return hStack; } 
			
			else { return ItemStack.EMPTY; }
		}

		public void setItem(int nPage, ItemStack stack) { }

		public int getMaxStackSize() { return 1; }

		public void setChanged() { NoteTileEntity.this.setChanged(); }

		public boolean stillValid(Player playerIn) {
			if (NoteTileEntity.this.level.getBlockEntity(NoteTileEntity.this.worldPosition) != NoteTileEntity.this) {
				return false; } 
			
			else {
				return playerIn.distanceToSqr((double)NoteTileEntity.this.worldPosition.getX() + 0.5D, (double)NoteTileEntity.this.worldPosition.getY() + 0.5D, (double)NoteTileEntity.this.worldPosition.getZ() + 0.5D) > 64.0D ? false : NoteTileEntity.this.hasBook(); }
		}

		public boolean canPlaceItem(int nPage, ItemStack stack) { return false; }

		public void clearContent() { }
	};
	
	private final ContainerData dataAccess = new ContainerData() {
		public int get(int nPage) {
			return nPage == 0 ? NoteTileEntity.this.page : 0; }

		public void set(int nPage, int count) {
			if (nPage == 0) { NoteTileEntity.this.setPage(count); }
		}

		public int getCount() { return 1; }
	};
	
	ItemStack book = ItemStack.EMPTY;
	int page;
	private int pageCount;

	public NoteTileEntity(BlockPos pos, BlockState state) {
		super(BlockEntity_CM.NOTE.get(), pos, state);
	}

	public ItemStack getBook() {
		return this.book;
	}

	public boolean hasBook() {
		return this.book.is(Items.WRITABLE_BOOK) || this.book.is(Items.WRITTEN_BOOK);
	}

	public void setBook(ItemStack stack) {
		this.setBook(stack, (Player)null);
	}

	void onBookItemRemove() {
		this.page = 0;
		this.pageCount = 0;
		NoteBook.resetBookState(this.getLevel(), this.getBlockPos(), this.getBlockState(), false);
	}

	public void setBook(ItemStack stack, @Nullable Player playerIn) {
		this.book = this.resolveBook(stack, playerIn);
		this.page = 0;
		this.pageCount = WrittenBookItem.getPageCount(this.book);
		this.setChanged();
	}

	void setPage(int nPage) {
		int i = Mth.clamp(nPage, 0, this.pageCount - 1);
		if (i != this.page) {
			this.page = i;
			this.setChanged();
			NoteBook.signalPageChange(this.getLevel(), this.getBlockPos(), this.getBlockState()); }
	}

	public int getPage() {
		return this.page;
	}

	public int getRedstoneSignal() {
		float f = this.pageCount > 1 ? (float)this.getPage() / ((float)this.pageCount - 1.0F) : 1.0F;
		return Mth.floor(f * 14.0F) + (this.hasBook() ? 1 : 0);
	}

	private ItemStack resolveBook(ItemStack stack, @Nullable Player playerIn) {
		if (this.level instanceof ServerLevel && stack.is(Items.WRITTEN_BOOK)) {
			WrittenBookItem.resolveBookComponents(stack, this.createCommandSourceStack(playerIn), playerIn); }
		return stack;
	}

	private CommandSourceStack createCommandSourceStack(@Nullable Player playerIn) {
		String s;
		Component component;
		if (playerIn == null) {
			s = "Lectern";
			component = new TextComponent("Lectern"); } 
		
		else {
			s = playerIn.getName().getString();
			component = playerIn.getDisplayName(); }

		Vec3 vec3 = Vec3.atCenterOf(this.worldPosition);
		return new CommandSourceStack(CommandSource.NULL, vec3, Vec2.ZERO, (ServerLevel)this.level, 2, s, component, this.level.getServer(), playerIn);
	}

	public boolean onlyOpCanSetNbt() {
		return true;
	}

	public void load(CompoundTag cTag) {
		super.load(cTag);
		if (cTag.contains("Book", 10)) {
			this.book = this.resolveBook(ItemStack.of(cTag.getCompound("Book")), (Player)null); } 
		
		else { this.book = ItemStack.EMPTY; }

		this.pageCount = WrittenBookItem.getPageCount(this.book);
		this.page = Mth.clamp(cTag.getInt("Page"), 0, this.pageCount - 1);
	}

	protected void saveAdditional(CompoundTag cTag) {
		super.saveAdditional(cTag);
		if (!this.getBook().isEmpty()) {
			cTag.put("Book", this.getBook().save(new CompoundTag()));
			cTag.putInt("Page", this.page); }
	}

	public void clearContent() {
		this.setBook(ItemStack.EMPTY);
	}

	public AbstractContainerMenu createMenu(int nPage, Inventory inventory, Player playerIn) {
		return new NoteMenu(nPage, this.bookAccess, this.dataAccess);
	}

	public Component getDisplayName() {
		return new TranslatableComponent("container.lectern");
	}
}
