package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public abstract class Abstract_CookPan extends Abstract_WaterLogged {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;

	public Abstract_CookPan(Block.Properties props) {
		super(props);
	}

	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(H_FACING, rotation.rotate(state.get(H_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(H_FACING)));
	}
	
	/* TickRandom */
	protected boolean cookingIn(IBlockReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.down());
		Block downBlock = downState.getBlock();
		return (downBlock instanceof FurnaceBlock && downState.get(FurnaceBlock.LIT) == true) ||
				(downBlock instanceof AbstractOvenBlock && downState.get(AbstractOvenBlock.LIT) == true) ||
				(downBlock instanceof Kit_Cooktop && downState.get(Kit_Cooktop.STAGE_1_3) == 2);
	}

	protected boolean cookingOUT(IBlockReader worldIn, BlockPos pos) {
		return !cookingIn(worldIn, pos);
	}
	
	protected boolean waterIn(BlockState state) {
		return state.get(WATERLOGGED);
	}
	
	protected boolean waterOUT(BlockState state) {
		return !state.get(WATERLOGGED);
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}

	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_frypan").applyTextStyle(TextFormatting.GRAY));
	}
}
