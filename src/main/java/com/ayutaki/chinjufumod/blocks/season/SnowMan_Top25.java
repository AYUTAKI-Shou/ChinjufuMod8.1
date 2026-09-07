package com.ayutaki.chinjufumod.blocks.season;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SnowMan_Top25 extends Base_SnowManTop {

	public SnowMan_Top25(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		IBlockState downState = worldIn.getBlockState(pos.down());
		Block downBlock = downState.getBlock();

		if (hStack.isEmpty()) {
			CMEvents.soundSnowBreak(worldIn, pos);
			CMEvents.mode1Through_takeItem(playerIn, hand, Item.getItemFromBlock(Blocks.WOOL), this.takeMeta(state));
			
			worldIn.setBlockState(pos, Seasonal_Blocks.SNOWMAN_TOP1.getDefaultState()
					.withProperty(Base_SnowManTop.H_FACING, state.getValue(H_FACING))
					.withProperty(Base_SnowManTop.STAGE_1_4, Integer.valueOf(2)), 3);
			
			if (downBlock instanceof SnowMan_BotDown) {
				worldIn.setBlockState(pos.down(), Seasonal_Blocks.SNOWMAN_BOT1D.getDefaultState()
						.withProperty(Base_SnowManBot.H_FACING, state.getValue(H_FACING))
						.withProperty(Base_SnowManBot.STAGE_1_4, Integer.valueOf(2)), 3); }
			else {
				worldIn.setBlockState(pos.down(), Seasonal_Blocks.SNOWMAN_BOT1.getDefaultState()
						.withProperty(Base_SnowManBot.H_FACING, state.getValue(H_FACING))
						.withProperty(Base_SnowManBot.STAGE_1_4, Integer.valueOf(2)), 3); } 
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	private int takeMeta(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		/** TOP2 White=0, Orange=1, Magenta=2, LightBlue=3 **/
		/** TOP3 Yellow=4, Lime=5, Pink=6, Gray=7, **/
		/** TOP4 LightGray=8, Cyan=9, Purple=10, Blue=11 **/
		/** TOP5 Brown=12, Green=13 Red=14, Black=15 **/
		
		if (this == Seasonal_Blocks.SNOWMAN_TOP3) { return i + 3; }
		if (this == Seasonal_Blocks.SNOWMAN_TOP4) { return i + 7; }
		if (this == Seasonal_Blocks.SNOWMAN_TOP5) { return i + 11; }
		else { return i - 1; }
	}
}
