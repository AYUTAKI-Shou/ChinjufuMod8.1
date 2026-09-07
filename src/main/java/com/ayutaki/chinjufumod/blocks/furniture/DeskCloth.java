package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.blocks.school.TeacherDesk;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class DeskCloth extends BaseStage4_FaceWater {

	private static final VoxelShape SOUTH_AABB = Block.box(-1.5D, 9.0D, 15.0D, 17.5D, 16.0D, 16.0D);
	private static final VoxelShape EAST_AABB = Block.box(15.0D, 9.0D, -1.5D, 16.0D, 16.0D, 17.5D);
	private static final VoxelShape WEST_AABB = Block.box(0.0D, 9.0D, -1.5D, 1.0D, 16.0D, 17.5D);
	private static final VoxelShape NORTH_AABB = Block.box(-1.5D, 9.0D, 0.0D, 17.5D, 16.0D, 1.0D);

	public DeskCloth(AbstractBlock.Properties props) {
		super(props);
	}
	
	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);

		if (hStack.isEmpty()) {
			CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, cloneItem(state), 1);
			worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
			return ActionResultType.SUCCESS; }
		
		else { //!empty
			return ActionResultType.PASS; }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
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
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state)) { worldIn.getBlockTicks().scheduleTick(pos, this, 60); }

		return this.canNotStay(state, worldIn, pos)? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	public boolean canNotStay(BlockState state, IWorld worldIn, BlockPos pos) {
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
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.getBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { }
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(cloneItem(state), 1);
	}
	
	private Item cloneItem(BlockState state) {
		int i = state.getValue(STAGE_1_4);
		if (this == Furniture_Blocks.DESKCLOTH_03) {
			if (i == 1) { return Items.WHITE_CARPET; }
			if (i == 2) { return Items.ORANGE_CARPET; }
			if (i == 3) { return Items.MAGENTA_CARPET; }
			else { return Items.LIGHT_BLUE_CARPET; }
		}
		if (this == Furniture_Blocks.DESKCLOTH_47) {
			if (i == 1) { return Items.YELLOW_CARPET; }
			if (i == 2) { return Items.LIME_CARPET; }
			if (i == 3) { return Items.PINK_CARPET; }
			else { return Items.GRAY_CARPET; }
		}
		if (this == Furniture_Blocks.DESKCLOTH_811) {
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
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
