package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class NabeTengusa extends Abstract_CookNabe {

	protected static final int COOK_TIME = 800;
	/* Property */
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	
	public NabeTengusa(Block.Properties props) {
		super(props);
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_4);
		
		if (i == 3) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake_NItem(worldIn, pos, playerIn, Items.BONE_MEAL, 2);
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(4)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 4) {
			Item hItem = hStack.getItem();
			int gHC = hStack.getCount();
			boolean NS = (state.get(H_FACING) == Direction.NORTH) || (state.get(H_FACING) == Direction.SOUTH);
			int FACE = NS? 0 : 8;
			
			if (hItem == Items.APPLE) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEK_APPLE.getDefaultState()
							.with(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_CHERRY) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEK_CHERRY.getDefaultState()
							.with(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_MIKAN) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEK_CITRUS.getDefaultState()
							.with(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_GRAPE) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEK_GRAPE.getDefaultState()
							.with(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			
			if (hItem == Items_Teatime.MIZUOKE_Milk) {
				CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Dish_Blocks.NABEK_MILK.getDefaultState()
						.with(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
			
			if (hItem == Items.MILK_BUCKET) {
				CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Dish_Blocks.NABEK_MILK.getDefaultState()
						.with(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }
			
			if (hItem == Items_Teatime.ANKO) {
				CMEvents.mode1Through_Consume(playerIn, hand, Items.BOWL);
				CMEvents.soundSnowPlace(worldIn, pos);
				worldIn.setBlockState(pos, Dish_Blocks.NABEK_YOKAN.getDefaultState()
						.with(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE)), 3); }

			if (hItem != Items.APPLE && hItem != Items_Teatime.FOOD_CHERRY &&
				hItem != Items_Teatime.FOOD_MIKAN && hItem != Items_Teatime.FOOD_GRAPE && 
				hItem != Items_Teatime.MIZUOKE_Milk && hItem != Items.MILK_BUCKET && 
				(hItem != Items_Teatime.ANKO)) {
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		if (i <= 2) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			
			else { } }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		
		if (waterOUT(state)) {
			int i = state.get(STAGE_1_4);
			if (i <= 2 && cookingIn(worldIn, pos)) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
 
			else { } }
		
		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
					.with(Nabe_kara.H_FACING, state.get(H_FACING))
					.with(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.with(Nabe_kara.WATERLOGGED, state.get(WATERLOGGED)), 3); }
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (cookingIn(worldIn, pos)) {
			int i = state.get(STAGE_1_4);
			
			if (i != 1) {
				if (rand.nextDouble() < 0.25D) {
					/** which, position x y z, speed x y z **/
					worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
			}
			
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }
		}
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_4, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_food_nabetengusa_n").applyTextStyle(TextFormatting.RED));
	}
}
