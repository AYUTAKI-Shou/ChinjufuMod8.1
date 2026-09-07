package com.ayutaki.chinjufumod.blocks.jpdeco;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.items.fuel.Seasonal_Slab150;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.state.TatamiType;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseTatami extends Block implements SimpleWaterloggedBlock {
	public static final MapCodec<BaseTatami> CODEC = simpleCodec(BaseTatami::new);
	@Override
	public MapCodec<? extends BaseTatami> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final EnumProperty<TatamiType> TYPE = EnumProperty.create("type", TatamiType.class);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	/* Collision */
	private static final VoxelShape AABB_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape AABB_TOP = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	public BaseTatami(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(TYPE, TatamiType.BOTTOM)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		boolean flag = (state.getValue(TYPE) == TatamiType.TOP && hit.getLocation().y - (double)pos.getY() <= 0.6D);
		boolean slab = (hItem == Items.OAK_SLAB || hItem == Items.SPRUCE_SLAB || hItem == Items.BIRCH_SLAB || 
				hItem == Items.JUNGLE_SLAB || hItem == Items.ACACIA_SLAB || hItem == Items.DARK_OAK_SLAB || 
				hItem == Items.MANGROVE_SLAB || hItem == Items.CHERRY_SLAB ||
				hItem instanceof Seasonal_Slab150);
		
		if (hItem instanceof Base_Hake) { return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; }
		
		if (flag) {
			if (slab) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, state.setValue(TYPE, this.takeType(hItem)), 3);
				return ItemInteractionResult.SUCCESS; }
		}
		
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}
	
	private TatamiType takeType(Item hItem) {
		if (hItem == Items.OAK_SLAB) { return TatamiType.OAK; }
		if (hItem == Items.SPRUCE_SLAB) { return TatamiType.SPRUCE; }
		if (hItem == Items.BIRCH_SLAB) { return TatamiType.BIRCH; }
		if (hItem == Items.JUNGLE_SLAB) { return TatamiType.JUNGLE; }
		if (hItem == Items.ACACIA_SLAB) { return TatamiType.ACACIA; }
		if (hItem == Items.DARK_OAK_SLAB) { return TatamiType.DARKOAK; }
		if (hItem == Items.MANGROVE_SLAB) {return TatamiType.MANGROVE; }
		if (hItem == Items.CHERRY_SLAB) {return TatamiType.CHERRY; }
		
		if (hItem == Items_Seasonal.SAKURA_slabhalf.get()) { return TatamiType.SAKURA; }
		if (hItem == Items_Seasonal.KAEDE_slabhalf.get()) { return TatamiType.KAEDE; }
		else { return TatamiType.ICHOH; }
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = worldIn.getBlockState(pos);
		FluidState fluid = worldIn.getFluidState(pos);

		Direction hitFace = context.getClickedFace();
		BlockState botState = this.defaultBlockState().setValue(TYPE, TatamiType.BOTTOM)
				.setValue(WATERLOGGED, Boolean.valueOf(Boolean.valueOf(fluid.getType() == Fluids.WATER)))
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
		BlockState topState = this.defaultBlockState().setValue(TYPE, TatamiType.TOP)
				.setValue(WATERLOGGED, Boolean.valueOf(Boolean.valueOf(fluid.getType() == Fluids.WATER)))
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
		
		if (state.is(this)) {
			/** Change to TatamiType.DOUBLE. **/
			TatamiType slabType = state.getValue(TYPE);
			if (slabType == TatamiType.TOP || slabType == TatamiType.BOTTOM) { return state.setValue(TYPE, TatamiType.DOUBLE); }

			else {
				return hitFace != Direction.DOWN && (hitFace == Direction.UP || context.getClickLocation().y - (double)pos.getY() <= 0.5D) ? botState : topState; }
		}
		
		else {
			return hitFace != Direction.DOWN && (hitFace == Direction.UP || context.getClickLocation().y - (double)pos.getY() <= 0.5D) ? botState : topState;
		}
	}

	/* Replace to DOUBLE. boolean t/f */
	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		ItemStack hStack = context.getItemInHand();
		TatamiType slabType = state.getValue(TYPE);

		if ((slabType == TatamiType.TOP || slabType == TatamiType.BOTTOM) && hStack.getItem() == this.asItem()) {

			if (context.replacingClickedOnBlock()) {
				boolean flag = context.getClickLocation().y - (double)context.getClickedPos().getY() > 0.5D;
				Direction hitFace = context.getClickedFace();

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
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}
	
	/* Waterlogged */
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	@Override
	public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluid) {
		return (state.getValue(TYPE) == TatamiType.TOP || state.getValue(TYPE) == TatamiType.BOTTOM) ? SimpleWaterloggedBlock.super.placeLiquid(worldIn, pos, state, fluid) : false;
	}

	@Override
	public boolean canPlaceLiquid(@Nullable Player playerIn, BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return (state.getValue(TYPE) == TatamiType.TOP || state.getValue(TYPE) == TatamiType.BOTTOM) ? SimpleWaterloggedBlock.super.canPlaceLiquid(playerIn, worldIn, pos, state, fluid) : false;
	}
	
	@Override
	public ItemStack pickupBlock(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(BlockStateProperties.WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)), 3);
			
			if (!state.canSurvive(worldIn, pos)) { worldIn.destroyBlock(pos, true); }
			return new ItemStack(Items.WATER_BUCKET);
		}
		
		else { return ItemStack.EMPTY; }
	} // for 20.2
	
	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
	}

	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	@Override
	public boolean isPathfindable(BlockState state, PathComputationType type) {
		switch (type) {
		case LAND:
			return false;
		case WATER:
			return state.getFluidState().is(FluidTags.WATER);
		case AIR:
			return false;
		default:
			return false;
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, TYPE, WATERLOGGED);
	}
	
	@Override
	public boolean useShapeForLightOcclusion(BlockState state) {
		return (state.getValue(TYPE) == TatamiType.TOP || state.getValue(TYPE) == TatamiType.BOTTOM);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		TatamiType slabType = state.getValue(TYPE);
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
		case MANGROVE:
		case CHERRY:
		case SAKURA:
		case KAEDE:
		case ICHOH:
			return Shapes.block();
		}
	}
	
	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_tatami").withStyle(ChatFormatting.GRAY));
	}
}
