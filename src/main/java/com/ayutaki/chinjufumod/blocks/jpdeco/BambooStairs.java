package com.ayutaki.chinjufumod.blocks.jpdeco;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BambooStairs extends Abstract_WaterLogged {
	public static final MapCodec<BambooStairs> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(BlockState.CODEC.fieldOf("base_state")
					.forGetter(getter -> getter.baseState), propertiesCodec()).apply(instance, BambooStairs::new));
	@Override
	public MapCodec<? extends BambooStairs> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<Half> TYPE = EnumProperty.create("type", Half.class);
	
	/* Collision */
	private static final VoxelShape BOT_BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape BOT_SOUTH = Shapes.or(BOT_BASE, Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D));
	private static final VoxelShape BOT_WEST = Shapes.or(BOT_BASE, Block.box(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D));
	private static final VoxelShape BOT_NORTH = Shapes.or(BOT_BASE, Block.box(0.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D));
	private static final VoxelShape BOT_EAST = Shapes.or(BOT_BASE, Block.box(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 16.0D));
	
	private static final VoxelShape TOP_BASE = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape TOP_SOUTH = Shapes.or(TOP_BASE, Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D));
	private static final VoxelShape TOP_WEST = Shapes.or(TOP_BASE, Block.box(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape TOP_NORTH = Shapes.or(TOP_BASE, Block.box(0.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape TOP_EAST = Shapes.or(TOP_BASE, Block.box(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 16.0D));
	
	private final Block base;
	private final BlockState baseState;
	
	public BambooStairs(BlockState state, BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(TYPE, Half.BOTTOM)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));

		this.baseState = state;
		this.base = state.getBlock();
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		BlockState blockState2 = this.defaultBlockState().setValue(TYPE, Half.BOTTOM).setValue(WATERLOGGED, Boolean.valueOf(Boolean.valueOf(fluid.getType() == Fluids.WATER)))
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
		Direction hitFace = context.getClickedFace();

		return hitFace != Direction.DOWN && (hitFace == Direction.UP || context.getClickLocation().y - (double)pos.getY() <= 0.5D) ? blockState2 : blockState2.setValue(TYPE, Half.TOP);
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
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	@Override
	public boolean isPathfindable(BlockState state, PathComputationType type) {
		switch (type) {
		case LAND:
			return false;
		case WATER:
			return state.getFluidState().is(FluidTags.WATER);
		case AIR:
			return false;
		default:
			return false;
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, TYPE, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		Half half = state.getValue(TYPE);
		
		switch (half) {
		case BOTTOM :
		default:
			
			switch (direction) {
			case NORTH:
			default: return BOT_NORTH;
			case SOUTH: return BOT_SOUTH;
			case EAST: return BOT_EAST;
			case WEST: return BOT_WEST;
			} // switch

		case TOP :
			
			switch (direction) {
			case NORTH:
			default: return TOP_NORTH;
			case SOUTH: return TOP_SOUTH;
			case EAST: return TOP_EAST;
			case WEST: return TOP_WEST;
			} // switch
		} // switch LOWER-UPPER
	}
	
	/* from StairsBlock */
	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
		this.base.animateTick(stateIn, worldIn, pos, rand);
	}

	public void attack(BlockState state, Level worldIn, BlockPos pos, Player playerIn) {
		this.baseState.attack(worldIn, pos, playerIn);
	}

	public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
		this.base.destroy(worldIn, pos, state);
	}

	@SuppressWarnings("deprecation")
	public float getExplosionResistance() {
		return this.base.getExplosionResistance();
	}

	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }
	
}
