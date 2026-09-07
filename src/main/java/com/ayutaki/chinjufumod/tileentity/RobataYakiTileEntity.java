package com.ayutaki.chinjufumod.tileentity;

import java.util.Optional;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CampfireCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class RobataYakiTileEntity extends TileEntity implements IClearable, ITickableTileEntity {

	private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
	private final int[] cookingProgress = new int[4];
	private final int[] cookingTime = new int[4];

	public RobataYakiTileEntity() {
		super(TileEntityType.CAMPFIRE);
	}

	public void tick() {
		boolean flag = this.getBlockState().getValue(CampfireBlock.LIT);

		if (!this.level.isClientSide) {
			if (flag) {
				this.cook();
			} 
			
			else {
				for(int i = 0; i < this.items.size(); ++i) {
					if (this.cookingProgress[i] > 0) {
						this.cookingProgress[i] = MathHelper.clamp(this.cookingProgress[i] - 2, 0, this.cookingTime[i]);
					}
				}
			}
		}
	}

	private void cook() {
		for(int i = 0; i < this.items.size(); ++i) {
			ItemStack stack_i = this.items.get(i);
			
			if (!stack_i.isEmpty()) {
				int j = this.cookingProgress[i]++;
				
				if (this.cookingProgress[i] >= this.cookingTime[i]) {
					IInventory iinventory = new Inventory(stack_i);
					
					ItemStack output = this.level.getRecipeManager()
							.getRecipeFor(IRecipeType.CAMPFIRE_COOKING, iinventory, this.level).map((iRecipe) -> {
						return iRecipe.assemble(iinventory);
					}).orElse(stack_i);
					
					BlockPos pos = this.getBlockPos();
					InventoryHelper.dropItemStack(this.level, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), output);
					this.items.set(i, ItemStack.EMPTY);
					this.markUpdated();
				}
			}
		}
	}

	public NonNullList<ItemStack> getItems() {
		return this.items;
	}

	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
		this.items.clear();
		ItemStackHelper.loadAllItems(compound, this.items);
		if (compound.contains("CookingTimes", 11)) {
			int[] aint = compound.getIntArray("CookingTimes");
			System.arraycopy(aint, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, aint.length));
		}

		if (compound.contains("CookingTotalTimes", 11)) {
			int[] aint1 = compound.getIntArray("CookingTotalTimes");
			System.arraycopy(aint1, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, aint1.length));
		}
	}

	public CompoundNBT save(CompoundNBT compound) {
		this.saveMetadataAndItems(compound);
		compound.putIntArray("CookingTimes", this.cookingProgress);
		compound.putIntArray("CookingTotalTimes", this.cookingTime);
		return compound;
	}

	private CompoundNBT saveMetadataAndItems(CompoundNBT compound) {
		super.save(compound);
		ItemStackHelper.saveAllItems(compound, this.items, true);
		return compound;
	}

	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(this.worldPosition, 13, this.getUpdateTag());
	}

	public CompoundNBT getUpdateTag() {
		return this.saveMetadataAndItems(new CompoundNBT());
	}

	public Optional<CampfireCookingRecipe> getCookableRecipe(ItemStack stack) {
		return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() : this.level.getRecipeManager()
				.getRecipeFor(IRecipeType.CAMPFIRE_COOKING, new Inventory(stack), this.level);
	}

	public boolean placeFood(ItemStack stack, int time) {
		for(int i = 0; i < this.items.size(); ++i) {
			ItemStack stack_i = this.items.get(i);
			
			if (stack_i.isEmpty()) {
				this.cookingTime[i] = time;
				this.cookingProgress[i] = 0;
				this.items.set(i, stack.split(1));
				this.markUpdated();
				return true; }
		}
		return false;
	}

	private void markUpdated() {
		this.setChanged();
		this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
	}

	public void clearContent() {
		this.items.clear();
	}

	public void dowse() {
		if (this.level != null) {
			if (!this.level.isClientSide) {
				InventoryHelper.dropContents(this.level, this.getBlockPos(), this.getItems());
			}
			this.markUpdated();
		}
	}
}
