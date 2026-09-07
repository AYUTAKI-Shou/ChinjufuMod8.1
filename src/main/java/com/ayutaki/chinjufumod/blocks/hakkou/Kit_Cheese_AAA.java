package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Kit_Cheese_AAA extends BaseStage4_Face {

	protected static final int COOK_TIME = 12000;
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	/* stage1=AAA, stage2=BAA, stage3=BBA, stage4=BBB */
	public Kit_Cheese_AAA(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(10.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hStack.isEmpty()) {
			/** stage1=AAA **/
			if (i == 1) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.CHEESE_CURD, 0);
				worldIn.setBlockState(pos, Kitchen_Blocks.CHEESE_OAA.getDefaultState()
						.withProperty(BaseStage3_Face.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1))); } /* -> OAA */
			
			/** stage2=BAA **/
			if (i == 2) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE, 0);
				worldIn.setBlockState(pos, Kitchen_Blocks.CHEESE_OAA.getDefaultState()
						.withProperty(BaseStage3_Face.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1))); } /* -> OAA */
			
			/** stage3=BBA **/
			if (i == 3) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE, 0);
				worldIn.setBlockState(pos, Kitchen_Blocks.CHEESE_OAA.getDefaultState()
						.withProperty(BaseStage3_Face.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(2))); } /* -> OBA */
			
			/** stage4=BBB **/
			if (i == 4) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.CHEESE, 0);
				worldIn.setBlockState(pos, Kitchen_Blocks.CHEESE_OAA.getDefaultState()
						.withProperty(BaseStage3_Face.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); } /* -> OBB */
		}

		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }

		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes,
			@Nullable Entity entityIn, boolean t_f) {
		EnumFacing facing = state.getValue(H_FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB[facing.getHorizontalIndex()]);
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, Kitchen_Blocks.CHEESE_AAA, COOK_TIME);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/* stage1=AAA, stage2=BAA, stage3=BBA, stage4=BBB */
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 4) { }
		else { //i != 4
			worldIn.scheduleUpdate(pos, Kitchen_Blocks.CHEESE_AAA, COOK_TIME + (500 * rand.nextInt(5)));
			worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); }
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


	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/* Drop Item and Clone Item. */
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		/* stage1=AAA, stage2=BAA, stage3=BBA, stage4=BBB */
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.CHEESE_TANA, 1, 0));
							stack.add(new ItemStack(Items_Teatime.CHEESE_CURD, 3, 0)); }

		if (i == 2) { stack.add(new ItemStack(Items_Teatime.CHEESE_TANA, 1, 0));
							stack.add(new ItemStack(Items_Teatime.CHEESE_CURD, 2, 0));
							stack.add(new ItemStack(Items_Teatime.CHEESE, 1, 0)); }

		if (i == 3) { stack.add(new ItemStack(Items_Teatime.CHEESE_TANA, 1, 0));
							stack.add(new ItemStack(Items_Teatime.CHEESE_CURD, 1, 0));
							stack.add(new ItemStack(Items_Teatime.CHEESE, 2, 0)); }

		if (i == 4) { stack.add(new ItemStack(Items_Teatime.CHEESE_TANA, 1, 0));
							stack.add(new ItemStack(Items_Teatime.CHEESE, 3, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.CHEESE_TANA);
	}
}
