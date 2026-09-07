package com.ayutaki.chinjufumod.blocks.jpblock;

import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class Base_Full_JP extends Block {
	/* Property */
	public static final BooleanProperty CRACK = BooleanProperty.create("cra");
	
	public Base_Full_JP(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(CRACK, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);

		if (hStack.isEmpty() && playerIn.isCrouching()) {
			CMEvents.soundStonePlace(worldIn, pos);
			worldIn.setBlock(pos, state.cycle(CRACK), 3);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(CRACK);
	}
}
