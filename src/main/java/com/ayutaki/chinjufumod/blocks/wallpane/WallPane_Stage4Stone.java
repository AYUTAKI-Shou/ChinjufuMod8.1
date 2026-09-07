package com.ayutaki.chinjufumod.blocks.wallpane;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WallPane_Stage4Stone extends BaseStage4_WP {

	public WallPane_Stage4Stone(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.WALLPANEL);
		setSoundType(SoundType.STONE);
		setResistance(10.0F);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (hStack.isEmpty() && playerIn.isSneaking()) {
			CMEvents.soundStonePlace(worldIn, pos);
			worldIn.setBlockState(pos, state.cycleProperty(STAGE_1_4), 2);
			return true;
		}
		return false;
	}
}
