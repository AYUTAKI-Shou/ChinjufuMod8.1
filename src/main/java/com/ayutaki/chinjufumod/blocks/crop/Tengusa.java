package com.ayutaki.chinjufumod.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.teatime.Shio_TT;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

public class Tengusa extends Regi_addState {
	/* Property */
	public static final PropertyInteger STAGE_0_7 = PropertyInteger.create("stage", 0, 7);

	public Tengusa(String name) {
		super(name);
		setSoundType(SoundType.PLANT);
		setHardness(0.0F);
		setResistance(1.0F);
		setLightOpacity(0);
		
		setTickRandomly(true);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		
		int i = ((Integer)state.getValue(STAGE_0_7)).intValue();

		if (i != 7) { //i != 7
			if (hItem == Items.DYE && k == 15) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
				
				if (i < 6) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_7, Integer.valueOf(i + 2))); }
				if (i == 6) { worldIn.setBlockState(pos, state.withProperty(STAGE_0_7, Integer.valueOf(7))); } 
			}
		}

		/** SUCCESS to not put anything on top. **/
		if (hItem instanceof Shio_TT) { return false; }
		return true;
	}
	
	/* RandomTick */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_0_7)).intValue();

		if (i != 7 && worldIn.getLightFromNeighbors(pos.up()) >= 9) {
			if (rand.nextInt(8) == 0) { 
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_7, Integer.valueOf(i + 1))); } }
		
		else { }
	}

	/* Change DownBlock. */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
		IBlockState downState1 = worldIn.getBlockState(pos.down());
		IBlockState downState2 = worldIn.getBlockState(pos.down(2));
		boolean WATER = (downState1.getBlock() == Blocks.WATER);
		boolean SAND2 = (downState2.getBlock() instanceof BlockSand) || (downState2.getBlock() instanceof BlockGravel);
		
		if (!WATER || !SAND2) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos); }

		else { }
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState downState1 = worldIn.getBlockState(pos.down());
		IBlockState downState2 = worldIn.getBlockState(pos.down(2));
		boolean WATER = (downState1.getBlock() == Blocks.WATER);
		boolean SAND2 = (downState2.getBlock() instanceof BlockSand) || (downState2.getBlock() instanceof BlockGravel);
		return WATER && SAND2;
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0D, -1.0D, 0.0D, 1.0D, 0.25D, 1.0D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_7, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_7)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_0_7 });
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (7 - ((Integer)state.getValue(STAGE_0_7)).intValue()) * 2;
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
		int i = ((Integer)state.getValue(STAGE_0_7)).intValue();
		
		int amount = (i >= 6)? 3 : ((i == 5)? 2 : 1);
		stack.add(new ItemStack(cloneItem(), amount, 3));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 3);
	}

	private Item cloneItem() {
		return Items_Teatime.SHIO;
	}
}
