package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.blocks.wood.Planks_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Planks_ST extends IBR_Seasonal {
	/* 木剣の修理でサクラ, カエデ。イチョウを区別するため, サブアイテムにはしない */
	public Planks_ST(String name) {
		super(name, Seasonal_Blocks.PLANKS);
		setUnlocalizedName(name);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;
	}

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Seasonal_Blocks.PLANKS, pos, false, facing, (Entity)null)) {

			worldIn.setBlockState(pos, Seasonal_Blocks.PLANKS.getDefaultState()
					.withProperty(Planks_CM.STAGE_1_3, Integer.valueOf(takeMeta())), 2);	
			
			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}
	
	private int takeMeta() {
		if (this == Items_Seasonal.PLANKS_sakura) { return 1; }
		if (this == Items_Seasonal.PLANKS_kaede) { return 2; }
		else { return 3; }
	}
}
