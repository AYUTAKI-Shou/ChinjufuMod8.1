package com.ayutaki.chinjufumod.tileentity;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.furnace.AbstractStoveBlock;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
	int litTime;
	int litDuration;
	int cookingProgress;
	int cookingTotalTime;
	@Nullable
	private static volatile Map<Item, Integer> fuelCache;
	protected final ContainerData dataAccess = new ContainerData() {
		@Override
		public int get(int index) {
			switch (index) {
				case 0:
					return AbstractStoveTileEntity.this.litTime;
				case 1:
					return AbstractStoveTileEntity.this.litDuration;
				case 2:
					return AbstractStoveTileEntity.this.cookingProgress;
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
					AbstractStoveTileEntity.this.litTime = value;
					break;
				case 1:
					AbstractStoveTileEntity.this.litDuration = value;
					break;
				case 2:
					AbstractStoveTileEntity.this.cookingProgress = value;
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
	
	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	private final RecipeManager.CachedCheck<Container, ? extends AbstractCookingRecipe> quickCheck;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected AbstractStoveTileEntity(BlockEntityType<?> tileType, BlockPos pos, BlockState state, RecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(tileType, pos, state);
		this.quickCheck = RecipeManager.createCheck((RecipeType)recipeTypeIn);
		this.recipeType = recipeTypeIn;
	}

	public static void invalidateCache() {
		fuelCache = null;
	}

	/**@deprecated Forge: get burn times by calling ForgeHooks#getBurnTime(ItemStack)*/
	@Deprecated
	public static Map<Item, Integer> getFuel() {
		Map<Item, Integer> map = fuelCache;
		
		if (map != null) { return map; }
		else {
			Map<Item, Integer> map1 = Maps.newLinkedHashMap();
			add(map1, Items.LAVA_BUCKET, 20000);
			add(map1, Blocks.COAL_BLOCK, 16000);
			add(map1, Items.BLAZE_ROD, 2400);
			add(map1, Items.COAL, 1600);
			add(map1, Items.CHARCOAL, 1600);
			add(map1, ItemTags.LOGS, 300);
			add(map1, ItemTags.BAMBOO_BLOCKS, 300);
			add(map1, ItemTags.PLANKS, 300);
			add(map1, Blocks.BAMBOO_MOSAIC, 300);
			add(map1, ItemTags.WOODEN_STAIRS, 300);
			add(map1, Blocks.BAMBOO_MOSAIC_STAIRS, 300);
			add(map1, ItemTags.WOODEN_SLABS, 150);
			add(map1, Blocks.BAMBOO_MOSAIC_SLAB, 150);
			add(map1, ItemTags.WOODEN_TRAPDOORS, 300);
			add(map1, ItemTags.WOODEN_PRESSURE_PLATES, 300);
			add(map1, ItemTags.WOODEN_FENCES, 300);
			add(map1, ItemTags.FENCE_GATES, 300);
			add(map1, Blocks.NOTE_BLOCK, 300);
			add(map1, Blocks.BOOKSHELF, 300);
			add(map1, Blocks.CHISELED_BOOKSHELF, 300);
			add(map1, Blocks.LECTERN, 300);
			add(map1, Blocks.JUKEBOX, 300);
			add(map1, Blocks.CHEST, 300);
			add(map1, Blocks.TRAPPED_CHEST, 300);
			add(map1, Blocks.CRAFTING_TABLE, 300);
			add(map1, Blocks.DAYLIGHT_DETECTOR, 300);
			add(map1, ItemTags.BANNERS, 300);
			add(map1, Items.BOW, 300);
			add(map1, Items.FISHING_ROD, 300);
			add(map1, Blocks.LADDER, 300);
			add(map1, ItemTags.SIGNS, 200);
			add(map1, ItemTags.HANGING_SIGNS, 800);
			add(map1, Items.WOODEN_SHOVEL, 200);
			add(map1, Items.WOODEN_SWORD, 200);
			add(map1, Items.WOODEN_HOE, 200);
			add(map1, Items.WOODEN_AXE, 200);
			add(map1, Items.WOODEN_PICKAXE, 200);
			add(map1, ItemTags.WOODEN_DOORS, 200);
			add(map1, ItemTags.BOATS, 1200);
			add(map1, ItemTags.WOOL, 100);
			add(map1, ItemTags.WOODEN_BUTTONS, 100);
			add(map1, Items.STICK, 100);
			add(map1, ItemTags.SAPLINGS, 100);
			add(map1, Items.BOWL, 100);
			add(map1, ItemTags.WOOL_CARPETS, 67);
			add(map1, Blocks.DRIED_KELP_BLOCK, 4001);
			add(map1, Items.CROSSBOW, 300);
			add(map1, Blocks.BAMBOO, 50);
			add(map1, Blocks.DEAD_BUSH, 100);
			add(map1, Blocks.SCAFFOLDING, 50);
			add(map1, Blocks.LOOM, 300);
			add(map1, Blocks.BARREL, 300);
			add(map1, Blocks.CARTOGRAPHY_TABLE, 300);
			add(map1, Blocks.FLETCHING_TABLE, 300);
			add(map1, Blocks.SMITHING_TABLE, 300);
			add(map1, Blocks.COMPOSTER, 300);
			add(map1, Blocks.AZALEA, 100);
			add(map1, Blocks.FLOWERING_AZALEA, 100);
			add(map1, Blocks.MANGROVE_ROOTS, 300);
			fuelCache = map1;
			return map1;
		}
	}

	@SuppressWarnings("deprecation")
	private static boolean isNeverAFurnaceFuel(Item item) {
		return item.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
	}

	private static void add(Map<Item, Integer> map, TagKey<Item> itemProvider, int burnTime) {
		for (Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(itemProvider)) {
			if (!isNeverAFurnaceFuel(holder.value())) {
				map.put(holder.value(), burnTime);
			}
		}
	}

	private static void add(Map<Item, Integer> map, ItemLike itemProvider, int burnTime) {
		Item item = itemProvider.asItem();
		if (isNeverAFurnaceFuel(item)) {
			if (SharedConstants.IS_RUNNING_IN_IDE) {
				throw (IllegalStateException)Util.pauseInIde(
					new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName(null).getString() + " a furnace fuel. That will not work!")); }
		} 
		else { map.put(item, burnTime); }
	}

	private boolean isLit() {
		return this.litTime > 0;
	}

	@SuppressWarnings("removal")
	@Override
	protected void loadAdditional(CompoundTag compound, HolderLookup.Provider loolup) {
		super.loadAdditional(compound, loolup);
		this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ContainerHelper.loadAllItems(compound, this.items, loolup);
		this.litTime = compound.getInt("BurnTime");
		this.cookingProgress = compound.getInt("CookTime");
		this.cookingTotalTime = compound.getInt("CookTimeTotal");
		this.litDuration = this.getBurnDuration(this.items.get(1));
		CompoundTag compoundtag = compound.getCompound("RecipesUsed");

		for (String s : compoundtag.getAllKeys()) {
			this.recipesUsed.put(new ResourceLocation(s), compoundtag.getInt(s));
		}
	}

	@Override
	protected void saveAdditional(CompoundTag compound, HolderLookup.Provider loolup) {
		super.saveAdditional(compound, loolup);
		compound.putInt("BurnTime", this.litTime);
		compound.putInt("CookTime", this.cookingProgress);
		compound.putInt("CookTimeTotal", this.cookingTotalTime);
		ContainerHelper.saveAllItems(compound, this.items, loolup);
		CompoundTag compoundtag = new CompoundTag();
		this.recipesUsed.forEach((p_187449_, p_187450_) -> compoundtag.putInt(p_187449_.toString(), p_187450_));
		compound.put("RecipesUsed", compoundtag);
	}

	public static void serverTick(Level worldIn, BlockPos pos, BlockState state, AbstractStoveTileEntity tileEntity) {
		boolean flag = tileEntity.isLit();
		boolean flag1 = false;
		if (tileEntity.isLit()) {
			tileEntity.litTime--;
		}

		ItemStack itemstack = tileEntity.items.get(1);
		boolean flag2 = !tileEntity.items.get(0).isEmpty();
		boolean flag3 = !itemstack.isEmpty();
		if (tileEntity.isLit() || flag3 && flag2) {
			RecipeHolder<?> recipeholder;
			if (flag2) {
				recipeholder = tileEntity.quickCheck.getRecipeFor(tileEntity, worldIn).orElse(null);
			} else {
				recipeholder = null;
			}

			int i = tileEntity.getMaxStackSize();
			if (!tileEntity.isLit() && tileEntity.canBurn(worldIn.registryAccess(), recipeholder, tileEntity.items, i)) {
				tileEntity.litTime = tileEntity.getBurnDuration(itemstack);
				tileEntity.litDuration = tileEntity.litTime;
				if (tileEntity.isLit()) {
					flag1 = true;
					if (itemstack.hasCraftingRemainingItem()) {
						tileEntity.items.set(1, itemstack.getCraftingRemainingItem());
					} else
					if (flag3) {
						//Item item = itemstack.getItem();
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							tileEntity.items.set(1, itemstack.getCraftingRemainingItem());
						}
					}
				}
			}

			if (tileEntity.isLit() && tileEntity.canBurn(worldIn.registryAccess(), recipeholder, tileEntity.items, i)) {
				tileEntity.cookingProgress++;
				if (tileEntity.cookingProgress == tileEntity.cookingTotalTime) {
					tileEntity.cookingProgress = 0;
					tileEntity.cookingTotalTime = getTotalCookTime(worldIn, tileEntity);
					if (tileEntity.burn(worldIn.registryAccess(), recipeholder, tileEntity.items, i)) {
						tileEntity.setRecipeUsed(recipeholder);
					}

					flag1 = true;
				}
			} else {
				tileEntity.cookingProgress = 0;
			}
		} else if (!tileEntity.isLit() && tileEntity.cookingProgress > 0) {
			tileEntity.cookingProgress = Mth.clamp(tileEntity.cookingProgress - 2, 0, tileEntity.cookingTotalTime);
		}

		if (flag != tileEntity.isLit()) {
			flag1 = true;
			state = state.setValue(AbstractStoveBlock.LIT, Boolean.valueOf(tileEntity.isLit()));
			worldIn.setBlock(pos, state, 3);
		}

		if (flag1) {
			setChanged(worldIn, pos, state);
		}
	}

	@SuppressWarnings("unchecked") // Recipe -> RecipeHolder for 20.2
	private boolean canBurn(RegistryAccess access, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> list, int size) {
		if (!list.get(0).isEmpty() && recipe != null) {
			ItemStack itemstack = ((RecipeHolder<net.minecraft.world.item.crafting.Recipe<WorldlyContainer>>)recipe).value().assemble(this, access);
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = list.get(2);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!ItemStack.isSameItemSameComponents(itemstack1, itemstack)) {
					return false;
				} else {
					return (itemstack1.getCount() + itemstack.getCount() <= size && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) // Forge fix: make furnace respect stack sizes in furnace recipes
						? true
						: itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
				}
			}
		} else {
			return false;
		}
	}

	@SuppressWarnings({ "unchecked" }) // Recipe -> RecipeHolder for 20.2
	private boolean burn(RegistryAccess access, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> list, int size) {
		if (recipe != null && canBurn(access, recipe, list, size)) {
			ItemStack itemstack = list.get(0);
			ItemStack itemstack1 = ((RecipeHolder<net.minecraft.world.item.crafting.Recipe<WorldlyContainer>>)recipe).value().assemble(this, access);
			ItemStack itemstack2 = list.get(2);
			if (itemstack2.isEmpty()) {
				list.set(2, itemstack1.copy());
			} else if (ItemStack.isSameItemSameComponents(itemstack2, itemstack1)) {
				itemstack2.grow(itemstack1.getCount());
			}

			if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !list.get(1).isEmpty() && list.get(1).is(Items.BUCKET)) {
				list.set(1, new ItemStack(Items.WATER_BUCKET));
			}

			itemstack.shrink(1);
			return true;
		} else {
			return false;
		}
	}

	/* Fuel burning time. Multiply this time by 1.5. */
	protected int getBurnDuration(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {
			//Item item = stack.getItem();
			return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, this.recipeType) /2 * 3;
		}
	}

	private static int getTotalCookTime(Level worldIn, AbstractStoveTileEntity tileEntity) {
		return tileEntity.quickCheck.getRecipeFor(tileEntity, worldIn).map(recipe -> recipe.value().getCookingTime()).orElse(200);
	}

	public static boolean isFuel(ItemStack stack) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, null) > 0;
	}

	@Override
	public int[] getSlotsForFace(Direction facing) {
		if (facing == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return facing == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int count, ItemStack stack, @Nullable Direction facing) {
		return this.canPlaceItem(count, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int count, ItemStack stack, Direction facing) {
		return facing == Direction.DOWN && count == 1 ? stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET) : true;
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
	public void setItem(int count, ItemStack stack) {
		ItemStack itemstack = this.items.get(count);
		boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameComponents(itemstack, stack);
		this.items.set(count, stack);
		stack.limitSize(this.getMaxStackSize(stack));
		if (count == 0 && !flag) {
			this.cookingTotalTime = getTotalCookTime(this.level, this);
			this.cookingProgress = 0;
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
			ItemStack itemstack = this.items.get(1);
			return net.minecraftforge.common.ForgeHooks.getBurnTime(stack, this.recipeType) > 0 || stack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
		}
	}

	@Override
	public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
		if (recipe != null) {
			ResourceLocation resourcelocation = recipe.id();
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

	public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel worldIn, Vec3 vc3) {
		List<RecipeHolder<?>> list = Lists.newArrayList();

		for (Entry<ResourceLocation> entry : this.recipesUsed.object2IntEntrySet()) {
			worldIn.getRecipeManager().byKey(entry.getKey()).ifPresent(p_296949_ -> {
				list.add((RecipeHolder<?>)p_296949_);
				createExperience(worldIn, vc3, entry.getIntValue(), ((AbstractCookingRecipe)p_296949_.value()).getExperience());
			});
		}

		return list;
	}

	private static void createExperience(ServerLevel worldIn, Vec3 vc3, int count, float count_f) {
		int i = Mth.floor((float)count * count_f);
		float f = Mth.frac((float)count * count_f);
		if (f != 0.0F && Math.random() < (double)f) {
			i++;
		}

		ExperienceOrb.award(worldIn, vc3, i);
	}

	@Override
	public void fillStackedContents(StackedContents helper) {
		for (ItemStack itemstack : this.items) {
			helper.accountStack(itemstack);
		}
	}

	net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
		net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
		if (capability == net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER && facing != null && !this.remove) {
			return switch (facing) {
				case UP -> handlers[0].cast();
				case DOWN -> handlers[1].cast();
				default -> handlers[2].cast();
			};
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		for (int x = 0; x < handlers.length; x++) {
			handlers[x].invalidate();
		}
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		this.handlers = net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
	}
}
