package com.ayutaki.chinjufumod.tileentity;

import com.ayutaki.chinjufumod.blocks.furniture.Tansu;
import com.ayutaki.chinjufumod.gui.TansuMenu;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.MenuTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
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

public class Tansu_TileEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
	
	@SuppressWarnings("unused")
	private static final int EVENT_SET_OPEN_COUNT = 1;
	private NonNullList<ItemStack> items = NonNullList.withSize(45, ItemStack.EMPTY);
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level worldIn, BlockPos pos, BlockState state) {
			Tansu_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.TANSU_OPEN.get());
			worldIn.setBlock(pos, state.setValue(Tansu.OPEN, Boolean.valueOf(true)), 3);
		}

		@Override
		protected void onClose(Level worldIn, BlockPos pos, BlockState state) {
			Tansu_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.TANSU_CLOSE.get());
			worldIn.setBlock(pos, state.setValue(Tansu.OPEN, Boolean.valueOf(false)), 3);
		}

		@Override
		protected void openerCountChanged(Level worldIn, BlockPos pos, BlockState state, int count, int openCount) {
			Tansu_TileEntity.this.signalOpenCount(worldIn, pos, state, count, openCount);
		}

		@Override
		protected boolean isOwnContainer(Player playerIn) {
			return false;	}
	};
	
	private final ChestLidController chestLidController = new ChestLidController();

	protected Tansu_TileEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state) {
		super(entityType, pos, state);
	}

	public Tansu_TileEntity(BlockPos pos, BlockState state) {
		this(BlockEntity_CM.TANSU.get(), pos, state);
	}

	@Override
	public int getContainerSize() {
		return 45;
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

	public static void lidAnimateTick(Level worldIn, BlockPos pos, BlockState state, Tansu_TileEntity tileEntity) {
		tileEntity.chestLidController.tickLid();
	}

	static void playSound(Level worldIn, BlockPos pos, BlockState state, SoundEvent sound) {
		double d0 = (double)pos.getX() + 0.5;
		double d1 = (double)pos.getY() + 0.5;
		double d2 = (double)pos.getZ() + 0.5;

		worldIn.playSound(null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, worldIn.random.nextFloat() * 0.1F + 0.9F);
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
	public void startOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	public void stopOpen(Player player) {
		if (!this.remove && !player.isSpectator()) {
			this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	public float getOpenNess(float partialTicks) {
		return this.chestLidController.getOpenness(partialTicks);
	}

	public static int getOpenCount(BlockGetter worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		if (blockstate.hasBlockEntity()) {
			BlockEntity blockentity = worldIn.getBlockEntity(pos);
			if (blockentity instanceof Tansu_TileEntity) {
				return ((Tansu_TileEntity)blockentity).openersCounter.getOpenerCount();
			}
		}
		return 0;
	}

	public static void swapContents(Tansu_TileEntity chest, Tansu_TileEntity otherChest) {
		NonNullList<ItemStack> nonnulllist = chest.getItems();
		chest.setItems(otherChest.getItems());
		otherChest.setItems(nonnulllist);
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new TansuMenu(MenuTypes_CM.TANSU_MENU.get(), id, inventory, this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setBlockState(BlockState state) {
		var oldState = getBlockState();
		super.setBlockState(state);
		// Neo: Chest state change might change the chest item handler -> invalidate
		if ((oldState.getValue(Tansu.H_FACING) != state.getValue(Tansu.H_FACING))
				|| (oldState.getValue(Tansu.TYPE) != state.getValue(Tansu.TYPE))) {
			this.invalidateCapabilities();
		}
	}

	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	protected void signalOpenCount(Level worldIn, BlockPos pos, BlockState state, int eventId, int eventParam) {
		Block block = state.getBlock();
		worldIn.blockEvent(pos, block, 1, eventParam);
	}
}
