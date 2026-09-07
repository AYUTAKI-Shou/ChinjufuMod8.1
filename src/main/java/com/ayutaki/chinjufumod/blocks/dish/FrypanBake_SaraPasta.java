package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FrypanBake_SaraPasta extends BaseStage4_Face {
	/**1=玉子焼き, 2=ハンバーグ, 3=トマトソース, 4=きのこソース**/
	public FrypanBake_SaraPasta(String name) {
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
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		/**1=玉子焼き, 2=ハンバーグ, 3=トマトソース, 4=きのこソース**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1 || i == 2) {
			if (hItem == Items_Teatime.Item_SARA) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takeSara(state), 0);
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				
				worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState()
						.withProperty(Frypan_kara.H_FACING, state.getValue(H_FACING))); }
			
			if (hItem != Items_Teatime.Item_SARA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (i == 3 || i == 4) {
			if (hItem == Items_Teatime.PASTA && k == 2) {
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takePasta(state), 0);
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				
				worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState()
						.withProperty(Frypan_kara.H_FACING, state.getValue(H_FACING))); }
			
			if (hItem != Items_Teatime.PASTA || k != 2) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Item takeSara(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return Items_Teatime.TAMAGOYAKI; }
		else { return Items_Teatime.EGGBURG; }
	}
	
	private Item takePasta(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 3) { return Items_Teatime.PASTATOMATO; }
		else { return Items_Teatime.PASTAKINOKO; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
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
		stack.add(new ItemStack(Items_Teatime.FRYPAN_BAKE_1, 1, cloneMeta(state)));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.FRYPAN_BAKE_1, 1, cloneMeta(state));
	}

	private int cloneMeta(IBlockState state) {
		/**1=玉子焼き, 2=ハンバーグ, 3=トマトソース, 4=きのこソース**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return 1; }
		if (i == 2) { return 2; }
		if (i == 3) { return 3; }
		else { return 4; }
	}
	
	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Steam effect. */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		World par1World = worldIn;
		int par2 = x;
		int par3 = y;
		int par4 = z;
		Random par5Random = rand;

		Block downBlock = worldIn.getBlockState(pos.down()).getBlock();
		boolean cook = (downBlock == Blocks.LIT_FURNACE || downBlock == Kitchen_Blocks.LIT_KITSTOVE || 
				downBlock == Kitchen_Blocks.LIT_KITOVEN || downBlock == Kitchen_Blocks.LIT_KITOVEN_B);

		if (cook) {
			int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
			/**1=玉子焼き, 2=ハンバーグ, 3=トマトソース, 4=きのこソース**/
			if (i == 1 || i == 2) {
				for (int la = 0; la < 1; ++la) {
					double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
					double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d3 = 0.12D;
					double d4 = 0.17D;
					par1World.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D); }

				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(x, y, z, SoundEvents_CM.JUU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
			}
			
			else {
				for (int la = 0; la < 1; ++la) {
					double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
					double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d3 = 0.12D;
					double d4 = 0.17D;
					par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D); }

				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
			}
		}
	}
}
