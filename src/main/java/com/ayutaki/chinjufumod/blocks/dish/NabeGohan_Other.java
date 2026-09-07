package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NabeGohan_Other extends BaseNabe {

	public NabeGohan_Other(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (hItem == Items_Teatime.Item_DISH && k == 3) {
			/** Collect with an Item **/
			CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takeChawan(), 0);
			if (i == 1) { CMEvents.addEXP(1, worldIn, pos, playerIn); }

			if (i != 4) { worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			if (i == 4) { worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
										.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
										.withProperty(Nabe_kara.DOWN, state.getValue(DOWN))
										.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(3))); }
		}

		if (hItem == Items.BOWL) {
			if (i == 1) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.MUSHIGOME, this.takeBowl());
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				
				worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
						.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.withProperty(Nabe_kara.DOWN, state.getValue(DOWN))
						.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(3))); }
			
			if (i != 1) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if ((hItem != Items_Teatime.Item_DISH && k != 3) && hItem != Items.BOWL) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Item takeChawan() {
		if (this == Dish_Blocks.NABEGOHAN_TAKE) { return Items_Teatime.GOHAN_TAKE; }
		if (this == Dish_Blocks.NABEGOHAN_KURI) { return Items_Teatime.GOHAN_KURI; }
		else { return Items_Teatime.SEKIHAN; }
	}
	
	private int takeBowl() {
		if (this == Dish_Blocks.NABEGOHAN_TAKE) { return 1; }
		if (this == Dish_Blocks.NABEGOHAN_KURI) { return 2; }
		else { return 3; }
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 1) { stack.add(new ItemStack(cloneItem(), 1, 0)); }
		else { stack.add(new ItemStack(Items_Teatime.NABE_kara, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}

	private Item cloneItem() {
		if (this == Dish_Blocks.NABEGOHAN_TAKE) { return Items_Teatime.NABEGOHAN_TAKE; }
		if (this == Dish_Blocks.NABEGOHAN_KURI) { return Items_Teatime.NABEGOHAN_KURI; }
		else { return Items_Teatime.NABESEKIHAN; }
	}
}
