package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Curry_set extends BaseFood_Stage5Water {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(3.0D, 0.0D, 1.0D, 13.0D, 3.0D, 11.0D);
	private static final VoxelShape AABB_WEST = Block.box(5.0D, 0.0D, 3.0D, 15.0D, 3.0D, 13.0D);
	private static final VoxelShape AABB_NORTH = Block.box(3.0D, 0.0D, 5.0D, 13.0D, 3.0D, 15.0D);
	private static final VoxelShape AABB_EAST = Block.box(1.0D, 0.0D, 3.0D, 11.0D, 3.0D, 13.0D);

	private static final VoxelShape DOWN_SOUTH = Block.box(3.0D, -8.0D, 1.0D, 13.0D, 0.1D, 11.0D);
	private static final VoxelShape DOWN_WEST = Block.box(5.0D, -8.0D, 3.0D, 15.0D, 0.1D, 13.0D);
	private static final VoxelShape DOWN_NORTH = Block.box(3.0D, -8.0D, 5.0D, 13.0D, 0.1D, 15.0D);
	private static final VoxelShape DOWN_EAST = Block.box(1.0D, -8.0D, 3.0D, 11.0D, 0.1D, 13.0D);

	public Curry_set(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
				
				/** add Potion Effect. **/
				if (!worldIn.isClientSide) {
					int eTIME = (this == Dish_Blocks.CURRYSET_T.get())? 5700 : 6300;
					int eTIME2 = (this == Dish_Blocks.CURRYSET_T.get())? 3300 : 3900;
					
					if (i == 1) {
						/** 1 second =20 tick, 6000/20=300 seconds, SATURATION 2 =1 piece of MEAT **/
						playerIn.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, eTIME, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0)); }
		
					if (i == 2) {
						/** Instant HEAL, 0, 0) **/
						playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0)); }
		
					if (i == 3) {
						/** REGENERATION 3600/20=180 seconds **/
						playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, eTIME2, 0)); }
		
					if (i == 4) {
						/** DAMAGE_RESISTANCE 3600/20=180 seconds **/
						playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, eTIME2, 1)); }
				}
				
				worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_5);
		
		if (waterIn(state, worldIn, pos) && i != 5) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(5)), 3); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
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
