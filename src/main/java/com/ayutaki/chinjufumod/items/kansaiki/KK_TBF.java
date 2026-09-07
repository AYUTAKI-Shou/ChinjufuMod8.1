package com.ayutaki.chinjufumod.items.kansaiki;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.KK_TBFEntity;
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

public class KK_TBF extends Abstract_Kansaiki {

	public KK_TBF(String name, int max) {
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
						KK_TBFEntity kansaiki = new KK_TBFEntity(worldIn, playerIn, stack);
						int POWER = 8; // Add the speed and distance of the Entity.
						
						boolean carrier = ShipTypes_CM.typeCarrier(playerIn);
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
		itemTip.add(I18n.format("tips.item_kk.name"));
		itemTip.add(TextFormatting.BLUE + I18n.format("tips.item_kk_11f.name")); }
}
