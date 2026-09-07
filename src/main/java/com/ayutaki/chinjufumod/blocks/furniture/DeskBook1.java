package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DeskBook1 extends BaseStage4_FaceDown {
	/* Collision */
	private static final AxisAlignedBB SOUTH_1 = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 4.0 * cw, 0.0 * cw, 5.0 * cw, 12.0 * cw, 1.5 * cw, 11.0 * cw);
	private static final AxisAlignedBB EAST_1 = CollisionHelper.getBlockBounds(EnumFacing.EAST, 4.0 * cw, 0.0 * cw, 5.0 * cw, 12.0 * cw, 1.5 * cw, 11.0 * cw);
	private static final AxisAlignedBB WEST_1 = CollisionHelper.getBlockBounds(EnumFacing.WEST, 4.0 * cw, 0.0 * cw, 5.0 * cw, 12.0 * cw, 1.5 * cw, 11.0 * cw);
	private static final AxisAlignedBB NORTH_1 = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 4.0 * cw, 0.0 * cw, 5.0 * cw, 12.0 * cw, 1.5 * cw, 11.0 * cw);
	
	private static final AxisAlignedBB SOUTH_4 = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 4.0 * cw, 0.0 * cw, 1.75 * cw, 12.0 * cw, 0.8 * cw, 14.25 * cw);
	private static final AxisAlignedBB EAST_4 = CollisionHelper.getBlockBounds(EnumFacing.EAST, 4.0 * cw, 0.0 * cw, 1.75 * cw, 12.0 * cw, 0.8 * cw, 14.25 * cw);
	private static final AxisAlignedBB WEST_4 = CollisionHelper.getBlockBounds(EnumFacing.WEST, 4.0 * cw, 0.0 * cw, 1.75 * cw, 12.0 * cw, 0.8 * cw, 14.25 * cw);
	private static final AxisAlignedBB NORTH_4 = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 4.0 * cw, 0.0 * cw, 1.75 * cw, 12.0 * cw, 0.8 * cw, 14.25 * cw);
	
	private static final AxisAlignedBB SOUTH_1D = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 4.0 * cw, -8.0 * cw, 5.0 * cw, 12.0 * cw, 0.01 * cw, 11.0 * cw);
	private static final AxisAlignedBB EAST_1D = CollisionHelper.getBlockBounds(EnumFacing.EAST, 4.0 * cw, -8.0 * cw, 5.0 * cw, 12.0 * cw, 0.01 * cw, 11.0 * cw);
	private static final AxisAlignedBB WEST_1D = CollisionHelper.getBlockBounds(EnumFacing.WEST, 4.0 * cw, -8.0 * cw, 5.0 * cw, 12.0 * cw, 0.01 * cw, 11.0 * cw);
	private static final AxisAlignedBB NORTH_1D = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 4.0 * cw, -8.0 * cw, 5.0 * cw, 12.0 * cw, 0.01 * cw, 11.0 * cw);
	
	private static final AxisAlignedBB SOUTH_4D = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 4.0 * cw, -8.0 * cw, 1.75 * cw, 12.0 * cw, 0.01 * cw, 14.25 * cw);
	private static final AxisAlignedBB EAST_4D = CollisionHelper.getBlockBounds(EnumFacing.EAST, 4.0 * cw, -8.0 * cw, 1.75 * cw, 12.0 * cw, 0.01 * cw, 14.25 * cw);
	private static final AxisAlignedBB WEST_4D = CollisionHelper.getBlockBounds(EnumFacing.WEST, 4.0 * cw, -8.0 * cw, 1.75 * cw, 12.0 * cw, 0.01 * cw, 14.25 * cw);
	private static final AxisAlignedBB NORTH_4D = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 4.0 * cw, -8.0 * cw, 1.75 * cw, 12.0 * cw, 0.01 * cw, 14.25 * cw);

	public DeskBook1(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(1.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		
		if (hItem == Items_Chinjufu.SHOUHOU_empty) {
			CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, Chinjufu_Blocks.NOTEBOOK.getDefaultState()
					.withProperty(NoteBook.H_FACING, state.getValue(H_FACING))
					.withProperty(NoteBook.STAGE_1_4, Integer.valueOf(3)));
			return true; }
		
		if (hStack.isEmpty()) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE_1_4));
			CMEvents.soundPage(worldIn, pos);
			if (i == 1 || i == 4) { CMEvents.soundWoodPlace(worldIn, pos); }
			return true; }
		
		return false;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		
		switch (direction) {
		case NORTH:
		default: 
			return (i == 1)? (flag? NORTH_1 : NORTH_1D) : (flag? NORTH_4 : NORTH_4D);
		
		case SOUTH: 
			return (i == 1)? (flag? SOUTH_1 : SOUTH_1D) : (flag? SOUTH_4 : SOUTH_4D);
			
		case EAST: 
			return (i == 1)? (flag? EAST_1 : EAST_1D) : (flag? EAST_4 : EAST_4D);
			
		case WEST: 
			return (i == 1)? (flag? WEST_1 : WEST_1D) : (flag? WEST_4 : WEST_4D);
		}
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
		stack.add(new ItemStack(Items.BOOK, 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items.BOOK, 1, 0);
	}
}
