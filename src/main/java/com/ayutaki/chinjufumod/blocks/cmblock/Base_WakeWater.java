package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_WakeWater extends Block {

	public Base_WakeWater(String name) {
		super(Material.SNOW);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);
		
		setSoundType(SoundType.SNOW);
		setHardness(0.1F);
		setResistance(500.0F);
		setLightOpacity(0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.00625D, 1.0D);
	}

	/* tick処理で消せなかった時の保険。再ログイン時に消去する */
	public void onChunkLoad(World worldIn, BlockPos pos) {
		worldIn.setBlockToAir(pos);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		if (hItem == Items_Weapon.DEVICE_SONAR) { return false; }
		
		else {
			/* 水を汲む WATERブロックは, クリエティブで汲めなくしているが保留 */
			if (hItem == Items.BUCKET) {
				CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items.WATER_BUCKET, 0); }
	
			/* 大釜(Cauldron)から引用 */
			if (hItem == Items.GLASS_BOTTLE) {
				CMEvents.Bottle_toWaterBottle(worldIn, pos, playerIn, hand); }
	
			/* TTimeItems 海水OKの水桶と土鍋のみ */
			if (hItem == Items_Teatime.MIZUOKE) {
				CMEvents.changeBucket_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.MIZUOKE_full, 0); }
	
			if (hItem == Items_Teatime.NABE_kara) {
				CMEvents.changeBowl_seBucket(worldIn, pos, playerIn, hand, Items_Teatime.NABESHIO_nama, 0); }
			
			if (hItem == Items_Teatime.KEIRYO_CUP) {
				CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.KEIRYO_CUP_full, 0); }
			
			/** 'true' to not put anything on top. **/
			return true;
		}
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items.AIR;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public SoundType getSoundType(IBlockState state, World worldIn, BlockPos pos, @Nullable Entity entity) {
		return super.getSoundType(state, worldIn, pos, entity);
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
