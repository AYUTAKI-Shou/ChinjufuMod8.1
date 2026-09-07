package com.ayutaki.chinjufumod.blocks.chair;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseWaterLogged;
import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CafeChair extends BaseWaterLogged {
	/* Collision */
	private static final VoxelShape AABB_BOX = Shapes.or(Block.box(4.0D, 9.0D, 4.0D, 12.0D, 10.0D, 12.0D),
			Block.box(3.0D, 7.0D, 3.0D, 13.0D, 9.0D, 13.0D),
			Block.box(7.0D, 1.0D, 7.0D, 9.0D, 7.0D, 9.0D),
			Block.box(7.0D, 0.0D, 3.0D, 9.0D, 1.0D, 13.0D),
			Block.box(3.0D, 0.0D, 7.0D, 7.0D, 1.0D, 9.0D),
			Block.box(9.0D, 0.0D, 7.0D, 13.0D, 1.0D, 9.0D));

	public CafeChair(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Base_Hake) { return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; }

		else {
			CMEvents.soundKinuzure(worldIn, pos);
			return SitableEntity.create(worldIn, pos, 0.4, playerIn);
		}
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
