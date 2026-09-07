package com.ayutaki.chinjufumod.items.garden;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.garden.Samon;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.IG_Wadeco;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Kumade_DT extends IG_Wadeco {

	public Kumade_DT(Properties props) {
		super(props);
	}
	
	private void soundKumade(IWorld iworld, PlayerEntity playerIn, BlockPos pos) {
		iworld.playSound(playerIn, pos, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); }
	
	private void consumeAndBreak(ItemStack hStack, PlayerEntity playerIn, ItemUseContext context) {
		IWorld iworld = context.getWorld();
		BlockPos pos = context.getPos();
		
		this.soundKumade(iworld, playerIn, pos);
		CMEvents.toolDamege(1, playerIn, hStack);
		playerIn.getCooldownTracker().setCooldown(this, 10);
	}
	
	/* FlintAndSteel */
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();
		IWorld iworld = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState state = iworld.getBlockState(pos);
		Block block = state.getBlock();
		ItemStack hStack = context.getItem();

		if (playerIn.getCooldownTracker().hasCooldown(this)) { return ActionResultType.PASS; }
		
		else { //!Cooldown			
			if (block == Blocks.SAND) {
				iworld.setBlockState(pos, Garden_Blocks.SAMON.getDefaultState().with(Samon.STAGE_0_7, Integer.valueOf(0)), 3);
				this.consumeAndBreak(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.GRAVEL) {
				iworld.setBlockState(pos, Garden_Blocks.SAMON_B.getDefaultState().with(Samon.STAGE_0_7, Integer.valueOf(0)), 3);
				this.consumeAndBreak(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }
			
			/** Samon **/
			if (block instanceof Samon) {
				this.soundKumade(iworld, playerIn, pos);
				iworld.setBlockState(pos, state.cycle(Samon.STAGE_0_7), 3);
				
				playerIn.getCooldownTracker().setCooldown(this, 10);
				return ActionResultType.SUCCESS; }
		}
		return ActionResultType.FAIL;
	}

	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.STICK;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_kumade").applyTextStyle(TextFormatting.GRAY));
	}
}
