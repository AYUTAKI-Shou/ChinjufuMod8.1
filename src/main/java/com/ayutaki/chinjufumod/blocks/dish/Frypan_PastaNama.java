package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class Frypan_PastaNama extends BaseFrypan_2Cook {

	public Frypan_PastaNama(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_2);

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (hItem == Items_Teatime.PASTA_sara) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takeItem());
				CMEvents.addEXP(1, worldIn, pos);

				worldIn.setBlockState(pos, Dish_Blocks.FRYPAN_kara.getDefaultState()
						.with(Frypan_kara.H_FACING, state.get(H_FACING))
						.with(Frypan_kara.WATERLOGGED, state.get(WATERLOGGED))); }
			
			if (hItem != Items_Teatime.PASTA_sara) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.FPTOMATO_nama) { return Items_Teatime.PASTATOMATO; }
		if (this == Dish_Blocks.FPKINOKO_nama) { return Items_Teatime.PASTAKINOKO; }
		else { return Items_Teatime.PASTASEAFOOD; }
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, blockTip, tipFlag);
		blockTip.add(new TranslationTextComponent("tips.block_food_frypan_pasta").applyTextStyle(TextFormatting.GRAY));
	}
}
