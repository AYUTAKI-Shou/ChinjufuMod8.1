package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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

public class Ramen extends BaseFood_Stage4Water {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(5.8D, 0.0D, 5.8D, 10.2D, 3.0D, 10.2D);
	private static final VoxelShape AABB_DOWN = Block.box(5.8D, -8.0D, 5.8D, 10.2D, 0.1D, 10.2D);
	
	public Ramen(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);

		if (hStack.isEmpty()) {
			CMEvents.soundEat(worldIn, pos);
			
			/** add Potion Effect. **/
			if (!worldIn.isClientSide) {
				/** Same as DONBURI_KATSU **/
				if (i == 1) {
					playerIn.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 4200, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3, 0)); }

				if (i == 2) {
					playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3, 0)); }

				if (i == 3) {
					playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3, 0)); }
				
				if (i == 4) {
					playerIn.addEffect(new MobEffectInstance(MobEffects.HEAL, 0, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3, 0));
					playerIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 4200, 0)); }
			}

			if (i == 4) {
				worldIn.setBlock(pos, Dish_Blocks.UDON_SU.get().defaultBlockState()
						.setValue(Udon.H_FACING, state.getValue(H_FACING))
						.setValue(Udon.DOWN, state.getValue(DOWN))
						.setValue(Udon.STAGE_1_4, Integer.valueOf(4)), 3); }

			if (i != 4) {
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			}
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
			
			/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		
		if (waterIn(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.UDON_SU.get().defaultBlockState()
					.setValue(Udon.H_FACING, state.getValue(H_FACING))
					.setValue(Udon.DOWN, state.getValue(DOWN))
					.setValue(Udon.STAGE_1_4, Integer.valueOf(4)), 3); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
	}
}
