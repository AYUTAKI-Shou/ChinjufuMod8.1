package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.SB_ZuiunEntity;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SB_Zuiun extends Abstract_Kansaiki {

	public SB_Zuiun(String name, int max) {
		super(name, max);
	}

	/* Called when the playerIn stops using an Item (stops holding the right mouse button). */
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer)entityLiving;
			boolean mode = playerIn.capabilities.isCreativeMode;
			ItemStack fuel = this.findAmmo(playerIn);

			int i = this.getMaxItemUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerIn, i, !fuel.isEmpty() || mode);
			if (i < 0) return;

			if (!fuel.isEmpty() || mode) {

				if (fuel.isEmpty()) { fuel = new ItemStack(Items_Weapon.KK_FUEL); }
				
				float charge = getArrowVelocity(i);
				if (!((double)charge < 0.1D)) {
					
					if (!worldIn.isRemote) {
						SB_ZuiunEntity kansaiki = new SB_ZuiunEntity(worldIn, playerIn, stack);
						int POWER = 5; // Add the speed and distance of the Entity.
						
						boolean carrier = (ShipTypes_CM.typeYURA(playerIn) || ShipTypes_CM.typeMOGAMI(playerIn) || ShipTypes_CM.typeTONE(playerIn)|| 
								ShipTypes_CM.typeISE(playerIn) || ShipTypes_CM.typeFUSOU(playerIn) || 
								ShipTypes_CM.typeI401(playerIn) || ShipTypes_CM.typeI13(playerIn));
						this.setDamageMove(stack, kansaiki, playerIn, POWER, carrier);
						if (carrier) { kansaiki.setCarrier(true); }
						
						worldIn.spawnEntity(kansaiki);
						
						if (mode) { playerIn.inventory.deleteStack(stack); } // mode
						if (!mode) {
							fuel.shrink(1);
							stack.shrink(1); } // !mode
					} // !worldIn.isClientSide
				} // charge
			} // !fuel.isEmpty()
			
		} // PlayerEntity
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(TextFormatting.DARK_GRAY + I18n.format("tips.item_kk.name"));
		itemTip.add(TextFormatting.GREEN + I18n.format("tips.item_kk_7f.name"));
		itemTip.add(TextFormatting.DARK_GRAY + I18n.format("tips.item_sb.name")); }
}
