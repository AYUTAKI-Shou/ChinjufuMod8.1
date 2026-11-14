package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Taru_Hakkou_sub extends Base_Taru15 {
	
	private static final int COOK_TIME = 2000;
	/// 空(T_0)
	/// 麹:1(sub_0), 2(sub_1), --> 3(T_1), 4(T_2), 空(T_15)
	/// 酒母:1(sub_2), 2(sub_3), --> 3(T_3), 4(T_4)
	/// もろみ:1(sub_4), 2(sub_5), --> 3(T_5), 4(T_6)
	/// 熟成:1(sub_6), 2(sub_7), --> 3(T_7), 4(T_8)
	/// 味噌:1(sub_8), 2(sub_9), --> 3(T_9), 4(T_10), 空(T_11)
	/// 紅茶:1(sub_10), 2(sub_11), --> 3(T_12), 4(T_13), 空(T_14)
	/// バニラ:1(sub_12), 2(sub_13), 3(sub_14), 4(sub_15)
	
	public Taru_Hakkou_sub(String name) {
		super(name);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, COOK_TIME);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		
		if (i == 15) { }

		else {
			worldIn.scheduleUpdate(pos, this, COOK_TIME + (500 * rand.nextInt(5)));
			
			boolean toTARU = (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11);
			if (toTARU) {
				IBlockState TARU = Hakkou_Blocks.HAKKOUTARU.getDefaultState();
				if (i == 1) { worldIn.setBlockState(pos, TARU.withProperty(STAGE_0_15, Integer.valueOf(1))); }
				if (i == 3) { worldIn.setBlockState(pos, TARU.withProperty(STAGE_0_15, Integer.valueOf(3))); }
				if (i == 5) { worldIn.setBlockState(pos, TARU.withProperty(STAGE_0_15, Integer.valueOf(5))); }
				if (i == 7) { worldIn.setBlockState(pos, TARU.withProperty(STAGE_0_15, Integer.valueOf(7))); }
				if (i == 9) { worldIn.setBlockState(pos, TARU.withProperty(STAGE_0_15, Integer.valueOf(9))); }
				if (i == 11) { worldIn.setBlockState(pos, TARU.withProperty(STAGE_0_15, Integer.valueOf(12))); }
			}

			if(!toTARU) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1))); }
		}
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/** 12=バニラ1, 13=バニラ2, 14=バニラ3, 15=バニラ4 **/
		
		if (i == 15) {
			if (hItem == Items.GLASS_BOTTLE) {
				
				int gHC = hStack.getCount();
				if (gHC >= 4) {
					CMEvents.consumeN_Hand(4, playerIn, hand);
					CMEvents.soundSnowTake(worldIn, pos);
					
					ItemStack take = new ItemStack(Items_Teatime.VANILLA_bot_14, 4);
					if (hStack.isEmpty()) { playerIn.setHeldItem(hand, take); }
					else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
					
					worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOUTARU.getDefaultState()
							.withProperty(STAGE_0_15, Integer.valueOf(14))); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
			}
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		else { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		return true;
	}

	/* Rendering */
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		
		if (i == 0 || i == 1) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 1)); }
		if (i == 2 || i == 3) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 2)); }
		if (i == 4 || i == 5) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 3)); }
		if (i == 6 || i == 7) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 4)); }
		if (i == 8 || i == 9) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 5)); }
		if (i == 10 || i == 11) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 6)); }
		if (i == 12 || i == 13 || i == 14) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 7)); }
		if (i == 15) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		
		if (i == 0 || i == 1) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 1); }
		if (i == 2 || i == 3) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 2); }
		if (i == 4 || i == 5) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 3); }
		if (i == 6 || i == 7) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 4); }
		if (i == 8 || i == 9) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 5); }
		if (i == 10 || i == 11) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 6); }
		else { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 7); }
	}
}
