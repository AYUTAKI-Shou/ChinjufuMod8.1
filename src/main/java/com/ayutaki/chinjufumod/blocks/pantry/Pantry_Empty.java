package com.ayutaki.chinjufumod.blocks.pantry;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pantry_Empty extends BaseFacingSlab_Water {
	/* Collision */
	private static final VoxelShape EMPTY_BOTTOM = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.5D, 16.0D),
			Block.box(0.0D, 0.5D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.5D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.5D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.5D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape EMPTY_TOP = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.5D, 16.0D),
			Block.box(0.0D, 8.5D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 8.5D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 8.5D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 8.5D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape EMPTY_DOUBLE = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.5D, 16.0D),
			Block.box(0.0D, 8.5D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 8.5D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 8.5D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 8.5D, 0.0D, 16.0D, 16.0D, 16.0D));

	private static final VoxelShape EMPTY2_BOTTOM = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.5D, 16.0D),
			Block.box(0.0D, 4.5D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 4.5D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 4.5D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 4.5D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape EMPTY2_TOP = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.5D, 16.0D),
			Block.box(0.0D, 12.5D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 12.5D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 12.5D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 12.5D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape EMPTY2_DOUBLE = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.5D, 16.0D),
			Block.box(0.0D, 12.5D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 12.5D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 12.5D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 12.5D, 0.0D, 16.0D, 16.0D, 16.0D));

	private static final VoxelShape EMPTY3_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape EMPTY3_TOP = Shapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));

	/* BOX_H_EMPTY深底, BOX_H_EMPTY2浅底, BOX_H_EMPTY3袋 */
	public Pantry_Empty(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int gHC = hStack.getCount();

		boolean food8 = (hItem == Items.APPLE || hItem == Items.BEEF || hItem == Items.BEETROOT || hItem == Items.BREAD ||
				 hItem == Items.CARROT || hItem == Items.CHICKEN || hItem == Items.CHORUS_FRUIT || hItem == Items.COCOA_BEANS || 
				 hItem == Items.COD || hItem == Items.EGG ||
				 hItem == Items.MUTTON || hItem == Items.PORKCHOP || hItem == Items.POTATO || hItem == Items.RABBIT ||
				 hItem == Items.WHEAT || hItem == Items.SALMON || hItem == Items.SWEET_BERRIES || 
				 
				hItem == Items_Teatime.SEEDS_AZUKI.get() || hItem == Items_Teatime.FOOD_MIKAN.get() || hItem == Items_Teatime.FOOD_CORN.get() || 
				hItem == Items_Teatime.FOOD_GRAPE.get() || hItem == Items_Teatime.FOOD_ONION.get() || hItem == Items_Teatime.HAMAGURI.get() || 
				hItem == Items_Teatime.SEEDS_RICE.get() || hItem == Items_Teatime.SEEDS_SOY.get() || hItem == Items_Teatime.FOOD_SPINACH.get() || 
				hItem == Items_Teatime.FOOD_TOMATO.get() || hItem == Items_Teatime.FOOD_CHERRY.get() || hItem == Items_Seasonal.TAKENOKO.get() || 
				hItem == Items_Seasonal.KURI.get() || hItem == Items_Teatime.CHADUTSU.get() || hItem == Items_Teatime.CANTEA.get() || 
				hItem == Items_Teatime.PEPPER_DRY.get() || hItem == Items_Teatime.CHILIPEPPER.get() || hItem == Items_Teatime.SEEDS_CUMIN.get() || 
				hItem == Items_Teatime.SEEDS_TURMERIC.get());
		
		boolean food4 = (hItem == Items_Teatime.FOOD_CABBAGE.get() || hItem == Items_Teatime.FOOD_GREENONION.get());
		boolean food2 = (hItem == Items_Teatime.FOOD_HAKUSAI.get() || hItem == Items_Teatime.IKA.get());
		
		SlabType slabType = state.getValue(TYPE);
		
		if (hItem == Items.EGG&& gHC < 8) { return InteractionResult.SUCCESS; } //Cancel throwing eggs.
		
		if (slabType != SlabType.DOUBLE) {
			if (food8 && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);		
				worldIn.setBlock(pos, takeBlock8(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return InteractionResult.SUCCESS; }
			
			if (food4 && gHC >= 4) {
				CMEvents.consumeN_seSnowP(4, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock4(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return InteractionResult.SUCCESS; }
			
			if (food2 && gHC >= 2) {
				CMEvents.consumeN_seSnowP(2, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock2(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return InteractionResult.SUCCESS; }
		}
		
		if (slabType == SlabType.DOUBLE) {
			if (food8 && gHC >= 16) {
				CMEvents.consumeN_seSnowP(16, worldIn, pos, playerIn, hand);	
				worldIn.setBlock(pos, takeBlock8(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return InteractionResult.SUCCESS; }
			
			if (food4 && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock4(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return InteractionResult.SUCCESS; }
			
			if (food2 && gHC >= 4) {
				CMEvents.consumeN_seSnowP(4, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock2(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return InteractionResult.SUCCESS; }
		}

		return InteractionResult.PASS;
	}
	
	private Block takeBlock8(Item hItem) {
		if (hItem == Items.APPLE) { return Pantry_Blocks.BOX_H_APPLE.get(); }
		if (hItem == Items.BEEF) { return Pantry_Blocks.BOX_H_BEEF.get(); }
		if (hItem == Items.BEETROOT) { return Pantry_Blocks.BOX_H_BEETROOT.get(); }
		if (hItem == Items.BREAD) { return Pantry_Blocks.BOX_H_BREAD.get(); }
		if (hItem == Items.CARROT) { return Pantry_Blocks.BOX_H_CARROT.get(); }
		if (hItem == Items.CHICKEN) { return Pantry_Blocks.BOX_H_CHICKEN.get(); } 
		if (hItem == Items.CHORUS_FRUIT) { return Pantry_Blocks.BOX_H_CHORUS.get(); }
		if (hItem == Items.COCOA_BEANS) { return Pantry_Blocks.BOX_H_COCO.get(); }
		if (hItem == Items.COD) { return Pantry_Blocks.BOX_H_COD.get(); }
		if (hItem == Items.EGG) { return Pantry_Blocks.BOX_H_EGG.get(); }
		if (hItem == Items.WHEAT) { return Pantry_Blocks.BOX_H_FLOUR.get(); }
		if (hItem == Items.MUTTON) { return Pantry_Blocks.BOX_H_MUTTON.get(); }
		if (hItem == Items.PORKCHOP) { return Pantry_Blocks.BOX_H_PORK.get(); }
		if (hItem == Items.POTATO) { return Pantry_Blocks.BOX_H_POTATO.get(); }
		if (hItem == Items.RABBIT) { return Pantry_Blocks.BOX_H_RABBIT.get(); }
		if (hItem == Items.SALMON) { return Pantry_Blocks.BOX_H_SALMON.get(); }
		if (hItem == Items.SWEET_BERRIES) { return Pantry_Blocks.BOX_H_SWBERRY.get(); }

		if (hItem == Items_Teatime.SEEDS_AZUKI.get()) { return Pantry_Blocks.BOX_H_AZUKI.get(); }
		if (hItem == Items_Teatime.FOOD_MIKAN.get()) { return Pantry_Blocks.BOX_H_CITRUS.get(); }
		if (hItem == Items_Teatime.FOOD_CORN.get()) { return Pantry_Blocks.BOX_H_CORN.get(); }
		if (hItem == Items_Teatime.FOOD_GRAPE.get()) { return Pantry_Blocks.BOX_H_GRAPE.get(); }
		if (hItem == Items_Teatime.FOOD_ONION.get()) { return Pantry_Blocks.BOX_H_ONION.get(); }
		if (hItem == Items_Teatime.HAMAGURI.get()) { return Pantry_Blocks.BOX_H_ORIENTCLAM.get(); }
		if (hItem == Items_Teatime.SEEDS_RICE.get()) { return Pantry_Blocks.BOX_H_RICE.get(); }
		if (hItem == Items_Teatime.SEEDS_SOY.get()) { return Pantry_Blocks.BOX_H_SOY.get(); }
		if (hItem == Items_Teatime.FOOD_SPINACH.get()) { return Pantry_Blocks.BOX_H_SPINACH.get(); }
		if (hItem == Items_Teatime.FOOD_TOMATO.get()) { return Pantry_Blocks.BOX_H_TOMATO.get(); }
		if (hItem == Items_Teatime.FOOD_CHERRY.get()) { return Pantry_Blocks.BOX_H_CHERRY.get(); }
		if (hItem == Items_Seasonal.TAKENOKO.get()) { return Pantry_Blocks.BOX_H_TAKENOKO.get(); }
		if (hItem == Items_Seasonal.KURI.get()) { return Pantry_Blocks.BOX_H_KURI.get(); }
		if (hItem == Items_Teatime.CHADUTSU.get()) { return Pantry_Blocks.BOX_H_TGREEN.get(); }
		if (hItem == Items_Teatime.CANTEA.get()) { return Pantry_Blocks.BOX_H_TRED.get(); }
		if (hItem == Items_Teatime.PEPPER_DRY.get()) { return Pantry_Blocks.BOX_H_BPEPPER.get(); }
		if (hItem == Items_Teatime.CHILIPEPPER.get()) { return Pantry_Blocks.BOX_H_CHILI.get(); }
		if (hItem == Items_Teatime.SEEDS_CUMIN.get()) { return Pantry_Blocks.BOX_H_CUMIN.get(); }
		else { return Pantry_Blocks.BOX_H_TURMERIC.get(); }
	}
	
	private Block takeBlock4(Item hItem) {
		if (hItem == Items_Teatime.FOOD_CABBAGE.get()) { return Pantry_Blocks.BOX_H_CABBAGE.get(); }
		else { return Pantry_Blocks.BOX_H_GREENONION.get(); }
	}
	
	private Block takeBlock2(Item hItem) {
		if (hItem == Items_Teatime.FOOD_HAKUSAI.get()) { return Pantry_Blocks.BOX_H_HAKUSAI.get(); }
		else { return Pantry_Blocks.BOX_H_SQUID.get(); }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		SlabType slabType = state.getValue(TYPE);
		switch (slabType) {
		case DOUBLE:
			return (this == Pantry_Blocks.BOX_H_EMPTY.get())? EMPTY_DOUBLE : ((this == Pantry_Blocks.BOX_H_EMPTY2.get())? EMPTY2_DOUBLE : Shapes.block());
		case TOP:
			return (this == Pantry_Blocks.BOX_H_EMPTY.get())? EMPTY_TOP : ((this == Pantry_Blocks.BOX_H_EMPTY2.get())? EMPTY2_TOP : EMPTY3_TOP);
		default:
			return (this == Pantry_Blocks.BOX_H_EMPTY.get())? EMPTY_BOTTOM : ((this == Pantry_Blocks.BOX_H_EMPTY2.get())? EMPTY2_BOTTOM : EMPTY3_BOTTOM);
		}
	}
	
	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }
	
}
