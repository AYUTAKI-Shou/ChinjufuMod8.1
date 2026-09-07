package com.ayutaki.chinjufumod.items.dish;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.Abstract_FoodStage3;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TeaCup_Item extends TTab_DishDrink {

	public TeaCup_Item(String name) {
		super(name, Dish_Blocks.TEACUP);
		setUnlocalizedName(name);
		
		/** 飲んでいる最中は皿を外したモデルを使う **/
		this.addPropertyOverride(new ResourceLocation("drink"), new IItemPropertyGetter() {

			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}

	/* onItemUseFinish */
	@Override
	protected ItemStack remainStack(ItemStack stack) {
		return new ItemStack(Items_Teatime.Item_DISH, 1, 2);
	}

	@Override
	protected void addEffect(ItemStack stack, EntityPlayer playerIn) {
		playerIn.addPotionEffect(new PotionEffect(MobEffects.HASTE, 2000, 0));
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.TEACUP;
	}

	@Override
	protected IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction) {
		return this.takeBlock().getDefaultState().withProperty(Abstract_FoodStage3.H_FACING, direction)
				.withProperty(Abstract_FoodStage3.STAGE_1_3, Integer.valueOf(1));
	}
}
