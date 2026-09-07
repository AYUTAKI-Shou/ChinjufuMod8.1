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
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Pizza_CTS extends Base_ConnectHalf {
	/* Property 0-3, 4-7, 8-11, 12-15 */
	public static final PropertyInteger STAGE_0_11 = PropertyInteger.create("stage", 0, 11);
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, 0.01D, 1.0D);

	public Pizza_CTS(String name) {
		super(name);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(STAGE_0_11, Integer.valueOf(1))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_0_11)).intValue();

		if (hStack.isEmpty()) {
			CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.PC_PIZZA, takeMeta(state));

			if (i == 3 || i == 7 || i == 11) {
				worldIn.setBlockState(pos, Dish_Blocks.PIZZA.getDefaultState().withProperty(Pizza.STAGE_1_6, Integer.valueOf(5)), 3); }
			else { 
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_11, Integer.valueOf(i + 1)), 3); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	private int takeMeta(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_0_11)).intValue();
		
		if (i <= 3) { return 1; }
		if (i >= 4 && i <= 7) { return 2; }
		else { return 3; }
	}
	
	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, this.connectHalf(worldIn, pos.down()));
	}

	/* Data value */
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_11)).intValue();
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_11, Integer.valueOf(meta));
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (11 - ((Integer)state.getValue(STAGE_0_11)).intValue()) * 2;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, STAGE_0_11 });
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
		return "axe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_0_11)).intValue();

		if (i == 0) { stack.add(new ItemStack(Items_Teatime.PIZZA, 1, 1)); }
		if (i == 1) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 3, 1)); }
		if (i == 2) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 2, 1)); }
		if (i == 3) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 1, 1)); }
		if (i == 4) { stack.add(new ItemStack(Items_Teatime.PIZZA, 1, 2)); }
		if (i == 5) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 3, 2)); }
		if (i == 6) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 2, 2)); }
		if (i == 7) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 1, 2)); }
		if (i == 8) { stack.add(new ItemStack(Items_Teatime.PIZZA, 1, 3)); }
		if (i == 9) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 3, 3)); }
		if (i == 10) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 2, 3)); }
		if (i == 11) { stack.add(new ItemStack(Items_Teatime.PC_PIZZA, 1, 3)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}

	private ItemStack cloneStack(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_0_11)).intValue();
		
		if (i <= 3) { return new ItemStack(Items_Teatime.PC_PIZZA, 1, 1); }
		if (i >= 4 && i <= 7) { return new ItemStack(Items_Teatime.PC_PIZZA, 1, 2); }
		else { return new ItemStack(Items_Teatime.PC_PIZZA, 1, 3); }
	}
}
