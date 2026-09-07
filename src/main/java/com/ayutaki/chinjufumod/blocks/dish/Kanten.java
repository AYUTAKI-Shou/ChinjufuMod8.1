package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffect;
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

public class Kanten extends BaseFood_Stage4Water {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(5.5D, 0.0D, 6.0D, 10.5D, 2.0D, 10.0D);
	private static final VoxelShape AABB_WEST = Block.box(6.0D, 0.0D, 5.5D, 10.0D, 2.0D, 10.5D);
	private static final VoxelShape AABB_NORTH = Block.box(5.5D, 0.0D, 6.0D, 10.5D, 2.0D, 10.0D);
	private static final VoxelShape AABB_EAST = Block.box(6.0D, 0.0D, 5.5D, 10.0D, 2.0D, 10.5D);

	private static final VoxelShape DOWN_SOUTH = Block.box(5.5D, -8.0D, 6.0D, 10.5D, 0.1D, 10.0D);
	private static final VoxelShape DOWN_WEST = Block.box(6.0D, -8.0D, 5.5D, 10.0D, 0.1D, 10.5D);
	private static final VoxelShape DOWN_NORTH = Block.box(5.5D, -8.0D, 6.0D, 10.5D, 0.1D, 10.0D);
	private static final VoxelShape DOWN_EAST = Block.box(6.0D, -8.0D, 5.5D, 10.0D, 0.1D, 10.5D);

	public Kanten(BlockBehaviour.Properties props) {
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
					boolean MILK = (this == Dish_Blocks.KANTEN_MILK.get());
					int MATCHA = (this == Dish_Blocks.YOKAN_MATCHA.get())? 80 : 0; /// (4560-4320)/3
					
					if (i == 1) {
						if (MILK) { playerIn.removeAllEffects();}
						else { playerIn.addEffect(new MobEffectInstance(this.takeEffect(), 1300 + MATCHA, 0));} }
		
					if (i == 2) {
						if (MILK) { playerIn.removeAllEffects();}
						else { playerIn.addEffect(new MobEffectInstance(this.takeEffect(), 1440 + MATCHA, 0)); } }
		
					if (i == 3) {
						if (MILK) { playerIn.removeAllEffects();}
						else { playerIn.addEffect(new MobEffectInstance(this.takeEffect(), 1580 + MATCHA, 0)); } }
				}
				
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	private Holder<MobEffect> takeEffect() {
		if (this == Dish_Blocks.KANTEN_APPLE.get()) { return MobEffects.DAMAGE_RESISTANCE; }
		if (this == Dish_Blocks.KANTEN_CHERRY.get()) { return MobEffects.DAMAGE_BOOST; }
		if (this == Dish_Blocks.KANTEN_CITRUS.get()) { return MobEffects.FIRE_RESISTANCE; }
		if (this == Dish_Blocks.KANTEN_GRAPE.get()) { return MobEffects.NIGHT_VISION; }
		else { return MobEffects.DIG_SPEED; }
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
