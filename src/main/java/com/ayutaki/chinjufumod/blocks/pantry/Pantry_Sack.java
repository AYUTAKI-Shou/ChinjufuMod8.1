package com.ayutaki.chinjufumod.blocks.pantry;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class Pantry_Sack extends BaseFacingSlab_Water {
	/* Collision */
	private static final VoxelShape SACK_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape SACK_TOP = VoxelShapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));

	public Pantry_Sack(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		if (hItem instanceof BlockItem) { return ActionResultType.PASS; }
		
		if (hStack.isEmpty()) {
			SlabType slabType = state.getValue(TYPE);
			boolean stateW = (slabType == SlabType.DOUBLE);
			
			int gHC = stateW? 2 : 1;
			CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), 8 * gHC);
			
			worldIn.setBlock(pos, Pantry_Blocks.BOX_H_EMPTY3.defaultBlockState()
					.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
					.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
					.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } 
		
		return ActionResultType.SUCCESS;
	}
	
	private Item takeItem() {
		if (this == Pantry_Blocks.BOX_H_COCO) { return Items.COCOA_BEANS; }
		if (this == Pantry_Blocks.BOX_H_FLOUR) { return Items.WHEAT; }

		if (this == Pantry_Blocks.BOX_H_AZUKI) { return Items_Teatime.SEEDS_AZUKI; }
		if (this == Pantry_Blocks.BOX_H_RICE) { return Items_Teatime.SEEDS_RICE; }
		if (this == Pantry_Blocks.BOX_H_SOY) { return Items_Teatime.SEEDS_SOY; }
		if (this == Pantry_Blocks.BOX_H_TGREEN) { return Items_Teatime.CHADUTSU; }
		if (this == Pantry_Blocks.BOX_H_TRED) { return Items_Teatime.CANTEA; }
		if (this == Pantry_Blocks.BOX_H_KURI) { return Items_Seasonal.KURI; }
		
		if (this == Pantry_Blocks.BOX_H_BPEPPER) { return Items_Teatime.PEPPER_DRY; }
		if (this == Pantry_Blocks.BOX_H_CHILI) { return Items_Teatime.CHILIPEPPER; }
		if (this == Pantry_Blocks.BOX_H_CUMIN) { return Items_Teatime.SEEDS_CUMIN; }
		else { return Items_Teatime.SEEDS_TURMERIC; }
	}
	
	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state)) { worldIn.getBlockTicks().scheduleTick(pos, this, 60); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.getBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);

			SlabType slabType = state.getValue(TYPE);
			int takeDOUBLE = (slabType == SlabType.DOUBLE)? 2 : 1;
			CMEvents.dropN_ROTTENFOOD(takeDOUBLE, worldIn, pos);
			
			worldIn.setBlock(pos, Pantry_Blocks.BOX_H_EMPTY3.defaultBlockState()
					.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
					.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
					.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }

		else { }
	}

	/* Collisions for each property. */
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		SlabType slabType = state.getValue(TYPE);
		switch (slabType) {
		case DOUBLE:
			return VoxelShapes.block();
		case TOP:
			return SACK_TOP;
		default:
			return SACK_BOTTOM;
		}
	}

	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 5; }

	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) { return 20; }
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
