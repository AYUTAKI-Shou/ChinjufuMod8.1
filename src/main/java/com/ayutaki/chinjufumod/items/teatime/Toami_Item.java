package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import com.ayutaki.chinjufumod.entity.ToamiEntity;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.tags.ItemCMTags;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class Toami_Item extends BlockItem implements ProjectileItem {

	public Toami_Item(Block blockIn, Item.Properties props) {
		super(blockIn, props.repairable(ItemCMTags.MATERIALS_YARN));
	}
	
	/* RightClick Action */
	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean mode = playerIn.getAbilities().instabuild;

		playerIn.playSound(SoundEvents_CM.THROW.get(), 1.0F, 1.0F);
		
		if (worldIn instanceof ServerLevel server) {
			ToamiEntity toami = new ToamiEntity(playerIn, server, hStack);
			int j = 6;

			if (mode) {
				toami.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 0.25F * j, 1.0F);
				worldIn.addFreshEntity(toami);
				playerIn.awardStat(Stats.ITEM_USED.get(this));
				playerIn.getInventory().removeItem(hStack);
				
				return InteractionResult.SUCCESS;
			} // World is CreativeMode.

			if (!mode) {
				int life = hStack.getMaxDamage() - hStack.getDamageValue();
				if (life <= 1) {
					worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
					hStack.shrink(1); 
					return InteractionResult.FAIL; }
				
				else {
					toami.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 0.25F * j, 1.0F);
					worldIn.addFreshEntity(toami);
					playerIn.awardStat(Stats.ITEM_USED.get(this));
					hStack.shrink(1); 
					
					return InteractionResult.SUCCESS; }
			} // World is not CreativeMode.
		}
		
		return InteractionResult.FAIL;
	}
	
	//////* BlockItem *///////////////////////////////////////////////
	/** Branch the process. **/
	@Override
	 public InteractionResult useOn(UseOnContext context) {
		return this.use(context.getLevel(), context.getPlayer(), context.getHand()); 
	} //for 1.21.4

	public InteractionResult place(BlockPlaceContext context) {
		return InteractionResult.FAIL;
	}

	protected boolean canPlace(BlockPlaceContext context, BlockState state) {
		return false;
	}
	
	@Override
	public Projectile asProjectile(Level worldIn, Position pos, ItemStack stack, Direction direct) {
		return new ToamiEntity(worldIn, pos.x(), pos.y(), pos.z(), stack);
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_toami").withStyle(ChatFormatting.GRAY));
	}
}
