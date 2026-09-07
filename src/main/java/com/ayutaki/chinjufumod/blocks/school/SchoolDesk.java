package com.ayutaki.chinjufumod.blocks.school;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.School_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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

public class SchoolDesk extends BaseFacingSapo {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, -0.1875, 1.0, 1.0, 1.1875);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, -0.1875, 1.0, 1.0, 1.1875);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, -0.1875, 1.0, 1.0, 1.1875);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, -0.1875, 1.0, 1.0, 1.1875);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public SchoolDesk(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumFacing playerFacing = playerIn.getHorizontalFacing().getOpposite();
		IBlockState upState = worldIn.getBlockState(pos.up());
		
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean upAble = upState.getMaterial().isReplaceable();
		boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
		
		if (success) {
			if (upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				Block bookBlock = (hItem == Items.BOOK)? Chinjufu_Blocks.DESKBOOK_1 : Chinjufu_Blocks.NOTEBOOK;
				
				worldIn.setBlockState(pos.up(), bookBlock.getDefaultState()
						.withProperty(BaseStage4_FaceDown.H_FACING, playerFacing)
						.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(1))); }
			return true; }
		
		else { return false; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes,
			@Nullable Entity entityIn, boolean t_f) {
		EnumFacing facing = state.getValue(H_FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB[facing.getHorizontalIndex()]);
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
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
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Chinjufu.SCHOOLDESK_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.SCHOOLDESK_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == School_Blocks.SCHOOLDESK) { return 0; }
		if (this == School_Blocks.SCHOOLDESK_s) { return 1; }
		if (this == School_Blocks.SCHOOLDESK_b) { return 2; }
		if (this == School_Blocks.SCHOOLDESK_j) { return 3; }
		if (this == School_Blocks.SCHOOLDESK_a) { return 4; }
		if (this == School_Blocks.SCHOOLDESK_d) { return 5; }
		if (this == School_Blocks.SCHOOLDESK_saku) { return 6; }
		if (this == School_Blocks.SCHOOLDESK_kae) { return 7; }
		else { return 8; } 
	}
}
