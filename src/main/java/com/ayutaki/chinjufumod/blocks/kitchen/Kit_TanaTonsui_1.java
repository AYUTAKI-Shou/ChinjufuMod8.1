package com.ayutaki.chinjufumod.blocks.kitchen;

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

public class Kit_TanaTonsui_1 extends BaseKit_Tana4Stage {

	public Kit_TanaTonsui_1(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hItem != Items_Teatime.Item_DISH || k != 5) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.Item_DISH, 5);
	
				if (i == 1) { worldIn.setBlockState(pos, Kitchen_Blocks.KIT_TANA.getDefaultState()
						.withProperty(Kit_Tana.H_FACING, state.getValue(H_FACING))); }
				
				else { //i != 1
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 1)), 3); } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (hItem == Items_Teatime.Item_DISH && k == 5) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			
			if (i == 4) { worldIn.setBlockState(pos, Kitchen_Blocks.KIT_TONSUIA.getDefaultState()
					.withProperty(Kit_TanaTonsui_A.H_FACING, state.getValue(H_FACING))); }
			
			else { //i != 4
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* Drop Item and Clone Item. */
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		stack.add(new ItemStack(Items_Teatime.Item_DISH, i, 5));
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.KIT_TANA, 1, 0);
	}
}
