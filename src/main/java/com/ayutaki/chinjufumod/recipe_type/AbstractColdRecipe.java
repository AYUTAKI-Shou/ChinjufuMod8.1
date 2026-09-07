package com.ayutaki.chinjufumod.recipe_type;

import com.ayutaki.chinjufumod.tileentity.AbstractReizouTileEntity;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class AbstractColdRecipe implements IRecipe<IInventory> {
	protected final IRecipeType<?> type;
	protected final ResourceLocation id;
	protected final String group;
	protected final Ingredient ingredient;
	protected final ItemStack result;
	protected final float experience;
	protected final int cookingTime;
	
	public AbstractColdRecipe(IRecipeType<?> recipeType, ResourceLocation name, String groupName, 
			Ingredient inputList, ItemStack output, float exp, int time) {
		this.type = recipeType;
		this.id = name;
		this.group = groupName;
		this.ingredient = inputList;
		this.result = output;
		this.experience = exp;
		this.cookingTime = time;
	}

	public IRecipeType<?> getType() {
		return this.type;
	}
	
	public ResourceLocation getId() {
		return this.id;
	}

	public String getGroup() {
		return this.group;
	}
	
	/** Check invSlot. **/
	public boolean matches(IInventory inv, World worldIn) {
		return this.ingredient.test(inv.getStackInSlot(AbstractReizouTileEntity.invSlot));
	}

	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add(this.ingredient);
		return nonnulllist;
	}
	
	public ItemStack getCraftingResult(IInventory inv) {
		return this.result.copy();
	}

	public ItemStack getRecipeOutput() {
		return this.result;
	}

	public float getExperience() {
		return this.experience;
	}

	public int getCookingTime() {
		return this.cookingTime;
	}
	
	public boolean canFit(int i, int count) {
		return true;
	}
}