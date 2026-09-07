package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Abstract_Hake;
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
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
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

public class Candle extends Base_ConnectWater {
	/* Property */
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(6.8D, 0.0D, 6.8D, 9.2D, 2.5D, 9.2D);
	private static final VoxelShape AABB_DOWN = Block.makeCuboidShape(6.8D, -8.0D, 6.8D, 9.2D, 0.1D, 9.2D);

	public Candle(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState()
				.with(LIT, Boolean.valueOf(false))
				.with(DOWN, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	public int getLightValue(BlockState state) {
		return state.get(LIT) ? 13 : 0;
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Abstract_Hake) { return ActionResultType.PASS; }

		else {
			if (state.get(LIT)) {
				if (hStack.isEmpty()) {
					CMEvents.soundFireExting(worldIn, pos);
					worldIn.setBlockState(pos, state.with(LIT, Boolean.valueOf(false))); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); } }
			
			else { //!LIT
				if (waterIn(state, worldIn, pos)) {
					CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }

				else { //!WATERLOGGED
					if (hItem == Items.FLINT_AND_STEEL) {
						CMEvents.soundFlint(worldIn, pos);
						CMEvents.toolDamege(1, playerIn, hStack);
						worldIn.setBlockState(pos, state.with(LIT, Boolean.valueOf(true))); }
	
					if (hItem == Items_Teatime.MATCH) {
						CMEvents.consume1_seFlint(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.with(LIT, Boolean.valueOf(true))); }
					
					if (hItem != Items.FLINT_AND_STEEL && hItem != Items_Teatime.MATCH) { 
						CMEvents.textNotHave(worldIn, pos, playerIn); }
				}
			}
			/** SUCCESS to not put anything on top. **/
			return ActionResultType.SUCCESS;
		}
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
		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10); }

		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.with(DOWN, down);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (state.get(LIT) && waterIn(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 10);
			CMEvents.soundFireExting(worldIn, pos);
			worldIn.setBlockState(pos, state.with(LIT, Boolean.valueOf(false))); }
		
		else { }
	}

	/* Create Blockstate */
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, LIT, WATERLOGGED);
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
		return ToolType.PICKAXE;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_andon").applyTextStyle(TextFormatting.GRAY));
	}
}
