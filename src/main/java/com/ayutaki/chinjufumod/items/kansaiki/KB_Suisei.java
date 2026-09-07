package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.KB_SuiseiEntity;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KB_Suisei extends Abstract_BowKansaiki {

	public KB_Suisei(Properties props) {
		super(props);
	}

	/* Called when the player stops using an Item (stops holding the right mouse button). */
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerIn = (PlayerEntity)entityLiving;
			boolean mode = playerIn.abilities.isCreativeMode;
			ItemStack fuel = playerIn.findAmmo(stack);

			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerIn, i, !fuel.isEmpty() || mode);

			if (i < 0) return;
			
			if (!fuel.isEmpty() || mode) {
				if (fuel.isEmpty()) { fuel = new ItemStack(Items_Weapon.KK_FUEL); }
				
				float charge = getArrowVelocity(i);
				if (!((double)charge < 0.1D)) {
				
					if (!worldIn.isRemote) {
						KB_SuiseiEntity kansaiki = new KB_SuiseiEntity(playerIn, worldIn, stack);
						int POWER = 6; // Add the speed and distance of the Entity.
						
						boolean carrier = ShipTypes_CM.typeCarrier(playerIn);
						this.setDamageMove(stack, kansaiki, playerIn, POWER, carrier);
						if (carrier) { kansaiki.setCarrier(true); }
						
						worldIn.addEntity(kansaiki);
						
						if (mode) { playerIn.inventory.deleteStack(stack); } // mode
						
						if (!mode) {
							fuel.shrink(1);
							stack.shrink(1); } // !mode
						
						playerIn.addStat(Stats.ITEM_USED.get(this));
					} // !worldIn.isClientSide
				} // charge
			} // !fuel.isEmpty() 
			
		} // PlayerEntity
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_kk").applyTextStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_kk_8f").applyTextStyle(TextFormatting.RED)); }
}
