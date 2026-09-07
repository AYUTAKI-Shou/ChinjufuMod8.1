package com.ayutaki.chinjufumod.blocks.dish;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class Zundou extends BaseStage2_FaceWater {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

	/** 1=close, 2=open **/
	public Zundou(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem == Items.WATER_BUCKET) {
			CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_MIZU.defaultBlockState()
					.setValue(Zundou_Mizu.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou_Mizu.STAGE_1_2, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.MIZUOKE_full) {
			CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_MIZU.defaultBlockState()
					.setValue(Zundou_Mizu.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou_Mizu.STAGE_1_2, Integer.valueOf(1)), 3); }


		if (hItem == Items.MILK_BUCKET) {
			CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_MILK.defaultBlockState()
					.setValue(Zundou_Milk.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou_Milk.STAGE_1_2, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.MIZUOKE_Milk) {
			CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_MILK.defaultBlockState()
					.setValue(Zundou_Milk.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou_Milk.STAGE_1_2, Integer.valueOf(1)), 3); }
		
		if (hItem != Items.WATER_BUCKET && hItem != Items_Teatime.MIZUOKE_full && hItem != Items.MILK_BUCKET && hItem != Items_Teatime.MIZUOKE_Milk) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}
}
