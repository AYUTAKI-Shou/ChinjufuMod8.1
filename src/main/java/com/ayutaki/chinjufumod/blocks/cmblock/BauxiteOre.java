package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.Random;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BauxiteOre extends Block {

	public BauxiteOre(String name) {
		super(Material.ROCK);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);
		setCreativeTab(ChinjufuModTabs.CHINJUFU);

		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(10.0F);

		setHarvestLevel("pickaxe", 2);
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Items_Chinjufu.BAUXITE_ORE, 1, 0);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items_Chinjufu.BAUXITE;
	}

	/* Drop Amount */
	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

	/* for Fortune Enchant. */
	@Override
	public int quantityDroppedWithBonus(int fortune, Random rand) {
		if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState()
				.getValidStates().iterator().next(), rand, fortune)) {

			int i = rand.nextInt(fortune + 2) - 1;

			if (i < 0) { i = 0; }

			return this.quantityDropped(rand) * (i + 1);
		}

		else { return this.quantityDropped(rand); }
	}

	/* Drop EXP */
	@Override
	public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess worldIn, BlockPos pos, int fortune) {
		Random rand = worldIn instanceof World ? ((World)worldIn).rand : new Random();
		return MathHelper.getInt(rand, 1, 3);
	}
}
