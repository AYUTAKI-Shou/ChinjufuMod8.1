package com.ayutaki.chinjufumod.tileentity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.objects.Reference2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FuelValues;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractStoveTileEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {

	protected static final int SLOT_INPUT = 0;
	protected static final int SLOT_FUEL = 1;
	protected static final int SLOT_RESULT = 2;
	public static final int DATA_LIT_TIME = 0;
	private static final int[] SLOTS_FOR_UP = new int[]{0};
	private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
	private static final int[] SLOTS_FOR_SIDES = new int[]{1};
	public static final int DATA_LIT_DURATION = 1;
	public static final int DATA_COOKING_PROGRESS = 2;
	public static final int DATA_COOKING_TOTAL_TIME = 3;
	public static final int NUM_DATA_VALUES = 4;
	public static final int BURN_TIME_STANDARD = 200;
	public static final int BURN_COOL_SPEED = 2;
	private final RecipeType<? extends AbstractCookingRecipe> recipeType;
	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
	int litTimeRemaining;
	int litTotalTime;
	int cookingTimer;
	int cookingTotalTime;
	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int index) {
			switch (index) {
				case 0:
					if (litTotalTime > Short.MAX_VALUE) {
						// Neo: preserve litTime / litDuration ratio on the client as data slots are synced as shorts.
						return net.minecraft.util.Mth.floor(((double) litTimeRemaining / litTotalTime) * Short.MAX_VALUE);
					}
					return AbstractStoveTileEntity.this.litTimeRemaining;
				case 1:
					return Math.min(AbstractStoveTileEntity.this.litTotalTime, Short.MAX_VALUE);
				case 2:
					return AbstractStoveTileEntity.this.cookingTimer;
				case 3:
					return AbstractStoveTileEntity.this.cookingTotalTime;
				default:
					return 0;
			}
		}

		@Override
		public void set(int index, int value) {
			switch (index) {
				case 0:
					AbstractStoveTileEntity.this.litTimeRemaining = value;
					break;
				case 1:
					AbstractStoveTileEntity.this.litTotalTime = value;
					break;
				case 2:
					AbstractStoveTileEntity.this.cookingTimer = value;
					break;
				case 3:
					AbstractStoveTileEntity.this.cookingTotalTime = value;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};
	
	private final Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed = new Reference2IntOpenHashMap<>();
	private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractCookingRecipe> quickCheck;

	@SuppressWarnings("unchecked")
	protected AbstractStoveTileEntity(BlockEntityType<?> tileType, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipe) {
		super(tileType, pos, state);
		this.quickCheck = RecipeManager.createCheck((RecipeType<AbstractCookingRecipe>)recipe);
		this.recipeType = recipe;
	}

	private boolean isLit() {
		return this.litTimeRemaining > 0;
	}

	@Override
	protected void loadAdditional(CompoundTag compound, HolderLookup.Provider loolup) {
		super.loadAdditional(compound, loolup);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.items, loolup);
		this.cookingTimer = compound.getInt("cooking_time_spent");
		this.cookingTotalTime = compound.getInt("cooking_total_time");
		this.litTimeRemaining = compound.getInt("lit_time_remaining");
		this.litTotalTime = compound.getInt("lit_total_time");
		CompoundTag compoundtag = compound.getCompound("RecipesUsed");

		for (String s : compoundtag.getAllKeys()) {
			this.recipesUsed.put(ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(s)), compoundtag.getInt(s));
		}
	}

	@Override
	protected void saveAdditional(CompoundTag compound, HolderLookup.Provider loolup) {
		super.saveAdditional(compound, loolup);
		compound.putInt("cooking_time_spent", this.cookingTimer);
		compound.putInt("cooking_total_time", this.cookingTotalTime);
		compound.putInt("lit_time_remaining", this.litTimeRemaining);
		compound.putInt("lit_total_time", this.litTotalTime);
		ContainerHelper.saveAllItems(compound, this.items, loolup);
		CompoundTag compoundtag = new CompoundTag();
		this.recipesUsed.forEach((p_380898_, p_380899_) -> compoundtag.putInt(p_380898_.location().toString(), p_380899_));
		compound.put("RecipesUsed", compoundtag);
	}

	@SuppressWarnings("deprecation")
	public static void serverTick(ServerLevel worldIn, BlockPos pos, BlockState state, AbstractStoveTileEntity furnace) {
		boolean flag = furnace.isLit();
		boolean flag1 = false;
		if (furnace.isLit()) {
			furnace.litTimeRemaining--;
		}

		ItemStack itemStack = furnace.items.get(1);
		ItemStack itemStack1 = furnace.items.get(0);
		boolean flag2 = !itemStack1.isEmpty();
		boolean flag3 = !itemStack.isEmpty();
		if (furnace.isLit() || flag3 && flag2) {
			SingleRecipeInput singlerecipeinput = new SingleRecipeInput(itemStack1);
			RecipeHolder<? extends AbstractCookingRecipe> recipeholder;
			if (flag2) {
				recipeholder = furnace.quickCheck.getRecipeFor(singlerecipeinput, worldIn).orElse(null); } 
			else {
				recipeholder = null; }

			int i = furnace.getMaxStackSize();
			if (!furnace.isLit() && canBurn(worldIn.registryAccess(), recipeholder, singlerecipeinput, furnace.items, i)) {
				furnace.litTimeRemaining = furnace.getBurnDuration(worldIn.fuelValues(), itemStack);
				furnace.litTotalTime = furnace.litTimeRemaining;
				if (furnace.isLit()) {
					flag1 = true;
					var remainder = itemStack.getCraftingRemainder();
					if (!remainder.isEmpty())
						furnace.items.set(1, remainder);
					else
					if (flag3) {
						Item item = itemStack.getItem();
						itemStack.shrink(1);
						if (itemStack.isEmpty()) {
							furnace.items.set(1, item.getCraftingRemainder()); // Neo: Remainder is handled in the `if` check above.
						}
					}
				}
			}

			if (furnace.isLit() && canBurn(worldIn.registryAccess(), recipeholder, singlerecipeinput, furnace.items, i)) {
				furnace.cookingTimer++;
				if (furnace.cookingTimer == furnace.cookingTotalTime) {
					furnace.cookingTimer = 0;
					furnace.cookingTotalTime = getTotalCookTime(worldIn, furnace);
					if (burn(worldIn.registryAccess(), recipeholder, singlerecipeinput, furnace.items, i)) {
						furnace.setRecipeUsed(recipeholder); }

					flag1 = true; }
			} 
			else {
				furnace.cookingTimer = 0; }
		} 
		else if (!furnace.isLit() && furnace.cookingTimer > 0) {
			furnace.cookingTimer = Mth.clamp(furnace.cookingTimer - 2, 0, furnace.cookingTotalTime);
		}

		if (flag != furnace.isLit()) {
			flag1 = true;
			state = state.setValue(AbstractFurnaceBlock.LIT, Boolean.valueOf(furnace.isLit()));
			worldIn.setBlock(pos, state, 3); }

		if (flag1) {
			setChanged(worldIn, pos, state); }
	}

	private static boolean canBurn(RegistryAccess worldIn, @Nullable RecipeHolder<? extends AbstractCookingRecipe> recipe, SingleRecipeInput input, NonNullList<ItemStack> list, int size) {
		if (!list.get(0).isEmpty() && recipe != null) {
			ItemStack itemStack = recipe.value().assemble(input, worldIn);
			if (itemStack.isEmpty()) {
				return false; } 
			
			else {
				ItemStack itemStack1 = list.get(2);
				if (itemStack1.isEmpty()) {
					return true; }
				
				else if (!ItemStack.isSameItemSameComponents(itemStack1, itemStack)) {
					return false; }
				
				else {
					return itemStack1.getCount() + itemStack.getCount() <= size && itemStack1.getCount() + itemStack.getCount() <= itemStack1.getMaxStackSize() // Neo fix: make furnace respect stack sizes in furnace recipes
						? true
						: itemStack1.getCount() + itemStack.getCount() <= itemStack.getMaxStackSize(); // Neo fix: make furnace respect stack sizes in furnace recipes
				}
			}
		}
		else { return false; }
	}

	private static boolean burn(RegistryAccess worldIn, @Nullable RecipeHolder<? extends AbstractCookingRecipe> recipe, SingleRecipeInput input, NonNullList<ItemStack> list, int size) {
		if (recipe != null && canBurn(worldIn, recipe, input, list, size)) {
			ItemStack itemStack = list.get(0);
			ItemStack itemStack1 = recipe.value().assemble(input, worldIn);
			ItemStack itemStack2 = list.get(2);
			if (itemStack2.isEmpty()) {
				list.set(2, itemStack1.copy()); }
			
			else if (ItemStack.isSameItemSameComponents(itemStack2, itemStack1)) {
				itemStack2.grow(itemStack1.getCount()); }

			if (itemStack.is(Blocks.WET_SPONGE.asItem()) && !list.get(1).isEmpty() && list.get(1).is(Items.BUCKET)) {
				list.set(1, new ItemStack(Items.WATER_BUCKET)); }

			itemStack.shrink(1);
			return true;
		}
		else { return false; }
	}

	protected int getBurnDuration(FuelValues time, ItemStack stack) {
		return stack.getBurnTime(this.recipeType, time) /2 * 3; //neo
	}

	private static int getTotalCookTime(ServerLevel worldIn, AbstractStoveTileEntity furnace) {
		SingleRecipeInput singlerecipeinput = new SingleRecipeInput(furnace.getItem(0));
		return furnace.quickCheck.getRecipeFor(singlerecipeinput, worldIn).map(recipe -> recipe.value().cookingTime()).orElse(200);
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return side == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
		return this.canPlaceItem(index, itemStack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return direction == Direction.DOWN && index == 1 ? stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET) : true;
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
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
	public void setItem(int index, ItemStack stack) {
		ItemStack itemStack = this.items.get(index);
		boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameComponents(itemStack, stack);
		this.items.set(index, stack);
		stack.limitSize(this.getMaxStackSize(stack));
		if (index == 0 && !flag && this.level instanceof ServerLevel server) {
			this.cookingTotalTime = getTotalCookTime(server, this);
			this.cookingTimer = 0;
			this.setChanged();
		}
	}

	@Override
	public boolean canPlaceItem(int count, ItemStack stack) {
		if (count == 2) {
			return false;
		} else if (count != 1) {
			return true;
		} else {
			ItemStack itemStack = this.items.get(1);
			return stack.getBurnTime(this.recipeType, this.level.fuelValues()) > 0 || stack.is(Items.BUCKET) && !itemStack.is(Items.BUCKET);
		}
	}

	@Override
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
		if (recipe != null) {
			ResourceKey<Recipe<?>> resourcekey = recipe.id();
			this.recipesUsed.addTo(resourcekey, 1);
		}
	}

	@Nullable
	@Override
	public RecipeHolder<?> getRecipeUsed() {
		return null;
	}

	@Override
	public void awardUsedRecipes(Player playerIn, List<ItemStack> list) { }

	public void awardUsedRecipesAndPopExperience(ServerPlayer playerIn) {
		List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(playerIn.serverLevel(), playerIn.position());
		playerIn.awardRecipes(list);

		for (RecipeHolder<?> recipeholder : list) {
			if (recipeholder != null) {
				playerIn.triggerRecipeCrafted(recipeholder, this.items);
			}
		}

		this.recipesUsed.clear();
	}

	public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel worldIn, Vec3 popVec) {
		List<RecipeHolder<?>> list = Lists.newArrayList();

		for (Entry<ResourceKey<Recipe<?>>> entry : this.recipesUsed.reference2IntEntrySet()) {
			worldIn.recipeAccess().byKey(entry.getKey()).ifPresent(iRecipe -> {
				list.add((RecipeHolder<?>)iRecipe);
				createExperience(worldIn, popVec, entry.getIntValue(), ((AbstractCookingRecipe)iRecipe.value()).experience());
			});
		}
		return list;
	}

	private static void createExperience(ServerLevel worldIn, Vec3 popVec, int count, float exp) {
		int i = Mth.floor((float)count * exp);
		float f = Mth.frac((float)count * exp);
		if (f != 0.0F && Math.random() < (double)f) {
			i++;
		}
		ExperienceOrb.award(worldIn, popVec, i);
	}

	@Override
	public void fillStackedContents(StackedItemContents helper) {
		for (ItemStack itemStack : this.items) {
			helper.accountStack(itemStack);
		}
	}
}
