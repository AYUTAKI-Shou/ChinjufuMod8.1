package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

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

public class Kit_Tana2Sake extends BaseKit_TanaWine {

	public Kit_Tana2Sake(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (hItem != takeItem()) {
			if (hStack.isEmpty()) {
				CMEvents.takeSAKEBottle_seFill(worldIn, pos, playerIn, hand, this.takeItem(), 0);
	
				if (i == 1) { worldIn.setBlockState(pos, Kitchen_Blocks.WINE_TANA.getDefaultState()
										.withProperty(Kit_Tana2.H_FACING, state.getValue(H_FACING))); }
				else { // != 1
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 1)), 3); } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (hItem == takeItem()) {
			if (i == 4) { CMEvents.textFullItem(worldIn, pos, playerIn); }
			else { // != 4
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Item takeItem() {
		if (this == Kitchen_Blocks.KIT_SAKENAMA) { return Items_Teatime.NAMASAKEBOT; }
		if (this == Kitchen_Blocks.KIT_SAKE) { return Items_Teatime.SAKEBOT; }
		if (this == Kitchen_Blocks.KIT_SAKEJUKU) { return Items_Teatime.JUKUSAKEBOT; }
		if (this == Kitchen_Blocks.KIT_CIDER) { return Items_Teatime.CIDERBOT; }
		if (this == Kitchen_Blocks.KIT_CIDERJUKU) { return Items_Teatime.JUKUCIDERBOT; }
		if (this == Kitchen_Blocks.KIT_WINE) { return Items_Teatime.WINEBOT; }
		if (this == Kitchen_Blocks.KIT_WINEJUKU) { return Items_Teatime.JUKUWINEBOT; }
		if (this == Kitchen_Blocks.KIT_MEAD) { return Items_Teatime.MEADBOT; }
		else { return Items_Teatime.JUKUMEADBOT; }
	}
	
	/* Drop Item and Clone Item. */
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) {
			stack.add(new ItemStack(Items_Teatime.WINE_TANA, 1, 0));
			stack.add(new ItemStack(takeItem(), 1, 0)); }

		if (i == 2) {
			stack.add(new ItemStack(Items_Teatime.WINE_TANA, 1, 0));
			stack.add(new ItemStack(takeItem(), 2, 0)); }

		if (i == 3) {
			stack.add(new ItemStack(Items_Teatime.WINE_TANA, 1, 0));
			stack.add(new ItemStack(takeItem(), 3, 0)); }

		if (i == 4) {
			stack.add(new ItemStack(Items_Teatime.WINE_TANA, 1, 0));
			stack.add(new ItemStack(takeItem(), 4, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.WINE_TANA);
	}
}
