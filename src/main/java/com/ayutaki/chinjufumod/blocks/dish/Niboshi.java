package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Niboshi extends BaseStage4_FaceWater {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 1.0D, 13.0D);
	
	/* Property 1=raw, 2=raw, 3=raw, 4=dry */
	public Niboshi(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);

		if (i == 4) {
			if (hItem == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.DASHI_bot_14.get());	
				worldIn.setBlock(pos, Dish_Blocks.SCONESET_kara.get().defaultBlockState()
						.setValue(SconeSet_kara.STAGE_1_2, Integer.valueOf(2)), 3); }
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		else { //i != 4
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	protected static boolean hasWater(LevelReader worldIn, BlockPos pos) {
		for(BlockPos nearPos : BlockPos.betweenClosed(pos.offset(-2, -2, -2), pos.offset(2, 2, 2))) {
			if (worldIn.getFluidState(nearPos).is(FluidTags.WATER)) {
				return true;
			}
		}
		return false;
	}
	
	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state)) { tick.scheduleTick(pos, this, 100); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 100); }
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 100);
			CMEvents.soundSnowBreak(worldIn, pos);
			worldIn.destroyBlock(pos, true); }

		else { }
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_4);
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		if (i < 4 && !hasWater(worldIn, pos)) {
			if (rand.nextInt(4) == 0) {
			worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); } }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_niboshi_boiled").withStyle(ChatFormatting.GRAY));
	}
}
