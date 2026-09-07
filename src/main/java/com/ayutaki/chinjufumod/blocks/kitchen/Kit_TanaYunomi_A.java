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

public class Kit_TanaYunomi_A extends BaseKit_Tana3Stage {

	public Kit_TanaYunomi_A(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		
		if ((hItem != Items_Teatime.Item_DISH || k !=1) && hItem != Items_Teatime.KYUSU_kara) {
			if (hStack.isEmpty()) {
				if (i == 1) {
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.Item_DISH, 1);
					worldIn.setBlockState(pos, Kitchen_Blocks.KIT_YUNOMI1.getDefaultState()
							.withProperty(Kit_TanaYunomi_1.H_FACING, state.getValue(H_FACING))
							.withProperty(Kit_TanaYunomi_1.STAGE_1_4, Integer.valueOf(4))); }
	
				if (i == 2) {
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.Item_DISH, 1);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i - 1)), 3); }
	
				if (i == 3) {
					CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.KYUSU_kara, 0);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i - 1)), 3); } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (hItem == Items_Teatime.Item_DISH && k ==1) {
			if (i == 1) {
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
			
			else { //i != 1
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (hItem == Items_Teatime.KYUSU_kara) {
			if (i == 2) {
				CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
			
			else { //i != 2
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Drop Item and Clone Item. */
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 1) { stack.add(new ItemStack(Items_Teatime.Item_DISH, 5, 1)); }
		if (i == 2) { stack.add(new ItemStack(Items_Teatime.Item_DISH, 6, 1)); }
		if (i == 3) {
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 6, 1));
			stack.add(new ItemStack(Items_Teatime.KYUSU_kara, 1, 0)); }
		
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
