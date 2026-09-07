package com.ayutaki.chinjufumod.blocks.amado;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
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

public class AmadoWindow extends BaseStage2_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, -0.03125, 0.0, 0.0, 0.03125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, -0.03125, 0.0, 0.0, 0.03125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, -0.03125, 0.0, 0.0, 0.03125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, -0.03125, 0.0, 0.0, 0.03125, 1.0, 1.0);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public AmadoWindow(String name) {
		super(name);
		setHardness(2.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/** 1=Left, 2=Right **/
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		EnumFacing direction = state.getValue(H_FACING);
		EnumFacing playerFacing = playerIn.getHorizontalFacing();

		IBlockState northState = worldIn.getBlockState(pos.north());
		IBlockState southState = worldIn.getBlockState(pos.south());
		IBlockState eastState = worldIn.getBlockState(pos.east());
		IBlockState westState = worldIn.getBlockState(pos.west());

		Block northBlock = northState.getBlock();
		Block southBlock = southState.getBlock();
		Block eastBlock = eastState.getBlock();
		Block westBlock = westState.getBlock();

		Block tobukuroR = this.takeRight();
		Block tobukuroL = this.takeLeft();

		IBlockState tobukuroR_FACE = tobukuroR.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobukuroL_FACE = tobukuroL.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState this_FACE = this.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		
		CMEvents.soundAmadoWin(worldIn, pos);
		
		if (i == 1) {
			/* i == 1, ブロックの向き */
			switch (direction) {
			case NORTH :
			default:
				/** プレイヤーの向きと叩く位置 **/
				if ((playerFacing == EnumFacing.NORTH && (hitX < 0.45D) && westState.getMaterial().isReplaceable()) ||
						(playerFacing == EnumFacing.SOUTH && (hitX < 0.45D) && westState.getMaterial().isReplaceable())) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.west(), this_FACE.withProperty(STAGE_1_2, Integer.valueOf(2))); }

				if ((playerFacing == EnumFacing.NORTH && (hitX > 0.55D) &&
						!eastState.getMaterial().isReplaceable() && eastBlock == tobukuroR && eastState.getValue(BaseStage3_Face.STAGE_1_3) > 1) ||
						(playerFacing == EnumFacing.SOUTH && (hitX > 0.55D) &&
						!eastState.getMaterial().isReplaceable() && eastBlock == tobukuroR && eastState.getValue(BaseStage3_Face.STAGE_1_3) > 1)) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.east(), tobukuroR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(eastState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); }
				break;

