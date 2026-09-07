package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Slab_JP;
import com.ayutaki.chinjufumod.blocks.jpdeco.BaseTatami;
import com.ayutaki.chinjufumod.blocks.unitblock.Chabudai;
import com.ayutaki.chinjufumod.blocks.unitblock.Kotatsu;
import com.ayutaki.chinjufumod.blocks.unitblock.LowDesk;
import com.ayutaki.chinjufumod.blocks.wood.WoodSlab_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.state.TatamiType;
import com.ayutaki.chinjufumod.tileentity.NoteTileEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;

public class Base_NoteBook extends BaseEntityBlock implements SimpleWaterloggedBlock {
	/* Property */
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");
	public static final BooleanProperty HAS_BOOK = BlockStateProperties.HAS_BOOK;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	
	public Base_NoteBook(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(HAS_BOOK, Boolean.valueOf(false))
				.setValue(POWERED, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (state.getValue(HAS_BOOK)) {
			if (!worldIn.isClientSide) { this.openScreen(worldIn, pos, playerIn); }
			return InteractionResult.sidedSuccess(worldIn.isClientSide);
		}
		
		else {
			if (hStack.is(ItemTags.LECTERN_BOOKS)) {
				return tryPlaceBook(playerIn, worldIn, pos, state, hStack)? InteractionResult.CONSUME : InteractionResult.PASS; }
			
			if (this == Furniture_Blocks.NOTEBOOK.get() && hItem == Items.BOOK) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, Furniture_Blocks.NOTEBOOK_B.get().defaultBlockState()
						.setValue(H_FACING, state.getValue(H_FACING))
						.setValue(OPEN, state.getValue(OPEN))
						.setValue(DOWN, state.getValue(DOWN))
						.setValue(HAS_BOOK, Boolean.valueOf(false))
						.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return InteractionResult.SUCCESS; }
			
			if (hStack.isEmpty()) {
				CMEvents.soundPage(worldIn, pos);
				if (this == Furniture_Blocks.NOTEBOOK_B.get()) { CMEvents.soundWoodPlace(worldIn, pos); }
				
				worldIn.setBlock(pos, state.cycle(OPEN), 3);
				return InteractionResult.SUCCESS; }
			
			else { return InteractionResult.PASS; }
		}
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new NoteTileEntity(pos, state);
	}
	
	public static boolean tryPlaceBook(@Nullable Player playerIn, Level worldIn, BlockPos pos, BlockState state, ItemStack hStack) {
		if (!state.getValue(HAS_BOOK)) {
			if (!worldIn.isClientSide) { placeBook(playerIn, worldIn, pos, state, hStack); }
			return true;
		}
		
		else { return false; }
	}
	
