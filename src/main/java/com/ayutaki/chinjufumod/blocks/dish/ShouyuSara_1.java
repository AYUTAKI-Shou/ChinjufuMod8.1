package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
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

public class ShouyuSara_1 extends BaseStage4_FDP {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.5625, 0.0, 0.375, 0.8125, 0.03125, 0.625);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.5625, 0.0, 0.375, 0.8125, 0.03125, 0.625);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.5625, 0.0, 0.375, 0.8125, 0.03125, 0.625);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.5625, 0.0, 0.375, 0.8125, 0.03125, 0.625);

	private static final AxisAlignedBB DOWN_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.5625, -0.5, 0.375, 0.8125, 0.01, 0.625);
	private static final AxisAlignedBB DOWN_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.5625, -0.5, 0.375, 0.8125, 0.01, 0.625);
	private static final AxisAlignedBB DOWN_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.5625, -0.5, 0.375, 0.8125, 0.01, 0.625);
	private static final AxisAlignedBB DOWN_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.5625, -0.5, 0.375, 0.8125, 0.01, 0.625);

	public ShouyuSara_1(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (hItem == Items_Teatime.SUSHI) {
			/** Collect with an Item **/
			CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
			CMEvents.take1Item(playerIn, hand, Items_Teatime.SHOUYUSUSHI, k);

			if (i == 4) { worldIn.setBlockState(pos, this.takeBlock().getDefaultState()
					.withProperty(BaseStage4_FDP.H_FACING, state.getValue(H_FACING))
					.withProperty(BaseStage4_FDP.DOWN, state.getValue(DOWN))
					.withProperty(BaseStage4_FDP.STAGE_1_4, Integer.valueOf(this.takeMeta()))); }
			else { //i != 4
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
		}

		if (hItem != Items_Teatime.SUSHI) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Block takeBlock() {
		if (this == Dish_Blocks.SHOUYUSARA_1) { return Dish_Blocks.SHOUYUSARA_5; }
		else { return Dish_Blocks.FOODKARA_SUSHI; }
	}
	
	private int takeMeta() {
		if (this == Dish_Blocks.SHOUYUSARA_1) { return 1; }
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

		if (this == Dish_Blocks.SHOUYUSARA_1 && i == 1) { stack.add(cloneStack()); }
		else { stack.add(new ItemStack(Items_Teatime.Item_SARA, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.SHOUYUSARA, 1, 0);
	}
}
