package com.ayutaki.chinjufumod.items.color;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.addtab.IR_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Base_Hake extends IR_Wadeco {

	public Base_Hake(String name) {
		super(name);
		setUnlocalizedName(name);

		maxStackSize = 1;
		setMaxDamage(128);
	}

	public static void consumeAndBreak(int damage, EntityPlayer playerIn, World worldIn, BlockPos pos, ItemStack hStack) {
		worldIn.playSound(playerIn, pos, SoundEvents_CM.PAINT, SoundCategory.BLOCKS, 1.0F, 0.8F);
		
		boolean mode = playerIn.capabilities.isCreativeMode;
		int life = hStack.getMaxDamage() - hStack.getItemDamage();
		
		if (life <= 1) {
			if (!mode) {
				ItemStack take = new ItemStack(Items_Wadeco.HAKE, 1, 0);
				if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
				hStack.shrink(1); }
			else { } }
		
		else { CMEvents.toolDamege(damage, playerIn, hStack); }
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_hake_color.name"));
	}
}
