package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.kitchen.UsuTsuki;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
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

public class KineYoko_TT extends IG_Teatime {

	public KineYoko_TT(Properties props) {
		super(props);
	}

	/* FlintAndSteel */
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();
		IWorld iworld = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = iworld.getBlockState(pos);
		Block block = state.getBlock();
		Direction hitFace = context.getClickedFace();
		ItemStack hStack = context.getItemInHand();
		
		if (playerIn.getCooldowns().isOnCooldown(this)) { return ActionResultType.PASS; }
		
		else { //!Cooldown
			
			if (block instanceof UsuTsuki) {
				int i = state.getValue(UsuTsuki.STAGE_0_15);
				
				if (hitFace == Direction.UP) {
					if (i >= 4 && i <= 14) {
						if (i >= 4 && i <= 6) { //volume, pitch
							iworld.playSound(playerIn, pos, SoundEvents.SNOW_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F); }
			
						if (i >= 7 && i <= 14) {
							iworld.playSound(playerIn, pos, SoundEvents.PLAYER_ATTACK_STRONG, SoundCategory.BLOCKS, 0.8F, 1.1F);
							iworld.playSound(playerIn, pos, SoundEvents.SLIME_JUMP, SoundCategory.BLOCKS, 0.1F, 0.8F); }

						iworld.setBlock(pos, Kitchen_Blocks.USU_TSUKI.defaultBlockState().setValue(UsuTsuki.STAGE_0_15, Integer.valueOf(i + 1)), 3);
						CMEvents.toolDamege(1, playerIn, hStack);
						playerIn.getCooldowns().addCooldown(this, 10); }
					
					else { }
				}
				
				else { //!UP
					iworld.playSound(playerIn, pos, SoundEvents_CM.TOUCH_BLOCK, SoundCategory.BLOCKS, 1.0F, 0.8F); }

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.FAIL;
	}

	/* Items needed for repair. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.OAK_LOG; }

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.block_usutsuki").withStyle(TextFormatting.GRAY));
	}
}
