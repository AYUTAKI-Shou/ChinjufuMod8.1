package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.hakkou.Bin_KomezuOil;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.ItemBlock_Regi;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
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

public class BaseSoyoil_NT extends ItemBlock_Regi {

	public BaseSoyoil_NT(String name) {
		super(name, Dish_Blocks.SOYOIL_bot);
		setUnlocalizedName(name);
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

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Dish_Blocks.SOYOIL_bot, pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			IBlockState putSTATE = Dish_Blocks.SOYOIL_bot.getDefaultState().withProperty(Bin_KomezuOil.H_FACING, direction)
					.withProperty(Bin_KomezuOil.STAGE_1_2, Integer.valueOf(takeMeta()));
			worldIn.setBlockState(pos, putSTATE, 10);
			
			CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}
		else { return EnumActionResult.FAIL; }
	}

	public int takeMeta() {
		if (this == Items_Teatime.SOYOIL_bot_12) { return 1; }
		else { return 2; }
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		if (this == Items_Teatime.SOYOIL_bot_12) { itemTip.add(I18n.format("tips.block_bot_3.name")); }
		if (this == Items_NoTab.SOYOIL_bot_22) { itemTip.add(I18n.format("tips.block_bot_4.name")); }
	}
}
