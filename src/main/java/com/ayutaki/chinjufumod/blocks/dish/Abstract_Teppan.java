package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.blocks.furnace.Kitchen_Oven;
import com.ayutaki.chinjufumod.blocks.furnace.Kitchen_Oven_B;
import com.ayutaki.chinjufumod.blocks.kitchen.Base_Cooktop;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class Abstract_Teppan extends Abstract_CookPan {
	/* Property */
	public static final PropertyBool DOWN = PropertyBool.create("down");

	public Abstract_Teppan(String name) {
		super(name);
		setSoundType(SoundType.SNOW);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* BlockState when it was placed. */
	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing,
			float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(H_FACING, placer.getHorizontalFacing().getOpposite());
	}

	/* Reaction to Neighboring blocks. */
	protected boolean connectTeppan(IBlockAccess worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		return block instanceof Base_Cooktop || block instanceof Kitchen_Oven || block instanceof Kitchen_Oven_B;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(DOWN, Boolean.valueOf(this.connectTeppan(worldIn, pos.down())));
	}
}
