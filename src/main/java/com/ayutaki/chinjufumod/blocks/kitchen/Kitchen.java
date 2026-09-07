package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Kitchen extends Base_Tana7 {
	/* Collision */
	private static final VoxelShape TOP = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	private static final VoxelShape AABB_SOUTH = Shapes.or(TOP, Block.box(0.0D, 0.0D, 1.0D, 16.0D, 15.0D, 15.0D));
	private static final VoxelShape AABB_WEST = Shapes.or(TOP, Block.box(1.0D, 0.0D, 0.0D, 15.0D, 15.0D, 16.0D));
	private static final VoxelShape AABB_NORTH = Shapes.or(TOP, Block.box(0.0D, 0.0D, 1.0D, 16.0D, 15.0D, 15.0D));
	private static final VoxelShape AABB_EAST = Shapes.or(TOP, Block.box(1.0D, 0.0D, 0.0D, 15.0D, 15.0D, 16.0D));
	
	public Kitchen(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_7);
		/** 1=Empty, 2=KETTLE, 3=ZUNDOU, 4=DONABE, 5=DONABE, 6=FRYPAN, 7=FRYPAN **/

		if (hStack.isEmpty()) {
			if (i == 1) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			
			if (i == 2) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.KETTLE_kara.get());
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(1)), 3); }
			
			if (i == 3) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.ZUNDOU.get());
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(1)), 3); }
			
			if (i == 4 && hItem != Items_Teatime.NABE_kara.get()) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.NABE_kara.get());
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(1)), 3); }
	
			if (i == 5) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.NABE_kara.get());
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(4)), 3); }
			
			if (i == 6 && hItem != Items_Teatime.FRYPAN_kara.get()) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.FRYPAN_kara.get());
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(1)), 3); }
			
			if (i == 7) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.FRYPAN_kara.get());
				worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(6)), 3); }
		}

		else { //!empty
			if (i == 1) {
				if (hItem == Items_Teatime.KETTLE_kara.get()) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 1.0F, 3.0F);
					worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(2)), 3); }
	
				if (hItem == Items_Teatime.ZUNDOU.get()) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 1.0F, 3.0F);
					worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(3)), 3); }
	
				if (hItem == Items_Teatime.NABE_kara.get()) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
					worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(4)), 3); }
	
				if (hItem == Items_Teatime.FRYPAN_kara.get()) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 1.0F, 3.0F);
					worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(6)), 3); }
				
				if (hItem != Items_Teatime.KETTLE_kara.get() && hItem != Items_Teatime.ZUNDOU.get() &&
						hItem != Items_Teatime.NABE_kara.get() && hItem != Items_Teatime.FRYPAN_kara.get()) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
	
			if (i == 4) {
				if (hItem == Items_Teatime.NABE_kara.get()) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
					worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(5)), 3); }
				
				if (hItem != Items_Teatime.NABE_kara.get()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
			}
			
			if (i == 6) {
				if (hItem == Items_Teatime.FRYPAN_kara.get()) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.METAL_PLACE, SoundSource.BLOCKS, 1.0F, 3.0F);
					worldIn.setBlock(pos, state.setValue(STAGE_1_7, Integer.valueOf(7)), 3); }
				
				if (hItem != Items_Teatime.FRYPAN_kara.get()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
			}
			
			if (i != 1 && i != 4 && i != 6) { //It needs to be made clear.
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_kitchen").withStyle(ChatFormatting.GRAY));
	}
}
