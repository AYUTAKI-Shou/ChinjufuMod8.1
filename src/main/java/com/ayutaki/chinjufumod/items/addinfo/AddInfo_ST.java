package com.ayutaki.chinjufumod.items.addinfo;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.IG_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AddInfo_ST extends IG_Seasonal {

	public AddInfo_ST(Properties props) {
		super(props);
	}
 
	/* アイテムは @Nullable World worldIn, ブロックは @Nullable IBlockReader worldIn*/
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		/** Seasonal **/
		if (this == Items_Seasonal.KURI) {
			itemTip.add(new TranslationTextComponent("tips.item_chestnut").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Seasonal.KURI_SWEET || this == Items_Seasonal.KURI_CHOCO) {
			itemTip.add(new TranslationTextComponent("tips.item_chestnutsweet").applyTextStyle(TextFormatting.GRAY)); }

		if (this == Items_Seasonal.COCOA_F) {
			itemTip.add(new TranslationTextComponent("tips.item_cocoa_ferm").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Seasonal.COCOA_R) {
			itemTip.add(new TranslationTextComponent("tips.item_cocoa_roast").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Seasonal.COCOA_M) {
			itemTip.add(new TranslationTextComponent("tips.item_cocoa_mass").applyTextStyle(TextFormatting.GRAY)); }
		if (this == Items_Seasonal.CHOCO_raw) {
			itemTip.add(new TranslationTextComponent("tips.item_choco_raw").applyTextStyle(TextFormatting.GRAY)); }
	}
}
