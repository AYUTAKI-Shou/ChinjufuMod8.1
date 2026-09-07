package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.SB_SeiranEntity;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SB_Seiran extends Abstract_BowKansaiki {

	public SB_Seiran(Item.Properties props) {
		super(props);
	}

	@Override
	public void releaseUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			boolean mode = playerIn.abilities.instabuild;
			ItemStack fuel = playerIn.getProjectile(stack);
			
			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerIn, i, !fuel.isEmpty() || mode);

			if (i < 0) return;
			
			if (!fuel.isEmpty() || mode) {
				if (fuel.isEmpty()) { fuel = new ItemStack(Items_Weapon.KK_FUEL); }
				
				float charge = getArrowVelocity(i);
				if (!((double)charge < 0.1D)) {
					
					if (!worldIn.isClientSide) {
						SB_SeiranEntity kansaiki = new SB_SeiranEntity(playerIn, worldIn, stack);
						int POWER = 4; // Add the speed and distance of the Entity.
						
						boolean carrier = (ShipTypes_CM.typeYURA(playerIn) || ShipTypes_CM.typeMOGAMI(playerIn) || ShipTypes_CM.typeTONE(playerIn)|| 
								ShipTypes_CM.typeISE(playerIn) || ShipTypes_CM.typeFUSOU(playerIn) || 
								ShipTypes_CM.typeI401(playerIn) || ShipTypes_CM.typeI13(playerIn));
						this.setDamageMove(stack, kansaiki, playerIn, POWER, carrier);
						if (carrier) { kansaiki.setCarrier(true); }
						
						worldIn.addFreshEntity(kansaiki);
						
						if (mode) { playerIn.inventory.removeItem(stack); } // mode
						
						if (!mode) {
							fuel.shrink(1);
							stack.shrink(1); } // !mode
						
						playerIn.awardStat(Stats.ITEM_USED.get(this));
					} // !worldIn.isClientSide
				} // charge
			} // !fuel.isEmpty() 
			
		} // PlayerEntity
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_kk").withStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_kk_11f").withStyle(TextFormatting.GREEN));
		itemTip.add(new TranslationTextComponent("tips.item_sb").withStyle(TextFormatting.GRAY)); }
}
