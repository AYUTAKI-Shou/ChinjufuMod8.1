package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NabeCooked_Azuki extends BaseStage4_Face {
	/** 1=寒天, 2=あずき, 3=あんこ, 4=テングサ **/
	public NabeCooked_Azuki(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* Steam effect. */

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		/** 1=寒天, 2=あずき, 3=あんこ, 4=テングサ **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) {
			int k = hStack.getMetadata();
			int gHC = hStack.getCount();
			boolean NS = (state.getValue(H_FACING) == EnumFacing.NORTH) || (state.getValue(H_FACING) == EnumFacing.SOUTH);
			int FACE = NS? 0 : 8;
			
			if (hItem == Items.APPLE && k == 0) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEK_APPLE.getDefaultState()
							.withProperty(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE))); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_CHERRY) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEK_CHERRY.getDefaultState()
							.withProperty(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE))); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_MIKAN) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEK_CITRUS.getDefaultState()
							.withProperty(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE))); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			if (hItem == Items_Teatime.FOOD_GRAPE) {
				if (gHC >= 3) {
					CMEvents.consumeN_seSnowP(3, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEK_GRAPE.getDefaultState()
							.withProperty(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE))); }
				
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); } }
			
			
			if (hItem == Items_Teatime.MIZUOKE_Milk) {
				CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Dish_Blocks.NABEK_MILK.getDefaultState()
						.withProperty(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE))); }
			
			if (hItem == Items.MILK_BUCKET) {
				CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, Dish_Blocks.NABEK_MILK.getDefaultState()
						.withProperty(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE))); }
			
			if (hItem == Items_Teatime.MUSHIGOME && k == 4) {
				CMEvents.mode1Through_Consume(playerIn, hand, Items.BOWL, 0);
				CMEvents.soundSnowPlace(worldIn, pos);
				worldIn.setBlockState(pos, Dish_Blocks.NABEK_YOKAN.getDefaultState()
						.withProperty(BaseNabe_Kanten.STAGE_0_15, Integer.valueOf(FACE))); }

			if ((hItem != Items.APPLE && k != 0) && hItem != Items_Teatime.FOOD_CHERRY &&
				hItem != Items_Teatime.FOOD_MIKAN && hItem != Items_Teatime.FOOD_GRAPE && 
				hItem != Items_Teatime.MIZUOKE_Milk && hItem != Items.MILK_BUCKET && 
				(hItem != Items_Teatime.MUSHIGOME && k != 4)) {
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (i == 2) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.PAN_KIJI, 4, 0);
				worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
						.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(4))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 3) {
			if (hItem == Items.BOWL) {
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.MUSHIGOME, 4);
				worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
						.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(4))); }
			
			if (hItem == Items_Teatime.MOCHI) {
				CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);	
				worldIn.setBlockState(pos, Dish_Blocks.NABEZENZAI_M.getDefaultState()
						.withProperty(BaseNabe.H_FACING, state.getValue(H_FACING))
						.withProperty(BaseNabe.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			
			if (hItem == Items_Seasonal.KURI_ROAST) {
				int gHC = hStack.getCount();
				if (gHC >= 8) {
					CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, Dish_Blocks.NABEZENZAI_K.getDefaultState()
							.withProperty(BaseNabe.H_FACING, state.getValue(H_FACING))
							.withProperty(BaseNabe.STAGE_1_4, Integer.valueOf(1)), 3); }
			
				else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
			}
			
			if (hItem != Items.BOWL && hItem != Items_Teatime.MOCHI && hItem != Items_Seasonal.KURI_ROAST) { 
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (i == 4) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items.DYE, 2, 15);
				worldIn.setBlockState(pos, state.withProperty(BaseNabe.STAGE_1_4, Integer.valueOf(1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.21875D, 0.0D, 0.21875D, 0.78125D, 0.25D, 0.78125D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Teatime.NABE_NAMA_AZUKI, 1, cloneMeta(state)));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.NABE_NAMA_AZUKI, 1, cloneMeta(state));
	}

	private int cloneMeta(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 2) { return 2; }
		if (i == 3) { return 3; }
		else { return 5; }
	}
}
