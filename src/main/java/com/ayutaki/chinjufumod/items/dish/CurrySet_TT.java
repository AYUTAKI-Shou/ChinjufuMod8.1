package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.blocks.dish.Curry_set;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CurrySet_TT extends IBR_Teatime {

	public CurrySet_TT(String name) {
		super(name, Dish_Blocks.CURRYSET);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_food_curryset_1";
		case 1:
			return "item." + "block_food_curryset_c1";
		case 2:
			return "item." + "block_food_curryset_t1";
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

			/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack hStack = playerIn.getHeldItem(hand);
			int k = hStack.getMetadata();
			
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(int2Block(k), pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			IBlockState putSTATE = this.int2Block(k).getDefaultState()
					.withProperty(Curry_set.H_FACING, direction)
					.withProperty(Curry_set.STAGE_1_4, Integer.valueOf(1));
			worldIn.setBlockState(pos, putSTATE, 10);
			CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}
	
	private Block int2Block(int k) {
		if (k == 0) { return Dish_Blocks.CURRYSET; }
		if (k == 1) { return Dish_Blocks.CURRYSET_C; }
		else { return Dish_Blocks.CURRYSET_T; }
	}
}
