package com.ayutaki.chinjufumod.items.cooktool;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.BaseNabeNama_4;
import com.ayutaki.chinjufumod.handler.CMEvents;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NabeNamaAzuki_TT extends Abstract_NabeNama4 {

	public NabeNamaAzuki_TT(String name) {
		super(name, Dish_Blocks.NABE_nama_Azuki);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		/** 1=赤飯, 2=あずき, 3=あんこ, 4=クリーム, 5=テングサ **/
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_food_nabesekihan_n";
		case 2:
			return "item." + "block_food_nabeazuki_n";
		case 3:
			return "item." + "block_food_nabeanko_n";
		case 4:
			return "item." + "block_food_nabecream";
		case 5:
			return "item." + "block_food_nabetengusa_n";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
		}
	}

	/* onItemUse */
	@Override
	protected Block takeBlock() {
		return Dish_Blocks.NABE_nama_Azuki;
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
			int k = hStack.getMetadata();
			/** 1=赤飯, 2=あずき, 3=あんこ, 4=クリーム, 5=テングサ **/
			
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(this.takeBlock(), pos, false, facing, (Entity)null)) {

			if (k != 4) {
				int stage = (k == 5)? 4 : k;
				/** Put the Block. **/
				IBlockState putSTATE = this.takeBlock().getDefaultState().withProperty(BaseNabeNama_4.H_FACING, direction)
						.withProperty(BaseNabeNama_4.STAGE_1_4, Integer.valueOf(stage));
				worldIn.setBlockState(pos, putSTATE, 10); }

			if (k == 4) {
				IBlockState putCREAM = Dish_Blocks.NABE_CREAM.getDefaultState().withProperty(BaseNabeNama_4.H_FACING, direction)
						.withProperty(BaseNabeNama_4.STAGE_1_4, Integer.valueOf(1));
				worldIn.setBlockState(pos, putCREAM, 10); }
			
			CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}
		else { return EnumActionResult.FAIL; }
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		super.addInformation(stack, worldIn, itemTip, advanced);
		int i = stack.getMetadata();
		/** 1=赤飯, 2=あずき, 3=あんこ, 4=クリーム, 5=テングサ **/
		
		if (i == 2) { itemTip.add(I18n.format("tips.take_emptyhand.name")); }
		if (i == 3) { itemTip.add(I18n.format("tips.take_bowl.name")); }
		if (i == 4) {
			itemTip.add(I18n.format("tips.block_food_nabecream.name"));
			itemTip.add(I18n.format("tips.take_bowl.name")); }
		
		if (i == 5) { itemTip.add(TextFormatting.RED + I18n.format("tips.block_food_nabetengusa_n.name")); }
	}
}
