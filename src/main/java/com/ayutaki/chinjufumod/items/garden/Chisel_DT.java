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

	private void consumeAndBreak(ItemStack hStack, PlayerEntity playerIn) {
		CMEvents.toolDamege(1, playerIn, hStack);
		playerIn.getCooldowns().addCooldown(this, 10);
	}
	
	private void chisel_seStone(ItemStack hStack, PlayerEntity playerIn, ItemUseContext context) {
		IWorld iworld = context.getLevel();
		BlockPos pos = context.getClickedPos();
		iworld.playSound(playerIn, pos, SoundEvents.STONE_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F);
		this.consumeAndBreak(hStack, playerIn);
	}
	
	private void chisel_seWood(ItemStack hStack, PlayerEntity playerIn, ItemUseContext context) {
		IWorld iworld = context.getLevel();
		BlockPos pos = context.getClickedPos();
		iworld.playSound(playerIn, pos, SoundEvents.WOOD_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F);
		this.consumeAndBreak(hStack, playerIn);
	}
	
	/* FlintAndSteel */
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();
		IWorld iworld = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = iworld.getBlockState(pos);
		Block block = state.getBlock();
		ItemStack hStack = context.getItemInHand();
		
		if (playerIn.getCooldowns().isOnCooldown(this)) { return ActionResultType.PASS; }
		
		else { //!Cooldown
			/** Stone **/
			if (block == Blocks.STONE) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI.defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.GRANITE) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_gra.defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.DIORITE) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_dio.defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.ANDESITE) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_and.defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			
			/** Slab **/
			if (block == Blocks.STONE_SLAB) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_slab.defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.GRANITE_SLAB) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_slab_gra.defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.DIORITE_SLAB) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_slab_dio.defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			if (block == Blocks.ANDESITE_SLAB) {
				iworld.setBlock(pos, Garden_Blocks.NIWAISHI_slab_and.defaultBlockState().setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, context);
				return ActionResultType.SUCCESS; }

			
			/** Base_Niwaishi **/
			if (block instanceof Base_Niwaishi) {
				int i = state.getValue(Base_Niwaishi.STAGE_0_15);
				boolean mode = playerIn.abilities.instabuild;
				
				if (i <= 13) {
					iworld.setBlock(pos, state.setValue(Base_Niwaishi.STAGE_0_15, Integer.valueOf(i + 2)), 3);
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
				Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);
				
				if (axis == Direction.Axis.Y) {
					iworld.setBlock(pos, Kitchen_Blocks.USU_TSUKI.defaultBlockState().setValue(UsuTsuki.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seWood(hStack, playerIn, context);
					return ActionResultType.SUCCESS; }
			}
			
			/** USU **/
			if (block instanceof UsuTsuki) {
				int i = state.getValue(UsuTsuki.STAGE_0_15);
		
				if (i <= 2) {
					iworld.setBlock(pos, state.setValue(UsuTsuki.STAGE_0_15, Integer.valueOf(i + 1)), 3);
					this.chisel_seWood(hStack, playerIn, context);
					return ActionResultType.SUCCESS; }
			}
		}
		return ActionResultType.FAIL;
	}

	/* Items needed for repair. */
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack material) {
		return material.getItem() == Items.IRON_INGOT;
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_chisel").withStyle(TextFormatting.GRAY));
	}
}
