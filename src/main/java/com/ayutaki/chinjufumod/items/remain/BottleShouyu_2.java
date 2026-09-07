package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BottleShouyu_2 extends BaseBottle_2 {

	public BottleShouyu_2(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items_NoTab.SHOUYU_bot_34.get()));
	}
}
