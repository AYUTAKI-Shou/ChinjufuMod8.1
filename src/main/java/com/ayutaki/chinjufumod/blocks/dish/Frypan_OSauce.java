package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Frypan_OSauce extends BaseFrypan_2Cook {

	public Frypan_OSauce(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_2);

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (hItem == Items.GLASS_BOTTLE) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, Items_Teatime.OSAUCE_bot_14.get());
		
				worldIn.setBlock(pos, Dish_Blocks.FRYPAN_kara.get().defaultBlockState()
						.setValue(Frypan_kara.H_FACING, state.getValue(H_FACING))
						.setValue(Frypan_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
			
			if (hItem != Items.GLASS_BOTTLE) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, blockTip, tipFlag);
		blockTip.add(Component.translatable("tips.block_food_frypan_osauce").withStyle(ChatFormatting.GRAY));
	}
}
