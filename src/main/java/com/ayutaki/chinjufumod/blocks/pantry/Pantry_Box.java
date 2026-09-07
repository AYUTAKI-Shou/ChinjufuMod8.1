package com.ayutaki.chinjufumod.blocks.pantry;

import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
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
	private static final VoxelShape BOX_BOTTOM = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.makeCuboidShape(0.0D, 6.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 6.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.makeCuboidShape(15.5D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape BOX_TOP = VoxelShapes.or(Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.makeCuboidShape(0.0D, 14.0D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.makeCuboidShape(15.5D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.makeCuboidShape(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.makeCuboidShape(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape BOX_DOUBLE = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.makeCuboidShape(0.0D, 14.0D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.makeCuboidShape(15.5D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D));

	public Pantry_Box(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		
		if (hItem instanceof BlockItem) { return ActionResultType.PASS; }
		
		if (hStack.isEmpty()) {
			int amount = (this == Pantry_Blocks.BOX_H_SQUID || this == Pantry_Blocks.BOX_H_HAKUSAI)? 2 : 
				((this == Pantry_Blocks.BOX_H_CABBAGE || this == Pantry_Blocks.BOX_H_GREENONION)? 4 : 8);
			
			SlabType slabType = state.get(TYPE);
			boolean stateW = (slabType == SlabType.DOUBLE);
			int gHC = stateW? 2 : 1;
			CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), amount * gHC);
			
			boolean BLOCK1 = (this != Pantry_Blocks.BOX_H_BEEF && this != Pantry_Blocks.BOX_H_CHICKEN && this != Pantry_Blocks.BOX_H_COD && 
					this != Pantry_Blocks.BOX_H_FISH && this != Pantry_Blocks.BOX_H_EGG && this != Pantry_Blocks.BOX_H_MUTTON && 
					this != Pantry_Blocks.BOX_H_PORK && this != Pantry_Blocks.BOX_H_RABBIT && this != Pantry_Blocks.BOX_H_SALMON && 
					this != Pantry_Blocks.BOX_H_SWBERRY && this != Pantry_Blocks.BOX_H_CHERRY && this != Pantry_Blocks.BOX_H_SQUID);
			
			Block takeType = (BLOCK1? Pantry_Blocks.BOX_H_EMPTY : Pantry_Blocks.BOX_H_EMPTY2);
			worldIn.setBlockState(pos, takeType.getDefaultState()
					.with(BaseFacingSlab_Water.H_FACING, state.get(H_FACING))
					.with(BaseFacingSlab_Water.TYPE, state.get(TYPE))
					.with(BaseFacingSlab_Water.WATERLOGGED, state.get(WATERLOGGED)), 3); } 
		
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
		return state.get(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if (state.get(WATERLOGGED)) { worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			SlabType slabType = state.get(TYPE);
			int takeDOUBLE = (slabType == SlabType.DOUBLE)? 2 : 1;
			CMEvents.dropN_ROTTENFOOD(takeDOUBLE, worldIn, pos);
			
			boolean BLOCK1 = (this != Pantry_Blocks.BOX_H_BEEF && this != Pantry_Blocks.BOX_H_CHICKEN && this != Pantry_Blocks.BOX_H_COD && 
					this != Pantry_Blocks.BOX_H_FISH && this != Pantry_Blocks.BOX_H_EGG && this != Pantry_Blocks.BOX_H_MUTTON && 
					this != Pantry_Blocks.BOX_H_PORK && this != Pantry_Blocks.BOX_H_RABBIT && this != Pantry_Blocks.BOX_H_SALMON && 
					this != Pantry_Blocks.BOX_H_SWBERRY && this != Pantry_Blocks.BOX_H_CHERRY && this != Pantry_Blocks.BOX_H_SQUID);
			
			Block takeType = (BLOCK1? Pantry_Blocks.BOX_H_EMPTY : Pantry_Blocks.BOX_H_EMPTY2);
			worldIn.setBlockState(pos, takeType.getDefaultState()
					.with(Pantry_Empty.H_FACING, state.get(H_FACING))
					.with(Pantry_Empty.TYPE, state.get(TYPE))
					.with(Pantry_Empty.WATERLOGGED, state.get(WATERLOGGED)), 3); }

		else { }
	}

	/* Collisions for each property. */
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		SlabType slabType = state.get(TYPE);
		switch(slabType) {
		case DOUBLE:
			return BOX_DOUBLE;
		case TOP:
			return BOX_TOP;
		default:
			return BOX_BOTTOM;
		}
	}

	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return true;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
