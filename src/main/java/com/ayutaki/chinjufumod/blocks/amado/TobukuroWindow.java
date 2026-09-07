package com.ayutaki.chinjufumod.blocks.amado;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_FaceWater;
import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TobukuroWindow extends Abstract_WaterLogged {
	public static final MapCodec<TobukuroWindow> CODEC = simpleCodec(TobukuroWindow::new);
	@Override
	public MapCodec<? extends TobukuroWindow> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_3 = IntegerProperty.create("stage", 1, 3);
	public static final BooleanProperty WHICH = BooleanProperty.create("which");
	
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(-0.5D, -0.5D, 0.0D, 16.5D, 16.5D, 3.0D);
	private static final VoxelShape AABB_WEST = Block.box(13.0D, -0.5D, -0.5D, 16.0D, 16.5D, 16.5D);
	private static final VoxelShape AABB_NORTH = Block.box(-0.5D, -0.5D, 13.0D, 16.5D, 16.5D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, -0.5D, -0.5D, 3.0D, 16.5D, 16.5D);

	/** 1=2 sheets, 2=1 sheet, 3=Zero **/
	public TobukuroWindow(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_3, Integer.valueOf(1))
				.setValue(WHICH, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player playerIn, BlockHitResult hit) {
		/** 1=2 sheets, 2=1 sheet, 3=Zero **/
		int i = state.getValue(STAGE_1_3);
		Direction direction = state.getValue(H_FACING);
		boolean which = state.getValue(WHICH);

		BlockState northState = worldIn.getBlockState(pos.north());
		BlockState southState = worldIn.getBlockState(pos.south());
		BlockState eastState = worldIn.getBlockState(pos.east());
		BlockState westState = worldIn.getBlockState(pos.west());

		if (i == 3) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 3
			CMEvents.soundAmadoWin(worldIn, pos);
			
			if (which) {
				BlockState AMADOWIN_FACE_2 = this.takeBlock().defaultBlockState()
						.setValue(BaseStage2_FaceWater.H_FACING, state.getValue(H_FACING)).setValue(BaseStage2_FaceWater.STAGE_1_2, Integer.valueOf(2));
				
				switch (direction) {
				case NORTH:
				default:
					if (eastState.canBeReplaced()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlock(pos.east(), AMADOWIN_FACE_2, 3); }
					
					else { } //!Replaceable
					break;

				case SOUTH:
					if (westState.canBeReplaced()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlock(pos.west(), AMADOWIN_FACE_2, 3); }
					
					else { } //!Replaceable
					break;

				case EAST:
					if (southState.canBeReplaced()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlock(pos.south(), AMADOWIN_FACE_2, 3); }
					
					else { } //!Replaceable
					break;
					
				case WEST:
					if (northState.canBeReplaced()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlock(pos.north(), AMADOWIN_FACE_2, 3); }
					
					else { } //!Replaceable
					break;
				} //switch
			} //which
			
			else {
				BlockState AMADOWIN_FACE_1 = this.takeBlock().defaultBlockState()
						.setValue(BaseStage2_FaceWater.H_FACING, state.getValue(H_FACING)).setValue(BaseStage2_FaceWater.STAGE_1_2, Integer.valueOf(1));
				
				switch (direction) {
				case NORTH:
				default:
					if (westState.canBeReplaced()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlock(pos.west(), AMADOWIN_FACE_1, 3); }
					
					else { } //!Replaceable
					break;

				case SOUTH:
					if (eastState.canBeReplaced()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlock(pos.east(), AMADOWIN_FACE_1, 3); }
					
					else { } //!Replaceable
					break;

				case EAST:
					if (northState.canBeReplaced()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlock(pos.north(), AMADOWIN_FACE_1, 3); }
					
					else { } //!Replaceable
					break;
					
				case WEST:
					if (southState.canBeReplaced()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlock(pos.south(), AMADOWIN_FACE_1, 3); }
					
					else { } //!Replaceable
					break;
				} // switch
			}
		} //i != 3
		return InteractionResult.SUCCESS;
	}

	private void updateTOBUKURO(BlockState state, Level worldIn, BlockPos pos) {
		int i = state.getValue(STAGE_1_3);
		CMEvents.soundFusumaS(worldIn, pos);
		worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(i + 1)), 3);
	}
	
	private Block takeBlock() {
		if (this == Slidedoor_Blocks.TOBUKUROWIN.get()) { return Slidedoor_Blocks.AMADOWIN.get(); }
		else { return Slidedoor_Blocks.AMADOWIN_S.get(); }
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		Player playerIn = context.getPlayer();

		boolean SNEAK = playerIn.isCrouching();
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(STAGE_1_3, Integer.valueOf(1))
				.setValue(WHICH, Boolean.valueOf(SNEAK))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
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
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_3, WHICH, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_tobukuro").withStyle(ChatFormatting.GRAY));
	}
}
