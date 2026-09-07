package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class BaseUnitBlock extends Abstract_WaterLogged {
	/* Property */
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	public static final BooleanProperty WHICH = BooleanProperty.create("which");
	
	public BaseUnitBlock(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false))
				.setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false))
				.setValue(WHICH, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		if (state.getBlock() instanceof Wagasa) {
			if (hStack.isEmpty() && playerIn.isCrouching()) {
				CMEvents.soundClothPlace(worldIn, pos);
				worldIn.setBlock(pos, state.cycle(WHICH), 3);
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.PASS;
		}
		
		else { 
			Direction facing = playerIn.getDirection().getOpposite();
			BlockState upState = worldIn.getBlockState(pos.above());
			FluidState upFluid = worldIn.getFluidState(pos.above());
			
			Item hItem = hStack.getItem();
			boolean upAble = (upState.getMaterial().isReplaceable() && upFluid.getType() == Fluids.EMPTY);
			boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty.get() || hItem == Items.BOOK);
			
			if (success) {
				Block block = state.getBlock();
				boolean lowDesk = (block instanceof LowDesk || block instanceof Chabudai || block instanceof Kotatsu);
				
				if (hItem == Items_Chinjufu.SHOUHOU_empty.get() && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Furniture_Blocks.NOTEBOOK.get().defaultBlockState()
							.setValue(NoteBook.H_FACING, facing)
							.setValue(NoteBook.HAS_BOOK, Boolean.valueOf(false))
							.setValue(NoteBook.DOWN, Boolean.valueOf(lowDesk))
							.setValue(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
				
				if (hItem == Items.BOOK && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Furniture_Blocks.DESKBOOK_1.get().defaultBlockState()
							.setValue(DeskBook1.H_FACING, facing)
							.setValue(DeskBook1.STAGE_1_4, Integer.valueOf(1))
							.setValue(NoteBook.DOWN, Boolean.valueOf(lowDesk))
							.setValue(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
				
				return InteractionResult.SUCCESS; }
			
			if (hStack.isEmpty() && playerIn.isCrouching()) {
				CMEvents.soundWoodPlace(worldIn, pos);
				worldIn.setBlock(pos, state.cycle(WHICH), 3);
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.PASS;
		}
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* Connect the blocks. */
	private boolean canConnectTo(BlockGetter worldIn, BlockPos pos, Direction direction, boolean which) {
		BlockState state = worldIn.getBlockState(pos.relative(direction));
		return state.is(this) && state.getValue(WHICH) == which;
	}

	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		boolean which = state.getValue(WHICH);
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH, which);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST, which);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH, which);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST, which);
		return state.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, WHICH, WATERLOGGED);
	}

	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }

}
