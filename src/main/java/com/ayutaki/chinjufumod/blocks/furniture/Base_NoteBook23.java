package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_NoteBook23 extends BaseStage4_FaceDown {

	public Base_NoteBook23(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(0.5F);
		setResistance(0.5F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		
		if (hItem == Items_Chinjufu.SHOUHOU_empty) {
			if (i != 4) { 
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)));
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand); 
				return true; }
		
			else { return false; }
		}
		
		if (hStack.isEmpty()) {
			CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Chinjufu.SHOUHOU_empty, 0);
			
			if (i == 1) { worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3); }
			else { //i != 1
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 1))); }
			return true;
		}
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
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
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		stack.add(new ItemStack(Items_Chinjufu.SHOUHOU_empty, i, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.SHOUHOU_empty, 1, 0);
	}
}
