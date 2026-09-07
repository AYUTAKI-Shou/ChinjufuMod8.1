package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Slab_JP;
import com.ayutaki.chinjufumod.blocks.jpdeco.BaseTatami;
import com.ayutaki.chinjufumod.blocks.unitblock.Chabudai;
import com.ayutaki.chinjufumod.blocks.unitblock.Kotatsu;
import com.ayutaki.chinjufumod.blocks.unitblock.LowDesk;
import com.ayutaki.chinjufumod.blocks.wood.WoodSlab_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.state.TatamiType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
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

public class TengusaWashed extends Base_DownStage5 {

	public TengusaWashed(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_5);

		/** Can harvest **/
		if (i == 5) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.TENGUSA_DRY, 2);
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Limit the place. */
	private boolean isSlabBot(IBlockReader worldIn, BlockPos pos) {
		BlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		return ((block instanceof SlabBlock && state.get(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof WoodSlab_CM && state.get(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseFacingSlab_Water && state.get(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof Base_Slab_JP && state.get(Base_Slab_JP.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseTatami && state.get(BaseTatami.TYPE) == TatamiType.BOTTOM) ||
				block instanceof LowDesk || block instanceof Chabudai || block instanceof Kotatsu);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.down();
		return this.isSlabBot(worldIn, downPos) || Block.hasEnoughSolidSide(worldIn, downPos, Direction.UP);
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		BlockState blockState = super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
		
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (connectWater(worldIn, pos, Direction.DOWN)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100); }

		return !state.isValidPosition(worldIn, pos) ? Blocks.AIR.getDefaultState() : blockState;
	}
	
	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 100);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);}
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		boolean seeSUN = worldIn.canSeeSky(pos.up()) && !worldIn.isRainingAt(pos.up()) && worldIn.isDaytime();
		if (seeSUN) {
			
			int i = state.get(STAGE_1_5);
			boolean canDRY = worldIn.getLightSubtracted(pos, 0) >= 15 && i != 5;
			if (canDRY) {
				if (rand.nextInt(6) == 0) { 
					worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(i + 1)), 2); } }
		}
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.item_crop_tengusawash").applyTextStyle(TextFormatting.GRAY));
	}
}