			case SOUTH :
				if ((playerFacing == EnumFacing.NORTH && (hitX < 0.45D) &&
						!westState.getMaterial().isReplaceable() && westBlock == tobukuroR && westState.getValue(BaseStage3_Face.STAGE_1_3) > 1) ||
						(playerFacing == EnumFacing.SOUTH && (hitX < 0.45D) &&
						!westState.getMaterial().isReplaceable() && westBlock == tobukuroR && westState.getValue(BaseStage3_Face.STAGE_1_3) > 1)) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.west(), tobukuroR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(westState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); }

				if ((playerFacing == EnumFacing.NORTH && (hitX > 0.55D) && eastState.getMaterial().isReplaceable()) ||
						(playerFacing == EnumFacing.SOUTH && (hitX > 0.55D) && eastState.getMaterial().isReplaceable())) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.east(), this_FACE.withProperty(STAGE_1_2, Integer.valueOf(2))); }
				break;

			case EAST :
				if ((playerFacing == EnumFacing.EAST && (hitZ < 0.45D) && northState.getMaterial().isReplaceable()) ||
						(playerFacing == EnumFacing.WEST && (hitZ < 0.45D) && northState.getMaterial().isReplaceable())) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.north(), this_FACE.withProperty(STAGE_1_2, Integer.valueOf(2))); }

				if ((playerFacing == EnumFacing.EAST && (hitZ > 0.55D) &&
						!southState.getMaterial().isReplaceable() && southBlock == tobukuroR && southState.getValue(BaseStage3_Face.STAGE_1_3) > 1) ||
						(playerFacing == EnumFacing.WEST && (hitZ > 0.55D) &&
						!southState.getMaterial().isReplaceable() && southBlock == tobukuroR && southState.getValue(BaseStage3_Face.STAGE_1_3) > 1)) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.south(), tobukuroR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(southState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); }
				break;
				
			case WEST :
				if ((playerFacing == EnumFacing.EAST && (hitZ < 0.45D) &&
						!northState.getMaterial().isReplaceable() && northBlock == tobukuroR && northState.getValue(BaseStage3_Face.STAGE_1_3) > 1) ||
						(playerFacing == EnumFacing.WEST && (hitZ < 0.45D) &&
						!northState.getMaterial().isReplaceable() && northBlock == tobukuroR && northState.getValue(BaseStage3_Face.STAGE_1_3) > 1)) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.north(), tobukuroR_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(northState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); }

				if ((playerFacing == EnumFacing.EAST && (hitZ > 0.55D) && southState.getMaterial().isReplaceable()) ||
						(playerFacing == EnumFacing.WEST && (hitZ > 0.55D) && southState.getMaterial().isReplaceable())) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.south(), this_FACE.withProperty(STAGE_1_2, Integer.valueOf(2))); }
				break;
			} // switch
		} // i == 1

		
		if (i == 2) {
			/* i == 2, ブロックの向き */
			switch (direction) {
			case NORTH :
			default:
				if ((playerFacing == EnumFacing.NORTH && (hitX < 0.45D) &&
						!westState.getMaterial().isReplaceable() && westBlock == tobukuroL && westState.getValue(BaseStage3_Face.STAGE_1_3) > 1) ||
						(playerFacing == EnumFacing.SOUTH && (hitX < 0.45D) &&
						!westState.getMaterial().isReplaceable() && westBlock == tobukuroL && westState.getValue(BaseStage3_Face.STAGE_1_3) > 1)) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.west(), tobukuroL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(westState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); }

				if ((playerFacing == EnumFacing.NORTH && (hitX > 0.55D) && eastState.getMaterial().isReplaceable()) ||
						(playerFacing == EnumFacing.SOUTH && (hitX > 0.55D) && eastState.getMaterial().isReplaceable())) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.east(), this_FACE.withProperty(STAGE_1_2, Integer.valueOf(1))); }
				break;

			case SOUTH :
				if ((playerFacing == EnumFacing.NORTH && (hitX < 0.45D) && westState.getMaterial().isReplaceable()) ||
						(playerFacing == EnumFacing.SOUTH && (hitX < 0.45D) && westState.getMaterial().isReplaceable())) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.west(), this_FACE.withProperty(STAGE_1_2, Integer.valueOf(1))); }

				if ((playerFacing == EnumFacing.NORTH && (hitX > 0.55D) &&
						!eastState.getMaterial().isReplaceable() && eastBlock == tobukuroL && eastState.getValue(BaseStage3_Face.STAGE_1_3) > 1) ||
						(playerFacing == EnumFacing.SOUTH && (hitX > 0.55D) &&
						!eastState.getMaterial().isReplaceable() && eastBlock == tobukuroL && eastState.getValue(BaseStage3_Face.STAGE_1_3) > 1)) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.east(), tobukuroL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(eastState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); }
				break;

			case EAST :
				if ((playerFacing == EnumFacing.EAST && (hitZ < 0.45D) &&
						!northState.getMaterial().isReplaceable() && northBlock == tobukuroL && northState.getValue(BaseStage3_Face.STAGE_1_3) > 1) ||
						(playerFacing == EnumFacing.WEST && (hitZ < 0.45D) &&
						!northState.getMaterial().isReplaceable() && northBlock == tobukuroL && northState.getValue(BaseStage3_Face.STAGE_1_3) > 1)) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.north(), tobukuroL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(northState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); }


				if ((playerFacing == EnumFacing.EAST && (hitZ > 0.55D) && southState.getMaterial().isReplaceable()) ||
						(playerFacing == EnumFacing.WEST && (hitZ > 0.55D) && southState.getMaterial().isReplaceable())) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.south(), this_FACE.withProperty(STAGE_1_2, Integer.valueOf(1))); }
				break;
				
			case WEST :
				if ((playerFacing == EnumFacing.EAST && (hitZ < 0.45D) && northState.getMaterial().isReplaceable()) ||
						(playerFacing == EnumFacing.WEST && (hitZ < 0.45D) && northState.getMaterial().isReplaceable())) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.north(), this_FACE.withProperty(STAGE_1_2, Integer.valueOf(1))); }

				if ((playerFacing == EnumFacing.EAST && (hitZ > 0.55D) &&
						!southState.getMaterial().isReplaceable() && southBlock == tobukuroL && southState.getValue(BaseStage3_Face.STAGE_1_3) > 1) ||
						(playerFacing == EnumFacing.WEST && (hitZ > 0.55D) &&
						!southState.getMaterial().isReplaceable() && southBlock == tobukuroL && southState.getValue(BaseStage3_Face.STAGE_1_3) > 1)) {

					this.putAIR(worldIn, pos);
					worldIn.setBlockState(pos.south(), tobukuroL_FACE.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(southState.getValue(BaseStage3_Face.STAGE_1_3) - 1))); }
				break;
			} // switch
		} // i == 2
		return true;
	}

	private void putAIR(World worldIn, BlockPos pos) {
		CMEvents.soundFusumaS(worldIn, pos);
		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
	}
	
	private Block takeRight() {
		if (this == Slidedoor_Blocks.AMADO_WIN) { return Slidedoor_Blocks.TOBUKURO_WINR; }
		else { return Slidedoor_Blocks.TOBUKUROS_WINR; }
	}
		
	private Block takeLeft() {
		if (this == Slidedoor_Blocks.AMADO_WIN) { return Slidedoor_Blocks.TOBUKURO_WINL; }
		else { return Slidedoor_Blocks.TOBUKUROS_WINL; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
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
}
