package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
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

public class SushiMeshi extends Base_ConnectHalf {
	/* Property */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.15625D, 0.9375D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.0625D, -0.5D, 0.0625D, 0.9375D, 0.01D, 0.9375D);

	public SushiMeshi(String name) {
		super(name);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(STAGE_0_15, Integer.valueOf(0))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		
		if (hItem == Items_Teatime.KIRIMI) {
			/** Collect with an Item **/
			CMEvents.consume1_seSnowB(worldIn, pos, playerIn, hand);
			CMEvents.take1Item(playerIn, hand, Items_Teatime.SUSHI, k);

			if (i == 15) { worldIn.setBlockState(pos, Dish_Blocks.PIZZA.getDefaultState()
					.withProperty(Pizza.DOWN, state.getValue(DOWN))
					.withProperty(Pizza.STAGE_1_6, Integer.valueOf(6))); }
			else { //i != 15
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
		}
		
		if (hItem != Items_Teatime.KIRIMI) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, this.connectHalf(worldIn, pos.down()));
	}

	/* Data value */
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (16 - ((Integer)state.getValue(STAGE_0_15)).intValue()) * 2;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, STAGE_0_15 });
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		return flag? AABB : AABB_DOWN;
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
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (i == 0) { stack.add(cloneStack()); }
		else { stack.add(new ItemStack(Items.BOWL, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.SUSHIMESHI, 1, 0);
	}
}
