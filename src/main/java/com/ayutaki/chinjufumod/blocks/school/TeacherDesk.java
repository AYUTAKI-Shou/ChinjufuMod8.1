package com.ayutaki.chinjufumod.blocks.school;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_FaceWater;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook2;
import com.ayutaki.chinjufumod.blocks.furniture.DeskCloth;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class TeacherDesk extends BaseStage3_FaceWater {
	/* Collision */
	private static final VoxelShape SOUTH_R = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_R = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	private static final VoxelShape WEST_R = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape NORTH_R = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_C = Block.makeCuboidShape(0.0D, 11.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape SOUTH_L = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_L = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape WEST_L = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	private static final VoxelShape NORTH_L = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public TeacherDesk(Block.Properties props) {
		super(props);
	}
	
	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		int i = state.get(STAGE_1_3);
		Direction direction = state.get(H_FACING);
		Direction facing = playerIn.getHorizontalFacing().getOpposite();
		
		BlockState northState = worldIn.getBlockState(pos.north());
		BlockState southState = worldIn.getBlockState(pos.south());
		BlockState eastState = worldIn.getBlockState(pos.east());
		BlockState westState = worldIn.getBlockState(pos.west());
		BlockState upState = worldIn.getBlockState(pos.up());
		
		IFluidState northFluid = worldIn.getFluidState(pos.north());
		IFluidState southFluid = worldIn.getFluidState(pos.south());
		IFluidState eastFluid = worldIn.getFluidState(pos.east());
		IFluidState westFluid = worldIn.getFluidState(pos.west());
		IFluidState upFluid = worldIn.getFluidState(pos.up());
		
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean book = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
		boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getFluid() == Fluids.EMPTY);
		
		if (i == 1) {
			boolean success = (hItem.isIn(ItemTags.CARPETS) || book);
			
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

				if (hItem.isIn(ItemTags.CARPETS)) {
					BlockState carpetState = takeBlock(hItem).getDefaultState()
							.with(DeskCloth.H_FACING, direction)
							.with(DeskCloth.STAGE_1_4, Integer.valueOf(takeMeta(hItem)));
							
					switch (direction) {
					case NORTH :
					default:
						if (!southState.getMaterial().isReplaceable()) { 
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						
						if (southState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.south(), carpetState.with(DeskCloth.WATERLOGGED, Boolean.valueOf(southFluid.getFluid() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						break;
				
					case SOUTH :
						if (!northState.getMaterial().isReplaceable()) { 
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						
						if (northState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.north(), carpetState.with(DeskCloth.WATERLOGGED, Boolean.valueOf(northFluid.getFluid() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						break;
				
					case EAST :
						if (!westState.getMaterial().isReplaceable()) { 
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						
						if (westState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.west(), carpetState.with(DeskCloth.WATERLOGGED, Boolean.valueOf(westFluid.getFluid() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						break;
						
					case WEST :
						if (!eastState.getMaterial().isReplaceable()) { 
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						
						if (eastState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.east(), carpetState.with(DeskCloth.WATERLOGGED, Boolean.valueOf(eastFluid.getFluid() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						break;
					} // switch
				}
				return ActionResultType.SUCCESS;
			}

			else { return ActionResultType.PASS; }
		}
		
		else {
			if (book && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				Block book2 = (hItem == Items.BOOK)? Furniture_Blocks.DESKBOOK_2 : Furniture_Blocks.NOTEBOOK_2;
				Block book3 = (hItem == Items.BOOK)? Furniture_Blocks.DESKBOOK_3 : Furniture_Blocks.NOTEBOOK_3;

				Block bookBlock = (i == 2)? book2 : book3;
				worldIn.setBlockState(pos.up(), bookBlock.getDefaultState()
						.with(DeskBook2.H_FACING, direction)
						.with(DeskBook2.STAGE_1_4, Integer.valueOf(1))
						.with(DeskBook2.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3);
				
				return ActionResultType.SUCCESS; }
			
			else { return ActionResultType.PASS; }
		}
	}
	
	private Block takeBlock(Item hItem) {
		boolean cloth03 = (hItem == Items.WHITE_CARPET) || (hItem == Items.ORANGE_CARPET) || (hItem == Items.MAGENTA_CARPET) || (hItem == Items.LIGHT_BLUE_CARPET);
		boolean cloth47 = (hItem == Items.YELLOW_CARPET) || (hItem == Items.LIME_CARPET) || (hItem == Items.PINK_CARPET) || (hItem == Items.GRAY_CARPET);
		boolean cloth811 = (hItem == Items.LIGHT_GRAY_CARPET) || (hItem == Items.CYAN_CARPET) || (hItem == Items.PURPLE_CARPET) || (hItem == Items.BLUE_CARPET);
		
		if (cloth03) { return Furniture_Blocks.DESKCLOTH_03; }
		if (cloth47) { return Furniture_Blocks.DESKCLOTH_47; }
		if (cloth811) { return Furniture_Blocks.DESKCLOTH_811; }
		else { return Furniture_Blocks.DESKCLOTH_1215; }
	}
	
	private int takeMeta(Item hItem) {
		boolean meta1 = (hItem == Items.WHITE_CARPET) || (hItem == Items.YELLOW_CARPET) || (hItem == Items.LIGHT_GRAY_CARPET) || (hItem == Items.BROWN_CARPET);
		boolean meta2 = (hItem == Items.ORANGE_CARPET) || (hItem == Items.LIME_CARPET) || (hItem == Items.CYAN_CARPET) || (hItem == Items.GREEN_CARPET);
		boolean meta3 = (hItem == Items.MAGENTA_CARPET) || (hItem == Items.PINK_CARPET) || (hItem == Items.PURPLE_CARPET) || (hItem == Items.RED_CARPET);

		if (meta1) { return 1; }
		if (meta2) { return 2; }
		if (meta3) { return 3; }
		else { return 4; }
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		PlayerEntity playerIn = context.getPlayer();
		
		Direction facing = playerIn.getHorizontalFacing();
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		BlockState State1 = this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(STAGE_1_3, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER));
				
		boolean WE = worldIn.getBlockState(new BlockPos(x + 1, y, z)).isReplaceable(context) && worldIn.getBlockState(new BlockPos(x - 1, y, z)).isReplaceable(context);
		boolean NS = worldIn.getBlockState(new BlockPos(x, y, z + 1)).isReplaceable(context) && worldIn.getBlockState(new BlockPos(x, y, z - 1)).isReplaceable(context);

		if (facing == Direction.NORTH && WE) { return State1; }
		if (facing == Direction.SOUTH && WE) { return State1; }
		if (facing == Direction.EAST && NS) { return State1; }
		if (facing == Direction.WEST && NS) { return State1; }

		else { 
			CMEvents.textNoPlace(context.getWorld(), context.getPos(), context.getPlayer());
			return null; }
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		Direction facing = placer.getHorizontalFacing();
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		BlockState State2 = this.getDefaultState().with(H_FACING, state.get(H_FACING)).with(STAGE_1_3, Integer.valueOf(2));
		BlockState State3 = this.getDefaultState().with(H_FACING, state.get(H_FACING)).with(STAGE_1_3, Integer.valueOf(3));

		switch (facing) {
		case NORTH :
		default :
			worldIn.setBlockState(new BlockPos(x - 1, y, z), State2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
			worldIn.setBlockState(new BlockPos(x + 1, y, z), State3.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
			break;

		case SOUTH :
			worldIn.setBlockState(new BlockPos(x - 1, y, z), State3.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
			worldIn.setBlockState(new BlockPos(x + 1, y, z), State2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
			break;

		case EAST :
			worldIn.setBlockState(new BlockPos(x, y, z - 1), State2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
			worldIn.setBlockState(new BlockPos(x, y, z + 1), State3.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
			break;
			
		case WEST :
			worldIn.setBlockState(new BlockPos(x, y, z - 1), State3.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
			worldIn.setBlockState(new BlockPos(x, y, z + 1), State2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
			break;
		} // direction
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int i = state.get(STAGE_1_3);
		Direction direction = state.get(H_FACING);

		switch (direction) {
		case NORTH:
		default:
			return (i == 2)? NORTH_R : ((i ==3)? NORTH_L : AABB_C);
			
		case SOUTH:
			return (i == 2)? SOUTH_R : ((i ==3)? SOUTH_L : AABB_C);

		case EAST:
			return (i == 2)? EAST_R : ((i ==3)? EAST_L : AABB_C);

		case WEST:
			return (i == 2)? WEST_R : ((i ==3)? WEST_L : AABB_C);
		}
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	/* Destroy a DoubleBlock from DoublePlantBlock.class */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		int i = state.get(STAGE_1_3);
		Direction direction = state.get(H_FACING);
	
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();

		if (i == 1) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				break;
			} // direction
		}
	
		if (i == 2) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x - 2, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 2, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z - 2), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 2), false);
				break;
			} // direction
		}
	
		if (i == 3) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 2, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x - 2, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 2), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z - 2), false);
				break;
			} // direction
		}
	}
}
