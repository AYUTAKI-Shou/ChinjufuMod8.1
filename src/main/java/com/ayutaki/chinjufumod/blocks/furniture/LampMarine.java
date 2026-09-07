package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_6FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LampMarine extends Base_6FaceWater {
	/* Property */
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	/* Collision */
	private static final VoxelShape AABB_UP = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 7.0D, 10.0D);
	private static final VoxelShape AABB_DOWN = Block.box(6.0D, 9.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private static final VoxelShape AABB_SOUTH = Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 7.0D);
	private static final VoxelShape AABB_WEST = Block.box(9.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	private static final VoxelShape AABB_NORTH = Block.box(6.0D, 6.0D, 9.0D, 10.0D, 10.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 6.0D, 6.0D, 7.0D, 10.0D, 10.0D);
	
	public LampMarine(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP)
				.setValue(LIT, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {

		if (state.getValue(LIT)) {
			CMEvents.soundStoneButton_Off(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3); }

		else { //!LIT
			CMEvents.soundStoneButton_On(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 3); }

		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, LIT, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(FACING);

		switch (direction) {
		default:
		case UP: return AABB_UP;
		case DOWN: return AABB_DOWN;
		case NORTH: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(new TranslatableComponent("tips.block_lamp").withStyle(ChatFormatting.GRAY));
	}
}
