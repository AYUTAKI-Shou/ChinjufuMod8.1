package com.ayutaki.chinjufumod.tileentity;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.recipe_type.AbstractColdRecipe;
import com.ayutaki.chinjufumod.recipe_type.ColdRecipeInput;

import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeManager.CachedCheck;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractReizouTileEntity extends RandomizableContainerBlockEntity implements LidBlockEntity, WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
	
	@SuppressWarnings("unused")
	private static final int EVENT_SET_OPEN_COUNT = 1;
	private final ChestLidController chestLidController = new ChestLidController();
	
	public static int invSlot;
	protected int storageTime;
	protected int recipeCookingTime;
	public float totalEXP = 0.0F;
	protected final ContainerData dataAccess = new ContainerData() {
		@Override
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

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0:
					AbstractReizouTileEntity.this.storageTime = value;
					break;
				case 1:
					AbstractReizouTileEntity.this.recipeCookingTime = value;
					break;
				case 2:
					AbstractReizouTileEntity.invSlot = value;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
	};
	protected final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
	protected final RecipeType<? extends AbstractColdRecipe> recipeType;
	protected final CachedCheck<ColdRecipeInput, AbstractColdRecipe> quickCheck;
	
	@SuppressWarnings("unchecked")
	protected AbstractReizouTileEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state, RecipeType<? extends AbstractColdRecipe> iRecipe) {
		super(entityType, pos, state);
		this.quickCheck = RecipeManager.createCheck((RecipeType<AbstractColdRecipe>)iRecipe);
		this.recipeType = iRecipe;
	}

	/*public AbstractReizouTileEntity(BlockPos pos, BlockState state) {
		this(BlockEntity_CM.REIZOU.get(), pos, state);
	}*/

	public abstract int getContainerSize();

	protected abstract Component getDefaultName();

	@Override
	protected void loadAdditional(CompoundTag compound, HolderLookup.Provider lookup) {
		super.loadAdditional(compound, lookup);
		this.storageTime = compound.getInt("StorageTime");
		this.recipeCookingTime = compound.getInt("RecipeCookingTime");
		AbstractReizouTileEntity.invSlot = compound.getInt("InventorySlot");
		this.totalEXP = compound.getFloat("TotalEXP");

		CompoundTag nbt = compound.getCompound("RecipesUsed");
		for (String s : nbt.getAllKeys()) {
			this.recipesUsed.put(ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(s)), nbt.getInt(s));
		}
	}

	@Override
	protected void saveAdditional(CompoundTag compound, HolderLookup.Provider lookup) {
		super.saveAdditional(compound, lookup);
		compound.putInt("StorageTime", this.storageTime);
		compound.putInt("RecipeCookingTime", this.recipeCookingTime);
		compound.putInt("InventorySlot", AbstractReizouTileEntity.invSlot);
		compound.putFloat("TotalEXP", this.totalEXP);

		CompoundTag nbt = new CompoundTag();
		this.recipesUsed.forEach((name, i) -> { nbt.putInt(name.location().toString(), i); });
		compound.put("RecipesUsed", nbt);
	}

	public static void lidAnimateTick(Level worldIn, BlockPos pos, BlockState state, AbstractReizouTileEntity tileEntity) {
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

	public abstract void startOpen(Player playerIn);

	public abstract void stopOpen(Player playerIn);

	protected abstract NonNullList<ItemStack> getItems();

	@Override
	public float getOpenNess(float partialTicks) {
		return this.chestLidController.getOpenness(partialTicks);
	}

	//public static int getOpenCount(BlockGetter worldIn, BlockPos pos) {

	//public static void swapContents(AbstractReizouTileEntity chest, AbstractReizouTileEntity otherChest) {

	protected abstract AbstractContainerMenu createMenu(int id, Inventory inventory);

	//public void setBlockState(BlockState state) {

	public abstract void recheckOpen();

	protected void signalOpenCount(Level worldIn, BlockPos pos, BlockState state, int eventId, int eventParam) {
		Block block = state.getBlock();
		worldIn.blockEvent(pos, block, 1, eventParam);
	}
	
	// Cooking Methods ////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Cooking //////////
	protected int getTotalCookTime(int slot, ServerLevel worldIn, AbstractReizouTileEntity tileEntity) {
		ColdRecipeInput recipeInput = new ColdRecipeInput(tileEntity.slotStack(slot));
		return tileEntity.quickCheck.getRecipeFor(recipeInput, worldIn).map(iRecipe -> iRecipe.value().getCookingTime()).orElse(400);
	}
	
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
		ItemStack remain_i = stack_i.getCraftingRemainder();
		
		ItemStack stack_9 = this.slotStack(i + 9);
		ItemStack stack_1 = this.slotStack(i + 1);
		int count_1 = stack_1.getCount();
		
		if (stack_9.isEmpty()) { 
			if (stack_1.isEmpty()) { return true; }
			
			if (ItemStack.isSameItemSameComponents(stack_1, remain_i)) { 
				return count_1 < remain_i.getMaxStackSize(); }
		}
		
		else if (ItemStack.isSameItemSameComponents(stack_9, output)) { 
			if (stack_9.getCount() + output.getCount() < output.getMaxStackSize()) { 
				if (stack_1.isEmpty()) { return true; }
				
				if (ItemStack.isSameItemSameComponents(stack_1, remain_i)) { 
					return count_1 < remain_i.getMaxStackSize(); }
			}
		}
		return false;
	}
	
	protected void remainItem(int i) {
		ItemStack stack_i = this.slotStack(i);
		ItemStack remain_i = stack_i.getCraftingRemainder();
		
		ItemStack stack_1 = this.slotStack(i + 1);
		if (stack_1.isEmpty()) {
			this.resultStackSet(i, 1, remain_i); }
		
		else if (ItemStack.isSameItemSameComponents(stack_1, remain_i)) { 
			stack_1.grow(1); }
	}
	
	///not hasRemain//////////
	protected boolean canCook(int i, ItemStack output) {
		ItemStack stack_i = this.slotStack(i);
		if (!stack_i.isEmpty()) {

			ItemStack stack_9 = this.slotStack(i + 9);
			if (stack_9.isEmpty()) { return true; }
			
			else if (ItemStack.isSameItemSameComponents(stack_9, output)) {
				return stack_9.getCount() + output.getCount() < output.getMaxStackSize(); }
		}
		return false;
	}
	
	protected void cookRecipe(int i, ItemStack output, RecipeHolder<?> iRecipe) {
		ItemStack stack_9 = this.slotStack(i + 9);
		if (stack_9.isEmpty()) {
			this.resultStackSet(i, 9, output); }
		
		else if (ItemStack.isSameItemSameComponents(stack_9, output)) { 
			stack_9.grow(output.getCount()); }
		
		this.slotStack(i).shrink(1);
		this.recipeCookingTime = ((AbstractColdRecipe) iRecipe.value()).getCookingTime();
		this.totalEXP += ((AbstractColdRecipe) iRecipe.value()).getExperience();
		AbstractReizouTileEntity.invSlot = i;
		this.storageTime = 0;
		this.setRecipeUsed(iRecipe);
		this.setChanged();
	}
	
	//private void slotCook(int i, ServerLevel worldIn, Reizou_TileEntity tileEntity) {

	/// RecipeCraftingHolder //////////
	@Override
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
		if (recipe != null) {
			ResourceKey<Recipe<?>> resourcelocation = recipe.id();
			this.recipesUsed.addTo(resourcelocation, 1);
		}
	}

	@Nullable
	@Override
	public RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void awardUsedRecipes(Player playerIn, List<ItemStack> list) { }
	
	/// StackedContentsCompatible //////////
	@Override
	public void fillStackedContents(StackedItemContents iRecipe) {
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
	
	//public boolean stillValid(Player playerIn) { Not need?

	public boolean isEmpty() {
		for(ItemStack stack : this.getItems()) {
			if (!stack.isEmpty()) {
				return false; }
		}
		return true;
	}

	public ItemStack removeItem(int i, int count) {
		return ContainerHelper.removeItem(this.getItems(), i, count);
	}

	public ItemStack removeItemNoUpdate(int i) {
		return ContainerHelper.takeItem(this.getItems(), i);
	}
	
	public void setItem(int i, ItemStack stack) {
		ItemStack stack_i = this.getItems().get(i);
		boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameComponents(stack_i, stack);
		this.getItems().set(i, stack);
		if (stack.getCount() > this.getMaxStackSize()) {
			stack.setCount(this.getMaxStackSize());
		}
		
		if (!flag && this.level instanceof ServerLevel worldIn) {
			if (i == 0) { this.setAct(i, worldIn); }
			if (i == 1) { this.setAct(i, worldIn); }
			if (i == 2) { this.setAct(i, worldIn); }
			if (i == 3) { this.setAct(i, worldIn); }
			if (i == 4) { this.setAct(i, worldIn); }
			if (i == 5) { this.setAct(i, worldIn); }
			if (i == 6) { this.setAct(i, worldIn); }
			if (i == 7) { this.setAct(i, worldIn); }
		}
	}
	
	protected void setAct(int i, ServerLevel worldIn) {
		this.recipeCookingTime = this.getTotalCookTime(i, worldIn, this);
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
		Level worldIn = playerIn.level();
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
