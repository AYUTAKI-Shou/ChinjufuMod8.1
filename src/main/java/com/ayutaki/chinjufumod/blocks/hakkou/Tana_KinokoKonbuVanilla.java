package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Tana_KinokoKonbuVanilla extends BaseTana_Stage05 {

	public Tana_KinokoKonbuVanilla(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_5);

		/** Can harvest **/
		if (i == 5) {
			if (hItem == Items.GLASS_BOTTLE) {
				
				if (this == Hakkou_Blocks.VANILLA_TARU.get()) {
					int gHC = hStack.getCount();
					if (gHC >= 4) {
						CMEvents.consumeN_Hand(4, playerIn, hand);
						CMEvents.soundSnowTake(worldIn, pos);
						
						ItemStack take = new ItemStack(Items_Teatime.VANILLA_bot_14.get(), 4);
						if (hStack.isEmpty()) { playerIn.setItemInHand(hand, take); }
						else if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }
						
						worldIn.setBlock(pos, Hakkou_Blocks.HAKKOU_TARU.get().defaultBlockState()
								.setValue(Taru_Hakkou.STAGE_0_5, Integer.valueOf(3)), 3); }
					
					else { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
				}
				
				else {
					/** Collect with an Item **/
					CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.DASHI_bot_14.get());	
					worldIn.setBlock(pos, Hakkou_Blocks.HAKKOU_TARU.get().defaultBlockState()
							.setValue(Taru_Hakkou.STAGE_0_5, Integer.valueOf(3)), 3);
				}
			}
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** Too early to collect **/
		else { //i != 5
			CMEvents.textEarlyCollect(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		if (this == Hakkou_Blocks.KINOKO_TARU.get()) { blockTip.add(Component.translatable("tips.block_taru_kinoko").withStyle(ChatFormatting.GRAY)); }
		if (this == Hakkou_Blocks.KONBU_TARU.get()) { blockTip.add(Component.translatable("tips.block_taru_konbu").withStyle(ChatFormatting.GRAY)); }
		if (this == Hakkou_Blocks.VANILLA_TARU.get()) { blockTip.add(Component.translatable("tips.block_taru_vanilla").withStyle(ChatFormatting.GRAY)); }
	}
}
