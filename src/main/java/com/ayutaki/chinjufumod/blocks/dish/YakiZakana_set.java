package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class YakiZakana_set extends BaseFood_Stage5WP {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(2.0D, 0.0D, 3.0D, 14.0D, 3.0D, 11.0D);
	private static final VoxelShape AABB_WEST = Block.box(5.0D, 0.0D, 2.0D, 13.0D, 3.0D, 14.0D);
	private static final VoxelShape AABB_NORTH = Block.box(2.0D, 0.0D, 5.0D, 14.0D, 3.0D, 13.0D);
	private static final VoxelShape AABB_EAST = Block.box(3.0D, 0.0D, 2.0D, 11.0D, 3.0D, 14.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(2.0D, -8.0D, 3.0D, 14.0D, 0.1D, 11.0D);
	private static final VoxelShape DOWN_WEST = Block.box(5.0D, -8.0D, 2.0D, 13.0D, 0.1D, 14.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(2.0D, -8.0D, 5.0D, 14.0D, 0.1D, 13.0D);
	private static final VoxelShape DOWN_EAST = Block.box(3.0D, -8.0D, 2.0D, 11.0D, 0.1D, 14.0D);

	public YakiZakana_set(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
				
				/** add Potion Effect. **/
				if (!worldIn.isClientSide) {
					boolean GOHAN = (this == Dish_Blocks.YAKIZAKANATEI || this == Dish_Blocks.YAKIJYAKETEI);
					int eTIME = GOHAN? 6200 : 6300;
					int eTIME2 = GOHAN? 3800 : 3900;
					
					if (i == 1) {
						/** 6000/20=300 seconds, SATURATION 2 =1 piece of MEAT **/
						playerIn.addEffect(new EffectInstance(Effects.DIG_SPEED, eTIME, 0));
						playerIn.addEffect(new EffectInstance(Effects.SATURATION, 4, 0)); }
		
					if (i == 2) {
						/** Instant HEAL, 0, 0) **/
						playerIn.addEffect(new EffectInstance(Effects.HEAL, 0, 0));
						playerIn.addEffect(new EffectInstance(Effects.SATURATION, 4, 0)); }
		
					if (i == 3) {
						/** REGENERATION 3600/20=180 seconds **/
						playerIn.addEffect(new EffectInstance(Effects.HEAL, 0, 0));
						playerIn.addEffect(new EffectInstance(Effects.SATURATION, 4, 0));
						playerIn.addEffect(new EffectInstance(Effects.REGENERATION, eTIME2, 0)); }
		
					if (i == 4) {
						/** DAMAGE_RESISTANCE 3600/20=180 seconds, +200tick **/
						playerIn.addEffect(new EffectInstance(Effects.HEAL, 0, 0));
						playerIn.addEffect(new EffectInstance(Effects.SATURATION, 4, 0));
						playerIn.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, eTIME2, 1)); }
				}
				
				worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_5);
		
		if (waterIn(state, worldIn, pos)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);

			if (i == 5) {
				CMEvents.soundSnowBreak(worldIn, pos);
				worldIn.setBlock(pos, Dish_Blocks.TAMAGOYAKITEI.defaultBlockState()
						.setValue(Tamagoyaki_set.H_FACING, state.getValue(H_FACING))
						.setValue(Tamagoyaki_set.DOWN, state.getValue(DOWN))
						.setValue(Tamagoyaki_set.WATERLOGGED, state.getValue(WATERLOGGED))
						.setValue(Tamagoyaki_set.STAGE_1_5, Integer.valueOf(5)), 3); }
			
			else { //i != 5
				CMEvents.drop1_ROTTENFOOD(worldIn, pos);
				worldIn.setBlock(pos, Dish_Blocks.TAMAGOYAKITEI.defaultBlockState()
						.setValue(Tamagoyaki_set.H_FACING, state.getValue(H_FACING))
						.setValue(Tamagoyaki_set.DOWN, state.getValue(DOWN))
						.setValue(Tamagoyaki_set.WATERLOGGED, state.getValue(WATERLOGGED))
						.setValue(Tamagoyaki_set.STAGE_1_5, Integer.valueOf(5)), 3); }
		}
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notDown? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return notDown? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return notDown? AABB_WEST : DOWN_WEST;
		case EAST:
			return notDown? AABB_EAST : DOWN_EAST;
		}
	}
}
