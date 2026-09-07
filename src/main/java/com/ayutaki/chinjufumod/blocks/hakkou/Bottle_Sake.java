package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Bottle_Sake extends BaseBlock_SakeBottle {

	public Bottle_Sake(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (hItem == Items_Teatime.Item_DISH && k == 7) {
			/** Collect with an Item **/
			CMEvents.consumeN_Hand(1, playerIn, hand);
			worldIn.playSound(null, pos, SoundEvents_CM.SAKE, SoundCategory.PLAYERS, 1.0F, 1.0F);
			CMEvents.take1Item(playerIn, hand, this.takeItem(), this.takeInt());

			if (i != 4) { worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			if (i == 4) { worldIn.setBlockState(pos, takeState()
										.withProperty(BaseBlock_SakeBottle.H_FACING, state.getValue(H_FACING))
										.withProperty(BaseBlock_SakeBottle.DOWN, state.getValue(DOWN))); } }
		
		if (hItem != Items_Teatime.Item_DISH || k != 7) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Item takeItem() {
		if (this == Hakkou_Blocks.NAMASAKEBOT) { return Items_Teatime.SAKEGLASS; }
		if (this == Hakkou_Blocks.SAKEBOT) { return Items_Teatime.SAKEGLASS; }
		if (this == Hakkou_Blocks.JUKUSAKEBOT) { return Items_Teatime.SAKEGLASS; }
		
		if (this == Hakkou_Blocks.WINEBOT) { return Items_Teatime.WINEGLASS; }
		if (this == Hakkou_Blocks.JUKUWINEBOT) { return Items_Teatime.WINEGLASS; }
		if (this == Hakkou_Blocks.CIDERBOT) { return Items_Teatime.WINEGLASS; }
		if (this == Hakkou_Blocks.JUKUCIDERBOT) { return Items_Teatime.WINEGLASS; }
		if (this == Hakkou_Blocks.MEADBOT) { return Items_Teatime.WINEGLASS; }
		else { return Items_Teatime.WINEGLASS; }
	}
	
	private int takeInt() {
		if (this == Hakkou_Blocks.NAMASAKEBOT) { return 1; }
		if (this == Hakkou_Blocks.SAKEBOT) { return 2; }
		if (this == Hakkou_Blocks.JUKUSAKEBOT) { return 3; }
		
		if (this == Hakkou_Blocks.WINEBOT) { return 1; }
		if (this == Hakkou_Blocks.JUKUWINEBOT) { return 2; }
		if (this == Hakkou_Blocks.CIDERBOT) { return 3; }
		if (this == Hakkou_Blocks.JUKUCIDERBOT) { return 4; }
		if (this == Hakkou_Blocks.MEADBOT) { return 5; }
		else { return 6; }
	}

	private IBlockState takeState() {
		if (this == Hakkou_Blocks.NAMASAKEBOT) { 
			return Hakkou_Blocks.KARABOTJP.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(1)); }
		if (this == Hakkou_Blocks.SAKEBOT) { 
			return Hakkou_Blocks.KARABOTJP.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(2)); }
		if (this == Hakkou_Blocks.JUKUSAKEBOT) { 
			return Hakkou_Blocks.KARABOTJP.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(3)); }
		
		if (this == Hakkou_Blocks.WINEBOT) { 
			return Hakkou_Blocks.KARABOT.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(1)); }
		if (this == Hakkou_Blocks.JUKUWINEBOT) { 
			return Hakkou_Blocks.KARABOT.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(2)); }
		if (this == Hakkou_Blocks.CIDERBOT) { 
			return Hakkou_Blocks.KARABOT.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(3)); }
		if (this == Hakkou_Blocks.JUKUCIDERBOT) { 
			return Hakkou_Blocks.KARABOT.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(4)); }
		
		if (this == Hakkou_Blocks.MEADBOT) { 
			return Hakkou_Blocks.KARABOTMEAD.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(1)); }
		if (this == Hakkou_Blocks.JUKUMEADBOT) { 
			return Hakkou_Blocks.KARABOTMEAD.getDefaultState().withProperty(BaseBlock_SakeBottle.STAGE_1_4, Integer.valueOf(2)); }
		return null;
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 1) { stack.add(new ItemStack(cloneItem(), 1, 0)); }
		else { stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 8)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == Hakkou_Blocks.NAMASAKEBOT) { return Items_Teatime.NAMASAKEBOT; }
		if (this == Hakkou_Blocks.SAKEBOT) { return Items_Teatime.SAKEBOT; }
		if (this == Hakkou_Blocks.JUKUSAKEBOT) { return Items_Teatime.JUKUSAKEBOT; }
		
		if (this == Hakkou_Blocks.WINEBOT) { return Items_Teatime.WINEBOT; }
		if (this == Hakkou_Blocks.JUKUWINEBOT) { return Items_Teatime.JUKUWINEBOT; }
		if (this == Hakkou_Blocks.CIDERBOT) { return Items_Teatime.CIDERBOT; }
		if (this == Hakkou_Blocks.JUKUCIDERBOT) { return Items_Teatime.JUKUCIDERBOT; }
		if (this == Hakkou_Blocks.MEADBOT) { return Items_Teatime.MEADBOT; }
		else { return Items_Teatime.JUKUMEADBOT; }
	}
}
