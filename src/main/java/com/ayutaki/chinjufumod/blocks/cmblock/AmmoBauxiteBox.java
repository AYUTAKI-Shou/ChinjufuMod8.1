package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AmmoBauxiteBox extends BaseStage2_Face {
	/** 1=弾薬, 2=ボーキサイト **/
	public AmmoBauxiteBox(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(2);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);

		if (hStack.isEmpty()) {
			int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
			Item takeItem = (i == 1)? Items_Weapon.AMMUNITION_L : Items_Chinjufu.BAUXITE;
			
			CMEvents.emptyTake_NItem(worldIn, pos, playerIn, takeItem, 8, 0);
			worldIn.setBlockState(pos, Chinjufu_Blocks.EMPTY_BOX.getDefaultState()); }
		
		return true;
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
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		
		if (i == 2) { return new ItemStack(Items_Chinjufu.AMUBAUX, 1, 2); }
		else { return new ItemStack(Items_Chinjufu.AMUBAUX, 1, 1); }
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		if (i == 1) {
			stack.add(new ItemStack(Items_Chinjufu.EMPTY_BOX, 1, 0));
			stack.add(new ItemStack(Items_Weapon.AMMUNITION_L, 8, 0)); }

		if (i == 2) {
			stack.add(new ItemStack(Items_Chinjufu.EMPTY_BOX, 1, 0));
			stack.add(new ItemStack(Items_Chinjufu.BAUXITE, 8, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		
		if (i == 2) { return new ItemStack(Items_Chinjufu.AMUBAUX, 1, 2); }
		else { return new ItemStack(Items_Chinjufu.AMUBAUX, 1, 1); }
	}
}
