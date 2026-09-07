package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NabeCooked_SNT extends BaseStage3_Face {
	/** 1=塩, 2=煮豆, 3=豆腐 **/
	public NabeCooked_SNT(String name) {
		super(name);

		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		/** 1=塩, 2=煮豆, 3=豆腐 **/
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 1) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SHIO, 3, 0);
				ItemStack take = new ItemStack(Items_Teatime.SHIO, 1, 1);
				if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }

				worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
						.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(4))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 2) {
			if (hItem == Items.BOWL) {
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.NIMAME, 0);
				
				worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
						.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(4))); }
			
			if (hItem != Items.BOWL) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (i == 3) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.TOUFU, 4, 0);

				worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
						.withProperty(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(4))); }
			
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

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_NoTab.NABE_SNT, 1, cloneMeta(state)));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_NoTab.NABE_SNT, 1, cloneMeta(state));
	}

	private int cloneMeta(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		
		if (i == 1) { return 1; }
		if (i == 2) { return 2; }
		else { return 3; }
	}
}
