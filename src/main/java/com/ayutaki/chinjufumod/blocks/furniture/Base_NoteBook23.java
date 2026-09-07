package com.ayutaki.chinjufumod.blocks.furniture;

import com.ayutaki.chinjufumod.blocks.dish.BaseFood_Stage4Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Base_NoteBook23 extends BaseFood_Stage4Water {

	public Base_NoteBook23(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		int i = state.getValue(STAGE_1_4);		
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem == Items_Chinjufu.SHOUHOU_empty.get()) {
			if (i == 4) { return InteractionResult.PASS; }
			
			else { //i != 4
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3);
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand); 
				return InteractionResult.SUCCESS; }
		}
		
		if (hStack.isEmpty()) {
			CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Chinjufu.SHOUHOU_empty.get());
			
			if (i == 1) { worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3); }
			else { //i != 1
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 1)), 3); }
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { }
	}
	
	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(Items_Chinjufu.SHOUHOU_empty.get());
	}
}
