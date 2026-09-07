package com.ayutaki.chinjufumod.blocks.crop;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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
	private static final VoxelShape[] SHAPES = new VoxelShape[]{ Block.box(2.0D, -1.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.box(2.0D, -1.0D, 2.0D, 14.0D, 6.0D, 14.0D),
			Block.box(2.0D, -1.0D, 2.0D, 14.0D, 7.0D, 14.0D),
			Block.box(2.0D, -1.0D, 2.0D, 14.0D, 7.0D, 14.0D),
			Block.box(1.5D, -1.0D, 1.5D, 14.5D, 8.0D, 14.5D),
			Block.box(1.5D, -1.0D, 1.5D, 14.5D, 8.0D, 14.5D),
			Block.box(1.0D, -1.0D, 1.0D, 15.0D, 10.0D, 15.0D),
			Block.box(1.0D, -1.0D, 1.0D, 15.0D, 10.0D, 15.0D) };

	public Tengusa(AbstractBlock.Properties props) {
		super(props);
		registerDefaultState(this.defaultBlockState().setValue(STAGE_0_7, Integer.valueOf(0))
				.setValue(WATERLOGGED, Boolean.valueOf(true)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_7);

		if (i != 7) { //i != 7
			if (hItem == Items.BONE_MEAL) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
				
				if (i < 6) { worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 2)), 3); }
				if (i == 6) { worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 1)), 3); } }
		}

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Limit the place. */
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() instanceof SandBlock || state.getBlock() instanceof GravelBlock;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		FluidState fluid = worldIn.getFluidState(pos);
		boolean canPlace = fluid.is(FluidTags.WATER) && fluid.getAmount() == 8;
		
		return this.mayPlaceOn(worldIn.getBlockState(downPos), worldIn, downPos) && canPlace;
	}

	/* Gives a value when placed. */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		float temp = worldIn.getBiome(pos).getTemperature(pos);
		boolean canPlace = fluid.is(FluidTags.WATER) && fluid.getAmount() == 8 && (temp >= 0.5F);
		
		return canPlace ? super.getStateForPlacement(context) : null;
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		BlockState blockState = super.updateShape(state, facing, newState, worldIn, pos, newPos);
		
		if (!blockState.isAir()) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		return !state.canSurvive(worldIn, pos) ? Blocks.AIR.defaultBlockState() : blockState;
	}

	/* TickRandom. Slow growth rate. rand.nextInt(12) == 0 ---> 7.3.x rand.nextInt(8) == 0 */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		int i = state.getValue(STAGE_0_7);
		if (i != 7 && worldIn.getRawBrightness(pos, 0) >= 9 && state.getValue(WATERLOGGED)) {
			if (rand.nextInt(8) == 0) {
				worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 1)), 2); } }

		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.getValue(STAGE_0_7)];
	}

	public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return false;
	}

	public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluid) {
		return false;
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_7, WATERLOGGED);
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.CROP_TENGUSA);
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
