package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.Item;

public class KettleBoil extends Item {

	public KettleBoil(Item.Properties props) {
		super(props.craftRemainder(Items_Teatime.KETTLE_kara.get()));
	}
}
