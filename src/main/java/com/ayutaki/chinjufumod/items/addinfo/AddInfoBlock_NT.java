package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.fuel.NoGroup_noFuel;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AddInfoBlock_NT extends NoGroup_noFuel {

	public AddInfoBlock_NT(Block block, Item.Properties props) {
		super(block, props);
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		if (this == Items_Teatime.KOUBOBOT_full) {
			itemTip.add(new TranslationTextComponent("tips.block_bin_koubo").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Teatime.NYUSANBOT_full) {
			itemTip.add(new TranslationTextComponent("tips.block_bin_nyusan").withStyle(TextFormatting.GRAY)); }
		
		if (this == Items_Seasonal.SNOWCORE) {
			itemTip.add(new TranslationTextComponent("tips.block_snowcore").withStyle(TextFormatting.GRAY)); }
		if (this == Items_Seasonal.SNOWMAN) {
			itemTip.add(new TranslationTextComponent("tips.block_snowman").withStyle(TextFormatting.GRAY)); }
	}
}
