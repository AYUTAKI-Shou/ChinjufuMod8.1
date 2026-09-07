package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.Config_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class TrayLetter extends BaseTray {

	public TrayLetter(Block.Properties props) {
		super(props);
	}
	
	/* Write Book */
	private void writebook(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consume1_seWrite(worldIn, pos, playerIn, hand);
		playerIn.giveExperiencePoints(-10); //戦闘詳報1冊=10連
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		Direction direction = state.get(H_FACING);

		if (state.get(LOST)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { // !LOST
			/* Battle report */
			if (hItem == Items_Chinjufu.SHOUHOU_empty) {
				
				if (playerIn.experienceTotal >= 100) {
					CMEvents.consume1_seWrite(worldIn, pos, playerIn, hand);
					playerIn.giveExperiencePoints(-100);
					CMEvents.take1Item(playerIn, hand, Items_Chinjufu.SHOUHOU); }

				/** Not enough EXP **/
				if (playerIn.experienceTotal < 100) { CMEvents.textNotEnough_EXP(worldIn, pos, playerIn); }
			} // SHOUHOU_empty
			
			if (hItem.isIn(ItemTags.LECTERN_BOOKS)) { 
				worldIn.playSound(null, pos, SoundEvents_CM.WRITE_REPORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
				return ActionResultType.PASS; }
			
			
			/* Enchantbook 5 times */
			if (hItem == Items.BOOK) {
				if (playerIn.experienceTotal >= 50) {					
					BlockState northState = worldIn.getBlockState(pos.north());
					BlockState southState = worldIn.getBlockState(pos.south());
					BlockState eastState = worldIn.getBlockState(pos.east());
					BlockState westState = worldIn.getBlockState(pos.west());
					Block northBlock = northState.getBlock();
					Block southBlock = southState.getBlock();
					Block eastBlock = eastState.getBlock();
					Block westBlock = westState.getBlock();
					
					IFluidState northFluid = worldIn.getFluidState(pos.north());
					IFluidState southFluid = worldIn.getFluidState(pos.south());
					IFluidState eastFluid = worldIn.getFluidState(pos.east());
					IFluidState westFluid = worldIn.getFluidState(pos.west());
					
					boolean useMakimono = (this == Unit_Blocks.FUDETRAY && Config_CM.getInstance().useMAKIMONO());

					if (useMakimono) {
						BlockState makiMono = Unit_Blocks.WRITTEN_MAKIMONO.getDefaultState().with(WrittenMakimono.H_FACING, direction);
						
						switch (direction) {
						case NORTH:
						default:
							/** left **/
							if (eastState.getMaterial().isReplaceable()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.east(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.east(), Direction.DOWN))
										.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(eastFluid.getFluid() == Fluids.WATER)), 3); }
							
							else { //!east_Replaceable
								if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.get(WrittenMakimono.STAGE_1_5) != 5) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.east(), eastState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(eastState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
								
								/** right **/
								if (eastBlock != Unit_Blocks.WRITTEN_MAKIMONO ||
										(eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.get(WrittenMakimono.STAGE_1_5) == 5)) {
									if (westState.getMaterial().isReplaceable()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.west(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.west(), Direction.DOWN))
												.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(westFluid.getFluid() == Fluids.WATER)), 3); }

									
									else { //!west_Replaceable
										if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.get(WrittenMakimono.STAGE_1_5) != 5) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.west(), westState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(westState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
										
										/** front **/
										if (westBlock != Unit_Blocks.WRITTEN_MAKIMONO ||
												(westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.get(WrittenMakimono.STAGE_1_5) == 5)) {
											if (southState.getMaterial().isReplaceable()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.south(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.south(), Direction.DOWN))
														.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(southFluid.getFluid() == Fluids.WATER)), 3); }
											
											else { //!south_Replaceable
												if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.get(WrittenMakimono.STAGE_1_5) != 5) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlockState(pos.south(), southState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(southState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;

						case SOUTH:
							/** left **/
							if (westState.getMaterial().isReplaceable()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.west(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.west(), Direction.DOWN))
										.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(westFluid.getFluid() == Fluids.WATER)), 3); }
							
							else { //!west_Replaceable
								if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.get(WrittenMakimono.STAGE_1_5) != 5) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.west(), westState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(westState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
								
								/** right **/
								if (westBlock != Unit_Blocks.WRITTEN_MAKIMONO ||
										(westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.get(WrittenMakimono.STAGE_1_5) == 5)) {
									if (eastState.getMaterial().isReplaceable()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.east(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.east(), Direction.DOWN))
												.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(eastFluid.getFluid() == Fluids.WATER)), 3); }
									
									else { //!east_Replaceable
										if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.get(WrittenMakimono.STAGE_1_5) != 5) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.east(), eastState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(eastState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
										
										/** front **/
										if (eastBlock != Unit_Blocks.WRITTEN_MAKIMONO ||
												(eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.get(WrittenMakimono.STAGE_1_5) == 5)) {
											if (northState.getMaterial().isReplaceable()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.north(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.north(), Direction.DOWN))
														.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(northFluid.getFluid() == Fluids.WATER)), 3); }
											
											else { //!north_Replaceable
												if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.get(WrittenMakimono.STAGE_1_5) != 5) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlockState(pos.north(), northState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(northState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;

						case EAST:
							/** left **/
							if (southState.getMaterial().isReplaceable()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.south(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.south(), Direction.DOWN))
										.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(southFluid.getFluid() == Fluids.WATER)), 3); }
							
							else { //!south_Replaceable
								if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.get(WrittenMakimono.STAGE_1_5) != 5) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.south(), southState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(southState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
								
								/** right **/
								if (southBlock != Unit_Blocks.WRITTEN_MAKIMONO ||
										(southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.get(WrittenMakimono.STAGE_1_5) == 5)) {
									if (northState.getMaterial().isReplaceable()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.north(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.north(), Direction.DOWN))
												.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(northFluid.getFluid() == Fluids.WATER)), 3); }
									
									else { //!north_Replaceable
										if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.get(WrittenMakimono.STAGE_1_5) != 5) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.north(), northState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(northState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
										
										/** front **/
										if (northBlock != Unit_Blocks.WRITTEN_MAKIMONO ||
												(northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.get(WrittenMakimono.STAGE_1_5) == 5)) {
											if (westState.getMaterial().isReplaceable()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.west(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.west(), Direction.DOWN))
														.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(westFluid.getFluid() == Fluids.WATER)), 3); }
											
											else { //!west_Replaceable
												if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.get(WrittenMakimono.STAGE_1_5) != 5) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlockState(pos.west(), westState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(westState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;
							
						case WEST:
							/** left **/
							if (northState.getMaterial().isReplaceable()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.north(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.north(), Direction.DOWN))
										.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(northFluid.getFluid() == Fluids.WATER)), 3); }
							
							else { //!north_Replaceable
								if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.get(WrittenMakimono.STAGE_1_5) != 5) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.north(), northState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(northState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
								
								/** right **/
								if (northBlock != Unit_Blocks.WRITTEN_MAKIMONO ||
										(northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.get(WrittenMakimono.STAGE_1_5) == 5)) {
									if (southState.getMaterial().isReplaceable()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.south(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.south(), Direction.DOWN))
												.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(southFluid.getFluid() == Fluids.WATER)), 3); }
									
									else { //!south_Replaceable
										if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.get(WrittenMakimono.STAGE_1_5) != 5) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.south(), southState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(southState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
										
										/** front **/
										if (southBlock != Unit_Blocks.WRITTEN_MAKIMONO ||
												(southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.get(WrittenMakimono.STAGE_1_5) == 5)) {
											if (eastState.getMaterial().isReplaceable()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.east(), makiMono.with(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.east(), Direction.DOWN))
														.with(WrittenMakimono.WATERLOGGED, Boolean.valueOf(eastFluid.getFluid() == Fluids.WATER)), 3); }
											
											else { //!east_Replaceable
												if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.get(WrittenMakimono.STAGE_1_5) != 5) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlockState(pos.east(), eastState.with(WrittenMakimono.STAGE_1_5, Integer.valueOf(eastState.get(WrittenMakimono.STAGE_1_5) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;
						} // switch
					} //useMakimono
					
					else {
						BlockState takeState = Unit_Blocks.WRITTEN_BOOK.getDefaultState().with(WrittenBook.H_FACING, direction);
						
						switch (direction) {
						case NORTH:
						default:
							/** left **/
							if (eastState.getMaterial().isReplaceable()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.east(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.east(), Direction.DOWN))
										.with(WrittenBook.WATERLOGGED, Boolean.valueOf(eastFluid.getFluid() == Fluids.WATER)), 3); }
							
							else { //!east_Replaceable
								if (eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.get(WrittenBook.STAGE_1_4) != 4) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.east(), eastState.with(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
								
								/** right **/
								if (eastBlock != Unit_Blocks.WRITTEN_BOOK ||
										(eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.get(WrittenBook.STAGE_1_4) == 4)) {
									if (westState.getMaterial().isReplaceable()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.west(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.west(), Direction.DOWN))
												.with(WrittenBook.WATERLOGGED, Boolean.valueOf(westFluid.getFluid() == Fluids.WATER)), 3); }
									
									else { //!west_Replaceable
										if (westBlock == Unit_Blocks.WRITTEN_BOOK && westState.get(WrittenBook.STAGE_1_4) != 4) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.west(), westState.with(WrittenBook.STAGE_1_4, Integer.valueOf(westState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
										
										/** front **/
										if (westBlock != Unit_Blocks.WRITTEN_BOOK ||
												(westBlock == Unit_Blocks.WRITTEN_BOOK && westState.get(WrittenBook.STAGE_1_4) == 4)) {
											if (southState.getMaterial().isReplaceable()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.south(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.south(), Direction.DOWN))
														.with(WrittenBook.WATERLOGGED, Boolean.valueOf(southFluid.getFluid() == Fluids.WATER)), 3); }
											
											else { //!south_Replaceable
												if (southBlock == Unit_Blocks.WRITTEN_BOOK && southState.get(WrittenBook.STAGE_1_4) != 4) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlockState(pos.south(), southState.with(WrittenBook.STAGE_1_4, Integer.valueOf(southState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;

						case SOUTH:
							/** left **/
							if (westState.getMaterial().isReplaceable()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.west(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.west(), Direction.DOWN))
										.with(WrittenBook.WATERLOGGED, Boolean.valueOf(westFluid.getFluid() == Fluids.WATER)), 3); }
							
							else { //!west_Replaceable
								if (westBlock == Unit_Blocks.WRITTEN_BOOK && westState.get(WrittenBook.STAGE_1_4) != 4) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.west(), westState.with(WrittenBook.STAGE_1_4, Integer.valueOf(westState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
								
								/** right **/
								if (westBlock != Unit_Blocks.WRITTEN_BOOK ||
										(westBlock == Unit_Blocks.WRITTEN_BOOK && westState.get(WrittenBook.STAGE_1_4) == 4)) {
									if (eastState.getMaterial().isReplaceable()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.east(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.east(), Direction.DOWN))
												.with(WrittenBook.WATERLOGGED, Boolean.valueOf(eastFluid.getFluid() == Fluids.WATER)), 3); }
									
									else { //!east_Replaceable
										if (eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.get(WrittenBook.STAGE_1_4) != 4) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.east(), eastState.with(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
										
										/** front **/
										if (eastBlock != Unit_Blocks.WRITTEN_BOOK ||
												(eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.get(WrittenBook.STAGE_1_4) == 4)) {
											if (northState.getMaterial().isReplaceable()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.north(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.north(), Direction.DOWN))
														.with(WrittenBook.WATERLOGGED, Boolean.valueOf(northFluid.getFluid() == Fluids.WATER)), 3); }
											
											else { //!north_Replaceable
												if (northBlock == Unit_Blocks.WRITTEN_BOOK && northState.get(WrittenBook.STAGE_1_4) != 4) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlockState(pos.north(), northState.with(WrittenBook.STAGE_1_4, Integer.valueOf(northState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;

						case EAST:
							/** left **/
							if (southState.getMaterial().isReplaceable()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.south(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.south(), Direction.DOWN))
										.with(WrittenBook.WATERLOGGED, Boolean.valueOf(southFluid.getFluid() == Fluids.WATER)), 3); }
							
							else { //!south_Replaceable
								if (southBlock == Unit_Blocks.WRITTEN_BOOK && southState.get(WrittenBook.STAGE_1_4) != 4) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.south(), southState.with(WrittenBook.STAGE_1_4, Integer.valueOf(southState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
								
								/** right **/
								if (southBlock != Unit_Blocks.WRITTEN_BOOK ||
										(southBlock == Unit_Blocks.WRITTEN_BOOK && southState.get(WrittenBook.STAGE_1_4) == 4)) {
									if (northState.getMaterial().isReplaceable()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.north(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.north(), Direction.DOWN))
												.with(WrittenBook.WATERLOGGED, Boolean.valueOf(northFluid.getFluid() == Fluids.WATER)), 3); }
									
									else { //!north_Replaceable
										if (northBlock == Unit_Blocks.WRITTEN_BOOK && northState.get(WrittenBook.STAGE_1_4) != 4) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.north(), northState.with(WrittenBook.STAGE_1_4, Integer.valueOf(northState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
										
										/** front **/
										if (northBlock != Unit_Blocks.WRITTEN_BOOK ||
												(northBlock == Unit_Blocks.WRITTEN_BOOK && northState.get(WrittenBook.STAGE_1_4) == 4)) {
											if (westState.getMaterial().isReplaceable()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.west(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.west(), Direction.DOWN))
														.with(WrittenBook.WATERLOGGED, Boolean.valueOf(westFluid.getFluid() == Fluids.WATER)), 3); }
											
											else { //!west_Replaceable
												if (westBlock == Unit_Blocks.WRITTEN_BOOK && westState.get(WrittenBook.STAGE_1_4) != 4) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlockState(pos.west(), westState.with(WrittenBook.STAGE_1_4, Integer.valueOf(westState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;
							
						case WEST:
							/** left **/
							if (northState.getMaterial().isReplaceable()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.north(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.north(), Direction.DOWN))
										.with(WrittenBook.WATERLOGGED, Boolean.valueOf(northFluid.getFluid() == Fluids.WATER)), 3); }
							
							else { //!north_Replaceable
								if (northBlock == Unit_Blocks.WRITTEN_BOOK && northState.get(WrittenBook.STAGE_1_4) != 4) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.north(), northState.with(WrittenBook.STAGE_1_4, Integer.valueOf(northState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
								
								/** right **/
								if (northBlock != Unit_Blocks.WRITTEN_BOOK ||
										(northBlock == Unit_Blocks.WRITTEN_BOOK && northState.get(WrittenBook.STAGE_1_4) == 4)) {
									if (southState.getMaterial().isReplaceable()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.south(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.south(), Direction.DOWN))
												.with(WrittenBook.WATERLOGGED, Boolean.valueOf(southFluid.getFluid() == Fluids.WATER)), 3); }
									
									else { //!south_Replaceable
										if (southBlock == Unit_Blocks.WRITTEN_BOOK && southState.get(WrittenBook.STAGE_1_4) != 4) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.south(), southState.with(WrittenBook.STAGE_1_4, Integer.valueOf(southState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
										
										/** front **/
										if (southBlock != Unit_Blocks.WRITTEN_BOOK ||
												(southBlock == Unit_Blocks.WRITTEN_BOOK && southState.get(WrittenBook.STAGE_1_4) == 4)) {
											if (eastState.getMaterial().isReplaceable()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.east(), takeState.with(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.east(), Direction.DOWN))
														.with(WrittenBook.WATERLOGGED, Boolean.valueOf(eastFluid.getFluid() == Fluids.WATER)), 3); }
											
											else { //!east_Replaceable
												if (eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.get(WrittenBook.STAGE_1_4) != 4) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlockState(pos.east(), eastState.with(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.get(WrittenBook.STAGE_1_4) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;
						} // switch direction
					} //!useMakimono
				} // totalExperience >= 50
				
				/** Not enough EXP **/
				if (playerIn.experienceTotal < 50) { CMEvents.textNotEnough_EXP(worldIn, pos, playerIn); }
			} // hItem == Items.BOOK
			
			if (hItem != Items_Chinjufu.SHOUHOU_empty && hItem != Items.BOOK) { 
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		} // !LOST
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
}
