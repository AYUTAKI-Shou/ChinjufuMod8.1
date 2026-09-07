package com.ayutaki.chinjufumod.items.sakuteki;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.addtab.IR_Armor;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class Bell_CM extends IR_Armor {

	public Bell_CM(String name) {
		super(name);
		setUnlocalizedName(name);
		setMaxStackSize(1);
	}

	protected void coolDown(World worldIn, EntityPlayer playerIn) {
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents_CM.SONAR, SoundCategory.PLAYERS, 0.25F, 1.25F);
		playerIn.getCooldownTracker().setCooldown(this, 100);
	}

	private void addGlow(EntityLiving entityLiving, int i) {
		World worldIn = entityLiving.world;
		/** add Potion Effect. **/
		if (!worldIn.isRemote) {
			
			if (entityLiving.isEntityAlive() && !entityLiving.isDead) { 
				entityLiving.addPotionEffect(new PotionEffect(new PotionEffect(MobEffects.GLOWING, 60))); }
		}
	}
	
	protected void list4Glow_Illager(List<AbstractIllager> list, int i) {
		/* for (型 変数名: コレクション) */
		for (AbstractIllager entityLiving: list) { this.addGlow(entityLiving, i); /** 繰り返し **/ };
	}
	
	protected void list4Glow_Witch(List<EntityWitch> list, int i) {
		for (EntityWitch entityLiving: list) { this.addGlow(entityLiving, i); };
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (playerIn.isInsideOfMaterial(Material.WATER)) {
			CMEvents.Item_Waterlogged(worldIn, playerIn);
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }

		else {
			if (playerIn.getCooldownTracker().hasCooldown(this)) { return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }
			
			else {
				this.coolDown(worldIn, playerIn);
				double x = playerIn.posX;
				double y = playerIn.posY + 1.0D;
				double z = playerIn.posZ;
				
				AxisAlignedBB AABB = new AxisAlignedBB(x - 32.0D, y - 32.0D, z - 32.0D, x + 32.0D, y + 32.0D, z + 32.0D);
				List<AbstractIllager> LIST_Illager = worldIn.getEntitiesWithinAABB(AbstractIllager.class, AABB);
				List<EntityWitch> LIST_Witch = worldIn.getEntitiesWithinAABB(EntityWitch.class, AABB);
				
				this.list4Glow_Illager(LIST_Illager, 1);
				this.list4Glow_Witch(LIST_Witch, 1);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }
		}
	}
}
