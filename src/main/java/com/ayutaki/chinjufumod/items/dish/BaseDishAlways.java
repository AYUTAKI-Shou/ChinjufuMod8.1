package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.fuel.NoGroup_noFuel;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/* MilkBucketItem を参照。extends は BlockNamedItem とする */
public abstract class BaseDishAlways extends NoGroup_noFuel {

	public BaseDishAlways(Block block, Item.Properties props) {
		super(block, props);
	}

	@Override
	public abstract UseAction getUseAction(ItemStack stack);
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}
	
	protected abstract void addEffect(LivingEntity entityLiving);
	
	protected abstract Item remainItem();

	/* Finish RightClick Action */
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		PlayerEntity playerIn = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;

		/** add Potion Effect. Block×1.2, Item×1.0 **/
		if (!worldIn.isRemote) { this.addEffect(entityLiving); }
		
		/** add Item **/
		if (playerIn != null) {
			playerIn.addStat(Stats.ITEM_USED.get(this));

			if (!playerIn.abilities.isCreativeMode) {
				ItemStack take = new ItemStack(this.remainItem(), 1);
				if (stack.isEmpty()) { return take; }
				else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
				
				stack.shrink(1);
			}
		}
		return stack;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
		playerIn.setActiveHand(hand);
		return ActionResult.resultSuccess(playerIn.getHeldItem(hand));
	}

	/* Branch the process. */
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();

		if (context.getFace() == Direction.UP && (playerIn.isSneaking() || playerIn.isPassenger()) ) {
			return this.tryPlace(new BlockItemUseContext(context)); }

		else {
			return this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType(); }
	}

	/* ToolTip*/
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.block_simpledish").applyTextStyle(TextFormatting.GRAY));
	}
}
