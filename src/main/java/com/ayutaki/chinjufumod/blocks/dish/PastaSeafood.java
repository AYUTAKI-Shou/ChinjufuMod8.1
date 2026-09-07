package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.core.BlockPos;
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

public class PastaSeafood extends BaseFood_Stage4Water {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 2.0D, 12.0D);
	private static final VoxelShape AABB_DOWN = Block.box(4.0D, -8.0D, 4.0D, 12.0D, 0.1D, 12.0D);

	public PastaSeafood(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);

		if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 4
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
				
				/** add Potion Effect. **/
				if (!worldIn.isClientSide) {
					if (i == 1) {
						/** 3600/20=180 seconds, SATURATION 2 =1 piece of MEAT **/
						playerIn.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 4200, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0)); }
		
					if (i == 2) {
						/** Instant HEAL, 0, 0) **/
						playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0)); }
		
					if (i == 3) {
						/** REGENERATION 3600/20=180 seconds **/
						playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 4200, 0));
						playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 4, 0)); }
				}
				
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_4);
		
		if (waterIn(state, worldIn, pos) && i != 4) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }
		
		else { }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
	}
}
