package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ItemGroups_CM;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MizuokeMilk_TT extends MilkBucketItem {

	public MizuokeMilk_TT(Item.Properties props) {
		super(props.group(ItemGroups_CM.TEATIME));
	}

	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {

		/** 牛乳入りバケツの効果を呼び出す **/
		if (!worldIn.isRemote) entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));

		if (entityLiving instanceof ServerPlayerEntity) {
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.addStat(Stats.ITEM_USED.get(this));
		}

		if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.isCreativeMode) {
			PlayerEntity playerIn = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;

			ItemStack take = new ItemStack(Items_Teatime.MIZUOKE, 1);
			if (stack.isEmpty()) { return take; }
			else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }

			stack.shrink(1);
		}

		return stack;
	}

	/* ToolTip*/
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_mizuoke_milk").applyTextStyle(TextFormatting.GRAY));
	}
}
