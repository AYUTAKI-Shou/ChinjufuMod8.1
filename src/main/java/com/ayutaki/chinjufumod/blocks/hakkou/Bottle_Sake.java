package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Bottle_Sake extends Base_Bottle {
	
	public Bottle_Sake(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_5);

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			if (hItem == Items_Teatime.DRINKGLASS.get()) {
				/** Collect with an Item **/
				CMEvents.consumeN_Hand(1, playerIn, hand);
				worldIn.playSound(null, pos, SoundEvents_CM.SAKE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
				CMEvents.take1Item(playerIn, hand, this.takeItem());

				worldIn.setBlock(pos, state.setValue(Base_Bottle.STAGE_1_5, Integer.valueOf(i + 1)), 3); }
			
			if (hItem != Items_Teatime.DRINKGLASS.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeItem() {
		if (this == Hakkou_Blocks.NAMASAKEBOT.get()) { return Items_Teatime.NAMASAKEGLASS.get(); }
		if (this == Hakkou_Blocks.SAKEBOT.get()) { return Items_Teatime.SAKEGLASS.get(); }
		if (this == Hakkou_Blocks.JUKUSAKEBOT.get()) { return Items_Teatime.JUKUSAKEGLASS.get(); }
		if (this == Hakkou_Blocks.CIDERBOT.get()) { return Items_Teatime.CIDERGLASS.get(); }
		if (this == Hakkou_Blocks.JUKUCIDERBOT.get()) { return Items_Teatime.JUKUCIDERGLASS.get(); }
		if (this == Hakkou_Blocks.WINEBOT.get()) { return Items_Teatime.WINEGLASS.get(); }
		if (this == Hakkou_Blocks.JUKUWINEBOT.get()) { return Items_Teatime.JUKUWINEGLASS.get(); }
		if (this == Hakkou_Blocks.MEADBOT.get()) { return Items_Teatime.MEADGLASS.get(); }
		else { return Items_Teatime.JUKUMEADGLASS.get(); }
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(new TranslatableComponent("tips.block_bot_sake").withStyle(ChatFormatting.GRAY));
	}
}
