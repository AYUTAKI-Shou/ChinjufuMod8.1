package com.ayutaki.chinjufumod.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NoriAmi extends Regi_addState {

	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 7);

	public NoriAmi(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(1.0F);
		setLightOpacity(0);

		setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		
		setTickRandomly(true);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(AGE)).intValue();

		/** Too early to collect **/
		if (i < 5) {
			if (hStack.isEmpty()) {
				CMEvents.textEarlyCollect(worldIn, pos, playerIn);
				return true; }
			
			else { return false; } //!empty
		}
		
		/** Can harvest **/
		if (i >= 5) {
			if (hStack.isEmpty()) {
				if (i == 5 || i == 6) { 
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.NORI_N, 0); }
				if (i == 7) {
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.NORI_N, 2, 0); }
				
				worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(0))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
			return true;
		}

		/** 側面で設置可能にするため **/
		if (hItem instanceof ItemBlock) { return false; }
		return true;
	}

	/* RandomTick */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }
		int i = ((Integer)state.getValue(AGE)).intValue();

		if (i != 7 && worldIn.getBlockState(pos.down()).getBlock() == Blocks.WATER && worldIn.getLightFromNeighbors(pos.up()) >= 9) {
			if (rand.nextInt(6) == 0) {
				worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1))); } }

		else { }
	}

	/* Change DownBlock. */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
		if (worldIn.getBlockState(pos.down()).getBlock() != Blocks.WATER) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos); }

		else { }
	}

	/* A place where you can put it. */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getBlock() == Blocks.WATER;
	}

	/* Data value */
	protected PropertyInteger getAgeProperty() {
		return AGE;
	}

	public int getMaxAge() {
		return 7;
	}

	protected int getAge(IBlockState state) {
		return ((Integer)state.getValue(this.getAgeProperty())).intValue();
	}

	public IBlockState withAge(int age) {
		return this.getDefaultState().withProperty(this.getAgeProperty(), Integer.valueOf(age));
	}

	public boolean isMaxAge(IBlockState state) {
		return ((Integer)state.getValue(this.getAgeProperty())).intValue() >= this.getMaxAge();
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.withAge(meta);
	}

	public int getMetaFromState(IBlockState state) {
		return this.getAge(state);
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AGE });
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (7 - ((Integer)state.getValue(AGE)).intValue()) * 2;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0D, -5.0D, 0.0D, 1.0D, 1.0D, 1.0D);
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
	
	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
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
		stack.add(new ItemStack(Items_Teatime.NORIAMI, 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.NORIAMI, 1, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_noriami.name"));
	}
}
