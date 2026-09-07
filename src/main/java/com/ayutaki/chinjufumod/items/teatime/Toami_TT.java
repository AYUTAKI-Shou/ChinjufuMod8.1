package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.ToamiEntity;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.fuel.Teatime_noFuel;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Toami_TT extends Teatime_noFuel {

	public Toami_TT(Block blockIn, Item.Properties props) {
		super(blockIn, props);
	}

	/* RightClick Action*/
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean mode = playerIn.abilities.isCreativeMode;

		playerIn.playSound(SoundEvents_CM.THROW, 1.0F, 1.0F);
		
		if (!worldIn.isRemote) {
			ToamiEntity toami = new ToamiEntity(playerIn, worldIn, hStack);
			int j = 6;

			if (mode) {
				toami.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.25F * j, 1.0F);
				worldIn.addEntity(toami);
				playerIn.inventory.deleteStack(hStack); } // World is CreativeMode.

			if (!mode) {
				int life = hStack.getMaxDamage() - hStack.getDamage();
				if (life <= 1) {
					worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F);
					hStack.shrink(1); }

				else {
					toami.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.25F * j, 1.0F);
					worldIn.addEntity(toami);
					hStack.shrink(1); } } // World is not CreativeMode.
		}

		return ActionResult.resultSuccess(hStack);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.STRING || material.getItem() == Items_Seasonal.ORIITO; }
	
	//////* BlockItem *///////////////////////////////////////////////
	/** 設置処理の分岐 **/
	public ActionResultType onItemUse(ItemUseContext context) {
		return this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType();
	}

	public ActionResultType tryPlace(BlockItemUseContext context) {
		return ActionResultType.FAIL;
	}

	protected boolean canPlace(BlockItemUseContext context, BlockState state) {
		return false;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_toami").applyTextStyle(TextFormatting.GRAY));
	}
}
