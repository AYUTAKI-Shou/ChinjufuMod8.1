package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Kitchen extends BaseStage3_Face {
	/** 1=キッチン, 2=やかん, 3=寸胴 **/
	public Kitchen(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(10.0F);
		setLightOpacity(1);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		/** 1=キッチン, 2=やかん, 3=寸胴 **/
		if (hStack.isEmpty()) {
			if (i == 2) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.Item_YAKAN_kara, 0);
				worldIn.setBlockState(pos, state.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1))); }
	
			if (i == 3) {
				CMEvents.emptyTake_1Item(worldIn, pos, playerIn, Items_Teatime.ZUNDOU, 0);
				worldIn.setBlockState(pos, state.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1))); }
		}
		
		else { //!empty
			if (i == 1) {
				if (hItem == Items_Teatime.NABE_kara) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.setBlockState(pos, Kitchen_Blocks.KIT_DONABE.getDefaultState()
							.withProperty(Kitchen_Donabe.H_FACING, state.getValue(H_FACING))
							.withProperty(Kitchen_Donabe.STAGE_1_4, Integer.valueOf(1))); }

				if (hItem == Items_Teatime.FRYPAN_kara) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1.0F, 3.0F);
					worldIn.setBlockState(pos, Kitchen_Blocks.KIT_DONABE.getDefaultState()
							.withProperty(Kitchen_Donabe.H_FACING, state.getValue(H_FACING))
							.withProperty(Kitchen_Donabe.STAGE_1_4, Integer.valueOf(3))); }

				if (hItem == Items_Teatime.Item_YAKAN_kara) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1.0F, 3.0F);
					worldIn.setBlockState(pos, state.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(2))); }

				if (hItem == Items_Teatime.ZUNDOU) {
					CMEvents.consumeN_Hand(1, playerIn, hand);
					worldIn.playSound(null, pos, SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.setBlockState(pos, state.withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3))); }
				
				if (hItem != Items_Teatime.NABE_kara && hItem != Items_Teatime.FRYPAN_kara &&
						hItem != Items_Teatime.Item_YAKAN_kara && hItem != Items_Teatime.ZUNDOU) {
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
			
			else { //i != 1
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
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
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i == 2) { stack.add(new ItemStack(Items_Teatime.Item_YAKAN_kara, 1, 0)); }
		if (i == 3) { stack.add(new ItemStack(Items_Teatime.ZUNDOU, 1, 0)); }
		
		stack.add(cloneStack());
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.KITCHEN, 1, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_kitchen.name"));
	}
}
