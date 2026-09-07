package com.ayutaki.chinjufumod.items.dish;

import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class Dish_Plate extends BaseDishNeeds {

	public Dish_Plate(Block block, Item.Properties props) {
		super(block, props.usingConvertsTo(Items_Teatime.SARA.get()));
	}
	
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
		Player playerIn = entityLiving instanceof Player ? (Player)entityLiving : null;
		
		/** add Item **/
		if (playerIn != null) {
			playerIn.awardStat(Stats.ITEM_USED.get(this));

			if (!playerIn.getAbilities().instabuild) {
				if (this == Items_Teatime.PASTASEAFOOD.get()) {
					ItemStack shell = new ItemStack(Items_NoTab.HAMAGURI_KARA.get(), 3);
					if (stack.isEmpty()) { return shell; }
					else if (!playerIn.getInventory().add(shell)) { playerIn.drop(shell, false); } }
				
				stack.shrink(1);
			}
		}
		return stack;
	}
}
