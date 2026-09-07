package com.ayutaki.chinjufumod.items.color;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.base.IG_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Abstract_Hake extends IG_Wadeco {

	public Abstract_Hake(Properties props) {
		super(props.durability(128));
	} //128

	/* Abstract */
	public abstract ActionResultType useOn(ItemUseContext context);
	
	@Override
	public boolean isRepairable(ItemStack stack) {
		return false;
	}
	
	public static void consumeAndBreak(int damage, ItemStack hStack, PlayerEntity playerIn, ItemUseContext context) {
		IWorld iworld = context.getLevel();
		BlockPos pos = context.getClickedPos();
		iworld.playSound(playerIn, pos, SoundEvents_CM.PAINT, SoundCategory.BLOCKS, 1.0F, 0.8F);
		
		boolean mode = playerIn.abilities.instabuild;
		hStack.hurtAndBreak(mode? 0 : damage, playerIn, user -> {
			ItemStack take = new ItemStack(Items_Wadeco.HAKE, 1);
			if (!playerIn.inventory.add(take)) { playerIn.drop(take, false); }
			user.broadcastBreakEvent(context.getHand()); } ); //BreakAnimation 無しで筆を返すことも可能
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_hake_color").withStyle(TextFormatting.GRAY));
	}
}
