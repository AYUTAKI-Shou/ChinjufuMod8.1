package com.ayutaki.chinjufumod.items.weapon;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.entity.AbstractAmmo_Entity;
import com.ayutaki.chinjufumod.entity.AmmoEntity_Kijyuu;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.client.resources.I18n;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Triple_Kijyuu extends ItemBow {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
	private final TimeUnit milliS = TimeUnit.MILLISECONDS;
	private int SHOOTCOUNT = 0;
	
	public Triple_Kijyuu(String name, int max) {
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
		return 64;
	}

	public EnumAction getItemUseAction(ItemStack hStack) {
		return EnumAction.BOW;
	}

	/** Luck **/
	protected float playerLuck(EntityPlayer playerIn) {
		return (playerIn != null)? playerIn.getLuck() : 0.0F;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack hStack = playerIn.getHeldItem(handIn);
		boolean flag = !this.findAmmo(playerIn).isEmpty();

		this.SHOOTCOUNT = 0;
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(hStack, worldIn, playerIn, handIn, flag);
		if (ret != null) return ret;
		
		if (!playerIn.capabilities.isCreativeMode && !flag) {
			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.EMPTY_AMMO, SoundCategory.PLAYERS, 0.8F, 0.6F);
			playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.rightclick.empty_ammo.name", new Object[0]), true);
			return new ActionResult(EnumActionResult.FAIL, hStack); }
		
		else {
			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.SET_GUN, SoundCategory.PLAYERS, 0.8F, 0.8F);
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }
	}
	
	
	public void onUsingTick(ItemStack hStack, EntityLivingBase entityIn, int timeLeft) {
		if (entityIn instanceof EntityPlayer) {
			EntityPlayer playerIn = (EntityPlayer)entityIn;
			World worldIn = entityIn.world;
			
			boolean mode = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, hStack) > 0;
			ItemStack projectile = this.findAmmo(playerIn);
			
			int i = this.getMaxItemUseDuration(hStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(hStack, worldIn, playerIn, i, !projectile.isEmpty() || mode);
			if (i < 0) return;
			
			if (!projectile.isEmpty() || mode) {
				/** Ammo item instance. Entity to be fired. **/
				if (projectile.isEmpty()) { projectile = new ItemStack(Items_Weapon.AMMUNITION_K); }
				
				float charge = getArrowVelocity(i);
				if (charge >= 1.0F) {
					
					if(!playerIn.getCooldownTracker().hasCooldown(this)) {
						playerIn.getCooldownTracker().setCooldown(this, 5); //50ms * 5
						boolean mode1 = playerIn.capabilities.isCreativeMode || (projectile.getItem() instanceof Ammo_Kijyuu && ((Ammo_Kijyuu) projectile.getItem()).isInfinite(projectile, hStack, playerIn));

						if (!worldIn.isRemote) {
							int localCount = SHOOTCOUNT;
							/* Ammo item instance. Entity to be fired. */
							Ammo_Kijyuu arrowItem = (Ammo_Kijyuu)(projectile.getItem() instanceof Ammo_Kijyuu ? projectile.getItem() : Items_Weapon.AMMUNITION_S);
							AmmoEntity_Kijyuu abstractArrow = (AmmoEntity_Kijyuu) arrowItem.createAmmo(worldIn, projectile, playerIn);

							/* Damage */
							int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, hStack);
							boolean LUCK = this.playerLuck(playerIn) > 0.0F;
							int A = LUCK? 2 : 6;
							double CRITICAL = (worldIn.rand.nextInt(A) == 0)? 0.5D : 0.0D;
							double LEVEL = (playerIn.experienceLevel >= 25)? 1.0D : ((playerIn.experienceLevel >= 19 && playerIn.experienceLevel < 25)? 0.5D : 0.0D);

							abstractArrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 6.0F, 1.0F);
							if (j == 0) { abstractArrow.setDamage(abstractArrow.getDamage() + LEVEL + CRITICAL); }
							if (j > 0) { abstractArrow.setDamage(abstractArrow.getDamage() + (double)j * 0.5D + LEVEL + CRITICAL); }
				
							/* add PUNCH I. */							
							int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, hStack);
							if (k > 0) { abstractArrow.setKnockbackStrength(k); }

							if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, hStack) > 0) { abstractArrow.setFire(100); }

							if (mode1 || playerIn.capabilities.isCreativeMode) {
								abstractArrow.pickupStatus = AmmoEntity_Kijyuu.PickupStatus.CREATIVE_ONLY; }

							worldIn.spawnEntity(abstractArrow);
							localCount = localCount + 1;
							SHOOTCOUNT = localCount;
							worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.AM_FIRE, SoundCategory.PLAYERS, 0.2F, 1.8F);
							
							if (!mode1 && !playerIn.capabilities.isCreativeMode) {
								scheduler.schedule(() -> this.shrinkAMMO(worldIn, playerIn, hStack), 60, milliS);
								scheduler.schedule(() -> this.dropSOUND(worldIn, playerIn), 90, milliS); }
						} //!worldIn.isClientSide
					} //Cooldown
				} //charge
			}// !isEmpty
		} // entityIn
		super.onUsingTick(hStack, entityIn, timeLeft);
	}
	
	private void shrinkAMMO(World worldIn, EntityPlayer playerIn, ItemStack hStack) {
		ItemStack projectile = this.findAmmo(playerIn);
		projectile.shrink(1);
		playerIn.dropItem(new ItemStack(Items_NoTab.CARTRIDGE_K), false);
		
		if (projectile.isEmpty()) { 
			playerIn.inventory.deleteStack(projectile);
			playerIn.stopActiveHand();
			CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
	}

	private void dropSOUND(World worldIn, EntityPlayer playerIn) {
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.AM_CARTRIDGE_K, 
				SoundCategory.BLOCKS, 1.2F, 0.8F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
	}

	/* Damage to the tool. [finish] */
	@Override
	public ItemStack onItemUseFinish(ItemStack hStack, World worldIn, EntityLivingBase entityIn) {
		if (entityIn instanceof EntityPlayer) {
			if (!worldIn.isRemote) {
				EntityPlayer playerIn = (EntityPlayer)entityIn;
				CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
		}
		return hStack;
	}
	
	/* Damage to the tool. [release] */
	@Override
	public void onPlayerStoppedUsing(ItemStack hStack, World worldIn, EntityLivingBase entityIn, int timeLeft) { 
		if (entityIn instanceof EntityPlayer) {
			if (!worldIn.isRemote) {
				EntityPlayer playerIn = (EntityPlayer)entityIn;
				CMEvents.toolDamege(this.SHOOTCOUNT, playerIn, hStack); }
		}
	}
	
	/* If not set to true, it will come off when damage is added to it. */
	@Override
	public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
		return true;
	}
	
	/* Ammo to be used. */
	protected boolean isArrow(ItemStack hStack) {
		return hStack.getItem() instanceof Ammo_Kijyuu;
	}
	
	public AbstractAmmo_Entity customizeArrow(AbstractAmmo_Entity arrow) {
		return arrow;
	}
	
	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return (repair.getItem() == Items.IRON_INGOT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_3rensou_kijyuu.name"));
		itemTip.add(I18n.format("tips.item_3rensou_kijyuu2.name"));
		itemTip.add(TextFormatting.DARK_GREEN + I18n.format("tips.item_3rensou_kijyuu3.name"));
	}
}
