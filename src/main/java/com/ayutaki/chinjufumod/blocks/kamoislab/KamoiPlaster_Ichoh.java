package com.ayutaki.chinjufumod.blocks.kamoislab;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class KamoiPlaster_Ichoh extends Base_KamoiPlaster {

	public KamoiPlaster_Ichoh(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(Items_Seasonal.PILLARSLAB_ich.get());
	}
}
