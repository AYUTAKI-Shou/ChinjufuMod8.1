package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Chanoki extends Abstract_WaterLogged {
	public static final MapCodec<Chanoki> CODEC = simpleCodec(Chanoki::new);
	@Override
	public MapCodec<? extends Chanoki> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_0_7 = IntegerProperty.create("stage", 0, 7);
	
	/* Collision */
	private static final VoxelShape AABB_BASE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 0.1D, 14.0D);
	
	private static final VoxelShape AABB_0 = Shapes.or(AABB_BASE, Block.box(7.0D, 0.1D, 7.0D, 9.0D, 12.0D, 9.0D));
	private static final VoxelShape AABB_1 = Shapes.or(AABB_BASE, Block.box(3.0D, 4.0D, 3.0D, 13.0D, 13.0D, 13.0D),
			Block.box(7.0D, 0.1D, 7.0D, 9.0D, 4.0D, 9.0D));
	private static final VoxelShape AABB_2 = Shapes.or(AABB_BASE, Block.box(2.0D, 4.0D, 2.0D, 14.0D, 14.0D, 14.0D),
			Block.box(7.0D, 0.1D, 7.0D, 9.0D, 4.0D, 9.0D));
	private static final VoxelShape AABB_3 = Shapes.or(AABB_BASE, Block.box(1.5D, 4.0D, 1.5D, 14.5D, 14.5D, 14.5D),
			Block.box(7.0D, 0.1D, 7.0D, 9.0D, 4.0D, 9.0D));
	private static final VoxelShape AABB_4 = Shapes.or(AABB_BASE, Block.box(1.0D, 3.75D, 1.0D, 15.0D, 15.0D, 15.0D),
			Block.box(7.0D, 0.1D, 7.0D, 9.0D, 4.0D, 9.0D));
	private static final VoxelShape AABB_5 = Shapes.or(AABB_BASE, Block.box(0.5D, 3.5D, 0.5D, 15.5D, 15.5D, 15.5D),
			Block.box(7.0D, 0.1D, 7.0D, 9.0D, 3.5D, 9.0D));
	private static final VoxelShape AABB_7 = Shapes.or(AABB_BASE, Block.box(0.0D, 3.5D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(7.0D, 0.1D, 7.0D, 9.0D, 3.5D, 9.0D));

	public Chanoki(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_0_7, Integer.valueOf(0))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_7);

		/** Can harvest **/
		if (i == 7) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.CHABA.get(), 4);
				worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(5)), 3); }

			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 7
			if (hItem == Items.BONE_MEAL) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
				
				if (i < 6) { worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 2)), 3); }
				if (i == 6) { worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 1)), 3); } }
		
			if (hItem != Items.BONE_MEAL) {
				if (hStack.isEmpty()) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				else { //!empty
					CMEvents.textNotHave(worldIn, pos, playerIn); } }
		}
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}
	
	/* Limit the place. */
	@SuppressWarnings("deprecation")
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return (state.is(BlockTags.DIRT) && state.isSolid() && !state.is(BlockTags.ANIMALS_SPAWNABLE_ON));
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		return this.mayPlaceOn(worldIn.getBlockState(downPos), worldIn, downPos);
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		/** pos.up() = Replaceable block. **/
		if (pos.getY() < worldIn.getMaxBuildHeight() - 1 && worldIn.getBlockState(pos.above()).canBeReplaced(context)) {
			return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }

		else { return null; }
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return (facing == Direction.DOWN && !state.canSurvive(worldIn, pos)) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom. Slow growth rate. rand.nextInt(12) == 0 */
	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		
		int i = state.getValue(STAGE_0_7);
		if (i != 7 && worldIn.getRawBrightness(pos, 0) >= 9 && !state.getValue(WATERLOGGED)) {
			if (rand.nextInt(8) == 0) {
				worldIn.setBlock(pos, state.setValue(STAGE_0_7, Integer.valueOf(i + 1)), 2); } }

		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		int i = state.getValue(STAGE_0_7);
		
		switch (i) {
		case 0:
		default: return AABB_0;
		case 1: return AABB_1;
		case 2: return AABB_2;
		case 3: return AABB_3;
		case 4: return AABB_4;
		case 5:
		case 6: return AABB_5;
		case 7: return AABB_7;
		} // switch
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_7, WATERLOGGED);
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.CHANOKI.get());
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_wood_chanoki_nae").withStyle(ChatFormatting.GRAY));
	}
}
