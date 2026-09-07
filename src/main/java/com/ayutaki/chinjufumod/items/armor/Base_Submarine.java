package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.ShipTypes_CM;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Base_Submarine extends Abstract_ArmorItem {

	/* 65/3kt=21 max8kt 6, 60/3kt=20 max5.5kt, 64/4kt=16 max7.7kt -> 20h 1h=1000tick Cut performance in half. */
	static int localCount =20;
	
	public Base_Submarine(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
		super(material, slot, props);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entity, int slot, boolean selected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entity;
			if (!playerIn.abilities.isCreativeMode) {

				if (ShipTypes_CM.typeSubmarine(playerIn)) {
					boolean SENSUI = playerIn.areEyesInFluid(FluidTags.WATER, true);
					
					if (SENSUI) {
						if (localCount <= 0) {
							playerIn.sendStatusMessage(new TranslationTextComponent("count.amount.0").applyTextStyle(TextFormatting.RED), true); }
						
						else {
							boolean hasBREATH = playerIn.isPotionActive(Effects.WATER_BREATHING);
							if (!hasBREATH) {
								--localCount;
								playerIn.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 500, 0));
								
								if (localCount > 5) { 
									playerIn.sendStatusMessage(new TranslationTextComponent("count.amount." + localCount).applyTextStyle(TextFormatting.AQUA), true); }
								if (localCount >= 2 && localCount <= 5) { 
									playerIn.sendStatusMessage(new TranslationTextComponent("count.amount." + localCount).applyTextStyle(TextFormatting.YELLOW), true); }
								if (localCount < 2) { 
									playerIn.sendStatusMessage(new TranslationTextComponent("count.amount." + localCount).applyTextStyle(TextFormatting.RED), true); } }
							
							if (hasBREATH) { 
								int airMAX = playerIn.getMaxAir();
								if ((playerIn.getAir() != airMAX)) { playerIn.setAir(airMAX); }
							}
						}
					} //isUnderWater()
					
					if (!SENSUI && localCount != 20) {
						localCount = 20; 
						playerIn.sendStatusMessage(new TranslationTextComponent("count.amount." + localCount), true); }
				}//typeSubmarine
			}//Creative
		}//Player
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_sabumarine").applyTextStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_sabumarine2").applyTextStyle(TextFormatting.GRAY));
	}
}
