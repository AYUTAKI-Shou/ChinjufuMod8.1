package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
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

public class Inagi extends Abstract_WaterLogged {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

	/* Collision */
	private static final VoxelShape BOT_SOUTH = Block.makeCuboidShape(2.0D, 7.0D, 7.25D, 14.0D, 16.0D, 8.75D);
	private static final VoxelShape BOT_WEST = Block.makeCuboidShape(7.25D, 7.0D, 2.0D, 8.75D, 16.0D, 14.0D);
	private static final VoxelShape BOT_NORTH = Block.makeCuboidShape(2.0D, 7.0D, 7.25D, 14.0D, 16.0D, 8.75D);
	private static final VoxelShape BOT_EAST = Block.makeCuboidShape(7.25D, 7.0D, 2.0D, 8.75D, 16.0D, 14.0D);

	private static final VoxelShape TOP_SOUTH = Block.makeCuboidShape(2.0D, 0.0D, 7.25D, 14.0D, 5.5D, 8.75D);
	private static final VoxelShape TOP_WEST = Block.makeCuboidShape(7.25D, 0.0D, 2.0D, 8.75D, 5.5D, 14.0D);
	private static final VoxelShape TOP_NORTH = Block.makeCuboidShape(2.0D, 0.0D, 7.25D, 14.0D, 5.5D, 8.75D);
	private static final VoxelShape TOP_EAST = Block.makeCuboidShape(7.25D, 0.0D, 2.0D, 8.75D, 5.5D, 14.0D);

	private static final VoxelShape S4_SOUTH = Block.makeCuboidShape(0.0D, 3.0D, 7.25D, 16.0D, 4.5D, 8.75D);
	private static final VoxelShape S4_WEST = Block.makeCuboidShape(7.25D, 3.0D, 0.0D, 8.75D, 4.5D, 16.0D);
	private static final VoxelShape S4_NORTH = Block.makeCuboidShape(0.0D, 3.0D, 7.25D, 16.0D, 4.5D, 8.75D);
	private static final VoxelShape S4_EAST = Block.makeCuboidShape(7.25D, 3.0D, 0.0D, 8.75D, 4.5D, 16.0D);

	public Inagi(Block.Properties props) {
		super(props);

		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(HALF, DoubleBlockHalf.LOWER)
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_4);
		/** 1=Empty, 2=Wet, 3=Wet, 4=Dry **/
		
