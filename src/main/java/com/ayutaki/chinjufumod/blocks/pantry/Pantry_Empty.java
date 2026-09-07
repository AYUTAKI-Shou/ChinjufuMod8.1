package com.ayutaki.chinjufumod.blocks.pantry;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlabW;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;
import com.ayutaki.chinjufumod.state.SlabHalf;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Pantry_Empty extends BaseFacingSlabW {
	/* Collision */
	private static final AxisAlignedBB DOUBLE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB BOTTOM_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	private static final AxisAlignedBB TOP_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB TOP_COLL = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

	public Pantry_Empty(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int gHC = hStack.getCount();

		boolean food8 = (hItem == Items.APPLE || hItem == Items.BEEF || hItem == Items.BEETROOT || hItem == Items.BREAD ||
				 hItem == Items.CARROT || hItem == Items.CHICKEN || hItem == Items.CHORUS_FRUIT || hItem == Items.EGG ||
				 hItem == Items.MUTTON || hItem == Items.PORKCHOP || hItem == Items.POTATO || hItem == Items.RABBIT ||
				 hItem == Items.WHEAT ||
				 hItem == Items_Teatime.FOOD_MIKAN || hItem == Items_Teatime.FOOD_CORN || hItem == Items_Teatime.FOOD_GRAPE || 
				 hItem == Items_Teatime.FOOD_ONION || hItem == Items_Teatime.HAMAGURI || hItem == Items_Teatime.SEEDS_RICE || 
				 hItem == Items_Teatime.SEEDS_SOY || hItem == Items_Teatime.FOOD_SPINACH || hItem == Items_Teatime.FOOD_TOMATO || 
				 hItem == Items_Teatime.FOOD_CHERRY || hItem == Items_Seasonal.TAKENOKO || hItem == Items_Seasonal.KURI || 
				 hItem == Items_Teatime.CHADUTSU ||hItem == Items_Teatime.CANTEA);

		boolean cocoa = (hItem == Items.DYE && k == 3);
		boolean fish = ((hItem == Items.FISH && k == 0) || (hItem == Items.FISH && k == 1));
		/** BPEPPER, CHILI, CUMIN, TURMERIC **/
		boolean spice = ((hItem == Items_Teatime.SPICE && k == 1) || (hItem == Items_Teatime.SPICE && k == 2));
		boolean spiceNae = ((hItem == Items_Teatime.SPICE_NAE && k == 1) || (hItem == Items_Teatime.SPICE_NAE && k == 2));
		
		boolean food4 = (hItem == Items_Teatime.FOOD_CABBAGE || hItem == Items_Teatime.FOOD_GREENONION);
		boolean food2 = (hItem == Items_Teatime.FOOD_HAKUSAI || hItem == Items_Teatime.IKA);
		
		if (hItem == Items.EGG && gHC < 8) { return true; } //Cancel throwing eggs.
		
		if (!state.getValue(DOUBLE)) {
			if (food8 && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, takeBlock8(hItem).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(false))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }

			if (cocoa && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Pantry_Blocks.BOX_H_COCO.getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(false))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (fish && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, fishBlock(k).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(false))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (spice && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, spiceBlock(k).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(false))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (spiceNae && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, naeBlock(k).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(false))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (food4 && gHC >= 4) {
				CMEvents.consumeN_seSnowP(4, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, takeBlock4(hItem).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(false))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (food2 && gHC >= 2) {
				CMEvents.consumeN_seSnowP(2, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, takeBlock2(hItem).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(false))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (hItem == Items_Teatime.BOX_H_EMPTY) {
				if (state.getValue(HALF) != SlabHalf.TOP && facing == EnumFacing.UP) {
					CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
					return true; }

				/** TOP はブロック下端から Double にできる **/
				if (state.getValue(DOUBLE) != true && state.getValue(HALF) == SlabHalf.TOP && (double)hitY <= 0.6D) {
					CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
					return true; }
			}
		} //!state.getValue(DOUBLE)


		if (state.getValue(DOUBLE)) { 
			if (food8 && gHC >= 16) {
				CMEvents.consumeN_seSnowP(16, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, takeBlock8(hItem).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(true))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }

			if (cocoa && gHC >= 16) {
				CMEvents.consumeN_seSnowP(16, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Pantry_Blocks.BOX_H_COCO.getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(true))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (fish && gHC >= 16) {
				CMEvents.consumeN_seSnowP(16, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, fishBlock(k).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(true))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (spice && gHC >= 16) {
				CMEvents.consumeN_seSnowP(16, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, spiceBlock(k).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(true))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (spiceNae && gHC >= 16) {
				CMEvents.consumeN_seSnowP(16, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, naeBlock(k).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(true))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (food4 && gHC >= 8) {
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, takeBlock4(hItem).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(true))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
			
			if (food2 && gHC >= 4) {
				CMEvents.consumeN_seSnowP(4, worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, takeBlock2(hItem).getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, Boolean.valueOf(true))
						.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
				return true; }
		} //state.getValue(DOUBLE)

		/** 側面で設置可能にするため false **/
		return false;
	}

	private Block takeBlock8(Item hItem) {
		if (hItem == Items.APPLE) { return Pantry_Blocks.BOX_H_APPLE; }
		if (hItem == Items.BEEF) { return Pantry_Blocks.BOX_H_BEEF; }
		if (hItem == Items.BEETROOT) { return Pantry_Blocks.BOX_H_BEETROOT; }
		if (hItem == Items.BREAD) { return Pantry_Blocks.BOX_H_BREAD; }
		if (hItem == Items.CARROT) { return Pantry_Blocks.BOX_H_CARROT; }
		if (hItem == Items.CHICKEN) { return Pantry_Blocks.BOX_H_CHICKEN; } 
		if (hItem == Items.CHORUS_FRUIT) { return Pantry_Blocks.BOX_H_CHORUS; }
		if (hItem == Items.EGG) { return Pantry_Blocks.BOX_H_EGG; }
		if (hItem == Items.WHEAT) { return Pantry_Blocks.BOX_H_FLOUR; }
		if (hItem == Items.MUTTON) { return Pantry_Blocks.BOX_H_MUTTON; }
		if (hItem == Items.PORKCHOP) { return Pantry_Blocks.BOX_H_PORK; }
		if (hItem == Items.POTATO) { return Pantry_Blocks.BOX_H_POTATO; }
		if (hItem == Items.RABBIT) { return Pantry_Blocks.BOX_H_RABBIT; }
		
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
		else { return Pantry_Blocks.BOX_H_TRED; }
	}
	
	private Block takeBlock4(Item hItem) {
		if (hItem == Items_Teatime.FOOD_CABBAGE) { return Pantry_Blocks.BOX_H_CABBAGE; }
		else { return Pantry_Blocks.BOX_H_GREENONION; }
	}
	
	private Block takeBlock2(Item hItem) {
		if (hItem == Items_Teatime.FOOD_HAKUSAI) { return Pantry_Blocks.BOX_H_HAKUSAI; }
		else { return Pantry_Blocks.BOX_H_SQUID; }
	}
	
	private Block fishBlock(int k) {
		if (k == 0) { return Pantry_Blocks.BOX_H_FISH; }
		else { return Pantry_Blocks.BOX_H_SALMON; }
	}
	
	private Block spiceBlock(int k) {
		if (k == 1) { return Pantry_Blocks.BOX_H_BPEPPER; }
		else { return Pantry_Blocks.BOX_H_CHILI; }
	}
	
	private Block naeBlock(int k) {
		if (k == 1) { return Pantry_Blocks.BOX_H_CUMIN; }
		else { return Pantry_Blocks.BOX_H_TURMERIC; }
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(DOUBLE)) { return DOUBLE_AABB; }

		else { return (state.getValue(HALF) == SlabHalf.TOP)? TOP_AABB : BOTTOM_AABB; }
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		boolean flag = state.getValue(DOUBLE);
		SlabHalf blockhalf = state.getValue(HALF);

		switch(blockhalf) {
		case TOP :
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, flag? DOUBLE_AABB : TOP_COLL); /** flag? true : false; **/
			break;
			
		case BOTTOM :
		default:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, flag? DOUBLE_AABB : BOTTOM_AABB);
			break;
		}
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	/* Rendering */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int w = state.getValue(DOUBLE)? 2 : 1;
		stack.add(new ItemStack(Items_Teatime.BOX_H_EMPTY, w, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.BOX_H_EMPTY, 1, 0);
	}
}
