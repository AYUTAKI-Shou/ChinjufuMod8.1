package com.ayutaki.chinjufumod.blocks.crop;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class Tengusa extends Abstract_WaterLogged {
	/* Property */
	public static final IntegerProperty STAGE_0_7 = IntegerProperty.create("stage", 0, 7);

	/* Collision */
	private static final VoxelShape[] SHAPES = new VoxelShape[]{ Block.makeCuboidShape(2.0D, -1.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.makeCuboidShape(2.0D, -1.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.makeCuboidShape(2.0D, -1.0D, 2.0D, 14.0D, 7.0D, 14.0D),
			Block.makeCuboidShape(2.0D, -1.0D, 2.0D, 14.0D, 7.0D, 14.0D),
			Block.makeCuboidShape(1.5D, -1.0D, 1.5D, 14.5D, 8.0D, 14.5D),
			Block.makeCuboidShape(1.5D, -1.0D, 1.5D, 14.5D, 8.0D, 14.5D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 10.0D, 15.0D),
			Block.makeCuboidShape(1.0D, -1.0D, 1.0D, 15.0D, 10.0D, 15.0D) };

	public Tengusa(Block.Properties props) {
		super(props);
		setDefaultState(this.stateContainer.getBaseState().with(STAGE_0_7, Integer.valueOf(0))
				.with(WATERLOGGED, Boolean.valueOf(true)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_0_7);

		if (i != 7) { //i != 7
			if (hItem == Items.BONE_MEAL) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
				
				if (i < 6) { worldIn.setBlockState(pos, state.with(STAGE_0_7, Integer.valueOf(i + 2)), 3); }
				if (i == 6) { worldIn.setBlockState(pos, state.with(STAGE_0_7, Integer.valueOf(i + 1)), 3); } }
		}

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
	
	/* Limit the place. */
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() instanceof SandBlock || state.getBlock() instanceof GravelBlock;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.down();
		IFluidState fluid = worldIn.getFluidState(pos);
		boolean canPlace = fluid.isTagged(FluidTags.WATER) && fluid.getLevel() == 8;
		
		return this.isValidGround(worldIn.getBlockState(downPos), worldIn, downPos) && canPlace;
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		float temp = worldIn.getBiome(pos).getTemperature(pos);
		boolean canPlace = fluid.isTagged(FluidTags.WATER) && fluid.getLevel() == 8 && (temp >= 0.5F);
		
		return canPlace ? super.getStateForPlacement(context) : null;
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		BlockState blockState = super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
		
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }
		return !state.isValidPosition(worldIn, pos) ? Blocks.AIR.getDefaultState() : blockState;
	}

	/* TickRandom. Slow growth rate. rand.nextInt(12) == 0 */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		int i = state.get(STAGE_0_7);
		if (i != 7 && worldIn.getLightSubtracted(pos, 0) >= 9 && state.get(WATERLOGGED)) {
			if (rand.nextInt(8) == 0) {
				worldIn.setBlockState(pos, state.with(STAGE_0_7, Integer.valueOf(i + 1)), 2); } }

		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.get(STAGE_0_7)];
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
		builder.add(STAGE_0_7, WATERLOGGED);
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.CROP_TENGUSA);
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
