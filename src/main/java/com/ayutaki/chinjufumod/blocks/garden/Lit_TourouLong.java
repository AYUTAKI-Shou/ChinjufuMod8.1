package com.ayutaki.chinjufumod.blocks.garden;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Lit_TourouLong extends Base_Longtourou {

	public Lit_TourouLong(String name) {
		super(name);
		setLightOpacity(0);
		/* Glow Stone=1.0F, Torch=0.9375F */
		setLightLevel(1.0F);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hStack.isEmpty()) {
			CMEvents.soundFireExting(worldIn, pos);
			worldIn.setBlockState(pos, Garden_Blocks.LONGTOUROU_TOP.getDefaultState()
					.withProperty(TourouLong_Top.H_FACING, state.getValue(H_FACING))
					.withProperty(TourouLong_Top.STAGE_1_4, Integer.valueOf(i))); }
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	/* SMOKE */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;

		if (rand.nextDouble() < 0.05D) {
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 - 0.2D, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
