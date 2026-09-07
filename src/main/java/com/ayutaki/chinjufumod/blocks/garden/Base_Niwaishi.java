package com.ayutaki.chinjufumod.blocks.garden;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_Niwaishi extends Regi_addState {
	/* Property */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);

	public Base_Niwaishi(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(10.0F);
		setLightOpacity(1);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_0_15, Integer.valueOf(0)));
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
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (hStack.isEmpty()) {
			if (playerIn.isSneaking()) {
				if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 12|| i == 14) {
					CMEvents.soundStonePlace(worldIn, pos);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i + 1)), 3); }
				
				if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11 || i == 13|| i == 15) {
					CMEvents.soundStonePlace(worldIn, pos);
					worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i - 1)), 3); } }
		}
		
		else { //!empty()
			if (hItem == Items_Wadeco.NOMI) { return false;}
			
			if (hItem != Items_Wadeco.NOMI) { }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

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
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		return (i == 0 || i == 1)? true : false;
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
}