		BlockState lowFace = this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER).with(H_FACING, state.get(H_FACING));
		BlockState upFace = this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(H_FACING, state.get(H_FACING));
		int gHC = hStack.getCount();

		if (state.get(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { //!WATERLOGGED
			/** Set rice plant **/
			if (i == 1) {
				
				if (hItem == Items_Teatime.INE && gHC >= 8) {
					/* Consume 8 Items. */
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					
					if (state.get(HALF) == DoubleBlockHalf.UPPER) {
						worldIn.setBlockState(pos, upFace.with(STAGE_1_4, Integer.valueOf(2)), 2);
						worldIn.setBlockState(pos.down(), lowFace.with(STAGE_1_4, Integer.valueOf(2)), 2); } }
				
				if (hItem == Items_Teatime.INE && gHC < 8) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
				
				if (hItem != Items_Teatime.INE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
				
			/** Too early to collect **/
			if (i == 2 || i == 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }	
			
			/** Can harvest **/
			if (i == 4) {
				if (hStack.isEmpty() && hItem != Items_Teatime.INE) {
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.INE_D, 8);
					CMEvents.addEXP(2, worldIn, pos);

					if (state.get(HALF) == DoubleBlockHalf.UPPER) {
						worldIn.setBlockState(pos, upFace.with(STAGE_1_4, Integer.valueOf(1)), 2);
						worldIn.setBlockState(pos.down(), lowFace.with(STAGE_1_4, Integer.valueOf(1)), 2); }

					if (state.get(HALF) != DoubleBlockHalf.UPPER) {
						worldIn.setBlockState(pos, lowFace.with(STAGE_1_4, Integer.valueOf(1)), 2);
						worldIn.setBlockState(pos.up(), upFace.with(STAGE_1_4, Integer.valueOf(2)), 2); } }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
			}
		} // It is not waterlogged.
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);

		/** pos.up() = Replaceable block. **/
		if (pos.getY() < 255 && worldIn.getBlockState(pos.up()).isReplaceable(context)) {
			return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
					.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
					.with(STAGE_1_4, Integer.valueOf(1))
					.with(HALF, DoubleBlockHalf.LOWER); }

		else { return null; }
	}

	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		IFluidState fluidUp = worldIn.getFluidState(pos.up());

		worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(H_FACING, state.get(H_FACING))
				.with(WATERLOGGED, Boolean.valueOf(fluidUp.isTagged(FluidTags.WATER))), 3);
	}
	
	/* Limit the place. */
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return true;
	}

	@SuppressWarnings("deprecation")
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.down();
		BlockState downState = worldIn.getBlockState(downPos);
		
		if (state.get(HALF) != DoubleBlockHalf.UPPER) {
			return this.isValidGround(downState, worldIn, downPos); }

		else {
			if (state.getBlock() != this) return super.isValidPosition(state, worldIn, pos);
			return downState.getBlock() == this && downState.get(HALF) == DoubleBlockHalf.LOWER; }
	}

	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(H_FACING, rotation.rotate(state.get(H_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(H_FACING)));
	}

	@OnlyIn(Dist.CLIENT)
	public long getPositionRandom(BlockState state, BlockPos pos) {
		return MathHelper.getCoordinateRandom(pos.getX(), pos.down(state.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {

		BlockState state1 = super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
		if (!state1.isAir(worldIn, pos)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		DoubleBlockHalf half = state.get(HALF);
		if (facing.getAxis() == Direction.Axis.Y && half == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
			return (newState.getBlock() == Crop_Blocks.INAGI) &&
					newState.get(HALF) != half ? state.with(H_FACING, newState.get(H_FACING)).with(STAGE_1_4, newState.get(STAGE_1_4)) : Blocks.AIR.getDefaultState();
		}
		else {
			return half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.isValidPosition(worldIn, pos) ? Blocks.AIR
					.getDefaultState() : super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos); }
	}

	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_4);
		DoubleBlockHalf half = state.get(HALF);
		
		BlockState lowFace = this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER).with(H_FACING, state.get(H_FACING));
		BlockState upFace = this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(H_FACING, state.get(H_FACING));
		
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		switch (half) {
		case LOWER:
		default:
			if (state.get(WATERLOGGED) && rand.nextInt(4) == 0) {
				if (i == 1) { }
	
				else { //i != 1
					CMEvents.drop1_ROTTENFOOD(worldIn, pos);
					worldIn.setBlockState(pos, lowFace.with(STAGE_1_4, Integer.valueOf(1)), 3);
					worldIn.setBlockState(pos.up(), upFace.with(STAGE_1_4, Integer.valueOf(1)), 3); } }
			
			if (!state.get(WATERLOGGED)) { }
			break;

		case UPPER:
			if (!state.get(WATERLOGGED) && !worldIn.isRainingAt(pos.up()) && rand.nextInt(4) == 0) {
				if (i == 1 || i == 4) { }
				
				else {
					worldIn.setBlockState(pos, upFace.with(STAGE_1_4, Integer.valueOf(i + 1)), 3);
					worldIn.setBlockState(pos.down(), lowFace.with(STAGE_1_4, Integer.valueOf(i + 1)), 3); } }
	
			/** WATERLOGGED **/
			if (state.get(WATERLOGGED) && rand.nextInt(4) == 0) {
				if (i == 1) { }
	
				else { //i != 1
					CMEvents.drop1_ROTTENFOOD(worldIn, pos);
					worldIn.setBlockState(pos, upFace.with(STAGE_1_4, Integer.valueOf(1)), 3);
					worldIn.setBlockState(pos.down(), lowFace.with(STAGE_1_4, Integer.valueOf(1)), 3); } }
			break;
		} // switch LOWER-UPPER
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		int i = state.get(STAGE_1_4);

		switch (direction) {
		case NORTH:
		default:
			return (state.get(HALF) != DoubleBlockHalf.UPPER && i == 1)? VoxelShapes.empty() :
							((state.get(HALF) != DoubleBlockHalf.UPPER && i != 1)? BOT_NORTH :
							((state.get(HALF) == DoubleBlockHalf.UPPER && i == 1)? S4_NORTH : TOP_NORTH));
		case SOUTH:
			return (state.get(HALF) != DoubleBlockHalf.UPPER && i == 1)? VoxelShapes.empty() :
							((state.get(HALF) != DoubleBlockHalf.UPPER && i != 1)? BOT_SOUTH :
							((state.get(HALF) == DoubleBlockHalf.UPPER && i == 1)? S4_SOUTH : TOP_SOUTH));
		case WEST:
			return (state.get(HALF) != DoubleBlockHalf.UPPER && i == 1)? VoxelShapes.empty() :
							((state.get(HALF) != DoubleBlockHalf.UPPER && i != 1)? BOT_WEST :
							((state.get(HALF) == DoubleBlockHalf.UPPER && i == 1)? S4_WEST : TOP_WEST));
		case EAST:
			return (state.get(HALF) != DoubleBlockHalf.UPPER && i == 1)? VoxelShapes.empty() :
							((state.get(HALF) != DoubleBlockHalf.UPPER && i != 1)? BOT_EAST :
							((state.get(HALF) == DoubleBlockHalf.UPPER && i == 1)? S4_EAST : TOP_EAST));
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, HALF, STAGE_1_4, WATERLOGGED);
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.INAGI);
	}
	
	/* Only for INAGI. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		BlockState upState = worldIn.getBlockState(pos.up());
		BlockState downState = worldIn.getBlockState(pos.down());
		int i = state.get(STAGE_1_4);

		if (downState.getBlock() == this && !playerIn.isCreative()) {
			worldIn.destroyBlock(pos.down(), false);
			if (i == 4) { CMEvents.addEXP(2, worldIn, pos); }
		}

		if (upState.getBlock() == this && !playerIn.isCreative()) {
			worldIn.destroyBlock(pos.up(), false);
			if (i == 4) { CMEvents.addEXP(2, worldIn, pos); }
		}

		if (playerIn.isCreative()) { worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 35); }
		super.onBlockHarvested(worldIn, pos, state, playerIn);
	}

	@Override
	public void harvestBlock(World worldIn, PlayerEntity playerIn, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, playerIn, pos, Blocks.AIR.getDefaultState(), te, stack);
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

	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
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
		blockTip.add(new TranslationTextComponent("tips.block_inagi").applyTextStyle(TextFormatting.GRAY));
	}
}
