package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Zundou_Shio extends BaseZundou_2Cook {
	/** 1=cold, 2=hot **/
	public Zundou_Shio(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_2);
		/** 1=cold, 2=hot **/

		if (i == 2) {
			if (hItem == Items_Teatime.PASTA_nama) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_PASTA.defaultBlockState()
						.setValue(Zundou4_Pasta.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Pasta.STAGE_1_4, Integer.valueOf(1)), 3); } //Large Items cool it down.
	
			if (hItem == Items.COD) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU_FISH.defaultBlockState()
						.setValue(Zundou4_Fish.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou4_Fish.STAGE_1_4, Integer.valueOf(1)), 3); } //Large Items cool it down.
			
			if (hItem != Items_Teatime.PASTA_nama && hItem != Items.COD && hItem != Items_Teatime.SHIO) {
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to use **/
		else { //i != 2
			CMEvents.textEarlyUse(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		super.appendHoverText(stack, worldIn, blockTip, tipFlag);
		blockTip.add(new TranslationTextComponent("tips.block_zundou_shiomizu").withStyle(TextFormatting.GRAY));
	}
}
