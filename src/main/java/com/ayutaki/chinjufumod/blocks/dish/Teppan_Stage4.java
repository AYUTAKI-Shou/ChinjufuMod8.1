package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Teppan_Stage4 extends Abstract_Teppan {

	protected static final int COOK_TIME = 700;
	/* Property */
	public static final PropertyInteger STAGE_1_4 = PropertyInteger.create("stage", 1, 4);
	/* Collision */
	protected static final double cw = 0.0625;
	private static final AxisAlignedBB AABB_BOX = new AxisAlignedBB(2.5D * cw, 0.0D * cw, 2.5D * cw, 13.5D * cw, 1.0D * cw, 13.5D * cw);
	
	public Teppan_Stage4(String name) {
		super(name);
		
		setDefaultState(this.blockState.getBaseState()
				.withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(STAGE_1_4, Integer.valueOf(1))
				.withProperty(DOWN, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		/** 1=raw, 2=re, 3=soba, 4=sauce **/
		boolean yakiSoba = (this == Dish_Blocks.YAKISOBA_nama) || (this == Dish_Blocks.YAKISOBASHIO_nama);

		if (yakiSoba && i == 4) {
			if (hItem == Items_Teatime.Item_SARA) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.OKONOMIYAKI, this.takeMeta());
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				
				worldIn.setBlockState(pos, Dish_Blocks.OKONOMIC_nama.getDefaultState()
						.withProperty(Teppan_4empty.H_FACING, state.getValue(H_FACING))
						.withProperty(Teppan_4empty.DOWN, state.getValue(DOWN))
						.withProperty(Teppan_4empty.STAGE_1_4, Integer.valueOf(4))); }
			
			if (hItem != Items_Teatime.Item_SARA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		else { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	private int takeMeta() {
		if (this == Dish_Blocks.YAKISOBA_nama) { return 6; }
		else { return 7; }
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing)state.getValue(H_FACING)).getHorizontalIndex();
		i = i | ((Integer)state.getValue(STAGE_1_4)).intValue() - 1 << 2;
		return i;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(H_FACING, EnumFacing.getHorizontal(meta))
				.withProperty(STAGE_1_4, Integer.valueOf(1 + (meta >> 2)));
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (4 - ((Integer)state.getValue(STAGE_1_4)).intValue()) * 2;
	}

	/*Create BlockStates in this block. */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DOWN, H_FACING, STAGE_1_4 });
	}
	
	/* Cooking */
	@Override
	public void observedNeighborChange(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.observedNeighborChange(state, worldIn, pos, blockIn, pos);
		if (cookingIn(worldIn, pos)) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
		
		else { }
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, COOK_TIME);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		boolean yakiSoba = (this == Dish_Blocks.YAKISOBA_nama) || (this == Dish_Blocks.YAKISOBASHIO_nama);

		if (yakiSoba && i == 4) { }
		
		else { 
			if (cookingIn(worldIn, pos)) {
				worldIn.setBlockState(pos, this.toClick().getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(DOWN, state.getValue(DOWN))
						.withProperty(STAGE_1_4, Integer.valueOf(i)), 3);
				CMEvents.soundKotePlace(worldIn, pos);
				worldIn.scheduleUpdate(pos, this, COOK_TIME); }
			
			else { }
		}
	}
	
	private Block toClick() {
		if (this == Dish_Blocks.OKONOMISOBA_nama) { return Dish_Blocks.OKONOMISOBA_click; }
		if (this == Dish_Blocks.OKONOMISOBAS_nama) { return Dish_Blocks.OKONOMISOBAS_click; }
		if (this == Dish_Blocks.OKONOMISOBAC_nama) { return Dish_Blocks.OKONOMISOBAC_click; }
		if (this == Dish_Blocks.YAKISOBA_nama) { return Dish_Blocks.YAKISOBA_click; }
		else { return Dish_Blocks.YAKISOBASHIO_click; }
	}
	
	/* add Effect */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		World par1World = worldIn;
		int par2 = x;
		int par3 = y;
		int par4 = z;
		Random par5Random = rand;

		double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
		double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
		double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
		double d3 = 0.12D;
		double d4 = 0.17D;
		
		if (cookingIn(worldIn, pos)) {
			int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
			
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(x, y, z, SoundEvents_CM.JUU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
			
			if (i != 1) {
				for (int la = 0; la < 1; ++la) {
					par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D); }
			}
		}
		
		else { }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_BOX;
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Teatime.FRYPAN_NAMA_4, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.FRYPAN_NAMA_4, 1, cloneMeta());
	}

	private int cloneMeta() {
		if (this == Dish_Blocks.OKONOMISOBA_nama) { return 5; }
		if (this == Dish_Blocks.OKONOMISOBAS_nama) { return 6; }
		if (this == Dish_Blocks.OKONOMISOBAC_nama) { return 7; }
		if (this == Dish_Blocks.YAKISOBA_nama) { return 8; }
		else { return 9; }
	}
}
