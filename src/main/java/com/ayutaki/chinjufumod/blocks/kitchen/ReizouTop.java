package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.tileentity.AbstractReizouTileEntity;
import com.ayutaki.chinjufumod.tileentity.ReizouTop_TileEntity;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.ChestType;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class ReizouTop extends AbstractReizouTop<ReizouTop_TileEntity> {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final BooleanProperty RIGHT = BooleanProperty.create("right");
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;

	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 13.4D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(2.6D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 2.6D, 16.0D, 10.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 13.4D, 10.0D, 16.0D);
	
	public ReizouTop(Block.Properties props, Supplier<TileEntityType<? extends ReizouTop_TileEntity>> entityType) {
		super(props, entityType);
		this.setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(WATERLOGGED, Boolean.valueOf(false))
				.with(OPEN, Boolean.valueOf(false))
				.with(RIGHT, Boolean.valueOf(false))
				.with(POWERED, Boolean.valueOf(false))
				.with(TYPE, ChestType.SINGLE));
	}

	public static TileEntityMerger.Type func_226919_h_(BlockState state) {
		return TileEntityMerger.Type.SINGLE;
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL; // BlockRenderType.MODEL でブロックのモデルを表示
	}
	
	///IForgeBlock//////////
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ReizouTop_TileEntity();
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

	/* Waterlogged */
	@SuppressWarnings("deprecation")
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	@Nullable
	private Direction candidatePartnerFacing(BlockItemUseContext context, Direction direct) {
		BlockState state = context.getWorld().getBlockState(context.getPos().offset(direct));
		return state.getBlock() == this && state.get(TYPE) == ChestType.SINGLE ? state.get(H_FACING) : null;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (stack.hasDisplayName()) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof ReizouTop_TileEntity) {
				((ReizouTop_TileEntity)tileEntity).setCustomName(stack.getDisplayName());
			}
		}
	}

	/** Chest -> Furnace **/
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			
			if (tileEntity instanceof AbstractReizouTileEntity) {
				InventoryHelper.dropItems(worldIn, pos, ((AbstractReizouTileEntity) tileEntity).getItems());
				((AbstractReizouTileEntity)tileEntity).dropExp(worldIn, pos);
				worldIn.updateComparatorOutputLevel(pos, this);
			}
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		if (worldIn.isRemote) { return ActionResultType.SUCCESS; }

		if (!worldIn.isRemote) {
			if (state.get(WATERLOGGED)) { 
				CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
			
			else { //!WATERLOGGED
				Direction direction = state.get(H_FACING);
				TileEntity tile = worldIn.getTileEntity(pos);
				
				BlockState northState = worldIn.getBlockState(pos.north());
				BlockState southState = worldIn.getBlockState(pos.south());
				BlockState eastState = worldIn.getBlockState(pos.east());
				BlockState westState = worldIn.getBlockState(pos.west());
				
				switch (direction) {
				case NORTH:
				default:
					if (northState.getMaterial().isReplaceable()) {
						if (northState.getBlock() instanceof FlowingFluidBlock) {
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						
						else { //!Fluid
							if (tile instanceof ReizouTop_TileEntity) {
								NetworkHooks.openGui((ServerPlayerEntity)playerIn, (ReizouTop_TileEntity)tile, pos); } }
					}
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn); }
					break;

				case SOUTH:
					if (southState.getMaterial().isReplaceable()) {
						if (southState.getBlock() instanceof FlowingFluidBlock) {
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						
						else { //!Fluid
							if (tile instanceof ReizouTop_TileEntity) {
								NetworkHooks.openGui((ServerPlayerEntity)playerIn, (ReizouTop_TileEntity)tile, pos); } }
					}
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn); }
					break;

				case EAST:
					if (eastState.getMaterial().isReplaceable()) {
						if (eastState.getBlock() instanceof FlowingFluidBlock) {
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						
						else { //!Fluid
							if (tile instanceof ReizouTop_TileEntity) {
								NetworkHooks.openGui((ServerPlayerEntity)playerIn, (ReizouTop_TileEntity)tile, pos); } }
					}
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn); }
					break;
					
				case WEST:
					if (westState.getMaterial().isReplaceable()) {
						if (westState.getBlock() instanceof FlowingFluidBlock) {
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						
						else { //!Fluid
							if (tile instanceof ReizouTop_TileEntity) {
								NetworkHooks.openGui((ServerPlayerEntity)playerIn, (ReizouTop_TileEntity)tile, pos); } }
					}
					
					else { //!Replaceable
						CMEvents.textIsBlocked(worldIn, pos, playerIn); }
					break;
				} // switch
			} // !state.get(WATERLOGGED)
		}
		return ActionResultType.SUCCESS;
	}

	protected Stat<ResourceLocation> getOpenStat() {
		return Stats.CUSTOM.get(Stats.OPEN_CHEST);
	}

	@Nullable
	public static IInventory getContainer(ReizouTop block, BlockState state, World worldIn, BlockPos pos, boolean flag) {
		return (IInventory)worldIn.getTileEntity(pos);
	}

	public TileEntityMerger.ICallbackWrapper<? extends ReizouTop_TileEntity> combine(BlockState state, World worldIn, BlockPos pos, boolean flag) {
		return TileEntityMerger.ICallback::func_225537_b_;
	}

	@OnlyIn(Dist.CLIENT)
	public static TileEntityMerger.ICallback<ReizouTop_TileEntity, Float2FloatFunction> opennessCombiner(final IChestLid lis) {
		return new TileEntityMerger.ICallback<ReizouTop_TileEntity, Float2FloatFunction>() {
			public Float2FloatFunction func_225539_a_(ReizouTop_TileEntity tileEntity_1, ReizouTop_TileEntity tileEntity_2) {
				return (p_226921_2_) -> {
					return Math.max(tileEntity_1.getLidAngle(p_226921_2_), tileEntity_2.getLidAngle(p_226921_2_));
				};
			}

			public Float2FloatFunction func_225538_a_(ReizouTop_TileEntity tileEntity) {
				return tileEntity::getLidAngle;
			}

			public Float2FloatFunction func_225537_b_() {
				return lis::getLidAngle;
			}
		};
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new ReizouTop_TileEntity();
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState state, World worldIn, BlockPos pos) {
		return Container.calcRedstoneFromInventory(getContainer(this, state, worldIn, pos, false));
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, OPEN, WATERLOGGED, POWERED, RIGHT, TYPE);
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType path) {
		return false;
	}
	
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
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
		} // switch
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.KIT_REIZOU, 1);
	}

	/* Destroy at the same time. & Drop item. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		BlockState downState = worldIn.getBlockState(pos.down());
		/** False is not Drop. **/
		if (downState.getBlock() == Kitchen_Blocks.KIT_REIZOU) {
			worldIn.destroyBlock(pos.down(), false);
		}
		super.onBlockHarvested(worldIn, pos, state, playerIn);
	}
}
