package com.ayutaki.chinjufumod.tileentity;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractReizouTileEntity extends RandomizableContainerBlockEntity implements LidBlockEntity, WorldlyContainer, RecipeHolder, StackedContentsCompatible {
	
	@SuppressWarnings("unused")
	private static final int EVENT_SET_OPEN_COUNT = 1;
	protected final ChestLidController chestLidController = new ChestLidController();

	public static int invSlot;
	protected int storageTime;
	protected int recipeCookingTime;
	public float totalEXP = 0.0F;
	protected final ContainerData dataAccess = new ContainerData() {
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
	
	protected AbstractReizouTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
	}

	/*public AbstractReizouTileEntity(BlockPos pos, BlockState state) {
		this(BlockEntity_CM.REIZOU_TOP.get(), pos, state);
	}*/

	public abstract int getContainerSize();

	protected abstract Component getDefaultName();

	public void load(CompoundTag compound) {
		super.load(compound);
		this.storageTime = compound.getInt("StorageTime");
		this.recipeCookingTime = compound.getInt("RecipeCookingTime");
		AbstractReizouTileEntity.invSlot = compound.getInt("InventorySlot");
		this.totalEXP = compound.getFloat("TotalEXP");
		
		CompoundTag nbt = compound.getCompound("RecipesUsed");
		for(String s : nbt.getAllKeys()) {
			this.recipesUsed.put(new ResourceLocation(s), nbt.getInt(s)); }
	}

	protected void saveAdditional(CompoundTag compound) {
		super.saveAdditional(compound);
		compound.putInt("StorageTime", this.storageTime);
		compound.putInt("RecipeCookingTime", this.recipeCookingTime);
		compound.putInt("InventorySlot", AbstractReizouTileEntity.invSlot);
		compound.putFloat("TotalEXP", this.totalEXP);
		
		CompoundTag nbt = new CompoundTag();
		this.recipesUsed.forEach((name, i) -> { nbt.putInt(name.toString(), i); });
		compound.put("RecipesUsed", nbt);
	}

	public static void lidAnimateTick(Level worldIn, BlockPos pos, BlockState state, AbstractReizouTileEntity tileEntity) {
		tileEntity.chestLidController.tickLid();
	}

	static void playSound(Level worldIn, BlockPos pos, BlockState state, SoundEvent sound) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.5D;
		double d2 = (double)pos.getZ() + 0.5D;

		worldIn.playSound((Player)null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, worldIn.random.nextFloat() * 0.1F + 0.9F);
	}

	public boolean triggerEvent(int id, int type) {
		if (id == 1) {
			this.chestLidController.shouldBeOpen(type > 0);
			return true;
		} else {
			return super.triggerEvent(id, type);
		}
	}

	public abstract void startOpen(Player playerIn);

	public abstract void stopOpen(Player playerIn);

	protected abstract NonNullList<ItemStack> getItems();

	protected abstract void setItems(NonNullList<ItemStack> stack);

	public float getOpenNess(float count) {
		return this.chestLidController.getOpenness(count);
	}

	//public static int getOpenCount(BlockGetter worldIn, BlockPos pos) {

	//public static void swapContents(AbstractReizouTileEntity tileEntity, AbstractReizouTileEntity otherTileEntity) {

	protected abstract AbstractContainerMenu createMenu(int id, Inventory inventory);

	protected net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
	
	@SuppressWarnings("deprecation")
	@Override
	public void setBlockState(BlockState state) {
		super.setBlockState(state);
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

	protected abstract net.minecraftforge.items.IItemHandlerModifiable createHandler();

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		if (chestHandler != null) {
			chestHandler.invalidate();
			chestHandler = null;
		}
	}

	public abstract void recheckOpen();

	protected void signalOpenCount(Level worldIn, BlockPos pos, BlockState state, int count, int openCount) {
		Block block = state.getBlock();
		worldIn.blockEvent(pos, block, 1, openCount);
	}
	
	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected abstract int getTotalCookTime(Level worldIn, AbstractReizouTileEntity tileEntity);
	
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
	
	protected void cookRecipe(int i, ItemStack output, Recipe<?> iRecipe) {
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
	
	//private void slotCook(int i, Level worldIn, ReizouTop_TileEntity tileEntity) {

	/// RecipeHolder //////////
	@Override
	public void setRecipeUsed(Recipe<?> iRecipe) {
		if (iRecipe != null) {
			ResourceLocation resource = iRecipe.getId();
			this.recipesUsed.addTo(resource, 1);}
	}

	@Nullable
	@Override
	public Recipe<?> getRecipeUsed() {
		return null;
	}
	
	public void awardUsedRecipes(Player playerIn) { }
	
	/// StackedContentsCompatible //////////
	@Override
	public void fillStackedContents(StackedContents iRecipe) {
		int[] slot_int = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		for (int i : slot_int) {
			iRecipe.accountStack(this.slotStack(i)); }
	}

	/// WorldlyContainer //////////
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

	public boolean stillValid(Player playerIn) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false; }
		else {
			return playerIn.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	public boolean isEmpty() {
		for(ItemStack stack : this.getItems()) {
			if (!stack.isEmpty()) {
				return false; }
		}
		return true;
	}

	public ItemStack getItem(int i) {
		return this.getItems().get(i);
	}

	public ItemStack removeItem(int i, int count) {
		return ContainerHelper.removeItem(this.getItems(), i, count);
	}

	public ItemStack removeItemNoUpdate(int i) {
		return ContainerHelper.takeItem(this.getItems(), i);
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
	public void expFromGUI(Player playerIn) {
		Level worldIn = playerIn.level;
		if (worldIn == null || worldIn.isClientSide) return;
		
		if (playerIn != null) {
			float total = this.totalEXP;
			
			if (total > 0.0F) {
				int i = Mth.floor(total);
				float point = total - i;
				
				if (i >= 1) {
					Vec3 v3d = playerIn.position();
					worldIn.addFreshEntity(new ExperienceOrb(worldIn, v3d.x, v3d.y, v3d.z, i));
					this.totalEXP = point;
					this.setChanged(); }
			}
		}
	}
	
	public void dropExp(Level worldIn, BlockPos pos) {
		if (worldIn == null || worldIn.isClientSide) return;
		
		float total = this.totalEXP;
		if (total > 0.0F) {
			
			int i = Mth.floor(total);
			float point = total - i;
			if (point > 0.0F && Math.random() < point)  { i++; }
			
			if (i >= 1) {
				worldIn.addFreshEntity(new ExperienceOrb(worldIn, pos.getX(), pos.getY(), pos.getZ(), i));
				this.totalEXP = 0.0F;
				this.setChanged(); }
		}
	}
}
