package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.blocks.school.TeacherDesk;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DeskCloth extends BaseStage4_FaceWater {
	/* Collision */
	private static final VoxelShape SOUTH_AABB = Block.box(-1.5D, 9.0D, 15.0D, 17.5D, 16.0D, 16.0D);
	private static final VoxelShape EAST_AABB = Block.box(15.0D, 9.0D, -1.5D, 16.0D, 16.0D, 17.5D);
	private static final VoxelShape WEST_AABB = Block.box(0.0D, 9.0D, -1.5D, 1.0D, 16.0D, 17.5D);
	private static final VoxelShape NORTH_AABB = Block.box(-1.5D, 9.0D, 0.0D, 17.5D, 16.0D, 1.0D);
	
	public DeskCloth(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		if (hStack.isEmpty()) {
			CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, cloneItem(state), 1);
			worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
			
			return InteractionResult.SUCCESS; }
		
		else { //!empty
			return InteractionResult.PASS; }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return NORTH_AABB;
		case SOUTH: return SOUTH_AABB;
		case EAST: return EAST_AABB;
		case WEST: return WEST_AABB;
		}
	}
	
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) { worldIn.scheduleTick(pos, this, 10); }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
		
		return this.canNotStay(state, worldIn, pos)? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	public boolean canNotStay(BlockState state, LevelReader worldIn, BlockPos pos) {
		Direction direction = state.getValue(H_FACING);
		Block northBlock = worldIn.getBlockState(pos.north()).getBlock();
		Block southBlock = worldIn.getBlockState(pos.south()).getBlock();
		Block eastBlock = worldIn.getBlockState(pos.east()).getBlock();
		Block westBlock = worldIn.getBlockState(pos.west()).getBlock();
		
		if (direction == Direction.NORTH) { return !(northBlock instanceof OfficeDesk) && !(northBlock instanceof TeacherDesk); }
		else if (direction == Direction.SOUTH) { return !(southBlock instanceof OfficeDesk) && !(southBlock instanceof TeacherDesk); }
		else if (direction == Direction.EAST) { return !(eastBlock instanceof OfficeDesk) && !(eastBlock instanceof TeacherDesk); }
		else if (direction == Direction.WEST) { return !(westBlock instanceof OfficeDesk) && !(westBlock instanceof TeacherDesk); }
		else { return true; }
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { }
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(cloneItem(state), 1);
	}
	
	private Item cloneItem(BlockState state) {
		int i = state.getValue(STAGE_1_4);
		if (this == Furniture_Blocks.DESKCLOTH_03.get()) {
			if (i == 1) { return Items.WHITE_CARPET; }
			if (i == 2) { return Items.ORANGE_CARPET; }
			if (i == 3) { return Items.MAGENTA_CARPET; }
			else { return Items.LIGHT_BLUE_CARPET; }
		}
		if (this == Furniture_Blocks.DESKCLOTH_47.get()) {
			if (i == 1) { return Items.YELLOW_CARPET; }
			if (i == 2) { return Items.LIME_CARPET; }
			if (i == 3) { return Items.PINK_CARPET; }
			else { return Items.GRAY_CARPET; }
		}
		if (this == Furniture_Blocks.DESKCLOTH_811.get()) {
			if (i == 1) { return Items.LIGHT_GRAY_CARPET; }
			if (i == 2) { return Items.CYAN_CARPET; }
			if (i == 3) { return Items.PURPLE_CARPET; }
			else { return Items.BLUE_CARPET; }
		}
		else {
			if (i == 1) { return Items.BROWN_CARPET; }
			if (i == 2) { return Items.GREEN_CARPET; }
			if (i == 3) { return Items.RED_CARPET; }
			else { return Items.BLACK_CARPET; }
		}
	}
}
