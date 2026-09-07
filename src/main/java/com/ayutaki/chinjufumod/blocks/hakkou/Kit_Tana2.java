package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Kit_Tana2 extends BaseFacingSapo {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public Kit_Tana2(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(10.0F);
		setLightOpacity(1);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		boolean bottle = (hItem == Items_Teatime.NAMASAKEBOT || hItem == Items_Teatime.SAKEBOT || hItem == Items_Teatime.JUKUSAKEBOT ||
				hItem == Items_Teatime.CIDERBOT || hItem == Items_Teatime.JUKUCIDERBOT ||
				hItem == Items_Teatime.WINEBOT || hItem == Items_Teatime.JUKUWINEBOT ||
				hItem == Items_Teatime.MEADBOT || hItem == Items_Teatime.JUKUMEADBOT);
		
		if (bottle) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			CMEvents.soundSAKEBottleFill(worldIn, pos);
			worldIn.setBlockState(pos, takeBlock(hItem).getDefaultState()
					.withProperty(BaseKit_TanaWine.H_FACING, state.getValue(H_FACING))
					.withProperty(BaseKit_TanaWine.STAGE_1_4, Integer.valueOf(1))); }

		else { //!bottle
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Block takeBlock(Item hItem) {
		if (hItem == Items_Teatime.NAMASAKEBOT) { return Kitchen_Blocks.KIT_SAKENAMA; }
		if (hItem == Items_Teatime.SAKEBOT) { return Kitchen_Blocks.KIT_SAKE; }
		if (hItem == Items_Teatime.JUKUSAKEBOT) { return Kitchen_Blocks.KIT_SAKEJUKU; }
		if (hItem == Items_Teatime.CIDERBOT) { return Kitchen_Blocks.KIT_CIDER; }
		if (hItem == Items_Teatime.JUKUCIDERBOT) { return Kitchen_Blocks.KIT_CIDERJUKU; }
		if (hItem == Items_Teatime.WINEBOT) { return Kitchen_Blocks.KIT_WINE; }
		if (hItem == Items_Teatime.JUKUWINEBOT) { return Kitchen_Blocks.KIT_WINEJUKU; }
		if (hItem == Items_Teatime.MEADBOT) { return Kitchen_Blocks.KIT_MEAD; }
		else { return Kitchen_Blocks.KIT_MEADJUKU; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes,
			@Nullable Entity entityIn, boolean t_f) {
		EnumFacing facing = state.getValue(H_FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB[facing.getHorizontalIndex()]);
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
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
		return "axe";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_kit2_tana.name"));
	}
}
