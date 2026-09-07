package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.hakkou.Base_Taru15;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;

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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TaruItem_TT extends IBR_Teatime {

	public TaruItem_TT(String name) {
		super(name, Hakkou_Blocks.HAKKOUTARU);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_taru_hakkou";
		case 1:
			return "item." + "block_taru_kouji_f";
		case 2:
			return "item." + "block_taru_shubo_f";
		case 3:
			return "item." + "block_taru_moromi_f";
		case 4:
			return "item." + "block_taru_jukusei_f";
		case 5:
			return "item." + "block_taru_miso_f";
		case 6:
			return "item." + "block_taru_koucha_f";
		case 7:
			return "item." + "block_taru_vanilla_f";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 6));
		}
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		Block TARU = (k == 0)? Hakkou_Blocks.HAKKOUTARU : Hakkou_Blocks.HAKKOUTARU_sub;
		
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(TARU, pos, false, facing, (Entity)null)) {

			if (k == 0) {
				/** Place Block_1 to change it to Block_2 later. **/
				IBlockState putSTATE = TARU.getDefaultState().withProperty(Base_Taru15.STAGE_0_15, Integer.valueOf(0));
				worldIn.setBlockState(pos, putSTATE, 10); }
			
			if (k != 0) {
				/** Place Block_2 after changing it from Block_1. **/
				IBlockState putSTATE = TARU.getDefaultState().withProperty(Base_Taru15.STAGE_0_15, Integer.valueOf(this.takeMeta(k)));
				worldIn.setBlockState(pos, putSTATE, 10); }

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	private int takeMeta(int k) {
		if (k == 1) { return 0; }
		if (k == 2) { return 2; }
		if (k == 3) { return 4; }
		if (k == 4) { return 6; }
		if (k == 5) { return 8; }
		if (k == 6) { return 10; }
		if (k == 7) { return 12; }
		else { return 0; }
	}
	/** 0=麹1, 1=麹2 **/
	/** 2=酒母1, 3=酒母2 **/
	/** 4=もろみ1, 5=もろみ2 **/
	/** 6=熟成酒1, 7=熟成酒2 **/
	/** 8=味噌1, 9=味噌2 **/
	/** 10=紅茶1, 11=紅茶2 **/
	/** 12=バニラ1, 13=バニラ2, 14=バニラ3, 15=バニラ4 **/
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 0) { itemTip.add(I18n.format("tips.block_taru_hakkou.name")); }
		if (k == 1) { itemTip.add(I18n.format("tips.block_taru_kouji.name")); }
		if (k == 2) { itemTip.add(I18n.format("tips.block_taru_shubo.name")); }
		if (k == 3) { itemTip.add(I18n.format("tips.block_taru_moromi.name")); }
		if (k == 4) { itemTip.add(I18n.format("tips.block_taru_jukusei.name")); }
		if (k == 5) { itemTip.add(I18n.format("tips.block_taru_miso.name")); }
		if (k == 6) { itemTip.add(I18n.format("tips.block_taru_koucha.name")); }
		if (k == 7) { itemTip.add(I18n.format("tips.block_taru_vanilla.name")); }
	}
}
