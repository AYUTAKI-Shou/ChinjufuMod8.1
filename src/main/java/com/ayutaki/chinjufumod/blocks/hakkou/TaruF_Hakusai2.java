package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class TaruF_Hakusai2 extends Abstract_WaterLogged {
	/* Property */
	public static final IntegerProperty STAGE_0_9 = IntegerProperty.create("stage", 0, 9);

	/* Collision */
	private static final VoxelShape AABB_BOX = VoxelShapes.or(Block.makeCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.25D, 8.0D, 0.25D, 15.75D, 12.0D, 15.75D),
			Block.makeCuboidShape(0.5D, 4.0D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.makeCuboidShape(0.75D, 0.0D, 0.75D, 15.25D, 4.0D, 15.25D));

	/** 0-5=unfinished, 6=1st time, 7=2nd time, 8=3rd time, 9=last **/
	public TaruF_Hakusai2(Block.Properties props) {
		super(props);
		setDefaultState(this.stateContainer.getBaseState().with(STAGE_0_9, Integer.valueOf(0))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_0_9);
	
		/** Can harvest **/
		if (i > 5) {
			if (hItem == Items_Teatime.SARA) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.HAKUSAIDUKE);

				if (i == 9) {
					worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOU_TARU.getDefaultState()
							.with(Taru_Hakkou.STAGE_0_5, Integer.valueOf(5))); }
				else { //i != 9
					worldIn.setBlockState(pos, state.with(STAGE_0_9, Integer.valueOf(i + 1))); }
			}
			
			if (hItem != Items_Teatime.SARA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i <= 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.get(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOU_TARU.getDefaultState()
					.with(Taru_Hakkou.STAGE_0_5, Integer.valueOf(5))
					.with(Taru_Hakkou.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		int i = state.get(STAGE_0_9);
		if (i >= 6) { }
		
		else { //<= 5
			if (rand.nextInt(2) == 0) {
				if (i < 5) {
					worldIn.setBlockState(pos, state.with(STAGE_0_9, Integer.valueOf(i + 1))); }

				if (i == 5) {
					worldIn.setBlockState(pos, state.with(STAGE_0_9, Integer.valueOf(i + 1)));
					CMEvents.addEXP(1, worldIn, pos); } }
		}
	}

	/* Create Blockstate */
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_9, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
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

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_taru_hakusai_f2").applyTextStyle(TextFormatting.GRAY));
	}
}