	private static void placeBook(@Nullable Player playerIn, Level worldIn, BlockPos pos, BlockState state, ItemStack p_153580_) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof NoteTileEntity) {
			NoteTileEntity noteTile = (NoteTileEntity)tileEntity;
			noteTile.setBook(p_153580_.split(1));
			resetBookState(worldIn, pos, state, true);
			worldIn.playSound((Player)null, pos, SoundEvents.BOOK_PUT, SoundSource.BLOCKS, 1.0F, 1.0F);
			worldIn.gameEvent(playerIn, GameEvent.BLOCK_CHANGE, pos);
		}
	}

	public static void resetBookState(Level worldIn, BlockPos pos, BlockState state, boolean hasBook) {
		worldIn.setBlock(pos, state.setValue(HAS_BOOK, Boolean.valueOf(hasBook)), 3);
		updateBelow(worldIn, pos, state);
	}

	public static void signalPageChange(Level worldIn, BlockPos pos, BlockState state) {
		worldIn.scheduleTick(pos, state.getBlock(), 2);
		worldIn.levelEvent(1043, pos, 0);
	}

	private static void updateBelow(Level worldIn, BlockPos pos, BlockState state) {
		worldIn.updateNeighborsAt(pos.below(), state.getBlock());
	}
	
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState state2, boolean hasBook) {
		if (!state.is(state2.getBlock())) {
			if (state.getValue(HAS_BOOK)) { this.popBook(state, worldIn, pos); }

			super.onRemove(state, worldIn, pos, state2, hasBook);
		}
	}

	private void popBook(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof NoteTileEntity) {
			NoteTileEntity noteTile = (NoteTileEntity)tileEntity;
			Direction direction = state.getValue(H_FACING);
			ItemStack copy = noteTile.getBook().copy();
			float f = 0.25F * (float)direction.getStepX();
			float f1 = 0.25F * (float)direction.getStepZ();
			ItemEntity hItem = new ItemEntity(worldIn, (double)pos.getX() + 0.5D + (double)f, (double)(pos.getY() + 1), (double)pos.getZ() + 0.5D + (double)f1, copy);
			hItem.setDefaultPickUpDelay();
			worldIn.addFreshEntity(hItem);
			noteTile.clearContent();
		}
	}

	public boolean isSignalSource(BlockState state) {
		return true;
	}
	
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	public int getAnalogOutputSignal(BlockState state, Level worldIn, BlockPos pos) {
		return 0;
	}
	
	@Nullable
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		return !state.getValue(HAS_BOOK) ? null : super.getMenuProvider(state, worldIn, pos);
	}

	private void openScreen(Level worldIn, BlockPos pos, Player playerIn) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof NoteTileEntity) {
			playerIn.openMenu((NoteTileEntity)tileEntity);
			playerIn.awardStat(Stats.INTERACT_WITH_LECTERN);
		}
	}

	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return false;
	}
		
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		ItemStack hStack = context.getItemInHand();
		Player playerIn = context.getPlayer();
		boolean hasBook = false;
		
		if (!worldIn.isClientSide && playerIn != null && playerIn.canUseGameMasterBlocks()) {
			 CompoundTag compoundtag = BlockItem.getBlockEntityData(hStack);
			 if (compoundtag != null && compoundtag.contains("Book")) { hasBook = true; }
		 }
			
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
				.setValue(HAS_BOOK, Boolean.valueOf(hasBook));
	}
	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Connect the blocks. */
	protected boolean connectHalf(BlockGetter worldIn, BlockPos pos, Direction direct) {
		BlockPos newPos = pos.relative(direct);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return ((block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof WoodSlab_CM && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseFacingSlab_Water && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof Base_Slab_JP && state.getValue(Base_Slab_JP.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseTatami && state.getValue(BaseTatami.TYPE) == TatamiType.BOTTOM) ||
				block instanceof LowDesk || block instanceof Chabudai || block instanceof Kotatsu);
	}

	protected boolean connectWater(BlockGetter worldIn, BlockPos pos, Direction direct) {
		BlockPos newPos = pos.relative(direct);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return ((block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM && state.getValue(SlabBlock.WATERLOGGED)) ||
				(block instanceof WoodSlab_CM && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM && state.getValue(SlabBlock.WATERLOGGED)) ||
				(block instanceof BaseFacingSlab_Water && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM && state.getValue(SlabBlock.WATERLOGGED)) ||
				(block instanceof Base_Slab_JP && state.getValue(Base_Slab_JP.TYPE) == SlabType.BOTTOM && state.getValue(SlabBlock.WATERLOGGED)) ||
				(block instanceof BaseTatami && state.getValue(BaseTatami.TYPE) == TatamiType.BOTTOM && state.getValue(SlabBlock.WATERLOGGED)) ||
				(block instanceof LowDesk && state.getValue(LowDesk.WATERLOGGED)) ||
				(block instanceof Chabudai && state.getValue(Chabudai.WATERLOGGED)) ||
				(block instanceof Kotatsu && state.getValue(Kotatsu.WATERLOGGED)));
	}
	
	/* Distinguish LOST from WATERLOGGED. */
	protected boolean waterIn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		if (state.getValue(WATERLOGGED) || connectWater(worldIn, pos, Direction.DOWN)) { return true; }
		return false;
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) { worldIn.scheduleTick(pos, this, 10); }

		if (waterIn(state, worldIn, pos)) { worldIn.scheduleTick(pos, this, 60); }
		
		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.setValue(DOWN, down);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.scheduleTick(pos, this, 60); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { };
	}

	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}
	
	@Override
	public boolean canPlaceLiquid(BlockGetter worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return !state.getValue(BlockStateProperties.WATERLOGGED) && fluid == Fluids.WATER;
	}
	
	@Override
	public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluid) {
		if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluid.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.scheduleTick(pos, fluid.getType(), fluid.getType().getTickDelay(worldIn)); }
			return true; }
		
		else { return false; }
	}
	
	@Override
	public ItemStack pickupBlock(LevelAccessor worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(BlockStateProperties.WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)), 3);
			
			if (!state.canSurvive(worldIn, pos)) { worldIn.destroyBlock(pos, true); }
			return new ItemStack(Items.WATER_BUCKET);
		}
		
		else { return ItemStack.EMPTY; }
	}
	
	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, WATERLOGGED, HAS_BOOK, OPEN, POWERED);
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Chinjufu.SHOUHOU_empty.get());
	}
}
