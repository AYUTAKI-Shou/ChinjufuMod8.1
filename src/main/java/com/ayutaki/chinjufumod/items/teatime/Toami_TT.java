package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.ToamiEntity;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Toami_TT extends IR_Teatime {

	public Toami_TT(String name) {
		super(name);
		setUnlocalizedName(name);

		setMaxStackSize(1);
		setMaxDamage(32);
	}

	/* RightClick Action */
	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, @Nonnull EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean mode = playerIn.capabilities.isCreativeMode;
		
		playerIn.playSound(SoundEvents_CM.THROW, 1.0F, 1.0F);
		
		if (!worldIn.isRemote) {
			ToamiEntity toami = new ToamiEntity(worldIn, playerIn, hStack);
			int j = 6; // Add the speed and distance of the Entity.
			
			if (mode) {
				toami.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.25F * j, 1.0F);
				worldIn.spawnEntity(toami);
				playerIn.inventory.deleteStack(hStack); }

			if (!mode) {
				int life = hStack.getMaxDamage() - hStack.getItemDamage();
				if (life <= 1) {
					worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.MASTER, 1.0F, 1.0F);
					hStack.shrink(1); }

				else {
					toami.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.25F * j, 1.0F);
					worldIn.spawnEntity(toami);
					hStack.shrink(1); } }
			}

		return ActionResult.newResult(EnumActionResult.SUCCESS, hStack);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return (repair.getItem() == Item.getItemFromBlock(Blocks.WEB));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_toami.name"));
	}
}
