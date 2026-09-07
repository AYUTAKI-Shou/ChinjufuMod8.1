package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MeasureCupFull_TT extends IR_Teatime {

	public MeasureCupFull_TT(String name) {
		super(name);
		setUnlocalizedName(name);
		
		setContainerItem(Items_Teatime.KEIRYO_CUP);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_Teatime.KEIRYO_CUP, 1, 0);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.PLAYERS, 1.0F, 1.0F);
		CMEvents.take1Item(playerIn, hand, Items_Teatime.KEIRYO_CUP, 0);

		CMEvents.consumeN_Hand(1, playerIn, hand);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack);
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_measurecup_full.name"));
	}
}
