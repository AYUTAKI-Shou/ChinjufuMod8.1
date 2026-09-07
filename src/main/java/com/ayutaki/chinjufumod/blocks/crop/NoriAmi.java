package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NoriAmi extends Block {
	public static final MapCodec<NoriAmi> CODEC = simpleCodec(NoriAmi::new);
	@Override
	public MapCodec<? extends NoriAmi> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);

	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(0.0D, -5.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public NoriAmi(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(AGE);

		/** Too early to collect **/
		if (i < 5) {
			if (hStack.isEmpty()) {
				CMEvents.textEarlyCollect(worldIn, pos, playerIn);
				return InteractionResult.SUCCESS; }
			
			else { //!empty
				return InteractionResult.PASS; }
		}	 
		
		/** Can harvest **/
		if (i >= 5) {
			if (hStack.isEmpty()) {
				if (i == 5 || i == 6) { 
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.NORI_N.get()); }
				if (i == 7) {
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.NORI_N.get(), 2); }
				
				worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(0)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
			
			return InteractionResult.SUCCESS;
		}
		/** To put it on the side of this, PASS. **/
		return InteractionResult.PASS;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState();
	}
	
	/* Limit the place. */
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.getBlock() == Blocks.WATER;
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		if (worldIn.getRawBrightness(pos, 0) >= 8 || worldIn.canSeeSky(pos)) {
			return this.mayPlaceOn(worldIn.getBlockState(downPos), worldIn, downPos); }
		
		return false;
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		return (facing == Direction.DOWN && !state.canSurvive(worldIn, pos)) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		int i = state.getValue(AGE);
		if (i < 7 && worldIn.getRawBrightness(pos, 0) >= 9) {
			if (rand.nextInt(6) == 0) { worldIn.setBlock(pos, state.setValue(AGE, Integer.valueOf(i + 1)), 3); } }

		else { }
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}
	
	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(Items_Teatime.NORIAMI.get());
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_noriami").withStyle(ChatFormatting.GRAY));
	}
}
