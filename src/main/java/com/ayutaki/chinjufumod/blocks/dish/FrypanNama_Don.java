package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class FrypanNama_Don extends BaseFrypanNama_4 {
	/**1=牛丼, 2=親子丼, 3=カツ, 4=カツ丼**/
	public FrypanNama_Don(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		CMEvents.textEarlyCollect(worldIn, pos, playerIn);
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	/* Cooking */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		/**1=牛丼, 2=親子丼, 3=カツ, 4=カツ丼**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (cookingIn(worldIn, pos)) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_BAKE_2.getDefaultState()
					.withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(i))); }
		
		else { }
	}

	/* add Effect */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		if (cookingIn(worldIn, pos)) {
			/**1=牛丼, 2=親子丼, 3=カツ, 4=カツ丼**/
			int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

			if (i == 3) {
				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(x, y, z, SoundEvents_CM.JUU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
			}

			else { //i != 3
				if (rand.nextDouble() < 0.1D) {
					worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }
			}
		}
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Teatime.FRYPAN_NAMA_2, 1, cloneMeta(state)));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.FRYPAN_NAMA_2, 1, cloneMeta(state));
	}

	private int cloneMeta(IBlockState state) {
		/**1=牛丼, 2=親子丼, 3=カツ, 4=カツ丼**/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return 1; }
		if (i == 2) { return 2; }
		if (i == 3) { return 3; }
		else { return 4; }
	}
}
