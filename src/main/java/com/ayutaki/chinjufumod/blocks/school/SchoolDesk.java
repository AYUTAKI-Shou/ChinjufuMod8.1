package com.ayutaki.chinjufumod.blocks.school;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
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
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SchoolDesk extends BaseFacingWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = VoxelShapes.or(Block.makeCuboidShape(-3.0D, 15.0D, 0.0D, 19.0D, 16.0D, 16.0D), 
			Block.makeCuboidShape(-2.0D, 11.0D, 1.0D, 18.0D, 15.0D, 15.0D),
			Block.makeCuboidShape(-2.0D, 3.0D, 1.0D, 18.0D, 5.0D, 2.0D),
			Block.makeCuboidShape(-2.0D, 0.0D, 1.0D, 0.0D, 15.0D, 15.0D),
			Block.makeCuboidShape(16.0D, 0.0D, 1.0D, 18.0D, 15.0D, 15.0D));
	private static final VoxelShape AABB_WEST = VoxelShapes.or(Block.makeCuboidShape(0.0D, 15.0D, -3.0D, 16.0D, 16.0D, 19.0D), 
			Block.makeCuboidShape(1.0D, 11.0D, -2.0D, 15.0D, 15.0D, 18.0D),
			Block.makeCuboidShape(14.0D, 3.0D, -2.0D, 15.0D, 5.0D, 18.0D),
			Block.makeCuboidShape(1.0D, 0.0D, -2.0D, 15.0D, 15.0D, 0.0D),
			Block.makeCuboidShape(1.0D, 0.0D, 16.0D, 15.0D, 15.0D, 18.0D));
	private static final VoxelShape AABB_NORTH = VoxelShapes.or(Block.makeCuboidShape(-3.0D, 15.0D, 0.0D, 19.0D, 16.0D, 16.0D), 
			Block.makeCuboidShape(-2.0D, 11.0D, 1.0D, 18.0D, 15.0D, 15.0D),
			Block.makeCuboidShape(-2.0D, 3.0D, 14.0D, 18.0D, 5.0D, 15.0D),
			Block.makeCuboidShape(-2.0D, 0.0D, 1.0D, 0.0D, 15.0D, 15.0D),
			Block.makeCuboidShape(16.0D, 0.0D, 1.0D, 18.0D, 15.0D, 15.0D));
	private static final VoxelShape AABB_EAST = VoxelShapes.or(Block.makeCuboidShape(0.0D, 15.0D, -3.0D, 16.0D, 16.0D, 19.0D), 
			Block.makeCuboidShape(1.0D, 11.0D, -2.0D, 15.0D, 15.0D, 18.0D),
			Block.makeCuboidShape(1.0D, 3.0D, -2.0D, 2.0D, 5.0D, 18.0D),
			Block.makeCuboidShape(1.0D, 0.0D, -2.0D, 15.0D, 15.0D, 0.0D),
			Block.makeCuboidShape(1.0D, 0.0D, 16.0D, 15.0D, 15.0D, 18.0D));

	public SchoolDesk(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		Direction facing = playerIn.getHorizontalFacing().getOpposite();
		BlockState upState = worldIn.getBlockState(pos.up());
		IFluidState upFluid = worldIn.getFluidState(pos.up());
		
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getFluid() == Fluids.EMPTY);
		boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
		
		if (success) {
			if (hItem == Items_Chinjufu.SHOUHOU_empty && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos.up(), Furniture_Blocks.NOTEBOOK.getDefaultState()
						.with(NoteBook.H_FACING, facing)
						.with(NoteBook.HAS_BOOK, Boolean.valueOf(false))
						.with(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3); }
			
			
			if (hItem == Items.BOOK && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos.up(), Furniture_Blocks.DESKBOOK_1.getDefaultState()
						.with(DeskBook1.H_FACING, facing)
						.with(DeskBook1.STAGE_1_4, Integer.valueOf(1))
						.with(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3); }
			
			return ActionResultType.SUCCESS; }
		
		else { return ActionResultType.PASS; }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
