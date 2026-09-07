package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_ConnectHalf;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TengusaWashed extends Base_ConnectHalf {
	/* Property */
	public static final PropertyInteger STAGE_1_5 = PropertyInteger.create("stage", 1, 5);
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.0D, -0.5D, 0.0D, 1.0D, 0.01D, 1.0D);

	public TengusaWashed(String name) {
		super(name);
		setSoundType(SoundType.PLANT);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(STAGE_1_5, Integer.valueOf(1))
				.withProperty(DOWN, Boolean.valueOf(false)));
		
		setTickRandomly(true);
	}
	
	/* RandomTick */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {

		boolean seeSUN = worldIn.canSeeSky(pos.up()) && !worldIn.isRainingAt(pos.up()) && worldIn.isDaytime();
		if (seeSUN) {
			
			int i = ((Integer)state.getValue(STAGE_1_5)).intValue();
			boolean canDRY = worldIn.getLightFromNeighbors(pos.up()) >= 15 && i != 5;
			if (canDRY) {
				if (rand.nextInt(6) == 0) { 
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_5, Integer.valueOf(i + 1))); } }
		}
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_5)).intValue();

		/** Can harvest **/
		if (i == 5) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SHIO, 2, 5);
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return true;
	}

	/* Reaction to Neighboring blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, this.connectHalf(worldIn, pos.down()));
	}

	/* Data value */
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_5)).intValue();
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_1_5, Integer.valueOf(meta));
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (5 - ((Integer)state.getValue(STAGE_1_5)).intValue()) * 2;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, STAGE_1_5 });
	}

	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		return flag? AABB : AABB_DOWN;
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Rendering */
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
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
		int i = ((Integer)state.getValue(STAGE_1_5)).intValue();
		if (i != 5) { stack.add(new ItemStack(Items_Teatime.SHIO, 1, 4)); }
		if (i == 5) { stack.add(new ItemStack(Items_Teatime.SHIO, 2, 5)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.SHIO, 1, 4);
	}
}
