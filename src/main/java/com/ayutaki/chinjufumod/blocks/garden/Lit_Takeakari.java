package com.ayutaki.chinjufumod.blocks.garden;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

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

public class Lit_Takeakari extends Base_Takeakari {

	public Lit_Takeakari(String name) {
		super(name);
		/* Glow Stone=1.0F, Torch=0.9375F */
		setLightLevel(0.9375F);
		setTickRandomly(true);
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.getValue(STAGE_1_3);
		
		if (hStack.isEmpty()) {
			CMEvents.soundFireExting(worldIn, pos);
			worldIn.setBlockState(pos, Lamp_Blocks.TAKEAKARI.getDefaultState()
					.withProperty(Takeakari.H_FACING, state.getValue(H_FACING))
					.withProperty(Takeakari.STAGE_1_3, Integer.valueOf(i))); }
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* SMOKE */
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {

		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;

		if (rand.nextDouble() < 0.02D) {
			EnumFacing direction = state.getValue(H_FACING);
			
			switch (direction) {
			case NORTH :
			default :
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 + 0.2D, d2 + 0.3125D, 0.0D, 0.0D, 0.0D);
				break;

			case SOUTH :
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 + 0.2D, d2 - 0.3125D, 0.0D, 0.0D, 0.0D);
				break;

			case EAST :
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.3125D, d1 + 0.2D, d2, 0.0D, 0.0D, 0.0D);
				break;
				
			case WEST :
				worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.3125D, d1 + 0.2D, d2, 0.0D, 0.0D, 0.0D);
				break;
			} // direction
		}
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
