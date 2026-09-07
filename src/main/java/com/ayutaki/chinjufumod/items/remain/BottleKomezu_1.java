package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BottleKomezu_1 extends BaseBottle_3 {

	public BottleKomezu_1(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items_NoTab.KOMEZU_bot_22.get()));
	}
}
