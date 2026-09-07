package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
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

public class Chauke_3shu extends Base_ConnectHalf {
	/* Property */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.28125D, 0.0D, 0.28125D, 0.71875D, 0.1875D, 0.71875D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.28125D, -0.5D, 0.28125D, 0.71875D, 0.01D, 0.71875D);

	public Chauke_3shu(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_0_15, Integer.valueOf(0)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		boolean KARA = (i == 4 || i == 10 || i == 15);
		
		if (KARA) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, takeItem(state), 0);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Item takeItem(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		
		if (i < 4) { return Items_Teatime.SENBEI; }
		if (i > 4 && i < 10) { return Items_Teatime.FOOD_MIKAN; }
		if (i > 10 && i < 15) { return Items_Teatime.SCONE; }
		return null;
	}

	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, this.connectHalf(worldIn, pos.down()));
	}

	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (15 - ((Integer)state.getValue(STAGE_0_15)).intValue()) * 2;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, STAGE_0_15 });
	}

	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		return flag? AABB : AABB_DOWN;
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

		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (i == 0) { stack.add(new ItemStack(Items_Teatime.JPCHAUKE, 1, 0)); }
		if (i == 1) {
			stack.add(new ItemStack(Items_Teatime.SENBEI, 3, 0));
			stack.add(cloneStack()); }
		if (i == 2) {
			stack.add(new ItemStack(Items_Teatime.SENBEI, 2, 0));
			stack.add(cloneStack()); }
		if (i == 3) {
			stack.add(new ItemStack(Items_Teatime.SENBEI, 1, 0));
			stack.add(cloneStack()); }

		if (i == 5) { stack.add(new ItemStack(Items_Teatime.JPCHAUKE, 1, 1)); }
		if (i == 6) {
			stack.add(new ItemStack(Items_Teatime.FOOD_MIKAN, 4, 0));
			stack.add(cloneStack()); }
		if (i == 7) {
			stack.add(new ItemStack(Items_Teatime.FOOD_MIKAN, 3, 0));
			stack.add(cloneStack()); }
		if (i == 8) {
			stack.add(new ItemStack(Items_Teatime.FOOD_MIKAN, 2, 0));
			stack.add(cloneStack()); }
		if (i == 9) {
			stack.add(new ItemStack(Items_Teatime.FOOD_MIKAN, 1, 0));
			stack.add(cloneStack()); }

		if (i == 11) { stack.add(new ItemStack(Items_Teatime.JPCHAUKE, 1, 2)); }
		if (i == 12) {
			stack.add(new ItemStack(Items_Teatime.SCONE, 3, 0));
			stack.add(cloneStack()); }
		if (i == 13) {
			stack.add(new ItemStack(Items_Teatime.SCONE, 2, 0));
			stack.add(cloneStack()); }
		if (i == 14) {
			stack.add(new ItemStack(Items_Teatime.SCONE, 1, 0));
			stack.add(cloneStack()); }

		if (i == 4 || i == 10 || i == 15) { stack.add(cloneStack()); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (i < 4) { return new ItemStack(Items_Teatime.JPCHAUKE, 1, 0); }
		if (i > 4 && i < 10) { return new ItemStack(Items_Teatime.JPCHAUKE, 1, 1); }
		if (i > 10) { return new ItemStack(Items_Teatime.JPCHAUKE, 1, 2); }
		else { return cloneStack(); }
	}

	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.Item_SARA, 1, 0);
	}
}
