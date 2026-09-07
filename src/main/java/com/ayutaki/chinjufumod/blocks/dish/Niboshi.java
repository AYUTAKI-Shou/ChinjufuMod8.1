package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class Niboshi extends BaseStage3_Face {

	protected static final int COOK_TIME = 3000;
	
	public Niboshi(String name) {
		super(name);
		setSoundType(SoundType.SNOW);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* Cooking */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 1) { worldIn.scheduleUpdate(pos, this, COOK_TIME); }
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 1) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			worldIn.setBlockState(pos, this.getDefaultState()
					.withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(STAGE_1_3, Integer.valueOf(2))); }

		else { }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 2) {
			if (hItem == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.DASHI_bot_14, 0);
				worldIn.setBlockState(pos, this.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(STAGE_1_3, Integer.valueOf(3))); }
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (i == 3) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items.PAPER, 0);
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState()); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 3) { return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.00625D, 0.8125D); }
		return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.0625D, 0.8125D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 3) { stack.add(new ItemStack(Items.PAPER, 1, 0)); }
		else { stack.add(cloneStack()); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.NIBOSHI, 1, 0);
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
