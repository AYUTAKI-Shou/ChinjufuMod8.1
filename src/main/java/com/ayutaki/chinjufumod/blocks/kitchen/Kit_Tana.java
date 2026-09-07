package com.ayutaki.chinjufumod.blocks.kitchen;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class Kit_Tana extends BaseFacingWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 10.0D);
	private static final VoxelShape AABB_WEST = Block.box(6.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public Kit_Tana(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem == Items_Teatime.SARA) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_SARA1.defaultBlockState()
					.setValue(Base_Tana6.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana6.STAGE_1_6, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.TONSUI) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_TONSUI1.defaultBlockState()
					.setValue(Base_Tana6.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana6.STAGE_1_6, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.YUNOMI) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_YUNOMI1.defaultBlockState()
					.setValue(Base_Tana7.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana7.STAGE_1_7, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.TCUP_kara) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_TCUP1.defaultBlockState()
					.setValue(Base_Tana7.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana7.STAGE_1_7, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.CHAWAN) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_CHAWAN1.defaultBlockState()
					.setValue(Base_Tana6.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana6.STAGE_1_6, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.SHIKKI) {
			CMEvents.consume1_seWoodDish(worldIn, pos, playerIn, hand);	
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_SHIKKI1.defaultBlockState()
					.setValue(Base_Tana6.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana6.STAGE_1_6, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.DRINKGLASS) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_DRINKGLASS1.defaultBlockState()
					.setValue(Base_Tana9.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana9.STAGE_1_9, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.DONBURI) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_DONBURI1.defaultBlockState()
					.setValue(Base_Tana4.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana4.STAGE_1_4, Integer.valueOf(1)), 3); }

		if (hItem == Items_Teatime.SUSHIGETA_kara) {
			CMEvents.consume1_seWoodDish(worldIn, pos, playerIn, hand);	
			
			worldIn.setBlock(pos, Kitchen_Blocks.KIT_SUSHIGETA1.defaultBlockState()
					.setValue(Base_Tana4.H_FACING, state.getValue(H_FACING))
					.setValue(Base_Tana4.STAGE_1_4, Integer.valueOf(1)), 3); }

		if (hItem != Items_Teatime.SARA && hItem != Items_Teatime.TONSUI && hItem != Items_Teatime.YUNOMI &&
				hItem != Items_Teatime.TCUP_kara && hItem != Items_Teatime.CHAWAN && hItem != Items_Teatime.SHIKKI &&
				hItem != Items_Teatime.DRINKGLASS && hItem != Items_Teatime.DONBURI && hItem != Items_Teatime.SUSHIGETA_kara) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_kit_tana").withStyle(TextFormatting.GRAY));
	}
}
