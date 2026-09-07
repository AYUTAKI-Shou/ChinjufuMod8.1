package com.ayutaki.chinjufumod.blocks.window;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Window_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Curtain extends BaseStage4_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);

	private static final AxisAlignedBB AABB_OR_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.13125, 1.0, 0.125);
	private static final AxisAlignedBB AABB_OR_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.13125, 1.0, 0.125);
	private static final AxisAlignedBB AABB_OR_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.13125, 1.0, 0.125);
	private static final AxisAlignedBB AABB_OR_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.13125, 1.0, 0.125);

	private static final AxisAlignedBB AABB_OL_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.875, 0.13125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_OL_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.875, 0.13125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_OL_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.875, 0.13125, 1.0, 1.0);
	private static final AxisAlignedBB AABB_OL_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.875, 0.13125, 1.0, 1.0);
	
	public Curtain(String name) {
		super(name);
		setSoundType(SoundType.CLOTH);
		setHardness(1.0F);
		setResistance(1.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		/** 1=Close Left, 2=Open Left, 3=Close Right, 4=Open Right **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (hItem instanceof Base_Hake) { return false; }

		else {
			CMEvents.soundCurtain(worldIn, pos, 0.8F, 0.9F);
			
			if (i == 1 || i == 3) {
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
	
			else {
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 1)), 3); }
			
			return true;
		}
	}


	/* BlockState when it was placed. */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		
		if (placer.isSneaking()) {
			return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite())
					.withProperty(Curtain.STAGE_1_4, Integer.valueOf(3)); }
		
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite())
				.withProperty(Curtain.STAGE_1_4, Integer.valueOf(1));
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default:
			return (i == 1)? AABB_NORTH : ((i ==2)? AABB_OL_NORTH : ((i ==3)? AABB_NORTH : AABB_OR_NORTH));
			
		case SOUTH:
			return (i == 1)? AABB_SOUTH : ((i ==2)? AABB_OL_SOUTH : ((i ==3)? AABB_SOUTH : AABB_OR_SOUTH));

		case EAST:
			return (i == 1)? AABB_EAST : ((i ==2)? AABB_OL_EAST : ((i ==3)? AABB_EAST : AABB_OR_EAST));

		case WEST:
			return (i == 1)? AABB_WEST : ((i ==2)? AABB_OL_WEST : ((i ==3)? AABB_WEST : AABB_OR_WEST));
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
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
		stack.add(new ItemStack(Items_Chinjufu.CURTAIN_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.CURTAIN_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == Window_Blocks.CURTAIN_white) { return 0; }
		if (this == Window_Blocks.CURTAIN_orange) { return 1; }
		if (this == Window_Blocks.CURTAIN_magenta) { return 2; }
		if (this == Window_Blocks.CURTAIN_lightblue) { return 3; }
		if (this == Window_Blocks.CURTAIN_yellow) { return 4; }
		if (this == Window_Blocks.CURTAIN_lime) { return 5; }
		if (this == Window_Blocks.CURTAIN_pink) { return 6; }
		if (this == Window_Blocks.CURTAIN_gray) { return 7; }
		if (this == Window_Blocks.CURTAIN_lightgray) { return 8; }
		if (this == Window_Blocks.CURTAIN_cyan) { return 9; }
		if (this == Window_Blocks.CURTAIN_purple) { return 10; }
		if (this == Window_Blocks.CURTAIN_blue) { return 11; }
		if (this == Window_Blocks.CURTAIN_brown) { return 12; }
		if (this == Window_Blocks.CURTAIN_green) { return 13; }
		if (this == Window_Blocks.CURTAIN_red) { return 14; }
		else { return 15; }
	}
}
