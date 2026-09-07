package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Endai_sub extends BaseEndai {

	public Endai_sub(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_0_2)).intValue();
		EnumFacing playerFacing = playerIn.getHorizontalFacing().getOpposite();
		IBlockState upState = worldIn.getBlockState(pos.up());
		
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean upAble = upState.getMaterial().isReplaceable();
		boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
		
		if (success) {
			if (upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				Block bookBlock = (hItem == Items.BOOK)? Chinjufu_Blocks.DESKBOOK_1 : Chinjufu_Blocks.NOTEBOOK;
				
				worldIn.setBlockState(pos.up(), bookBlock.getDefaultState()
						.withProperty(BaseStage4_FaceDown.H_FACING, playerFacing)
						.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(1))); }
			return true; }
		
		if (hStack.isEmpty()) {
			if (playerIn.isSneaking()) {
				CMEvents.soundWoodPlace(worldIn, pos);
				worldIn.setBlockState(pos, Unit_Blocks.ENDAI.getDefaultState()
						.withProperty(Endai.STAGE_0_2, Integer.valueOf(i))); }
			return true;
		}
		return false;
	}
}
