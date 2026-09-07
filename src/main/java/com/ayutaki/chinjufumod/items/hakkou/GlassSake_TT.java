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

public class GlassSake_TT extends TTab_DishDrink {

	public GlassSake_TT(String name) {
		super(name, Hakkou_Blocks.SAKEGLASS);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/** 1=生酒, 2=日本酒, 3=熟成酒, 4=甘酒 **/
		switch (stack.getMetadata()) {
		case 1:
			return "item." + "block_glass_sakenama";
		case 2:
		default:
			return "item." + "block_glass_sake";
		case 3:
			return "item." + "block_glass_sakejuku";
		case 4:
			return "item." + "block_glass_amazake";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
		}
	}

	/* onItemUseFinish */
	@Override
	protected ItemStack remainStack(ItemStack stack) {
		int k = stack.getMetadata();
		int YUNOMI = (k == 4)? 1 : 7;
		return new ItemStack(Items_Teatime.Item_DISH, 1, YUNOMI);
	}

	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		int k = stack.getMetadata();
		if (k == 1) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2250, 0));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2600, 0)); }

		if (k == 2) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2250, 1));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2600, 0)); }

		if (k == 3) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 2250, 2));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 2250, 0)); }

		if (k == 4) {
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2000, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Hakkou_Blocks.SAKEGLASS;
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
				
		return this.intBlock(k).getDefaultState().withProperty(Base_Glass.STAGE_1_15, Integer.valueOf(this.takeMeta(k)));
	}

	private Block intBlock(int k) {
		return (k == 4)? Hakkou_Blocks.WINEGLASS : Hakkou_Blocks.SAKEGLASS;
	}
	
	private int takeMeta(int k) {
		if (k == 2) { return 4; }
		if (k == 3) { return 7; }
		if (k == 4) { return 13; }
		else { return 1; }
	}
}
