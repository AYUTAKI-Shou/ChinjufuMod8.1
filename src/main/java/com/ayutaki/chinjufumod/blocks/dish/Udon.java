package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Udon extends BaseFood_Stage4WP {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(5.8D, 0.0D, 5.8D, 10.2D, 3.0D, 10.2D);
	private static final VoxelShape AABB_DOWN = Block.makeCuboidShape(5.8D, -8.0D, 5.8D, 10.2D, 0.1D, 10.2D);

	public Udon(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		
		int i = state.get(STAGE_1_4);
		boolean su_udon = (this == Dish_Blocks.UDON_SU);
		
		/** 肉うどん **/
		if (hItem == Items.COOKED_BEEF) {
			if (su_udon && i == 1) {
				CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
				
				worldIn.setBlockState(pos, Dish_Blocks.UDON_NIKU.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(DOWN, state.get(DOWN))
						.with(STAGE_1_4, Integer.valueOf(1))); }
			
			else { //!su_udon || i != 1
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}

		/** 月見うどん **/
		if (hItem == Items.EGG) {
			if (su_udon && i == 1) {
				CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
				
				worldIn.setBlockState(pos, Dish_Blocks.UDON_TSUKIMI.getDefaultState()
						.with(H_FACING, state.get(H_FACING))
						.with(DOWN, state.get(DOWN))
						.with(STAGE_1_4, Integer.valueOf(1))); }
			
			else { //!su_udon || i != 1
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}

		if (hItem != Items.COOKED_BEEF && hItem != Items.EGG) {
			if (hStack.isEmpty()) {
				if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
				
				else { //i != 4
					CMEvents.soundEat(worldIn, pos);
					
					/** add Potion Effect. **/
					if (!worldIn.isRemote) {
						if (su_udon) { playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 2, 0)); }
						
						else { //!su_udon
							if (i == 1) { 
								/** 3600/20=180 seconds, SATURATION 2 =1 piece of MEAT **/
								playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 3600, 0));
								playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 4, 0)); }
							
							if (i == 2) { 
								/** Instant HEAL, 0, 0) **/
								playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0, 0));
								playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 4, 0)); }
							
							if (i == 3) { 
								/** REGENERATION 3600/20=180 seconds **/
								playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0, 0));
								playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 4, 0));
								playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 3600, 0)); } }
					}
		
					worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1))); } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_4);
		
		if (waterIn(state, worldIn, pos) && i != 4) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(4))); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
	}
}
