package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.dish.BaseNabe;
import com.ayutaki.chinjufumod.blocks.dish.Nabe_kara;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NabeAmazake_cooked extends BaseNabe {

	public NabeAmazake_cooked(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();

		if (hItem == Items_Teatime.Item_DISH && k == 1) {
			/** Collect with an Item **/
			CMEvents.changeDish_seTea(worldIn, pos, playerIn, hand, Items_Teatime.SAKEGLASS, 4);
			if (i == 1) { CMEvents.addEXP(1, worldIn, pos, playerIn); }
			
			if (i != 4) { worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			if (i == 4) { worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
										.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
										.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(2))); }
		}
		
		if (hItem != Items_Teatime.Item_DISH || k != 1) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}


	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 1) { stack.add(cloneStack()); }
		else { stack.add(new ItemStack(Items_Teatime.NABE_kara, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}

	public ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.NABEAMAZAKE, 1, 0);
	}
}
