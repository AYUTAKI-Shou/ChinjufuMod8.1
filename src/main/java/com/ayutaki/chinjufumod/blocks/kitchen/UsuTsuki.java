package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

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
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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

public class UsuTsuki extends Abstract_WaterLogged {
	/* Property 3=empty, kome=4k-5k-6k-7m-8m-9m-10m-11m-12m-13m-14m-15m=comp */
	public static final IntegerProperty STAGE_0_15 = IntegerProperty.create("stage", 0, 15);

	/* Collision */
	private static final VoxelShape AABB_0 = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	private static final VoxelShape AABB_1 = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

	public UsuTsuki(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(STAGE_0_15, Integer.valueOf(3))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_0_15);
		
		if (hItem == Items_Teatime.MUSHIGOME) {
			if (i == 3) {
				CMEvents.mode1Through_Consume(playerIn, hand, Items.BOWL);
				worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);
				worldIn.setBlockState(pos, state.with(STAGE_0_15, Integer.valueOf(4)), 3); }
			
			else { } //i != 3
		}
		
		if (hItem == Items_Teatime.KINE_YOKO && i >= 4 && i <= 15) { return ActionResultType.PASS; }
		
		if (hItem == Items_Wadeco.NOMI && i <= 2) { return ActionResultType.PASS; }
		
		if (hStack.isEmpty()) {
			if (i == 15) { 
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.MOCHI);
				worldIn.setBlockState(pos, state.with(STAGE_0_15, Integer.valueOf(3)), 3); }
			
			else { } //i != 15
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(STAGE_0_15, Integer.valueOf(3)).with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (state.get(WATERLOGGED)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_0_15);
		
		if (i >= 4) {
			if (state.get(WATERLOGGED)) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
				
				CMEvents.drop1_ROTTENFOOD(worldIn, pos);
				worldIn.setBlockState(pos, state.with(STAGE_0_15, Integer.valueOf(3)), 3); }
			
			else { } }
		
		else { }//i <= 3
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_15, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int i = state.get(STAGE_0_15);
		return (i == 0)? AABB_0 : AABB_1;
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(cloneItem(state), 1);
	}
	
	private Item cloneItem(BlockState state) {
		int i = state.get(STAGE_0_15);
		if (i <= 2) { return Items.OAK_LOG; }
		else { return Items_Teatime.USU_TSUKI; }
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
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_usutsuki").applyTextStyle(TextFormatting.GRAY));
	}
}
