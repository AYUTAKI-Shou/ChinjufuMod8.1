package com.ayutaki.chinjufumod.blocks.wood;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class Abstract_Leaf extends BlockLeaves {

	public static final PropertyBool DECAYABLE = PropertyBool.create("decayable");
	protected boolean leavesFancy;

	public Abstract_Leaf(String name) {
		super();
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);
		setCreativeTab(ChinjufuModTabs.SEASONAL);

		setSoundType(SoundType.PLANT);
		setHardness(0.1F);
		setResistance(0.1F);
		setLightOpacity(1);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (this == Seasonal_Blocks.SAKURA_flow) { return MapColor.PINK; }
		if (this == Seasonal_Blocks.KAEDE_leaf) { return MapColor.RED; }
		if (this == Seasonal_Blocks.ICHOH_leaf) { return MapColor.YELLOW; }
		else { return MapColor.BROWN; }
	}

	/* Abstract */
	@Override
	protected abstract BlockStateContainer createBlockState();
	
	@Override
	protected abstract ItemStack getSilkTouchDrop(IBlockState state);
	
	public abstract NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess worldIn, BlockPos pos, int fortune);
	
	@Override
	public abstract ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn);
	
	@Override
	public abstract java.util.List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune);
	

	/* ドロップアイテムに適用するメタData value */
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	/* ハサミで回収できるようにする */
	@Override
	public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
		if (!worldIn.isRemote && stack.getItem() == Items.SHEARS) {
			playerIn.addStat(StatList.getBlockStats(this)); }
		
		else { super.harvestBlock(worldIn, playerIn, pos, state, te, stack); }
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) { 
		return true;
	}

	@Override 
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) { return true; }
	
	/* Render */
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

	/* プレイヤーが設置したときは消失しない */
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (placer != null) {
			worldIn.setBlockState(pos, state.withProperty(DECAYABLE, Boolean.valueOf(false)), 4);
		}
	}
}
