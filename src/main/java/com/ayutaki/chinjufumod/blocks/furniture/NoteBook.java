package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.handler.CMEvents;
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

public class NoteBook extends BaseStage4_FaceDown {
	/* Collision */
	private static final AxisAlignedBB SOUTH_1 = new AxisAlignedBB(5.0D * cw, 0.0D * cw, 4.0D * cw, 11.0D * cw, 0.6D * cw, 12.0D * cw);
	private static final AxisAlignedBB EAST_1 = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 5.0D * cw, 12.0D * cw, 0.6D * cw, 11.0D * cw);
	private static final AxisAlignedBB WEST_1 = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 5.0D * cw, 12.0D * cw, 0.6D * cw, 11.0D * cw);
	private static final AxisAlignedBB NORTH_1 = new AxisAlignedBB(5.0D * cw, 0.0D * cw, 4.0D * cw, 11.0D * cw, 0.6D * cw, 12.0D * cw);
	
	private static final AxisAlignedBB SOUTH_HB = new AxisAlignedBB(1.75D * cw, 0.0D * cw, 4.0D * cw, 14.25D * cw, 0.5D * cw, 12.0D * cw);
	private static final AxisAlignedBB EAST_HB = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 1.75D * cw, 12.0D * cw, 0.5D * cw, 14.25D * cw);
	private static final AxisAlignedBB WEST_HB = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 1.75D * cw, 12.0D * cw, 0.5D * cw, 14.25D * cw);
	private static final AxisAlignedBB NORTH_HB = new AxisAlignedBB(1.75D * cw, 0.0D * cw, 4.0D * cw, 14.25D * cw, 0.5D * cw, 12.0D * cw);
	
	private static final AxisAlignedBB SOUTH_1D = new AxisAlignedBB(5.0D * cw, -8.0D * cw, 4.0D * cw, 11.0D * cw, 0.01D * cw, 12.0D * cw);
	private static final AxisAlignedBB EAST_1D = new AxisAlignedBB(4.0D * cw, -8.0D * cw, 5.0D * cw, 12.0D * cw, 0.01D * cw, 11.0D * cw);
	private static final AxisAlignedBB WEST_1D = new AxisAlignedBB(4.0D * cw, -8.0D * cw, 5.0D * cw, 12.0D * cw, 0.01D * cw, 11.0D * cw);
	private static final AxisAlignedBB NORTH_1D = new AxisAlignedBB(5.0D * cw, -8.0D * cw, 4.0D * cw, 11.0D * cw, 0.01D * cw, 12.0D * cw);
	
	private static final AxisAlignedBB SOUTH_HBD = new AxisAlignedBB(1.75D * cw, -8.0D * cw, 4.0D * cw, 14.25D * cw, 0.01D * cw, 12.0D * cw);
	private static final AxisAlignedBB EAST_HBD = new AxisAlignedBB(4.0D * cw, -8.0D * cw, 1.75D * cw, 12.0D * cw, 0.01D * cw, 14.25D * cw);
	private static final AxisAlignedBB WEST_HBD = new AxisAlignedBB(4.0D * cw, -8.0D * cw, 1.75D * cw, 12.0D * cw, 0.01D * cw, 14.25D * cw);
	private static final AxisAlignedBB NORTH_HBD = new AxisAlignedBB(1.75D * cw, -8.0D * cw, 4.0D * cw, 14.25D * cw, 0.01D * cw, 12.0D * cw);
	
	private static final AxisAlignedBB SOUTH_3 = new AxisAlignedBB(5.0D * cw, 0.0D * cw, 4.0D * cw, 11.0D * cw, 1.9D * cw, 12.0D * cw);
	private static final AxisAlignedBB EAST_3 = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 5.0D * cw, 12.0D * cw, 1.9D * cw, 11.0D * cw);
	private static final AxisAlignedBB WEST_3 = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 5.0D * cw, 12.0D * cw, 1.9D * cw, 11.0D * cw);
	private static final AxisAlignedBB NORTH_3 = new AxisAlignedBB(5.0D * cw, 0.0D * cw, 4.0D * cw, 11.0D * cw, 1.9D * cw, 12.0D * cw);
	
	private static final AxisAlignedBB SOUTH_4HB = new AxisAlignedBB(1.75D * cw, 0.0D * cw, 6.0D * cw, 14.25D * cw, 0.5D * cw, 14.0D * cw);
	private static final AxisAlignedBB EAST_4HB = new AxisAlignedBB(6.0D * cw, 0.0D * cw, 1.75D * cw, 14.0D * cw, 0.5D * cw, 14.25D * cw);
	private static final AxisAlignedBB WEST_4HB = new AxisAlignedBB(2.0D * cw, 0.0D * cw, 1.75D * cw, 10.0D * cw, 0.5D * cw, 14.25D * cw);
	private static final AxisAlignedBB NORTH_4HB = new AxisAlignedBB(1.75D * cw, 0.0D * cw, 2.0D * cw, 14.25D * cw, 0.5D * cw, 10.0D * cw);
	
	private static final AxisAlignedBB SOUTH_3D = new AxisAlignedBB(5.0D * cw, -8.0D * cw, 4.0D * cw, 11.0D * cw, 0.01D * cw, 12.0D * cw);
	private static final AxisAlignedBB EAST_3D = new AxisAlignedBB(4.0D * cw, -8.0D * cw, 5.0D * cw, 12.0D * cw, 0.01D * cw, 11.0D * cw);
	private static final AxisAlignedBB WEST_3D = new AxisAlignedBB(4.0D * cw, -8.0D * cw, 5.0D * cw, 12.0D * cw, 0.01D * cw, 11.0D * cw);
	private static final AxisAlignedBB NORTH_3D = new AxisAlignedBB(5.0D * cw, -8.0D * cw, 4.0D * cw, 11.0D * cw, 0.01D * cw, 12.0D * cw);
	
	private static final AxisAlignedBB SOUTH_4HBD = new AxisAlignedBB(1.75D * cw, -8.0D * cw, 6.0D * cw, 14.25D * cw, 0.01D * cw, 14.0D * cw);
	private static final AxisAlignedBB EAST_4HBD = new AxisAlignedBB(6.0D * cw, -8.0D * cw, 1.75D * cw, 14.0D * cw, 0.01D * cw, 14.25D * cw);
	private static final AxisAlignedBB WEST_4HBD = new AxisAlignedBB(2.0D * cw, -8.0D * cw, 1.75D * cw, 10.0D * cw, 0.01D * cw, 14.25D * cw);
	private static final AxisAlignedBB NORTH_4HBD = new AxisAlignedBB(1.75D * cw, -8.0D * cw, 2.0D * cw, 14.25D * cw, 0.01D * cw, 10.0D * cw);
	
	public NoteBook(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean hasBook = (i == 3 || i == 4);

		if (hItem == Items.BOOK) {
			if (!hasBook) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 2)));
				return true; }

			else { return false; }
		}
		
		if (hStack.isEmpty()) {
			CMEvents.soundPage(worldIn, pos);
			if (hasBook) { CMEvents.soundWoodPlace(worldIn, pos); }
			
			boolean closed = (i == 1 || i == 3);
			if (closed) { worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); }
			if (!closed) { worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 1))); }
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
		
		AxisAlignedBB NORTH_12 = (i == 1)? (flag? NORTH_1 : NORTH_1D) : (flag? NORTH_HB : NORTH_HBD);
		AxisAlignedBB SOUTH_12 = (i == 1)? (flag? SOUTH_1 : SOUTH_1D) : (flag? SOUTH_HB : SOUTH_HBD);
		AxisAlignedBB EAST_12 = (i == 1)? (flag? EAST_1 : EAST_1D) : (flag? EAST_HB : EAST_HBD);
		AxisAlignedBB WEST_12 = (i == 1)? (flag? WEST_1 : WEST_1D) : (flag? WEST_HB : WEST_HBD);
		
		AxisAlignedBB NORTH_34 = (i == 3)? (flag? NORTH_3 : NORTH_3D) : (flag? NORTH_4HB : NORTH_4HBD);
		AxisAlignedBB SOUTH_34 = (i == 3)? (flag? SOUTH_3 : SOUTH_3D) : (flag? SOUTH_4HB : SOUTH_4HBD);
		AxisAlignedBB EAST_34 = (i == 3)? (flag? EAST_3 : EAST_3D) : (flag? EAST_4HB : EAST_4HBD);
		AxisAlignedBB WEST_34 = (i == 3)? (flag? WEST_3 : WEST_3D) : (flag? WEST_4HB : WEST_4HBD);
		
		switch (direction) {
		case NORTH:
		default: return (i <= 2)? NORTH_12 : NORTH_34;
		case SOUTH: return (i <= 2)? SOUTH_12 : SOUTH_34;
		case EAST: return (i <= 2)? EAST_12 :EAST_34;
		case WEST: return (i <= 2)? WEST_12 : WEST_34;
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
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		List<ItemStack> stack = new ArrayList<ItemStack>();
		
		stack.add(new ItemStack(Items_Chinjufu.SHOUHOU_empty, 1, 0));
		if (i >= 3) { stack.add(new ItemStack(Items.BOOK, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.SHOUHOU_empty, 1, 0);
	}
}
