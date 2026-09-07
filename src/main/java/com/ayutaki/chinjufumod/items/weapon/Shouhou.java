package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.base.IG_Chinjufu;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Shouhou extends IG_Chinjufu {

	public Shouhou(Item.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!worldIn.isRemote) {
			worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents_CM.PAGE_TURN, SoundCategory.PLAYERS, 1.2F, 1.0F);

			worldIn.addEntity(new ExperienceOrbEntity(worldIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), 100));
			hStack.shrink(1);
			
			return ActionResult.resultSuccess(hStack);
		}
		return ActionResult.resultSuccess(hStack);
	}
	
	/* ToolTip*/
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_shouhou").applyTextStyle(TextFormatting.GRAY));
	}
}
