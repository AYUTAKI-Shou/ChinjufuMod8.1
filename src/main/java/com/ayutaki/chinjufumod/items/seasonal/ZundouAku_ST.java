package com.ayutaki.chinjufumod.items.seasonal;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.Zundou_ShioAku;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Seasonal;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ZundouAku_ST extends IBR_Seasonal {

	public ZundouAku_ST(String name) {
		super(name, Dish_Blocks.SHIOAKUNABE);
		setUnlocalizedName(name);
		
		setContainerItem(Items_Teatime.ZUNDOU); 
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_Teatime.ZUNDOU);
	}

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
		int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		EnumFacing direction = EnumFacing.getHorizontal(i);
		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Dish_Blocks.SHIOAKUNABE, pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			IBlockState putSTATE = Dish_Blocks.SHIOAKUNABE.getDefaultState()
					.withProperty(Zundou_ShioAku.H_FACING, direction)
					.withProperty(Zundou_ShioAku.STAGE_1_4, Integer.valueOf(3));
			worldIn.setBlockState(pos, putSTATE, 10);
			/** 1=塩水, 2=塩湯, 3=灰汁水, 4=灰汁湯 **/

			CMEvents.ItemBlock_Metal(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_zundou_aku.name"));
	}
}
