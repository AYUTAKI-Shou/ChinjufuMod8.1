package com.ayutaki.chinjufumod.blocks.kamoislab;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_WallPanel;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class Kamoi_DirtWall extends Abstract_KamoiStone {

	public Kamoi_DirtWall(Block.Properties props) {
		super(props);
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(cloneItem(), 1);
	}
	
	private Item cloneItem() {
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_oak) { return Items_WallPanel.PILLARSLAB_oak; }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_spru) { return Items_WallPanel.PILLARSLAB_spru; }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_bir) { return Items_WallPanel.PILLARSLAB_bir; }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_jun) { return Items_WallPanel.PILLARSLAB_jun; }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_aca) { return Items_WallPanel.PILLARSLAB_aca; }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_doak) { return Items_WallPanel.PILLARSLAB_doak; }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_sakura) { return Items_Seasonal.PILLARSLAB_saku; }
		if (this == KamoiPlaster_Blocks.KAMOI_dirt_kaede) { return Items_Seasonal.PILLARSLAB_kae; }
		else { return Items_Seasonal.PILLARSLAB_ich; }
	}
}
