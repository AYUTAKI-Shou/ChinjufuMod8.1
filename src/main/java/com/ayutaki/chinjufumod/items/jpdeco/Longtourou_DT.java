package com.ayutaki.chinjufumod.items.jpdeco;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.garden.TourouLong_Bot;
import com.ayutaki.chinjufumod.blocks.garden.TourouLong_Top;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Wadeco;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Longtourou_DT extends IBR_Wadeco {

	public Longtourou_DT(String name) {
		super(name, Garden_Blocks.LONGTOUROU_BOT);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_longtourou_stone";
		case 2:
			return "item." + "block_longtourou_gra";
		case 3:
			return "item." + "block_longtourou_dio";
		case 4:
			return "item." + "block_longtourou_and";
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

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Garden_Blocks.LONGTOUROU_BOT, pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = Garden_Blocks.LONGTOUROU_BOT.getDefaultState()
					.withProperty(TourouLong_Bot.H_FACING, direction)
					.withProperty(TourouLong_Bot.STAGE_1_4, Integer.valueOf(k));
			IBlockState state2 = Garden_Blocks.LONGTOUROU_TOP.getDefaultState()
					.withProperty(TourouLong_Top.H_FACING, direction)
					.withProperty(TourouLong_Top.STAGE_1_4, Integer.valueOf(k));

			worldIn.setBlockState(pos, putSTATE);
			worldIn.setBlockState(pos.up(), state2);

			CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_ishitourou.name"));
	}
}
