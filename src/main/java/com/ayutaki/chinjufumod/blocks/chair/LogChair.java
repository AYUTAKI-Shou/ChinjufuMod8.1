package com.ayutaki.chinjufumod.blocks.chair;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseWaterLogged;
import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LogChair extends BaseWaterLogged {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 10.0D, 12.0D);

	public LogChair(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		CMEvents.soundSitChair(worldIn, pos);
		return SitableEntity.create(worldIn, pos, 0.4, playerIn);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_isu").withStyle(ChatFormatting.GRAY));
	}
}
