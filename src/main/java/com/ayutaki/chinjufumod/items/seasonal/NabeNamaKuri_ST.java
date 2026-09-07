package com.ayutaki.chinjufumod.items.seasonal;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.BaseNabeNama_4;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Seasonal;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

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

public class NabeNamaKuri_ST extends IBR_Seasonal {

	public NabeNamaKuri_ST(String name) {
		super(name, Dish_Blocks.NABE_nama_TK);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/** 1=生クリ, 2=茹でクリ **/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_food_nabekuri_n";
		case 2:
			return "item." + "block_food_nabekuri_b";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
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

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Dish_Blocks.NABE_nama_TK, pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = Dish_Blocks.NABE_nama_TK.getDefaultState()
					.withProperty(BaseNabeNama_4.H_FACING, direction)
					.withProperty(BaseNabeNama_4.STAGE_1_4, Integer.valueOf(k + 2));
			worldIn.setBlockState(pos, putSTATE, 10);

			CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}
		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1) { itemTip.add(I18n.format("tips.block_nabe.name")); }
		if (k == 2) { itemTip.add(I18n.format("tips.take_bowl.name")); }
	}
}
