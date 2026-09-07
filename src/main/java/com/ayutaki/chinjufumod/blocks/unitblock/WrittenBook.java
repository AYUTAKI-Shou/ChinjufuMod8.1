package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public class WrittenBook extends BaseStage4_FaceDown {
	/* Collision */
	private static final AxisAlignedBB AABB_1S = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.3125, 0.75, 0.09375, 0.6875);
	private static final AxisAlignedBB AABB_1W = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.3125, 0.75, 0.09375, 0.6875);
	private static final AxisAlignedBB AABB_1N = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.3125, 0.75, 0.09375, 0.6875);
	private static final AxisAlignedBB AABB_1E = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.3125, 0.75, 0.09375, 0.6875);

	private static final AxisAlignedBB AABB_2S = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.3125, 0.75, 0.1875, 0.6875);
	private static final AxisAlignedBB AABB_2W = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.3125, 0.75, 0.1875, 0.6875);
	private static final AxisAlignedBB AABB_2N = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.3125, 0.75, 0.1875, 0.6875);
	private static final AxisAlignedBB AABB_2E = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.3125, 0.75, 0.1875, 0.6875);
	
	private static final AxisAlignedBB AABB_3S = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.3125, 0.75, 0.28125, 0.6875);
	private static final AxisAlignedBB AABB_3W = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.3125, 0.75, 0.28125, 0.6875);
	private static final AxisAlignedBB AABB_3N = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.3125, 0.75, 0.28125, 0.6875);
	private static final AxisAlignedBB AABB_3E = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.3125, 0.75, 0.28125, 0.6875);
	
	private static final AxisAlignedBB AABB_4S = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.3125, 0.75, 0.375, 0.6875);
	private static final AxisAlignedBB AABB_4W = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.3125, 0.75, 0.375, 0.6875);
	private static final AxisAlignedBB AABB_4N = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.3125, 0.75, 0.375, 0.6875);
	private static final AxisAlignedBB AABB_4E = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.3125, 0.75, 0.375, 0.6875);
	
	private static final AxisAlignedBB DOWN_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, -0.5, 0.3125, 0.75, 0.01, 0.6875);
	private static final AxisAlignedBB DOWN_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, -0.5, 0.3125, 0.75, 0.01, 0.6875);
	private static final AxisAlignedBB DOWN_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, -0.5, 0.3125, 0.75, 0.01, 0.6875);
	private static final AxisAlignedBB DOWN_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, -0.5, 0.3125, 0.75, 0.01, 0.6875);
	
	public WrittenBook(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(0.5F);
		setResistance(0.5F);
		setLightOpacity(0);
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
	
		switch (direction) {
		case NORTH:
		default:
			return flag? ((i == 1)? AABB_1N : ((i == 2)? AABB_2N : ((i == 3)? AABB_3N : AABB_4N))) : DOWN_NORTH;
		case SOUTH:
			return flag? ((i == 1)? AABB_1S : ((i == 2)? AABB_2S : ((i == 3)? AABB_3S : AABB_4S))) : DOWN_SOUTH;
		case EAST:
			return flag? ((i == 1)? AABB_1E : ((i == 2)? AABB_2E : ((i == 3)? AABB_3E : AABB_4E))) : DOWN_EAST;
		case WEST:
			return flag? ((i == 1)? AABB_1W : ((i == 2)? AABB_2W : ((i == 3)? AABB_3W : AABB_4W))) : DOWN_WEST;
		}
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public int damageDropped(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_4)).intValue();
	}
	 
	@Override
	 public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
		return true;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (worldIn instanceof World) {
			LootTable table = null;
			switch (i) {
			case 1 :
				table = ((World) worldIn).getLootTableManager()
						.getLootTableFromLocation(new ResourceLocation(ChinjufuMod.MOD_ID, "blocks/block_written_book")); 
			break;
				
			case 2 :
				table = ((World) worldIn).getLootTableManager()
						.getLootTableFromLocation(new ResourceLocation(ChinjufuMod.MOD_ID, "blocks/block_written_book2")); 
				break;
				
			case 3 :
				table = ((World) worldIn).getLootTableManager()
						.getLootTableFromLocation(new ResourceLocation(ChinjufuMod.MOD_ID, "blocks/block_written_book3")); 
				break;
				
			case 4 :
				table = ((World) worldIn).getLootTableManager()
						.getLootTableFromLocation(new ResourceLocation(ChinjufuMod.MOD_ID, "blocks/block_written_book4")); 
				break;
				
			default : return; } // switch
			
			LootContext context = new LootContext.Builder(((WorldServer) worldIn)).build();
			drops.addAll(table.generateLootForPools(((World) worldIn).rand, context));
		}
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items.BOOK, 1, 0);
	}
}
