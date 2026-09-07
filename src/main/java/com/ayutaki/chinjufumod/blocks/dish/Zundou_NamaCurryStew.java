package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Zundou_NamaCurryStew extends BaseZundou_4Stage {

	protected static final int COOK_TIME = 1200; /* Long Cook */
	/** 1=Chicken, 2=Beef, 3=Stew, 4=Tomato **/
	public Zundou_NamaCurryStew(String name) {
		super(name);
	}

	/* Cooking */
	@Override
	public void observedNeighborChange(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.observedNeighborChange(state, worldIn, pos, blockIn, pos);
		if (cookingIn(worldIn, pos)) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
		
		else { }
	} // Need.

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, COOK_TIME);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (cookingIn(worldIn, pos)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			
			worldIn.setBlockState(pos, takeBlock(state).getDefaultState()
					.withProperty(BaseZundou_4Stage.H_FACING, state.getValue(H_FACING))
					.withProperty(BaseZundou_4Stage.STAGE_1_4, Integer.valueOf(1))); }
		
		else { }
	}
	
	private Block takeBlock(IBlockState state) {
		/** 1=Chicken, 2=Beef, 3=Stew, 4=Tomato **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return Dish_Blocks.ZUNDOU_CURRY_C; }
		if (i == 2) { return Dish_Blocks.ZUNDOU_CURRY; }
		if (i == 3) { return Dish_Blocks.ZUNDOU_STEW; }
		else { return Dish_Blocks.ZUNDOU_CURRY_T; }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		CMEvents.textRequestHeat(worldIn, pos, playerIn);
		/** 'true' to not put anything on top. **/
		return true;
	}

	/*Drop Item and Clone Item.
	 * registerRenderMeta(ZUNDOU_RCURRY, 2, "block_food_cunabe_n");
		registerRenderMeta(ZUNDOU_RCURRY, 1, "block_food_cunabe_cn");
		registerRenderMeta(ZUNDOU_RCURRY, 4, "block_food_cunabe_tn");
		registerRenderMeta(ZUNDOU_RCURRY, 3, "block_food_stewnabe_n"); */
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
		/** 1=Chicken, 2=Beef, 3=Stew, 4=Tomato **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { return new ItemStack(Items_Teatime.ZUNDOU_RCURRY, 1, 1); }
		if (i == 2) { return new ItemStack(Items_Teatime.ZUNDOU_RCURRY, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Teatime.ZUNDOU_RCURRY, 1, 3); }
		if (i == 4) { return new ItemStack(Items_Teatime.ZUNDOU_RCURRY, 1, 4); }
		return null;
	}
}
