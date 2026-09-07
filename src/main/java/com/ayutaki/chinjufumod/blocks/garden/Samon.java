package com.ayutaki.chinjufumod.blocks.garden;

import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class Samon extends Block {
	public static final MapCodec<Samon> CODEC = simpleCodec(Samon::new);
	@Override
	public MapCodec<? extends Samon> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_0_7 = IntegerProperty.create("stage", 0, 7);

	public Samon(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_0_7, Integer.valueOf(0)));
	}
	
	/* RightClick Action -> ItemKumade */
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_7);
	}

	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		if (this == Garden_Blocks.SAMON_B.get()) { return new ItemStack(Items.GRAVEL); }
		return new ItemStack(Items.SAND);
	}
}
