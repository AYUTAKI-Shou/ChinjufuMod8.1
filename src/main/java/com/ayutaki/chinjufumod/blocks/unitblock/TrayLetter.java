package com.ayutaki.chinjufumod.blocks.unitblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.Config_CM;
import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TrayLetter extends Base_ConnectHalf {
	/* Property */
	public static final PropertyInteger STAGE_1_2 = PropertyInteger.create("stage", 1, 2);
	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.03125D, 1.0D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, 0.01D, 1.0D);

	public TrayLetter(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(STAGE_1_2, Integer.valueOf(1))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}
	
	/* Write Book playerIn.addExperience(-10); */
	private void writebook(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.consume1_seWrite(worldIn, pos, playerIn, hand);
		if (playerIn != null) { this.giveExperiencePoints(playerIn, -10); } //戦闘詳報1冊=10連
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		EnumFacing direction = state.getValue(H_FACING);
		
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		
		/* Battle report playerIn.addExperience(-100); */
		if (hItem == Items_Chinjufu.SHOUHOU_empty) {
			if (playerIn.experienceTotal >= 100) {
				CMEvents.consume1_seWrite(worldIn, pos, playerIn, hand);
				if (playerIn != null) { this.giveExperiencePoints(playerIn, -100); }
				CMEvents.take1Item(playerIn, hand, Items_Chinjufu.SHOUHOU, 0); }
			
			/** Not enough EXP **/
			if (playerIn.experienceTotal < 100) { CMEvents.textNotEnough_EXP(worldIn, pos, playerIn); }
		} // hItem == Items_Chinjufu.SHOUHOU_empty
		
		if (hItem == Items.WRITABLE_BOOK || hItem == Items.WRITTEN_BOOK) {
			worldIn.playSound(null, pos, SoundEvents_CM.WRITE_REPORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
			return false;
		}
		
		/* Enchantbook */
		if (hItem == Items.BOOK) {
			
			IBlockState northState = worldIn.getBlockState(pos.north());
			IBlockState southState = worldIn.getBlockState(pos.south());
			IBlockState eastState = worldIn.getBlockState(pos.east());
			IBlockState westState = worldIn.getBlockState(pos.west());
			Block northBlock = northState.getBlock();
			Block southBlock = southState.getBlock();
			Block eastBlock = eastState.getBlock();
			Block westBlock = westState.getBlock();
			
			if (playerIn.experienceTotal >= 50) {
				boolean useMakimono = (i == 2 && Config_CM.useMAKIMONO);
				
				if (useMakimono) {
					IBlockState makiMono = Unit_Blocks.WRITTEN_MAKIMONO.getDefaultState().withProperty(WrittenMakimono.H_FACING, direction);
					IBlockState makiMono5 = Unit_Blocks.WRITTEN_MAKIMONO5.getDefaultState().withProperty(WrittenMakimono5.H_FACING, direction);

					switch (direction) {
					case NORTH :
					default:
						/** left **/
						if (eastState.getMaterial().isReplaceable()) {
							this.writebook(worldIn, pos, playerIn, hand);
							worldIn.setBlockState(pos.east(), makiMono, 3); }
						
						else { //!east_Replaceable
							if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.east(), eastState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
							
							if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.east(), makiMono5, 3); }
							
							/** right **/
							if (eastBlock != Unit_Blocks.WRITTEN_MAKIMONO) {
								if (westState.getMaterial().isReplaceable()) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.west(), makiMono, 3); }

								else { //!west_Replaceable
									if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.west(), westState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
									
									if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.west(), makiMono5, 3); }
									
									/** front **/
									if (westBlock != Unit_Blocks.WRITTEN_MAKIMONO) {
										if (southState.getMaterial().isReplaceable()) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.south(), makiMono, 3); }
										
										else { //!south_Replaceable
											if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.south(), southState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
											
											if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.south(), makiMono5, 3); }
											
											if (southBlock != Unit_Blocks.WRITTEN_MAKIMONO) { CMEvents.textNoPlace(worldIn, pos, playerIn); } }
									}
								}
							}
						}
						break;
					
					case SOUTH :
						/** left **/
						if (westState.getMaterial().isReplaceable()) {
							this.writebook(worldIn, pos, playerIn, hand);
							worldIn.setBlockState(pos.west(), makiMono, 3); }
						
						else { //!west_Replaceable
							if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.west(), westState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
							
							if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.west(), makiMono5, 3); }
							
							/** right **/
							if (westBlock != Unit_Blocks.WRITTEN_MAKIMONO) {
								if (eastState.getMaterial().isReplaceable()) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.east(), makiMono, 3); }
								
								else { //!east_Replaceable
									if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.east(), eastState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
									
									if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.east(), makiMono5, 3); }
									
									/** front **/
									if (eastBlock != Unit_Blocks.WRITTEN_MAKIMONO) {
										if (northState.getMaterial().isReplaceable()) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.north(), makiMono, 3); }
										
										else { //!north_Replaceable
											if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.north(), northState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
											
											if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.north(), makiMono5, 3); }
											
											if (northBlock != Unit_Blocks.WRITTEN_MAKIMONO) { CMEvents.textNoPlace(worldIn, pos, playerIn); } }
									} /** right **/
								}
							} /** front **/
						} /** left **/
						break;
					
					case EAST :
						/** left **/
						if (southState.getMaterial().isReplaceable()) {
							this.writebook(worldIn, pos, playerIn, hand);
							worldIn.setBlockState(pos.south(), makiMono, 3); }
						
						else { //!south_Replaceable
							if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.south(), southState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
							
							if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.south(), makiMono5, 3); }
							
							/** right **/
							if (southBlock != Unit_Blocks.WRITTEN_MAKIMONO) {
								if (northState.getMaterial().isReplaceable()) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.north(), makiMono, 3); }
								
								else { //!north_Replaceable
									if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.north(), northState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
									
									if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.north(), makiMono5, 3); }
									
									/** front **/
									if (northBlock != Unit_Blocks.WRITTEN_MAKIMONO) {
										if (westState.getMaterial().isReplaceable()) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.west(), makiMono, 3); }
										
										else { //!west_Replaceable
											if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.west(), westState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
											
											if (westBlock == Unit_Blocks.WRITTEN_MAKIMONO && westState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.west(), makiMono5, 3); }
											
											if (westBlock != Unit_Blocks.WRITTEN_MAKIMONO) { CMEvents.textNoPlace(worldIn, pos, playerIn); } }
									}
								}
							}
						}
						break;
						
					case WEST :
						/** left **/
						if (northState.getMaterial().isReplaceable()) {
							this.writebook(worldIn, pos, playerIn, hand);
							worldIn.setBlockState(pos.north(), makiMono, 3); }
						
						else { //!north_Replaceable
							if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.north(), northState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
							
							if (northBlock == Unit_Blocks.WRITTEN_MAKIMONO && northState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.north(), makiMono5, 3); }
							
							/** right **/
							if (northBlock != Unit_Blocks.WRITTEN_MAKIMONO) {
								if (southState.getMaterial().isReplaceable()) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.south(), makiMono, 3); }
								
								else { //!south_Replaceable
									if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.south(), southState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
									
									if (southBlock == Unit_Blocks.WRITTEN_MAKIMONO && southState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.south(), makiMono5, 3); }
									
									/** front **/
									if (southBlock != Unit_Blocks.WRITTEN_MAKIMONO) {
										if (eastState.getMaterial().isReplaceable()) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.east(), makiMono, 3); }
										
										else { //!east_Replaceable
											if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.getValue(WrittenMakimono.STAGE_1_4) != 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.east(), eastState.withProperty(WrittenMakimono.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenMakimono.STAGE_1_4) + 1)), 3); }
											
											if (eastBlock == Unit_Blocks.WRITTEN_MAKIMONO && eastState.getValue(WrittenMakimono.STAGE_1_4) == 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.east(), makiMono5, 3); }
											
											if (eastBlock != Unit_Blocks.WRITTEN_MAKIMONO) { CMEvents.textNoPlace(worldIn, pos, playerIn); } }
									}
								}
							}
						}
						break;
					} // direction
				} //useMakimono
				
				else {
					IBlockState takeState = Unit_Blocks.WRITTEN_BOOK.getDefaultState().withProperty(WrittenBook.H_FACING, direction);

					switch (direction) {
					case NORTH :
					default:
						/** left **/
						if (eastState.getMaterial().isReplaceable()) {
							this.writebook(worldIn, pos, playerIn, hand);
							worldIn.setBlockState(pos.east(), takeState, 3); }
						
						else { //!east_Replaceable
							if (eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.getValue(WrittenBook.STAGE_1_4) != 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.east(), eastState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
							
							/** right **/
							if (eastBlock != Unit_Blocks.WRITTEN_BOOK ||
									(eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.getValue(WrittenBook.STAGE_1_4) == 4)) {
								if (westState.getMaterial().isReplaceable()) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.west(), takeState, 3); }
								
								else { //!west_Replaceable
									if (westBlock == Unit_Blocks.WRITTEN_BOOK && westState.getValue(WrittenBook.STAGE_1_4) != 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.west(), westState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
									
									/** front **/
									if (westBlock != Unit_Blocks.WRITTEN_BOOK ||
											(westBlock == Unit_Blocks.WRITTEN_BOOK && westState.getValue(WrittenBook.STAGE_1_4) == 4)) {
										if (southState.getMaterial().isReplaceable()) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.south(), takeState, 3); }
										
										else { //!south_Replaceable
											if (southBlock == Unit_Blocks.WRITTEN_BOOK && southState.getValue(WrittenBook.STAGE_1_4) != 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.south(), southState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }

											else { CMEvents.textNoPlace(worldIn, pos, playerIn); } }
									}
								}
							}
						}
						break;
					
					case SOUTH :
						/** left **/
						if (westState.getMaterial().isReplaceable()) {
							this.writebook(worldIn, pos, playerIn, hand);
							worldIn.setBlockState(pos.west(), takeState, 3); }
						
						else { //!west_Replaceable
							if (westBlock == Unit_Blocks.WRITTEN_BOOK && westState.getValue(WrittenBook.STAGE_1_4) != 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.west(), westState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
							
							/** right **/
							if (westBlock != Unit_Blocks.WRITTEN_BOOK ||
									(westBlock == Unit_Blocks.WRITTEN_BOOK && westState.getValue(WrittenBook.STAGE_1_4) == 4)) {
								if (eastState.getMaterial().isReplaceable()) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.east(), takeState, 3); }
								
								else { //!east_Replaceable
									if (eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.getValue(WrittenBook.STAGE_1_4) != 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.east(), eastState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
									
									/** front **/
									if (eastBlock != Unit_Blocks.WRITTEN_BOOK ||
											(eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.getValue(WrittenBook.STAGE_1_4) == 4)) {
										if (northState.getMaterial().isReplaceable()) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.north(), takeState, 3); }
										
										else { //!north_Replaceable
											if (northBlock == Unit_Blocks.WRITTEN_BOOK && northState.getValue(WrittenBook.STAGE_1_4) != 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.north(), northState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
											
											else { CMEvents.textNoPlace(worldIn, pos, playerIn); } }
									} /** right **/
								}
							} /** front **/
						} /** left **/
						break;
					
					case EAST :
						/** left **/
						if (southState.getMaterial().isReplaceable()) {
							this.writebook(worldIn, pos, playerIn, hand);
							worldIn.setBlockState(pos.south(), takeState, 3); }
						
						else { //!south_Replaceable
							if (southBlock == Unit_Blocks.WRITTEN_BOOK && southState.getValue(WrittenBook.STAGE_1_4) != 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.south(), southState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
							
							/** right **/
							if (southBlock != Unit_Blocks.WRITTEN_BOOK ||
									(southBlock == Unit_Blocks.WRITTEN_BOOK && southState.getValue(WrittenBook.STAGE_1_4) == 4)) {
								if (northState.getMaterial().isReplaceable()) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.north(), takeState, 3); }
								
								else { //!north_Replaceable
									if (northBlock == Unit_Blocks.WRITTEN_BOOK && northState.getValue(WrittenBook.STAGE_1_4) != 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.north(), northState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
									
									/** front **/
									if (northBlock != Unit_Blocks.WRITTEN_BOOK ||
											(northBlock == Unit_Blocks.WRITTEN_BOOK && northState.getValue(WrittenBook.STAGE_1_4) == 4)) {
										if (westState.getMaterial().isReplaceable()) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.west(), takeState, 3); }
										
										else { //!west_Replaceable
											if (westBlock == Unit_Blocks.WRITTEN_BOOK && westState.getValue(WrittenBook.STAGE_1_4) != 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.west(), westState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(westState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
											
											else { CMEvents.textNoPlace(worldIn, pos, playerIn); } }
									}
								}
							}
						}
						break;
						
					case WEST :
						/** left **/
						if (northState.getMaterial().isReplaceable()) {
							this.writebook(worldIn, pos, playerIn, hand);
							worldIn.setBlockState(pos.north(), takeState, 3); }
						
						else { //!north_Replaceable
							if (northBlock == Unit_Blocks.WRITTEN_BOOK && northState.getValue(WrittenBook.STAGE_1_4) != 4) {
								this.writebook(worldIn, pos, playerIn, hand);
								worldIn.setBlockState(pos.north(), northState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(northState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
							
							/** right **/
							if (northBlock != Unit_Blocks.WRITTEN_BOOK ||
									(northBlock == Unit_Blocks.WRITTEN_BOOK && northState.getValue(WrittenBook.STAGE_1_4) == 4)) {
								if (southState.getMaterial().isReplaceable()) {
									this.writebook(worldIn, pos, playerIn, hand);
									worldIn.setBlockState(pos.south(), takeState, 3); }
								
								else { //!south_Replaceable
									if (southBlock == Unit_Blocks.WRITTEN_BOOK && southState.getValue(WrittenBook.STAGE_1_4) != 4) {
										this.writebook(worldIn, pos, playerIn, hand);
										worldIn.setBlockState(pos.south(), southState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(southState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
									
									/** front **/
									if (southBlock != Unit_Blocks.WRITTEN_BOOK ||
											(southBlock == Unit_Blocks.WRITTEN_BOOK && southState.getValue(WrittenBook.STAGE_1_4) == 4)) {
										if (eastState.getMaterial().isReplaceable()) {
											this.writebook(worldIn, pos, playerIn, hand);
											worldIn.setBlockState(pos.east(), takeState, 3); }
										
										else { //!east_Replaceable
											if (eastBlock == Unit_Blocks.WRITTEN_BOOK && eastState.getValue(WrittenBook.STAGE_1_4) != 4) {
												this.writebook(worldIn, pos, playerIn, hand);
												worldIn.setBlockState(pos.east(), eastState.withProperty(WrittenBook.STAGE_1_4, Integer.valueOf(eastState.getValue(WrittenBook.STAGE_1_4) + 1)), 3); }
											
											else { CMEvents.textNoPlace(worldIn, pos, playerIn); } }
									}
								}
							}
						}
						break;
					} // direction
				} //!useMakimono

			} //playerIn.experienceTotal >= 50
			
			/** Not enough EXP **/
			if (playerIn.experienceTotal < 50) { CMEvents.textNotEnough_EXP(worldIn, pos, playerIn); }
		} // hItem == Items.BOOK
		
		if (hItem != Items_Chinjufu.SHOUHOU_empty && hItem != Items.BOOK && hItem != Items.WRITABLE_BOOK && hItem != Items.WRITTEN_BOOK) { 
			CMEvents.textNotHave(worldIn, pos, playerIn); }

		return true;
	}

	/** from 1.16.5 PlayerEntity **/
	public void giveExperiencePoints(EntityPlayer playerIn, int exp) {
		playerIn.addScore(exp);
		playerIn.experience += (float)exp / (float)playerIn.xpBarCap();
		playerIn.experienceTotal = MathHelper.clamp(playerIn.experienceTotal + exp, 0, Integer.MAX_VALUE);
		
		while(playerIn.experience < 0.0F) {
			float f = playerIn.experience * (float)playerIn.xpBarCap();
			if (playerIn.experienceLevel > 0) {
				playerIn.addExperienceLevel(-1);
				playerIn.experience = 1.0F + f / (float)playerIn.xpBarCap(); } 
			else {
				playerIn.addExperienceLevel(-1);
				playerIn.experience = 0.0F; }
		}

		while(playerIn.experience >= 1.0F) {
				playerIn.experience = (playerIn.experience - 1.0F) * (float)playerIn.xpBarCap();
				playerIn.addExperienceLevel(1);
				playerIn.experience /= (float)playerIn.xpBarCap(); }
	}

	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, this.connectHalf(worldIn, pos.down()));
	}

	/* BlockState when it was placed. */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing)state.getValue(H_FACING)).getHorizontalIndex();
		i = i | ((Integer)state.getValue(STAGE_1_2)).intValue() - 1 << 2;
		return i;
	}

	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(H_FACING, rot.rotate((EnumFacing)state.getValue(H_FACING)));
	}

	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta))
				.withProperty(STAGE_1_2, Integer.valueOf(1 + (meta >> 2)));
	}

	/*Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { H_FACING, DOWN, STAGE_1_2 });
	}

	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		return flag? AABB : AABB_DOWN;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		stack.add(new ItemStack(Items_Chinjufu.LETTERTRAY, 1, i));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		return new ItemStack(Items_Chinjufu.LETTERTRAY, 1, i);
	}
}
