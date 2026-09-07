package com.ayutaki.chinjufumod.blocks.season;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SnowMan_Top1 extends Base_SnowManTop {

	/** TOP1 1=normal, 2=carrot, 3=Roma, 4=blank **/
	public SnowMan_Top1(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		
		Block downBlock = worldIn.getBlockState(pos.down()).getBlock();
		boolean downSNOW = (downBlock instanceof SnowMan_BotDown);
		
		IBlockState downState1 = Seasonal_Blocks.SNOWMAN_BOT1.getDefaultState()
				.withProperty(Base_SnowManBot.H_FACING, state.getValue(H_FACING));
		IBlockState downState1d = Seasonal_Blocks.SNOWMAN_BOT1D.getDefaultState()
				.withProperty(Base_SnowManBot.H_FACING, state.getValue(H_FACING));

		IBlockState SNOWMAN_BOT = downSNOW? downState1d : downState1;
		Block SNOWMAN_BOT25 = downSNOW? this.takeBOTD(k) : this.takeBOT(k);
		
		/** TOP1 1=normal, 2=carrot, 3=Roma, 4=blank **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		switch (i) {
		case 1 :
		default :
			if (hItem == Items.CARROT) {
				CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(2)), 3);
				worldIn.setBlockState(pos.down(), SNOWMAN_BOT.withProperty(STAGE_1_4, Integer.valueOf(2)), 3); }
			
			if (hItem != Items.CARROT) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;

			
		case 2 :
			if (hItem == Items_Teatime.FOOD_TOMATO) {
				CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(3)), 3);
				worldIn.setBlockState(pos.down(), SNOWMAN_BOT.withProperty(STAGE_1_4, Integer.valueOf(3)), 3); }
			
			if (hItem == Items.BUCKET) {
				CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(4)), 3);
				worldIn.setBlockState(pos.down(), SNOWMAN_BOT.withProperty(STAGE_1_4, Integer.valueOf(4)), 3); }
			
			if (hItem == Item.getItemFromBlock(Blocks.WOOL)) {
				CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
				
				worldIn.setBlockState(pos, takeTOP(k).getDefaultState()
						.withProperty(Base_SnowManTop.H_FACING, state.getValue(H_FACING))
						.withProperty(Base_SnowManTop.STAGE_1_4, Integer.valueOf(takeMeta(k))), 3);
				worldIn.setBlockState(pos.down(), SNOWMAN_BOT25.getDefaultState()
						.withProperty(Base_SnowManBot.H_FACING, state.getValue(H_FACING))
						.withProperty(Base_SnowManBot.STAGE_1_4, Integer.valueOf(takeMeta(k))), 3);
			} // WOOL
			
			if (hItem != Items_Teatime.FOOD_TOMATO && hItem != Items.BUCKET && hItem != Item.getItemFromBlock(Blocks.WOOL)) {
				CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 3 :
			if (hStack.isEmpty()) {
				CMEvents.soundSnowBreak(worldIn, pos);
				CMEvents.mode1Through_takeItem(playerIn, hand, Items_Teatime.FOOD_TOMATO, 0);
			
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(2)), 3);
				worldIn.setBlockState(pos.down(), SNOWMAN_BOT.withProperty(STAGE_1_4, Integer.valueOf(2)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
			break;
			
		case 4 :
			if (hStack.isEmpty()) {
				CMEvents.soundSnowBreak(worldIn, pos);
				CMEvents.mode1Through_takeItem(playerIn, hand, Items.BUCKET, 0);
			
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(2)), 3);
				worldIn.setBlockState(pos.down(), SNOWMAN_BOT.withProperty(STAGE_1_4, Integer.valueOf(2)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
			break;
		}
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	/** TOP1 1=normal, 2=carrot, 3=Roma, 4=blank **/
	/** TOP2 White=1, Orange=2, Magenta=3, LightBlue=4 **/
	/** TOP3 Yellow=5, Lime=6, Pink=7, Gray=8, **/
	/** TOP4 LightGray=9, Cyan=10, Purple=11, Blue=12 **/
	/** TOP5 Brown=13, Green=14, Red=15, Black=16 **/
	private Block takeTOP(int k) {
		if (k <= 3) { return Seasonal_Blocks.SNOWMAN_TOP2; }
		if (k >= 4 && k <= 7) { return Seasonal_Blocks.SNOWMAN_TOP3; }
		if (k >= 8 && k <= 11) { return Seasonal_Blocks.SNOWMAN_TOP4; }
		else { return Seasonal_Blocks.SNOWMAN_TOP5; }
	}
	
	private Block takeBOT(int k) {
		if (k <= 3) { return Seasonal_Blocks.SNOWMAN_BOT2; }
		if (k >= 4 && k <= 7) { return Seasonal_Blocks.SNOWMAN_BOT3; }
		if (k >= 8 && k <= 11) { return Seasonal_Blocks.SNOWMAN_BOT4; }
		else { return Seasonal_Blocks.SNOWMAN_BOT5; }
	}

	private Block takeBOTD(int k) {		
		if (k <= 3) { return Seasonal_Blocks.SNOWMAN_BOT2D; }
		if (k >= 4 && k <= 7) { return Seasonal_Blocks.SNOWMAN_BOT3D; }
		if (k >= 8 && k <= 11) { return Seasonal_Blocks.SNOWMAN_BOT4D; }
		else { return Seasonal_Blocks.SNOWMAN_BOT5D; }
	}
	
	private int takeMeta(int k) {
		if (k == 0 || k == 4 || k == 8 || k == 12) { return 1; }
		if (k == 1 || k == 5 || k == 9 || k == 13) { return 2; }
		if (k == 2 || k == 6 || k == 10 || k == 14) { return 3; }
		else { return 4; }
	}
}
