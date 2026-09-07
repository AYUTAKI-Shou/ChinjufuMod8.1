package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Abstract_RensouHou extends ItemBow {

	public Abstract_RensouHou(String name, int max) {
		super();
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		setUnlocalizedName(name);
		setCreativeTab(ChinjufuModTabs.CMARMOR);
		setMaxDamage(max);
		this.maxStackSize = 1;
		
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack hStack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == hStack ? 1.0F : 0.0F; } } );
	}

	/* Abstract */
	public abstract void onPlayerStoppedUsing(ItemStack hStack, World worldIn, EntityLivingBase entityLiving, int timeLeft);

	protected abstract boolean isArrow(ItemStack hStack);
	
	@SideOnly(Side.CLIENT)
	public abstract void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced);

	/** Luck **/
	protected float playerLuck(EntityPlayer playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}
	
	/* Ammo */
	protected void setAmmoDamage(ItemStack hStack, World worldIn, AbstractAmmo_Entity abstractArrow, EntityPlayer playerIn, double addKOUKEI, double fixFIT, boolean shipType) {
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, hStack);
		
		double FIT = (shipType)? fixFIT : 0.0D;
		int pLevel = playerIn.experienceLevel;
		double LEVEL = (pLevel >= 25)? 1.5D : ((pLevel>= 19 && pLevel< 25)? 1.0D : ((pLevel>= 12 && pLevel< 19)? 0.5D : 0.0D));
		
		boolean LUCK = this.playerLuck(playerIn) > 0.0F;
		double criticalL = LUCK? ((worldIn.rand.nextInt(2) == 0)? 1.5D : 1.0D) : 1.0D;
		double NORMAL = (abstractArrow.getDamage() + addKOUKEI + FIT + LEVEL);
		double POWER = (abstractArrow.getDamage() + addKOUKEI + (double)j * 0.5D + FIT + LEVEL);
		
		if (j == 0) { abstractArrow.setDamage(NORMAL * criticalL); }
		if (j > 0) { abstractArrow.setDamage(POWER * criticalL); }
	}
	
	protected void setAmmoEnchant(ItemStack hStack, AbstractAmmo_Entity abstractArrow) {
		int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, hStack);
		if (k == 0) { abstractArrow.setKnockbackStrength(1); }
		if (k > 0) { abstractArrow.setKnockbackStrength(k + 1); }

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, hStack) > 0) { 
			abstractArrow.setFire(100); }
	}
	
	/* private -> protected */
	protected ItemStack findAmmo(EntityPlayer playerIn) {

		if (this.isArrow(playerIn.getHeldItem(EnumHand.OFF_HAND))) {
			return playerIn.getHeldItem(EnumHand.OFF_HAND); }

		else if (this.isArrow(playerIn.getHeldItem(EnumHand.MAIN_HAND))) {
			return playerIn.getHeldItem(EnumHand.MAIN_HAND); }

		else {
			for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
				ItemStack hStack = playerIn.inventory.getStackInSlot(i);

				if (this.isArrow(hStack)) { return hStack; }
			}
			return ItemStack.EMPTY; }
	}

	/* 引き絞りに寄って矢に与える速度 */
	public static float getArrowVelocity(int charge) {
		float f = (float)charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) { f = 1.0F; }
		return f;
	}
	
	public int getMaxItemUseDuration(ItemStack hStack) {
		return 72000;
	}

	public EnumAction getItemUseAction(ItemStack hStack) {
		return EnumAction.BOW;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean flag = !this.findAmmo(playerIn).isEmpty();
		
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(hStack, worldIn, playerIn, hand, flag);
		if (ret != null) return ret;
		
		if (!playerIn.capabilities.isCreativeMode && !flag) {
			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.EMPTY_AMMO, SoundCategory.PLAYERS, 0.8F, 0.6F);
			playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.empty_ammo.name", new Object[0]), true);
			return new ActionResult(EnumActionResult.FAIL, hStack); }
		
		else {
			if(!playerIn.getCooldownTracker().hasCooldown(this)) {
				worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.SET_GUN, SoundCategory.PLAYERS, 0.8F, 0.8F);
				playerIn.setActiveHand(hand);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }
			
			else { return new ActionResult(EnumActionResult.FAIL, hStack); }
		}
	}

	public AbstractAmmo_Entity customizeArrow(AbstractAmmo_Entity arrow) {
		return arrow;
	}
	
	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return (repair.getItem() == Items.IRON_INGOT);
	}
}
