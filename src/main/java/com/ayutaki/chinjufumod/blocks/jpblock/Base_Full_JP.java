package com.ayutaki.chinjufumod.blocks.jpblock;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class Base_Full_JP extends Block {
	public static final MapCodec<Base_Full_JP> CODEC = simpleCodec(Base_Full_JP::new);
	@Override
	public MapCodec<? extends Base_Full_JP> codec() { return CODEC; }
	
	/* Property */
	public static final BooleanProperty CRACK = BooleanProperty.create("cra");
	
	public Base_Full_JP(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(CRACK, Boolean.valueOf(false)));
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Base_Hake) { return InteractionResult.PASS; }

		else {
			if (hStack.isEmpty()) {
				if (playerIn.isCrouching()) {
					CMEvents.soundStonePlace(worldIn, pos);
					worldIn.setBlock(pos, state.cycle(CRACK), 3);
					return InteractionResult.SUCCESS; }
			}
			return InteractionResult.PASS;
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(CRACK);
	}
}
