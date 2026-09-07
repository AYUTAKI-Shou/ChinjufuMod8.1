package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.hakkou.NabeAmazake_cooked;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NabeNama_SNTA extends BaseNabeNama_4 {
	/** １=塩, 2=煮豆, 3=豆腐, 4=甘酒**/
	public NabeNama_SNTA(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		CMEvents.textRequestHeat(worldIn, pos, playerIn);
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Cooking */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/** １=塩, 2=煮豆, 3=豆腐, 4=甘酒**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (cookingIn(worldIn, pos)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);

			if (i == 4) {
				worldIn.setBlockState(pos, Hakkou_Blocks.NABEAMAZAKE.getDefaultState()
						.withProperty(NabeAmazake_cooked.H_FACING, state.getValue(H_FACING))); }
			
			else { //i != 4
				worldIn.setBlockState(pos, Dish_Blocks.NABE_cooked_SNT.getDefaultState()
						.withProperty(NabeCooked_SNT.H_FACING, state.getValue(H_FACING))
						.withProperty(NabeCooked_SNT.STAGE_1_3, Integer.valueOf(i))); } }
		
		else { }
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

		if (cookingIn(worldIn, pos)) {
			for (int la = 0; la < 1; ++la) {
				double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
				double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
				double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
				double d3 = 0.12D;
				double d4 = 0.17D;
				par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D);
			}
			
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}

	private ItemStack cloneStack(IBlockState state) {
		/** １=塩, 2=煮豆, 3=豆腐, 4=甘酒**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 2) { return new ItemStack(Items_Teatime.NABE_nama_SNTA, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Teatime.NABE_nama_SNTA, 1, 3); }
		if (i == 4) { return new ItemStack(Items_Teatime.NABEAMAZAKE_nama, 1, 0); }
		else { return new ItemStack(Items_Teatime.NABESHIO_nama, 1, 0); }
	}
}
