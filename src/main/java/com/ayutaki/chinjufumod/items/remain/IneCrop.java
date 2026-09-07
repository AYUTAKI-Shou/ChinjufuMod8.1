package com.ayutaki.chinjufumod.items.remain;

import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.world.item.Item;

public class IneCrop extends Item {

	public IneCrop(Item.Properties props) {
		super(props.craftRemainder(Items_Teatime.INEWARA.get())); //Writing this in 'registry' will be null.
	}
}
