package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import com.ayutaki.chinjufumod.tags.ItemCMTags;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public record CMTiers(TagKey<Block> incorrectBlock, int durability, float atackSpeed, float dealDamage, int enchant, TagKey<Item> repairItems) {
	public static final ToolMaterial ANCHOR = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 500, 6.0F, 2.0F, 14, ItemTags.IRON_TOOL_MATERIALS);
	public static final ToolMaterial SAKURA = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 0.0F, 15, ItemCMTags.REPAIRS_SAKURA);
	public static final ToolMaterial KAEDE = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 0.0F, 15, ItemCMTags.REPAIRS_KAEDE);
	public static final ToolMaterial ICHOH = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 2.0F, 0.0F, 15, ItemCMTags.REPAIRS_ICHOH);

	private Item.Properties applyCommonProperties(Item.Properties props) {
		return props.durability(this.durability).repairable(this.repairItems).enchantable(this.enchant);
	}

	public Item.Properties applyToolProperties(Item.Properties props, TagKey<Block> blockTag, float damage, float speed) {
		HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
		return this.applyCommonProperties(props)
			.component(
				DataComponents.TOOL,
				new Tool(List.of(
						Tool.Rule.deniesDrops(holdergetter.getOrThrow(this.incorrectBlock)), 
						Tool.Rule.minesAndDrops(holdergetter.getOrThrow(blockTag), this.atackSpeed)),
					1.0F, 1 ))
			.attributes(this.createToolAttributes(damage, speed));
	}

	private ItemAttributeModifiers createToolAttributes(float damage, float speed) {
		return ItemAttributeModifiers.builder()
			.add(Attributes.ATTACK_DAMAGE,
				new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, (double)(damage + this.dealDamage), AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ATTACK_SPEED,
				new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, (double)speed, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND).build();
	}

	@SuppressWarnings("deprecation")
	public Item.Properties applySwordProperties(Item.Properties props, float damage, float speed) {
		HolderGetter<Block> holdergetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
		return this.applyCommonProperties(props)
			.component(
				DataComponents.TOOL,
				new Tool(List.of(
						Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
						Tool.Rule.overrideSpeed(holdergetter.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)),
					1.0F, 2))
			.attributes(this.createSwordAttributes(damage, speed));
	}

	private ItemAttributeModifiers createSwordAttributes(float damage, float speed) {
		return ItemAttributeModifiers.builder()
			.add(Attributes.ATTACK_DAMAGE,
				new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, (double)(damage + this.dealDamage), AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND)
			.add(Attributes.ATTACK_SPEED,
				new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, (double)speed, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND).build();
	}
}
