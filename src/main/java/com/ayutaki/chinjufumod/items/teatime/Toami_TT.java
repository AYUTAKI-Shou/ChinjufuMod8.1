package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.entity.ToamiEntity;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.fuel.Teatime_noFuel;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class Toami_TT extends Teatime_noFuel {

	public Toami_TT(Block blockIn, Item.Properties props) {
		super(blockIn, props);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean mode = playerIn.getAbilities().instabuild;

		playerIn.playSound(SoundEvents_CM.THROW.get(), 1.0F, 1.0F);
		
		if (!worldIn.isClientSide) {
			ToamiEntity toami = new ToamiEntity(playerIn, worldIn, hStack);
			int j = 6;
			
			if (mode) {
				toami.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 0.25F * j, 1.0F);
				worldIn.addFreshEntity(toami);
				playerIn.awardStat(Stats.ITEM_USED.get(this));
				playerIn.getInventory().removeItem(hStack);
			} // World is CreativeMode.

			if (!mode) {
				int life = hStack.getMaxDamage() - hStack.getDamageValue();
				if (life <= 1) {
					worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
					hStack.shrink(1); }
				
				else {
					toami.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 0.25F * j, 1.0F);
					worldIn.addFreshEntity(toami);
					playerIn.awardStat(Stats.ITEM_USED.get(this));
					hStack.shrink(1); }
			} // World is not CreativeMode.
		}
		
		return InteractionResultHolder.sidedSuccess(hStack, worldIn.isClientSide());
	}
	
	/* Item repair material. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.STRING || material.getItem() == Items_Seasonal.ORIITO.get(); }

	
	//////* BlockItem *///////////////////////////////////////////////
	/** Branch the process. **/
	@Override
	 public InteractionResult useOn(UseOnContext context) {
		return this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
	}

	public InteractionResult place(BlockPlaceContext context) {
		return InteractionResult.FAIL;
	}

	protected boolean canPlace(BlockPlaceContext context, BlockState state) {
		return false;
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_toami").withStyle(ChatFormatting.GRAY));
	}
}
