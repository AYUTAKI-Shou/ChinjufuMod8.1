package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_Name;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/* 空き箱は, Minecraftのブロックを拡張したクラス
* BlockEmptyBox is a class that extends Block of Minecraft. */
public class EmptyBox extends Regi_Name {
	/* Collision 1/16=0.0625 ここから ↓ */
	private static final AxisAlignedBB AABB_BASE = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);
	private static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
	
	/*ブロックな基本的な性質 Blocked basic characteristics */
	public EmptyBox(String name) {
		super(name, Material.WOOD);
		setCreativeTab(ChinjufuModTabs.CHINJUFU);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int gHC = hStack.getCount();

		boolean ammo = (hItem == Items_Weapon.AMMUNITION_L && gHC >= 8);
		boolean bauxi = (hItem == Items_Chinjufu.BAUXITE && gHC >= 8);

		if (ammo || bauxi) {
			CMEvents.consumeN_Hand(8, playerIn, hand);
			CMEvents.soundWoodPlace(worldIn, pos);
			
			int takeType = (ammo? 1 : 2);
			EnumFacing playerFacing = playerIn.getHorizontalFacing().getOpposite();
			
			worldIn.setBlockState(pos, Chinjufu_Blocks.AMUBAUX.getDefaultState()
					.withProperty(AmmoBauxiteBox.H_FACING, playerFacing)
					.withProperty(AmmoBauxiteBox.STAGE_1_2, Integer.valueOf(takeType)));
			return true; }
		
		return false;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, 
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean t_f) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_BASE);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return FULL_BLOCK_AABB;
	}
	/*ここまで So far↑ */

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

	/*立方体かどうか Cube or not? */
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	/*透明かどうか Opaque or not? */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
}
