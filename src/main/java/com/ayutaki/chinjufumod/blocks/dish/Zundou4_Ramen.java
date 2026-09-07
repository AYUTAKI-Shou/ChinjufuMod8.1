package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Zundou4_Ramen extends BaseZundou_4Cook {
	/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
	public Zundou4_Ramen(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_4);
		
		boolean soup = (hItem == Items_Teatime.SHOUYU_Rsoup || hItem == Items_Teatime.MISO_Rsoup|| hItem == Items_Teatime.SHIO_Rsoup || 
				hItem == Items_Teatime.SARA);
		boolean other = (hItem == Items_Teatime.RAMEN_SHOUYU || hItem == Items_Teatime.RAMEN_MISO || hItem == Items_Teatime.RAMEN_SHIO || 
				hItem == Items_Teatime.RAMEN_nama);
		
		if (!soup && !other) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 1=raw-cold, 2=raw-hot, 3=boiled-cold, 4=boiled-hot **/
		else {
			if (i == 3 || i == 4) {

				if (hItem == Items_Teatime.SHOUYU_Rsoup) {
					/** Collect with an Item **/
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN_SHOUYU);
					CMEvents.addEXP(1, worldIn, pos); }
				
				if (hItem == Items_Teatime.MISO_Rsoup) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN_MISO);
					CMEvents.addEXP(1, worldIn, pos); }
				
				if (hItem == Items_Teatime.SHIO_Rsoup) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.RAMEN_SHIO);
					CMEvents.addEXP(1, worldIn, pos); }
				
				if (hItem == Items_Teatime.SARA) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.SOBA_PLATE); }
				
				if (soup) {
					worldIn.setBlockState(pos, Dish_Blocks.ZUNDOU_MIZU.getDefaultState()
							.with(Zundou_Mizu.H_FACING, state.get(H_FACING))
							.with(Zundou_Mizu.STAGE_1_2, Integer.valueOf(i - 2)), 3); }
				
				if (!soup) { }
			}
				
			else { //i != 3 && i != 4
				CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		}
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(Items_Teatime.ZUNDOU_MIZU);
	}
}
