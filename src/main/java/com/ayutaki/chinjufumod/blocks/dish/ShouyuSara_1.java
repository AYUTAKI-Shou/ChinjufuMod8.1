package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShouyuSara_1 extends BaseFood_Stage9Water {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(6.0D, 0.0D, 9.0D, 10.0D, 0.5D, 13.0D);
	private static final VoxelShape AABB_WEST = Block.box(3.0D, 0.0D, 6.0D, 7.0D, 0.5D, 10.0D);
	private static final VoxelShape AABB_NORTH = Block.box(6.0D, 0.0D, 3.0D, 10.0D, 0.5D, 7.0D);
	private static final VoxelShape AABB_EAST = Block.box(9.0D, 0.0D, 6.0D, 13.0D, 0.5D, 10.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(6.0D, -8.0D, 9.0D, 10.0D, 0.1D, 13.0D);
	private static final VoxelShape DOWN_WEST = Block.box(3.0D, -8.0D, 6.0D, 7.0D, 0.1D, 10.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(6.0D, -8.0D, 3.0D, 10.0D, 0.1D, 7.0D);
	private static final VoxelShape DOWN_EAST = Block.box(9.0D, -8.0D, 6.0D, 13.0D, 0.1D, 10.0D);
	
	public ShouyuSara_1(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_9);
		boolean sushi = (hItem == Items_Teatime.SUSHI_S.get() || hItem == Items_Teatime.SUSHI_F.get() || hItem == Items_Teatime.SUSHI_B.get() || hItem == Items_Teatime.SUSHI_T.get());
		boolean shouyu = (hItem == Items_Teatime.SHOUYU_bot_14.get() || hItem == Items_NoTab.SHOUYU_bot_24.get() || hItem == Items_NoTab.SHOUYU_bot_34.get() || hItem == Items_NoTab.SHOUYU_bot_44.get());

		if (state.getValue(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { //!WATERLOGGED
			/** Empty **/
			if (i == 9) {
				if (shouyu) {
					if (hItem == Items_Teatime.SHOUYU_bot_14.get()) { CMEvents.Soysauce_to2(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_24.get()) { CMEvents.Soysauce_to3(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_34.get()) { CMEvents.Soysauce_to4(worldIn, pos, playerIn, hand); }
					if (hItem == Items_NoTab.SHOUYU_bot_44.get()) { CMEvents.Soysauce_toBottle(worldIn, pos, playerIn, hand); }
					
					worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(1)), 3); }
				
				if (!shouyu) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			/** Not empty **/
			if (i != 9) {
				if (sushi) {
					if (hItem == Items_Teatime.SUSHI_S.get()) {
						/** Collect with an Item **/
						CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI_S.get()); }
					
					if (hItem == Items_Teatime.SUSHI_F.get()) {
						CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI_F.get()); }
					
					if (hItem == Items_Teatime.SUSHI_B.get()) {
						CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI_B.get()); }
					
					if (hItem == Items_Teatime.SUSHI_T.get()) {
						CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI_T.get()); }
		
					worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(i + 1)), 3); }
				
				if (!sushi) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_9);
		
		if (waterIn(state, worldIn, pos) && i != 9) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_9, Integer.valueOf(9)), 3); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notDown? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return notDown? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return notDown? AABB_WEST : DOWN_WEST;
		case EAST:
			return notDown? AABB_EAST : DOWN_EAST;
		}
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_food_shouyusara_1").withStyle(ChatFormatting.GRAY));
	}
}
