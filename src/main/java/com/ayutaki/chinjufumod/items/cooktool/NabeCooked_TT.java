package com.ayutaki.chinjufumod.items.cooktool;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.BaseNabe;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
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

public class NabeCooked_TT extends IBR_Teatime {

	public NabeCooked_TT(String name, Block putBlock) {
		super(name, putBlock);
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

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(this.block, pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			IBlockState putSTATE = this.block.getDefaultState()
					.withProperty(BaseNabe.H_FACING, direction)
					.withProperty(BaseNabe.STAGE_1_4, Integer.valueOf(1));
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
		if (this == Items_Teatime.NABEMISO || this == Items_Teatime.NABEZENZAI_M || this == Items_Teatime.NABEZENZAI_K) { 
			itemTip.add(I18n.format("tips.take_shikki.name")); }
		
		if (this == Items_Teatime.NABETORI) { itemTip.add(I18n.format("tips.block_food_nabe_1.name")); }
		if (this == Items_Teatime.NABECORNSOUP) { itemTip.add(I18n.format("tips.take_plate.name")); }
		if (this == Items_Teatime.NABEGOHAN) { itemTip.add(I18n.format("tips.block_food_nabegohan_1.name")); }
		
		if (this == Items_Teatime.NABEGOHAN_TAKE || this == Items_Teatime.NABEGOHAN_KURI || this == Items_Teatime.NABESEKIHAN) { 
			itemTip.add(I18n.format("tips.block_food_nabegohantake_1.name")); }

		if (this == Items_Teatime.NABEAMAZAKE) { itemTip.add(I18n.format("tips.block_food_nabeaz_1.name")); }
	}
}
