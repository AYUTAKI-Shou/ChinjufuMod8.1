package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
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

public class Tana_KinokoKonbuVanilla extends BaseTana_Stage05 {

	public Tana_KinokoKonbuVanilla(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_0_5);

		/** Can harvest **/
		if (i == 5) {
			if (hItem == Items.GLASS_BOTTLE) {
				
				if (this == Hakkou_Blocks.VANILLA_TARU) {
					int gHC = hStack.getCount();
					if (gHC >= 4) {
						CMEvents.consumeN_Hand(4, playerIn, hand);
						CMEvents.soundSnowTake(worldIn, pos);
						
						ItemStack take = new ItemStack(Items_Teatime.VANILLA_bot_14, 4);
						if (hStack.isEmpty()) { playerIn.setHeldItem(hand, take); }
						else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
						
						worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOU_TARU.getDefaultState()
								.with(Taru_Hakkou.STAGE_0_5, Integer.valueOf(3))); }
					
					else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
				}
				
				else {
					/** Collect with an Item **/
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.DASHI_bot_14);
					worldIn.setBlockState(pos, Hakkou_Blocks.HAKKOU_TARU.getDefaultState()
							.with(Taru_Hakkou.STAGE_0_5, Integer.valueOf(3))); 
				}
			}
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		if (this == Hakkou_Blocks.KINOKO_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_kinoko").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Hakkou_Blocks.KONBU_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_konbu").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Hakkou_Blocks.VANILLA_TARU) { blockTip.add(new TranslationTextComponent("tips.block_taru_vanilla").applyTextStyle(TextFormatting.GRAY)); }
	}
}
