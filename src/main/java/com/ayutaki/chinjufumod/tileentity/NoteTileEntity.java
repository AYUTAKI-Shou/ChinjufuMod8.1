package com.ayutaki.chinjufumod.tileentity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.furniture.Base_NoteBook;
import com.ayutaki.chinjufumod.gui.NoteMenu;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.component.WritableBookContent;
import net.minecraft.world.item.component.WrittenBookContent;
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
		@Override
		public int getContainerSize() { return 1; }

		@Override
		public boolean isEmpty() { return NoteTileEntity.this.book.isEmpty(); }

		@Override
		public ItemStack getItem(int nPage) {
			return nPage == 0 ? NoteTileEntity.this.book : ItemStack.EMPTY; }

		@Override
		public ItemStack removeItem(int nPage, int count) {
			if (nPage == 0) {
				ItemStack hStack = NoteTileEntity.this.book.split(count);
				if (NoteTileEntity.this.book.isEmpty()) { NoteTileEntity.this.onBookItemRemove(); }
				return hStack; }
			
			else { return ItemStack.EMPTY; }
		}

		@Override
		public ItemStack removeItemNoUpdate(int nPage) {
			if (nPage == 0) {
				ItemStack hStack = NoteTileEntity.this.book;
				NoteTileEntity.this.book = ItemStack.EMPTY;
				NoteTileEntity.this.onBookItemRemove();
				return hStack; }
			
			else { return ItemStack.EMPTY; }
		}

		@Override
		public void setItem(int nPage, ItemStack hStack) { }

		@Override
		public int getMaxStackSize() { return 1; }

		@Override
		public void setChanged() { NoteTileEntity.this.setChanged(); }

		@Override
		public boolean stillValid(Player playerIn) {
			return Container.stillValidBlockEntity(NoteTileEntity.this, playerIn) && NoteTileEntity.this.hasBook();
		}

		@Override
		public boolean canPlaceItem(int nPage, ItemStack hStack) {
			return false;
		}

		@Override
		public void clearContent() { }
	};
	
	private final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int nPage) {
			return nPage == 0 ? NoteTileEntity.this.page : 0; }

		@Override
		public void set(int nPage, int count) {
			if (nPage == 0) { NoteTileEntity.this.setPage(count); }
		}

		@Override
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

	public void setBook(ItemStack hStack) {
		this.setBook(hStack, null);
	}

	void onBookItemRemove() {
		this.page = 0;
		this.pageCount = 0;
		Base_NoteBook.resetBookState(null, this.getLevel(), this.getBlockPos(), this.getBlockState(), false);
	}

	public void setBook(ItemStack hStack, @Nullable Player playerIn) {
		this.book = this.resolveBook(hStack, playerIn);
		this.page = 0;
		this.pageCount = getPageCount(this.book);
		this.setChanged();
	}

	void setPage(int nPage) {
		int i = Mth.clamp(nPage, 0, this.pageCount - 1);
		if (i != this.page) {
			this.page = i;
			this.setChanged();
			Base_NoteBook.signalPageChange(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	public int getPage() {
		return this.page;
	}

	public int getRedstoneSignal() {
		float f = this.pageCount > 1 ? (float)this.getPage() / ((float)this.pageCount - 1.0F) : 1.0F;
		return Mth.floor(f * 14.0F) + (this.hasBook() ? 1 : 0);
	}

	private ItemStack resolveBook(ItemStack hStack, @Nullable Player playerIn) {
		if (this.level instanceof ServerLevel && hStack.is(Items.WRITTEN_BOOK)) {
			WrittenBookItem.resolveBookComponents(hStack, this.createCommandSourceStack(playerIn), playerIn);
		}
		return hStack;
	}

	private CommandSourceStack createCommandSourceStack(@Nullable Player playerIn) {
		String s;
		Component component;
		if (playerIn == null) {
			s = "Lectern";
			component = Component.literal("Lectern");
		} 
		else {
			s = playerIn.getName().getString();
			component = playerIn.getDisplayName();
		}

		Vec3 vec3 = Vec3.atCenterOf(this.worldPosition);
		return new CommandSourceStack(
			CommandSource.NULL, vec3, Vec2.ZERO, (ServerLevel)this.level, 2, s, component, this.level.getServer(), playerIn
		);
	}

	@Override
	public boolean onlyOpCanSetNbt() {
		return true;
	}

	@Override
	protected void loadAdditional(CompoundTag cTag, HolderLookup.Provider provider) {
		super.loadAdditional(cTag, provider);
		if (cTag.contains("Book", 10)) {
			this.book = this.resolveBook(ItemStack.parse(provider, cTag.getCompound("Book")).orElse(ItemStack.EMPTY), null);
		}
		else { this.book = ItemStack.EMPTY; }

		this.pageCount = getPageCount(this.book);
		this.page = Mth.clamp(cTag.getInt("Page"), 0, this.pageCount - 1);
	}

	@Override
	protected void saveAdditional(CompoundTag cTag, HolderLookup.Provider provider) {
		super.saveAdditional(cTag, provider);
		if (!this.getBook().isEmpty()) {
			cTag.put("Book", this.getBook().save(provider));
			cTag.putInt("Page", this.page);
		}
	}

	@Override
	public void clearContent() {
		this.setBook(ItemStack.EMPTY);
	}

	@Override
	public AbstractContainerMenu createMenu(int nPage, Inventory inventory, Player playerIn) {
		return new NoteMenu(nPage, this.bookAccess, this.dataAccess);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.lectern");
	}

	private static int getPageCount(ItemStack hStack) {
		WrittenBookContent written = hStack.get(DataComponents.WRITTEN_BOOK_CONTENT);
		if (written != null) { return written.pages().size(); }
		
		else {
			WritableBookContent writable = hStack.get(DataComponents.WRITABLE_BOOK_CONTENT);
			return writable != null ? writable.pages().size() : 0;
		}
	}
}
