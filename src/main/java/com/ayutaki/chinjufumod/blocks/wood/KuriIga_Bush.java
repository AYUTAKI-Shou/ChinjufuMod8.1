package com.ayutaki.chinjufumod.blocks.wood;

import java.util.Random;

import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KuriIga_Bush extends Abstract_Bush {
	/* Collision */
	private static final AxisAlignedBB AABB_BOX = new AxisAlignedBB(0.35D, -0.0625D, 0.35D, 0.65D, 0.25D, 0.65D);
	
	public KuriIga_Bush(String name) {
		super(name);
		setSoundType(SoundType.SNOW);
		setHardness(0.1F);
		setResistance(0.1F);
		setLightOpacity(0);
	}

 @Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		for (BlockPos.MutableBlockPos nearpos : BlockPos.getAllInBoxMutable(pos.add(-2, 0, -2), pos.add(2, 2, 2))) {
			if (worldIn.getBlockState(nearpos).getBlock() == Seasonal_Blocks.OAKKARE_log) {
				return this.canSustainBush(worldIn.getBlockState(pos.down()));
			}
		}
		return false;
	}

 @Override
 protected boolean canSustainBush(IBlockState state) {
 	return state.getBlock() instanceof BlockGrass || state.getBlock() instanceof BlockDirt || 
 			state.getBlock() instanceof BlockFarmland || state.getBlock() instanceof FallLeaf;
 }
 
	/* Collisions for each property. */
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.XZ;
	}
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_BOX.offset(state.getOffset(source, pos));
	}

	/*Drop Item and Clone Item.*/
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Items_Seasonal.KURI_IGA, 1, 0);
	}

	public int quantityDropped(Random random) {
		return MathHelper.getInt(random, 1, 2);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items_Seasonal.KURI;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Seasonal.KURI_IGA, 1, 0);
	}
}
