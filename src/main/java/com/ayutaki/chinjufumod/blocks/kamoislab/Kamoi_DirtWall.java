package com.ayutaki.chinjufumod.blocks.kamoislab;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_WallPanel;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class Kamoi_DirtWall extends Abstract_KamoiStone {

	public Kamoi_DirtWall(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(cloneItem(), 1);
	}
	
	private Item cloneItem() {
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_oak.get()) { return Items_WallPanel.PILLARSLAB_oak.get(); }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_spru.get()) { return Items_WallPanel.PILLARSLAB_spru.get(); }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_bir.get()) { return Items_WallPanel.PILLARSLAB_bir.get(); }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_jun.get()) { return Items_WallPanel.PILLARSLAB_jun.get(); }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_aca.get()) { return Items_WallPanel.PILLARSLAB_aca.get(); }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_doak.get()) { return Items_WallPanel.PILLARSLAB_doak.get(); }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_sakura.get()) { return Items_Seasonal.PILLARSLAB_saku.get(); }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_kaede.get()) { return Items_Seasonal.PILLARSLAB_kae.get(); }
		else { return Items_Seasonal.PILLARSLAB_ich.get(); }
	}
}
