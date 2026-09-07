package com.ayutaki.chinjufumod.blocks.unitblock;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BaseUnitBlock extends Abstract_WaterLogged {
	/* Property */
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	public static final BooleanProperty WHICH = BooleanProperty.create("which");

	public BaseUnitBlock(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false))
				.with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false))
				.with(WHICH, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (state.getBlock() instanceof Wagasa) {
			/** Hand is Empty. **/
			if (hStack.isEmpty() && playerIn.isSneaking()) {
				CMEvents.soundClothPlace(worldIn, pos);
				worldIn.setBlockState(pos, state.cycle(WHICH));
				return ActionResultType.SUCCESS;
			}
			return ActionResultType.PASS;
		}
		
		else { //!WAGASA
			Direction facing = playerIn.getHorizontalFacing().getOpposite();
			BlockState upState = worldIn.getBlockState(pos.up());
			IFluidState upFluid = worldIn.getFluidState(pos.up());
			
			Item hItem = hStack.getItem();
			boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getFluid() == Fluids.EMPTY);
			boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);

			if (success) {
				Block block = state.getBlock();
				boolean lowDesk = (block instanceof LowDesk || block instanceof Chabudai || block instanceof Kotatsu);

				if (hItem == Items_Chinjufu.SHOUHOU_empty && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), Furniture_Blocks.NOTEBOOK.getDefaultState()
							.with(NoteBook.H_FACING, facing)
							.with(NoteBook.HAS_BOOK, Boolean.valueOf(false))
							.with(NoteBook.DOWN, Boolean.valueOf(lowDesk))
							.with(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3); }

				if (hItem == Items.BOOK && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), Furniture_Blocks.DESKBOOK_1.getDefaultState()
							.with(DeskBook1.H_FACING, facing)
							.with(DeskBook1.STAGE_1_4, Integer.valueOf(1))
							.with(DeskBook1.DOWN, Boolean.valueOf(lowDesk))
							.with(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getFluid() == Fluids.WATER)), 3); }
				
				return ActionResultType.SUCCESS; }
			
			if (hStack.isEmpty() && playerIn.isSneaking()) {
				CMEvents.soundWoodPlace(worldIn, pos);
				worldIn.setBlockState(pos, state.cycle(WHICH));
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.PASS;
	}
	
	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	/* Connect the blocks. */
	private boolean canConnectTo(IWorld worldIn, BlockPos source, Direction direction, boolean which) {
		BlockState state = worldIn.getBlockState(source.offset(direction));
		return state.getBlock() == this && state.get(WHICH) == which;
	}

	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }
		
		boolean which = state.get(WHICH);
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH, which);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST, which);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH, which);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST, which);
		return state.with(NORTH, north).with(EAST, east).with(SOUTH, south).with(WEST, west);
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, WHICH, WATERLOGGED);
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
}
