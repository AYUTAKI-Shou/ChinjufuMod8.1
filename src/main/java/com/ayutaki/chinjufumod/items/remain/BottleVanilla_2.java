package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BottleVanilla_2 extends BaseBottle_2 {

	public BottleVanilla_2(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items_NoTab.VANILLA_bot_34.get()));
	}
}
