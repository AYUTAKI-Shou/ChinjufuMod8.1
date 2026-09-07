package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BottleVanilla_1 extends BaseBottle_1 {

	public BottleVanilla_1(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items_NoTab.VANILLA_bot_24.get()));
	}
}
