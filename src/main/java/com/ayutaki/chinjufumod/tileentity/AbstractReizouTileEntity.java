package com.ayutaki.chinjufumod.tileentity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public abstract class AbstractReizouTileEntity extends LockableLootTileEntity implements ITickableTileEntity, IInventory, ISidedInventory, IRecipeHolder, IRecipeHelperPopulator {

	protected float preOpenness;
	protected float lidOpenness;
	protected int openCount;
	private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
	
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

		public int getCount() {
			return 3;
		}
	};
	protected final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	
	protected AbstractReizouTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
	}

	/* public AbstractReizouTileEntity() {
		this(TileEntity_CM.REIZOU);
	} */

	public abstract int getContainerSize();

	protected abstract ITextComponent getDefaultName();

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
		this.storageTime = compound.getInt("StorageTime");
		this.recipeCookingTime = compound.getInt("RecipeCookingTime");
		AbstractReizouTileEntity.invSlot = compound.getInt("InventorySlot");
		this.totalEXP = compound.getFloat("TotalEXP");
		
		CompoundNBT nbt = compound.getCompound("RecipesUsed");
		for(String s : nbt.getAllKeys()) {
			this.recipesUsed.put(new ResourceLocation(s), nbt.getInt(s)); }
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		compound.putInt("StorageTime", this.storageTime);
		compound.putInt("RecipeCookingTime", this.recipeCookingTime);
		compound.putInt("InventorySlot", AbstractReizouTileEntity.invSlot);
		compound.putFloat("TotalEXP", this.totalEXP);
		
		CompoundNBT nbt = new CompoundNBT();
		this.recipesUsed.forEach((name, i) -> { nbt.putInt(name.toString(), i); });
		compound.put("RecipesUsed", nbt);
		return compound;
	}


	//public static int getOpenCount(World worldIn, LockableTileEntity lTileEntity, int ticksSinceSync,

	//public static int getOpenCount(World worldIn, LockableTileEntity lTileEntity, int x, int y, int z)

	protected void playSound(SoundEvent sound) {
		double d0 = (double)this.worldPosition.getX() + 0.5D;
		double d1 = (double)this.worldPosition.getY() + 0.5D;
		double d2 = (double)this.worldPosition.getZ() + 0.5D;

		this.level.playSound((PlayerEntity)null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
	}

	@Override
	public boolean triggerEvent(int id, int type) {
		if (id == 1) {
			this.openCount = type;
			return true;
		} 
		else {
			return super.triggerEvent(id, type);
		}
	}

	@Override
	public void startOpen(PlayerEntity playerIn) {
		if (!playerIn.isSpectator()) {
			if (this.openCount < 0) {
				this.openCount = 0;
			}
			++this.openCount;
			this.signalOpenCount();
		}
	}

	public void stopOpen(PlayerEntity playerIn) {
		if (!playerIn.isSpectator()) {
			--this.openCount;
			this.signalOpenCount();
		}
	}

	protected abstract void signalOpenCount();

	protected abstract NonNullList<ItemStack> getItems();

	protected abstract void setItems(NonNullList<ItemStack> stack);

	public static int getOpenCount(IBlockReader worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos);
		if (state.hasTileEntity()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof AbstractReizouTileEntity) {
				return ((AbstractReizouTileEntity)tileentity).openCount;
			}
		}
		return 0;
	}

	//public static void swapContents(AbstractReizouTileEntity tileEntity, AbstractReizouTileEntity otherTileEntity)

	protected abstract Container createMenu(int id, PlayerInventory inventory);

	@Override
	public void clearCache() {
		super.clearCache();
		if (this.chestHandler != null) {
			net.minecraftforge.common.util.LazyOptional<?> oldHandler = this.chestHandler;
			this.chestHandler = null;
			oldHandler.invalidate();
		}
	}

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
		 if (!this.remove && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			 if (this.chestHandler == null)
				 this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
			 return this.chestHandler.cast();
		 }
		 return super.getCapability(cap, side);
	}

	////////////////////////
	protected abstract net.minecraftforge.items.IItemHandlerModifiable createHandler();

	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
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
			
			if (stack_1.sameItem(remain_i)) { 
				return count_1 < remain_i.getMaxStackSize(); }
		}
		
		else if (stack_9.sameItem(output)) { 
			if (stack_9.getCount() + output.getCount() < output.getMaxStackSize()) { 
				if (stack_1.isEmpty()) { return true; }
				
				if (stack_1.sameItem(remain_i)) { 
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
		
		else if (stack_1.sameItem(remain_i)) { 
			stack_1.grow(1); }
	}
	
	///not hasRemain//////////
	protected boolean canCook(int i, ItemStack output) {
		ItemStack stack_i = this.slotStack(i);
		if (!stack_i.isEmpty()) {

			ItemStack stack_9 = this.slotStack(i + 9);
			if (stack_9.isEmpty()) { return true; }
			
			else if (stack_9.sameItem(output)) {
				return stack_9.getCount() + output.getCount() < output.getMaxStackSize(); }
		}
		return false;
	}
	
	protected void cookRecipe(int i, ItemStack output, IRecipe<?> iRecipe) {
		ItemStack stack_9 = this.slotStack(i + 9);
		if (stack_9.isEmpty()) {
			this.resultStackSet(i, 9, output); }
		
		else if (stack_9.sameItem(output)) { 
			stack_9.grow(output.getCount()); }
		
		this.slotStack(i).shrink(1);
		this.recipeCookingTime = ((AbstractColdRecipe) iRecipe).getCookingTime();
		this.totalEXP += ((AbstractColdRecipe) iRecipe).getExperience();
		AbstractReizouTileEntity.invSlot = i;
		this.storageTime = 0;
		this.setRecipeUsed(iRecipe);
		this.setChanged();
	}
	
	//	private void slotCook(int i, World worldIn, ReizouTop_TileEntity tileEntity) {
	
	/// IRecipeHolder //////////
	@Override
	public void setRecipeUsed(IRecipe<?> iRecipe) {
		if (iRecipe != null) {
			ResourceLocation resource = iRecipe.getId();
			this.recipesUsed.addTo(resource, 1);}
	}

	@Nullable
	@Override
	public IRecipe<?> getRecipeUsed() {
		return null;
	}
	
	public void awardUsedRecipes(PlayerEntity playerIn) { }

	/// IRecipeHelperPopulator //////////
	@Override
	public void fillStackedContents(RecipeItemHelper iRecipe) {
		int[] slot_int = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		for (int i : slot_int) {
			iRecipe.accountStack(this.slotStack(i)); }
	}
	
	/// ISidedInventory //////////
	@Override
	public int[] getSlotsForFace(Direction direction) {
		return new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
	}

	@Override
	public boolean canPlaceItemThroughFace(int i, ItemStack stack, Direction direction) {
		return this.canPlaceItem(i, stack);
	}

	public abstract boolean canPlaceItem(int i, ItemStack stack);
	
	@Override
	public boolean canTakeItemThroughFace(int i, ItemStack stack, Direction direction) {
		return true;
	}
	
	public boolean stillValid(PlayerEntity playerIn) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false; } 
		
		else {
			return playerIn.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D; }
	}
	
	public boolean isEmpty() {
		for(ItemStack stack : this.getItems()) {
			if (!stack.isEmpty()) {
				return false; }
		}
		return true;
	}

	public ItemStack removeItem(int i, int count) {
		return ItemStackHelper.removeItem(this.getItems(), i, count);
	}

	public ItemStack removeItemNoUpdate(int i) {
		return ItemStackHelper.takeItem(this.getItems(), i);
	}
	
	public void setItem(int i, ItemStack stack) {
		ItemStack stack_i = this.getItems().get(i);
		boolean flag = !stack.isEmpty() && stack.sameItem(stack_i) && ItemStack.tagMatches(stack, stack_i);
		this.getItems().set(i, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
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
		this.recipeCookingTime = this.getTotalCookTime(this.level, this);
		AbstractReizouTileEntity.invSlot = i;
		this.storageTime = 0;
		this.setChanged();
	}
	
	public void clearContent() {
		this.getItems().clear();
	}
	
	/// exp //////////
	/** Get it from the GUI. **/
	public void expFromGUI(PlayerEntity playerIn) {
		World worldIn = playerIn.level;
		if (worldIn == null || worldIn.isClientSide) return;
		
		if (playerIn != null) {
			float total = this.totalEXP;
			
			if (total > 0.0F) {
				int i = MathHelper.floor(total);
				float point = total - i;
				
				if (i >= 1) {
					Vector3d v3d = playerIn.position();
					worldIn.addFreshEntity(new ExperienceOrbEntity(worldIn, v3d.x, v3d.y, v3d.z, i));
					this.totalEXP = point;
					this.setChanged(); }
			}
		}
	}
	
	public void dropExp(World worldIn, BlockPos pos) {
		if (worldIn == null || worldIn.isClientSide) return;
		
		float total = this.totalEXP;
		if (total > 0.0F) {
			
			int i = MathHelper.floor(total);
			float point = total - i;
			if (point > 0.0F && Math.random() < point)  { i++; }
			
			if (i >= 1) {
				worldIn.addFreshEntity(new ExperienceOrbEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), i));
				this.totalEXP = 0.0F;
				this.setChanged(); }
		}
	}
}
