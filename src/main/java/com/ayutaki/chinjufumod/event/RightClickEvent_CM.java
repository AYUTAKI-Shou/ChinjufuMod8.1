package com.ayutaki.chinjufumod.event;

import java.util.Arrays;
import java.util.UUID;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.items.color.Hake_Blue;
import com.ayutaki.chinjufumod.items.color.Hake_Brown;
import com.ayutaki.chinjufumod.items.color.Hake_Cyan;
import com.ayutaki.chinjufumod.items.color.Hake_Gray;
import com.ayutaki.chinjufumod.items.color.Hake_Green;
import com.ayutaki.chinjufumod.items.color.Hake_LightBlue;
import com.ayutaki.chinjufumod.items.color.Hake_LightGray;
import com.ayutaki.chinjufumod.items.color.Hake_Lime;
import com.ayutaki.chinjufumod.items.color.Hake_Magenta;
import com.ayutaki.chinjufumod.items.color.Hake_Orange;
import com.ayutaki.chinjufumod.items.color.Hake_Pink;
import com.ayutaki.chinjufumod.items.color.Hake_Purple;
import com.ayutaki.chinjufumod.items.color.Hake_Red;
import com.ayutaki.chinjufumod.items.color.Hake_White;
import com.ayutaki.chinjufumod.items.color.Hake_Yellow;
import com.ayutaki.chinjufumod.network.OpenUI_Sign;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.PacketDistributor;

public class RightClickEvent_CM {

	@SubscribeEvent
	public void editSign(RightClickBlock event) {
		Player player = event.getEntity();
		if (player != null) {
			
			Level world = event.getLevel();
			BlockPos pos = new BlockPos(event.getPos());
			BlockState state = world.getBlockState(pos);
			
			/* SignBlock */
			if (state.getBlock() instanceof SignBlock) {
				BlockEntity tileEntity = world.getBlockEntity(pos);
				ItemStack hStack = player.getMainHandItem();
				Item hItem = hStack.getItem();
				boolean mode = player.getAbilities().instabuild;

				if (tileEntity instanceof SignBlockEntity sign) {
					if (hItem instanceof Base_Hake) {

						if (sign.isWaxed()) {
							world.playSound(null, sign.getBlockPos(), sign.getSignInteractionFailedSoundEvent(), SoundSource.BLOCKS); }
						
						else {
							player.playSound(SoundEvents_CM.PAINT.get(), 1.0F, 1.0F);
							boolean front = sign.isFacingFrontText(player);
							
							if(this.tryApplyToSign(world, sign, front, player, hItem)) {
								sign.executeClickCommandsIfPresent(player, world, pos, front); }
							
							if(!world.isClientSide()) {
								if(!this.otherPlayerIsEditingSign(player, sign) && this.hasEditableText(player, sign, front)) {
									sign.setAllowedPlayerEditor(player.getUUID());
									ChinjufuMod.CHANNEL.send(new OpenUI_Sign(player.getUUID(), sign.getBlockPos()), PacketDistributor.ALL.noArg()); } 

								hStack.hurtAndBreak(mode? 0 : 1, player.getRandom(), player instanceof ServerPlayer user ? user : null, 
										() -> {
											player.broadcastBreakEvent(EquipmentSlot.MAINHAND);
											hStack.shrink(1);
											ItemStack take = new ItemStack(Items_Wadeco.HAKE.get(), 1);
											if (!player.getInventory().add(take)) { player.drop(take, false); }}); }
							return; }//!sign.isWaxed()
					} //Base_Hake
				}//sign
			}
		}
	}
	
	private boolean hasEditableText(Player player, SignBlockEntity sign, boolean front) {
		SignText signtext = sign.getText(front);
		return Arrays.stream(signtext.getMessages(player.isTextFilteringEnabled()))
			.allMatch(p_327267_ -> p_327267_.equals(CommonComponents.EMPTY) || p_327267_.getContents() instanceof PlainTextContents);
	}

	private boolean otherPlayerIsEditingSign(Player player, SignBlockEntity sign) {
		UUID uuid = sign.getPlayerWhoMayEdit();
		return uuid != null && !uuid.equals(player.getUUID());
	}
	
	public boolean tryApplyToSign(Level worldIn, SignBlockEntity tileEntity, boolean front, Player playerIn, Item item) {
		if (tileEntity.updateText(boardText -> boardText.setColor(this.brushColor(item)), front)) {
			return true; }
		
		else { return false; }
	}
	
	private DyeColor brushColor(Item hItem) {
		if (hItem instanceof Hake_White) { return DyeColor.WHITE; }
		if (hItem instanceof Hake_Orange) { return DyeColor.ORANGE; }
		if (hItem instanceof Hake_Magenta) { return DyeColor.MAGENTA; }
		if (hItem instanceof Hake_LightBlue) { return DyeColor.LIGHT_BLUE; }
		if (hItem instanceof Hake_Yellow) { return DyeColor.YELLOW; }
		if (hItem instanceof Hake_Lime) { return DyeColor.LIME; }
		if (hItem instanceof Hake_Pink) { return DyeColor.PINK; }
		if (hItem instanceof Hake_Gray) { return DyeColor.GRAY; }
		if (hItem instanceof Hake_LightGray) { return DyeColor.LIGHT_GRAY; }
		if (hItem instanceof Hake_Cyan) { return DyeColor.CYAN; }
		if (hItem instanceof Hake_Purple) { return DyeColor.PURPLE; }
		if (hItem instanceof Hake_Blue) { return DyeColor.BLUE; }
		if (hItem instanceof Hake_Brown) { return DyeColor.BROWN; }
		if (hItem instanceof Hake_Green) { return DyeColor.GREEN; }
		if (hItem instanceof Hake_Red) { return DyeColor.RED; }
		else { return DyeColor.BLACK; }
	}
}
