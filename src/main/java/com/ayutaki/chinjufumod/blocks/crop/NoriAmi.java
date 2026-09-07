package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Harvest;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
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
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class NoriAmi extends Abstract_Harvest {
	/* Property */
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(0.0D, -5.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public NoriAmi(Block.Properties props) {
		super(props);
		setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(AGE);

		/** Too early to collect **/
		if (i < 5) {
			if (hStack.isEmpty()) {
				CMEvents.textEarlyCollect(worldIn, pos, playerIn);
				return ActionResultType.SUCCESS; }
			
			else { //!empty
				return ActionResultType.PASS; }
		}
		
		/** Can harvest **/
		if (i >= 5) {
			if (hStack.isEmpty()) {
				if (i == 5 || i == 6) { 
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.NORI_N); }
				if (i == 7) {
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.NORI_N, 2); }
		
				worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(0)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
			
			return ActionResultType.SUCCESS;
		}
		
		/** To put it on the side of this, PASS. **/
		return ActionResultType.PASS;
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState();
	}

	/* Limit the place. */
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() == Blocks.WATER;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.down();
		if (worldIn.getLightSubtracted(pos, 0) >= 8 || worldIn.canSeeSky(pos)) {
			return this.isValidGround(worldIn.getBlockState(downPos), worldIn, downPos); }
		
		return false;
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		return facing == Direction.DOWN && !state.isValidPosition(worldIn, pos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(AGE);

		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		if (i < 7 && worldIn.getLightSubtracted(pos, 0) >= 9 && rand.nextInt(6) == 0) {
			worldIn.setBlockState(pos, state.with(AGE, Integer.valueOf(i + 1))); }

		else { }
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
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
		blockTip.add(new TranslationTextComponent("tips.block_noriami").applyTextStyle(TextFormatting.GRAY));
	}
}
