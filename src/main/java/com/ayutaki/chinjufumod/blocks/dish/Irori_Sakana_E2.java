package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Irori_Sakana_E2 extends BaseIrori_Sakana {

	public Irori_Sakana_E2(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		boolean hitNorth = (hitX > 0.3D) && (hitX < 0.7D) && (hitZ < 0.3D);
		boolean hitSouth = (hitX > 0.3D) && (hitX < 0.7D) && (hitZ > 0.7D);
		boolean hitEast = (hitX > 0.7D) && (hitZ > 0.3D) && (hitZ < 0.7D);
		boolean hitWest = (hitX < 0.3D) && (hitZ > 0.3D) && (hitZ < 0.7D);

		switch (i) {
		case 0 :
		default: //00 ERCC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(10)), 3); }

				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(14)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(11)), 3); }
				
				if (hitNorth != true) { CMEvents.soundTouchBlock(worldIn, pos); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
	
		case 1 : //01 ECEE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitEast != true) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(12)), 3); }
				
				if (hitEast) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitSouth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(4)), 3); }
				
				if (hitWest) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(2)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
	
		case 2 : //02 ECER 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitSouth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(0)), 3); }
				
				if (hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(13)), 3); }
				
				if (hitEast || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitSouth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(5)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 3 : //03 ECEC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitSouth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(1)), 3); }
				
				if (hitWest) {					
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(1)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(14)), 3); }
				
				if (hitEast || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitSouth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(6)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 4 : //04 ECRE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitWest) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(2)), 3); }
				
				if (hitSouth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(15)), 3); }
				
				if (hitEast || hitSouth) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitWest) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(5)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
	
		case 5 : //05 ECRR 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(3)), 3); }
				
				if (hitSouth || hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(0)), 3); }
				
				if (hitNorth != true) { CMEvents.soundTouchBlock(worldIn, pos); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 6 : //06 ECRC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {					
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(4)), 3); }
				
				if (hitSouth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitWest) {					
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(4)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(1)), 3); }
				
				if (hitNorth != true) { CMEvents.soundTouchBlock(worldIn, pos); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 7 :
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitWest) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(5)), 3); }

				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(1)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(2)), 3); }
				
				if (hitEast || hitSouth) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitWest) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(8)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
	
		case 8 : //08 ECCR 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(6)), 3); }

				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(2)), 3); }
				
				if (hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(3)), 3); }
				
				if (hitNorth != true) { CMEvents.soundTouchBlock(worldIn, pos); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 9 : //09 ECCC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_E1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(7)), 3); }

				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(3)), 3); }

				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(7)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(4)), 3); }
				
				if (hitNorth != true) { CMEvents.soundTouchBlock(worldIn, pos); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 10 : //10 REEE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitNorth != true) { CMEvents.textNotHave(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitEast) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(3)), 3); }
				
				if (hitSouth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(13)), 3); }
				
				if (hitWest) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(11)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 11 : //11 REER 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitEast || hitSouth) { CMEvents.textNotHave(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitEast) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(4)), 3); }
				
				if (hitSouth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(14)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
	
		case 12 : //12 REEC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitEast || hitSouth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(10)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitEast) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(5)), 3); }
				
				if (hitSouth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(15)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 13 : //13 RERE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitSouth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitEast || hitWest) { CMEvents.textNotHave(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitSouth) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitEast) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(6)), 3); }
				
				if (hitWest) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(14)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
	
		case 14 : //14 RERR 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitEast != true) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitEast) { CMEvents.textNotHave(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitEast != true) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitEast) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(7)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
	
		case 15 : //15 RERC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitSouth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitEast) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(13)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				if (hitEast != true) { CMEvents.soundTouchBlock(worldIn, pos); }
				
				if (hitEast) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R1.getDefaultState()
							.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(8)), 3); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
		} // switch

		/** 'true' to not put anything on top. **/
		return true;
	}

	/* TickRandom */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (cookingIn(worldIn, pos)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			
			if (i == 0 || i == 5 || i == 6 || i == 8) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(9))); } //9 ECCC

			if (i == 2) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(3))); } //3 ECEC

			if (i == 4) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(7))); } //7 ECCE

			if (i == 10) {
				worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(5))); } //5 CEEE

			if (i == 11 || i == 12) {
				worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(7))); } //7 CEEC

			if (i == 13) {
				worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(11))); } //11 CECE

			if (i == 14 || i == 15) {
				worldIn.setBlockState(pos, Dish_Blocks.IRORISAKANA_R2.getDefaultState()
						.withProperty(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(13))); } //13 CECC

			else { }
		}
		else { }
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (i == 0) { stack.add(cloneStack(1));
							stack.add(cloneCook(2)); }

		if (i == 1) { stack.add(cloneCook(1)); }

		if (i == 2) { stack.add(cloneStack(1));
							stack.add(cloneCook(1)); }

		if (i == 3) { stack.add(cloneCook(2)); }

		if (i == 4) { stack.add(cloneStack(1));
							stack.add(cloneCook(1)); }

		if (i == 5) { stack.add(cloneStack(2));
							stack.add(cloneCook(1)); }

		if (i == 6) { stack.add(cloneStack(1));
							stack.add(cloneCook(2)); }

		if (i == 7) { stack.add(cloneCook(2)); }

		if (i == 8) { stack.add(cloneStack(1));
							stack.add(cloneCook(2)); }

		if (i == 9) { stack.add(cloneCook(3)); }

		if (i == 10) { stack.add(cloneStack(1)); }

		if (i == 11) { stack.add(cloneStack(2)); }

		if (i == 12) { stack.add(cloneStack(1));
							stack.add(cloneCook(1)); }

		if (i == 13) { stack.add(cloneStack(2)); }

		if (i == 14) { stack.add(cloneStack(3)); }

		if (i == 15) { stack.add(cloneStack(2));
							stack.add(cloneCook(1)); }

		return stack;
	}
}
