package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Harvest;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class Enden extends Abstract_Harvest {
	/* Property */
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	/** 1=Water Filled, 2, 3, 4, 5=Salt_1, 6=Salt_1, 7=Salt_2, 8=Salt_2, 9=Salt_3 **/
	public static final IntegerProperty WET_1_9 = IntegerProperty.create("wet", 1, 9);

	private static final VoxelShape AABB_1 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.5D, 16.0D);
	private static final VoxelShape AABB_9 = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	public Enden(AbstractBlock.Properties props) {
		super(props);

		/** Default state **/
		registerDefaultState(this.defaultBlockState().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false))
				.setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false))
				.setValue(WET_1_9, Integer.valueOf(1)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(WET_1_9);
		/** 1=Water Filled, 2, 3, 4, 5=Salt_1, 6=Salt_1, 7=Salt_2, 8=Salt_2, 9=Salt_3 **/

		/** Can harvest **/
		if (i >= 5) {
			if (hStack.isEmpty()) {
				
				if (i == 5 || i == 6) { 
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.SHIO); }
				if (i == 7 || i == 8) { 
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SHIO, 2); }
				if (i == 9) {
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SHIO, 3);
					ItemStack take = new ItemStack(Items_Teatime.NIGARI, 1);
					if (!playerIn.inventory.add(take)) { playerIn.drop(take, false); } }
				
				worldIn.setBlock(pos, Crop_Blocks.ENDEN_k.defaultBlockState(), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i < 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }	
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return !this.defaultBlockState().canSurvive(context.getLevel(), context.getClickedPos()) ? Blocks.SAND.defaultBlockState() : this.defaultBlockState();
	}
	
	/* Connect the blocks. */
	private boolean canConnectTo(IWorld worldIn, BlockPos source, Direction direction) {
		BlockState state = worldIn.getBlockState(source.relative(direction));
		return state.getBlock() == this;
	}

	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		worldIn.getBlockTicks().scheduleTick(pos, this, 2);
		
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST);
		return state.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
		BlockState upState = worldIn.getBlockState(pos.above());
		return !upState.getMaterial().isSolid() || upState.getBlock() instanceof FenceGateBlock || upState.getBlock() instanceof MovingPistonBlock;
	}

	/* Conditions for TickRandom. */
	public static void turnToSand(BlockState state, World worldIn, BlockPos pos) {
		worldIn.setBlockAndUpdate(pos, Blocks.SAND.defaultBlockState());
	}

	/* TickRandom */
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		super.tick(state, worldIn, pos, rand);
		if (!state.canSurvive(worldIn, pos)) { turnToSand(state, worldIn, pos); }
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		BlockState upState = worldIn.getBlockState(pos.above());
		if (upState.getBlock() instanceof FlowingFluidBlock) { 
			if (rand.nextInt(2) == 0) { turnToSand(state, worldIn, pos); }
		}
		
		/** WATERLOGGED で水面より下と成るため pos.above() か **/
		if (worldIn.canSeeSky(pos.above())) {
			int i = state.getValue(WET_1_9);

			if (worldIn.isRainingAt(pos.above())) {
				if (i == 1) { }
				else { //i != 1
					if (rand.nextInt(2) == 0) { worldIn.setBlock(pos, state.setValue(WET_1_9, Integer.valueOf(i - 1)), 3); } }
			}
			
			else { //Rain == false
				/** RawBrightness is >= 14. **/
				boolean canDRY = worldIn.isDay() && worldIn.getRawBrightness(pos, 0) >= 14 && i != 9;
				if (canDRY) {
					if (rand.nextInt(6) == 0) { 
						worldIn.setBlock(pos, state.setValue(WET_1_9, Integer.valueOf(i + 1)), 3); } }
				
				else { }
			}
		}
		else { }
	}

	/* Collision */
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_9;
	}

	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int i = state.getValue(WET_1_9);
		return (i == 9)? AABB_9 : AABB_1;
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, WET_1_9);
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.ENDEN);
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.SHOVEL;
	}
	
	/* 透過 */
	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isViewBlocking(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return true;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_enden").withStyle(TextFormatting.GRAY));
	}
}
