package com.ayutaki.chinjufumod.blocks.window;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoorHingeSide;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class CurtainLarge extends Abstract_WaterLogged {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	public static final IntegerProperty STAGE_1_2 = IntegerProperty.create("stage", 1, 2);
	
	/* Collision */
	private static final VoxelShape CLOSE_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
	private static final VoxelShape CLOSE_EAST = Block.makeCuboidShape(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape CLOSE_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape CLOSE_WEST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);

	private static final VoxelShape RAIL_NORTH = Block.makeCuboidShape(0.0D, 15.25D, 0.0D, 16.0D, 16.0D, 0.75D);
	private static final VoxelShape RAIL_EAST = Block.makeCuboidShape(15.25D, 15.25D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape RAIL_SOUTH = Block.makeCuboidShape(0.0D, 15.25D, 15.25D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape RAIL_WEST = Block.makeCuboidShape(0.0D, 15.25D, 0.0D, 0.75D, 16.0D, 16.0D);
	
	private static final VoxelShape OPENR_NORTH = VoxelShapes.or(Block.makeCuboidShape(13.5D, 0.0D, 0.0D, 16.0D, 16.0D, 2.6D), RAIL_NORTH);
	private static final VoxelShape OPENR_EAST = VoxelShapes.or(Block.makeCuboidShape(13.4D, 0.0D, 13.5D, 16.0D, 16.0D, 16.0D), RAIL_EAST);
	private static final VoxelShape OPENR_SOUTH = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 13.4D, 2.5D, 16.0D, 16.0D), RAIL_SOUTH);
	private static final VoxelShape OPENR_WEST = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.6D, 16.0D, 2.5D), RAIL_WEST);

	private static final VoxelShape OPENL_NORTH = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.5D, 16.0D, 2.6D), RAIL_NORTH);
	private static final VoxelShape OPENL_EAST = VoxelShapes.or(Block.makeCuboidShape(13.4D, 0.0D, 0.0D, 16.0D, 16.0D, 2.5D), RAIL_EAST);
	private static final VoxelShape OPENL_SOUTH = VoxelShapes.or(Block.makeCuboidShape(13.5D, 0.0D, 13.4D, 16.0D, 16.0D, 16.0D), RAIL_SOUTH);
	private static final VoxelShape OPENL_WEST = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 13.5D, 2.6D, 16.0D, 16.0D), RAIL_WEST);
	
	public CurtainLarge(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_2, Integer.valueOf(1))
				.with(OPEN, Boolean.valueOf(false))
				.with(HINGE, DoorHingeSide.LEFT)
				.with(HALF, DoubleBlockHalf.UPPER)
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		// if (hItem instanceof Base_ItemHake) { return ActionResultType.PASS; } Too Complex.

		int i = state.get(STAGE_1_2);
		Direction direction = state.get(H_FACING);
		DoubleBlockHalf half = state.get(HALF);
		DoorHingeSide hinge = state.get(HINGE);

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		CMEvents.soundCurtain(worldIn, pos, 0.8F, 0.8F);
		
		boolean flag = state.get(OPEN)? false : true;
		
		BlockState directFlag = this.getDefaultState().with(H_FACING, direction).with(OPEN, Boolean.valueOf(flag));
		
		BlockState Up_LEFT1 = directFlag.with(STAGE_1_2, Integer.valueOf(1)).with(HALF, DoubleBlockHalf.UPPER).with(HINGE, DoorHingeSide.LEFT);
		BlockState Low_LEFT1 = directFlag.with(STAGE_1_2, Integer.valueOf(1)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, DoorHingeSide.LEFT);
		BlockState Up_LEFT2 = directFlag.with(STAGE_1_2, Integer.valueOf(2)).with(HALF, DoubleBlockHalf.UPPER).with(HINGE, DoorHingeSide.LEFT);
		BlockState Low_LEFT2 = directFlag.with(STAGE_1_2, Integer.valueOf(2)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, DoorHingeSide.LEFT);
		
		BlockState Up_RIGHT1 = directFlag.with(STAGE_1_2, Integer.valueOf(1)).with(HALF, DoubleBlockHalf.UPPER).with(HINGE, DoorHingeSide.RIGHT);
		BlockState Low_RIGHT1 = directFlag.with(STAGE_1_2, Integer.valueOf(1)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, DoorHingeSide.RIGHT);
		BlockState Up_RIGHT2 = directFlag.with(STAGE_1_2, Integer.valueOf(2)).with(HALF, DoubleBlockHalf.UPPER).with(HINGE, DoorHingeSide.RIGHT);
		BlockState Low_RIGHT2 = directFlag.with(STAGE_1_2, Integer.valueOf(2)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, DoorHingeSide.RIGHT);
		
		///// STAGE_1 ////////////////////
		switch (i) {
		case 1 :
		default :
			switch (half) {
			case LOWER :
			default :
				switch (hinge) {
				case LEFT :
				default :
					worldIn.setBlockState(pos.up(), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.up()).getFluid() == Fluids.WATER)), 3);
					worldIn.setBlockState(pos, Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos).getFluid() == Fluids.WATER)), 3);
					
					switch (direction) {
					case NORTH :
					default :
						worldIn.setBlockState(new BlockPos(x - 1, y + 1, z), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y + 1, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x - 1, y, z), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case SOUTH :
						worldIn.setBlockState(new BlockPos(x + 1, y + 1, z), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y + 1, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x + 1, y, z), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case EAST :
						worldIn.setBlockState(new BlockPos(x, y + 1, z - 1), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y + 1, z - 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y, z - 1), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
						break;
						
					case WEST :
						worldIn.setBlockState(new BlockPos(x, y + 1, z + 1), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y + 1, z + 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y, z + 1), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
						break;
					} // direction
					break;

				case RIGHT :
					worldIn.setBlockState(pos.up(), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.up()).getFluid() == Fluids.WATER)), 3);
					worldIn.setBlockState(pos, Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos).getFluid() == Fluids.WATER)), 3);

					switch (direction) {
					case NORTH :
					default :
						worldIn.setBlockState(new BlockPos(x + 1, y + 1, z), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y + 1, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x + 1, y, z), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case SOUTH :
						worldIn.setBlockState(new BlockPos(x - 1, y + 1, z), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y + 1, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x - 1, y, z), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case EAST :
						worldIn.setBlockState(new BlockPos(x, y + 1, z + 1), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y + 1, z + 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y, z + 1), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
						break;
						
					case WEST :
						worldIn.setBlockState(new BlockPos(x, y + 1, z - 1), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y + 1, z - 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y, z - 1), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
						break;
					} // direction
					break;
				} // LEFT-RIGHT
				break;


			case UPPER :
				switch (hinge) {
				case LEFT :
				default :
					worldIn.setBlockState(pos, Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos).getFluid() == Fluids.WATER)), 3);
					worldIn.setBlockState(pos.down(), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.down()).getFluid() == Fluids.WATER)), 3);

					switch (direction) {
					case NORTH :
					default :
						worldIn.setBlockState(new BlockPos(x - 1, y, z), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case SOUTH :
						worldIn.setBlockState(new BlockPos(x + 1, y, z), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case EAST :
						worldIn.setBlockState(new BlockPos(x, y, z - 1), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z - 1)).getFluid() == Fluids.WATER)), 3);
						break;
						
					case WEST :
						worldIn.setBlockState(new BlockPos(x, y, z + 1), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z + 1)).getFluid() == Fluids.WATER)), 3);
						break;
					} // direction
					break;

				case RIGHT :
					worldIn.setBlockState(pos, Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos).getFluid() == Fluids.WATER)), 3);
					worldIn.setBlockState(pos.down(), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.down()).getFluid() == Fluids.WATER)), 3);

					switch (direction) {
					case NORTH :
					default :
						worldIn.setBlockState(new BlockPos(x + 1, y, z), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case SOUTH :
						worldIn.setBlockState(new BlockPos(x - 1, y, z), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case EAST :
						worldIn.setBlockState(new BlockPos(x, y, z + 1), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z + 1)).getFluid() == Fluids.WATER)), 3);
						break;
						
					case WEST :
						worldIn.setBlockState(new BlockPos(x, y, z - 1), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z - 1)).getFluid() == Fluids.WATER)), 3);
						break;
					} // direction
					break;
				} // LEFT-RIGHT
				break;
			} // LOWER-UPPER
			break;


		///// STAGE_2 ////////////////////
		case 2 :
			switch (half) {
			case LOWER :
			default :
				switch (hinge) {
				case LEFT :
				default :
					worldIn.setBlockState(pos.up(), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.up()).getFluid() == Fluids.WATER)), 3);
					worldIn.setBlockState(pos, Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos).getFluid() == Fluids.WATER)), 3);

					switch (direction) {
					case NORTH :
					default :
						worldIn.setBlockState(new BlockPos(x + 1, y + 1, z), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y + 1, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x + 1, y, z), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case SOUTH :
						worldIn.setBlockState(new BlockPos(x - 1, y + 1, z), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y + 1, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x - 1, y, z), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case EAST :
						worldIn.setBlockState(new BlockPos(x, y + 1, z + 1), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y + 1, z + 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y, z + 1), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
						break;
						
					case WEST :
						worldIn.setBlockState(new BlockPos(x, y + 1, z - 1), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y + 1, z - 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y, z - 1), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
						break;
					} // direction
					break;

				case RIGHT :
					worldIn.setBlockState(pos.up(), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.up()).getFluid() == Fluids.WATER)), 3);
					worldIn.setBlockState(pos, Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos).getFluid() == Fluids.WATER)), 3);

					switch (direction) {
					case NORTH :
					default :
						worldIn.setBlockState(new BlockPos(x - 1, y + 1, z), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y + 1, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x - 1, y, z), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case SOUTH :
						worldIn.setBlockState(new BlockPos(x + 1, y + 1, z), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y + 1, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x + 1, y, z), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case EAST :
						worldIn.setBlockState(new BlockPos(x, y + 1, z - 1), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y + 1, z - 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y, z - 1), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
						break;
						
					case WEST :
						worldIn.setBlockState(new BlockPos(x, y + 1, z + 1), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y + 1, z + 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y, z + 1), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
						break;
					} // direction
					break;
				} // LEFT-RIGHT
				break;

			case UPPER :
				switch (hinge) {
				case LEFT :
				default :
					worldIn.setBlockState(pos, Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos).getFluid() == Fluids.WATER)), 3);
					worldIn.setBlockState(pos.down(), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.down()).getFluid() == Fluids.WATER)), 3);

					switch (direction) {
					case NORTH :
					default :
						worldIn.setBlockState(new BlockPos(x + 1, y, z), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case SOUTH :
						worldIn.setBlockState(new BlockPos(x - 1, y, z), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case EAST :
						worldIn.setBlockState(new BlockPos(x, y, z + 1), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z + 1)).getFluid() == Fluids.WATER)), 3);
						break;
						
					case WEST :
						worldIn.setBlockState(new BlockPos(x, y, z - 1), Up_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z - 1)).getFluid() == Fluids.WATER)), 3);
						break;
					} // direction
					break;

				case RIGHT :
					worldIn.setBlockState(pos, Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos).getFluid() == Fluids.WATER)), 3);
					worldIn.setBlockState(pos.down(), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.down()).getFluid() == Fluids.WATER)), 3);

					switch (direction) {
					case NORTH :
					default :
						worldIn.setBlockState(new BlockPos(x - 1, y, z), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case SOUTH :
						worldIn.setBlockState(new BlockPos(x + 1, y, z), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
						break;

					case EAST :
						worldIn.setBlockState(new BlockPos(x, y, z - 1), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z - 1)).getFluid() == Fluids.WATER)), 3);
						break;
						
					case WEST :
						worldIn.setBlockState(new BlockPos(x, y, z + 1), Up_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z + 1)).getFluid() == Fluids.WATER)), 3);
						break;
					} // direction
					break;
				} // LEFT-RIGHT
				break;
			} // LOWER-UPPER
			break;
		} // STAGE_1_2
		
		return ActionResultType.SUCCESS;
	}


	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		PlayerEntity playerIn = context.getPlayer();

		/** pos.up() = Replaceable block. **/
		if (worldIn.getBlockState(pos.down()).isReplaceable(context)) {
			
			Direction facing = playerIn.getHorizontalFacing();
			double x = (double) pos.getX();
			double y = (double) pos.getY();
			double z = (double) pos.getZ();
			
			BlockState directFlag = this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing())
					.with(STAGE_1_2, Integer.valueOf(1)).with(OPEN, Boolean.valueOf(false))
					.with(WATERLOGGED, Boolean.valueOf(fluid.getFluid() == Fluids.WATER));
			
			BlockState Right = directFlag.with(HINGE, DoorHingeSide.RIGHT).with(HALF, DoubleBlockHalf.UPPER);
			BlockState Left = directFlag.with(HINGE, DoorHingeSide.LEFT).with(HALF, DoubleBlockHalf.UPPER);
			
			if (playerIn.isSneaking()) {
				if (facing == Direction.NORTH && worldIn.getBlockState(new BlockPos(x + 1, y, z)).isReplaceable(context)) { return Right; }
				if (facing == Direction.SOUTH && worldIn.getBlockState(new BlockPos(x - 1, y, z)).isReplaceable(context)) { return Right; }
				if (facing == Direction.EAST && worldIn.getBlockState(new BlockPos(x, y, z + 1)).isReplaceable(context)) { return Right; }
				if (facing == Direction.WEST && worldIn.getBlockState(new BlockPos(x, y, z - 1)).isReplaceable(context)) { return Right; }
				else { 
					CMEvents.textNoPlace(context.getWorld(), context.getPos(), context.getPlayer());
					return null; } }
			
			else { // !Crouching
				if (facing == Direction.NORTH && worldIn.getBlockState(new BlockPos(x - 1, y, z)).isReplaceable(context)) { return Left; }
				if (facing == Direction.SOUTH && worldIn.getBlockState(new BlockPos(x + 1, y, z)).isReplaceable(context)) { return Left; }
				if (facing == Direction.EAST && worldIn.getBlockState(new BlockPos(x, y, z - 1)).isReplaceable(context)) { return Left; }
				if (facing == Direction.WEST && worldIn.getBlockState(new BlockPos(x, y, z + 1)).isReplaceable(context)) { return Left; }
				else { 
					CMEvents.textNoPlace(context.getWorld(), context.getPos(), context.getPlayer());
					return null; }
			}
		}

		else { //// !down_Replaceable
			CMEvents.textNoPlace(context.getWorld(), context.getPos(), context.getPlayer());
			return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

		Direction facing = placer.getHorizontalFacing();
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();

		BlockState directFlag = this.getDefaultState().with(H_FACING, state.get(H_FACING)).with(OPEN, Boolean.valueOf(false));
		
		if (placer.isCrouching()) {
			BlockState Low_RIGHT1 = directFlag.with(STAGE_1_2, Integer.valueOf(1)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, DoorHingeSide.RIGHT);
			BlockState Up_RIGHT2 = directFlag.with(STAGE_1_2, Integer.valueOf(2)).with(HALF, DoubleBlockHalf.UPPER).with(HINGE, DoorHingeSide.RIGHT);
			BlockState Low_RIGHT2 = directFlag.with(STAGE_1_2, Integer.valueOf(2)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, DoorHingeSide.RIGHT);
			
			worldIn.setBlockState(pos.down(), Low_RIGHT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.down()).getFluid() == Fluids.WATER)), 3);

			switch (facing) {
			case NORTH :
			default :
				worldIn.setBlockState(new BlockPos(x + 1, y, z), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
				worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
				break;

			case SOUTH :
				worldIn.setBlockState(new BlockPos(x - 1, y, z), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
				worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
				break;

			case EAST :
				worldIn.setBlockState(new BlockPos(x, y, z + 1), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
				worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z + 1)).getFluid() == Fluids.WATER)), 3);
				break;
				
			case WEST :
				worldIn.setBlockState(new BlockPos(x, y, z - 1), Up_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
				worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), Low_RIGHT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z - 1)).getFluid() == Fluids.WATER)), 3);
				break;
			} // direction
		}

		else { // !Crouching
			BlockState Low_LEFT1 = directFlag.with(STAGE_1_2, Integer.valueOf(1)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, DoorHingeSide.LEFT);
			BlockState Up_LEFT2 = directFlag.with(STAGE_1_2, Integer.valueOf(2)).with(HALF, DoubleBlockHalf.UPPER).with(HINGE, DoorHingeSide.LEFT);
			BlockState Low_LEFT2 = directFlag.with(STAGE_1_2, Integer.valueOf(2)).with(HALF, DoubleBlockHalf.LOWER).with(HINGE, DoorHingeSide.LEFT);
			
			worldIn.setBlockState(pos.down(), Low_LEFT1.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(pos.down()).getFluid() == Fluids.WATER)), 3);

			switch (facing) {
			case NORTH :
			default :
				worldIn.setBlockState(new BlockPos(x - 1, y, z), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y, z)).getFluid() == Fluids.WATER)), 3);
				worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x - 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
				break;

			case SOUTH :
				worldIn.setBlockState(new BlockPos(x + 1, y, z), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y, z)).getFluid() == Fluids.WATER)), 3);
				worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x + 1, y - 1, z)).getFluid() == Fluids.WATER)), 3);
				break;

			case EAST :
				worldIn.setBlockState(new BlockPos(x, y, z - 1), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z - 1)).getFluid() == Fluids.WATER)), 3);
				worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z - 1)).getFluid() == Fluids.WATER)), 3);
				break;
				
			case WEST :
				worldIn.setBlockState(new BlockPos(x, y, z + 1), Up_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y, z + 1)).getFluid() == Fluids.WATER)), 3);
				worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), Low_LEFT2.with(WATERLOGGED, Boolean.valueOf(worldIn.getFluidState(new BlockPos(x, y - 1, z + 1)).getFluid() == Fluids.WATER)), 3);
				break;
			} // direction
		}
	}
	
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(H_FACING, rot.rotate(state.get(H_FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return mirrorIn == Mirror.NONE ? state : state.rotate(mirrorIn.toRotation(state.get(H_FACING))).cycle(HINGE);
	}

	/* Return a random long to be passed to {@link IBakedModel#getQuads}, used for random model rotations */
	@OnlyIn(Dist.CLIENT)
	public long getPositionRandom(BlockState state, BlockPos pos) {
		return MathHelper.getCoordinateRandom(pos.getX(), pos.down(state.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	}
	
	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		BlockState state1 = super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
		if (!state1.isAir(worldIn, pos)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int i = state.get(STAGE_1_2);
		Direction direction = state.get(H_FACING);
		DoubleBlockHalf half = state.get(HALF);
		boolean flagopen = !state.get(OPEN);
		boolean flagright = (state.get(HINGE) == DoorHingeSide.RIGHT);

		switch (half) {
		case LOWER:
		default:
			switch (direction) {
			case NORTH:
			default:
				return flagopen? CLOSE_NORTH : ((i == 1)? VoxelShapes.empty() : (flagright? OPENR_NORTH : OPENL_NORTH));
			case SOUTH:
				return flagopen? CLOSE_SOUTH : ((i == 1)? VoxelShapes.empty() : (flagright? OPENR_SOUTH : OPENL_SOUTH));
			case WEST:
				return flagopen? CLOSE_WEST : ((i == 1)? VoxelShapes.empty() : (flagright? OPENR_WEST : OPENL_WEST));
			case EAST:
				return flagopen? CLOSE_EAST : ((i == 1)? VoxelShapes.empty() : (flagright? OPENR_EAST : OPENL_EAST));
			}

		case UPPER:
			switch (direction) {
			case NORTH:
			default:
				return flagopen? CLOSE_NORTH : ((i == 1)? RAIL_NORTH : (flagright? OPENR_NORTH : OPENL_NORTH));
			case SOUTH:
				return flagopen? CLOSE_SOUTH : ((i == 1)? RAIL_SOUTH : (flagright? OPENR_SOUTH : OPENL_SOUTH));
			case WEST:
				return flagopen? CLOSE_WEST : ((i == 1)? RAIL_WEST : (flagright? OPENR_WEST : OPENL_WEST));
			case EAST:
				return flagopen? CLOSE_EAST : ((i == 1)? RAIL_EAST : (flagright? OPENR_EAST : OPENL_EAST));
			}
		} // switch LOWER-UPPER
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	/* Destroy a DoubleBlock from DoublePlantBlock.class */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		int i = state.get(STAGE_1_2);
		Direction direction = state.get(H_FACING);
		DoubleBlockHalf half = state.get(HALF);
	
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();

		boolean mode = playerIn.abilities.isCreativeMode;
		boolean left = (state.get(HINGE) == DoorHingeSide.LEFT);
		
		if (!worldIn.isRemote) {
			switch (i) {
			case 1 :
			default :
				switch (half) {
				case LOWER :
				default :
					worldIn.destroyBlock(pos, mode? false : true);
					worldIn.destroyBlock(pos.up(), false);
					
					switch (direction) {
					case NORTH :
					default :
						worldIn.destroyBlock(left? new BlockPos(x - 1, y, z) : new BlockPos(x + 1, y, z), false);
						worldIn.destroyBlock(left? new BlockPos(x - 1, y + 1, z) : new BlockPos(x + 1, y + 1, z), false);
						break;

					case SOUTH :
						worldIn.destroyBlock(left? new BlockPos(x + 1, y, z) : new BlockPos(x - 1, y, z), false);
						worldIn.destroyBlock(left? new BlockPos(x + 1, y + 1, z) : new BlockPos(x - 1, y + 1, z), false);
						break;

					case EAST :
						worldIn.destroyBlock(left? new BlockPos(x, y, z - 1) : new BlockPos(x, y, z + 1), false);
						worldIn.destroyBlock(left? new BlockPos(x, y + 1, z - 1) : new BlockPos(x, y + 1, z + 1), false);
						break;
						
					case WEST :
						worldIn.destroyBlock(left? new BlockPos(x, y, z + 1) : new BlockPos(x, y, z - 1), false);
						worldIn.destroyBlock(left? new BlockPos(x, y + 1, z + 1) : new BlockPos(x, y + 1, z - 1), false);
						break;
					} // direction
					break; //case 1, LOWER


				case UPPER :
					worldIn.destroyBlock(pos, mode? false : true);
					worldIn.destroyBlock(pos.down(), false);
					
					switch (direction) {
					case NORTH :
					default :
						worldIn.destroyBlock(left? new BlockPos(x - 1, y, z) : new BlockPos(x + 1, y, z), false);
						worldIn.destroyBlock(left? new BlockPos(x - 1, y - 1, z) : new BlockPos(x + 1, y - 1, z), false);
						break;

					case SOUTH :
						worldIn.destroyBlock(left? new BlockPos(x + 1, y, z) : new BlockPos(x - 1, y, z), false);
						worldIn.destroyBlock(left? new BlockPos(x + 1, y - 1, z) : new BlockPos(x - 1, y - 1, z), false);
						break;

					case EAST :
						worldIn.destroyBlock(left? new BlockPos(x, y, z - 1) : new BlockPos(x, y, z + 1), false);
						worldIn.destroyBlock(left? new BlockPos(x, y - 1, z - 1) : new BlockPos(x, y - 1, z + 1), false);
						break;
						
					case WEST :
						worldIn.destroyBlock(left? new BlockPos(x, y, z + 1) : new BlockPos(x, y, z - 1), false);
						worldIn.destroyBlock(left? new BlockPos(x, y - 1, z + 1) : new BlockPos(x, y - 1, z - 1), false);
						break;
					} // direction
					break; //case 1, UPPER
				}
				break; //case 1


			case 2 :
				switch (half) {
				case LOWER :
				default :
					worldIn.destroyBlock(pos, mode? false : true);
					worldIn.destroyBlock(pos.up(), false);
					
					switch (direction) {
					case NORTH :
					default :
						worldIn.destroyBlock(left? new BlockPos(x + 1, y, z) : new BlockPos(x - 1, y, z), false);
						worldIn.destroyBlock(left? new BlockPos(x + 1, y + 1, z) : new BlockPos(x - 1, y + 1, z), false);
						break;

					case SOUTH :
						worldIn.destroyBlock(left? new BlockPos(x - 1, y, z) : new BlockPos(x + 1, y, z), false);
						worldIn.destroyBlock(left? new BlockPos(x - 1, y + 1, z) : new BlockPos(x + 1, y + 1, z), false);
						break;

					case EAST :
						worldIn.destroyBlock(left? new BlockPos(x, y, z + 1) : new BlockPos(x, y, z - 1), false);
						worldIn.destroyBlock(left? new BlockPos(x, y + 1, z + 1) : new BlockPos(x, y + 1, z - 1), false);
						break;
						
					case WEST :
						worldIn.destroyBlock(left? new BlockPos(x, y, z - 1) : new BlockPos(x, y, z + 1), false);
						worldIn.destroyBlock(left? new BlockPos(x, y + 1, z - 1) : new BlockPos(x, y + 1, z + 1), false);
						break;
					} // direction
					break; //case 2, LOWER


				case UPPER :
					worldIn.destroyBlock(pos, mode? false : true);
					worldIn.destroyBlock(pos.down(), false);
					
					switch (direction) {
					case NORTH :
					default :
						worldIn.destroyBlock(left? new BlockPos(x + 1, y, z) : new BlockPos(x - 1, y, z), false);
						worldIn.destroyBlock(left? new BlockPos(x + 1, y - 1, z) : new BlockPos(x - 1, y - 1, z), false);
						break;

					case SOUTH :
						worldIn.destroyBlock(left? new BlockPos(x - 1, y, z) : new BlockPos(x + 1, y, z), false);
						worldIn.destroyBlock(left? new BlockPos(x - 1, y - 1, z) : new BlockPos(x + 1, y - 1, z), false);
						break;

					case EAST :
						worldIn.destroyBlock(left? new BlockPos(x, y, z + 1) : new BlockPos(x, y, z - 1), false);
						worldIn.destroyBlock(left? new BlockPos(x, y - 1, z + 1) : new BlockPos(x, y - 1, z - 1), false);
						break;
						
					case WEST :
						worldIn.destroyBlock(left? new BlockPos(x, y, z - 1) : new BlockPos(x, y, z + 1), false);
						worldIn.destroyBlock(left? new BlockPos(x, y - 1, z - 1) : new BlockPos(x, y - 1, z + 1), false);
						break;
					} // direction
					break; //case 2, UPPER
				}
				break; //case 2
			}
		}
	}

	/* @deprecated call via {@link IBlockState#getMobilityFlag()} whenever possible. Implementing/overriding is fine. */
	public PushReaction getPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		switch(type) {
		case LAND:
			return state.get(OPEN);
		case WATER:
			return false;
		case AIR:
			return state.get(OPEN);
		default:
			return false;
		}
	}
	
	/* Create Blockstate */
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(HALF, H_FACING, OPEN, HINGE, STAGE_1_2, WATERLOGGED);
	}
	
	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_window").applyTextStyle(TextFormatting.GRAY));
	}
}
