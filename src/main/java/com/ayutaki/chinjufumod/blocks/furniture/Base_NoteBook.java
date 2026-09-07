package com.ayutaki.chinjufumod.blocks.furniture;

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

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class Base_NoteBook extends ContainerBlock implements IWaterLoggable {
	/* Property */
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final BooleanProperty DOWN = BooleanProperty.create("down");
	public static final BooleanProperty HAS_BOOK = BlockStateProperties.HAS_BOOK;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	
	public Base_NoteBook(AbstractBlock.Properties props) {
		super(props);
		registerDefaultState(this.defaultBlockState().setValue(H_FACING, Direction.NORTH)
				.setValue(OPEN, Boolean.valueOf(false))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(HAS_BOOK, Boolean.valueOf(false))
				.setValue(POWERED, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		if (state.getValue(HAS_BOOK)) {
			if (!worldIn.isClientSide) { this.openScreen(worldIn, pos, playerIn); }
			return ActionResultType.sidedSuccess(worldIn.isClientSide);
		} 
		
		else {
			if (hItem.is(ItemTags.LECTERN_BOOKS)) {
				return tryPlaceBook(worldIn, pos, state, hStack)? ActionResultType.CONSUME : ActionResultType.PASS; }
			
			if (this == Furniture_Blocks.NOTEBOOK && hItem == Items.BOOK) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, Furniture_Blocks.NOTEBOOK_B.defaultBlockState()
						.setValue(H_FACING, state.getValue(H_FACING))
						.setValue(OPEN, state.getValue(OPEN))
						.setValue(DOWN, state.getValue(DOWN))
						.setValue(HAS_BOOK, Boolean.valueOf(false))
						.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
			
			if (hStack.isEmpty()) {
				CMEvents.soundPage(worldIn, pos);
				if (this == Furniture_Blocks.NOTEBOOK_B) { CMEvents.soundWoodPlace(worldIn, pos); }
				
				worldIn.setBlock(pos, state.cycle(OPEN), 3);
				return ActionResultType.SUCCESS; }
			
			else { return ActionResultType.PASS; }
		}
	}
	
	/** Lectern **/
	@Override
	public BlockRenderType getRenderShape(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	@Nullable
	public TileEntity newBlockEntity(IBlockReader worldIn) {
		return new NoteTileEntity();
	}
	
	public static boolean tryPlaceBook(World worldIn, BlockPos pos, BlockState state, ItemStack stack) {
		if (!state.getValue(HAS_BOOK)) {
			if (!worldIn.isClientSide) { placeBook(worldIn, pos, state, stack); }
			return true;
		} 
		
		else { return false; }
	}

	private static void placeBook(World worldIn, BlockPos pos, BlockState state, ItemStack stack) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof NoteTileEntity) {
			NoteTileEntity noteTile = (NoteTileEntity)tileEntity;
			noteTile.setBook(stack.split(1));
			resetBookState(worldIn, pos, state, true);
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BOOK_PUT, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}

	public static void resetBookState(World worldIn, BlockPos pos, BlockState state, boolean hasBook) {
		worldIn.setBlock(pos, state.setValue(HAS_BOOK, Boolean.valueOf(hasBook)), 3);
		updateBelow(worldIn, pos, state);
	}
	
	public static void signalPageChange(World worldIn, BlockPos pos, BlockState state) {
		worldIn.getBlockTicks().scheduleTick(pos, state.getBlock(), 2);
		worldIn.levelEvent(1043, pos, 0);
	}
			
	private static void updateBelow(World worldIn, BlockPos pos, BlockState state) {
		worldIn.updateNeighborsAt(pos.below(), state.getBlock());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState state2, boolean hasBook) {
		if (!state.is(state2.getBlock())) {
			if (state.getValue(HAS_BOOK)) { this.popBook(state, worldIn, pos); }

			super.onRemove(state, worldIn, pos, state2, hasBook);
		}
	}

	private void popBook(BlockState state, World worldIn, BlockPos pos) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
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
	
	public int getAnalogOutputSignal(BlockState state, World worldIn, BlockPos pos) {
		return 0;
	}
		
	@Nullable
	public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
		return !state.getValue(HAS_BOOK) ? null : super.getMenuProvider(state, worldIn, pos);
	}

	protected void openScreen(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof NoteTileEntity) {
			playerIn.openMenu((NoteTileEntity)tileEntity);
			playerIn.awardStat(Stats.INTERACT_WITH_LECTERN);
		}
	}

	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		ItemStack stack = context.getItemInHand();
		CompoundNBT nbt = stack.getTag();
		PlayerEntity playerIn = context.getPlayer();
		boolean hasBook = false;
		
		if (!worldIn.isClientSide && playerIn != null && nbt != null && playerIn.canUseGameMasterBlocks() && nbt.contains("BlockEntityTag")) {
			CompoundNBT nbt1 = nbt.getCompound("BlockEntityTag");
			if (nbt1.contains("Book")) { hasBook = true; }
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
	public static boolean connectHalf(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.relative(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return ((block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof WoodSlab_CM && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseFacingSlab_Water && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof Base_Slab_JP && state.getValue(Base_Slab_JP.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseTatami && state.getValue(BaseTatami.TYPE) == TatamiType.BOTTOM) ||
				block instanceof LowDesk || block instanceof Chabudai || block instanceof Kotatsu);
	}

	protected boolean connectWater(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.relative(face);
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
	
	protected boolean waterIn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return (state.getValue(WATERLOGGED) || connectWater(worldIn, pos, Direction.DOWN));
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state, worldIn, pos)) { worldIn.getBlockTicks().scheduleTick(pos, this, 60); }
		
		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.setValue(DOWN, down);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.getBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { }
	}
	
	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluid) {
		return !state.getValue(BlockStateProperties.WATERLOGGED) && fluid == Fluids.WATER;
	}

	@Override
	public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluid) {
		if (!state.getValue(BlockStateProperties.WATERLOGGED) && fluid.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.getLiquidTicks().scheduleTick(pos, fluid.getType(), fluid.getType().getTickDelay(worldIn)); }
			return true; }
		
		else { return false; }
	}

	@Override
	public Fluid takeLiquid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(BlockStateProperties.WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER; }
		
		else { return Fluids.EMPTY; }
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, WATERLOGGED, HAS_BOOK, OPEN, POWERED);
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Chinjufu.SHOUHOU_empty);
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	@Override
	public int getHarvestLevel(BlockState state) {
		return 0;
	}
}
