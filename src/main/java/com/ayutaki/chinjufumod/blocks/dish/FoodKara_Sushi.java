package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FoodKara_Sushi extends BaseStage4_FDP {
	/* Collision */
	private static final AxisAlignedBB AABB_1_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 1.0, 0.15625, 1.0);
	private static final AxisAlignedBB AABB_1_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 1.0, 0.15625, 1.0);
	private static final AxisAlignedBB AABB_1_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 1.0, 0.15625, 1.0);
	private static final AxisAlignedBB AABB_1_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 1.0, 0.15625, 1.0);

	private static final AxisAlignedBB AABB_2_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.4375, 0.0, 0.125, 0.78125, 0.09375, 0.9375);
	private static final AxisAlignedBB AABB_2_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.4375, 0.0, 0.125, 0.78125, 0.09375, 0.9375);
	private static final AxisAlignedBB AABB_2_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.4375, 0.0, 0.125, 0.78125, 0.09375, 0.9375);
	private static final AxisAlignedBB AABB_2_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.4375, 0.0, 0.125, 0.78125, 0.09375, 0.9375);

	private static final AxisAlignedBB AABB_3_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.3125, 0.0, 0.125, 0.78125, 0.1875, 1.0);
	private static final AxisAlignedBB AABB_3_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.3125, 0.0, 0.125, 0.78125, 0.1875, 1.0);
	private static final AxisAlignedBB AABB_3_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.3125, 0.0, 0.125, 0.78125, 0.1875, 1.0);
	private static final AxisAlignedBB AABB_3_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.3125, 0.0, 0.125, 0.78125, 0.1875, 1.0);

	private static final AxisAlignedBB AABB_4_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.5625, 0.0, 0.375, 0.8125, 0.03125, 0.625);
	private static final AxisAlignedBB AABB_4_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.5625, 0.0, 0.375, 0.8125, 0.03125, 0.625);
	private static final AxisAlignedBB AABB_4_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.5625, 0.0, 0.375, 0.8125, 0.03125, 0.625);
	private static final AxisAlignedBB AABB_4_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.5625, 0.0, 0.375, 0.8125, 0.03125, 0.625);

	private static final AxisAlignedBB AABB_D1_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, -0.5, 0.0, 1.0, 0.01, 1.0);
	private static final AxisAlignedBB AABB_D1_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, -0.5, 0.0, 1.0, 0.01, 1.0);
	private static final AxisAlignedBB AABB_D1_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, -0.5, 0.0, 1.0, 0.01, 1.0);
	private static final AxisAlignedBB AABB_D1_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, -0.5, 0.0, 1.0, 0.01, 1.0);

	private static final AxisAlignedBB AABB_D2_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.4375, -0.5, 0.125, 0.78125, 0.01, 0.9375);
	private static final AxisAlignedBB AABB_D2_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.4375, -0.5, 0.125, 0.78125, 0.01, 0.9375);
	private static final AxisAlignedBB AABB_D2_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.4375, -0.5, 0.125, 0.78125, 0.01, 0.9375);
	private static final AxisAlignedBB AABB_D2_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.4375, -0.5, 0.125, 0.78125, 0.01, 0.9375);

	private static final AxisAlignedBB AABB_D3_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.3125, -0.5, 0.125, 0.78125, 0.01, 1.0);
	private static final AxisAlignedBB AABB_D3_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.3125, -0.5, 0.125, 0.78125, 0.01, 1.0);
	private static final AxisAlignedBB AABB_D3_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.3125, -0.5, 0.125, 0.78125, 0.01, 1.0);
	private static final AxisAlignedBB AABB_D3_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.3125, -0.5, 0.125, 0.78125, 0.01, 1.0);

	private static final AxisAlignedBB AABB_D4_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.5625, -0.5, 0.375, 0.8125, 0.01, 0.625);
	private static final AxisAlignedBB AABB_D4_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.5625, -0.5, 0.375, 0.8125, 0.01, 0.625);
	private static final AxisAlignedBB AABB_D4_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.5625, -0.5, 0.375, 0.8125, 0.01, 0.625);
	private static final AxisAlignedBB AABB_D4_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.5625, -0.5, 0.375, 0.8125, 0.01, 0.625);
	/** 1=寿司桶, 2=寿司下駄の空, 3=寿司セットの空, 4=醤油皿の空**/
	public FoodKara_Sushi(String name) {
		super(name);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		return (i == 4)? SoundType.STONE : SoundType.WOOD;
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		/** 1=寿司桶, 2=寿司下駄の空, 3=寿司セットの空, 4=醤油皿の空**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		boolean shouyu = (hItem == Items_Teatime.SHOUYU_bot_14 || hItem == Items_NoTab.SHOUYU_bot_24 || 
				hItem == Items_NoTab.SHOUYU_bot_34 || hItem == Items_NoTab.SHOUYU_bot_44);
		
		if (i == 1 || i == 3) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 1 && i != 3
			if (shouyu) {
				if (hItem == Items_Teatime.SHOUYU_bot_14) { CMEvents.Soysauce_to2(worldIn, pos, playerIn, hand); }
				if (hItem == Items_NoTab.SHOUYU_bot_24) { CMEvents.Soysauce_to3(worldIn, pos, playerIn, hand); }
				if (hItem == Items_NoTab.SHOUYU_bot_34) { CMEvents.Soysauce_to4(worldIn, pos, playerIn, hand); }
				if (hItem == Items_NoTab.SHOUYU_bot_44) { CMEvents.Soysauce_toBottle(worldIn, pos, playerIn, hand); }

				if (i == 2) { worldIn.setBlockState(pos, Dish_Blocks.SUSHIGETA_kara1_4.getDefaultState()
						.withProperty(SushiGeta_kara1_4.H_FACING, state.getValue(H_FACING))
						.withProperty(SushiGeta_kara1_4.DOWN, state.getValue(DOWN))
						.withProperty(SushiGeta_kara1_4.STAGE_1_4, Integer.valueOf(1))); }
				
				if (i == 4) { worldIn.setBlockState(pos, Dish_Blocks.SHOUYUSARA_1.getDefaultState()
						.withProperty(ShouyuSara_1.H_FACING, state.getValue(H_FACING))
						.withProperty(ShouyuSara_1.DOWN, state.getValue(DOWN))
						.withProperty(ShouyuSara_1.STAGE_1_4, Integer.valueOf(1))); }
			}

			else { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		/** 1=寿司桶, 2=寿司下駄の空, 3=寿司セットの空, 4=醤油皿の空**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			if (i == 2) { return flag? AABB_2_NORTH : AABB_D2_NORTH; }
			if (i == 3) { return flag? AABB_3_NORTH : AABB_D3_NORTH; }
			if (i == 4) { return flag? AABB_4_NORTH : AABB_D4_NORTH; }
			return flag? AABB_1_NORTH : AABB_D1_NORTH;
		case SOUTH:
			if (i == 2) { return flag? AABB_2_SOUTH : AABB_D2_SOUTH; }
			if (i == 3) { return flag? AABB_3_SOUTH : AABB_D3_SOUTH; }
			if (i == 4) { return flag? AABB_4_SOUTH : AABB_D4_SOUTH; }
			return flag? AABB_1_SOUTH : AABB_D1_SOUTH;
		case WEST:
			if (i == 2) { return flag? AABB_2_WEST : AABB_D2_WEST; }
			if (i == 3) { return flag? AABB_3_WEST : AABB_D3_WEST; }
			if (i == 4) { return flag? AABB_4_WEST : AABB_D4_WEST; }
			return flag? AABB_1_WEST : AABB_D1_WEST;
		case EAST:
			if (i == 2) { return flag? AABB_2_EAST : AABB_D2_EAST; }
			if (i == 3) { return flag? AABB_3_EAST : AABB_D3_EAST; }
			if (i == 4) { return flag? AABB_4_EAST : AABB_D4_EAST; }
			return flag? AABB_1_EAST : AABB_D1_EAST;
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		/** 1=寿司桶, 2=寿司下駄の空, 3=寿司セットの空, 4=醤油皿の空**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		List<ItemStack> stack = new ArrayList<ItemStack>();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.SUSHIOKE, 1, 0)); }
		if (i == 2) { stack.add(new ItemStack(Items_Teatime.SUSHIGETA_kara, 1, 0)); }
		if (i == 3) {
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 1));
			stack.add(new ItemStack(Items_Teatime.SUSHIGETA_kara, 1, 0)); }
		if (i == 4) { stack.add(new ItemStack(Items_Teatime.Item_SARA, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.SUSHIGETA_kara, 1, 0);
	}
}
