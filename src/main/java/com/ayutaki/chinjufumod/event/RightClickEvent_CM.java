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

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class RightClickEvent_CM {

	@SubscribeEvent
	public void editSign(RightClickBlock event) {
		PlayerEntity player = event.getPlayer();
		if (player != null) {
			
			World world = event.getWorld();
			BlockPos pos = new BlockPos(event.getPos());
			BlockState state = world.getBlockState(pos);
		
			/* SignBlock */
			if (state.getBlock() instanceof AbstractSignBlock) {
				TileEntity tileEntity = world.getTileEntity(pos);
				ItemStack hStack = player.getHeldItemMainhand();
				boolean mode = player.abilities.isCreativeMode;

				if (tileEntity instanceof SignTileEntity) {
					if (hStack.getItem() instanceof Abstract_Hake) {
						player.playSound(SoundEvents_CM.PAINT, 1.0F, 1.0F);
						SignTileEntity sign = (SignTileEntity) tileEntity;
						sign.setTextColor(brushColor(hStack));
						
						if(!world.isRemote) {
							ObfuscationReflectionHelper.setPrivateValue(SignTileEntity.class, sign, true, "field_145916_j");
							player.openSignEditor(sign); 

							hStack.damageItem(mode? 0 : 1, player, user -> {
								ItemStack take = new ItemStack(Items_Wadeco.HAKE, 1);
								if (!player.inventory.addItemStackToInventory(take)) { player.dropItem(take, false); }
								user.sendBreakAnimation(EquipmentSlotType.MAINHAND); }); }
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
