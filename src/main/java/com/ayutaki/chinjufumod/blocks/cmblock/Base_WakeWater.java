package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.Optional;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Weapon;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Base_WakeWater extends Block implements BucketPickup {
	public static final MapCodec<Base_WakeWater> CODEC = simpleCodec(Base_WakeWater::new);
	@Override
	public MapCodec<? extends Base_WakeWater> codec() { return CODEC; }
	
	private static final VoxelShape AABB_BOX = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.1D, 16.0D);

	public Base_WakeWater(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem == Items_Weapon.DEVICE_SONAR.get()) { return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; }

		else {
			/** 水を汲む **/
			if (hItem == Items.GLASS_BOTTLE) {
				CMEvents.Bottle_toWaterBottle(worldIn, pos, playerIn, hand); }
	
			/** TTimeItems **/
			if (hItem == Items_Teatime.MIZUOKE.get()) {
				CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.MIZUOKE_full.get()); }
	
			if (hItem == Items_Teatime.NABE_kara.get()) {
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.NABESHIO_nama.get()); }
	
			if (hItem == Items_Teatime.KEIRYO_CUP.get()) {
				CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.KEIRYO_CUP_full.get()); }
			
			/** SUCCESS to not put anything on top. **/
			return ItemInteractionResult.SUCCESS;
		}
	} // for 1.20.6

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items.AIR);
	} // for 1.20.6

	/* BucketPickup Items.BUCKET -> Items.WATER_BUCKET */
	@Override
	public ItemStack pickupBlock(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(BlockStateProperties.WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(BlockStateProperties.WATERLOGGED, Boolean.valueOf(false)), 3);
			
			if (!state.canSurvive(worldIn, pos)) { worldIn.destroyBlock(pos, true); }
			return new ItemStack(Items.WATER_BUCKET);
		}
		
		else { return ItemStack.EMPTY; }
	} // for 20.2
	
	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Fluids.WATER.getPickupSound();
	}
}
