package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import com.ayutaki.chinjufumod.items.fuel.Not_Fuel;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public abstract class BaseDishAlways extends Not_Fuel {

	public BaseDishAlways(Block block, Item.Properties props) {
		super(block, props);
	}
	
	/** Need Animation. **/
	@Override
	public abstract UseAnim getUseAnimation(ItemStack stack);
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}
	
	protected abstract void addEffect(LivingEntity entityLiving);
	
	protected abstract Item remainItem();
	
	/* Finish RightClick Action. 8*/
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		Player playerIn = entityLiving instanceof Player ? (Player)entityLiving : null;
		
		/** add Potion Effect. Block×1.2, Item×1.0 **/
		if (!worldIn.isClientSide) { this.addEffect(entityLiving); }
		
		/** add Item **/
		if (playerIn != null) {
			playerIn.awardStat(Stats.ITEM_USED.get(this));

			if (!playerIn.getAbilities().instabuild) {
				ItemStack take = new ItemStack(this.remainItem(), 1);
				if (stack.isEmpty()) { return take; }
				else if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }

				stack.shrink(1);
			}
		}
		return stack;
	}
	
	 public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		playerIn.startUsingItem(hand);
		return InteractionResultHolder.consume(playerIn.getItemInHand(hand));
	}
	 
	/* Branch the process. */
	@Override
	 public InteractionResult useOn(UseOnContext context) {
		Player playerIn = context.getPlayer();

		if (context.getClickedFace() == Direction.UP && (playerIn.isCrouching() || playerIn.isPassenger())) {
			return this.place(new BlockPlaceContext(context)); }

		else {
			return this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult(); }
	 }

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.block_simpledish").withStyle(ChatFormatting.GRAY));
	}
}
