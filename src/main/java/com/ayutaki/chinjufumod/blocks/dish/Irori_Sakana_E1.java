package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Irori_Sakana_E1 extends BaseIrori_Sakana {

	public Irori_Sakana_E1(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_15);

		boolean hitNorth = (hit.getLocation().x - (double)pos.getX() > 0.3D) && (hit.getLocation().x - (double)pos.getX() < 0.7D) && (hit.getLocation().z - (double)pos.getZ() < 0.3D);
		boolean hitSouth = (hit.getLocation().x - (double)pos.getX() > 0.3D) && (hit.getLocation().x - (double)pos.getX() < 0.7D) && (hit.getLocation().z - (double)pos.getZ() > 0.7D);
		boolean hitEast = (hit.getLocation().x - (double)pos.getX() > 0.7D) && (hit.getLocation().z - (double)pos.getZ() > 0.3D) && (hit.getLocation().z - (double)pos.getZ() < 0.7D);
		boolean hitWest = (hit.getLocation().x - (double)pos.getX() < 0.3D) && (hit.getLocation().z - (double)pos.getZ() > 0.3D) && (hit.getLocation().z - (double)pos.getZ() < 0.7D);

		switch (i) {
		case 0: //00 EEER 北東南西
		default:
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (!hitWest) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(11)), 3); }
					
					if (hitEast) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(9)), 3); }
					
					if (hitSouth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(3)), 3); }
					
					if (hitWest) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;

		case 1: //01 EEEC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (!hitWest) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(12)), 3); }
					
					if (hitEast) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(10)), 3); }
					
					if (hitSouth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(4)), 3); }
					
					if (hitWest) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;

		case 2: //02 EERE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (!hitSouth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitSouth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(13)), 3); }
					
					if (hitEast) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(11)), 3); }
					
					if (hitSouth) { CMEvents.soundTouchBlock(worldIn, pos); }
					
					if (hitWest) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(3)), 3); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 3: //03 EERR 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitEast) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitSouth || hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } } 
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(14)), 3); }
					
					if (hitEast) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(12)), 3); }
					
					if (hitSouth || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 4: //04 EERC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitEast) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitSouth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(2)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(15)), 3); }
					
					if (hitEast) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(13)), 3); }
					
					if (hitSouth || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;

		case 5: //05 EECE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (!hitSouth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(0)), 3); }
					
					if (hitEast) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(14)), 3); }
					
					if (hitSouth) { CMEvents.soundTouchBlock(worldIn, pos); }

					if (hitWest) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(6)), 3); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 6: //06 EECR 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitEast) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(0)), 3); }
				
				if (hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(1)), 3); }
					
					if (hitEast) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(15)), 3); }
					
					if (hitSouth || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 7: //07 EECC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitEast) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(1)), 3); }

				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(5)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(2)), 3); }
					
					if (hitEast) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
								.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(0)), 3); }
					
					if (hitSouth || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;

		case 8: //08 EREE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (!hitEast) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } } 
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(3)), 3); }
					
					if (hitEast) { CMEvents.soundTouchBlock(worldIn, pos); }
					
					if (hitSouth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(11)), 3); }
					
					if (hitWest) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(9)), 3); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 9: //09 ERER 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitSouth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast || hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(4)), 3); }
					
					if (hitEast || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); }
					
					if (hitSouth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(12)), 3); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 10: //10 EREC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth || hitSouth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(8)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(5)), 3); }
					
					if (hitEast || hitWest) { CMEvents.soundTouchBlock(worldIn, pos); }

					if (hitSouth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(13)), 3); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 11: //11 ERRE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitEast || hitSouth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitNorth || hitWest) { CMEvents.textNotHave(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(6)), 3); }
					
					if (hitEast || hitSouth) { CMEvents.soundTouchBlock(worldIn, pos); }
					
					if (hitWest) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(12)), 3); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;

		case 12: //12 ERRR 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (!hitNorth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(7)), 3); }
					
					if (!hitNorth) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
			
		case 13: //13 ERRC 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitEast || hitSouth) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitWest) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(11)), 3); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(8)), 3); }
					
					if (!hitNorth) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;

		case 14: //14 ERCE 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitEast) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(8)), 3); }
				
				if (hitNorth || hitWest) { CMEvents.textNotHave(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(9)), 3); }
					
					if (hitEast || hitSouth) { CMEvents.soundTouchBlock(worldIn, pos); }
					
					if (hitWest) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(15)), 3); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;

		case 15: //15 ERCR 北東南西
			/** Take it. **/
			if (hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) {
				if (hitNorth) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				
				if (hitSouth) {
					CMEvents.take_SAKANA(worldIn, pos, playerIn);
					worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(9)), 3); }
				
				if (hitEast || hitWest) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); } }
			
			/** Place it. **/
			if (!hStack.isEmpty() && hItem == Items_Teatime.KUSHI_SAKANA) {
				
				if (waterOUT(state)) {
					if (hitNorth) {
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_R1.defaultBlockState()
							.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(10)), 3); }
					
					if (!hitNorth) { CMEvents.soundTouchBlock(worldIn, pos); } }
				
				if (waterIn(state)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); } }
			
			if (!hStack.isEmpty() && hItem != Items_Teatime.KUSHI_SAKANA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			break;
		} // switch
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_0_15);

		if (waterIn(state)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);

			if (i == 12 || i == 13 ||i == 15) {
				CMEvents.destroyN_STICK_ROTTEN(3, worldIn, pos); }
			
			if (i == 3 || i == 4 || i == 6 || i == 7 || i == 9 || i == 10 || i == 11 || i == 14) {
				CMEvents.destroyN_STICK_ROTTEN(2, worldIn, pos); }
			
			if (i == 0 || i == 1 || i == 2 || i == 5 || i == 8) {
				CMEvents.destroyN_STICK_ROTTEN(1, worldIn, pos); }
		}
		
		else { //waterOUT
			if (cookingIn(worldIn, pos, Direction.DOWN)) {
				boolean COOKED = (i == 1 || i == 7);
				if (COOKED) { }

				else {
					worldIn.getBlockTicks().scheduleTick(pos, this, COOK_TIME);
					
					if (i == 0) { //1 EEEC
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(1)), 3); }
		
					if (i == 2) { //5 EECE
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(5)), 3); }
		
					if (i == 3 || i == 4 || i == 6) { //7 EECC
						worldIn.setBlock(pos, state.setValue(STAGE_0_15, Integer.valueOf(7)), 3); }
		
					if (i == 8) { //1 ECEE
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
								.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(1)), 3); }
		
					if (i == 9 || i == 10) { //3 ECEC
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
								.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(3)), 3); }
		
					if (i == 11 || i == 14) {//7 ECCE
							worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
								.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(7)), 3); }
		
					if (i == 12 || i == 13 || i == 15) {//9 ECCC
						worldIn.setBlock(pos, Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
								.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(9)), 3); } } 
			}
	
			else { } //cookingOUT
		}
	}
}
