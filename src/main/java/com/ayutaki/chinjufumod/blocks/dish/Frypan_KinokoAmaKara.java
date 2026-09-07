package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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

public class Frypan_KinokoAmaKara extends BaseFrypanNama_4 {
	/** 1=きのこ甘辛(生), 2=きのこ甘辛, 3=空やかん, 4=空き **/
	public Frypan_KinokoAmaKara(String name) {
		super(name);
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Cooking */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/** 1=きのこ甘辛(生), 2=きのこ甘辛, 3=空やかん, 4=空き **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) {
			if (cookingIn(worldIn, pos)) {
				worldIn.scheduleUpdate(pos, this, COOK_TIME);
				worldIn.setBlockState(pos, state.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(2))); }
		}

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
		
		/** 1=きのこ甘辛(生), 2=きのこ甘辛, 3=空やかん, 4=空き **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();


		if (rand.nextDouble() < 0.1D) {
			if (i <= 2) {
				if (cookingIn(worldIn, pos)) {
					worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
			}
		}

		if (i == 2) {
			if (cookingIn(worldIn, pos)) {

				for (int la = 0; la < 1; ++la) {
					double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
					double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
					double d3 = 0.12D;
					double d4 = 0.17D;
					par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D);
				}
			}
		}
		
		else { }
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(cloneItem(state), 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(state), 1, 0);
	}

	private Item cloneItem(IBlockState state) {
		/** 1=きのこ甘辛(生), 2=きのこ甘辛, 3=空やかん, 4=空き **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return Items_Teatime.FPKINOKOAK_nama; }
		if (i == 2) { return Items_Teatime.FPKINOKOAK; }
		if (i == 3) { return Items_Teatime.Item_YAKAN_kara; }
		else { return Items.AIR; }
	}
}
