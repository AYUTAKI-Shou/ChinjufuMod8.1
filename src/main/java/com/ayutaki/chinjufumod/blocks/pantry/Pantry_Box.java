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

public class Pantry_Box extends BaseFacingSlab_Water {
	/* Collision */
	private static final VoxelShape BOX_BOTTOM = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 6.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape BOX_TOP = VoxelShapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 14.0D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 14.0D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape BOX_DOUBLE = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 14.0D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 14.0D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D));

	public Pantry_Box(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		if (hItem instanceof BlockItem) { return ActionResultType.PASS; }
		
		if (hStack.isEmpty()) {
			int amount = (this == Pantry_Blocks.BOX_H_SQUID || this == Pantry_Blocks.BOX_H_HAKUSAI)? 2 : 
				((this == Pantry_Blocks.BOX_H_CABBAGE || this == Pantry_Blocks.BOX_H_GREENONION)? 4 : 8);
			
			SlabType slabType = state.getValue(TYPE);
			boolean stateW = (slabType == SlabType.DOUBLE);
			int gHC = stateW? 2 : 1;
			CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), amount * gHC);
			
			boolean BLOCK1 = (this != Pantry_Blocks.BOX_H_BEEF && this != Pantry_Blocks.BOX_H_CHICKEN && this != Pantry_Blocks.BOX_H_COD && 
					this != Pantry_Blocks.BOX_H_FISH && this != Pantry_Blocks.BOX_H_EGG && this != Pantry_Blocks.BOX_H_MUTTON && 
					this != Pantry_Blocks.BOX_H_PORK && this != Pantry_Blocks.BOX_H_RABBIT && this != Pantry_Blocks.BOX_H_SALMON && 
					this != Pantry_Blocks.BOX_H_SWBERRY && this != Pantry_Blocks.BOX_H_CHERRY && this != Pantry_Blocks.BOX_H_SQUID);
			
			Block takeType = (BLOCK1? Pantry_Blocks.BOX_H_EMPTY : Pantry_Blocks.BOX_H_EMPTY2);
			worldIn.setBlock(pos, takeType.defaultBlockState()
					.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
					.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
					.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } 
		
		return ActionResultType.SUCCESS;
	}
	
	private Item takeItem() {
		if (this == Pantry_Blocks.BOX_H_APPLE) { return Items.APPLE; }
		if (this == Pantry_Blocks.BOX_H_BEEF) { return Items.BEEF; }
		if (this == Pantry_Blocks.BOX_H_BEETROOT) { return Items.BEETROOT; }
		if (this == Pantry_Blocks.BOX_H_BREAD) { return Items.BREAD; }
		if (this == Pantry_Blocks.BOX_H_CARROT) { return Items.CARROT; }
		if (this == Pantry_Blocks.BOX_H_CHICKEN) { return Items.CHICKEN; }
		if (this == Pantry_Blocks.BOX_H_CHORUS) { return Items.CHORUS_FRUIT; }
		if (this == Pantry_Blocks.BOX_H_EGG) { return Items.EGG; }
		if (this == Pantry_Blocks.BOX_H_COD) { return Items.COD; }
		if (this == Pantry_Blocks.BOX_H_FISH) { return Items.COD; }
		if (this == Pantry_Blocks.BOX_H_MUTTON) { return Items.MUTTON; }
		if (this == Pantry_Blocks.BOX_H_PORK) { return Items.PORKCHOP; }
		if (this == Pantry_Blocks.BOX_H_POTATO) { return Items.POTATO; }
		if (this == Pantry_Blocks.BOX_H_RABBIT) { return Items.RABBIT; }
		if (this == Pantry_Blocks.BOX_H_SALMON) { return Items.SALMON; }
		if (this == Pantry_Blocks.BOX_H_SWBERRY) { return Items.SWEET_BERRIES; }
				
		if (this == Pantry_Blocks.BOX_H_CABBAGE) { return Items_Teatime.FOOD_CABBAGE; }
		if (this == Pantry_Blocks.BOX_H_HAKUSAI) { return Items_Teatime.FOOD_HAKUSAI; }
		if (this == Pantry_Blocks.BOX_H_CHERRY) { return Items_Teatime.FOOD_CHERRY; }
		if (this == Pantry_Blocks.BOX_H_CITRUS) { return Items_Teatime.FOOD_MIKAN; }
		if (this == Pantry_Blocks.BOX_H_CORN) { return Items_Teatime.FOOD_CORN; }
		if (this == Pantry_Blocks.BOX_H_GREENONION) { return Items_Teatime.FOOD_GREENONION; }
		if (this == Pantry_Blocks.BOX_H_GRAPE) { return Items_Teatime.FOOD_GRAPE; }
		if (this == Pantry_Blocks.BOX_H_ONION) { return Items_Teatime.FOOD_ONION; }
		if (this == Pantry_Blocks.BOX_H_ORIENTCLAM) { return Items_Teatime.HAMAGURI; }
		if (this == Pantry_Blocks.BOX_H_SPINACH) { return Items_Teatime.FOOD_SPINACH; }
		if (this == Pantry_Blocks.BOX_H_SQUID) { return Items_Teatime.IKA; }
		if (this == Pantry_Blocks.BOX_H_TOMATO) { return Items_Teatime.FOOD_TOMATO; }
		else { return Items_Seasonal.TAKENOKO; }
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
			
			boolean BLOCK1 = (this != Pantry_Blocks.BOX_H_BEEF && this != Pantry_Blocks.BOX_H_CHICKEN && this != Pantry_Blocks.BOX_H_COD && 
					this != Pantry_Blocks.BOX_H_FISH && this != Pantry_Blocks.BOX_H_EGG && this != Pantry_Blocks.BOX_H_MUTTON && 
					this != Pantry_Blocks.BOX_H_PORK && this != Pantry_Blocks.BOX_H_RABBIT && this != Pantry_Blocks.BOX_H_SALMON && 
					this != Pantry_Blocks.BOX_H_SWBERRY && this != Pantry_Blocks.BOX_H_CHERRY && this != Pantry_Blocks.BOX_H_SQUID);

			Block takeType = (BLOCK1? Pantry_Blocks.BOX_H_EMPTY : Pantry_Blocks.BOX_H_EMPTY2);
			worldIn.setBlock(pos, takeType.defaultBlockState()
					.setValue(Pantry_Empty.H_FACING, state.getValue(H_FACING))
					.setValue(Pantry_Empty.TYPE, state.getValue(TYPE))
					.setValue(Pantry_Empty.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }

		else { }
	}

	/* Collisions for each property. */
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		SlabType slabType = state.getValue(TYPE);
		switch (slabType) {
		case DOUBLE:
			return BOX_DOUBLE;
		case TOP:
			return BOX_TOP;
		default:
			return BOX_BOTTOM;
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
