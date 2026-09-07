package com.ayutaki.chinjufumod.blocks.wood;

import java.util.Random;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.ParticleTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Leaf_Sakuraflower extends Abstract_Leaf {

	public static final PropertyBool CHECK_DECAY = PropertyBool.create("check_decay");

	public Leaf_Sakuraflower(String name) {
		super(name);

		setDefaultState(blockState.getBaseState()
				.withProperty(CHECK_DECAY, Boolean.valueOf(true))
				.withProperty(DECAYABLE, Boolean.valueOf(true)));
	}

	/* デフォルトのメタData valueを呼び出し */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
				.withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	/* メタData valueを拾う */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if (!state.getValue(DECAYABLE).booleanValue()) { i |= 4; }
		
		if (state.getValue(CHECK_DECAY).booleanValue()) { i |= 8; }
		return i;
	}

	/* メタData valueとして CHECK_DECAY, DECAYABLE を設ける */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { CHECK_DECAY, DECAYABLE });
	}

	/*Drop Item and Clone Item.*/
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) { 
		return true;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Items_Seasonal.SAKURA_flow, 1, 0);
	}

	public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess worldIn, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(Items_Seasonal.SAKURA_flow, 1, 0));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Seasonal.SAKURA_flow, 1, 0);
	}
	
	@Override
	public java.util.List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		java.util.List<ItemStack> stack = new java.util.ArrayList<ItemStack>();
		Random rand = worldIn instanceof World ? ((World)worldIn).rand : new Random();
		int chance = fortune > 0? (fortune == 1 ? 30 : 25) : 40;

		if (rand.nextInt(chance) == 0) { stack.add(new ItemStack(Items_Seasonal.SAKURA_nae, 1, 0)); }
		if (rand.nextInt(chance) == 1) { stack.add(new ItemStack(Items_Seasonal.SAKURA_nae, 1, 0)); }
		if (rand.nextInt(chance) == 2) { stack.add(new ItemStack(Items_Teatime.FOOD_CHERRY, 1, 0)); }
		if (rand.nextInt(chance) == 3) { stack.add(new ItemStack(Items_Teatime.FOOD_CHERRY, 1, 0)); }
		
		if (rand.nextInt(chance) == 4) { stack.add(new ItemStack(Items.STICK, 1, 0)); }
		return stack;
	}
	
	/* 桜の花びら */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		if (worldIn.isAirBlock(pos.down())) {

			/** 確率 120分の1 **/
			if (rand.nextInt(120) == 0) {
				int j = rand.nextInt(2) * 2 - 1;
				int k = rand.nextInt(2) * 2 - 1;

				double d0 = pos.getX() + 0.5D + 0.25D * j;
				double d1 = pos.getY() - 0.15D;
				double d2 = pos.getZ() + 0.5D + 0.25D * k;
				double d3 = rand.nextFloat() * j * 0.1D;
				double d4 = (rand.nextFloat() * 0.055D) + 0.015D;
				double d5 = rand.nextFloat() * k * 0.1D;

				ChinjufuMod.PROXY.spawnParticle(ParticleTypes_CM.FALLSAKURA, d0, d1, d2, d3, -d4, d5);
			}
		}
	}
}
