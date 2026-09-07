package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class NabeCream extends Abstract_CookNabe {
	public static final MapCodec<NabeCream> CODEC = simpleCodec(NabeCream::new);
	@Override
	public MapCodec<? extends NabeCream> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	
	public NabeCream(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);
		
		if (this == Dish_Blocks.NABE_CREAM_sub.get()) { 
			if (i == 4) {
				Item hItem = hStack.getItem();
				if (hItem == Items.BOWL) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.CUSTARD_CREAM.get());
					worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
							.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
							.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4)), 3); }
				
				if (hItem != Items.BOWL) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			else {
				if (cookingIn(worldIn, pos)) {
					if (hStack.isEmpty()) {
						CMEvents.soundSnowBreak(worldIn, pos);
						worldIn.setBlock(pos, Dish_Blocks.NABE_CREAM.get().defaultBlockState()
								.setValue(H_FACING, state.getValue(H_FACING))
								.setValue(STAGE_1_4, Integer.valueOf(i + 1))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
					
					else { //!empty
						CMEvents.textFullItem(worldIn, pos, playerIn); }
				}
				else { CMEvents.textRequestHeat(worldIn, pos, playerIn); }
			}
		}
		
		else {
			if (cookingIn(worldIn, pos)) {
				if (hStack.isEmpty()) {
					CMEvents.soundSnowBreak(worldIn, pos);
					worldIn.setBlock(pos, Dish_Blocks.NABE_CREAM_sub.get().defaultBlockState()
							.setValue(H_FACING, state.getValue(H_FACING))
							.setValue(STAGE_1_4, Integer.valueOf(i))
							.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
			}
			else { CMEvents.textRequestHeat(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		if (waterIn(state)) { tick.scheduleTick(pos, this, 30); }
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 30); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 30);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
					.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
		
		else { }
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;

		if (cookingIn(worldIn, pos)) {
			int i = state.getValue(STAGE_1_4);
			if (this == Dish_Blocks.NABE_CREAM_sub.get() && i == 4) {
				double d4 = rand.nextDouble() * 0.6D - 0.3D;
				double d6 = rand.nextDouble() * 6.0D / 16.0D;
				if (rand.nextDouble() < 0.1D) {
					/** which, position x y z, speed x y z **/
					worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
			}
			
			if (rand.nextDouble() < 0.1D) {
				worldIn.playLocalSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU.get(), SoundSource.BLOCKS, 0.5F, 0.7F, false); }
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_4, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, blockTip, tipFlag);
		blockTip.add(Component.translatable("tips.block_food_nabecream").withStyle(ChatFormatting.GRAY));
		blockTip.add(Component.translatable("tips.take_bowl").withStyle(ChatFormatting.GRAY));
	}
}
