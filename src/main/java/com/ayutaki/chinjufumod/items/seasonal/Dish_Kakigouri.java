package com.ayutaki.chinjufumod.items.seasonal;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.dish.TNot_AlwaysEat;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dish_Kakigouri extends TNot_AlwaysEat {

	public Dish_Kakigouri(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
		setUnlocalizedName(name);
		setCreativeTab(ChinjufuModTabs.SEASONAL);
	}
	
	@Override
	protected ItemStack remainStack() { 
		return new ItemStack(Items_Teatime.Item_DISH, 1, 7); }
	
	/* onItemUseFinish */
	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		if (this == Items_Seasonal.KAKIGOURI_block) { playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, 1250, 0)); }
		if (this == Items_Seasonal.KAKIGOURI_apple) { playerIn.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 1900, 0)); }
		if (this == Items_Seasonal.KAKIGOURI_cherry) { playerIn.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1900, 0)); }
		if (this == Items_Seasonal.KAKIGOURI_citrus) { playerIn.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1900, 0)); }
		if (this == Items_Seasonal.KAKIGOURI_grape) { playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1900, 0)); }
		if (this == Items_Seasonal.KAKIGOURI_tea) { playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 1900, 0)); }
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		if (this == Items_Seasonal.KAKIGOURI_block) { return Seasonal_Blocks.KAKIGOURI_block; }
		if (this == Items_Seasonal.KAKIGOURI_apple) { return Seasonal_Blocks.KAKIGOURI_apple; }
		if (this == Items_Seasonal.KAKIGOURI_cherry) { return Seasonal_Blocks.KAKIGOURI_cherry; }
		if (this == Items_Seasonal.KAKIGOURI_citrus) { return Seasonal_Blocks.KAKIGOURI_citrus; }
		if (this == Items_Seasonal.KAKIGOURI_grape) { return Seasonal_Blocks.KAKIGOURI_grape; }
		else { return Seasonal_Blocks.KAKIGOURI_tea; }
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		return this.takeBlock().getDefaultState().withProperty(BaseStage4_FaceDown.H_FACING, direction)
				.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(1));
	}

	@Override
	protected void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand); }

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		super.addInformation(stack, worldIn, itemTip, advanced);
		itemTip.add(I18n.format("tips.block_kakigouri.name"));
	}
}
