package com.ayutaki.chinjufumod.blocks.wood;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Wood_Blocks;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class KuriIga_Fall extends FallingBlock implements SimpleWaterloggedBlock {
	public static final MapCodec<KuriIga_Fall> CODEC = simpleCodec(KuriIga_Fall::new);
	@Override
	public MapCodec<? extends KuriIga_Fall> codec() { return CODEC; }
	
	/* Property */
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	/* Collision */
	protected static final float AABB_OFFSET = 6.0F;
	private static final VoxelShape AABB_BOX = Block.box(6.0D, -1.0D, 6.0D, 10.0D, 3.5D, 10.0D);

	public KuriIga_Fall(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
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

	/* Update BlockState. */
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	protected boolean onBush(BlockState state, BlockGetter worldIn, BlockPos pos) {
		Block downBlock = worldIn.getBlockState(pos.below()).getBlock();
		return downBlock instanceof BushBlock || downBlock instanceof KuriIga_Fall;
	}
	
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		if (waterIn(state)) { worldIn.scheduleTick(pos, Wood_Blocks.KURIIGA_FALL.get(), 200); }

		else{ worldIn.scheduleTick(pos, Wood_Blocks.KURIIGA_FALL.get(), this.getDelayAfterPlace()); }
		
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.scheduleTick(pos, Wood_Blocks.KURIIGA_FALL.get(), 200); }
		
		else{ worldIn.scheduleTick(pos, Wood_Blocks.KURIIGA_FALL.get(), this.getDelayAfterPlace()); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (isFree(worldIn.getBlockState(pos.below())) && pos.getY() >= worldIn.getMinBuildHeight()) {
			FallingBlockEntity fallingblockentity = FallingBlockEntity.fall(worldIn, pos, state);
			this.falling(fallingblockentity);
			//worldIn.addFreshEntity(fallingblockentity); 
		}
		
		if (waterIn(state)) { 
			worldIn.scheduleTick(pos, Wood_Blocks.KURIIGA_FALL.get(), 200);
			worldIn.destroyBlock(pos, false); }
		
		if (onBush(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, Wood_Blocks.KURIIGA_FALL.get(), this.getDelayAfterPlace());
			this.dropKuri(state, worldIn, pos); }
		
		else { }
	}
	
	private void dropKuri(BlockState state, ServerLevel worldIn, BlockPos pos) {
		int x = (int) pos.getX();
		int y = (int) pos.getY();
		int z = (int) pos.getZ();
		FluidState fluidDown = worldIn.getFluidState(new BlockPos(x, y - 1, z));
		
		worldIn.removeBlock(new BlockPos(x, y, z), false);
		worldIn.setBlock(new BlockPos(x, y - 1, z), Wood_Blocks.KURIIGA_FALL.get().defaultBlockState()
				.setValue(WATERLOGGED, Boolean.valueOf(fluidDown.getType() == Fluids.WATER)), 3);
	}
	
	public void onLand(Level worldIn, BlockPos pos, BlockState fallingState, BlockState hitState, FallingBlockEntity entity) {
		CMEvents.soundSnowPlace(worldIn, pos);
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	/* Collisions for each property. + .dynamicShape() -> .registry .offsetType(BlockBehaviour.OffsetType.XZ) */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Vec3 vector3d = state.getOffset(worldIn, pos);
		return AABB_BOX.move(vector3d.x, vector3d.y, vector3d.z);
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Seasonal.KURI_IGA.get());
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_chestnuts").withStyle(ChatFormatting.GRAY));
	}
}
