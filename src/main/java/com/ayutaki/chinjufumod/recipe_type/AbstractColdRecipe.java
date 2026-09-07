package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.tileentity.AbstractReizouTileEntity;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class AbstractColdRecipe implements Recipe<Container> {
	protected final RecipeType<?> type;
	protected final CookingBookCategory category;
	protected final String group;
	protected final Ingredient ingredient;
	protected final ItemStack result;
	protected final float experience;
	protected final int cookingTime;
	
	public AbstractColdRecipe(RecipeType<?> recipeType, String groupName, CookingBookCategory name, Ingredient inputList, ItemStack output, float exp, int time) {
		this.type = recipeType;
		this.category = name;
		this.group = groupName;
		this.ingredient = inputList;
		this.result = output;
		this.experience = exp;
		this.cookingTime = time;
	}

	public RecipeType<?> getType() {
		return this.type;
	}

	public CookingBookCategory category() {
		return CookingBookCategory.FOOD;
	}
	
	public String getGroup() {
		return this.group;
	}

	/** Check invSlot. **/
	public boolean matches(Container inv, Level worldIn) {
		return this.ingredient.test(inv.getItem(AbstractReizouTileEntity.invSlot));
	}

	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.ingredient);
		return nonnulllist;
	}
	
	public ItemStack assemble(Container inv, HolderLookup.Provider lookUp) {
		return this.result.copy();
	}

	public ItemStack getResultItem(HolderLookup.Provider lookUp) {
		return this.result;
	}

	public float getExperience() {
		return this.experience;
	}

	public int getCookingTime() {
		return this.cookingTime;
	}

	public boolean canCraftInDimensions(int i, int count) {
		return true;
	}
	
	// [net.minecraft.client.ClientRecipeBook/]: Unknown recipe category:
	@Override
	public boolean isSpecial() {
		return true;
	}

	public interface Factory<T extends AbstractColdRecipe> {
		T create(String groupName, CookingBookCategory name, Ingredient inputList, ItemStack output, float exp, int time);
	}
}