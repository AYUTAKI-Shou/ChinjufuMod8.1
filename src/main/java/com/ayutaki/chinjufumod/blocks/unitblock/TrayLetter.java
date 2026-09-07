package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.Config_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class TrayLetter extends BaseTray {
	
	public TrayLetter(BlockBehaviour.Properties props) {
		super(props);
	}

	/* Write Book */
	private void writebook(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consume1_seWrite(worldIn, pos, playerIn, hand);
		playerIn.giveExperiencePoints(-10); //戦闘詳報1冊=10連
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		Direction direction = state.getValue(H_FACING);
		
		if (state.getValue(LOST)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { // !LOST
			/* Battle report */
			if (hItem == Items_Chinjufu.SHOUHOU_empty.get()) {
				
				if (playerIn.totalExperience >= 100) {
					CMEvents.consume1_seWrite(worldIn, pos, playerIn, hand);
					playerIn.giveExperiencePoints(-100);
					CMEvents.take1Item(playerIn, hand, Items_Chinjufu.SHOUHOU.get()); }
				
				/** Not enough EXP **/
				if (playerIn.totalExperience < 100) { CMEvents.textNotEnough_EXP(worldIn, pos, playerIn); }
			} // hItem == Items_Chinjufu.SHOUHOU_empty
			
			if (hStack.is(ItemTags.LECTERN_BOOKS)) {
				worldIn.playSound(null, pos, SoundEvents_CM.WRITE_REPORT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
				return InteractionResult.PASS; }
			
			/* Enchantbook 5 times */
			if (hItem == Items.BOOK) {
				
				if (playerIn.totalExperience >= 50) {
					BlockState northState = worldIn.getBlockState(pos.north());
					BlockState southState = worldIn.getBlockState(pos.south());
					BlockState eastState = worldIn.getBlockState(pos.east());
					BlockState westState = worldIn.getBlockState(pos.west());
					Block northBlock = northState.getBlock();
					Block southBlock = southState.getBlock();
					Block eastBlock = eastState.getBlock();
					Block westBlock = westState.getBlock();
					
					FluidState northFluid = worldIn.getFluidState(pos.north());
					FluidState southFluid = worldIn.getFluidState(pos.south());
					FluidState eastFluid = worldIn.getFluidState(pos.east());
					FluidState westFluid = worldIn.getFluidState(pos.west());
					
					boolean useMakimono = (this == Unit_Blocks.FUDETRAY.get() && Config_CM.INSTANCE.useMAKIMONO.get());

					if (useMakimono) {
						BlockState makiMono = Unit_Blocks.WRITTEN_MAKIMONO.get().defaultBlockState().setValue(WrittenMakimono.H_FACING, direction);

						switch (direction) {
						case NORTH:
						default:
							/** left **/
							if (eastState.canBeReplaced()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlock(pos.east(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.east(), Direction.DOWN))
										.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(eastFluid.getType() == Fluids.WATER)), 3); }
							
							else { //!Replaceable
								if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && eastState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlock(pos.east(), eastState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(eastState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
								
								/** right **/
								if (eastBlock != Unit_Blocks.WRITTEN_MAKIMONO.get() ||
										(eastBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && eastState.getValue(WrittenMakimono.STAGE_1_5) == 5)) {
									if (westState.canBeReplaced()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlock(pos.west(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.west(), Direction.DOWN))
												.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(westFluid.getType() == Fluids.WATER)), 3); }

									
									else { //!Replaceable
										if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && westState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlock(pos.west(), westState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(westState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
										
										/** front **/
										if (westBlock != Unit_Blocks.WRITTEN_MAKIMONO.get() ||
												(westBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && westState.getValue(WrittenMakimono.STAGE_1_5) == 5)) {
											if (southState.canBeReplaced()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlock(pos.south(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.south(), Direction.DOWN))
														.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(southFluid.getType() == Fluids.WATER)), 3); }
											
											else { //!Replaceable
												if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && southState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlock(pos.south(), southState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(southState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;

						case SOUTH:
							/** left **/
							if (westState.canBeReplaced()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlock(pos.west(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.west(), Direction.DOWN))
										.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(westFluid.getType() == Fluids.WATER)), 3); }
							
							else { //!Replaceable
								if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && westState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlock(pos.west(), westState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(westState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
								
								/** right **/
								if (westBlock != Unit_Blocks.WRITTEN_MAKIMONO.get() ||
										(westBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && westState.getValue(WrittenMakimono.STAGE_1_5) == 5)) {
									if (eastState.canBeReplaced()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlock(pos.east(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.east(), Direction.DOWN))
												.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(eastFluid.getType() == Fluids.WATER)), 3); }
									
									else { //!Replaceable
										if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && eastState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlock(pos.east(), eastState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(eastState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
										
										/** front **/
										if (eastBlock != Unit_Blocks.WRITTEN_MAKIMONO.get() ||
												(eastBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && eastState.getValue(WrittenMakimono.STAGE_1_5) == 5)) {
											if (northState.canBeReplaced()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlock(pos.north(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.north(), Direction.DOWN))
														.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(northFluid.getType() == Fluids.WATER)), 3); }
											
											else { //!Replaceable
												if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && northState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlock(pos.north(), northState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(northState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;

						case EAST:
							/** left **/
							if (southState.canBeReplaced()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlock(pos.south(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.south(), Direction.DOWN))
										.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(southFluid.getType() == Fluids.WATER)), 3); }
							
							else { //!Replaceable
								if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && southState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlock(pos.south(), southState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(southState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
								
								/** right **/
								if (southBlock != Unit_Blocks.WRITTEN_MAKIMONO.get() ||
										(southBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && southState.getValue(WrittenMakimono.STAGE_1_5) == 5)) {
									if (northState.canBeReplaced()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlock(pos.north(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.north(), Direction.DOWN))
												.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(northFluid.getType() == Fluids.WATER)), 3); }
									
									else { //!Replaceable
										if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && northState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlock(pos.north(), northState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(northState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
										
										/** front **/
										if (northBlock != Unit_Blocks.WRITTEN_MAKIMONO.get() ||
												(northBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && northState.getValue(WrittenMakimono.STAGE_1_5) == 5)) {
											if (westState.canBeReplaced()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlock(pos.west(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.west(), Direction.DOWN))
														.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(westFluid.getType() == Fluids.WATER)), 3); }
											
											else { //!Replaceable
												if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && westState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlock(pos.west(), westState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(westState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;
							
						case WEST:
							/** left **/
							if (northState.canBeReplaced()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlock(pos.north(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.north(), Direction.DOWN))
										.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(northFluid.getType() == Fluids.WATER)), 3); }
							
							else { //!Replaceable
								if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && northState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlock(pos.north(), northState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(northState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
								
								/** right **/
								if (northBlock != Unit_Blocks.WRITTEN_MAKIMONO.get() ||
										(northBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && northState.getValue(WrittenMakimono.STAGE_1_5) == 5)) {
									if (southState.canBeReplaced()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlock(pos.south(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.south(), Direction.DOWN))
												.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(southFluid.getType() == Fluids.WATER)), 3); }
									
									else { //!Replaceable
										if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && southState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlock(pos.south(), southState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(southState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
										
										/** front **/
										if (southBlock != Unit_Blocks.WRITTEN_MAKIMONO.get() ||
												(southBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && southState.getValue(WrittenMakimono.STAGE_1_5) == 5)) {
											if (eastState.canBeReplaced()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlock(pos.east(), makiMono.setValue(WrittenMakimono.DOWN, WrittenMakimono.connectHalf(worldIn, pos.east(), Direction.DOWN))
														.setValue(WrittenMakimono.WATERLOGGED, Boolean.valueOf(eastFluid.getType() == Fluids.WATER)), 3); }
											
											else { //!Replaceable
												if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO.get() && eastState.getValue(WrittenMakimono.STAGE_1_5) != 5) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlock(pos.east(), eastState.setValue(WrittenMakimono.STAGE_1_5, Integer.valueOf(eastState.getValue(WrittenMakimono.STAGE_1_5) + 1)), 3); }
												
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
						BlockState takeState = Unit_Blocks.WRITTEN_BOOK.get().defaultBlockState().setValue(WrittenBook.H_FACING, direction);

						switch (direction) {
						case NORTH:
						default:
							/** left **/
							if (eastState.canBeReplaced()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlock(pos.east(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.east(), Direction.DOWN))
										.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(eastFluid.getType() == Fluids.WATER)), 3); }
							
							else { //!Replaceable
								if (eastBlock == Unit_Blocks.WRITTEN_BOOK.get() && eastState.getValue(WrittenBook.STAGE_1_4) != 4) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlock(pos.east(), eastState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
								
								/** right **/
								if (eastBlock != Unit_Blocks.WRITTEN_BOOK.get() ||
										(eastBlock == Unit_Blocks.WRITTEN_BOOK.get() && eastState.getValue(WrittenBook.STAGE_1_4) == 4)) {
									if (westState.canBeReplaced()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlock(pos.west(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.west(), Direction.DOWN))
												.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(westFluid.getType() == Fluids.WATER)), 3); }
									
									else { //!Replaceable
										if (westBlock == Unit_Blocks.WRITTEN_BOOK.get() && westState.getValue(WrittenBook.STAGE_1_4) != 4) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlock(pos.west(), westState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
										
										/** front **/
										if (westBlock != Unit_Blocks.WRITTEN_BOOK.get() ||
												(westBlock == Unit_Blocks.WRITTEN_BOOK.get() && westState.getValue(WrittenBook.STAGE_1_4) == 4)) {
											if (southState.canBeReplaced()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlock(pos.south(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.south(), Direction.DOWN))
														.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(southFluid.getType() == Fluids.WATER)), 3); }
											
											else { //!Replaceable
												if (southBlock == Unit_Blocks.WRITTEN_BOOK.get() && southState.getValue(WrittenBook.STAGE_1_4) != 4) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlock(pos.south(), southState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;

						case SOUTH:
							/** left **/
							if (westState.canBeReplaced()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlock(pos.west(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.west(), Direction.DOWN))
										.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(westFluid.getType() == Fluids.WATER)), 3); }
							
							else { //!Replaceable
								if (westBlock == Unit_Blocks.WRITTEN_BOOK.get() && westState.getValue(WrittenBook.STAGE_1_4) != 4) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlock(pos.west(), westState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
								
								/** right **/
								if (westBlock != Unit_Blocks.WRITTEN_BOOK.get() ||
										(westBlock == Unit_Blocks.WRITTEN_BOOK.get() && westState.getValue(WrittenBook.STAGE_1_4) == 4)) {
									if (eastState.canBeReplaced()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlock(pos.east(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.east(), Direction.DOWN))
												.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(eastFluid.getType() == Fluids.WATER)), 3); }
									
									else { //!Replaceable
										if (eastBlock == Unit_Blocks.WRITTEN_BOOK.get() && eastState.getValue(WrittenBook.STAGE_1_4) != 4) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlock(pos.east(), eastState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
										
										/** front **/
										if (eastBlock != Unit_Blocks.WRITTEN_BOOK.get() ||
												(eastBlock == Unit_Blocks.WRITTEN_BOOK.get() && eastState.getValue(WrittenBook.STAGE_1_4) == 4)) {
											if (northState.canBeReplaced()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlock(pos.north(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.north(), Direction.DOWN))
														.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(northFluid.getType() == Fluids.WATER)), 3); }
											
											else { //!Replaceable
												if (northBlock == Unit_Blocks.WRITTEN_BOOK.get() && northState.getValue(WrittenBook.STAGE_1_4) != 4) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlock(pos.north(), northState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;

						case EAST:
							/** left **/
							if (southState.canBeReplaced()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlock(pos.south(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.south(), Direction.DOWN))
										.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(southFluid.getType() == Fluids.WATER)), 3); }
							
							else { //!Replaceable
								if (southBlock == Unit_Blocks.WRITTEN_BOOK.get() && southState.getValue(WrittenBook.STAGE_1_4) != 4) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlock(pos.south(), southState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
								
								/** right **/
								if (southBlock != Unit_Blocks.WRITTEN_BOOK.get() ||
										(southBlock == Unit_Blocks.WRITTEN_BOOK.get() && southState.getValue(WrittenBook.STAGE_1_4) == 4)) {
									if (northState.canBeReplaced()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlock(pos.north(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.north(), Direction.DOWN))
												.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(northFluid.getType() == Fluids.WATER)), 3); }
									
									else { //!Replaceable
										if (northBlock == Unit_Blocks.WRITTEN_BOOK.get() && northState.getValue(WrittenBook.STAGE_1_4) != 4) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlock(pos.north(), northState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
										
										/** front **/
										if (northBlock != Unit_Blocks.WRITTEN_BOOK.get() ||
												(northBlock == Unit_Blocks.WRITTEN_BOOK.get() && northState.getValue(WrittenBook.STAGE_1_4) == 4)) {
											if (westState.canBeReplaced()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlock(pos.west(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.west(), Direction.DOWN))
														.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(westFluid.getType() == Fluids.WATER)), 3); }
											
											else { //!Replaceable
												if (westBlock == Unit_Blocks.WRITTEN_BOOK.get() && westState.getValue(WrittenBook.STAGE_1_4) != 4) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlock(pos.west(), westState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
												
												else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
											}
										}
									}
								}
							}
							break;
							
						case WEST:
							/** left **/
							if (northState.canBeReplaced()) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlock(pos.north(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.north(), Direction.DOWN))
										.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(northFluid.getType() == Fluids.WATER)), 3); }
							
							else { //!Replaceable
								if (northBlock == Unit_Blocks.WRITTEN_BOOK.get() && northState.getValue(WrittenBook.STAGE_1_4) != 4) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlock(pos.north(), northState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
								
								/** right **/
								if (northBlock != Unit_Blocks.WRITTEN_BOOK.get() ||
										(northBlock == Unit_Blocks.WRITTEN_BOOK.get() && northState.getValue(WrittenBook.STAGE_1_4) == 4)) {
									if (southState.canBeReplaced()) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlock(pos.south(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.south(), Direction.DOWN))
												.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(southFluid.getType() == Fluids.WATER)), 3); }
									
									else { //!Replaceable
										if (southBlock == Unit_Blocks.WRITTEN_BOOK.get() && southState.getValue(WrittenBook.STAGE_1_4) != 4) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlock(pos.south(), southState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
										
										/** front **/
										if (southBlock != Unit_Blocks.WRITTEN_BOOK.get() ||
												(southBlock == Unit_Blocks.WRITTEN_BOOK.get() && southState.getValue(WrittenBook.STAGE_1_4) == 4)) {
											if (eastState.canBeReplaced()) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlock(pos.east(), takeState.setValue(WrittenBook.DOWN, WrittenBook.connectHalf(worldIn, pos.east(), Direction.DOWN))
														.setValue(WrittenBook.WATERLOGGED, Boolean.valueOf(eastFluid.getType() == Fluids.WATER)), 3); }
											
											else { //!Replaceable
												if (eastBlock == Unit_Blocks.WRITTEN_BOOK.get() && eastState.getValue(WrittenBook.STAGE_1_4) != 4) {
													this.writebook(worldIn, pos, playerIn, hand);
													worldIn.setBlock(pos.east(), eastState.setValue(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
												
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
				if (playerIn.totalExperience < 50) { CMEvents.textNotEnough_EXP(worldIn, pos, playerIn); }
			} // hItem == Items.BOOK
			
			if (hItem != Items_Chinjufu.SHOUHOU_empty.get() && hItem != Items.BOOK && !hStack.is(ItemTags.LECTERN_BOOKS)) { 
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		} //getValue(LOST) != true
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
}
