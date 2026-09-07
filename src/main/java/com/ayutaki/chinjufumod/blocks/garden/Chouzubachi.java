package com.ayutaki.chinjufumod.blocks.garden;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
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

public class Chouzubachi extends Regi_addState {
	/* Property */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);
	/** 石=空0,1,2,3 GRA=空4,5,6,7 DIO=空8,9,10,11 AND=空12,13,14,15 空スタートで確定 **/
	
	public Chouzubachi(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_0_15, Integer.valueOf(0)));
		
		setTickRandomly(true);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.STONE;
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		/** 石=空0,1,2,3 GRA=空4,5,6,7 DIO=空8,9,10,11 AND=空12,13,14,15 **/
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		
		if (i >= 0 && i <= 3) {
			if (i != 0) {
				if (hStack.isEmpty() && hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full) {
					CMEvents.soundWaterUse(worldIn, pos);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i - 1)), 3); }
				
				if (!hStack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); } }
			
			if (i != 3) {
				if (hItem == Items.WATER_BUCKET) {
					CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(3))); }
				
				if (hItem == Items_Teatime.MIZUOKE_full) {
					CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(3))); }
				
				if (i == 0 && hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); } }
		}
		
		if (i >= 4 && i <= 7) {
			if (i != 4) {
				if (hStack.isEmpty() && hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full) {
					CMEvents.soundWaterUse(worldIn, pos);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i - 1)), 3); }
				
				if (!hStack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); } }
			
			if (i != 7) {
				if (hItem == Items.WATER_BUCKET) {
					CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(7))); }
				
				if (hItem == Items_Teatime.MIZUOKE_full) {
					CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(7))); }
				
				if (i == 4 && hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); } }
		}
		
		
		if (i >= 8 && i <= 11) {
			if (i != 8) {
				if (hStack.isEmpty() && hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full) {
					CMEvents.soundWaterUse(worldIn, pos);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i - 1)), 3); }
				
				if (!hStack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); } }
			
			if (i != 11) {
				if (hItem == Items.WATER_BUCKET) {
					CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(11))); }
				
				if (hItem == Items_Teatime.MIZUOKE_full) {
					CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(11))); }
				
				if (i == 8 && hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); } }
		}
		
		if (i >= 12 && i <= 15) {
			if (i != 12) {
				if (hStack.isEmpty() && hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full) {
					CMEvents.soundWaterUse(worldIn, pos);
					worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i - 1)), 3); }
				
				if (!hStack.isEmpty()) { CMEvents.textFullItem(worldIn, pos, playerIn); } }
			
			if (i != 15) {
				if (hItem == Items.WATER_BUCKET) {
					CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(15))); }
				
				if (hItem == Items_Teatime.MIZUOKE_full) {
					CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(15))); }
				
				if (i == 12 && hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); } }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* RandomTick */
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		/** 石=空0,1,2,3 GRA=空4,5,6,7 DIO=空8,9,10,11 AND=空12,13,14,15 **/
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		boolean full = (i == 3 || i ==7 || i == 11 || i == 15);
		if (worldIn.isRainingAt(pos.up()) && !full) {
			worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 1))); }
		
		else { }
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.625D, 0.9375D);
	}

	/* Data value */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_0_15 });
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (15 - ((Integer)state.getValue(STAGE_0_15)).intValue()) * 2;
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

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
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
		/** 石=空0,1,2,3 GRA=空4,5,6,7 DIO=空8,9,10,11 AND=空12,13,14,15 **/
		
		if (i <= 3) { return new ItemStack(Items_Wadeco.CHOUZUBACHI, 1, 0); }
		if (i >= 4 && i <= 7) { return new ItemStack(Items_Wadeco.CHOUZUBACHI, 1, 1); }
		if (i >= 8 && i <= 11) { return new ItemStack(Items_Wadeco.CHOUZUBACHI, 1, 2); }
		else { return new ItemStack(Items_Wadeco.CHOUZUBACHI, 1, 3); }
	}
}
