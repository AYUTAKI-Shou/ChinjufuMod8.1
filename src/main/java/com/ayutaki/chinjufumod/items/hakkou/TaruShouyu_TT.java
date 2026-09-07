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

public class TaruShouyu_TT extends IBR_Teatime {

	public TaruShouyu_TT(String name) {
		super(name, Hakkou_Blocks.SHOUYUTARU);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "block_taru_shouyu_f";
		case 2:
			return "item." + "block_taru_komezu_f";
		case 3:
			return "item." + "block_taru_kinoko_f";
		case 4:
			return "item." + "block_taru_nori_f";
		case 5:
			return "item." + "block_taru_pepper_f";
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

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		Block TARU = Hakkou_Blocks.SHOUYUTARU;
		
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(TARU, pos, false, facing, (Entity)null)) {

			IBlockState putSTATE = TARU.getDefaultState().withProperty(Base_Taru15.STAGE_0_15, Integer.valueOf(this.takeMeta(k)));
			worldIn.setBlockState(pos, putSTATE, 10);
			
			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	private int takeMeta(int k) {
		if (k == 1) { return 1; }
		if (k == 2) { return 6; }
		if (k == 3) { return 11; }
		if (k == 4) { return 13; }
		if (k == 5) { return 0; }
		else { return 0; }
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1) { itemTip.add(I18n.format("tips.block_taru_shouyu.name")); }
		if (k == 2) { itemTip.add(I18n.format("tips.block_taru_komezu.name")); }
		if (k == 3) { itemTip.add(I18n.format("tips.block_taru_kinoko.name")); }
		if (k == 4) { itemTip.add(I18n.format("tips.block_taru_nori.name")); }
		if (k == 5) { itemTip.add(I18n.format("tips.block_taru_pepper.name")); }
	}
}
