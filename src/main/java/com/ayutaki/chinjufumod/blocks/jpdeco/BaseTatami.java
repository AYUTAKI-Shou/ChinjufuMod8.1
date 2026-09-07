package com.ayutaki.chinjufumod.blocks.jpdeco;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Harvest;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Abstract_Hake;
import com.ayutaki.chinjufumod.items.fuel.Seasonal_Slab150;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.state.TatamiType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BaseTatami extends Abstract_Harvest implements IWaterLoggable {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<TatamiType> TYPE = EnumProperty.create("type", TatamiType.class);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	/* Collision */
	private static final VoxelShape AABB_BOTTOM = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape AABB_TOP = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	public BaseTatami(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(TYPE, TatamiType.BOTTOM)
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean flag = (state.get(TYPE) == TatamiType.TOP && hit.getHitVec().y - (double)pos.getY() <= 0.6D);
		boolean slab = (hItem == Items.OAK_SLAB || hItem == Items.SPRUCE_SLAB || hItem == Items.BIRCH_SLAB || 
				hItem == Items.JUNGLE_SLAB || hItem == Items.ACACIA_SLAB || hItem == Items.DARK_OAK_SLAB || hItem instanceof Seasonal_Slab150);
		
		if (hItem instanceof Abstract_Hake) { return ActionResultType.PASS; }
		
		if (flag) {
			if (slab) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.with(TYPE, this.takeType(hItem)), 3);
				return ActionResultType.SUCCESS; }
		}

		return ActionResultType.PASS;
	}
	
	private TatamiType takeType(Item hItem) {
		if (hItem == Items.OAK_SLAB) { return TatamiType.OAK; }
		if (hItem == Items.SPRUCE_SLAB) { return TatamiType.SPRUCE; }
		if (hItem == Items.BIRCH_SLAB) { return TatamiType.BIRCH; }
		if (hItem == Items.JUNGLE_SLAB) { return TatamiType.JUNGLE; }
		if (hItem == Items.ACACIA_SLAB) { return TatamiType.ACACIA; }
		if (hItem == Items.DARK_OAK_SLAB) { return TatamiType.DARKOAK; }
		if (hItem == Items_Seasonal.SAKURA_slabhalf) { return TatamiType.SAKURA; }
		if (hItem == Items_Seasonal.KAEDE_slabhalf) { return TatamiType.KAEDE; }
		else { return TatamiType.ICHOH; }
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState state = worldIn.getBlockState(pos);
		IFluidState fluid = worldIn.getFluidState(pos);
		
		Direction hitFace = context.getFace();
		BlockState botState = this.getDefaultState().with(TYPE, TatamiType.BOTTOM)
				.with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER))
				.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite());
		BlockState topState = this.getDefaultState().with(TYPE, TatamiType.TOP)
				.with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER))
				.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite());
		
		if (state.getBlock() == this) {
			/** Change to TatamiType.DOUBLE. **/
			TatamiType slabType = state.get(TYPE);
			if (slabType == TatamiType.TOP || slabType == TatamiType.BOTTOM) { return state.with(TYPE, TatamiType.DOUBLE); }
			
			else {
				return hitFace != Direction.DOWN && (hitFace == Direction.UP || context.getHitVec().y - (double)pos.getY() <= 0.5D) ? botState : topState; }
		}

		else {

			return hitFace != Direction.DOWN && (hitFace == Direction.UP || context.getHitVec().y - (double)pos.getY() <= 0.5D) ? botState : topState;
		}
	}

	/* Replace to DOUBLE. boolean t/f */
	@Override
	public boolean isReplaceable(BlockState state, BlockItemUseContext useContext) {
		ItemStack hStack = useContext.getItem();
		TatamiType slabType = state.get(TYPE);

		/** This is Not DOUBLE. && You use this ItemBlock. **/
		if ((slabType == TatamiType.TOP || slabType == TatamiType.BOTTOM) && hStack.getItem() == this.asItem()) {

			if (useContext.replacingClickedOnBlock()) {
				boolean flag = useContext.getHitVec().y - (double)useContext.getPos().getY() > 0.5D;
				Direction hitFace = useContext.getFace();

				if (slabType == TatamiType.BOTTOM) { return hitFace == Direction.UP || flag && hitFace.getAxis().isHorizontal(); }
				
				else { return hitFace == Direction.DOWN || !flag && hitFace.getAxis().isHorizontal(); }
			}
			else { return true; }
		}
		else { return false; }
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

	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		return (state.get(TYPE) == TatamiType.TOP || state.get(TYPE) == TatamiType.BOTTOM)? IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn) : false;
	}
	
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return (state.get(TYPE) == TatamiType.TOP || state.get(TYPE) == TatamiType.BOTTOM)? IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn) : false;
	}
	
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
		builder.add(H_FACING, TYPE, WATERLOGGED);
	}

	public boolean isTransparent(BlockState state) {
		return state.get(TYPE) != TatamiType.DOUBLE;
	}

	/* Collisions for each property. */
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		TatamiType slabType = state.get(TYPE);
		switch (slabType) {
		case TOP:
			return AABB_TOP;
		case BOTTOM:
		default:
			return AABB_BOTTOM;
		case DOUBLE:
		case OAK:
		case SPRUCE:
		case BIRCH:
		case JUNGLE:
		case ACACIA:
		case DARKOAK:
		case SAKURA:
		case KAEDE:
		case ICHOH:
			return VoxelShapes.fullCube();
		}
	}

	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		if (state.get(TYPE) == TatamiType.BOTTOM || state.get(TYPE) == TatamiType.TOP) { return false; }
		return true;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		if (state.get(TYPE) == TatamiType.BOTTOM || state.get(TYPE) == TatamiType.TOP) { return false; }
		return true;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		if (state.get(TYPE) != TatamiType.BOTTOM) { return true; }
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
		blockTip.add(new TranslationTextComponent("tips.block_tatami").applyTextStyle(TextFormatting.GRAY));
	}
}
