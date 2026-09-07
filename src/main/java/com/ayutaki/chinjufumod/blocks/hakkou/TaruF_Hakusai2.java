package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
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

public class TaruF_Hakusai2 extends Abstract_WaterLogged {
	public static final MapCodec<TaruF_Hakusai2> CODEC = simpleCodec(TaruF_Hakusai2::new);
	@Override
	public MapCodec<? extends TaruF_Hakusai2> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_0_9 = IntegerProperty.create("stage", 0, 9);
	/* Collision */
	private static final VoxelShape AABB_BOX = Shapes.or(Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.25D, 8.0D, 0.25D, 15.75D, 12.0D, 15.75D),
			Block.box(0.5D, 4.0D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.75D, 0.0D, 0.75D, 15.25D, 4.0D, 15.25D));

	/** 0-5=unfinished, 6=1st time, 7=2nd time, 8=3rd time, 9=last **/
	public TaruF_Hakusai2(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_0_9, Integer.valueOf(0))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_9);

		/** Can harvest **/
		if (i > 5) {
			if (hItem == Items_Teatime.SARA.get()) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.HAKUSAIDUKE.get());

				if (i == 9) {
					worldIn.setBlock(pos, Hakkou_Blocks.HAKKOU_TARU.get().defaultBlockState()
							.setValue(Taru_Hakkou.STAGE_0_5, Integer.valueOf(5)), 3); }
				else { //i != 9
					worldIn.setBlock(pos, state.setValue(STAGE_0_9, Integer.valueOf(i + 1)), 3); }
			}
			
			if (hItem != Items_Teatime.SARA.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i <= 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 100); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 100); }
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) return;

		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 100);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Hakkou_Blocks.HAKKOU_TARU.get().defaultBlockState()
					.setValue(Taru_Hakkou.STAGE_0_5, Integer.valueOf(4))
					.setValue(Taru_Hakkou.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	/* TickRandom */
	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) return;
		
		int i = state.getValue(STAGE_0_9);
		if (i >= 6) { }
		
		else { //<= 5
			if (rand.nextInt(2) == 0) {
				if (i < 5) {
					worldIn.setBlock(pos, state.setValue(STAGE_0_9, Integer.valueOf(i + 1)), 3); }

				if (i == 5) {
					worldIn.setBlock(pos, state.setValue(STAGE_0_9, Integer.valueOf(i + 1)), 3);
					CMEvents.addEXP(1, worldIn, pos); } }
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_9, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}
	
	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_taru_hakusai_f2").withStyle(ChatFormatting.GRAY));
	}
}
