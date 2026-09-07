package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
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

public class Zundou extends BaseStage3_Face {
	/** 1=close, 2=open **/
	public Zundou(String name) {
		super(name);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		if (hItem == Items.WATER_BUCKET) {
			CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_MIZUMILK.getDefaultState()
					.withProperty(Zundou_MizuMilk.H_FACING, state.getValue(H_FACING))
					.withProperty(Zundou_MizuMilk.STAGE_1_4, Integer.valueOf(1))); }

		if (hItem == Items_Teatime.MIZUOKE_full) {
			CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_MIZUMILK.getDefaultState()
					.withProperty(Zundou_MizuMilk.H_FACING, state.getValue(H_FACING))
					.withProperty(Zundou_MizuMilk.STAGE_1_4, Integer.valueOf(1))); }

		if (hItem == Items.MILK_BUCKET) {
			CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_MIZUMILK.getDefaultState()
					.withProperty(Zundou_MizuMilk.H_FACING, state.getValue(H_FACING))
					.withProperty(Zundou_MizuMilk.STAGE_1_4, Integer.valueOf(3))); }

		if (hItem == Items_Teatime.MIZUOKE_Milk) {
			CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_MIZUMILK.getDefaultState()
					.withProperty(Zundou_MizuMilk.H_FACING, state.getValue(H_FACING))
					.withProperty(Zundou_MizuMilk.STAGE_1_4, Integer.valueOf(3))); }
		
		if (hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full && 
				hItem != Items.MILK_BUCKET && hItem != Items_Teatime.MIZUOKE_Milk) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}

	private ItemStack cloneStack(IBlockState state) {
		return new ItemStack(Items_Teatime.ZUNDOU, 1, 0);
	}
	
	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
