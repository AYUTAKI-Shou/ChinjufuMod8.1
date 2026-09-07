package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public class WrittenMakimono5 extends Base_ConnectHalf {
	/* Property */
	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool DOWN = PropertyBool.create("down");

	private static final AxisAlignedBB AABB_1S = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.265625, 0.75, 0.3125, 0.734375);
	private static final AxisAlignedBB AABB_1W = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.265625, 0.75, 0.3125, 0.734375);
	private static final AxisAlignedBB AABB_1N = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.265625, 0.75, 0.3125, 0.734375);
	private static final AxisAlignedBB AABB_1E = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.265625, 0.75, 0.3125, 0.734375);
	
	private static final AxisAlignedBB DOWN_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, -0.5, 0.265625, 0.75, 0.01, 0.734375);
	private static final AxisAlignedBB DOWN_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, -0.5, 0.265625, 0.75, 0.01, 0.734375);
	private static final AxisAlignedBB DOWN_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, -0.5, 0.265625, 0.75, 0.01, 0.734375);
	private static final AxisAlignedBB DOWN_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, -0.5, 0.265625, 0.75, 0.01, 0.734375);
	
	public WrittenMakimono5(String name) {
		super(name);

		setSoundType(SoundType.WOOD);
		setHardness(0.5F);
		setResistance(0.5F);
		setLightOpacity(0);
		
		setDefaultState(this.blockState.getBaseState()
				.withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, Boolean.valueOf(this.connectHalf(worldIn, pos.down())));
	}

	/* BlockState when it was placed. */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(H_FACING, rot.rotate((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(H_FACING)).getHorizontalIndex();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta));
	}

	/*Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, H_FACING });
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return flag? AABB_1N : DOWN_NORTH;
		case SOUTH:
			return flag? AABB_1S : DOWN_SOUTH;
		case EAST:
			return flag? AABB_1E : DOWN_EAST;
		case WEST:
			return flag? AABB_1W : DOWN_WEST;
		}
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		if (worldIn instanceof World) {
			LootTable table = null;
			table = ((World) worldIn).getLootTableManager()
					.getLootTableFromLocation(new ResourceLocation(ChinjufuMod.MOD_ID, "blocks/block_written_book5")); 
			
			LootContext context = new LootContext.Builder(((WorldServer) worldIn)).build();
			drops.addAll(table.generateLootForPools(((World) worldIn).rand, context)); }
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items.BOOK, 1, 0);
	}
}
