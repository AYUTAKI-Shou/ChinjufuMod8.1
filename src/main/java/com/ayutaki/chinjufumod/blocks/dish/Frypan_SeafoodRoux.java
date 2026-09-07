package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Frypan_SeafoodRoux extends BaseFrypanNama_4 {

	protected static final int COOK_TIME = 1000;
	/**1=シーフードソース生, 2=シーフードソース, 3=カレールー生, 4=カレールー**/
	public Frypan_SeafoodRoux(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		/**1=シーフードソース生, 2=シーフードソース, 3=カレールー生, 4=カレールー**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1 ||i == 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 2) {
			if (hItem == Items_Teatime.PASTA && k == 2) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.PASTASEAFOOD, 0);
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				
				worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState()
						.withProperty(Frypan_kara.H_FACING, state.getValue(H_FACING))); }
			
			if (hItem != Items_Teatime.PASTA || k != 2) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		if (i == 4) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SPICE, 2, 7);
				worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState()
						.withProperty(Frypan_kara.H_FACING, state.getValue(H_FACING))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	/* Cooking */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/**1=シーフードソース生, 2=シーフードソース, 3=カレールー生, 4=カレールー **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		boolean RAW = (i == 1 || i == 3);
		if (cookingIn(worldIn, pos) && RAW) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); }
		
		else { }
	}
	
	/* add Effect */
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
			int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
			/**1=シーフードソース生, 2=シーフードソース, 3=カレールー生, 4=カレールー **/
			if (i <= 2) {
				if (i == 2) {
					for (int la = 0; la < 1; ++la) {
						double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
						double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
						double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
						double d3 = 0.12D;
						double d4 = 0.17D;
						par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D); }
				}
				
				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
			}
			
			else {
				if (i == 4) {	
					for (int la = 0; la < 1; ++la) {
						double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
						double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
						double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
						double d3 = 0.12D;
						double d4 = 0.17D;
						par1World.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D); }
				}
				
				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(x, y, z, SoundEvents_CM.JUU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
			}
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		/**1=シーフードソース生, 2=シーフードソース, 3=カレールー生, 4=カレールー**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 4) { 
			stack.add(new ItemStack(Items_Teatime.FRYPAN_kara, 1, 0));
			stack.add(new ItemStack(Items_Teatime.SPICE, 2, 7)); }
		else { stack.add(cloneStack(state)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}

	private ItemStack cloneStack(IBlockState state) {
		/**1=シーフードソース生, 2=シーフードソース, 3=カレールー生, 4=カレールー**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return new ItemStack(Items_Teatime.FRYPAN_NAMA_3, 1, 1); }
		if (i == 2) { return new ItemStack(Items_Teatime.FRYPAN_NAMA_3, 1, 2); }
		if (i == 3) { return new ItemStack(Items_Teatime.FRYPAN_NAMA_3, 1, 3); }
		else { return new ItemStack(Items_Teatime.FRYPAN_kara, 1, 0); }
	}
}
