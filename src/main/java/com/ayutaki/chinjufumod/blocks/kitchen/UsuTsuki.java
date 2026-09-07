package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class UsuTsuki extends Regi_addState {
	/* Property 3=empty, kome=4k-5k-6k-7m-8m-9m-10m-11m-12m-13m-14m-15m=comp */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);

	/* Collision */
	private static final double cw = 0.0625;
	private static final AxisAlignedBB AABB_0 = new AxisAlignedBB(1.0D * cw, 0.0D * cw, 1.0D * cw, 15.0D * cw, 14.0D * cw, 15.0D * cw);
	private static final AxisAlignedBB AABB_1 = new AxisAlignedBB(2.0D * cw, 0.0D * cw, 2.0D * cw, 14.0D * cw, 12.0D * cw, 14.0D * cw);
	
	public UsuTsuki(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_0_15, Integer.valueOf(3)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();

		if (hItem == Items_Teatime.MUSHIGOME && k == 0) {
			if (i == 3) {
				CMEvents.mode1Through_Consume(playerIn, hand, Items.BOWL, 0);
				worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(4)), 3); }
			
			else { } //i != 3
		}
		
		if (hItem == Items_Teatime.KINE_YOKO && i >= 4 && i <= 15) { return false; }
		
		if (hItem == Items_Wadeco.NOMI && i <= 2) { return false; }
		
		if (hStack.isEmpty()) {
			if (i == 15) { 
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.MOCHI, 0);
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(3)), 3); }
			
			else { } //i != 15
		}
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* BlockState when it was placed. .getOpposite() = add 180.*/
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(3));
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		return (i == 0)? AABB_0 : AABB_1;
	}

	/* Data value */
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (16 - ((Integer)state.getValue(STAGE_0_15)).intValue()) * 2;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_0_15 });
	}

	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
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
		stack.add(cloneStack(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}

	private ItemStack cloneStack(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		if (i >= 3) { return new ItemStack(Items_Teatime.USU_TSUKI, 1, 0); }
		else { return new ItemStack(Item.getItemFromBlock(Blocks.LOG), 1, 0); }
	}
}
