package com.ayutaki.chinjufumod.blocks.window;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Curtain extends BaseStage4_FaceWater {
	/* Collision */
	private static final VoxelShape CLOSE_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	private static final VoxelShape CLOSE_WEST = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape CLOSE_NORTH = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape CLOSE_EAST = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);

	private static final VoxelShape OPENR_SOUTH = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.1D);
	private static final VoxelShape OPENR_WEST = Block.box(13.9D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape OPENR_NORTH = Block.box(0.0D, 0.0D, 13.9D, 2.0D, 16.0D, 16.0D);
	private static final VoxelShape OPENR_EAST = Block.box(0.0D, 0.0D, 0.0D, 2.1D, 16.0D, 2.0D);

	private static final VoxelShape OPENL_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.1D);
	private static final VoxelShape OPENL_WEST = Block.box(13.9D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
	private static final VoxelShape OPENL_NORTH = Block.box(14.0D, 0.0D, 13.9D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape OPENL_EAST = Block.box(0.0D, 0.0D, 14.0D, 2.1D, 16.0D, 16.0D);
	
	public Curtain(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		/** 1=Close Left, 2=Open Left, 3=Close Right, 4=Open Right **/
		int i = state.getValue(STAGE_1_4);
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Base_Hake) { return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; }

		else {
			CMEvents.soundCurtain(worldIn, pos, 0.8F, 0.9F);
			
			if (i == 1 || i == 3) {
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
	
			if (i == 2 || i == 4) {
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i - 1)), 3); }
	
			return ItemInteractionResult.SUCCESS;
		}
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		Player playerIn = context.getPlayer();

		int SNEAK = playerIn.isCrouching()? 3 : 1;
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
				.setValue(STAGE_1_4, Integer.valueOf(SNEAK));
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		int i = state.getValue(STAGE_1_4);

		switch (direction) {
		case NORTH:
		default:
			return (i == 1)? CLOSE_NORTH : ((i == 2)? OPENL_NORTH : ((i == 3)? CLOSE_NORTH : OPENR_NORTH));
		case SOUTH:
			return (i == 1)? CLOSE_SOUTH : ((i == 2)? OPENL_SOUTH : ((i == 3)? CLOSE_SOUTH : OPENR_SOUTH));
		case WEST:
			return (i == 1)? CLOSE_WEST : ((i == 2)? OPENL_WEST : ((i == 3)? CLOSE_WEST : OPENR_WEST));
		case EAST:
			return (i == 1)? CLOSE_EAST : ((i == 2)? OPENL_EAST : ((i == 3)? CLOSE_EAST : OPENR_EAST));
		}
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_window").withStyle(ChatFormatting.GRAY));
	}
}
