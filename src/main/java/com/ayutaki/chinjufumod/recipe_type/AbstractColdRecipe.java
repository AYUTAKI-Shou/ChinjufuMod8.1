package com.ayutaki.chinjufumod.recipe_type;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.tileentity.AbstractReizouTileEntity;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class AbstractColdRecipe implements Recipe<ColdRecipeInput> {
	protected final String group;
	private final CookingBookCategory category;
	protected final Ingredient ingredient;
	protected final ItemStack result;
	protected final float experience;
	protected final int cookingTime;
	@Nullable
	private PlacementInfo placementInfo;
	
	public AbstractColdRecipe(String groupName, CookingBookCategory name, Ingredient inputList, ItemStack output, float exp, int time) {
		this.group = groupName;
		this.category = name;
		this.ingredient = inputList;
		this.result = output;
		this.experience = exp;
		this.cookingTime = time;
	}

	@Override
	public RecipeBookCategory recipeBookCategory() {
		return CM_RecipeBookCategory.COLD_RECIPE.get();
	}

	@Override
	public abstract RecipeType<? extends AbstractColdRecipe> getType();
	
	public CookingBookCategory category() {
		return this.category;
	}
	
	public String getGroup() {
		return this.group;
	}
	
	@Override
	public abstract RecipeSerializer<? extends AbstractColdRecipe> getSerializer();
	
	public boolean matches(ColdRecipeInput inv, Level worldIn) {
		return this.ingredient.test(inv.getItem(AbstractReizouTileEntity.invSlot));
	}

	public Ingredient input() {
		return this.ingredient;
	}
	
	@Override
	public PlacementInfo placementInfo() {
		if (this.placementInfo == null) {
			this.placementInfo = PlacementInfo.create(this.ingredient);
		}
		return this.placementInfo;
	}

	public ItemStack assemble(ColdRecipeInput inv, HolderLookup.Provider lookUp) {
		return this.result.copy();
	}

	protected ItemStack result() {
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

	protected abstract Item furnaceIcon();
	
	@Override
	public boolean isSpecial() {
		return true;
	}
	
	@FunctionalInterface
	public interface Factory<T extends AbstractColdRecipe> {
		T create(String groupName, CookingBookCategory name, Ingredient inputList, ItemStack output, float exp, int time);
	}
}