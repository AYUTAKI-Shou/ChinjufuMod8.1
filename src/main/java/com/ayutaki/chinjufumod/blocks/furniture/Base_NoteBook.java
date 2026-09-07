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

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
	
	public Base_NoteBook(Block.Properties props) {
		super(props);
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(OPEN, Boolean.valueOf(false))
				.with(DOWN, Boolean.valueOf(false))
				.with(HAS_BOOK, Boolean.valueOf(false))
				.with(POWERED, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		
		if (state.get(HAS_BOOK)) {
			if (!worldIn.isRemote) { this.openScreen(worldIn, pos, playerIn); }
			return ActionResultType.SUCCESS;
		} 
		
		else {
			if (hItem.isIn(ItemTags.LECTERN_BOOKS)) {
				return tryPlaceBook(worldIn, pos, state, hStack)? ActionResultType.SUCCESS: ActionResultType.PASS; }
			
			if (this == Furniture_Blocks.NOTEBOOK && hItem == Items.BOOK) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Furniture_Blocks.NOTEBOOK_B.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(OPEN, state.get(OPEN))
						.with(DOWN, state.get(DOWN))
						.with(HAS_BOOK, Boolean.valueOf(false))
						.with(WATERLOGGED, state.get(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
			
			if (hStack.isEmpty()) {
				CMEvents.soundPage(worldIn, pos);
				if (this == Furniture_Blocks.NOTEBOOK_B) { CMEvents.soundWoodPlace(worldIn, pos); }
				
				worldIn.setBlockState(pos, state.cycle(OPEN), 3);
				return ActionResultType.SUCCESS; }
			
			else { return ActionResultType.PASS; }
		}
	}
	
	/** Lectern **/
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new NoteTileEntity();
	}
	
	public static boolean tryPlaceBook(World worldIn, BlockPos pos, BlockState state, ItemStack stack) {
		if (!state.get(HAS_BOOK)) {
			if (!worldIn.isRemote) { placeBook(worldIn, pos, state, stack); }
			return true;
		} 
		
		else { return false; }
	}

	private static void placeBook(World worldIn, BlockPos pos, BlockState state, ItemStack stack) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof NoteTileEntity) {
			NoteTileEntity noteTile = (NoteTileEntity)tileEntity;
			noteTile.setBook(stack.split(1));
			resetBookState(worldIn, pos, state, true);
			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BOOK_PUT, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}

	public static void resetBookState(World worldIn, BlockPos pos, BlockState state, boolean hasBook) {
		worldIn.setBlockState(pos, state.with(HAS_BOOK, Boolean.valueOf(hasBook)), 3);
		updateBelow(worldIn, pos, state);
	}

	public static void signalPageChange(World worldIn, BlockPos pos, BlockState state) {
		worldIn.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), 2);
		worldIn.playEvent(1043, pos, 0);
	}

	private static void updateBelow(World worldIn, BlockPos pos, BlockState state) {
		worldIn.notifyNeighborsOfStateChange(pos.down(), state.getBlock());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean hasBook) {
		if (state.getBlock() != newState.getBlock()) {
			if (state.get(HAS_BOOK)) { this.dropBook(state, worldIn, pos); }

			super.onReplaced(state, worldIn, pos, newState, hasBook);
		}
	}

	private void dropBook(BlockState state, World worldIn, BlockPos pos) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof NoteTileEntity) {
			NoteTileEntity noteTile = (NoteTileEntity)tileEntity;
			Direction direction = state.get(H_FACING);
			ItemStack copy = noteTile.getBook().copy();
			float f = 0.25F * (float)direction.getXOffset();
			float f1 = 0.25F * (float)direction.getZOffset();
			ItemEntity hItem = new ItemEntity(worldIn, (double)pos.getX() + 0.5D + (double)f, (double)(pos.getY() + 1), (double)pos.getZ() + 0.5D + (double)f1, copy);
			hItem.setDefaultPickupDelay();
			worldIn.addEntity(hItem);
			noteTile.clear();
		}
	}

	public boolean canProvidePower(BlockState state) {
		return true;
	}

	public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
		return 0;
	}

	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
		if (blockState.get(HAS_BOOK)) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof NoteTileEntity) {
				return ((NoteTileEntity)tileEntity).getComparatorSignalLevel(); }
		}
		return 0;
	}

	@Nullable
	public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
		return !state.get(HAS_BOOK) ? null : super.getContainer(state, worldIn, pos);
	}

	private void openScreen(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof NoteTileEntity) {
			playerIn.openContainer((NoteTileEntity)tileEntity);
			playerIn.addStat(Stats.INTERACT_WITH_LECTERN);
		}
	}

	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER)
				.with(H_FACING, context.getPlacementHorizontalFacing().getOpposite());
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

	/* Connect the blocks. */
	protected boolean connectHalf(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return ((block instanceof SlabBlock && state.get(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof WoodSlab_CM && state.get(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseFacingSlab_Water && state.get(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof Base_Slab_JP && state.get(Base_Slab_JP.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseTatami && state.get(BaseTatami.TYPE) == TatamiType.BOTTOM) ||
				block instanceof LowDesk || block instanceof Chabudai || block instanceof Kotatsu);
	}

	protected boolean connectWater(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return ((block instanceof SlabBlock && state.get(SlabBlock.TYPE) == SlabType.BOTTOM && state.get(SlabBlock.WATERLOGGED)) ||
				(block instanceof WoodSlab_CM && state.get(SlabBlock.TYPE) == SlabType.BOTTOM && state.get(SlabBlock.WATERLOGGED)) ||
				(block instanceof BaseFacingSlab_Water && state.get(SlabBlock.TYPE) == SlabType.BOTTOM && state.get(SlabBlock.WATERLOGGED)) ||
				(block instanceof Base_Slab_JP && state.get(Base_Slab_JP.TYPE) == SlabType.BOTTOM && state.get(SlabBlock.WATERLOGGED)) ||
				(block instanceof BaseTatami && state.get(BaseTatami.TYPE) == TatamiType.BOTTOM && state.get(SlabBlock.WATERLOGGED)) ||
				(block instanceof LowDesk && state.get(LowDesk.WATERLOGGED)) ||
				(block instanceof Chabudai && state.get(Chabudai.WATERLOGGED)) ||
				(block instanceof Kotatsu && state.get(Kotatsu.WATERLOGGED)));
	}

	/* Distinguish LOST from WATERLOGGED. */
	protected boolean waterIn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return (state.get(WATERLOGGED) || connectWater(worldIn, pos, Direction.DOWN));
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		boolean down = connectHalf(worldIn, pos, Direction.DOWN);
		return state.with(DOWN, down);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state, worldIn, pos)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			CMEvents.destroyDrop_ClothB(worldIn, pos); }
		
		else { }
	}

	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return IWaterLoggable.super.canContainFluid(worldIn, pos, state, fluidIn);
	}
	
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
		return IWaterLoggable.super.receiveFluid(worldIn, pos, state, fluidStateIn);
	}

	@Override
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.get(BlockStateProperties.WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER; }
		
		else { return Fluids.EMPTY; }
	}
	
	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(DOWN, H_FACING, WATERLOGGED, HAS_BOOK, OPEN, POWERED);
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
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
