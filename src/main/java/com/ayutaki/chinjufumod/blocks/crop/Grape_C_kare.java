package com.ayutaki.chinjufumod.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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

public class Grape_C_kare extends BaseStage3_Face {
	/** 収穫後1, 枯れ2, 3 -> Bの1 **/
	private static final AxisAlignedBB AABB_1_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.0, 0.8125, 1.5, 1.0);
	private static final AxisAlignedBB AABB_1_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.0, 0.8125, 1.5, 1.0);
	private static final AxisAlignedBB AABB_1_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.0, 0.8125, 1.5, 1.0);
	private static final AxisAlignedBB AABB_1_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.0, 0.8125, 1.5, 1.0);

	private static final AxisAlignedBB AABB_2_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.0, 0.8125, 1.5, 1.0);
	private static final AxisAlignedBB AABB_2_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.0, 0.8125, 1.5, 1.0);
	private static final AxisAlignedBB AABB_2_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.0, 0.8125, 1.5, 1.0);
	private static final AxisAlignedBB AABB_2_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.0, 0.8125, 1.5, 1.0);

	private static final AxisAlignedBB AABB_3_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.40625, 0.0, 0.0, 0.625, 1.4375, 1.0);
	private static final AxisAlignedBB AABB_3_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.40625, 0.0, 0.0, 0.625, 1.4375, 1.0);
	private static final AxisAlignedBB AABB_3_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.40625, 0.0, 0.0, 0.625, 1.4375, 1.0);
	private static final AxisAlignedBB AABB_3_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.40625, 0.0, 0.0, 0.625, 1.4375, 1.0);

	public Grape_C_kare(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setTickRandomly(true);
	}

	/* RandomTick */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
			if (rand.nextInt(8) == 0) {
				
				int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
				if (i == 3) {
					worldIn.setBlockState(pos, Crop_Blocks.BUDOUNOKI_B.getDefaultState()
							.withProperty(H_FACING, state.getValue(H_FACING))
							.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1)));
					worldIn.setBlockState(pos.up(), Crop_Blocks.BUDOUTOP_B.getDefaultState()
							.withProperty(H_FACING, state.getValue(H_FACING))
							.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(1))); }
				
				else { //i != 3
					int i1 = i + 1;
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i1)));
					worldIn.setBlockState(pos.up(), Crop_Blocks.BUDOUTOP_C.getDefaultState()
							.withProperty(H_FACING, state.getValue(H_FACING))
							.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(i1))); } }
		}
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		/** Sleeping **/
		if (i != 3) { CMEvents.textIsSleeping(worldIn, pos, playerIn); }
		
		if (i == 3) {
			if (hItem == Items.DYE && k == 15) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
								
				worldIn.setBlockState(pos, Crop_Blocks.BUDOUNOKI_B.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(2)));
				worldIn.setBlockState(pos.up(), Crop_Blocks.BUDOUTOP_B.getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(2))); }
			
			if ((hItem == Items.DYE && k != 15) || hItem != Items.DYE) {
				if (hStack.isEmpty()) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				else { //!empty
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing direction = state.getValue(H_FACING);
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		switch (direction) {
		case NORTH:
		default:
			return (i == 1)? AABB_1_NORTH : ((i == 2)? AABB_2_NORTH : AABB_3_NORTH);
		case SOUTH:
			return (i == 1)? AABB_1_SOUTH : ((i == 2)? AABB_2_SOUTH : AABB_3_SOUTH);
		case EAST:
			return (i == 1)? AABB_1_EAST : ((i == 2)? AABB_2_EAST : AABB_3_EAST);
		case WEST:
			return (i == 1)? AABB_1_WEST : ((i == 2)? AABB_2_WEST : AABB_3_WEST);
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Change DownBlock. */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
		boolean DIRT = (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockDirt);
		if (!DIRT) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos); }

		else { }
	}

	/* Rendering */
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof GrapeTop_C_kare) {
			worldIn.destroyBlock(pos.up(), false); }
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
		int i = state.getValue(STAGE_1_3).intValue();

		stack.add(new ItemStack(cloneItem(), (i == 3)? 1 : 3, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		return Items_Teatime.BUDOUNOKI;
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
