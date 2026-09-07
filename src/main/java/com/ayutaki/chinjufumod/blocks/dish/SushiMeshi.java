package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
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

public class SushiMeshi extends Base_ConnectWater {
	/* Property */
	public static final IntegerProperty STAGE_0_15 = IntegerProperty.create("stage", 0, 15);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 2.5D, 15.0D);
	private static final VoxelShape AABB_DOWN = Block.makeCuboidShape(1.0D, -8.0D, 1.0D, 15.0D, 0.1D, 15.0D);

	public SushiMeshi(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(STAGE_0_15, Integer.valueOf(0))
				.with(DOWN, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_0_15);
		boolean kirimi = (hItem == Items_Teatime.KIRIMI_S || hItem == Items_Teatime.KIRIMI_F || hItem == Items_Teatime.KIRIMI_B || hItem == Items_Teatime.KIRIMI_T);
		
		if (kirimi) {
			if (hItem == Items_Teatime.KIRIMI_S) { 
				/** Collect with an Item **/
				CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
				CMEvents.take1Item(playerIn, hand, Items_Teatime.SUSHI_S); }
			
			if (hItem == Items_Teatime.KIRIMI_F) { 
				CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
				CMEvents.take1Item(playerIn, hand, Items_Teatime.SUSHI_F); }
			
			if (hItem == Items_Teatime.KIRIMI_B) { 
				CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
				CMEvents.take1Item(playerIn, hand, Items_Teatime.SUSHI_B); }
			
			if (hItem == Items_Teatime.KIRIMI_T) { 
				CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
				CMEvents.take1Item(playerIn, hand, Items_Teatime.SUSHI_T); }
			
			if (i != 15) { worldIn.setBlockState(pos, state.with(STAGE_0_15, Integer.valueOf(i + 1)), 3); }

			if (i == 15) {
				worldIn.setBlockState(pos, Dish_Blocks.SUSHIOKE_FULL_1.getDefaultState()
						.with(SushiOkeFull_1.H_FACING, Direction.NORTH)
						.with(SushiOkeFull_1.DOWN, state.get(DOWN))
						.with(SushiOkeFull_1.STAGE_1_9, Integer.valueOf(9)), 3); }
		}
		
		if (!kirimi) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
				.with(DOWN, Base_ConnectWater.connectHalf(worldIn, pos, Direction.DOWN));
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (connectWater(worldIn, pos, Direction.DOWN)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.with(DOWN, down);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterIn(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.SUSHIOKE_FULL_1.getDefaultState()
					.with(SushiOkeFull_1.H_FACING, Direction.NORTH)
					.with(SushiOkeFull_1.DOWN, state.get(DOWN))
					.with(SushiOkeFull_1.STAGE_1_9, Integer.valueOf(9))
					.with(SushiOkeFull_1.WATERLOGGED, state.get(WATERLOGGED)), 3); }
		
		else { }
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, STAGE_0_15, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
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
		blockTip.add(new TranslationTextComponent("tips.block_food_sushimeshi").applyTextStyle(TextFormatting.GRAY));
	}
}
