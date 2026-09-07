package com.ayutaki.chinjufumod.items.garden;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.garden.Base_Niwaishi;
import com.ayutaki.chinjufumod.blocks.kitchen.UsuTsuki;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.IG_Wadeco;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
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

public class Chisel_DT extends IG_Wadeco {

	public Chisel_DT(Properties props) {
		super(props);
	}

	private void consumeAndBreak(ItemStack hStack, PlayerEntity playerIn, ItemUseContext context) {
		CMEvents.toolDamege(1, playerIn, hStack);
		playerIn.getCooldownTracker().setCooldown(this, 10);
	}
	
	private void chisel_seStone(ItemStack hStack, PlayerEntity playerIn, ItemUseContext context) {
		IWorld iworld = context.getWorld();
		BlockPos pos = context.getPos();
		iworld.playSound(playerIn, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F);
		this.consumeAndBreak(hStack, playerIn, context);
	}
	
	private void chisel_seWood(ItemStack hStack, PlayerEntity playerIn, ItemUseContext context) {
		IWorld iworld = context.getWorld();
		BlockPos pos = context.getPos();
		iworld.playSound(playerIn, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F);
		this.consumeAndBreak(hStack, playerIn, context);
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
			/** Stone **/
			if (block == Blocks.STONE) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI.getDefaultState().with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.GRANITE) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_gra.getDefaultState().with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.DIORITE) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_dio.getDefaultState().with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.ANDESITE) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_and.getDefaultState().with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			/** Slab **/
			if (block == Blocks.STONE_SLAB) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_slab.getDefaultState().with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.GRANITE_SLAB) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_slab_gra.getDefaultState().with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.DIORITE_SLAB) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_slab_dio.getDefaultState().with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.ANDESITE_SLAB) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_slab_and.getDefaultState().with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }
			

			/** Base_Niwaishi **/
			if (block instanceof Base_Niwaishi) {
				int i = state.get(Base_Niwaishi.STAGE_0_15);
				boolean mode = playerIn.abilities.isCreativeMode;
				
				if (i <= 13) {
					iworld.setBlockState(pos, state.with(Base_Niwaishi.STAGE_0_15, Integer.valueOf(i + 2)), 3);
					this.chisel_seStone(hStack, playerIn, context);
					return ActionResultType.SUCCESS; }

				if (i == 14 || i == 15) {
					if (mode) { iworld.destroyBlock(pos, false); }
					else { iworld.destroyBlock(pos, true); } /*!mode*/
					
					this.chisel_seStone(hStack, playerIn, context);
					return ActionResultType.SUCCESS; }
			}
			
			
			/** oak **/
			if (block == Blocks.OAK_LOG) {
				Direction.Axis axis = state.get(RotatedPillarBlock.AXIS);
				
				if (axis == Direction.Axis.Y) {
					iworld.setBlockState(pos, Kitchen_Blocks.USU_TSUKI.getDefaultState().with(UsuTsuki.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seWood(hStack, playerIn, context);
					return ActionResultType.SUCCESS; }
			}

			/** USU **/
			if (block instanceof UsuTsuki) {
				int i = state.get(UsuTsuki.STAGE_0_15);
		
				if (i <= 2) {
					iworld.setBlockState(pos, state.with(UsuTsuki.STAGE_0_15, Integer.valueOf(i + 1)), 3);
					this.chisel_seWood(hStack, playerIn, context);
					return ActionResultType.SUCCESS; }
			}
		}
		return ActionResultType.FAIL;
	}

	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.IRON_INGOT;
	}

	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_chisel").applyTextStyle(TextFormatting.GRAY));
	}
}
