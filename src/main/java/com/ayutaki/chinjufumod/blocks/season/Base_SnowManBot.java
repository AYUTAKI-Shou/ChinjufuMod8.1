package com.ayutaki.chinjufumod.blocks.season;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.registry.School_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_SnowManBot extends BaseStage4_Face {

	public Base_SnowManBot(String name) {
		super(name);
		setSoundType(SoundType.SNOW);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}
	
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.SNOW;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.8125D, 0.8125D);
	}

	/* TickRandom */
	private boolean hasHeat(World worldIn, BlockPos pos) {
		for(BlockPos nearpos : BlockPos.getAllInBoxMutable(pos.add(-2, -1, -2), pos.add(2, 1, 2))) {
			IBlockState nearstate = worldIn.getBlockState(nearpos);
			Block nearblock = nearstate.getBlock();

			if (nearblock == Blocks.LAVA || nearblock == Blocks.MAGMA ||
					nearblock instanceof BlockFire || nearblock == Blocks.LIT_FURNACE ||
					nearblock == School_Blocks.LIT_CSTOVE_top || 
					nearblock == Kitchen_Blocks.LIT_KITOVEN || nearblock == Kitchen_Blocks.LIT_KITOVEN_B || 
					nearblock == Kitchen_Blocks.LIT_IRORI || nearblock == Kitchen_Blocks.LIT_KITSTOVE) {
				return true; }
		}
		return false;
	}
	
	@Override
	public void observedNeighborChange(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.observedNeighborChange(state, worldIn, pos, blockIn, pos);
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}
	
	@Override
	public int tickRate(World worldIn) {
		return 200;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		Block downBlock = worldIn.getBlockState(pos.down()).getBlock();

		if (downBlock == Blocks.ICE || downBlock == Blocks.PACKED_ICE || downBlock == Blocks.SNOW) { }
		
		if (downBlock != Blocks.ICE && downBlock != Blocks.PACKED_ICE && downBlock != Blocks.SNOW) { 
			
			if (this.hasHeat(worldIn, pos)) {
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
				worldIn.destroyBlock(pos.up(), false);
				worldIn.destroyBlock(pos, true); }
			
			else { //!hasHeat
				if (worldIn.getBiome(pos).getTemperature(pos) > 0.85F) {
					worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
					worldIn.destroyBlock(pos.up(), false);
					worldIn.destroyBlock(pos, true); }
				
				else { } //<= 0.85F
			}
		}
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
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "shovel";
	}
	
	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		Block upBlock = worldIn.getBlockState(pos.up()).getBlock();
		if (upBlock instanceof Base_SnowManTop) { worldIn.destroyBlock(pos.up(), false); }
	}

	/*Drop Item and Clone Item.*/
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Seasonal.SNOWMAN);
	}
}
