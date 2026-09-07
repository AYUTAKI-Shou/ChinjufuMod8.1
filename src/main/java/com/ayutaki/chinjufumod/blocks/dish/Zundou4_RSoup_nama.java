package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Zundou4_RSoup_nama extends BaseZundou_4LongCook {
	/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
	public Zundou4_RSoup_nama(String name) {
		super(name);
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (hStack.isEmpty()) {
			if (i == 3 || i == 4) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items.DYE, 4, 15);
	
				worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_RSOUP.getDefaultState()
						.withProperty(Zundou4_RSoup.H_FACING, state.getValue(H_FACING))
						.withProperty(Zundou4_RSoup.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			else { //i != 3 && i != 4
				CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }

		/** 'true' to not put anything on top. **/
		return true;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack());
		return stack;
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.ZUNDOU_RSOUP, 1, 0);
	}
}
