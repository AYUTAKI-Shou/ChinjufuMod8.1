package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Taru_Hakkou extends Base_Taru15 {
	
	private static final int COOK_TIME = 2000;
	/** 0=空, 1=麹未3, 2=麹, 3=酒母未3, 4=酒母, 5=もろみ未3, 6=もろみ, 7=熟成酒未3, 8=熟成酒 **/
	/** 9=味噌未3, 10=味噌, 11=味噌の空樽, 12=紅茶未3, 13=紅茶, 14=紅茶の空棚, 15=麹の空棚 **/

	public Taru_Hakkou(String name) {
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
		/** 0=空, 1=麹未3, 2=麹, 3=酒母未3, 4=酒母, 5=もろみ未3, 6=もろみ, 7=熟成酒未3, 8=熟成酒 **/
		/** 9=味噌未3, 10=味噌, 11=味噌の空樽, 12=紅茶未3, 13=紅茶, 14=紅茶の空棚, 15=麹の空棚 **/
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		
		if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 12) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME + (500 * rand.nextInt(5)));
			
			if (i == 1) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(2))); }
			if (i == 3) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(4))); }
			if (i == 5) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(6))); }
			if (i == 7) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(8))); }
			if (i == 9) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(10))); }
			if (i == 12) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(13))); }
		}

		else { }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		/** 0=空, 1=麹未3, 2=麹, 3=酒母未3, 4=酒母, 5=もろみ未3, 6=もろみ, 7=熟成酒未3, 8=熟成酒 **/
		/** 9=味噌未3, 10=味噌, 11=味噌の空樽, 12=紅茶未3, 13=紅茶, 14=紅茶の空棚, 15=麹の空棚 **/
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (hStack.isEmpty()) {
			if (i == 2) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.KOMEKOUJI, 4, 0);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(15))); 
			} /* 2=麹 */
			
			if (i == 8) {
				CMEvents.takeSAKEBottle_seFill(worldIn, pos, playerIn, hand, Items_Teatime.JUKUSAKEBOT, 0);
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(0))); 
			} /* 8=熟成酒 */
			
			if (i == 10) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.MISO, 4, 0);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(11))); 
			} /* 10=味噌 */
			
			if (i == 13) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.CHABA, 8, 2);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(14))); 
			} /* 13=紅茶 */
			
			if (i == 15) {
				CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, Items_Seasonal.TANMONO, 4, 0);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(14))); 
			} /* 15=麹の空棚 */
			
			if (i == 0 || i == 4 || i == 6 || i == 14) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			
			if (i == 11) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
			
			/** Too early to collect **/
			if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 12) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}

		/** 0=空, 1=麹未3, 2=麹, 3=酒母未3, 4=酒母, 5=もろみ未3, 6=もろみ, 7=熟成酒未3, 8=熟成酒 **/
		/** 9=味噌未3, 10=味噌, 11=味噌の空樽, 12=紅茶未3, 13=紅茶, 14=紅茶の空棚, 15=麹の空棚 **/
		else { //!empty
			IBlockState TARU_sub = Hakkou_Blocks.HAKKOUTARU_sub.getDefaultState();
			IBlockState SHOUYU = Hakkou_Blocks.SHOUYUTARU.getDefaultState();
			
			if (i == 0) {
				if (hItem == Items_Teatime.SAKEBOT) {
					/** Collect with an Item **/
					CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
					CMEvents.soundSAKEBottleFill(worldIn, pos);
					worldIn.setBlockState(pos, TARU_sub.withProperty(STAGE_0_15, Integer.valueOf(6))); }
				
				if (hItem != Items_Teatime.SAKEBOT) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			} /* 0=空 */
			
			if (i == 4) {
				if (hItem == Items.BOWL) {
					CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.SHUBO, 0);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(0))); }

				if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			} /* 4=酒母 */
			
			if (i == 6) {
				if (hItem == Items.BOWL) {
					CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.MORO, 0);
					CMEvents.addEXP(1, worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(0))); }
				
				if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			} /* 6=もろみ */
			
			if (i == 14) {
				int k = hStack.getMetadata();
				int gHC = hStack.getCount();

				if (hItem == Items_Teatime.CHADUTSU) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, TARU_sub.withProperty(STAGE_0_15, Integer.valueOf(10))); }
				
				if (hItem == Items_Teatime.CHABA && k == 1 && gHC >= 8) {
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, TARU_sub.withProperty(STAGE_0_15, Integer.valueOf(10))); }
				
				if (hItem == Item.getItemFromBlock(Blocks.BROWN_MUSHROOM) && gHC >= 8) {
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, SHOUYU.withProperty(STAGE_0_15, Integer.valueOf(11))); }
				
				if (hItem == Items_Teatime.NORI_N && gHC >= 8) {
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, SHOUYU.withProperty(STAGE_0_15, Integer.valueOf(13))); }
				
				if (hItem == Items_Teatime.SPICE && k == 0 && gHC >= 8) {
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, SHOUYU.withProperty(STAGE_0_15, Integer.valueOf(0))); }
				
				if (hItem == Items_Teatime.SPICE && k == 8 && gHC >= 8) {
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, TARU_sub.withProperty(STAGE_0_15, Integer.valueOf(12))); }
				
				if ((hItem == Items_Teatime.CHABA && k == 1 && gHC < 8) || (hItem == Items_Teatime.NORI_N && gHC < 8) ||
						(hItem == Item.getItemFromBlock(Blocks.BROWN_MUSHROOM) && gHC < 8) ||
						(hItem == Items_Teatime.SPICE && k == 0 && gHC < 8) ||
						(hItem == Items_Teatime.SPICE && k == 8 && gHC < 8)) {
					CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
				
				if (hItem != Items_Teatime.CHADUTSU && hItem != Items_Teatime.CHABA && 
						hItem != Items_Teatime.NORI_N && hItem != Item.getItemFromBlock(Blocks.BROWN_MUSHROOM) && 
						hItem != Items_Teatime.SPICE) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			} /* 14=紅茶の空棚 */
			
			if (i == 11) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
			
			if (i == 2 || i == 8 || i == 10 || i == 13 || i == 15) { CMEvents.textFullItem(worldIn, pos, playerIn); }
			
			/** Too early to collect **/
			if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 12) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/** 0=空, 1=麹未3, 2=麹, 3=酒母未3, 4=酒母, 5=もろみ未3, 6=もろみ, 7=熟成酒未3, 8=熟成酒 **/
		/** 9=味噌未3, 10=味噌, 11=味噌の空樽, 12=紅茶未3, 13=紅茶, 14=紅茶の空棚, 15=麹の空棚 **/
		
		if (i == 0 || i == 4 || i == 6 || i == 14) {
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		if (i == 1) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 1)); }
		if (i == 3) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 2)); }
		if (i == 5) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 3)); }
		if (i == 7) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 4)); }
		if (i == 8) { stack.add(new ItemStack(Items_Teatime.JUKUSAKEBOT, 1, 0));
							stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		if (i == 9) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 5)); }
		if (i == 10 || i == 11) { stack.add(new ItemStack(Blocks.STONE_SLAB, 1, 0));
							stack.add(new ItemStack(Items_Seasonal.TANMONO, 1, 0));
							stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		if (i == 12) { stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 6)); }
		if (i == 13) { stack.add(new ItemStack(Items_Teatime.CHABA, 8, 2));
							stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		if (i == 2 || i == 15) { stack.add(new ItemStack(Items_Seasonal.TANMONO, 4, 0));
							stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/** 0=空, 1=麹未3, 2=麹, 3=酒母未3, 4=酒母, 5=もろみ未3, 6=もろみ, 7=熟成酒未3, 8=熟成酒 **/
		/** 9=味噌未3, 10=味噌, 11=味噌の空樽, 12=紅茶未3, 13=紅茶, 14=紅茶の空棚, 15=麹の空棚 **/
		
		if (i == 1 || i == 2) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 1); }
		if (i == 3 || i == 4) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 2); }
		if (i == 5 || i == 6) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 3); }
		if (i == 7 || i == 8) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 4); }
		if (i == 9 || i == 10) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 5); }
		if (i == 12 || i == 13) { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 6); }
		else { return new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0); }
	}
}
