package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.phys.shapes.VoxelShape;

public class Kit_Tana2 extends BaseFacingWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape AABB_WEST = Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);

	public Kit_Tana2(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		boolean bottle = (hItem == Items_Teatime.NAMASAKEBOT.get() || hItem == Items_Teatime.SAKEBOT.get() || hItem == Items_Teatime.JUKUSAKEBOT.get() ||
				hItem == Items_Teatime.CIDERBOT.get() || hItem == Items_Teatime.JUKUCIDERBOT.get() ||
				hItem == Items_Teatime.WINEBOT.get() || hItem == Items_Teatime.JUKUWINEBOT.get() ||
				hItem == Items_Teatime.MEADBOT.get() || hItem == Items_Teatime.JUKUMEADBOT.get());
		
		if (bottle) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			CMEvents.soundSAKEBottleFill(worldIn, pos);
			worldIn.setBlock(pos, this.takeBlock(hItem).defaultBlockState()
					.setValue(Base_WineTana.H_FACING, state.getValue(H_FACING))
					.setValue(Base_WineTana.STAGE_1_4, Integer.valueOf(1)), 3); }

		if (!bottle) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	private Block takeBlock(Item hItem) {
		if (hItem == Items_Teatime.NAMASAKEBOT.get()) { return Hakkou_Blocks.KIT_SAKENAMA.get(); }
		if (hItem == Items_Teatime.SAKEBOT.get()) { return Hakkou_Blocks.KIT_SAKE.get(); }
		if (hItem == Items_Teatime.JUKUSAKEBOT.get()) { return Hakkou_Blocks.KIT_SAKEJUKU.get(); }
		if (hItem == Items_Teatime.CIDERBOT.get()) { return Hakkou_Blocks.KIT_CIDER.get(); }
		if (hItem == Items_Teatime.JUKUCIDERBOT.get()) { return Hakkou_Blocks.KIT_CIDERJUKU.get(); }
		if (hItem == Items_Teatime.WINEBOT.get()) { return Hakkou_Blocks.KIT_WINE.get(); }
		if (hItem == Items_Teatime.JUKUWINEBOT.get()) { return Hakkou_Blocks.KIT_WINEJUKU.get(); }
		if (hItem == Items_Teatime.MEADBOT.get()) { return Hakkou_Blocks.KIT_MEAD.get(); }
		else { return Hakkou_Blocks.KIT_MEADJUKU.get(); }
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case EAST: return AABB_EAST;
		case WEST: return AABB_WEST;
		} // switch
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_kit2_tana").withStyle(ChatFormatting.GRAY));
	}
}
