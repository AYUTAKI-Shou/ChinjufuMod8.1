package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class Kit_Tana2 extends BaseFacingWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D);

	public Kit_Tana2(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		
		boolean bottle = (hItem == Items_Teatime.NAMASAKEBOT || hItem == Items_Teatime.SAKEBOT || hItem == Items_Teatime.JUKUSAKEBOT ||
				hItem == Items_Teatime.CIDERBOT || hItem == Items_Teatime.JUKUCIDERBOT ||
				hItem == Items_Teatime.WINEBOT || hItem == Items_Teatime.JUKUWINEBOT ||
				hItem == Items_Teatime.MEADBOT || hItem == Items_Teatime.JUKUMEADBOT);
		
		if (bottle) {
			CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
			CMEvents.soundSAKEBottleFill(worldIn, pos);
			worldIn.setBlockState(pos, this.takeBlock(hItem).getDefaultState()
					.with(Base_WineTana.H_FACING, state.get(H_FACING))
					.with(Base_WineTana.STAGE_1_4, Integer.valueOf(1)), 3); }

		if (!bottle) {
			CMEvents.textNotHave(worldIn, pos, playerIn); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Block takeBlock(Item hItem) {
		if (hItem == Items_Teatime.NAMASAKEBOT) { return Hakkou_Blocks.KIT_SAKENAMA; }
		if (hItem == Items_Teatime.SAKEBOT) { return Hakkou_Blocks.KIT_SAKE; }
		if (hItem == Items_Teatime.JUKUSAKEBOT) { return Hakkou_Blocks.KIT_SAKEJUKU; }
		if (hItem == Items_Teatime.CIDERBOT) { return Hakkou_Blocks.KIT_CIDER; }
		if (hItem == Items_Teatime.JUKUCIDERBOT) { return Hakkou_Blocks.KIT_CIDERJUKU; }
		if (hItem == Items_Teatime.WINEBOT) { return Hakkou_Blocks.KIT_WINE; }
		if (hItem == Items_Teatime.JUKUWINEBOT) { return Hakkou_Blocks.KIT_WINEJUKU; }
		if (hItem == Items_Teatime.MEADBOT) { return Hakkou_Blocks.KIT_MEAD; }
		else { return Hakkou_Blocks.KIT_MEADJUKU; }
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case EAST: return AABB_EAST;
		case WEST: return AABB_WEST;
		}
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
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
		blockTip.add(new TranslationTextComponent("tips.block_kit2_tana").applyTextStyle(TextFormatting.GRAY));
	}
}
