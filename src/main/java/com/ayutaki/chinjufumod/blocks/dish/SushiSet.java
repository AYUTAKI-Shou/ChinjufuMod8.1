package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
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

public class SushiSet extends BaseStage4_FDA {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.3125, 0.0, 0.125, 0.78125, 0.1875, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.3125, 0.0, 0.125, 0.78125, 0.1875, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.3125, 0.0, 0.125, 0.78125, 0.1875, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.3125, 0.0, 0.125, 0.78125, 0.1875, 1.0);

	private static final AxisAlignedBB DOWN_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.3125, -0.5, 0.125, 0.78125, 0.01, 1.0);
	private static final AxisAlignedBB DOWN_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.3125, -0.5, 0.125, 0.78125, 0.01, 1.0);
	private static final AxisAlignedBB DOWN_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.3125, -0.5, 0.125, 0.78125, 0.01, 1.0);
	private static final AxisAlignedBB DOWN_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.3125, -0.5, 0.125, 0.78125, 0.01, 1.0);

	public SushiSet(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hStack.isEmpty()) {
			if (this == Dish_Blocks.SUSHISET_4shoku) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.SHOUYUSUSHI, i);
				
				if (i == 4) {
					worldIn.setBlockState(pos, Dish_Blocks.FOODKARA_SUSHI.getDefaultState()
							.withProperty(FoodKara_Sushi.H_FACING, state.getValue(H_FACING))
							.withProperty(FoodKara_Sushi.STAGE_1_4, Integer.valueOf(3))); }
				else { //i != 4
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			}
			
			else { //!4shoku
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.SHOUYUSUSHI, takeMeta());
				
				if (i == 4) {
					worldIn.setBlockState(pos, Dish_Blocks.FOODKARA_SUSHI.getDefaultState()
							.withProperty(FoodKara_Sushi.H_FACING, state.getValue(H_FACING))
							.withProperty(FoodKara_Sushi.STAGE_1_4, Integer.valueOf(3))); }
				else { //i != 4
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			}
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	private int takeMeta() {
		if (this == Dish_Blocks.SUSHISET_salmon) { return 1; }
		if (this == Dish_Blocks.SUSHISET_fish) { return 2; }
		if (this == Dish_Blocks.SUSHISET_beef) { return 3; }
		else { return 4; }
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return flag? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return flag? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return flag? AABB_WEST : DOWN_WEST;
		case EAST:
			return flag? AABB_EAST : DOWN_EAST;
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(cloneItem(), 1, 0)); }
		else {
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 1));
			stack.add(new ItemStack(Items_Teatime.SUSHIGETA_kara, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}

	private Item cloneItem() {
		if (this == Dish_Blocks.SUSHISET_4shoku) { return Items_Teatime.SUSHISET_4shoku; }
		if (this == Dish_Blocks.SUSHISET_salmon) { return Items_Teatime.SUSHISET_salmon; }
		if (this == Dish_Blocks.SUSHISET_fish) { return Items_Teatime.SUSHISET_fish; }
		if (this == Dish_Blocks.SUSHISET_beef) { return Items_Teatime.SUSHISET_beef; }
		else { return Items_Teatime.SUSHISET_tamago; }
	}
}
