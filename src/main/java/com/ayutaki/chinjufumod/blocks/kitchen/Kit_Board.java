package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.gui.KitBoardMenu;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/* CraftingTableBlock + facing Property */
public class Kit_Board extends CraftingTableBlock implements SimpleWaterloggedBlock {
	public static final MapCodec<Kit_Board> CODEC = simpleCodec(Kit_Board::new);
	@Override
	public MapCodec<? extends Kit_Board> codec() { return CODEC; }
	
	private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	/* Collision */
	private static final VoxelShape TOP = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	private static final VoxelShape AABB_SOUTH = Shapes.or(TOP, Block.box(0.0D, 0.0D, 1.0D, 16.0D, 15.0D, 15.0D));
	private static final VoxelShape AABB_WEST = Shapes.or(TOP, Block.box(1.0D, 0.0D, 0.0D, 15.0D, 15.0D, 16.0D));
	private static final VoxelShape AABB_NORTH = Shapes.or(TOP, Block.box(0.0D, 0.0D, 1.0D, 16.0D, 15.0D, 15.0D));
	private static final VoxelShape AABB_EAST = Shapes.or(TOP, Block.box(1.0D, 0.0D, 0.0D, 15.0D, 15.0D, 16.0D));

	public Kit_Board(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* RightClick Action */
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player playerIn, BlockHitResult hit) {
		if (worldIn.isClientSide) { return InteractionResult.SUCCESS; }
		
		else {
			/** Open UI. **/
			playerIn.openMenu(state.getMenuProvider(worldIn, pos));
			playerIn.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			
			worldIn.playSound(null, pos, SoundEvents_CM.KITCHEN_CUT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
			return InteractionResult.CONSUME;
		}
	}
	
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		return new SimpleMenuProvider((id, inventory, entity) -> {
			return new KitBoardMenu(id, inventory, ContainerLevelAccess.create(worldIn, pos)); }, CONTAINER_TITLE);
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
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
	public boolean canPlaceLiquid(@Nullable Player playerIn, BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return fluid == Fluids.WATER;
	} // for 20.2
	
	@Override
	public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluid) {
		if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluid.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.scheduleTick(pos, fluid.getType(), fluid.getType().getTickDelay(worldIn)); }
			return true; }
		
		else { return false; }
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
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_kit_board").withStyle(ChatFormatting.GRAY));
	}
}
