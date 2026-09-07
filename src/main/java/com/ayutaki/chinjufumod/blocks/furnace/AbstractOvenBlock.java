package com.ayutaki.chinjufumod.blocks.furnace;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.AbstractOvenTileEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
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
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public abstract class AbstractOvenBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
	protected abstract MapCodec<? extends AbstractOvenBlock> codec();
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected AbstractOvenBlock(BlockBehaviour.Properties props) {
		super(props);
		this.registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(LIT, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player playerIn, BlockHitResult hit) {
		if (worldIn.isClientSide) { return InteractionResult.SUCCESS; } 
		
		else {
			if (state.getValue(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
			
			else { //!WATERLOGGED
				Direction direction = state.getValue(AbstractOvenBlock.H_FACING);
				
				switch (direction) {
				case NORTH:
				default:
					if ((worldIn.getBlockState(pos.north()).canBeReplaced() || worldIn.getBlockState(pos.north()).getBlock() instanceof CarpetBlock) &&
							(worldIn.getBlockState(pos.south()).canBeReplaced() || worldIn.getBlockState(pos.south()).getBlock() instanceof CarpetBlock ||
									worldIn.getBlockState(pos.south()).getBlock() == School_Blocks.STOVECHIMNEY_joint.get())) {
	
						CMEvents.soundOpenOven(worldIn, pos);
						this.openContainer(worldIn, pos, playerIn); }
					
					else { CMEvents.textIsBlocked(worldIn, pos, playerIn); }
					break;
	
				case SOUTH:
					if ((worldIn.getBlockState(pos.south()).canBeReplaced() || worldIn.getBlockState(pos.south()).getBlock() instanceof CarpetBlock) &&
							(worldIn.getBlockState(pos.north()).canBeReplaced() || worldIn.getBlockState(pos.north()).getBlock() instanceof CarpetBlock ||
									worldIn.getBlockState(pos.north()).getBlock() == School_Blocks.STOVECHIMNEY_joint.get())) {
	
						CMEvents.soundOpenOven(worldIn, pos);
						this.openContainer(worldIn, pos, playerIn); }
					
					else { CMEvents.textIsBlocked(worldIn, pos, playerIn); }
					break;
	
				case EAST:
					if ((worldIn.getBlockState(pos.east()).canBeReplaced() || worldIn.getBlockState(pos.east()).getBlock() instanceof CarpetBlock) &&
							(worldIn.getBlockState(pos.west()).canBeReplaced() || worldIn.getBlockState(pos.west()).getBlock() instanceof CarpetBlock ||
									worldIn.getBlockState(pos.west()).getBlock() == School_Blocks.STOVECHIMNEY_joint.get())) {
	
					CMEvents.soundOpenOven(worldIn, pos);
					this.openContainer(worldIn, pos, playerIn); }
					
					else { CMEvents.textIsBlocked(worldIn, pos, playerIn); }
					break;
					
				case WEST:
					if ((worldIn.getBlockState(pos.west()).canBeReplaced() || worldIn.getBlockState(pos.west()).getBlock() instanceof CarpetBlock) &&
							(worldIn.getBlockState(pos.east()).canBeReplaced() || worldIn.getBlockState(pos.east()).getBlock() instanceof CarpetBlock ||
									worldIn.getBlockState(pos.east()).getBlock() == School_Blocks.STOVECHIMNEY_joint.get())) {
	
						CMEvents.soundOpenOven(worldIn, pos);
						this.openContainer(worldIn, pos, playerIn); }
					
					else { CMEvents.textIsBlocked(worldIn, pos, playerIn); }
					break;
				} // switch
			}
			return InteractionResult.CONSUME;
		}
	}

	protected abstract void openContainer(Level worldIn, BlockPos pos, Player playerIn);

	@Override
	protected void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean flag) {
		if (!state.is(newState.getBlock())) {
			
			BlockEntity blockentity = worldIn.getBlockEntity(pos);
			if (blockentity instanceof AbstractOvenTileEntity oven) {
				
				if (worldIn instanceof ServerLevel server) {
					Containers.dropContents(worldIn, pos, oven);
					oven.getRecipesToAwardAndPopExperience(server, Vec3.atCenterOf(pos));
				}

				super.onRemove(state, worldIn, pos, newState, flag);
				worldIn.updateNeighbourForOutputSignal(pos, this); } 
			
			else { super.onRemove(state, worldIn, pos, newState, flag);
			}
		}
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected int getAnalogOutputSignal(BlockState state, Level worldIn, BlockPos pos) {
		return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(worldIn.getBlockEntity(pos));
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
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
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Waterlogged */
	@Override
	protected FluidState getFluidState(BlockState state) {
			return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	protected boolean propagatesSkylightDown(BlockState state) {
		return !state.getValue(WATERLOGGED);
	}
	
	@Override
	public boolean canPlaceLiquid(@Nullable Player playerIn, BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return fluid == Fluids.WATER;
	}

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
	}

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
		builder.add(H_FACING, LIT, WATERLOGGED);
	}

	/* If you walk on it while it's burning, you'll take damage. */
	@SuppressWarnings("deprecation")
	public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
		if (state.getValue(LIT)) {
			if (!entityIn.fireImmune() && entityIn instanceof LivingEntity) {
				entityIn.hurt(worldIn.damageSources().hotFloor(), 1.0F); }
			
			super.stepOn(worldIn, pos, state, entityIn); }
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;

		if (state.getValue(LIT)) {
				if (rand.nextDouble() < 0.1D) {
					worldIn.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false); }
		}
	}
	
	@Nullable
	protected static <T extends BlockEntity> BlockEntityTicker<T> createFurnaceTicker(Level worldIn, BlockEntityType<T> tileEntity, BlockEntityType<? extends AbstractOvenTileEntity> abstractEntity) {
		return (worldIn instanceof ServerLevel server)? createTickerHelper(tileEntity, abstractEntity, (level, pos, state, E) -> AbstractOvenTileEntity.serverTick(server, pos, state, E)) : null;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_kit_oven").withStyle(ChatFormatting.GRAY));
	}
}
