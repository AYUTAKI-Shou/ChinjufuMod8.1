package com.ayutaki.chinjufumod.event;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Abstract_Hake;
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
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class RightClickEvent_CM {

	@SubscribeEvent
	public void editSign(RightClickBlock event) {
		Player player = event.getPlayer();
		if (player != null) {
			
			Level world = player.getLevel();
			BlockPos pos = new BlockPos(event.getPos());
			BlockState state = world.getBlockState(pos);
			
			/* SignBlock */
			if (state.getBlock() instanceof SignBlock) {
				BlockEntity tileEntity = world.getBlockEntity(pos);
				ItemStack hStack = player.getMainHandItem();
				boolean mode = player.getAbilities().instabuild;
	
				if (tileEntity instanceof SignBlockEntity sign) {
					if (hStack.getItem() instanceof Abstract_Hake) {
						player.playSound(SoundEvents_CM.PAINT.get(), 1.0F, 1.0F);
						sign.setColor(brushColor(hStack));
						
						if(!world.isClientSide()) {
							ObfuscationReflectionHelper.setPrivateValue(SignBlockEntity.class, sign, true, "f_59721_"); // <- sign.setEditable(true); accesstransformer.cfg
							player.openTextEdit(sign); 

							hStack.hurtAndBreak(mode? 0 : 1, player, user -> {
								ItemStack take = new ItemStack(Items_Wadeco.HAKE.get(), 1);
								if (!player.getInventory().add(take)) { player.drop(take, false); }
								user.broadcastBreakEvent(EquipmentSlot.MAINHAND); } ); }
						return; } //Base_Hake
				}//sign
			}
		}
	}

	private DyeColor brushColor(ItemStack stack) {
		Item item = stack.getItem();
		if (item instanceof Hake_White) { return DyeColor.WHITE; }
		if (item instanceof Hake_Orange) { return DyeColor.ORANGE; }
		if (item instanceof Hake_Magenta) { return DyeColor.MAGENTA; }
		if (item instanceof Hake_LightBlue) { return DyeColor.LIGHT_BLUE; }
		if (item instanceof Hake_Yellow) { return DyeColor.YELLOW; }
		if (item instanceof Hake_Lime) { return DyeColor.LIME; }
		if (item instanceof Hake_Pink) { return DyeColor.PINK; }
		if (item instanceof Hake_Gray) { return DyeColor.GRAY; }
		if (item instanceof Hake_LightGray) { return DyeColor.LIGHT_GRAY; }
		if (item instanceof Hake_Cyan) { return DyeColor.CYAN; }
		if (item instanceof Hake_Purple) { return DyeColor.PURPLE; }
		if (item instanceof Hake_Blue) { return DyeColor.BLUE; }
		if (item instanceof Hake_Brown) { return DyeColor.BROWN; }
		if (item instanceof Hake_Green) { return DyeColor.GREEN; }
		if (item instanceof Hake_Red) { return DyeColor.RED; }
		else { return DyeColor.BLACK; }
	}
}
