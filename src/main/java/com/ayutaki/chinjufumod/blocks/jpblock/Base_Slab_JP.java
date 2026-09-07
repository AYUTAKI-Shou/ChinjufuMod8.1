package com.ayutaki.chinjufumod.blocks.jpblock;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Harvest;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class Base_Slab_JP extends Abstract_Harvest implements IWaterLoggable {
	/* Property */
	public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
	public static final BooleanProperty CRACK = BooleanProperty.create("cra");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	/* Collision */
	private static final VoxelShape AABB_BOTTOM = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape AABB_TOP = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public Base_Slab_JP(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(TYPE, SlabType.BOTTOM)
			.with(CRACK, Boolean.valueOf(false))
			.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);

		if (hStack.isEmpty() && playerIn.isSneaking()) {
			CMEvents.soundStonePlace(worldIn, pos);
			worldIn.setBlockState(pos, state.cycle(CRACK));
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState state = worldIn.getBlockState(pos);
		IFluidState fluid = worldIn.getFluidState(pos);

		if (state.getBlock() == this) {
			/** Change to SlabType.DOUBLE. **/
			return state.with(TYPE, SlabType.DOUBLE); }

		else {
			BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER))
					.with(CRACK, Boolean.valueOf(false));
			Direction hitFace = context.getFace();

			return hitFace != Direction.DOWN && (hitFace == Direction.UP || context.getHitVec().y - (double)pos.getY() <= 0.5D) ? blockState2 : blockState2.with(TYPE, SlabType.TOP);
		}
	}

	/* Replace to DOUBLE. boolean t/f */
	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		ItemStack hStack = useContext.getItem();
		SlabType slabType = state.get(TYPE);

		/** This is Not DOUBLE. && You use this ItemBlock. **/
		if (slabType != SlabType.DOUBLE && hStack.getItem() == this.asItem()) {

			if (useContext.replacingClickedOnBlock()) {
				boolean flag = useContext.getHitVec().y - (double)useContext.getPos().getY() > 0.5D;
				Direction hitFace = useContext.getFace();

				if (slabType == SlabType.BOTTOM) { return hitFace == Direction.UP || flag && hitFace.getAxis().isHorizontal(); }

				else { return hitFace == Direction.DOWN || !flag && hitFace.getAxis().isHorizontal(); }
			}
			else { return true; }
		}
		else { return false; }
	}

	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		return state.get(TYPE) != SlabType.DOUBLE ? IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn) : false;
	}
	
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return state.get(TYPE) != SlabType.DOUBLE ? IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn) : false;
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }
		
		return super.updatePostPlacement(stateIn, facing, newState, worldIn, pos, newPos);
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		switch(type) {
		case LAND:
			return false;
		case WATER:
			return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
		case AIR:
			return false;
		default:
			return false;
		}
	}

	/* Create Blockstate */
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(CRACK, TYPE, WATERLOGGED);
	}

	public boolean isTransparent(BlockState state) {
		return state.get(TYPE) != SlabType.DOUBLE;
	}

	/* Collisions for each property. */
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		SlabType slabType = state.get(TYPE);
		switch(slabType) {
		case DOUBLE:
			return VoxelShapes.fullCube();
		case TOP:
			return AABB_TOP;
		default:
			return AABB_BOTTOM;
		}
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
}
