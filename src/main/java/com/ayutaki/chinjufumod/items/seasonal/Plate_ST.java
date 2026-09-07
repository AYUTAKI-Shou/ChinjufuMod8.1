package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Seasonal;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Plate_ST extends IR_Seasonal {

	public Plate_ST(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 150;
	}
	
	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_plate_sakura";
		case 1:
			return "item." + "block_plate_kaede";
		case 2:
			return "item." + "block_plate_ichoh";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
		}
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(int2Block(k), pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = this.int2Block(k).getDefaultState();
			worldIn.setBlockState(pos, putSTATE, 10);

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	private Block int2Block(int k) {
		if (k == 0) { return Seasonal_Blocks.SAKURA_PLATE; }
		if (k == 1) { return Seasonal_Blocks.KAEDE_PLATE; }
		else { return Seasonal_Blocks.ICHOH_PLATE; }
	}
}
