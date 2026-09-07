package com.ayutaki.chinjufumod.blocks.wood;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_BlockLog;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Log_WoodCM extends Regi_BlockLog {

	public Log_WoodCM(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.SEASONAL);
		setHardness(2.0F);
		setResistance(10.0F);
		
		setDefaultState(blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	/* BlockState に応じた MapColor を得る */
	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return Blocks.LOG.getDefaultState().getMapColor(worldIn, pos);
	}

	/* IDが同じで, メタData valueが異なるブロックのリストを返す */
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this));
	}

	/* 向きの応じたメタData valueを呼び出し */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();

		switch (meta & 12) {
			case 0:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
				break;
			case 4:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
				break;
			case 8:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
				break;
			default:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
		}
		return state;
	}

	/* メタData valueを拾う */
	@Override
	@SuppressWarnings("incomplete-switch")
	public int getMetaFromState(IBlockState state) {
		int meta = 0;

		switch (state.getValue(LOG_AXIS)) {
			case X:
				meta |= 4;
				break;
			case Z:
				meta |= 8;
				break;
			case NONE:
				meta |= 12;
		}
		return meta;
	}

	/* メタData valueとして LOG_AXIS を設ける */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(cloneItem(), 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == Seasonal_Blocks.SAKURA_log) { return Items_Seasonal.SAKURA_log; }
		if (this == Seasonal_Blocks.KAEDE_log) { return Items_Seasonal.KAEDE_log; }
		if (this == Seasonal_Blocks.ICHOH_log) { return Items_Seasonal.ICHOH_log; }
		else { return Item.getItemFromBlock(Blocks.LOG); }
	}
}
