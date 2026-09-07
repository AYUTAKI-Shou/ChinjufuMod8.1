package com.ayutaki.chinjufumod.items.weapon;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public enum CMTiers implements Tier {
	
	ANCHOR(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 6.0F, 2.0F, 14, () -> Ingredient.of(Items.IRON_INGOT));

	private final TagKey<Block> incorrectBlocksForDrops;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Supplier<Ingredient> repairIngredient;

	private CMTiers(final TagKey<Block> tag, final int durability, final float atackSpeed, final float dealDamage, final int enchant, final Supplier<Ingredient> repairItem) {
		this.incorrectBlocksForDrops = tag;
		this.uses = durability;
		this.speed = atackSpeed;
		this.damage = dealDamage;
		this.enchantmentValue = enchant;
		this.repairIngredient = Suppliers.memoize(repairItem::get);
	}

	@Override
	public int getUses() {
		return this.uses;
	}

	@Override
	public float getSpeed() {
		return this.speed;
	}

	@Override
	public float getAttackDamageBonus() {
		return this.damage;
	}

	@Override
	public TagKey<Block> getIncorrectBlocksForDrops() {
		return this.incorrectBlocksForDrops;
	}

	@Override
	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}
}
