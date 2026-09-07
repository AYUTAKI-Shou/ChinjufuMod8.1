package com.ayutaki.chinjufumod.blocks.cmblock;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseWaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class EmptyBox extends BaseWaterLogged {
	/* Collision */
	private static final VoxelShape AABB_BOX = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D),
			Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D),
			Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));

	public EmptyBox(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int gHC = hStack.getCount();

		boolean ammo = (hItem == Items_Weapon.AMMUNITION_L && gHC >= 8);
		boolean bauxi = (hItem == Items_Chinjufu.BAUXITE && gHC >= 8);

		if (ammo || bauxi) {
			CMEvents.consumeN_Hand(8, playerIn, hand);
			CMEvents.soundWoodPlace(worldIn, pos);
			
			Direction facing = playerIn.getDirection().getOpposite();
			Block takeType = (ammo? Chinjufu_Blocks.AMMO_BOX : Chinjufu_Blocks.BAUXITE_BOX);
			worldIn.setBlock(pos, takeType.defaultBlockState()
					.setValue(AmmoBauxiteBox.H_FACING, facing)
					.setValue(AmmoBauxiteBox.WATERLOGGED, state.getValue(WATERLOGGED)), 3);

			return ActionResultType.SUCCESS; }
		
		return ActionResultType.PASS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}

	/* Flammable Block */
	/** IForgeBlock.class Called when fire is updating, checks if a block face can catch fire. **/
	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return true; }

	/** Called when fire is updating on a neighbor block. **/
	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 5; }

	/**Chance that fire will spread and consume this block. 300 being a 100% chance, 0, being a 0% chance **/
	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 20; }

}
