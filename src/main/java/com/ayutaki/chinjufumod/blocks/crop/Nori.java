package com.ayutaki.chinjufumod.blocks.crop;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class Nori extends Abstract_WaterLogged {
	/* Property */
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	public static final IntegerProperty STAGE_1_6 = IntegerProperty.create("age", 1, 6);

	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 1.25D, 15.0D);

	public Nori(Block.Properties props) {
		super(props);
		setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false))
				.with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false))
				.with(STAGE_1_6, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(true)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_6);

		if (i != 6) {
			if (hItem == Items.BONE_MEAL) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
				
				if (i < 5) { worldIn.setBlockState(pos, state.with(STAGE_1_6, Integer.valueOf(i + 2)), 3); }
				if (i == 5) { worldIn.setBlockState(pos, state.with(STAGE_1_6, Integer.valueOf(i + 1)), 3); } }
		}

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
	
	/* Limit the place. */
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return (state.getMaterial() == Material.ROCK && state.getMaterial().isSolid());
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.down();
		IFluidState fluid = worldIn.getFluidState(pos);
		boolean canPlace = fluid.isTagged(FluidTags.WATER) && fluid.getLevel() == 8;
		
		return this.isValidGround(worldIn.getBlockState(downPos), worldIn, downPos) && canPlace;
	}

	/* Connect the blocks. */
	public static boolean canConnectTo(IWorld worldIn, BlockPos pos, Direction direction) {
		BlockState state = worldIn.getBlockState(pos.offset(direction));
		return state.isSolidSide(worldIn, pos, direction);
	}
	
	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);

		boolean canPlace = fluid.isTagged(FluidTags.WATER) && fluid.getLevel() == 8;
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST);
		
		return canPlace? this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER))
				.with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west) : null;
	}

	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }
		
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST);
		return !state.isValidPosition(worldIn, pos) ? Blocks.AIR.getDefaultState() : state
				.with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west);
	}

	/* TickRandom. Slow growth rate. rand.nextInt(12) == 0 */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		int i = state.get(STAGE_1_6);
		if (i != 6 && worldIn.getLightSubtracted(pos, 0) >= 9 && state.get(WATERLOGGED)) {
			if (rand.nextInt(8) == 0) {
				worldIn.setBlockState(pos, state.with(STAGE_1_6, Integer.valueOf(i + 1)), 2); } }

		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return false;
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidState) {
		return false;
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE_1_6, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.NORI_N);
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
	
	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}
}
