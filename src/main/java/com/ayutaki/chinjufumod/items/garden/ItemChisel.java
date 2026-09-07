package com.ayutaki.chinjufumod.items.garden;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.garden.Base_Niwaishi;
import com.ayutaki.chinjufumod.blocks.kitchen.UsuTsuki;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ItemChisel extends Item {

	public ItemChisel(Item.Properties props) {
		super(props.durability(256).repairable(Items.IRON_INGOT));
	}
	
	private void consumeAndBreak(ItemStack hStack, Player playerIn) {
		CMEvents.toolDamege(1, playerIn, hStack);
		playerIn.getCooldowns().addCooldown(hStack, 10);
	}
	
	private void chisel_seStone(ItemStack hStack, Player playerIn, UseOnContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		worldIn.playSound(playerIn, pos, SoundEvents.STONE_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F);
		this.consumeAndBreak(hStack, playerIn);
	}
	
	private void chisel_seWood(ItemStack hStack, Player playerIn, UseOnContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		worldIn.playSound(playerIn, pos, SoundEvents.WOOD_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F);
		this.consumeAndBreak(hStack, playerIn);
	}
	
	public InteractionResult useOn(UseOnContext context) {
		Player playerIn = context.getPlayer();
		Level iworld = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = iworld.getBlockState(pos);
		Block block = state.getBlock();
		ItemStack hStack = context.getItemInHand();
		
		if (!playerIn.getCooldowns().isOnCooldown(hStack)) {
			/** Stone **/
			if (block == Blocks.STONE) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI.get().defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return InteractionResult.SUCCESS; }

			if (block == Blocks.GRANITE) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_gra.get().defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return InteractionResult.SUCCESS; }

			if (block == Blocks.DIORITE) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_dio.get().defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return InteractionResult.SUCCESS; }

			if (block == Blocks.ANDESITE) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_and.get().defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return InteractionResult.SUCCESS; }

			
			/** Slab **/
			if (block == Blocks.STONE_SLAB) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_slab.get().defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return InteractionResult.SUCCESS; }

			if (block == Blocks.GRANITE_SLAB) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_slab_gra.get().defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return InteractionResult.SUCCESS; }

			if (block == Blocks.DIORITE_SLAB) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_slab_dio.get().defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return InteractionResult.SUCCESS; }

			if (block == Blocks.ANDESITE_SLAB) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_slab_and.get().defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return InteractionResult.SUCCESS; }
			
			
			/** Base_Niwaishi **/
			if (block instanceof Base_Niwaishi) {
				int i = state.getValue(Base_Niwaishi.STAGE_0_15);
				boolean mode = playerIn.getAbilities().instabuild;
				
				if (i <= 13) {
					iworld.setBlock(pos, state.setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(i + 2)), 3);
					this.chisel_seStone(hStack, playerIn, context);
					return InteractionResult.SUCCESS; }
				
				if (i == 14 || i == 15) {
					if (!mode) { iworld.destroyBlock(pos, true); }
					if (mode) { iworld.destroyBlock(pos, false); }
				
					this.chisel_seStone(hStack, playerIn, context);
					return InteractionResult.SUCCESS; }
			}
			
			
			/** oak **/
			if (block == Blocks.OAK_LOG) {
				Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);
				
				if (axis == Direction.Axis.Y) {
					iworld.setBlock(pos, Kitchen_Blocks.USU_TSUKI.get().defaultBlockState().setValue(UsuTsuki.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seWood(hStack, playerIn, context);
					return InteractionResult.SUCCESS; }
			}
			
			/** USU **/
			if (block instanceof UsuTsuki) {
				int i = state.getValue(UsuTsuki.STAGE_0_15);
		
				if (i <= 2) {
					iworld.setBlock(pos, state.setValue(UsuTsuki.STAGE_0_15, Integer.valueOf(i + 1)), 3);
					this.chisel_seWood(hStack, playerIn, context);
					return InteractionResult.SUCCESS; }
			}
		}

		return InteractionResult.FAIL;
	}

	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_chisel").withStyle(ChatFormatting.GRAY));
	}
}
