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

	static int localCount =20;
	
	public Base_Submarine(IArmorMaterial material, EquipmentSlotType slot, Properties props) {
		super(material, slot, props);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entity, int slot, boolean selected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entity;
			if (!playerIn.abilities.instabuild) {
				
				if (ShipTypes_CM.typeSubmarine(playerIn)) {
					boolean SENSUI = playerIn.isEyeInFluid(FluidTags.WATER);
					
					if (SENSUI) {
						if (localCount <= 0) {
							playerIn.displayClientMessage(new TranslationTextComponent("count.amount.0").withStyle(TextFormatting.RED), true); }

						else {
							boolean hasBREATH = playerIn.hasEffect(Effects.WATER_BREATHING);
							if (!hasBREATH) {
								--localCount;
								/** add Potion Effect. **/
								playerIn.addEffect(new EffectInstance(Effects.WATER_BREATHING, 500, 0));//Using !worldIn.isClientSide causes the numbers to be wrong.

								if (localCount > 5) { 
									playerIn.displayClientMessage(new TranslationTextComponent("count.amount." + localCount).withStyle(TextFormatting.AQUA), true); }
								if (localCount >= 2 && localCount <= 5) { 
									playerIn.displayClientMessage(new TranslationTextComponent("count.amount." + localCount).withStyle(TextFormatting.YELLOW), true); }
								if (localCount < 2) { 
									playerIn.displayClientMessage(new TranslationTextComponent("count.amount." + localCount).withStyle(TextFormatting.RED),true); } }
							
							if (hasBREATH) { 
								int airMAX = playerIn.getMaxAirSupply();
								if ((playerIn.getAirSupply() != airMAX)) { playerIn.setAirSupply(airMAX); }
							}
						}
					} //isUnderWater()
					
					if (!SENSUI && localCount != 20) {
						localCount = 20;
						playerIn.displayClientMessage(new TranslationTextComponent("count.amount." + localCount), true); }
				}//typeSubmarine
			}//Creative
		}//Player
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_sabumarine").withStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_sabumarine2").withStyle(TextFormatting.GRAY));
	}
}
