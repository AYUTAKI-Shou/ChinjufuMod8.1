package com.ayutaki.chinjufumod.items.seasonal;

import com.ayutaki.chinjufumod.blocks.wood.FallLeaf;
import com.ayutaki.chinjufumod.handler.Biomes_CM;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Seasonal;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class Warahai_ST extends IR_Seasonal {

	public Warahai_ST(String name) {
		super(name);
		setUnlocalizedName(name);
	}

	/* FlintAndSteel */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Biome biome = worldIn.getBiome(pos);
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (biome == Biomes_CM.BIOME_SAKURA || biome == Biomes_CM.BIOME_SAKURA_HILL) {
			
			Block block = worldIn.getBlockState(pos).getBlock();
			
			if (block instanceof BlockDirt || block instanceof BlockGrass || block instanceof FallLeaf) {
				
				if (worldIn.isAirBlock(pos.up())) {
					worldIn.playSound(null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 0.8F, 1.2F);
					
					for(int n = 0; n < 15; ++n) {
						double d0 = worldIn.rand.nextGaussian() * 0.02D;
						double d1 = worldIn.rand.nextGaussian() * 0.02D;
						double d2 = worldIn.rand.nextGaussian() * 0.02D;
						worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + worldIn.rand.nextFloat(), pos.getY() +worldIn.rand.nextFloat() + 0.5D, pos.getZ() + worldIn.rand.nextFloat(), d0, d1, d2); }
					
					if (worldIn.rand.nextInt(3) == 0) {
						worldIn.setBlockState(pos.up(), Seasonal_Blocks.TAKENOKO.getDefaultState(), 3); }
					
					CMEvents.consume1_Stack(hStack, playerIn);
					return EnumActionResult.SUCCESS;
				}
			}
		}
		return EnumActionResult.FAIL;
	}
}
