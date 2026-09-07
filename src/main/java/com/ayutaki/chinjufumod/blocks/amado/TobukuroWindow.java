package com.ayutaki.chinjufumod.blocks.amado;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.base.BaseStage2_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class TobukuroWindow extends Abstract_WaterLogged {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_3 = IntegerProperty.create("stage", 1, 3);
	public static final BooleanProperty WHICH = BooleanProperty.create("which");

	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(-0.5D, -0.5D, 0.0D, 16.5D, 16.5D, 3.0D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(13.0D, -0.5D, -0.5D, 16.0D, 16.5D, 16.5D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(-0.5D, -0.5D, 13.0D, 16.5D, 16.5D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(0.0D, -0.5D, -0.5D, 3.0D, 16.5D, 16.5D);

	/** 1=2 sheets, 2=1 sheet, 3=Zero **/
	public TobukuroWindow(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_3, Integer.valueOf(1))
				.with(WHICH, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		/** 1=2 sheets, 2=1 sheet, 3=Zero **/
		int i = state.get(STAGE_1_3);
		Direction direction = state.get(H_FACING);
		boolean which = state.get(WHICH);

		BlockState northState = worldIn.getBlockState(pos.north());
		BlockState southState = worldIn.getBlockState(pos.south());
		BlockState eastState = worldIn.getBlockState(pos.east());
		BlockState westState = worldIn.getBlockState(pos.west());

		if (i == 3) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 3
			CMEvents.soundAmadoWin(worldIn, pos);
			
			if (which) {
				BlockState AMADO_FACE_2 = this.takeBlock().getDefaultState()
						.with(H_FACING, state.get(H_FACING)).with(BaseStage2_FaceWater.STAGE_1_2, Integer.valueOf(2));
				
				switch (direction) {
				case NORTH:
				default:
					if (eastState.getMaterial().isReplaceable()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlockState(pos.east(), AMADO_FACE_2); }
					
					else { } //east_Replaceable
					break;

				case SOUTH:
					if (westState.getMaterial().isReplaceable()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlockState(pos.west(), AMADO_FACE_2); }
					
					else { } //west_Replaceable
					break;

				case EAST:
					if (southState.getMaterial().isReplaceable()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlockState(pos.south(), AMADO_FACE_2); }
					
					else { } //south_Replaceable
					break;
					
				case WEST:
					if (northState.getMaterial().isReplaceable()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlockState(pos.north(), AMADO_FACE_2); }
					
					else { } //north_Replaceable
					break;
				} // switch
			}
			
			else { //!which
				BlockState AMADO_FACE_1 = this.takeBlock().getDefaultState()
						.with(H_FACING, state.get(H_FACING)).with(BaseStage2_FaceWater.STAGE_1_2, Integer.valueOf(1));
				
				switch (direction) {
				case NORTH:
				default:
					if (westState.getMaterial().isReplaceable()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlockState(pos.west(), AMADO_FACE_1); }
					
					else { } //west_Replaceable
					break;

				case SOUTH:
					if (eastState.getMaterial().isReplaceable()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlockState(pos.east(), AMADO_FACE_1); }
					
					else { } //east_Replaceable
					break;

				case EAST:
					if (northState.getMaterial().isReplaceable()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlockState(pos.north(), AMADO_FACE_1); }
					
					else { } //north_Replaceable
					break;
					
				case WEST:
					if (southState.getMaterial().isReplaceable()) {
						this.updateTOBUKURO(state, worldIn, pos);
						worldIn.setBlockState(pos.south(), AMADO_FACE_1); }
					
					else { } //south_Replaceable
					break;
				} // switch
			}
		} // i != 3
		return ActionResultType.SUCCESS;
	}

	private void updateTOBUKURO(BlockState state, World worldIn, BlockPos pos) {
		int i = state.get(STAGE_1_3);
		CMEvents.soundFusumaS(worldIn, pos);
		worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 1)));
	}
	
	private Block takeBlock() {
		if (this == Slidedoor_Blocks.TOBUKUROWIN) { return Slidedoor_Blocks.AMADOWIN; }
		else { return Slidedoor_Blocks.AMADOWIN_S; }
	}
	
	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		PlayerEntity playerIn = context.getPlayer();

		boolean SNEAK = playerIn.isSneaking();
		return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(STAGE_1_3, Integer.valueOf(1))
				.with(WHICH, Boolean.valueOf(SNEAK))
				.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

		/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(H_FACING, rotation.rotate(state.get(H_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(H_FACING)));
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_3, WHICH, WATERLOGGED);
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
		}
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
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
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_tobukuro").applyTextStyle(TextFormatting.GRAY));
	}
}
