package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.registry.Items_NoTab;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BottleShouyu_3 extends BaseBottle_3 {

	public BottleShouyu_3(Block block, Item.Properties props) {
		super(block, props.craftRemainder(Items_NoTab.SHOUYU_bot_44.get()));
	}
}
