package com.ayutaki.chinjufumod.blocks.school;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_FaceWater;
import com.ayutaki.chinjufumod.blocks.dish.BaseFood_Stage4Water;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.DeskCloth;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TeacherDesk extends BaseStage3_FaceWater {
	/* Collision */
	private static final VoxelShape SOUTH_R = Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_R = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	private static final VoxelShape WEST_R = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape NORTH_R = Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_C = Block.box(0.0D, 11.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape SOUTH_L = Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
	private static final VoxelShape EAST_L = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape WEST_L = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	private static final VoxelShape NORTH_L = Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public TeacherDesk(BlockBehaviour.Properties props) {
		super(props);
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		int i = state.getValue(STAGE_1_3);
		Direction direction = state.getValue(H_FACING);
		Direction facing = playerIn.getDirection().getOpposite();
		
		BlockState northState = worldIn.getBlockState(pos.north());
		BlockState southState = worldIn.getBlockState(pos.south());
		BlockState eastState = worldIn.getBlockState(pos.east());
		BlockState westState = worldIn.getBlockState(pos.west());
		BlockState upState = worldIn.getBlockState(pos.above());
		
		FluidState northFluid = worldIn.getFluidState(pos.north());
		FluidState southFluid = worldIn.getFluidState(pos.south());
		FluidState eastFluid = worldIn.getFluidState(pos.east());
		FluidState westFluid = worldIn.getFluidState(pos.west());
		FluidState upFluid = worldIn.getFluidState(pos.above());
		
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		boolean book = (hItem == Items_Chinjufu.SHOUHOU_empty.get() || hItem == Items.BOOK);
		boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getType() == Fluids.EMPTY);
				
		if (i == 1) {
			boolean success = (hStack.is(ItemTags.CARPETS) || book);
			
			if (success) {
				if (hItem == Items_Chinjufu.SHOUHOU_empty.get() && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Furniture_Blocks.NOTEBOOK.get().defaultBlockState()
							.setValue(NoteBook.H_FACING, facing)
							.setValue(NoteBook.HAS_BOOK, Boolean.valueOf(false))
							.setValue(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
				
				
				if (hItem == Items.BOOK && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Furniture_Blocks.DESKBOOK_1.get().defaultBlockState()
							.setValue(DeskBook1.H_FACING, facing)
							.setValue(DeskBook1.STAGE_1_4, Integer.valueOf(1))
							.setValue(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }

				if (hStack.is(ItemTags.CARPETS)) { 
					BlockState carpetState = takeBlock(hItem).defaultBlockState()
							.setValue(DeskCloth.H_FACING, direction)
							.setValue(DeskCloth.STAGE_1_4, Integer.valueOf(this.takeMeta(hItem)));
							
					switch (direction) {
					case NORTH :
					default:
						if (southState.getMaterial().isReplaceable()) {
							worldIn.setBlock(pos.south(), carpetState.setValue(DeskCloth.WATERLOGGED, Boolean.valueOf(southFluid.getType() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
				
					case SOUTH :
						if (northState.getMaterial().isReplaceable()) {
							worldIn.setBlock(pos.north(), carpetState.setValue(DeskCloth.WATERLOGGED, Boolean.valueOf(northFluid.getType() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
				
					case EAST :
						if (westState.getMaterial().isReplaceable()) {
							worldIn.setBlock(pos.west(), carpetState.setValue(DeskCloth.WATERLOGGED, Boolean.valueOf(westFluid.getType() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
						
					case WEST :
						if (eastState.getMaterial().isReplaceable()) {
							worldIn.setBlock(pos.east(), carpetState.setValue(DeskCloth.WATERLOGGED, Boolean.valueOf(eastFluid.getType() == Fluids.WATER)), 3);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
					} // switch
				}
				return InteractionResult.SUCCESS;
			}
				
			else { return InteractionResult.PASS; }
		}
		
		else {
			if (book && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				Block book2 = (hItem == Items.BOOK)? Furniture_Blocks.DESKBOOK_2.get() : Furniture_Blocks.NOTEBOOK_2.get();
				Block book3 = (hItem == Items.BOOK)? Furniture_Blocks.DESKBOOK_3.get() : Furniture_Blocks.NOTEBOOK_3.get();

				Block bookBlock = (i == 2)? book2 : book3;
				worldIn.setBlock(pos.above(), bookBlock.defaultBlockState()
						.setValue(BaseFood_Stage4Water.H_FACING, direction)
						.setValue(BaseFood_Stage4Water.STAGE_1_4, Integer.valueOf(1))
						.setValue(BaseFood_Stage4Water.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3);
				
			return InteractionResult.SUCCESS; }
			
			else { return InteractionResult.PASS; }
		}
	}

	private Block takeBlock(Item hItem) {
		boolean cloth03 = (hItem == Items.WHITE_CARPET) || (hItem == Items.ORANGE_CARPET) || (hItem == Items.MAGENTA_CARPET) || (hItem == Items.LIGHT_BLUE_CARPET);
		boolean cloth47 = (hItem == Items.YELLOW_CARPET) || (hItem == Items.LIME_CARPET) || (hItem == Items.PINK_CARPET) || (hItem == Items.GRAY_CARPET);
		boolean cloth811 = (hItem == Items.LIGHT_GRAY_CARPET) || (hItem == Items.CYAN_CARPET) || (hItem == Items.PURPLE_CARPET) || (hItem == Items.BLUE_CARPET);
		
		if (cloth03) { return Furniture_Blocks.DESKCLOTH_03.get(); }
		if (cloth47) { return Furniture_Blocks.DESKCLOTH_47.get(); }
		if (cloth811) { return Furniture_Blocks.DESKCLOTH_811.get(); }
		else { return Furniture_Blocks.DESKCLOTH_1215.get(); }
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
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		Player playerIn = context.getPlayer();
		
		Direction facing = playerIn.getDirection();
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		BlockState State1 = this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(STAGE_1_3, Integer.valueOf(1));
				
		boolean WE = worldIn.getBlockState(new BlockPos(x + 1, y, z)).canBeReplaced(context) && worldIn.getBlockState(new BlockPos(x - 1, y, z)).canBeReplaced(context);
		boolean NS = worldIn.getBlockState(new BlockPos(x, y, z + 1)).canBeReplaced(context) && worldIn.getBlockState(new BlockPos(x, y, z - 1)).canBeReplaced(context);

		if (facing == Direction.NORTH && WE) { return State1; }
		if (facing == Direction.SOUTH && WE) { return State1; }
		if (facing == Direction.EAST && NS) { return State1; }
		if (facing == Direction.WEST && NS) { return State1; }

		else { 
			CMEvents.textNoPlace(context.getLevel(), context.getClickedPos(), context.getPlayer());
			return null; }
	}
	
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		Direction facing = placer.getDirection();
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		BlockState State2 = this.defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)).setValue(STAGE_1_3, Integer.valueOf(2));
		BlockState State3 = this.defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)).setValue(STAGE_1_3, Integer.valueOf(3));
		
		switch (facing) {
		case NORTH :
		default :
			worldIn.setBlock(new BlockPos(x - 1, y, z), State2.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getType() == Fluids.WATER)), 3);
			worldIn.setBlock(new BlockPos(x + 1, y, z), State3.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getType() == Fluids.WATER)), 3);
			break;

		case SOUTH :
			worldIn.setBlock(new BlockPos(x - 1, y, z), State3.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getType() == Fluids.WATER)), 3);
			worldIn.setBlock(new BlockPos(x + 1, y, z), State2.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getType() == Fluids.WATER)), 3);
			break;

		case EAST :
			worldIn.setBlock(new BlockPos(x, y, z - 1), State2.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getType() == Fluids.WATER)), 3);
			worldIn.setBlock(new BlockPos(x, y, z + 1), State3.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getType() == Fluids.WATER)), 3);
			break;
			
		case WEST :
			worldIn.setBlock(new BlockPos(x, y, z - 1), State3.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getType() == Fluids.WATER)), 3);
			worldIn.setBlock(new BlockPos(x, y, z + 1), State2.setValue(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getType() == Fluids.WATER)), 3);
			break;
		} // direction
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		int i = state.getValue(STAGE_1_3);
		Direction direction = state.getValue(H_FACING);

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
	
	/* Controls drops when broken. End the process with "break;".*/
	@Override
	public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		int i = state.getValue(STAGE_1_3);
		Direction direction = state.getValue(H_FACING);
	
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
