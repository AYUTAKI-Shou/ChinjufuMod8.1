package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.blocks.school.TeacherDesk;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class DeskCloth extends BaseStage4_Face {

	private static final AxisAlignedBB SOUTH_AABB = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 15.0 * cw, 9.0 * cw, -1.5 * cw, 16.0 * cw, 16.0 * cw, 17.5 * cw);
	private static final AxisAlignedBB EAST_AABB = CollisionHelper.getBlockBounds(EnumFacing.EAST, 15.0 * cw, 9.0 * cw, -1.5 * cw, 16.0 * cw, 16.0 * cw, 17.5 * cw);
	private static final AxisAlignedBB WEST_AABB = CollisionHelper.getBlockBounds(EnumFacing.WEST, 15.0 * cw, 9.0 * cw, -1.5 * cw, 16.0 * cw, 16.0 * cw, 17.5 * cw);
	private static final AxisAlignedBB NORTH_AABB = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 15.0 * cw, 9.0 * cw, -1.5 * cw, 16.0 * cw, 16.0 * cw, 17.5 * cw);
	
	public DeskCloth(String name) {
		super(name);
		setSoundType(SoundType.CLOTH);
		setHardness(1.0F);
		setResistance(1.0F);
		setLightOpacity(1);
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (hStack.isEmpty()) {
			CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, Item.getItemFromBlock(Blocks.CARPET), 1, cloneMeta(state));
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			return true; }
		
		else { return false; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return NORTH_AABB;
		case SOUTH: return SOUTH_AABB;
		case EAST: return EAST_AABB;
		case WEST: return WEST_AABB;
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		this.checkAndDropBlock(worldIn, pos, state);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		this.checkAndDropBlock(worldIn, pos, state);
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (this.canNotStay(worldIn, pos, state)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3); }
		
		else { }
	}
	
	public boolean canNotStay(World worldIn, BlockPos pos, IBlockState state) {
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		Block northBlock = worldIn.getBlockState(pos.north()).getBlock();
		Block southBlock = worldIn.getBlockState(pos.south()).getBlock();
		Block eastBlock = worldIn.getBlockState(pos.east()).getBlock();
		Block westBlock = worldIn.getBlockState(pos.west()).getBlock();
		
		if (direction == EnumFacing.NORTH) { return !(northBlock instanceof OfficeDesk) && !(northBlock instanceof TeacherDesk); }
		else if (direction == EnumFacing.SOUTH) { return !(southBlock instanceof OfficeDesk) && !(southBlock instanceof TeacherDesk); }
		else if (direction == EnumFacing.EAST) { return !(eastBlock instanceof OfficeDesk) && !(eastBlock instanceof TeacherDesk); }
		else if (direction == EnumFacing.WEST) { return !(westBlock instanceof OfficeDesk) && !(westBlock instanceof TeacherDesk); }
		else { return true; }
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
		stack.add(new ItemStack(Item.getItemFromBlock(Blocks.CARPET), 1, cloneMeta(state)));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Item.getItemFromBlock(Blocks.CARPET), 1, cloneMeta(state));
	}
	
	private int cloneMeta(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (this == Chinjufu_Blocks.DESKCLOTH_03) {
			if (i == 1) { return 0; } //carpetWhite
			if (i == 2) { return 1; } //carpetOrange
			if (i == 3) { return 2; } //carpetMagenta
			else { return 3; } } //carpetLightBlue
		
		if (this == Chinjufu_Blocks.DESKCLOTH_47) {
			if (i == 1) { return 4; } //carpetYellow
			if (i == 2) { return 5; } //carpetLime
			if (i == 3) { return 6; } //carpetPink
			else { return 7; } } //carpetGray
		
		if (this == Chinjufu_Blocks.DESKCLOTH_811) {
			if (i == 1) { return 8; } //carpetLightGray
			if (i == 2) { return 9; } //carpetCyan
			if (i == 3) { return 10; } //carpetPurple
			else { return 11; } } //carpetBlue
		
		else {
			if (i == 1) { return 12; } //carpetBrown
			if (i == 2) { return 13; } //carpetGreen
			if (i == 3) { return 14; } //carpetRed
			else { return 15; } } //carpetBlack
	}
}
