package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.furnace.Irori;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public abstract class Abstract_CookNabe extends Abstract_WaterLogged {
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public Abstract_CookNabe(BlockBehaviour.Properties props) {
		super(props);
	}

	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}
	
	/* Conditions for TickRandom. */
	protected boolean cookingIn(LevelReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		Block downBlock = downState.getBlock();
		return (downBlock instanceof FurnaceBlock && downState.getValue(FurnaceBlock.LIT) == true) ||
				(downBlock instanceof AbstractOvenBlock && downState.getValue(AbstractOvenBlock.LIT) == true) ||
				(downBlock instanceof Irori && downState.getValue(Irori.LIT) == true) ||
				(downBlock instanceof Kit_Cooktop && downState.getValue(Kit_Cooktop.STAGE_1_3) == 2);
	}

	protected boolean cookingOUT(LevelReader worldIn, BlockPos pos) {
		return !cookingIn(worldIn, pos);
	}
	
	protected boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	protected boolean waterOUT(BlockState state) {
		return !state.getValue(WATERLOGGED);
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_nabe").withStyle(ChatFormatting.GRAY));
	}
}
