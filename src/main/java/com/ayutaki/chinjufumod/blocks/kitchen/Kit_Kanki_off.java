package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Kit_Kanki_off extends Base_Kanki {

	public Kit_Kanki_off(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (hStack.isEmpty()) {
			if (playerIn.isSneaking()) {
				worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);
				worldIn.setBlockState(pos, state.cycleProperty(STAGE_1_3), 2); }
			
			else { //!isCrouching
				CMEvents.soundStoneButton_On(worldIn, pos);
				worldIn.setBlockState(pos, Kitchen_Blocks.KIT_KANKI_2.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(STAGE_1_3, state.getValue(STAGE_1_3))); }
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_kit_kanki.name"));
	}
}
