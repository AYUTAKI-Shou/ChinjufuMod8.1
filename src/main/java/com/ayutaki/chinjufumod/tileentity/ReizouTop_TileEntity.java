package com.ayutaki.chinjufumod.tileentity;

import com.ayutaki.chinjufumod.blocks.kitchen.ReizouTop;
import com.ayutaki.chinjufumod.gui.ReizouTopMenu;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.MenuTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;
import com.ayutaki.chinjufumod.recipe_type.CM_RecipeType;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;

public class ReizouTop_TileEntity extends AbstractReizouTileEntity {

	private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);
	
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level worldIn, BlockPos pos, BlockState state) {
			ReizouTop_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.REIZOU_OPEN.get());
			worldIn.setBlock(pos, state.setValue(ReizouTop.OPEN, Boolean.valueOf(true)), 3);
		}

		@Override
		protected void onClose(Level worldIn, BlockPos pos, BlockState state) {
			ReizouTop_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.REIZOU_CLOSE.get());
			worldIn.setBlock(pos, state.setValue(ReizouTop.OPEN, Boolean.valueOf(false)), 3);
		}

		@Override
		protected void openerCountChanged(Level worldIn, BlockPos pos, BlockState state, int count, int openCount) {
			ReizouTop_TileEntity.this.signalOpenCount(worldIn, pos, state, count, openCount);
		}

		@Override
		protected boolean isOwnContainer(Player playerIn) {
			return false;	}
	};

	protected ReizouTop_TileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state, CM_RecipeType.FREEZE_RECIPE.get());
	}

	public ReizouTop_TileEntity(BlockPos pos, BlockState state) {
		this(BlockEntity_CM.REIZOU_TOP.get(), pos, state);
	}

	public int getContainerSize() {
		return 27;
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.chinjufumod.reitou");
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

	public static void serverTick(Level worldIn, BlockPos pos, BlockState state, ReizouTop_TileEntity tileEntity) {
		if (tileEntity.power()) { 
			tileEntity.slotCook(0, worldIn, tileEntity);
			tileEntity.slotCook(1, worldIn, tileEntity);
			tileEntity.slotCook(2, worldIn, tileEntity);
			tileEntity.slotCook(3, worldIn, tileEntity);
			tileEntity.slotCook(4, worldIn, tileEntity);
			tileEntity.slotCook(5, worldIn, tileEntity);
			tileEntity.slotCook(6, worldIn, tileEntity);
			tileEntity.slotCook(7, worldIn, tileEntity);
		}
		
		else {
			tileEntity.storageTime = 0;
			tileEntity.setChanged(); }
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

	public static int getOpenCount(BlockGetter worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		if (blockstate.hasBlockEntity()) {
			BlockEntity blockentity = worldIn.getBlockEntity(pos);
			if (blockentity instanceof ReizouTop_TileEntity) {
				return ((ReizouTop_TileEntity)blockentity).openersCounter.getOpenerCount();
			}
		}
		return 0;
	}

	public static void swapContents(ReizouTop_TileEntity tileEntity, ReizouTop_TileEntity otherTileEntity) {
		NonNullList<ItemStack> nonnulllist = tileEntity.getItems();
		tileEntity.setItems(otherTileEntity.getItems());
		otherTileEntity.setItems(nonnulllist);
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new ReizouTopMenu(MenuTypes_CM.REIZOUTOP_MENU.get(), id, inventory, this);
	}

	protected net.minecraftforge.items.IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof ReizouTop chestBlock)) {
			return new net.minecraftforge.items.wrapper.InvWrapper(this);
		}
		Container inv = ReizouTop.getContainer(chestBlock, state, getLevel(), getBlockPos(), true);
		return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
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

	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected boolean power() {
		BlockState state = this.level.getBlockState(this.worldPosition);
		boolean hasPower = (state.getBlock() instanceof ReizouTop)? state.getValue(ReizouTop.POWERED) : false;
		boolean open = (state.getBlock() instanceof ReizouTop)? state.getValue(ReizouTop.OPEN) : false;
		
		return hasPower && !open;
	}

	private void slotCook(int i, Level worldIn, ReizouTop_TileEntity tileEntity) {
		AbstractReizouTileEntity.invSlot = i;
		tileEntity.setChanged();
		
		if (tileEntity.readyCook(i)) {
			
			RecipeHolder<?> iRecipe = tileEntity.quickCheck.getRecipeFor(tileEntity, worldIn).orElse(null);
			if (iRecipe != null) {

				ItemStack output = ((AbstractColdRecipe) iRecipe.value()).assemble(tileEntity, worldIn.registryAccess());
				if (!output.isEmpty()) {
					boolean hasRemain = tileEntity.items.get(i).hasCraftingRemainingItem();
					
					if (hasRemain) {
						if (tileEntity.canRemainCook(i, output)) {
							tileEntity.storageTime++;
							
							if (tileEntity.storageTime >= tileEntity.recipeCookingTime) {
								tileEntity.remainItem(i);
								tileEntity.cookRecipe(i, output, iRecipe); } }
					}
					
					if (!hasRemain) {
						if (tileEntity.canCook(i, output)) {
							tileEntity.storageTime++;
							
							if (tileEntity.storageTime >= tileEntity.recipeCookingTime) {
								tileEntity.cookRecipe(i, output, iRecipe); } }
					}
					
				}
			}
		}
	}

	/// WorldlyContainer //////////
	@Override
	public boolean canPlaceItem(int i, ItemStack stack) {
		return i < 28;
	}
}
