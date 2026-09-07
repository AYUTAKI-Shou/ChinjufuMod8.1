package com.ayutaki.chinjufumod.tileentity;

import com.ayutaki.chinjufumod.blocks.kitchen.Reizou;
import com.ayutaki.chinjufumod.gui.ReizouMenu;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.MenuTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;
import com.ayutaki.chinjufumod.recipe_type.CM_RecipeType;
import com.ayutaki.chinjufumod.recipe_type.ColdRecipeInput;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;

public class Reizou_TileEntity extends AbstractReizouTileEntity {
	
	private NonNullList<ItemStack> items = NonNullList.withSize(45, ItemStack.EMPTY);
	
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		@Override
		protected void onOpen(Level worldIn, BlockPos pos, BlockState state) {
			Reizou_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.REIZOU_OPEN.get());
			worldIn.setBlock(pos, state.setValue(Reizou.OPEN, Boolean.valueOf(true)), 3);
		}

		@Override
		protected void onClose(Level worldIn, BlockPos pos, BlockState state) {
			Reizou_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.REIZOU_CLOSE.get());
			worldIn.setBlock(pos, state.setValue(Reizou.OPEN, Boolean.valueOf(false)), 3);
		}

		@Override
		protected void openerCountChanged(Level worldIn, BlockPos pos, BlockState state, int count, int openCount) {
			Reizou_TileEntity.this.signalOpenCount(worldIn, pos, state, count, openCount);
		}

		@Override
		protected boolean isOwnContainer(Player playerIn) {
			return false;	}
	};
	
	protected Reizou_TileEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state) {
		super(entityType, pos, state, CM_RecipeType.CHILL_RECIPE.get());
	}

	public Reizou_TileEntity(BlockPos pos, BlockState state) {
		this(BlockEntity_CM.REIZOU.get(), pos, state);
	}

	@Override
	public int getContainerSize() {
		return 45;
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.chinjufumod.reizou");
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
	
	public static void serverTick(ServerLevel worldIn, BlockPos pos, BlockState state, Reizou_TileEntity tileEntity) {
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

	public static int getOpenCount(BlockGetter worldIn, BlockPos pos) {
		BlockState blockstate = worldIn.getBlockState(pos);
		if (blockstate.hasBlockEntity()) {
			BlockEntity blockentity = worldIn.getBlockEntity(pos);
			if (blockentity instanceof Reizou_TileEntity) {
				return ((Reizou_TileEntity)blockentity).openersCounter.getOpenerCount();
			}
		}
		return 0;
	}

	public static void swapContents(Reizou_TileEntity chest, Reizou_TileEntity otherChest) {
		NonNullList<ItemStack> nonnulllist = chest.getItems();
		chest.setItems(otherChest.getItems());
		otherChest.setItems(nonnulllist);
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new ReizouMenu(MenuTypes_CM.REIZOU_MENU.get(), id, inventory, this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void setBlockState(BlockState state) {
		var oldState = getBlockState();
		super.setBlockState(state);
		// Neo: Chest state change might change the chest item handler -> invalidate
		if ((oldState.getValue(Reizou.H_FACING) != state.getValue(Reizou.H_FACING))
				|| (oldState.getValue(Reizou.TYPE) != state.getValue(Reizou.TYPE))) {
			this.invalidateCapabilities();
		}
	}

	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	@Override
	protected boolean power() {
		BlockState state = this.level.getBlockState(this.worldPosition);
		boolean hasPower = (state.getBlock() instanceof Reizou)? state.getValue(Reizou.POWERED) : false;
		boolean open = (state.getBlock() instanceof Reizou)? state.getValue(Reizou.OPEN) : false;
		
		return hasPower && !open;
	}

	private void slotCook(int i, ServerLevel worldIn, Reizou_TileEntity tileEntity) {
		AbstractReizouTileEntity.invSlot = i;
		tileEntity.setChanged();
		
		if (tileEntity.readyCook(i)) {
			ColdRecipeInput recipeInput = new ColdRecipeInput(tileEntity.slotStack(i));
			
			RecipeHolder<?> iRecipe = tileEntity.quickCheck.getRecipeFor(recipeInput, worldIn).orElse(null);
			if (iRecipe != null) {

				ItemStack output = ((AbstractColdRecipe) iRecipe.value()).assemble(recipeInput, worldIn.registryAccess());
				if (!output.isEmpty()) {
					var checkRemain = tileEntity.items.get(i).getCraftingRemainder();
					
					if (!checkRemain.isEmpty()) {
						if (tileEntity.canRemainCook(i, output)) {
							tileEntity.storageTime++;
							
							if (tileEntity.storageTime >= tileEntity.recipeCookingTime) {
								tileEntity.remainItem(i);
								tileEntity.cookRecipe(i, output, iRecipe); } }
					}
					
					if (checkRemain.isEmpty()) {
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
		return i < 46;
	}
}
