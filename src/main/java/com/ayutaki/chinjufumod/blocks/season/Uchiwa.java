package com.ayutaki.chinjufumod.blocks.season;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Uchiwa extends BaseFacingSapo {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public static final PropertyInteger KAKUDO = PropertyInteger.create("kakudo", 1, 4);

	public Uchiwa(String name) {
		super(name);
		setSoundType(SoundType.CLOTH);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(KAKUDO, Integer.valueOf(1)));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean t_f) {

		EnumFacing facing = state.getValue(H_FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB[facing.getHorizontalIndex()]);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (hStack.isEmpty()) {
			if (playerIn.isSneaking()) {
				CMEvents.soundClothPlace(worldIn, pos);
				worldIn.setBlockState(pos, state.cycleProperty(KAKUDO), 2); }
			
			else { //!isCrouching
				CMEvents.textNotSneak(worldIn, pos, playerIn); }
			return true; 
		}
		return false;
	}

	protected int getLog(IBlockState state) {
		return ((Integer)state.getValue(KAKUDO)).intValue() * 2;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta))
				.withProperty(KAKUDO, Integer.valueOf(1 + (meta >> 2)));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing)state.getValue(H_FACING)).getHorizontalIndex();
		i = i | ((Integer)state.getValue(KAKUDO)).intValue() - 1 << 2;
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { H_FACING, KAKUDO });
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
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
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
		stack.add(new ItemStack(Items_Seasonal.UCHIWA_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Seasonal.UCHIWA_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == Seasonal_Blocks.UCHIWA_white) { return 0; }
		if (this == Seasonal_Blocks.UCHIWA_orange) { return 1; }
		if (this == Seasonal_Blocks.UCHIWA_magenta) { return 2; }
		if (this == Seasonal_Blocks.UCHIWA_lightb) { return 3; }
		if (this == Seasonal_Blocks.UCHIWA_yellow) { return 4; }
		if (this == Seasonal_Blocks.UCHIWA_lime) { return 5; }
		if (this == Seasonal_Blocks.UCHIWA_pink) { return 6; }
		if (this == Seasonal_Blocks.UCHIWA_gray) { return 7; }
		if (this == Seasonal_Blocks.UCHIWA_lightg) { return 8; }
		if (this == Seasonal_Blocks.UCHIWA_cyan) { return 9; }
		if (this == Seasonal_Blocks.UCHIWA_purple) { return 10; }
		if (this == Seasonal_Blocks.UCHIWA_blue) { return 11; }
		if (this == Seasonal_Blocks.UCHIWA_brown) { return 12; }
		if (this == Seasonal_Blocks.UCHIWA_green) { return 13; }
		if (this == Seasonal_Blocks.UCHIWA_red) { return 14; }
		else { return 15; }
	}
}
