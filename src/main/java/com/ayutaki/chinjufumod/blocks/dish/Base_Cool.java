package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.MapColor;

public class Base_Cool extends Abstract_WaterLogged {
	public static final MapCodec<Base_Cool> CODEC = simpleCodec(Base_Cool::new);
	@Override
	public MapCodec<? extends Base_Cool> codec() { return CODEC; }

	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	public static final BooleanProperty UP = BooleanProperty.create("up");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");
	
	public Base_Cool(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false))
				.setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false))
				.setValue(UP, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}
	
	/* Connect the blocks. */
	public static boolean isICE(BlockGetter worldIn, BlockPos pos, Direction direction) {
		BlockState state = worldIn.getBlockState(pos.relative(direction));
		Block blockIn = state.getBlock();
		return (state.getMapColor(worldIn, pos) == MapColor.ICE) ||
					(blockIn instanceof SnowLayerBlock && state.getValue(SnowLayerBlock.LAYERS) == 8) ||
					(blockIn == Blocks.SNOW_BLOCK);
	}
	
	/* Conditions for TickRandom. */
	protected boolean cookingIn(LevelReader worldIn, BlockPos pos) {
		int localCount = 0;
		boolean north = isICE(worldIn, pos, Direction.NORTH);
		boolean east = isICE(worldIn, pos, Direction.EAST);
		boolean south = isICE(worldIn, pos, Direction.SOUTH);
		boolean west = isICE(worldIn, pos, Direction.WEST);
		boolean up = isICE(worldIn, pos, Direction.UP);
		boolean down = isICE(worldIn, pos, Direction.DOWN);
		
		if (north) { ++localCount; }
		if (east) { ++localCount; }
		if (south) { ++localCount; }
		if (west) { ++localCount; }
		if (up) { ++localCount; }
		if (down) { ++localCount; }
		return localCount >= 3;
	}

	protected boolean cookingOUT(LevelReader worldIn, BlockPos pos) {
		return !cookingIn(worldIn, pos);
	}
	
	protected boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	protected boolean waterOUT(BlockState state) {
		return !state.getValue(WATERLOGGED);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_4, NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED);
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_cool").withStyle(ChatFormatting.GRAY));
	}
}
