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
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NabeNama_Azuki extends BaseNabeNama_4 {
	/** 1=赤飯, 2=あずき, 3=あんこ, 4=テングサ **/
	public NabeNama_Azuki(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		CMEvents.textRequestHeat(worldIn, pos, playerIn);
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	/* Cooking. Delete 'super.updateTick(' */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/** 1=赤飯, 2=あずき, 3=あんこ, 4=テングサ **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (cookingIn(worldIn, pos)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			if (i == 1) {
				worldIn.setBlockState(pos, Dish_Blocks.NABESEKIHAN.getDefaultState()
						.withProperty(NabeGohan_Other.H_FACING, state.getValue(H_FACING))
						.withProperty(NabeGohan_Other.STAGE_1_4, Integer.valueOf(1))); }
			
			else { //i != 1
				worldIn.setBlockState(pos, Dish_Blocks.NABE_cooked_Azuki.getDefaultState()
						.withProperty(NabeCooked_Azuki.H_FACING, state.getValue(H_FACING))
						.withProperty(NabeCooked_Azuki.STAGE_1_4, Integer.valueOf(i))); }
		}
		
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

		/** 1=赤飯, 2=あずき, 3=あんこ, 4=テングサ **/
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
		/** 1=赤飯, 2=あずき, 3=あんこ, 4=テングサ **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		int stage = (i == 4)? 5 : i;
		return new ItemStack(Items_Teatime.NABE_NAMA_AZUKI, 1, stage);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
