package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.base.Abstract_ConnectWater;
import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.furnace.Irori;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BaseNabe extends Abstract_ConnectWater {
	public static final MapCodec<BaseNabe> CODEC = simpleCodec(BaseNabe::new);
	@Override
	public MapCodec<? extends BaseNabe> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	public static final BooleanProperty COOK = BooleanProperty.create("cook");

	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(1.5D, 0.0D, 3.5D, 10.5D, 4.5D, 12.5D);
	private static final VoxelShape AABB_WEST = Block.box(3.5D, 0.0D, 1.5D, 12.5D, 4.5D, 10.5D);
	private static final VoxelShape AABB_NORTH = Block.box(5.5D, 0.0D, 3.5D, 14.5D, 4.5D, 12.5D);
	private static final VoxelShape AABB_EAST = Block.box(3.5D, 0.0D, 5.5D, 12.5D, 4.5D, 14.5D);

	private static final VoxelShape COOK_SOUTH = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	private static final VoxelShape COOK_WEST = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	private static final VoxelShape COOK_NORTH = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	private static final VoxelShape COOK_EAST = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);

	private static final VoxelShape DOWN_SOUTH = Block.box(1.5D, -8.0D, 3.5D, 10.5D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_WEST = Block.box(3.5D, -8.0D, 1.5D, 12.5D, 0.1D, 10.5D);
	private static final VoxelShape DOWN_NORTH = Block.box(5.5D, -8.0D, 3.5D, 14.5D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_EAST = Block.box(3.5D, -8.0D, 5.5D, 12.5D, 0.1D, 14.5D);
	
	public BaseNabe(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(COOK, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(COOK, this.connectCook(worldIn, pos, Direction.DOWN))
				.setValue(DOWN, Abstract_ConnectWater.connectHalf(worldIn, pos, Direction.DOWN))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
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
	protected boolean connectCook(BlockGetter worldIn, BlockPos pos, Direction direct) {
		BlockPos newPos = pos.relative(direct);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return (block instanceof FurnaceBlock || block instanceof AbstractOvenBlock || 
				block instanceof Irori || block instanceof Kit_Cooktop);
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		if (connectWater(worldIn, pos, Direction.DOWN)) {
			tick.scheduleTick(pos, this, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state, worldIn, pos)) { tick.scheduleTick(pos, this, 60); }

		boolean cook = connectCook(worldIn, pos, Direction.DOWN);
		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.setValue(COOK, cook).setValue(DOWN, down);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.scheduleTick(pos, this, 60); }
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(COOK, DOWN, H_FACING, STAGE_1_4, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notCook = !((Boolean)state.getValue(COOK)).booleanValue();
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notCook? (notDown? AABB_NORTH : DOWN_NORTH) : COOK_NORTH;
		case SOUTH:
			return notCook? (notDown? AABB_SOUTH : DOWN_SOUTH) : COOK_SOUTH;
		case WEST:
			return notCook? (notDown? AABB_WEST : DOWN_WEST) : COOK_WEST;
		case EAST:
			return notCook? (notDown? AABB_EAST : DOWN_EAST) : COOK_EAST;
		}
	}
	
	private boolean cookingIn(LevelReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		Block downBlock = downState.getBlock();
		return (downBlock instanceof FurnaceBlock && downState.getValue(FurnaceBlock.LIT) == true) ||
				(downBlock instanceof AbstractOvenBlock && downState.getValue(AbstractOvenBlock.LIT) == true) ||
				(downBlock instanceof Irori && downState.getValue(Irori.LIT) == true) ||
				(downBlock instanceof Kit_Cooktop && downState.getValue(Kit_Cooktop.STAGE_1_3) == 2);
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		boolean notGohan = (this != Dish_Blocks.NABEGOHAN.get() && this != Dish_Blocks.NABEGOHAN_TAKE.get() && 
				this != Dish_Blocks.NABEGOHAN_KURI.get() && this != Dish_Blocks.NABESEKIHAN.get());
		if (notGohan) {
			
			if (cookingIn(worldIn, pos)) {
				if (rand.nextDouble() < 0.1D) {
					worldIn.playLocalSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU.get(), SoundSource.BLOCKS, 0.3F, 0.7F, false); }

				if (rand.nextDouble() < 0.25D) {
					/** which, position x y z, speed x y z **/
					worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
			}
		}
	}
}
