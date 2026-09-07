package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
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
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class Chanoki extends Abstract_WaterLogged {
	/* Property */
	public static final IntegerProperty STAGE_0_7 = IntegerProperty.create("stage", 0, 7);

	/* Collision */
	private static final VoxelShape AABB_BASE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 0.1D, 14.0D);
	
	private static final VoxelShape AABB_0 = VoxelShapes.or(AABB_BASE, Block.makeCuboidShape(7.0D, 0.1D, 7.0D, 9.0D, 12.0D, 9.0D));
	private static final VoxelShape AABB_1 = VoxelShapes.or(AABB_BASE, Block.makeCuboidShape(3.0D, 4.0D, 3.0D, 13.0D, 13.0D, 13.0D),
			Block.makeCuboidShape(7.0D, 0.1D, 7.0D, 9.0D, 4.0D, 9.0D));
	private static final VoxelShape AABB_2 = VoxelShapes.or(AABB_BASE, Block.makeCuboidShape(2.0D, 4.0D, 2.0D, 14.0D, 14.0D, 14.0D),
			Block.makeCuboidShape(7.0D, 0.1D, 7.0D, 9.0D, 4.0D, 9.0D));
	private static final VoxelShape AABB_3 = VoxelShapes.or(AABB_BASE, Block.makeCuboidShape(1.5D, 4.0D, 1.5D, 14.5D, 14.5D, 14.5D),
			Block.makeCuboidShape(7.0D, 0.1D, 7.0D, 9.0D, 4.0D, 9.0D));
	private static final VoxelShape AABB_4 = VoxelShapes.or(AABB_BASE, Block.makeCuboidShape(1.0D, 3.75D, 1.0D, 15.0D, 15.0D, 15.0D),
			Block.makeCuboidShape(7.0D, 0.1D, 7.0D, 9.0D, 4.0D, 9.0D));
	private static final VoxelShape AABB_5 = VoxelShapes.or(AABB_BASE, Block.makeCuboidShape(0.5D, 3.5D, 0.5D, 15.5D, 15.5D, 15.5D),
			Block.makeCuboidShape(7.0D, 0.1D, 7.0D, 9.0D, 3.5D, 9.0D));
	private static final VoxelShape AABB_7 = VoxelShapes.or(AABB_BASE, Block.makeCuboidShape(0.0D, 3.5D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(7.0D, 0.1D, 7.0D, 9.0D, 3.5D, 9.0D));

	public Chanoki(Block.Properties props) {
		super(props);
		setDefaultState(this.stateContainer.getBaseState().with(STAGE_0_7, Integer.valueOf(0))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_0_7);

		/** Can harvest **/
		if (i == 7) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.CHABA, 4);
				worldIn.setBlockState(pos, state.with(STAGE_0_7, Integer.valueOf(5)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 7
			if (hItem == Items.BONE_MEAL) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
				
				if (i < 6) { worldIn.setBlockState(pos, state.with(STAGE_0_7, Integer.valueOf(i + 2)), 3); }
				if (i == 6) { worldIn.setBlockState(pos, state.with(STAGE_0_7, Integer.valueOf(i + 1)), 3); }
			}
			
			if (hItem != Items.BONE_MEAL) {
				if (hStack.isEmpty()) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }	
				
				else { //!empty
					CMEvents.textNotHave(worldIn, pos, playerIn); } }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
	
	/* Limit the place. */
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return (state.getMaterial() == Material.EARTH && state.getMaterial().isSolid());
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.down();
		return this.isValidGround(worldIn.getBlockState(downPos), worldIn, downPos);
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);

		/** pos.up() = Replaceable block. **/
		if (pos.getY() < 255 && worldIn.getBlockState(pos.up()).isReplaceable(context)) {
			return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER); }

		else { return null; }
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }
		
		return facing == Direction.DOWN && !state.isValidPosition(worldIn, pos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom. Slow growth rate. rand.nextInt(12) == 0 */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		int i = state.get(STAGE_0_7);
		if (i != 7 && worldIn.getLightSubtracted(pos, 0) >= 9 && !state.get(WATERLOGGED)) {
			if (rand.nextInt(8) == 0) {
				worldIn.setBlockState(pos, state.with(STAGE_0_7, Integer.valueOf(i + 1)), 2); } }

		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int i = state.get(STAGE_0_7);
		
		switch (i) {
		case 0:
		default: return AABB_0;
		case 1: return AABB_1;
		case 2: return AABB_2;
		case 3: return AABB_3;
		case 4: return AABB_4;
		case 5:
		case 6: return AABB_5;
		case 7: return AABB_7;
		} // switch
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_7, WATERLOGGED);
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.CHANOKI);
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
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_wood_chanoki_nae").applyTextStyle(TextFormatting.GRAY));
	}
}
