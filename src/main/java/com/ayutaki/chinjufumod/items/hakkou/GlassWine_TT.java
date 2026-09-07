package com.ayutaki.chinjufumod.items.hakkou;

import com.ayutaki.chinjufumod.blocks.hakkou.Base_Glass;
import com.ayutaki.chinjufumod.items.dish.TTab_DishDrink;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;

public class GlassWine_TT extends TTab_DishDrink {

	public GlassWine_TT(String name) {
		super(name, Hakkou_Blocks.WINEGLASS);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/** 1=ワイン, 2=熟成ワイン, 3=シードル, 4=熟成シードル, 5=ミード, 6=熟成ミード **/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_glass_wine";
		case 2:
			return "item." + "block_glass_winejuku";
		case 3:
			return "item." + "block_glass_cider";
		case 4:
			return "item." + "block_glass_ciderjuku";
		case 5:
			return "item." + "block_glass_mead";
		case 6:
			return "item." + "block_glass_meadjuku";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
		}
	}

	/* onItemUseFinish */
	@Override
	protected ItemStack remainStack(ItemStack stack) {
		return new ItemStack(Items_Teatime.Item_DISH, 1, 7);
	}

	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		int k = stack.getMetadata();
		if (k == 1 || k == 3 || k == 5) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2250, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2600, 0)); }

		if (k == 2 || k == 4 || k == 6) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2250, 1));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2250, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Hakkou_Blocks.WINEGLASS;
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		return this.intBlock(k).getDefaultState().withProperty(Base_Glass.STAGE_1_15, Integer.valueOf(this.takeMeta(k)));
	}

	private Block intBlock(int k) {
		boolean notMEAD = (k != 5 && k != 6);
		return (notMEAD)? Hakkou_Blocks.WINEGLASS : Hakkou_Blocks.SAKEGLASS;
	}
	
	private int takeMeta(int k) {
		if (k == 2) { return 4; }
		if (k == 3) { return 7; }
		if (k == 4) { return 10; }

		if (k == 5) { return 10; }
		if (k == 6) { return 13; }
		else { return 1; }
	}
}
