package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Abstract_Hake;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
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

public class Wall_Sama extends BaseStage4_FaceWater {

	/* Collision ...Based on the value of WallBlock.*/
	private static final VoxelShape AABB_NORTH = Shapes.or(Block.box(0.0D, 0.0D, 5.0D, 16.0D, 6.0D, 11.0D),
			Block.box(0.0D, 10.0D, 5.0D, 16.0D, 16.0D, 11.0D),
			Block.box(10.0D, 6.0D, 5.0D, 16.0D, 10.0D, 11.0D),
			Block.box(0.0D, 6.0D, 5.0D, 6.0D, 10.0D, 11.0D));
	private static final VoxelShape AABB_EAST = Shapes.or(Block.box(5.0D, 0.0D, 0.0D, 11.0D, 6.0D, 16.0D),
			Block.box(5.0D, 10.0D, 0.0D, 11.0D, 16.0D, 16.0D),
			Block.box(5.0D, 6.0D, 0.0D, 11.0D, 10.0D, 6.0D),
			Block.box(5.0D, 6.0D, 10.0D, 11.0D, 10.0D, 16.0D));
	private static final VoxelShape AABB_SOUTH = Shapes.or(Block.box(0.0D, 0.0D, 5.0D, 16.0D, 6.0D, 11.0D),
			Block.box(0.0D, 10.0D, 5.0D, 16.0D, 16.0D, 11.0D),
			Block.box(0.0D, 6.0D, 5.0D, 6.0D, 10.0D, 11.0D),
			Block.box(10.0D, 6.0D, 5.0D, 16.0D, 10.0D, 11.0D));
	private static final VoxelShape AABB_WEST = Shapes.or(Block.box(5.0D, 0.0D, 0.0D, 11.0D, 6.0D, 16.0D),
			Block.box(5.0D, 10.0D, 0.0D, 11.0D, 16.0D, 16.0D),
			Block.box(5.0D, 6.0D, 0.0D, 11.0D, 10.0D, 6.0D),
			Block.box(5.0D, 6.0D, 10.0D, 11.0D, 10.0D, 16.0D));
	
	private static final VoxelShape DOUBLE_NORTH = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(10.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 6.0D, 10.0D, 16.0D));
	private static final VoxelShape DOUBLE_EAST = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 6.0D),
			Block.box(0.0D, 6.0D, 10.0D, 16.0D, 10.0D, 16.0D));
	private static final VoxelShape DOUBLE_SOUTH = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 6.0D, 10.0D, 16.0D),
			Block.box(10.0D, 6.0D, 0.0D, 16.0D, 10.0D, 16.0D));
	private static final VoxelShape DOUBLE_WEST = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 16.0D, 10.0D, 6.0D),
			Block.box(0.0D, 6.0D, 10.0D, 16.0D, 10.0D, 16.0D));

	public Wall_Sama(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);
		
		if (hItem instanceof Abstract_Hake) { return InteractionResult.PASS; }

		if (hStack.getItem() == this.asItem() && (i == 1 || i == 2)) {
			CMEvents.consume1_seStoneP(worldIn, pos, playerIn, hand);
			worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 2)), 3);
			return InteractionResult.SUCCESS; }
		
		else {
			if (hStack.isEmpty() && playerIn.isCrouching()) {
				CMEvents.soundStonePlace(worldIn, pos);

				switch (i) {
				case 1:
				default:
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3);
					break;

				case 2:
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 1)), 3);
					break;

				case 3:
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3);
					break;
					
				case 4:
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 1)), 3);
					break;
				} // switch STAGE_1_4
				return InteractionResult.SUCCESS; 
			}
			return InteractionResult.PASS;
		}
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		int i = state.getValue(STAGE_1_4);
		
		switch (i) {
		case 1:
		default:
		case 2:
			switch (direction) {
			case NORTH:
			default: return AABB_NORTH;
			case SOUTH: return AABB_SOUTH;
			case WEST: return AABB_WEST;
			case EAST: return AABB_EAST;
			}

		case 3:
		case 4:
			switch (direction) {
			case NORTH:
			default: return DOUBLE_NORTH;
			case SOUTH: return DOUBLE_SOUTH;
			case WEST: return DOUBLE_WEST;
			case EAST: return DOUBLE_EAST;
			}
		} // switch STAGE_1_4
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(new TranslatableComponent("tips.block_sama").withStyle(ChatFormatting.GRAY));
	}
}
