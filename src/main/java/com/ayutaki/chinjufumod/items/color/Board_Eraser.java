package com.ayutaki.chinjufumod.items.color;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.IG_Chinjufu;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Board_Eraser extends IG_Chinjufu {

	public Board_Eraser(Properties props) {
		super(props.durability(32));
	}
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_eraser").withStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_eraser2").withStyle(TextFormatting.GRAY));
	}
}
