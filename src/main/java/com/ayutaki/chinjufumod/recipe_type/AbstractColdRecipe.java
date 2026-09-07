package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.tileentity.AbstractReizouTileEntity;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public abstract class AbstractColdRecipe implements Recipe<Container> {
	protected final RecipeType<?> type;
	protected final ResourceLocation id;
	protected final String group;
	protected final Ingredient ingredient;
	protected final ItemStack result;
	protected final float experience;
	protected final int cookingTime;
	
	public AbstractColdRecipe(RecipeType<?> recipeType, ResourceLocation name, String groupName, 
			Ingredient inputList, ItemStack output, float exp, int time) {
		this.type = recipeType;
		this.id = name;
		this.group = groupName;
		this.ingredient = inputList;
		this.result = output;
		this.experience = exp;
		this.cookingTime = time;
	}

	public RecipeType<?> getType() {
		return this.type;
	}

	public ResourceLocation getId() {
		return this.id;
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
	
	public ItemStack assemble(Container inv) {
		return this.result.copy();
	}

	public ItemStack getResultItem() {
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
}
