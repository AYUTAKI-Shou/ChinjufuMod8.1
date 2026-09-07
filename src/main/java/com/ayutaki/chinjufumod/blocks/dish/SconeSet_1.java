package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SconeSet_1 extends BaseStage4_Face {

	public SconeSet_1(String name) {
		super(name);
		setSoundType(SoundType.METAL);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hStack.isEmpty()) {
			CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, takeItem(state), 0);

			if (i == 4) {
				worldIn.setBlockState(pos, Dish_Blocks.SCONESET_a.getDefaultState()
						.withProperty(SconeSet_a.H_FACING, state.getValue(H_FACING))
						.withProperty(SconeSet_a.STAGE_1_4, Integer.valueOf(1))); }
			else { //i != 4
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
		}
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Item takeItem(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return Items_Teatime.EGGSAND; }
		if (i == 2) { return Items_Teatime.CHICKENSAND; }
		if (i == 3) { return Items_Teatime.EGGSAND; }
		else { return Items_Teatime.CHICKENSAND; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.65625D, 0.8125D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) {
			stack.add(new ItemStack(Items_Teatime.SCONESET_1, 1, 0)); }

		if (i == 2) {
			stack.add(new ItemStack(Items_Teatime.SCONESET_kara, 1, 0));
			stack.add(new ItemStack(Items_Teatime.CAKE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.SCONE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.CHICKENSAND, 2, 0));
			stack.add(new ItemStack(Items_Teatime.EGGSAND, 1, 0)); }

		if (i == 3) {
			stack.add(new ItemStack(Items_Teatime.SCONESET_kara, 1, 0));
			stack.add(new ItemStack(Items_Teatime.CAKE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.SCONE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.CHICKENSAND, 1, 0));
			stack.add(new ItemStack(Items_Teatime.EGGSAND, 1, 0)); }

		if (i == 4) {
			stack.add(new ItemStack(Items_Teatime.SCONESET_kara, 1, 0));
			stack.add(new ItemStack(Items_Teatime.CAKE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.SCONE, 2, 0));
			stack.add(new ItemStack(Items_Teatime.CHICKENSAND, 1, 0)); }
			return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.SCONESET_1, 1, 0);
	}
}
