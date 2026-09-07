package com.ayutaki.chinjufumod.tileentity;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;
import com.google.common.collect.Maps;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractReizouTileEntity extends LockableLootTileEntity implements IChestLid, ITickableTileEntity, IInventory, ISidedInventory, IRecipeHolder, IRecipeHelperPopulator {

	protected float prevLidAngle;
	protected float lidAngle;
	protected int openCount;
	protected net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
	
	public static int invSlot;
	protected int storageTime;
	protected int recipeCookingTime;
	public float totalEXP = 0.0F;
	protected final IIntArray dataAccess = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return AbstractReizouTileEntity.this.storageTime;
			case 1:
				return AbstractReizouTileEntity.this.recipeCookingTime;
			case 2:
				return AbstractReizouTileEntity.invSlot;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				AbstractReizouTileEntity.this.storageTime = value;
			case 1:
				AbstractReizouTileEntity.this.recipeCookingTime = value;
			case 2:
				AbstractReizouTileEntity.invSlot = value;
			}
		}

		public int size() {
			return 3;
		}
	};
	private final Map<ResourceLocation, Integer> recipesUsedMap = Maps.newHashMap();
	
	public AbstractReizouTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
	}

	/* public AbstractReizouTileEntity() {
		this(TileEntity_CM.REIZOU);
	} */

	/* インベントリ数 */
	public abstract int getSizeInventory();

	/* GUI */
	protected abstract ITextComponent getDefaultName();

	protected abstract Container createMenu(int id, PlayerInventory inventory);

	/* 収納したアイテムの処理 */
	public abstract NonNullList<ItemStack> getItems();

	public abstract void setItems(NonNullList<ItemStack> itemsIn);

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		this.storageTime = compound.getInt("StorageTime");
		this.recipeCookingTime = compound.getInt("RecipeCookingTime");
		AbstractReizouTileEntity.invSlot = compound.getInt("InventorySlot");
		this.totalEXP = compound.getFloat("TotalEXP");
		
		int i = compound.getShort("RecipesUsedSize");
		for(int j = 0; j < i; ++j) {
			ResourceLocation resource = new ResourceLocation(compound.getString("RecipeLocation" + j));
			int k = compound.getInt("RecipeAmount" + j);
			this.recipesUsedMap.put(resource, k);
		}
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		compound.putInt("StorageTime", this.storageTime);
		compound.putInt("RecipeCookingTime", this.recipeCookingTime);
		compound.putInt("InventorySlot", AbstractReizouTileEntity.invSlot);
		compound.putFloat("TotalEXP", this.totalEXP);
		
		compound.putShort("RecipesUsedSize", (short)this.recipesUsedMap.size());
		int i = 0;
		for(Entry<ResourceLocation, Integer> entry : this.recipesUsedMap.entrySet()) {
			compound.putString("RecipeLocation" + i, entry.getKey().toString());
			compound.putInt("RecipeAmount" + i, entry.getValue());
			++i;
		}
	}

	//public static int calculatePlayersUsingSync(World worldIn, LockableTileEntity lTileEntity, int ticksSinceSync, int x, int y, int z, int numPlayerUsing) {

	//public static int calculatePlayersUsing(World worldIn, LockableTileEntity lTileEntity, int x, int y, int z) {

	protected void playSound(SoundEvent sound) {
		double dx = (double) this.pos.getX() + 0.5D;
		double dy = (double) this.pos.getY() + 0.5D;
		double dz = (double) this.pos.getZ() + 0.5D;
		this.world.playSound((PlayerEntity) null, dx, dy, dz, sound, SoundCategory.BLOCKS, 1.0F, 1.0F);
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			this.openCount = type;
			return true;
		} else {
			return super.receiveClientEvent(id, type);
		}
	}

	@Override
	public void openInventory(PlayerEntity playerIn) {
		if (!playerIn.isSpectator()) {
			if (this.openCount < 0) {
				this.openCount = 0;
			}
			++this.openCount;
			this.onOpenOrClose();
		}
	}

	@Override
	public void closeInventory(PlayerEntity playerIn) {
		if (!playerIn.isSpectator()) {
			--this.openCount;
			this.onOpenOrClose();
		}
	}

	protected abstract void onOpenOrClose();

	@OnlyIn(Dist.CLIENT)
	public float getLidAngle(float partialTicks) {
		return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
	}

	public static int getPlayersUsing(IBlockReader reader, BlockPos pos) {
		BlockState state = reader.getBlockState(pos);
		if (state.hasTileEntity()) {
			TileEntity tileentity = reader.getTileEntity(pos);
			if (tileentity instanceof AbstractReizouTileEntity) {
				return ((AbstractReizouTileEntity) tileentity).openCount;
			}
		}
		return 0;
	}

	//public static void swapContents(AbstractReizouTileEntity te, AbstractReizouTileEntity otherTe) {

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		if (this.chestHandler != null) {
			this.chestHandler.invalidate();
			this.chestHandler = null;
		}
	}

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
		if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (this.chestHandler == null)
				this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
			return this.chestHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	protected abstract net.minecraftforge.items.IItemHandlerModifiable createHandler();

	@Override
	public void remove() {
		super.remove();
		if (chestHandler != null)
			chestHandler.invalidate();
	}
	
	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected abstract int getTotalCookTime(World worldIn, AbstractReizouTileEntity tileEntity);
	
	protected abstract boolean power();
	
	protected ItemStack slotStack(int i) {
		return this.getItems().get(i);
	}
	
	protected boolean readyCook(int i) {
		return !this.slotStack(i).isEmpty();
	}

	protected ItemStack resultStackSet(int slot, int resultSlot, ItemStack stack) {
		return this.getItems().set(slot + resultSlot, stack.copy());
	}
	
	///hasRemain//////////
	protected boolean canRemainCook(int i, ItemStack output) {
		ItemStack stack_i = this.slotStack(i);
		ItemStack remain_i = stack_i.getContainerItem();
		
		ItemStack stack_9 = this.slotStack(i + 9);
		ItemStack stack_1 = this.slotStack(i + 1);
		int count_1 = stack_1.getCount();
		
		if (stack_9.isEmpty()) { 
			if (stack_1.isEmpty()) { return true; }
			
			if (stack_1.isItemEqual(remain_i)) { 
				return count_1 < remain_i.getMaxStackSize(); }
		}
		
		else if (stack_9.isItemEqual(output)) { 
			if (stack_9.getCount() + output.getCount() < output.getMaxStackSize()) { 
				if (stack_1.isEmpty()) { return true; }
				
				if (stack_1.isItemEqual(remain_i)) { 
					return count_1 < remain_i.getMaxStackSize(); }
			}
		}
		return false;
	}
	
	protected void remainItem(int i) {
		ItemStack stack_i = this.slotStack(i);
		ItemStack remain_i = stack_i.getContainerItem();
		
		ItemStack stack_1 = this.slotStack(i + 1);
		if (stack_1.isEmpty()) {
			this.resultStackSet(i, 1, remain_i); }
		
		else if (stack_1.isItemEqual(remain_i)) { 
			stack_1.grow(1); }
	}
	
	///not hasRemain//////////
	protected boolean canCook(int i, ItemStack output) {
		ItemStack stack_i = this.slotStack(i);
		if (!stack_i.isEmpty()) {

			ItemStack stack_9 = this.slotStack(i + 9);
			if (stack_9.isEmpty()) { return true; }
			
			else if (stack_9.isItemEqual(output)) {
				return stack_9.getCount() + output.getCount() < output.getMaxStackSize(); }
		}
		return false;
	}
	
	protected void cookRecipe(int i, ItemStack output, IRecipe<?> iRecipe) {
		ItemStack stack_9 = this.slotStack(i + 9);
		if (stack_9.isEmpty()) {
			this.resultStackSet(i, 9, output); }
		
		else if (stack_9.isItemEqual(output)) { 
			stack_9.grow(output.getCount()); }
		
		this.slotStack(i).shrink(1);
		this.recipeCookingTime = ((AbstractColdRecipe) iRecipe).getCookingTime();
		this.totalEXP += ((AbstractColdRecipe) iRecipe).getExperience();
		AbstractReizouTileEntity.invSlot = i;
		this.storageTime = 0;
		this.setRecipeUsed(iRecipe);
		this.markDirty();
	}
	
	//private void slotCook(int i, World worldIn, ReizouTop_TileEntity tileEntity) {

	/// IRecipeHolder //////////
	@Override
	public void setRecipeUsed(IRecipe<?> iRecipe) {
		if (iRecipe != null) {
			this.recipesUsedMap.compute(iRecipe.getId(), (key, i) -> {
				 return 1 + (i == null ? 0 : i);
			 });
		 }
	}
		
	@Nullable
	@Override
	public IRecipe<?> getRecipeUsed() {
		return null;
	}

	public void onCrafting(PlayerEntity player) { }

	/// IRecipeHelperPopulator //////////
	@Override
	public void fillStackedContents(RecipeItemHelper iRecipe) {
		int[] slot_int = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		for (int i : slot_int) {
			iRecipe.accountStack(this.slotStack(i)); }
	}
	
	/// ISidedInventory //////////
	@Override
	public int[] getSlotsForFace(Direction side) {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
	}

	@Override
	public boolean canInsertItem(int i, ItemStack stack, Direction direction) {
		return this.isItemValidForSlot(i, stack);
	}

	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}
		
	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		return true;
	}

	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false; }
		else {
			return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}
	
	public boolean isEmpty() {
		for(ItemStack stack : this.getItems()) {
			if (!stack.isEmpty()) {
				return false; }
		}
		return true;
	}

  public ItemStack decrStackSize(int i, int count) {
		return ItemStackHelper.getAndSplit(this.getItems(), i, count);
	}

	public ItemStack removeStackFromSlot(int i) {
		return ItemStackHelper.getAndRemove(this.getItems(), i);
	}
	
	public void setInventorySlotContents(int i, ItemStack stack) {
		ItemStack stack_i = this.getItems().get(i);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(stack_i) && ItemStack.areItemStackTagsEqual(stack, stack_i);
		this.getItems().set(i, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
		
		if (i == 0 && !flag) { this.setAct(i); }
		if (i == 1 && !flag) { this.setAct(i); }
		if (i == 2 && !flag) { this.setAct(i); }
		if (i == 3 && !flag) { this.setAct(i); }
		if (i == 4 && !flag) { this.setAct(i); }
		if (i == 5 && !flag) { this.setAct(i); }
		if (i == 6 && !flag) { this.setAct(i); }
		if (i == 7 && !flag) { this.setAct(i); }
	}
	
	private void setAct(int i) {
		this.recipeCookingTime = this.getTotalCookTime(this.world, this);
		AbstractReizouTileEntity.invSlot = i;
		this.storageTime = 0;
		this.markDirty();
	}

	public void clear() {
		this.getItems().clear();
	}
	
	/// exp //////////
	/** Get it from the GUI. **/
	public void expFromGUI(PlayerEntity playerIn) {
		World worldIn = playerIn.world;
		if (worldIn == null || worldIn.isRemote) return;
		
		if (playerIn != null) {
			float total = this.totalEXP;
			
			if (total > 0.0F) {
				int i = MathHelper.floor(total);
				float point = total - i;
				
				if (i >= 1) {
					worldIn.addEntity(new ExperienceOrbEntity(worldIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), i));
					this.totalEXP = point;
					this.markDirty(); }
			}
		}
	}
	
	public void dropExp(World worldIn, BlockPos pos) {
		if (worldIn == null || worldIn.isRemote) return;
		
		float total = this.totalEXP;
		if (total > 0.0F) {
			
			int i = MathHelper.floor(total);
			float point = total - i;
			if (point > 0.0F && Math.random() < point)  { i++; }
			
			if (i >= 1) {
				worldIn.addEntity(new ExperienceOrbEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), i));
				this.totalEXP = 0.0F;
				this.markDirty(); }
		}
	}
}
