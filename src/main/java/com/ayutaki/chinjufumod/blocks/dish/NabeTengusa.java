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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NabeTengusa extends Abstract_CookNabe {
	public static final MapCodec<NabeTengusa> CODEC = simpleCodec(NabeTengusa::new);
	@Override
	public MapCodec<? extends NabeTengusa> codec() { return CODEC; }
	
	protected static final int COOK_TIME = 800;
	/* Property */
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	
	public NabeTengusa(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);
		
		if (i == 3) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake_NItem(worldIn, pos, playerIn, Items.BONE_MEAL, 2);
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 4) {
			Item hItem = hStack.getItem();
			int gHC = hStack.getCount();
			boolean NS = (state.getValue(H_FACING) == Direction.NORTH) || (state.getValue(H_FACING) == Direction.SOUTH);
			int FACE = NS? 0 : 8;
			
			if (hItem == Items.APPLE) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Dish_Blocks.NABEK_APPLE.get().defaultBlockState()
							.setValue(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_CHERRY.get()) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Dish_Blocks.NABEK_CHERRY.get().defaultBlockState()
							.setValue(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_MIKAN.get()) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Dish_Blocks.NABEK_CITRUS.get().defaultBlockState()
							.setValue(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_GRAPE.get()) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, Dish_Blocks.NABEK_GRAPE.get().defaultBlockState()
							.setValue(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			
			if (hItem == Items_Teatime.MIZUOKE_Milk.get()) {
				CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, Dish_Blocks.NABEK_MILK.get().defaultBlockState()
						.setValue(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
			
			if (hItem == Items.MILK_BUCKET) {
				CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, Dish_Blocks.NABEK_MILK.get().defaultBlockState()
						.setValue(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
			
			if (hItem == Items_Teatime.ANKO.get()) {
				CMEvents.mode1Through_Consume(playerIn, hand, Items.BOWL);
				CMEvents.soundSnowPlace(worldIn, pos);
				worldIn.setBlock(pos, Dish_Blocks.NABEK_YOKAN.get().defaultBlockState()
						.setValue(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }

			if (hItem != Items.APPLE && hItem != Items_Teatime.FOOD_CHERRY.get() &&
				hItem != Items_Teatime.FOOD_MIKAN.get() && hItem != Items_Teatime.FOOD_GRAPE.get() && 
				hItem != Items_Teatime.MIZUOKE_Milk.get() && hItem != Items.MILK_BUCKET && 
				(hItem != Items_Teatime.ANKO.get())) {
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		if (i <= 2) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
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
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			
			else { } }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			
			else { } }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_4);
			if (i <= 2 && cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
 
			else { } }
		
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
					.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (cookingIn(worldIn, pos)) {
			int i = state.getValue(STAGE_1_4);
			if (i != 1) {
				if (rand.nextDouble() < 0.25D) {
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
		blockTip.add(Component.translatable("tips.block_food_nabetengusa_n").withStyle(ChatFormatting.RED));
	}
}
