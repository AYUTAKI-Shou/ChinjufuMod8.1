package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.ShipTypes_CM;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public abstract class Base_Submarine extends Abstract_ArmorItem {

	static int localCount =20;
	
	public Base_Submarine(ArmorMaterial material, EquipmentSlot slot, Item.Properties props) {
		super(material, slot, props);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entity, int slot, boolean selected) {
		if (entity != null && entity instanceof Player) {
			Player playerIn = (Player)entity;
			if (!playerIn.getAbilities().instabuild) {
				
				if (ShipTypes_CM.typeSubmarine(playerIn)) {
					boolean SENSUI = playerIn.isEyeInFluid(FluidTags.WATER);
					
					if (SENSUI) {
						if (localCount <= 0) {
							playerIn.displayClientMessage(new TranslatableComponent("count.amount.0").withStyle(ChatFormatting.RED), true); }
						
						else {
							boolean hasBREATH = playerIn.hasEffect(MobEffects.WATER_BREATHING);
							if (!hasBREATH) {
								--localCount;
								playerIn.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 500, 0));
								
								if (localCount > 5) { 
									playerIn.displayClientMessage(new TranslatableComponent("count.amount." + localCount).withStyle(ChatFormatting.AQUA), true); }
								if (localCount >= 2 && localCount <= 5) { 
									playerIn.displayClientMessage(new TranslatableComponent("count.amount." + localCount).withStyle(ChatFormatting.YELLOW), true); }
								if (localCount < 2) { 
									playerIn.displayClientMessage(new TranslatableComponent("count.amount." + localCount).withStyle(ChatFormatting.RED), true); } }
							
							if (hasBREATH) { 
								int airMAX = playerIn.getMaxAirSupply();
								if ((playerIn.getAirSupply() != airMAX)) { playerIn.setAirSupply(airMAX); }
							}
						}
					} //isUnderWater()
					
					if (!SENSUI && localCount != 20) {
						localCount = 20;
						playerIn.displayClientMessage(new TranslatableComponent("count.amount." + localCount), true); }
				}//typeSubmarine
			}//Creative
		}//Player
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_sabumarine").withStyle(ChatFormatting.GRAY));
		itemTip.add(new TranslatableComponent("tips.item_sabumarine2").withStyle(ChatFormatting.GRAY));
	}
}
