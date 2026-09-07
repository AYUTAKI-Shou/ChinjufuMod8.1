package com.ayutaki.chinjufumod.blocks.chair;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.entity.helper.SittableUtil;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.state.TypeLR;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Sofa extends BaseFacingSapo {

	public static final PropertyEnum<TypeLR> TYPE = PropertyEnum.create("type", TypeLR.class);

	private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 0.875);
	private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.0, 0.0, 0.0, 0.875, 0.375, 1.0);
	private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.125, 0.0, 0.0, 1.0, 0.375, 1.0);
	private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0, 0.0, 0.125, 1.0, 0.375, 1.0);

	private static final AxisAlignedBB CHAIR_BASE = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 0.375, 0.875);
	private static final AxisAlignedBB CHAIR_BACKREST_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.375, 0.0, 0.1, 1.0, 1.0);
	private static final AxisAlignedBB CHAIR_BACKREST_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.375, 0.0, 0.1, 1.0, 1.0);
	private static final AxisAlignedBB CHAIR_BACKREST_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.375, 0.0, 0.1, 1.0, 1.0);
	private static final AxisAlignedBB CHAIR_BACKREST_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.375, 0.0, 0.1, 1.0, 1.0);

	public Sofa(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.CHINJUFU);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Base_Hake) { return false; }

		else {
			if (SittableUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 2.5 * 0.0625)) {
				worldIn.updateComparatorOutputLevel(pos, this);
				CMEvents.soundKinuzure(worldIn, pos);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return SittableUtil.isSomeoneSitting(worldIn, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);

		switch (direction) {
		case NORTH :
		default : return AABB_NORTH;
		case SOUTH : return AABB_SOUTH;
		case EAST : return AABB_EAST;
		case WEST : return AABB_WEST;
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		if (!(entityIn instanceof SitableEntity)) {
			EnumFacing direction= state.getValue(H_FACING);
			switch(direction) {
			case NORTH:
			default:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BACKREST_NORTH);
				break;
			case SOUTH:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BACKREST_SOUTH);
				break;
			case EAST:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BACKREST_EAST);
				break;
			case WEST:
				super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BACKREST_WEST);
				break;
			}
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, CHAIR_BASE);
		}
	}

	/* Connect to the side. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing facing = (EnumFacing) state.getValue(H_FACING);
		IBlockState left_block = worldIn.getBlockState(pos.offset(facing.rotateYCCW()));
		IBlockState right_block = worldIn.getBlockState(pos.offset(facing.rotateY()));
		boolean left = (left_block.getBlock() == this) && left_block.getValue(H_FACING).equals(facing);
		boolean right = (right_block.getBlock() == this) && right_block.getValue(H_FACING).equals(facing);

		 if (right) {

			if (left) { return state.withProperty(TYPE, TypeLR.BOTH); }

			else { return state.withProperty(TYPE, TypeLR.RIGHT); } }

		else if (left) {

			if (right) { return state.withProperty(TYPE, TypeLR.BOTH); }

			else { return state.withProperty(TYPE, TypeLR.LEFT); } }
		 
		return state.withProperty(TYPE, TypeLR.DEFAULT);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { H_FACING, TYPE });
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
		if (this == Furniture_Blocks.SOFA_leather) { stack.add(new ItemStack(Items_Chinjufu.BENCH_item, 1, 9)); } 
		if (this != Furniture_Blocks.SOFA_leather) { stack.add(new ItemStack(Items_Chinjufu.SOFA_item, 1, cloneMeta())); } 
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if (this == Furniture_Blocks.SOFA_leather) { return new ItemStack(Items_Chinjufu.BENCH_item, 1, 9); } 
		else { return new ItemStack(Items_Chinjufu.SOFA_item, 1, cloneMeta()); }
	}
	
	private int cloneMeta() {
		if (this == Furniture_Blocks.SOFA_white) { return 0; }
		if (this == Furniture_Blocks.SOFA_orange) { return 1; }
		if (this == Furniture_Blocks.SOFA_magenta) { return 2; }
		if (this == Furniture_Blocks.SOFA_lightb) { return 3; }
		if (this == Furniture_Blocks.SOFA_yellow) { return 4; }
		if (this == Furniture_Blocks.SOFA_lime) { return 5; }
		if (this == Furniture_Blocks.SOFA_pink) { return 6; }
		if (this == Furniture_Blocks.SOFA_gray) { return 7; }
		if (this == Furniture_Blocks.SOFA_lightg) { return 8; }
		if (this == Furniture_Blocks.SOFA_cyan) { return 9; }
		if (this == Furniture_Blocks.SOFA_purple) { return 10; }
		if (this == Furniture_Blocks.SOFA_blue) { return 11; }
		if (this == Furniture_Blocks.SOFA_brown) { return 12; }
		if (this == Furniture_Blocks.SOFA_green) { return 13; }
		if (this == Furniture_Blocks.SOFA_red) { return 14; }
		else { return 15; }
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_isu.name"));
	}
}
