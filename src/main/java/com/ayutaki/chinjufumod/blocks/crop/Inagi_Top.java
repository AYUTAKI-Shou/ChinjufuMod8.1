package com.ayutaki.chinjufumod.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Inagi_Top extends BaseStage4_Face {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.45, 0.0, 0.125, 0.55, 0.34375, 0.875);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.45, 0.0, 0.125, 0.55, 0.34375, 0.875);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.45, 0.0, 0.125, 0.55, 0.34375, 0.875);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.45, 0.0, 0.125, 0.55, 0.34375, 0.875);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	private static final AxisAlignedBB AABB_1_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.45, 0.175, 0.0, 0.55, 0.275, 1.0);
	private static final AxisAlignedBB AABB_1_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.45, 0.175, 0.0, 0.55, 0.275, 1.0);
	private static final AxisAlignedBB AABB_1_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.45, 0.175, 0.0, 0.55, 0.275, 1.0);
	private static final AxisAlignedBB AABB_1_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.45, 0.175, 0.0, 0.55, 0.275, 1.0);
	private static final AxisAlignedBB[] AABB_1 = { AABB_1_SOUTH, AABB_1_WEST, AABB_1_NORTH, AABB_1_EAST };

	/** 1=Empty, 2=Wet, 3=Wet, 4=Dry **/
	public Inagi_Top(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setTickRandomly(true);
	}

	/* RandomTick */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		boolean wet = (i == 2 || i == 3);
		if (!worldIn.isRainingAt(pos.up()) && wet) {
			if (rand.nextInt(4) == 0) {
				int i1 = i + 1;
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i1)));
				worldIn.setBlockState(pos.down(), Crop_Blocks.INAGI_BOT.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(i1))); } }
		
		else { }
	}

	/* RightClick Action */
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int gHC = hStack.getCount();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		/** Set rice plant **/
		if (i == 1) {
			if (hItem == Items_Teatime.INE && k == 0 && gHC >= 8) {
				/* Consume 8 Items. */
				CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
				
				worldIn.setBlockState(pos, state.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(2)));
				worldIn.setBlockState(pos.down(), Crop_Blocks.INAGI_BOT.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(2))); }
			
			if (hItem == Items_Teatime.INE && gHC < 8) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
			
			if (hItem != Items_Teatime.INE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		if (i == 2 || i == 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** Can harvest **/
		if (i == 4) {
			if (hStack.isEmpty() && hItem != Items_Teatime.INE) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.INE, 8, 1);
				CMEvents.addEXP(2, worldIn, pos, playerIn);
				
				worldIn.setBlockState(pos, state.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1)));
				worldIn.setBlockState(pos.down(), Crop_Blocks.INAGI_BOT.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1))); }
			
			if (!hStack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { return AABB_1[facing.getHorizontalIndex()]; }
		return AABB[facing.getHorizontalIndex()];
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Rendering */
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		/** Special method using Exp. **/
		if (worldIn.getBlockState(pos.down()).getBlock() instanceof Inagi) {
			if (playerIn.capabilities.isCreativeMode) { worldIn.destroyBlock(pos.down(), false); }

			else { 
				worldIn.destroyBlock(pos.down(), false);
				int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
				if (i == 4) { CMEvents.addEXP(2, worldIn, pos, playerIn); } }
		}
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 1) {
			stack.add(new ItemStack(Items_Seasonal.TAKE, 5, 0)); }

		if (i == 2 || i == 3) {
			stack.add(new ItemStack(Items_Seasonal.TAKE, 5, 0));
			stack.add(new ItemStack(Items_Teatime.INE, 8, 0)); }

		if (i == 4) {
			stack.add(new ItemStack(Items_Seasonal.TAKE, 5, 0));
			stack.add(new ItemStack(Items_Teatime.INE, 8, 1)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.INAGI, 1, 0);
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
