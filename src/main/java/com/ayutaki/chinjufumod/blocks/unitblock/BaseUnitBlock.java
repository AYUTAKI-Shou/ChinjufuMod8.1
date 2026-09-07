package com.ayutaki.chinjufumod.blocks.unitblock;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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

	public BaseUnitBlock(AbstractBlock.Properties props) {
		super(props);
		registerDefaultState(this.defaultBlockState().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false))
				.setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false))
				.setValue(WHICH, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);

		if (state.getBlock() instanceof Wagasa) {
			if (hStack.isEmpty() && playerIn.isCrouching()) {
				CMEvents.soundClothPlace(worldIn, pos);
				worldIn.setBlock(pos, state.cycle(WHICH), 3);
				return ActionResultType.SUCCESS;
			}
			return ActionResultType.PASS;
		}
		
		else { //!WAGASA
			Direction facing = playerIn.getDirection().getOpposite();
			BlockState upState = worldIn.getBlockState(pos.above());
			FluidState upFluid = worldIn.getFluidState(pos.above());
			
			Item hItem = hStack.getItem();
			boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getType() == Fluids.EMPTY);
			boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
			
			if (success) {
				Block block = state.getBlock();
				boolean lowDesk = (block instanceof LowDesk || block instanceof Chabudai || block instanceof Kotatsu);
				
				if (hItem == Items_Chinjufu.SHOUHOU_empty && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Furniture_Blocks.NOTEBOOK.defaultBlockState()
							.setValue(NoteBook.H_FACING, facing)
							.setValue(NoteBook.HAS_BOOK, Boolean.valueOf(false))
							.setValue(NoteBook.DOWN, Boolean.valueOf(lowDesk))
							.setValue(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
				
				if (hItem == Items.BOOK && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Furniture_Blocks.DESKBOOK_1.defaultBlockState()
							.setValue(DeskBook1.H_FACING, facing)
							.setValue(DeskBook1.STAGE_1_4, Integer.valueOf(1))
							.setValue(DeskBook1.DOWN, Boolean.valueOf(lowDesk))
							.setValue(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
				
				return ActionResultType.SUCCESS; }
			
			if (hStack.isEmpty() && playerIn.isCrouching()) {
				CMEvents.soundWoodPlace(worldIn, pos);
				worldIn.setBlock(pos, state.cycle(WHICH), 3);
				return ActionResultType.SUCCESS;
			}
			return ActionResultType.PASS;
		}
	}
	
	/* Gives a value when placed. */
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Connect the blocks. */
	private boolean canConnectTo(IWorld worldIn, BlockPos source, Direction direction, boolean which) {
		BlockState state = worldIn.getBlockState(source.relative(direction));
		return state.getBlock() == this && state.getValue(WHICH) == which;
	}

	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		boolean which = state.getValue(WHICH);
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH, which);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST, which);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH, which);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST, which);
		return state.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, WHICH, WATERLOGGED);
	}

	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 5; }

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 20; }
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
