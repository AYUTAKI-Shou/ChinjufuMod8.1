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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NabeCream extends BaseNabeNama_4 {

	public NabeCream(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (this == Dish_Blocks.NABE_CREAM_sub) { 
			if (i == 4) {
				Item hItem = hStack.getItem();
				if (hItem == Items.BOWL) {
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.MUSHIGOME, 6);
					worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
							.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
							.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(4)), 3); }
				
				if (hItem != Items.BOWL) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			else {
				if (cookingIn(worldIn, pos)) {
					if (hStack.isEmpty()) {
						CMEvents.soundSnowBreak(worldIn, pos);
						worldIn.setBlockState(pos, Dish_Blocks.NABE_CREAM.getDefaultState()
								.withProperty(H_FACING, state.getValue(H_FACING))
								.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); }
					
					else { //!empty
						CMEvents.textFullItem(worldIn, pos, playerIn); }
				}
				else { CMEvents.textRequestHeat(worldIn, pos, playerIn); }
			}
		}
		
		else {
			if (cookingIn(worldIn, pos)) {
				if (hStack.isEmpty()) {
					CMEvents.soundSnowBreak(worldIn, pos);
					worldIn.setBlockState(pos, Dish_Blocks.NABE_CREAM_sub.getDefaultState()
							.withProperty(H_FACING, state.getValue(H_FACING))
							.withProperty(STAGE_1_4, Integer.valueOf(i))); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
			}
			else { CMEvents.textRequestHeat(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return true;
	}
	
	/* Steam effect. */
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (rand.nextDouble() < 0.1D) {
			if (cookingIn(worldIn, pos)) {
				worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		boolean raw = (this == Dish_Blocks.NABE_CREAM && i == 1);
		
		if (raw) { stack.add(new ItemStack(Items_Teatime.NABE_NAMA_AZUKI, 1, 4)); }
		else { stack.add(new ItemStack(Items_Teatime.NABE_kara, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.NABE_NAMA_AZUKI, 1, 4);
	}
}
