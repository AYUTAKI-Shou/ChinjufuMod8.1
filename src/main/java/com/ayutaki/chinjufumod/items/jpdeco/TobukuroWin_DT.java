package com.ayutaki.chinjufumod.items.jpdeco;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TobukuroWin_DT extends IR_Wadeco {

	public TobukuroWin_DT(String name) {
		super(name);
		setUnlocalizedName(name);
	}
	
	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 150;
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

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(takeRight(), pos, false, facing, (Entity)null)) {
			if (playerIn instanceof EntityPlayerMP) { CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, pos, hStack); }
			
			if (playerIn.isSneaking()) {
				worldIn.setBlockState(pos, takeLeft().getDefaultState()
						.withProperty(BaseStage3_Face.H_FACING, direction)
						.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1)), 10); }

			else { 
				worldIn.setBlockState(pos, takeRight().getDefaultState()
					.withProperty(BaseStage3_Face.H_FACING, direction)
					.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1)), 10); }

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}
	
	private Block takeRight() {
		if (this == Items_Wadeco.TOBUKURO_WINR) { return Slidedoor_Blocks.TOBUKURO_WINR; }
		else { return Slidedoor_Blocks.TOBUKUROS_WINR; }
	}
	
	private Block takeLeft() {
		if (this == Items_Wadeco.TOBUKURO_WINR) { return Slidedoor_Blocks.TOBUKURO_WINL; }
		else { return Slidedoor_Blocks.TOBUKUROS_WINL; }
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_tobukuro.name"));
	}
}
