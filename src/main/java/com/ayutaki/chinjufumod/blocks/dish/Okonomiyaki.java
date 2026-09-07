package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

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

public class Okonomiyaki extends BaseFood_Stage5WP {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(3.5D, 0.0D, 2.5D, 12.5D, 3.0D, 11.5D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(4.5D, 0.0D, 3.5D, 13.5D, 3.0D, 12.5D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(3.5D, 0.0D, 4.5D, 12.5D, 3.0D, 13.5D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(2.5D, 0.0D, 3.5D, 11.5D, 3.0D, 12.5D);

	private static final VoxelShape DOWN_SOUTH = Block.makeCuboidShape(3.5D, -8.0D, 2.5D, 12.5D, 0.1D, 11.5D);
	private static final VoxelShape DOWN_WEST = Block.makeCuboidShape(4.5D, -8.0D, 3.5D, 13.5D, 0.1D, 12.5D);
	private static final VoxelShape DOWN_NORTH = Block.makeCuboidShape(3.5D, -8.0D, 4.5D, 12.5D, 0.1D, 13.5D);
	private static final VoxelShape DOWN_EAST = Block.makeCuboidShape(2.5D, -8.0D, 3.5D, 11.5D, 0.1D, 12.5D);

	public Okonomiyaki(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);

				/** add Potion Effect. **/
				if (!worldIn.isRemote) {
					boolean okonomi = (this == Dish_Blocks.OKONOMIC || 
							this == Dish_Blocks.OKONOMIYAKI || this == Dish_Blocks.OKONOMIS);
	
					if (okonomi) {
						int eTIME = (this == Dish_Blocks.OKONOMIC)? 2600 : 3200;
						int MEAT = (this == Dish_Blocks.OKONOMIC)? 2 : 3;
						
						if (i == 1) {
							playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, eTIME, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, MEAT, 0)); }
			
						if (i == 2) {
							playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 3, 0)); }
			
						if (i == 3) {
							playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 3, 0)); }
			
						if (i == 4) {
							playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, MEAT, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, eTIME, 0)); } }
					
					else { //!okonomi
						int eTIME = (this == Dish_Blocks.OKONOMISOBAC)? 2600 : 3200;
						int MEAT = (this == Dish_Blocks.OKONOMISOBAC)? 3 : 4;
					
						if (i == 1) {
							playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, eTIME, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 3, 0)); }
			
						if (i == 2) {
							playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, MEAT, 0)); }
			
						if (i == 3) {
							playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, MEAT, 0)); }
			
						if (i == 4) {
							playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 3, 0));
							playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, eTIME, 0)); } }
				}
				
				worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(i + 1))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_5);
		
		if (waterIn(state, worldIn, pos) && i != 5) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(5))); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();

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
