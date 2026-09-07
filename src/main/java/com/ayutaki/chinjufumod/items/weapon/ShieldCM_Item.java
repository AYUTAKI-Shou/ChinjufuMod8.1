package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.addtab.IR_Armor;

import net.minecraft.block.BlockDispenser;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ShieldCM_Item extends IR_Armor {

	public ShieldCM_Item(String name, int max) {
		super(name);
		setUnlocalizedName(name);
		setMaxDamage(max);
		this.maxStackSize = 1;
		
		this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack hStack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == hStack ? 1.0F : 0.0F; } } );
		
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
	}

	/* The shield in 1.12.2 has a knockback. */
	
	/* returns the action that specifies what animation to play when the items is being used */
	public EnumAction getItemUseAction(ItemStack hStack) {
		return EnumAction.BLOCK;
	}

	/* How long it takes to use or consume an item */
	public int getMaxItemUseDuration(ItemStack hStack) {
		return 72000;
	}

	/* Called when the equipped item is right clicked. */
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack hStack = playerIn.getHeldItem(handIn);
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack);
	}

	/* 盾として消耗処理 */
	public boolean isShield(ItemStack hStack, @Nullable EntityLivingBase entity) {
		return (hStack.getItem() == Items.SHIELD || hStack.getItem()== ShieldCM_Item.this);
	}

	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return (repair.getItem() == Items.IRON_INGOT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_shield.name"));
	}
}
