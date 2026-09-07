package com.ayutaki.chinjufumod.items.armor;

import java.util.List;

import com.ayutaki.chinjufumod.handler.ShipTypes_CM;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;

public class Base_Submarine extends Base_ArmorItem {

	static int localCount =20;
	
	public Base_Submarine(ArmorMaterial material, ArmorType slot, Item.Properties props) {
		super(material, slot, props);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entity, int slot, boolean flag) {
		if (entity != null && entity instanceof Player playerIn) {
			if (!playerIn.getAbilities().instabuild) {
				
				if (ShipTypes_CM.typeSubmarine(playerIn)) {
					boolean SENSUI = playerIn.isEyeInFluidType(NeoForgeMod.WATER_TYPE.value());
					
					if (SENSUI) {
						if (localCount <= 0) { 
							playerIn.displayClientMessage(Component.translatable("count.amount.0").withStyle(ChatFormatting.RED), true); }
						
						else {
							boolean hasBREATH = playerIn.hasEffect(MobEffects.WATER_BREATHING);
							if (!hasBREATH) {
								--localCount;
								playerIn.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 500, 0));
								
								if (localCount > 5) { 
									playerIn.displayClientMessage(Component.translatable("count.amount." + localCount).withStyle(ChatFormatting.AQUA), true); }
								if (localCount >= 2 && localCount <= 5) { 
									playerIn.displayClientMessage(Component.translatable("count.amount." + localCount).withStyle(ChatFormatting.YELLOW), true); }
								if (localCount < 2) { 
									playerIn.displayClientMessage(Component.translatable("count.amount." + localCount).withStyle(ChatFormatting.RED), true); } }
							
							if (hasBREATH) { 
								int airMAX = playerIn.getMaxAirSupply();
								if ((playerIn.getAirSupply() != airMAX)) { playerIn.setAirSupply(airMAX); }
							}
						}
					} //isUnderWater()
					
					if (!SENSUI && localCount != 20) {
						localCount = 20;
						playerIn.displayClientMessage(Component.translatable("count.amount." + localCount), true); }
				}//typeSubmarine
			}//Creative
		}//Player
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_sabumarine").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_sabumarine2").withStyle(ChatFormatting.GRAY));
	}
}
