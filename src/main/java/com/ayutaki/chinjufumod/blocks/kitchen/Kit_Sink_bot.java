package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Kit_Sink_bot extends BaseStage2_Face {

	public Kit_Sink_bot(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(10.0F);
		setLightOpacity(1);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (i != 1) {
			/*水を止める*/
			if (hStack.isEmpty()) {
				worldIn.playSound(null, pos, SoundEvents_CM.WATER_STOP, SoundCategory.BLOCKS, 1.0F, 1.0F);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_2, Integer.valueOf(1)));
				worldIn.setBlockState(pos.up(), Kitchen_Blocks.KIT_SINK_TOP.getDefaultState()
						.withProperty(Kit_Sink_top.H_FACING, state.getValue(H_FACING))
						.withProperty(Kit_Sink_top.STAGE_1_2, Integer.valueOf(1)));
				return true; }

			/*水を汲む*/
			if (hItem == Items.BUCKET) {
				CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items.WATER_BUCKET, 0);
				return true; }

			/*大釜(Cauldron)から引用*/
			if (hItem == Items.GLASS_BOTTLE) {
				CMEvents.Bottle_toWaterBottle(worldIn, pos, playerIn, hand);
				return true; }

			/* TTimeItems */
			if (hItem == Items_Teatime.MIZUOKE) {
				CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.MIZUOKE_full, 0);
				return true; }

			if (hItem == Items_Teatime.Item_YAKAN_kara) {
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.KETTLE_full, 0);
				return true; }

			if (hItem == Items_Teatime.ZUNDOU) {
				CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.ZUNDOU_MIZU, 0);
				return true; }

			if (hItem == Items_Teatime.KEIRYO_CUP) {
				CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.KEIRYO_CUP_full, 0);
				return true; }

			/* シンク台は真水のため除外 */
			if (hItem == Items_Teatime.NABE_kara) { 
				CMEvents.textNotHave(worldIn, pos, playerIn);
				return true; }
		}
		
		if (i == 1) {
			if (hStack.isEmpty()) {
				if (worldIn.getBlockState(new BlockPos(x, y - 2, z)).getBlock() == Blocks.WATER) {
					worldIn.playSound(null, pos, SoundEvents_CM.WATER_START, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_2, Integer.valueOf(2)));
					worldIn.setBlockState(pos.up(), Kitchen_Blocks.KIT_SINK_TOP.getDefaultState()
							.withProperty(Kit_Sink_top.H_FACING, state.getValue(H_FACING))
							.withProperty(Kit_Sink_top.STAGE_1_2, Integer.valueOf(2))); }
				
				if (worldIn.getBlockState(new BlockPos(x, y - 2, z)).getBlock() != Blocks.WATER) {
					CMEvents.soundTouchBlock(worldIn, pos); } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
			
			return true;
		}
		/* 1.15.2 に合わせて false */
		return false;
	}


	/*2ブロック下が水ブロックではなくなると, 水が止まる*/
	@Override
	public void observedNeighborChange(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.observedNeighborChange(state, worldIn, pos, blockIn, pos);
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public int tickRate(World worldIn) {
		return 20;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();

		/*「!=」がノットイコール*/
		if (i != 1) {
			if (worldIn.getBlockState(new BlockPos(x, y - 2, z)).getBlock() != Blocks.WATER) {
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_2, Integer.valueOf(1)));
				worldIn.setBlockState(pos.up(), Kitchen_Blocks.KIT_SINK_TOP.getDefaultState()
						.withProperty(Kit_Sink_top.H_FACING, state.getValue(H_FACING))
						.withProperty(Kit_Sink_top.STAGE_1_2, Integer.valueOf(1))); }
		}

		if (i == 1) { }
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

	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof Kit_Sink_top) {
			worldIn.destroyBlock(pos.up(), false);
		}
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
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.KIT_SINK1, 1, 0);
	}
}
