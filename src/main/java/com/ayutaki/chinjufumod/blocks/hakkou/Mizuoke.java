package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Mizuoke extends BaseStage4_Face {

	public Mizuoke(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
		
		setTickRandomly(true);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		/* 1=Empty, 2=1.7, 3=3.4, 4=5.1, Full5=6.8, Full6=8.5 */

		/** バケツ **/
		if (hItem == Items.WATER_BUCKET) {
			CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
			
			if (i == 1) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE_full.getDefaultState()
						.withProperty(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.withProperty(Mizuoke_full.STAGE_1_2, Integer.valueOf(1))); }

			else { //i != 1
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE_full.getDefaultState()
						.withProperty(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.withProperty(Mizuoke_full.STAGE_1_2, Integer.valueOf(2))); }
			return true;
		}

		if (hItem == Items_Teatime.MIZUOKE_full) {
			CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
			
			if (i == 1) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE_full.getDefaultState()
						.withProperty(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.withProperty(Mizuoke_full.STAGE_1_2, Integer.valueOf(1))); }

			else { //i != 1
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE_full.getDefaultState()
						.withProperty(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.withProperty(Mizuoke_full.STAGE_1_2, Integer.valueOf(2))); }
			return true;
		}

		/** ガラス瓶 **/
		if (i >= 3 && hItem == Items.GLASS_BOTTLE) {
			CMEvents.Bottle_toWaterBottle(worldIn, pos, playerIn, hand);
			worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 2)));
			return true;
		}

		/** 水入りガラス瓶 **/
		if (hItem == Items.POTIONITEM && PotionUtils.getPotionFromItem(hStack) == PotionTypes.WATER) {
			worldIn.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
			CMEvents.mode1Through_Consume(playerIn, hand, Items.GLASS_BOTTLE, 0);
			
			if (i <= 2) { worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 2))); }
			if (i == 3) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE_full.getDefaultState()
						.withProperty(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.withProperty(Mizuoke_full.STAGE_1_2, Integer.valueOf(1))); }
			if (i == 4) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE_full.getDefaultState()
						.withProperty(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.withProperty(Mizuoke_full.STAGE_1_2, Integer.valueOf(2))); }
			return true;
		}

		/** 計量カップ **/
		if (i > 2 && hItem == Items_Teatime.KEIRYO_CUP) {
			CMEvents.changeBottle_seBottle(worldIn, pos, playerIn, hand, Items_Teatime.KEIRYO_CUP_full, 0);
			worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 2)));
			return true;
		}
		
		/** 洗う **/
		else {
			if (i >= 3 && hItem instanceof ItemArmor) {
				ItemArmor armor = (ItemArmor)hItem;
				if (armor.getArmorMaterial() == ItemArmor.ArmorMaterial.LEATHER && armor.hasColor(hStack) && !worldIn.isRemote) {
					armor.removeColor(hStack);

					CMEvents.soundWaterUse(worldIn, pos);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 2)));
					playerIn.addStat(StatList.ARMOR_CLEANED);
					return true;
				}
			} //鎧

			if (i >= 3 && hItem instanceof ItemBanner) {
				if (TileEntityBanner.getPatterns(hStack) > 0 && !worldIn.isRemote) {
					CMEvents.wash_Banner(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 2))); }
				return true;
			} //旗

			if (i >= 3 && hItem instanceof Base_Hake) {
				CMEvents.washHAKE_MIZUOKE(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 2)));
				return true;
			}//色筆
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* RandomTick */
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		/* 1=Empty, 2=1.7, 3=3.4, 4=5.1, Full5=6.8, Full6=8.5 */
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (worldIn.isRainingAt(pos.up())) {
			if (i == 4) {
				worldIn.setBlockState(pos, Hakkou_Blocks.MIZUOKE_full.getDefaultState()
						.withProperty(Mizuoke_full.H_FACING, state.getValue(H_FACING))
						.withProperty(Mizuoke_full.STAGE_1_2, Integer.valueOf(1))); }
			
			else { worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1))); }
		}
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5625D, 0.75D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}

	public ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.MIZUOKE, 1, 0);
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
