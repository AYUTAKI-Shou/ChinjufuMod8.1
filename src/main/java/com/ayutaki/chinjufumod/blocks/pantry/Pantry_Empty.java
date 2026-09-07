package com.ayutaki.chinjufumod.blocks.pantry;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class Pantry_Empty extends BaseFacingSlab_Water {
	/* BOX_H_EMPTY深底, BOX_H_EMPTY2浅底, BOX_H_EMPTY3袋 */
	/* Collision */
	private static final VoxelShape EMPTY_BOTTOM = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 0.5D, 16.0D),
			Block.box(0.0D, 0.5D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.5D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.5D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.5D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape EMPTY_TOP = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.5D, 16.0D),
			Block.box(0.0D, 8.5D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 8.5D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 8.5D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 8.5D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape EMPTY_DOUBLE = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.5D, 16.0D),
			Block.box(0.0D, 8.5D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 8.5D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 8.5D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 8.5D, 0.0D, 16.0D, 16.0D, 16.0D));

	private static final VoxelShape EMPTY2_BOTTOM = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.5D, 16.0D),
			Block.box(0.0D, 4.5D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 4.5D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 4.5D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 4.5D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape EMPTY2_TOP = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.5D, 16.0D),
			Block.box(0.0D, 12.5D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 12.5D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 12.5D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 12.5D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape EMPTY2_DOUBLE = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.5D, 16.0D),
			Block.box(0.0D, 12.5D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 12.5D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 12.5D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 12.5D, 0.0D, 16.0D, 16.0D, 16.0D));

	private static final VoxelShape EMPTY3_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape EMPTY3_TOP = VoxelShapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));

	public Pantry_Empty(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int gHC = hStack.getCount();

		boolean food8 = (hItem == Items.APPLE || hItem == Items.BEEF || hItem == Items.BEETROOT || hItem == Items.BREAD ||
				hItem == Items.CARROT || hItem == Items.CHICKEN || hItem == Items.CHORUS_FRUIT || hItem == Items.COCOA_BEANS || 
				hItem == Items.COD || hItem == Items.EGG ||
				hItem == Items.MUTTON || hItem == Items.PORKCHOP || hItem == Items.POTATO || hItem == Items.RABBIT ||
				hItem == Items.WHEAT || hItem == Items.SALMON || hItem == Items.SWEET_BERRIES || 
				 
				hItem == Items_Teatime.SEEDS_AZUKI || hItem == Items_Teatime.FOOD_MIKAN || hItem == Items_Teatime.FOOD_CORN || 
				hItem == Items_Teatime.FOOD_GRAPE || hItem == Items_Teatime.FOOD_ONION || hItem == Items_Teatime.HAMAGURI || 
				hItem == Items_Teatime.SEEDS_RICE || hItem == Items_Teatime.SEEDS_SOY || hItem == Items_Teatime.FOOD_SPINACH || 
				hItem == Items_Teatime.FOOD_TOMATO || hItem == Items_Teatime.FOOD_CHERRY || hItem == Items_Seasonal.TAKENOKO || 
				hItem == Items_Seasonal.KURI || hItem == Items_Teatime.CHADUTSU || hItem == Items_Teatime.CANTEA || 
				hItem == Items_Teatime.PEPPER_DRY || hItem == Items_Teatime.CHILIPEPPER || hItem == Items_Teatime.SEEDS_CUMIN || 
				hItem == Items_Teatime.SEEDS_TURMERIC);
		
		boolean food4 = (hItem == Items_Teatime.FOOD_CABBAGE || hItem == Items_Teatime.FOOD_GREENONION);
		boolean food2 = (hItem == Items_Teatime.FOOD_HAKUSAI || hItem == Items_Teatime.IKA);

		SlabType slabType = state.getValue(TYPE);
		
		if (hItem == Items.EGG && gHC < 8) { return ActionResultType.SUCCESS; } //Cancel throwing eggs.
		
		if (slabType != SlabType.DOUBLE) {
			if (food8 && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock8(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
			
			if (food4 && gHC >= 4) {
				CMEvents.consumeN_seSnowP(4, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock4(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
			
			if (food2 && gHC >= 2) {
				CMEvents.consumeN_seSnowP(2, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock2(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
		}
		
		if (slabType == SlabType.DOUBLE) {
			if (food8 && gHC >= 16) {
				CMEvents.consumeN_seSnowP(16, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock8(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
			
			if (food4 && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock4(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
			
			if (food2 && gHC >= 4) {
				CMEvents.consumeN_seSnowP(4, worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, takeBlock2(hItem).defaultBlockState()
						.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
						.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
						.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3);
				return ActionResultType.SUCCESS; }
		}

		return ActionResultType.PASS;
	}
	
	private Block takeBlock8(Item hItem) {
		if (hItem == Items.APPLE) { return Pantry_Blocks.BOX_H_APPLE; }
		if (hItem == Items.BEEF) { return Pantry_Blocks.BOX_H_BEEF; }
		if (hItem == Items.BEETROOT) { return Pantry_Blocks.BOX_H_BEETROOT; }
		if (hItem == Items.BREAD) { return Pantry_Blocks.BOX_H_BREAD; }
		if (hItem == Items.CARROT) { return Pantry_Blocks.BOX_H_CARROT; }
		if (hItem == Items.CHICKEN) { return Pantry_Blocks.BOX_H_CHICKEN; } 
		if (hItem == Items.CHORUS_FRUIT) { return Pantry_Blocks.BOX_H_CHORUS; }
		if (hItem == Items.COCOA_BEANS) { return Pantry_Blocks.BOX_H_COCO; }
		if (hItem == Items.COD) { return Pantry_Blocks.BOX_H_COD; }
		if (hItem == Items.EGG) { return Pantry_Blocks.BOX_H_EGG; }
		if (hItem == Items.WHEAT) { return Pantry_Blocks.BOX_H_FLOUR; }
		if (hItem == Items.MUTTON) { return Pantry_Blocks.BOX_H_MUTTON; }
		if (hItem == Items.PORKCHOP) { return Pantry_Blocks.BOX_H_PORK; }
		if (hItem == Items.POTATO) { return Pantry_Blocks.BOX_H_POTATO; }
		if (hItem == Items.RABBIT) { return Pantry_Blocks.BOX_H_RABBIT; }
		if (hItem == Items.SALMON) { return Pantry_Blocks.BOX_H_SALMON; }
		if (hItem == Items.SWEET_BERRIES) { return Pantry_Blocks.BOX_H_SWBERRY; }
				
		if (hItem == Items_Teatime.SEEDS_AZUKI) { return Pantry_Blocks.BOX_H_AZUKI; }
		if (hItem == Items_Teatime.FOOD_MIKAN) { return Pantry_Blocks.BOX_H_CITRUS; }
		if (hItem == Items_Teatime.FOOD_CORN) { return Pantry_Blocks.BOX_H_CORN; }
		if (hItem == Items_Teatime.FOOD_GRAPE) { return Pantry_Blocks.BOX_H_GRAPE; }
		if (hItem == Items_Teatime.FOOD_ONION) { return Pantry_Blocks.BOX_H_ONION; }
		if (hItem == Items_Teatime.HAMAGURI) { return Pantry_Blocks.BOX_H_ORIENTCLAM; }
		if (hItem == Items_Teatime.SEEDS_RICE) { return Pantry_Blocks.BOX_H_RICE; }
		if (hItem == Items_Teatime.SEEDS_SOY) { return Pantry_Blocks.BOX_H_SOY; }
		if (hItem == Items_Teatime.FOOD_SPINACH) { return Pantry_Blocks.BOX_H_SPINACH; }
		if (hItem == Items_Teatime.FOOD_TOMATO) { return Pantry_Blocks.BOX_H_TOMATO; }
		if (hItem == Items_Teatime.FOOD_CHERRY) { return Pantry_Blocks.BOX_H_CHERRY; }
		if (hItem == Items_Seasonal.TAKENOKO) { return Pantry_Blocks.BOX_H_TAKENOKO; }
		if (hItem == Items_Seasonal.KURI) { return Pantry_Blocks.BOX_H_KURI; }
		if (hItem == Items_Teatime.CHADUTSU) { return Pantry_Blocks.BOX_H_TGREEN; }
		if (hItem == Items_Teatime.CANTEA) { return Pantry_Blocks.BOX_H_TRED; }
		if (hItem == Items_Teatime.PEPPER_DRY) { return Pantry_Blocks.BOX_H_BPEPPER; }
		if (hItem == Items_Teatime.CHILIPEPPER) { return Pantry_Blocks.BOX_H_CHILI; }
		if (hItem == Items_Teatime.SEEDS_CUMIN) { return Pantry_Blocks.BOX_H_CUMIN; }
		else { return Pantry_Blocks.BOX_H_TURMERIC; }
	}
	
	private Block takeBlock4(Item hItem) {
		if (hItem == Items_Teatime.FOOD_CABBAGE) { return Pantry_Blocks.BOX_H_CABBAGE; }
		else { return Pantry_Blocks.BOX_H_GREENONION; }
	}
	
	private Block takeBlock2(Item hItem) {
		if (hItem == Items_Teatime.FOOD_HAKUSAI) { return Pantry_Blocks.BOX_H_HAKUSAI; }
		else { return Pantry_Blocks.BOX_H_SQUID; }
	}
	
	/* Collisions for each property. */
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		SlabType slabType = state.getValue(TYPE);
		switch (slabType) {
		case DOUBLE:
			return (this == Pantry_Blocks.BOX_H_EMPTY)? EMPTY_DOUBLE : ((this == Pantry_Blocks.BOX_H_EMPTY2)? EMPTY2_DOUBLE : VoxelShapes.block());
		case TOP:
			return (this == Pantry_Blocks.BOX_H_EMPTY)? EMPTY_TOP : ((this == Pantry_Blocks.BOX_H_EMPTY2)? EMPTY2_TOP : EMPTY3_TOP);
		default:
			return (this == Pantry_Blocks.BOX_H_EMPTY)? EMPTY_BOTTOM : ((this == Pantry_Blocks.BOX_H_EMPTY2)? EMPTY2_BOTTOM : EMPTY3_BOTTOM);
		}
	}

	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 5; }

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 20; }
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
