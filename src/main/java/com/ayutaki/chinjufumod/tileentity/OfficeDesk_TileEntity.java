package com.ayutaki.chinjufumod.tileentity;

import com.ayutaki.chinjufumod.blocks.furniture.OfficeDesk;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OfficeDesk_TileEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {

	@SuppressWarnings("unused")
	private static final int EVENT_SET_OPEN_COUNT = 1;
	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY); //
	
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level worldIn, BlockPos pos, BlockState state) {
			OfficeDesk_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.TANSU_OPEN.get());
			worldIn.setBlock(pos, state.setValue(OfficeDesk.OPEN, Boolean.valueOf(true)), 3);
		}

		@Override
		protected void onClose(Level worldIn, BlockPos pos, BlockState state) {
			OfficeDesk_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.TANSU_CLOSE.get());
			worldIn.setBlock(pos, state.setValue(OfficeDesk.OPEN, Boolean.valueOf(false)), 3);
		}

		@Override
		protected void openerCountChanged(Level worldIn, BlockPos pos, BlockState state, int count, int openCount) {
			OfficeDesk_TileEntity.this.signalOpenCount(worldIn, pos, state, count, openCount);
		}

		@Override
		protected boolean isOwnContainer(Player playerIn) {
			return false;	}
	};
	
	private final ChestLidController chestLidController = new ChestLidController();

	protected OfficeDesk_TileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
	}

	public OfficeDesk_TileEntity(BlockPos pos, BlockState state) {
		this(BlockEntity_CM.OFFICEDESK.get(), pos, state);
	}

	public int getContainerSize() {
		return 27; //27
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.chest");
	}

	@Override
	protected void loadAdditional(CompoundTag compound, HolderLookup.Provider lookup) {
		super.loadAdditional(compound, lookup);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(compound)) {
			ContainerHelper.loadAllItems(compound, this.items, lookup);
		}
	}

	@Override
	protected void saveAdditional(CompoundTag compound, HolderLookup.Provider lookup) {
		super.saveAdditional(compound, lookup);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.items, lookup);
		}
	}

	public static void lidAnimateTick(Level worldIn, BlockPos pos, BlockState state, OfficeDesk_TileEntity tileEntity) {
		tileEntity.chestLidController.tickLid();
	}

	static void playSound(Level worldIn, BlockPos pos, BlockState state, SoundEvent sound) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.5D;
		double d2 = (double)pos.getZ() + 0.5D;

		worldIn.playSound((Player)null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, worldIn.random.nextFloat() * 0.1F + 0.9F);
	}

	@Override
	public boolean triggerEvent(int id, int type) {
		if (id == 1) {
			this.chestLidController.shouldBeOpen(type > 0);
			return true;
		} else {
			return super.triggerEvent(id, type);
		}
	}

	@Override
	public void startOpen(Player playerIn) {
		if (!this.remove && !playerIn.isSpectator()) {
			this.openersCounter.incrementOpeners(playerIn, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	public void stopOpen(Player playerIn) {
		if (!this.remove && !playerIn.isSpectator()) {
			this.openersCounter.decrementOpeners(playerIn, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> stack) {
		this.items = stack;
	}

	@Override
	public float getOpenNess(float count) {
		return this.chestLidController.getOpenness(count);
	}

	public static int getOpenCount(BlockGetter worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		if (blockstate.hasBlockEntity()) {
			BlockEntity blockentity = worldIn.getBlockEntity(pos);
			if (blockentity instanceof OfficeDesk_TileEntity) {
				return ((OfficeDesk_TileEntity)blockentity).openersCounter.getOpenerCount();
			}
		}
		return 0;
	}

	public static void swapContents(OfficeDesk_TileEntity tileEntity, OfficeDesk_TileEntity otherTileEntity) {
		NonNullList<ItemStack> nonnulllist = tileEntity.getItems();
		tileEntity.setItems(otherTileEntity.getItems());
		otherTileEntity.setItems(nonnulllist);
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		//return new OfficeDeskMenu(MenuTypes_CM.TANSU_MENU.get(), id, inventory, this); Stopped using it in 1.20.2. 
		return new ChestMenu(MenuType.GENERIC_9x3, id, inventory, this, 3);
	}

	private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;

	@SuppressWarnings("deprecation")
	@Override
	public void setBlockState(BlockState state) {
		super.setBlockState(state);
		if (this.chestHandler != null) {
			var oldHandler = this.chestHandler;
			this.chestHandler = null;
			oldHandler.invalidate();
		}
	}

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
		if (cap == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER && !this.remove) {
			if (this.chestHandler == null)
				this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof OfficeDesk chestBlock)) {
			return new net.minecraftforge.items.wrapper.InvWrapper(this);
		}
		Container inv = OfficeDesk.getContainer(chestBlock, state, getLevel(), getBlockPos(), true);
		return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		if (chestHandler != null) {
			chestHandler.invalidate();
			chestHandler = null;
		}
	}
	
	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	protected void signalOpenCount(Level worldIn, BlockPos pos, BlockState state, int count, int openCount) {
		Block block = state.getBlock();
		worldIn.blockEvent(pos, block, 1, openCount);
	}
}
