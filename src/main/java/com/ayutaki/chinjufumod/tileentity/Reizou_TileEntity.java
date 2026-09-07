package com.ayutaki.chinjufumod.tileentity;

import com.ayutaki.chinjufumod.blocks.kitchen.Reizou;
import com.ayutaki.chinjufumod.gui.ReizouMenu;
import com.ayutaki.chinjufumod.handler.BlockEntity_CM;
import com.ayutaki.chinjufumod.handler.MenuTypes_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;
import com.ayutaki.chinjufumod.recipe_type.CM_RecipeInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;

public class Reizou_TileEntity extends AbstractReizouTileEntity {

	private NonNullList<ItemStack> items = NonNullList.withSize(45, ItemStack.EMPTY);
	
	private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
		protected void onOpen(Level worldIn, BlockPos pos, BlockState state) {
			Reizou_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.REIZOU_OPEN.get());
			worldIn.setBlock(pos, state.setValue(Reizou.OPEN, Boolean.valueOf(true)), 3);
		}

		protected void onClose(Level worldIn, BlockPos pos, BlockState state) {
			Reizou_TileEntity.playSound(worldIn, pos, state, SoundEvents_CM.REIZOU_CLOSE.get());
			worldIn.setBlock(pos, state.setValue(Reizou.OPEN, Boolean.valueOf(false)), 3);
		}

		protected void openerCountChanged(Level worldIn, BlockPos pos, BlockState state, int count, int openCount) {
			Reizou_TileEntity.this.signalOpenCount(worldIn, pos, state, count, openCount);
		}

		protected boolean isOwnContainer(Player playerIn) {
			return false;	}
	};

	protected Reizou_TileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
	}

	public Reizou_TileEntity(BlockPos pos, BlockState state) {
		this(BlockEntity_CM.REIZOU.get(), pos, state);
	}

	public int getContainerSize() {
		return 45;
	}

	protected Component getDefaultName() {
		return new TranslatableComponent("container.chinjufumod.reizou");
	}

	public void load(CompoundTag compound) {
		super.load(compound);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(compound)) {
			ContainerHelper.loadAllItems(compound, this.items);
		}
	}

	protected void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		if (!this.trySaveLootTable(compound)) {
			ContainerHelper.saveAllItems(compound, this.items);
		}
	}

	public static void serverTick(Level worldIn, BlockPos pos, BlockState state, Reizou_TileEntity tileEntity) {
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

	public void startOpen(Player playerIn) {
		if (!this.remove && !playerIn.isSpectator()) {
			this.openersCounter.incrementOpeners(playerIn, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	public void stopOpen(Player playerIn) {
		if (!this.remove && !playerIn.isSpectator()) {
			this.openersCounter.decrementOpeners(playerIn, this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	protected void setItems(NonNullList<ItemStack> stack) {
		this.items = stack;
	}

	public static int getOpenCount(BlockGetter worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos);
		if (state.hasBlockEntity()) {
			BlockEntity blockentity = worldIn.getBlockEntity(pos);
			if (blockentity instanceof Reizou_TileEntity) {
				return ((Reizou_TileEntity)blockentity).openersCounter.getOpenerCount();
			}
		}
		return 0;
	}

	public static void swapContents(Reizou_TileEntity tileEntity, Reizou_TileEntity otherTileEntity) {
		NonNullList<ItemStack> nonnulllist = tileEntity.getItems();
		tileEntity.setItems(otherTileEntity.getItems());
		otherTileEntity.setItems(nonnulllist);
	}

	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new ReizouMenu(MenuTypes_CM.REIZOU_MENU.get(), id, inventory, this);
	}

	protected net.minecraftforge.items.IItemHandlerModifiable createHandler() {
		BlockState state = this.getBlockState();
		if (!(state.getBlock() instanceof Reizou)) {
			return new net.minecraftforge.items.wrapper.InvWrapper(this);
		}
		Container inv = Reizou.getContainer((Reizou) state.getBlock(), state, getLevel(), getBlockPos(), true);
		return new net.minecraftforge.items.wrapper.InvWrapper(inv == null ? this : inv);
	}

	public void recheckOpen() {
		if (!this.remove) {
			this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
		}
	}

	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected int getTotalCookTime(Level worldIn, AbstractReizouTileEntity tileEntity) {
		return worldIn.getRecipeManager().getRecipeFor(CM_RecipeInit.CHILL_RECIPE, tileEntity, worldIn)
				.map(AbstractColdRecipe::getCookingTime).orElse(400);
	}
	
	protected boolean power() {
		BlockState state = this.level.getBlockState(this.worldPosition);
		boolean hasPower = (state.getBlock() instanceof Reizou)? state.getValue(Reizou.POWERED) : false;
		boolean open = (state.getBlock() instanceof Reizou)? state.getValue(Reizou.OPEN) : false;
		
		return hasPower && !open;
	}

	private void slotCook(int i, Level worldIn, Reizou_TileEntity tileEntity) {
		AbstractReizouTileEntity.invSlot = i;
		tileEntity.setChanged();
		
		if (tileEntity.readyCook(i)) {
			
			Recipe<?> iRecipe = worldIn.getRecipeManager().getRecipeFor(CM_RecipeInit.CHILL_RECIPE, tileEntity, worldIn).orElse(null);
			if (iRecipe != null) {

				ItemStack output = ((AbstractColdRecipe) iRecipe).assemble(tileEntity);
				if (!output.isEmpty()) {
					boolean hasRemain = tileEntity.items.get(i).hasContainerItem();
					
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
		return i < 46;
	}
}
