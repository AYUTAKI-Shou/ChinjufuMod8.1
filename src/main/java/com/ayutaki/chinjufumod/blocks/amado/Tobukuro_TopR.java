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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Tobukuro_TopR extends BaseStage3_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, -0.03125, 0.3125, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, -0.03125, 0.3125, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, -0.03125, 0.3125, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, -0.03125, 0.3125, 1.03125, 1.03125);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	/** 1=4 sheets, 2=3 sheets, 3=2 sheets **/
	public Tobukuro_TopR(String name) {
		super(name);
		setHardness(2.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/** 1=4 sheets, 2=3 sheets, 3=2 sheets **/
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = state.getValue(H_FACING);

		IBlockState northState = worldIn.getBlockState(pos.north());
		IBlockState southState = worldIn.getBlockState(pos.south());
		IBlockState eastState = worldIn.getBlockState(pos.east());
		IBlockState westState = worldIn.getBlockState(pos.west());

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();

		IBlockState AMADO_FACELO_1 = this.takeBot().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))
				.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1));
		IBlockState AMADO_FACEUP_1 = this.takeTop().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))
				.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1));

		CMEvents.soundAmado(worldIn, pos);
		
		switch (direction) {
		case NORTH :
		default :
			if (westState.getMaterial().isReplaceable()) {
				worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), AMADO_FACELO_1);
				worldIn.setBlockState(new BlockPos(x - 1, y, z), AMADO_FACEUP_1);

				if (i == 3) { this.updateTOBUKURO_DOWN3(state, worldIn, pos); }
				else { this.updateTOBUKURO_DOWN(state, worldIn, pos); } }
			
			else { } //!Replaceable
			break;

		case SOUTH :
			if (eastState.getMaterial().isReplaceable()) {
				worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), AMADO_FACELO_1);
				worldIn.setBlockState(new BlockPos(x + 1, y, z), AMADO_FACEUP_1);

				if (i == 3) { this.updateTOBUKURO_DOWN3(state, worldIn, pos); }
				else { this.updateTOBUKURO_DOWN(state, worldIn, pos); } }
			
			else { } //!Replaceable
			break;

		case EAST :
			if (northState.getMaterial().isReplaceable()) {
				worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), AMADO_FACELO_1);
				worldIn.setBlockState(new BlockPos(x, y, z - 1), AMADO_FACEUP_1);

				if (i == 3) { this.updateTOBUKURO_DOWN3(state, worldIn, pos); }
				else { this.updateTOBUKURO_DOWN(state, worldIn, pos); } }
			
			else { } //!Replaceable
			break;
			
		case WEST :
			if (southState.getMaterial().isReplaceable()) {
				worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), AMADO_FACELO_1);
				worldIn.setBlockState(new BlockPos(x, y, z + 1), AMADO_FACEUP_1);

				if (i == 3) { this.updateTOBUKURO_DOWN3(state, worldIn, pos); }
				else { this.updateTOBUKURO_DOWN(state, worldIn, pos); } }
			
			else { } //!Replaceable
			break;
		} // switch
		return true;
	}

	private void updateTOBUKURO_DOWN(IBlockState state, World worldIn, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		IBlockState tobuBotR_FACE = this.takeRightBot().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)));
		worldIn.setBlockState(pos.down(), tobuBotR_FACE.withProperty(STAGE_1_3, Integer.valueOf(i + 1)));
	}
	
	private void updateTOBUKURO_DOWN3(IBlockState state, World worldIn, BlockPos pos) {
		IBlockState tobuBotR2_FACE = this.takeRightBot2().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));
		IBlockState tobuTopR2_FACE = this.takeRightTop2().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlockState(pos, tobuTopR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
		worldIn.setBlockState(pos.down(), tobuBotR2_FACE.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1)));
	}
	
	private Block takeRightBot2() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPR) { return Slidedoor_Blocks.TOBUKURO_BOTR2; }
		else { return Slidedoor_Blocks.TOBUKUROS_BOTR2; }
	}
	
	private Block takeRightBot() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPR) { return Slidedoor_Blocks.TOBUKURO_BOTR; }
		else { return Slidedoor_Blocks.TOBUKUROS_BOTR; }
	}

	private Block takeRightTop2() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPR) { return Slidedoor_Blocks.TOBUKURO_TOPR2; }
		else { return Slidedoor_Blocks.TOBUKUROS_TOPR2; }
	}
	
	private Block takeTop() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPR) { return Slidedoor_Blocks.AMADO_TOP; }
		else { return Slidedoor_Blocks.AMADOS_TOP; }
	}

	private Block takeBot() {
		if (this == Slidedoor_Blocks.TOBUKURO_TOPR) { return Slidedoor_Blocks.AMADO_BOT; }
		else { return Slidedoor_Blocks.AMADOS_BOT; }
	}
	
	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.down()).getBlock() instanceof Tobukuro_BotR) {
			if (playerIn.capabilities.isCreativeMode) { worldIn.destroyBlock(pos.down(), false); }
			else { worldIn.destroyBlock(pos.down(), true); }
		}
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	/*Drop Item and Clone Item.*/
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
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
}
