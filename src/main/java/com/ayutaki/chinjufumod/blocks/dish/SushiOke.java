package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SushiOke extends Regi_addState {
	/* Property */
	public static final PropertyInteger STAGE_1_6 = PropertyInteger.create("stage", 1, 6);

	public SushiOke(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_1_6, Integer.valueOf(1)));
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_6)).intValue();
	
		if (i != 6 && hItem == Items_Teatime.SUSHIOKE && facing == EnumFacing.UP) {
			CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);			
			worldIn.setBlockState(pos, state.withProperty(STAGE_1_6, Integer.valueOf(i + 1)), 3);
			return true; }

		if (hStack.isEmpty() && hItem != Items_Teatime.SUSHIOKE && facing == EnumFacing.UP) {
			CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.SUSHIOKE, 0);

			if (i == 1) { worldIn.setBlockState(pos, Blocks.AIR.getDefaultState()); }
			else { //i != 1
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_6, Integer.valueOf(i - 1)), 3);
			return true; }
		}

		return false;
	}


	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_1_6)).intValue();

		if (i == 2) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D); }
		if (i == 3) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.46875D, 1.0D); }
		if (i == 4) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D); }
		if (i == 5) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.78125D, 1.0D); }
		if (i == 6) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D); }
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.15625D, 1.0D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Data value */
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_6)).intValue();
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_1_6, Integer.valueOf(meta));
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (6 - ((Integer)state.getValue(STAGE_1_6)).intValue()) * 2;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_1_6 });
	}

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_6)).intValue();

		stack.add(new ItemStack(Items_Teatime.SUSHIOKE, i, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.SUSHIOKE, 1, 0);
	}
}
