package com.ayutaki.chinjufumod.blocks.window;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.window.CurtainTall_CT;
import com.ayutaki.chinjufumod.items.window.Curtain_CT;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Window_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
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

public class Window extends BaseStage4_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.453125, 0.0, 0.0, 0.546875, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.453125, 0.0, 0.0, 0.546875, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.453125, 0.0, 0.0, 0.546875, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.453125, 0.0, 0.0, 0.546875, 1.0, 1.0);

	private static final AxisAlignedBB AABB_OL_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, -0.453125, 0.0, 0.89375, 0.546875, 1.0, 0.9875);
	private static final AxisAlignedBB AABB_OL_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, -0.453125, 0.0, 0.89375, 0.546875, 1.0, 0.9875);
	private static final AxisAlignedBB AABB_OL_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, -0.453125, 0.0, 0.89375, 0.546875, 1.0, 0.9875);
	private static final AxisAlignedBB AABB_OL_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, -0.453125, 0.0, 0.89375, 0.546875, 1.0, 0.9875);

	private static final AxisAlignedBB AABB_OR_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, -0.453125, 0.0, 0.0125, 0.546875, 1.0, 0.10625);
	private static final AxisAlignedBB AABB_OR_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, -0.453125, 0.0, 0.0125, 0.546875, 1.0, 0.10625);
	private static final AxisAlignedBB AABB_OR_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, -0.453125, 0.0, 0.0125, 0.546875, 1.0, 0.10625);
	private static final AxisAlignedBB AABB_OR_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, -0.453125, 0.0, 0.0125, 0.546875, 1.0, 0.10625);

	public Window(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
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
		
		if (hItem instanceof Curtain_CT || hItem instanceof CurtainTall_CT) { return false; }

		else {
			if (i == 1 || i == 3) {
				CMEvents.soundWin_Open(worldIn, pos);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
	
			else {
				CMEvents.soundWin_Close(worldIn, pos);
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
					.withProperty(Window.STAGE_1_4, Integer.valueOf(3)); }
		
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite())
				.withProperty(Window.STAGE_1_4, Integer.valueOf(1));
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

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		EnumFacing direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH :
		default:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1)? AABB_NORTH : ((i ==2)? AABB_OL_NORTH : ((i ==3)? AABB_NORTH : AABB_OR_NORTH)));
			break;
			
		case SOUTH:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1)? AABB_SOUTH : ((i ==2)? AABB_OL_SOUTH : ((i ==3)? AABB_SOUTH : AABB_OR_SOUTH)));
			break;

		case EAST:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1)? AABB_EAST : ((i ==2)? AABB_OL_EAST : ((i ==3)? AABB_EAST : AABB_OR_EAST)));
			break;

		case WEST:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1)? AABB_WEST : ((i ==2)? AABB_OL_WEST : ((i ==3)? AABB_WEST : AABB_OR_WEST)));
			break;
		}
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
		stack.add(new ItemStack(Items_Chinjufu.WINDOW_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.WINDOW_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == Window_Blocks.WINDOW_oak) { return 0; }
		if (this == Window_Blocks.WINDOW_spruce) { return 1; }
		if (this == Window_Blocks.WINDOW_birch) { return 2; }
		if (this == Window_Blocks.WINDOW_jungle) { return 3; }
		if (this == Window_Blocks.WINDOW_acacia) { return 4; }
		if (this == Window_Blocks.WINDOW_darkoak) { return 5; }
		if (this == Window_Blocks.WINDOW_sakura) { return 6; }
		if (this == Window_Blocks.WINDOW_kaede) { return 7; }
		else { return 8; }
	}
}
