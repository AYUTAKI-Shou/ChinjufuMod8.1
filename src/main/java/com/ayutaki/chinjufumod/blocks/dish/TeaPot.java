package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
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

public class TeaPot extends BaseFood_Stage5Water {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(4.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D);
	private static final VoxelShape AABB_WEST = Block.box(6.0D, 0.0D, 4.0D, 10.0D, 4.0D, 10.0D);
	private static final VoxelShape AABB_NORTH = Block.box(6.0D, 0.0D, 6.0D, 12.0D, 4.0D, 10.0D);
	private static final VoxelShape AABB_EAST = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 12.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(4.0D, -8.0D, 2.0D, 10.0D, 0.1D, 6.0D);
	private static final VoxelShape DOWN_WEST = Block.box(10.0D, -8.0D, 4.0D, 14.0D, 0.1D, 10.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(6.0D, -8.0D, 10.0D, 12.0D, 0.1D, 14.0D);
	private static final VoxelShape DOWN_EAST = Block.box(2.0D, -8.0D, 6.0D, 6.0D, 0.1D, 12.0D);
	
	public TeaPot(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hItem == Items_Teatime.TCUP_kara.get()) {
				/** Collect with an Item **/
				CMEvents.changeDish_seTea(worldIn, pos, playerIn, hand, Items_Teatime.TEACUP.get());
				worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3); }
			
			if (hItem != Items_Teatime.TCUP_kara.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_5);
		
		if (waterIn(state, worldIn, pos) && i != 5) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.soundBubble(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(5)), 3); }
		
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
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(new TranslatableComponent("tips.block_food_teapot_1").withStyle(ChatFormatting.GRAY));
	}
}
