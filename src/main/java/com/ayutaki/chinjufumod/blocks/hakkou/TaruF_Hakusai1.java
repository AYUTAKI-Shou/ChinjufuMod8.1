package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
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

public class TaruF_Hakusai1 extends Base_Stage05 {
	/* Collision */
	private static final VoxelShape AABB_BOX = VoxelShapes.or(Block.makeCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.25D, 8.0D, 0.25D, 15.75D, 12.0D, 15.75D),
			Block.makeCuboidShape(0.5D, 4.0D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.makeCuboidShape(0.75D, 0.0D, 0.75D, 15.25D, 4.0D, 15.25D));

	public TaruF_Hakusai1(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_0_5);

		/** Can harvest **/
		if (i == 5) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.FOOD_HAKUSAI2);
				worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOU_TARU.getDefaultState()
						.with(Taru_Hakkou.STAGE_0_5, Integer.valueOf(4))); }
		
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
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
					.with(Taru_Hakkou.STAGE_0_5, Integer.valueOf(4))
					.with(Taru_Hakkou.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		int i = state.get(STAGE_0_5);
		if (i < 5) {
			if (rand.nextInt(2) == 0) {
				worldIn.setBlockState(pos, state.with(STAGE_0_5, Integer.valueOf(i + 1))); } }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_taru_hakusai_f").applyTextStyle(TextFormatting.GRAY));
	}
}
