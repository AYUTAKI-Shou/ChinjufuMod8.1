package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class Taru_Shouyu extends Base_Taru15 {

	private static final int COOK_TIME = 6000;
	/** 1=未発酵の醤油, 2=醤油, 3=醤油, 4=醤油, 5=醤油 **/
	/** 6=未発酵の米酢, 7=米酢, 8=米酢, 9=米酢, 10=米酢 **/
	/** 11=未乾燥のキノコ, 12=乾燥キノコ **/
	/** 13=生海苔, 14=板海苔 **/
	/** 0=生胡椒, 15=黒胡椒 **/

	public Taru_Shouyu(String name) {
		super(name);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, COOK_TIME);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (i == 0 || i == 1 || i == 6 || i == 11 || i == 13) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME + (500 * rand.nextInt(5)));
			
			if (i == 0) { 
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(15))); }
			else {
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i + 1))); } }

		else { }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		IBlockState TARU_0 = Hakkou_Blocks.HAKKOUTARU.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(0));
		IBlockState TARU_14 = Hakkou_Blocks.HAKKOUTARU.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(14));
		
		/** Too early to collect **/
		if (i == 1 || i == 6 || i == 11 || i == 13 || i == 0) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** 1=未発酵の醤油, 2=醤油, 3=醤油, 4=醤油, 5=醤油 **/
		if (i >= 2 && i <= 5) {
			if (hItem == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.SHOUYU_bot_14, 0);
				if (i == 2) { CMEvents.addEXP(1, worldIn, pos, playerIn); }
				
				if (i != 5) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
				if (i == 5) { worldIn.setBlockState(pos, TARU_0); } }
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		/** 6=未発酵の米酢, 7=米酢, 8=米酢, 9=米酢, 10=米酢 **/
		if (i >= 7 && i <= 10) {
			if (hItem == Items.GLASS_BOTTLE) {
				CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.KOMEZU_bot_12, 0);
				if (i == 7) { CMEvents.addEXP(1, worldIn, pos, playerIn); }
				
				if (i != 10) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
				if (i == 10) { worldIn.setBlockState(pos, TARU_0); } }
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** 11=未乾燥のキノコ, 12=乾燥キノコ **/
		if (i == 12) {
			if (hItem == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.DASHI_bot_14, 0);
				worldIn.setBlockState(pos, TARU_14); }
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** 13=生海苔, 14=板海苔 **/
		if (i == 14) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.NORI_I, 4, 0);
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				worldIn.setBlockState(pos, TARU_14); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** 0=生胡椒, 15=黒胡椒 **/
		if (i == 15) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SPICE, 8, 1);
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				worldIn.setBlockState(pos, TARU_14); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
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
		/** 1=未発酵の醤油, 2=醤油, 3=醤油, 4=醤油, 5=醤油 未発酵はそのまま回収 **/
		/** 6=未発酵の米酢, 7=米酢, 8=米酢, 9=米酢, 10=米酢 発酵済みは樽が壊れると回収できない **/
		/** 11=未乾燥のキノコ, 12=乾燥キノコ **/
		/** 13=生海苔, 14=板海苔 **/
		/** 0=生胡椒, 15=黒胡椒 **/
		
		if (i == 0) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 5)); }
		if (i == 2 || i == 3 || i == 4 || i == 5|| i == 7 || i == 8 || i == 9 || i == 10 || i == 15) {
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		if (i == 1) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 1)); }
		if (i == 6 ) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 2)); }

		if (i == 11) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 3)); }
		if (i == 12) {
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0));
			stack.add(new ItemStack(Blocks.BROWN_MUSHROOM, 8, 0)); }

		if (i == 13) { stack.add(new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 4)); }
		if (i == 14) {
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0));
			stack.add(new ItemStack(Items_Teatime.NORI_I, 4, 0)); }

		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/** 1=未発酵の醤油, 2=醤油, 3=醤油, 4=醤油, 5=醤油 **/
		/** 6=未発酵の米酢, 7=米酢, 8=米酢, 9=米酢, 10=米酢 **/
		/** 11=未乾燥のキノコ, 12=乾燥キノコ **/
		/** 13=生海苔, 14=板海苔 **/
		/** 0=生胡椒, 15=黒胡椒 **/
		
		if (i >= 1 && i <= 5) { return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 1); }
		if (i >= 6 && i <= 10) { return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 2); }
		if (i == 11 || i == 12) { return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 3); }
		if (i == 13 || i == 14) { return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 4); }
		return new ItemStack(Items_Teatime.SHOUYU_TARU, 1, 5);
	}
}
