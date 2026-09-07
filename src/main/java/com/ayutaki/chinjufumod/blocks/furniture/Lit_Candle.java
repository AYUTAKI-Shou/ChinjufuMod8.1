package com.ayutaki.chinjufumod.blocks.furniture;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Lit_Candle extends Base_Candle {

	public Lit_Candle(String name) {
		super(name);
		/* Glow Stone=1.0F, Torch=0.9375F */
		setLightLevel(0.875F);
		setTickRandomly(true);
	}

	/* SMOKE...Particle size is too large.
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.7D;
		double d2 = (double)pos.getZ() + 0.5D;

		if (rand.nextDouble() < 0.025D) {
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1 - 0.4D, d2, 0.0D, 0.0D, 0.0D);
		}
	}*/

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (hItem instanceof Base_Hake) { return false; }

		else {
			if (hStack.isEmpty()) {
				CMEvents.soundFireExting(worldIn, pos);
	
				worldIn.setBlockState(pos, Lamp_Blocks.CANDLE.getDefaultState()
						.withProperty(Candle.STAGE_0_15, Integer.valueOf(i))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
