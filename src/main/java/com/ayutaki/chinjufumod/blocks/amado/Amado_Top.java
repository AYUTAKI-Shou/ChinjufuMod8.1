package com.ayutaki.chinjufumod.blocks.amado;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Amado_Top extends BaseStage4_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, -0.03125, 0.0, 0.0, 0.03125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, -0.03125, 0.0, 0.0, 0.03125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, -0.03125, 0.0, 0.0, 0.03125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, -0.03125, 0.0, 0.0, 0.03125, 1.0, 1.0);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public Amado_Top(String name) {
		super(name);
		setHardness(2.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		EnumFacing direction = state.getValue(H_FACING);
		EnumFacing playerFacing = playerIn.getHorizontalFacing();

		IBlockState northState = worldIn.getBlockState(pos.north());
		IBlockState southState = worldIn.getBlockState(pos.south());
		IBlockState eastState = worldIn.getBlockState(pos.east());
		IBlockState westState = worldIn.getBlockState(pos.west());

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();

		Block northBlock = northState.getBlock();
		Block southBlock = southState.getBlock();
		Block eastBlock = eastState.getBlock();
		Block westBlock = westState.getBlock();

		
		Block tobuBotL2 = this.takeLeftBot2();
		Block tobuBotL = this.takeLeftBot();
		Block tobuBotR2 = this.takeRightBot2();
		Block tobuBotR = this.takeRightBot();

		Block tobuTopL2 = this.takeLeftTop2();
		Block tobuTopL = this.takeLeftTop();
		Block tobuTopR2 = this.takeRightTop2();
		Block tobuTopR = this.takeRightTop();

		IBlockState tobuBotL2_FACE = tobuBotL2.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuBotL_FACE = tobuBotL.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuBotR2_FACE = tobuBotR2.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuBotR_FACE = tobuBotR.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));

		IBlockState tobuTopL2_FACE = tobuTopL2.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuTopL_FACE = tobuTopL.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuTopR2_FACE = tobuTopR2.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuTopR_FACE = tobuTopR.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));

		
		Block amadoBot = this.takeBot();
		IBlockState amadoBot_FACE = amadoBot.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState this_FACE = this.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		
		CMEvents.soundAmado(worldIn, pos);
		
		/* Stored in TOBUKURO. */
		if (i == 4) {
			switch (direction) {
			case NORTH :
			default:
				/** RIGHT side is TOBUKURO. **/
				if (westBlock == tobuTopL && westState.getValue(BaseStage3_Face.STAGE_1_3) > 1) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.NORTH && hitX < 0.45D) || (playerFacing == EnumFacing.SOUTH && hitX < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), tobuBotL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(westState.getValue(BaseStage3_Face.STAGE_1_3) - 1)));
						worldIn.setBlockState(new BlockPos(x - 1, y, z), tobuTopL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(westState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); } }
				
				/** RIGHT side is TOBUKURO.2 **/
				else if (westBlock == tobuTopL2) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.NORTH && hitX < 0.45D) || (playerFacing == EnumFacing.SOUTH && hitX < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						if (westState.getValue(BaseStage2_Face.STAGE_1_2) == 1) {
							worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), tobuBotL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)));
							worldIn.setBlockState(new BlockPos(x - 1, y, z), tobuTopL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
						
						if (westState.getValue(BaseStage2_Face.STAGE_1_2) != 1) {
							worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), tobuBotL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
							worldIn.setBlockState(new BlockPos(x - 1, y, z), tobuTopL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } } }
				break;

			case SOUTH :
				/** RIGHT side is TOBUKURO. **/
				if (eastBlock == tobuTopL && eastState.getValue(BaseStage3_Face.STAGE_1_3) > 1) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.NORTH && hitX > 0.55D) || (playerFacing == EnumFacing.SOUTH && hitX > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), tobuBotL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(eastState.getValue(BaseStage3_Face.STAGE_1_3) - 1)));
						worldIn.setBlockState(new BlockPos(x + 1, y, z), tobuTopL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(eastState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); } }
				
				/** RIGHT side is TOBUKURO.2 **/
				else if (eastBlock == tobuTopL2) { 
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.NORTH && hitX > 0.55D) || (playerFacing == EnumFacing.SOUTH && hitX > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						if (eastState.getValue(BaseStage2_Face.STAGE_1_2) == 1) {
							worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), tobuBotL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)));
							worldIn.setBlockState(new BlockPos(x + 1, y, z), tobuTopL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
						
						if (eastState.getValue(BaseStage2_Face.STAGE_1_2) != 1) {
							worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), tobuBotL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
							worldIn.setBlockState(new BlockPos(x + 1, y, z), tobuTopL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } } }
				break;

			case EAST :
				/** RIGHT side is TOBUKURO. **/
				if (northBlock == tobuTopL && northState.getValue(BaseStage3_Face.STAGE_1_3) > 1) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.EAST && hitZ < 0.45D) || (playerFacing == EnumFacing.WEST && hitZ < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), tobuBotL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(northState.getValue(BaseStage3_Face.STAGE_1_3) - 1)));
						worldIn.setBlockState(new BlockPos(x, y, z - 1), tobuTopL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(northState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); } }
				
				/** RIGHT side is TOBUKURO.2 **/
				else if (northBlock == tobuTopL2) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.EAST && hitZ < 0.45D) || (playerFacing == EnumFacing.WEST && hitZ < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						if (northState.getValue(BaseStage2_Face.STAGE_1_2) == 1) {
							worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), tobuBotL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)));
							worldIn.setBlockState(new BlockPos(x, y, z - 1), tobuTopL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
						
						if (northState.getValue(BaseStage2_Face.STAGE_1_2) != 1) {
							worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), tobuBotL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
							worldIn.setBlockState(new BlockPos(x, y, z - 1), tobuTopL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } } }
				break;
				
			case WEST :
				/** RIGHT side is TOBUKURO. **/
				if (southBlock == tobuTopL && southState.getValue(BaseStage3_Face.STAGE_1_3) > 1) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.EAST && hitZ > 0.55D) || (playerFacing == EnumFacing.WEST && hitZ > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), tobuBotL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(southState.getValue(BaseStage3_Face.STAGE_1_3) - 1)));
						worldIn.setBlockState(new BlockPos(x, y, z + 1), tobuTopL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(southState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); } }
				
				/** RIGHT side is TOBUKURO.2 **/
				else if (southBlock == tobuTopL2) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.EAST && hitZ > 0.55D) || (playerFacing == EnumFacing.WEST && hitZ > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						if (southState.getValue(BaseStage2_Face.STAGE_1_2) == 1) {
							worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), tobuBotL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)));
							worldIn.setBlockState(new BlockPos(x, y, z + 1), tobuTopL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
						
						if (southState.getValue(BaseStage2_Face.STAGE_1_2) != 1) {
							worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), tobuBotL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
							worldIn.setBlockState(new BlockPos(x, y, z + 1), tobuTopL2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } } }
				break;
			} // switch
		} //i == 4
		
		
		/* move to Right. */
		if (i < 4) {
			switch (direction) {
			case NORTH :
			default:
				/** RIGHT side is Empty. **/
				if (worldIn.getBlockState(new BlockPos(x - 1, y - 1, z)).getMaterial().isReplaceable() && 
						worldIn.getBlockState(new BlockPos(x - 1, y, z)).getMaterial().isReplaceable()) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.NORTH && hitX < 0.45D) || (playerFacing == EnumFacing.SOUTH && hitX < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), amadoBot_FACE.withProperty(STAGE_1_4, Integer.valueOf(i + 1)));
						worldIn.setBlockState(new BlockPos(x - 1, y, z), this_FACE.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); } }
				break;

			case SOUTH :
				/** RIGHT side is Empty. **/
				if (worldIn.getBlockState(new BlockPos(x + 1, y - 1, z)).getMaterial().isReplaceable() && 
						worldIn.getBlockState(new BlockPos(x + 1, y, z)).getMaterial().isReplaceable()) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.NORTH && hitX > 0.55D) || (playerFacing == EnumFacing.SOUTH && hitX > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);

						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), amadoBot_FACE.withProperty(STAGE_1_4, Integer.valueOf(i + 1)));
						worldIn.setBlockState(new BlockPos(x + 1, y, z), this_FACE.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); } }
				break;

			case EAST :
				/** RIGHT side is Empty. **/
				if (worldIn.getBlockState(new BlockPos(x, y - 1, z - 1)).getMaterial().isReplaceable() && 
						worldIn.getBlockState(new BlockPos(x, y, z - 1)).getMaterial().isReplaceable()) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.EAST && hitZ < 0.45D) || (playerFacing == EnumFacing.WEST && hitZ < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);

						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), amadoBot_FACE.withProperty(STAGE_1_4, Integer.valueOf(i + 1)));
						worldIn.setBlockState(new BlockPos(x, y, z - 1), this_FACE.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); } }
				break;
				
			case WEST :
				/** RIGHT side is Empty. **/
				if (worldIn.getBlockState(new BlockPos(x, y - 1, z + 1)).getMaterial().isReplaceable() && 
						worldIn.getBlockState(new BlockPos(x, y, z + 1)).getMaterial().isReplaceable()) {
					/** hit RIGHT side. **/
					if ((playerFacing == EnumFacing.EAST && hitZ > 0.55D) || (playerFacing == EnumFacing.WEST && hitZ > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);

						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), amadoBot_FACE.withProperty(STAGE_1_4, Integer.valueOf(i + 1)));
						worldIn.setBlockState(new BlockPos(x, y, z + 1), this_FACE.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); } }
				break;
			} // switch
		} //i < 4
		
		
		/* Stored in TOBUKURO. */
		if (i == 1) {
			switch (direction) {
			case NORTH :
			default:
				/** LEFT side is TOBUKURO. **/
				if (eastBlock == tobuTopR && eastState.getValue(BaseStage3_Face.STAGE_1_3) > 1) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.NORTH && hitX > 0.55D) || (playerFacing == EnumFacing.SOUTH && hitX > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), tobuBotR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(eastState.getValue(BaseStage3_Face.STAGE_1_3) - 1)));
						worldIn.setBlockState(new BlockPos(x + 1, y, z), tobuTopR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(eastState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); } }
				
				/** LEFT side is TOBUKURO.2 **/
				else if (eastBlock == tobuTopR2) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.NORTH && hitX > 0.55D) || (playerFacing == EnumFacing.SOUTH && hitX > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						if (eastState.getValue(BaseStage2_Face.STAGE_1_2) == 1) {
							worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), tobuBotR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)));
							worldIn.setBlockState(new BlockPos(x + 1, y, z), tobuTopR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
						
						if (eastState.getValue(BaseStage2_Face.STAGE_1_2) != 1) {
							worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), tobuBotR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
							worldIn.setBlockState(new BlockPos(x + 1, y, z), tobuTopR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } } }
				break;

			case SOUTH :
				/** LEFT side is TOBUKURO. **/
				if (westBlock == tobuTopR && westState.getValue(BaseStage3_Face.STAGE_1_3) > 1) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.NORTH && hitX < 0.45D) || (playerFacing == EnumFacing.SOUTH && hitX < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), tobuBotR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(westState.getValue(BaseStage3_Face.STAGE_1_3) - 1)));
						worldIn.setBlockState(new BlockPos(x - 1, y, z), tobuTopR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(westState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); } }
				
				/** LEFT side is TOBUKURO.2 **/
				else if (westBlock == tobuTopR2) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.NORTH && hitX < 0.45D) || (playerFacing == EnumFacing.SOUTH && hitX < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						if (westState.getValue(BaseStage2_Face.STAGE_1_2) == 1) {
							worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), tobuBotR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)));
							worldIn.setBlockState(new BlockPos(x - 1, y, z), tobuTopR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
						
						if (westState.getValue(BaseStage2_Face.STAGE_1_2) != 1) {
							worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), tobuBotR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
							worldIn.setBlockState(new BlockPos(x - 1, y, z), tobuTopR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } } }
				break;

			case EAST :
				/** LEFT side is TOBUKURO. **/
				if (southBlock == tobuTopR && southState.getValue(BaseStage3_Face.STAGE_1_3) > 1) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.EAST && hitZ > 0.55D) || (playerFacing == EnumFacing.WEST && hitZ > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), tobuBotR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(southState.getValue(BaseStage3_Face.STAGE_1_3) - 1)));
						worldIn.setBlockState(new BlockPos(x, y, z + 1), tobuTopR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(southState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); } }
				
				/** LEFT side is TOBUKURO.2 **/
				if (southBlock == tobuTopR2) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.EAST && hitZ > 0.55D) || (playerFacing == EnumFacing.WEST && hitZ > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						if (southState.getValue(BaseStage2_Face.STAGE_1_2) == 1) {
							worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), tobuBotR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)));
							worldIn.setBlockState(new BlockPos(x, y, z + 1), tobuTopR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
						
						if (southState.getValue(BaseStage2_Face.STAGE_1_2) != 1) {
							worldIn.setBlockState(new BlockPos(x, y, z + 1), tobuBotR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
							worldIn.setBlockState(new BlockPos(x, y, z + 1), tobuTopR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } } }
				break;
				
			case WEST :
				/** LEFT side is TOBUKURO. **/
				if (northBlock == tobuTopR && northState.getValue(BaseStage3_Face.STAGE_1_3) > 1) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.EAST && hitZ < 0.45D) || (playerFacing == EnumFacing.WEST && hitZ < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);

						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), tobuBotR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(northState.getValue(BaseStage3_Face.STAGE_1_3) - 1)));
						worldIn.setBlockState(new BlockPos(x, y, z - 1), tobuTopR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(northState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); } }
				
				/** LEFT side is TOBUKURO.2 **/
				if (northBlock == tobuTopR2) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.EAST && hitZ < 0.45D) || (playerFacing == EnumFacing.WEST && hitZ < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);
						
						if (northState.getValue(BaseStage2_Face.STAGE_1_2) == 1) {
							worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), tobuBotR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)));
							worldIn.setBlockState(new BlockPos(x, y, z - 1), tobuTopR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
						
						if (northState.getValue(BaseStage2_Face.STAGE_1_2) != 1) {
							worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), tobuBotR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
							worldIn.setBlockState(new BlockPos(x, y, z - 1), tobuTopR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1))); } } }
				break;
			} // switch
		} //i == 1
		
		
		/* move to Left. */
		if (i > 1) {
			switch (direction) {
			case NORTH :
			default:
				/** LEFT side is Empty. **/
				if (worldIn.getBlockState(new BlockPos(x + 1, y - 1, z)).getMaterial().isReplaceable() && 
						worldIn.getBlockState(new BlockPos(x + 1, y, z)).getMaterial().isReplaceable()) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.NORTH && hitX > 0.55D) || (playerFacing == EnumFacing.SOUTH && hitX > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);

						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), amadoBot_FACE.withProperty(STAGE_1_4, Integer.valueOf(i - 1)));
						worldIn.setBlockState(new BlockPos(x + 1, y, z), this_FACE.withProperty(STAGE_1_4, Integer.valueOf(i - 1))); } }
				break;

			case SOUTH :
				if (worldIn.getBlockState(new BlockPos(x - 1, y - 1, z)).getMaterial().isReplaceable() && 
						worldIn.getBlockState(new BlockPos(x - 1, y, z)).getMaterial().isReplaceable()) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.NORTH && hitX < 0.45D) || (playerFacing == EnumFacing.SOUTH && hitX < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);

						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), amadoBot_FACE.withProperty(STAGE_1_4, Integer.valueOf(i - 1)));
						worldIn.setBlockState(new BlockPos(x - 1, y, z), this_FACE.withProperty(STAGE_1_4, Integer.valueOf(i - 1))); } }
				break;

			case EAST :
				if (worldIn.getBlockState(new BlockPos(x, y - 1, z + 1)).getMaterial().isReplaceable() && 
						worldIn.getBlockState(new BlockPos(x, y, z + 1)).getMaterial().isReplaceable()) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.EAST && hitZ > 0.55D) || (playerFacing == EnumFacing.WEST && hitZ > 0.55D)) {
						this.putAIR_DOWN(worldIn, pos);

						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), amadoBot_FACE.withProperty(STAGE_1_4, Integer.valueOf(i - 1)));
						worldIn.setBlockState(new BlockPos(x, y, z + 1), this_FACE.withProperty(STAGE_1_4, Integer.valueOf(i - 1))); } }
				break;
				
			case WEST :
				if (worldIn.getBlockState(new BlockPos(x, y - 1, z - 1)).getMaterial().isReplaceable() && 
						worldIn.getBlockState(new BlockPos(x, y, z - 1)).getMaterial().isReplaceable()) {
					/** hit LEFT side **/
					if ((playerFacing == EnumFacing.EAST && hitZ < 0.45D) || (playerFacing == EnumFacing.WEST && hitZ < 0.45D)) {
						this.putAIR_DOWN(worldIn, pos);

						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), amadoBot_FACE.withProperty(STAGE_1_4, Integer.valueOf(i - 1)));
						worldIn.setBlockState(new BlockPos(x, y, z - 1), this_FACE.withProperty(STAGE_1_4, Integer.valueOf(i - 1))); } }
				break;
			} // switch
		} //i > 1
		return true;
	}

	private void putAIR_DOWN(World worldIn, BlockPos pos) {
		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		worldIn.setBlockState(pos.down(), Blocks.AIR.getDefaultState());
	}
	
	private Block takeRightBot() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.TOBUKURO_BOTR; }
		else { return Slidedoor_Blocks.TOBUKUROS_BOTR; }
	}
	
	private Block takeRightBot2() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.TOBUKURO_BOTR2; }
		else { return Slidedoor_Blocks.TOBUKUROS_BOTR2; }
	}
	
	private Block takeLeftBot() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.TOBUKURO_BOTL; }
		else { return Slidedoor_Blocks.TOBUKUROS_BOTL; }
	}
	
	private Block takeLeftBot2() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.TOBUKURO_BOTL2; }
		else { return Slidedoor_Blocks.TOBUKUROS_BOTL2; }
	}
	
	private Block takeRightTop() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.TOBUKURO_TOPR; }
		else { return Slidedoor_Blocks.TOBUKUROS_TOPR; }
	}
	
	private Block takeRightTop2() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.TOBUKURO_TOPR2; }
		else { return Slidedoor_Blocks.TOBUKUROS_TOPR2; }
	}
	
	private Block takeLeftTop() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.TOBUKURO_TOPL; }
		else { return Slidedoor_Blocks.TOBUKUROS_TOPL; }
	}
	
	private Block takeLeftTop2() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.TOBUKURO_TOPL2; }
		else { return Slidedoor_Blocks.TOBUKUROS_TOPL2; }
	}
	
	private Block takeBot() {
		if (this == Slidedoor_Blocks.AMADO_TOP) { return Slidedoor_Blocks.AMADO_BOT; }
		else { return Slidedoor_Blocks.AMADOS_BOT; }
	}
	
	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.down()).getBlock() instanceof Amado_Bot) {
			worldIn.destroyBlock(pos.down(), false); }
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}
}
