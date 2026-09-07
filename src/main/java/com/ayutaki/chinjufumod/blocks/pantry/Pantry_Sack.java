package com.ayutaki.chinjufumod.blocks.pantry;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pantry_Sack extends BaseFacingSlab_Water {
	/* Collision */
	private static final VoxelShape SACK_BOTTOM = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	private static final VoxelShape SACK_TOP = Shapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));

	public Pantry_Sack(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		if (hItem instanceof BlockItem) { return InteractionResult.PASS; }
		
		if (hStack.isEmpty()) {
			SlabType slabType = state.getValue(TYPE);
			boolean stateW = (slabType == SlabType.DOUBLE);
			
			int gHC = stateW? 2 : 1;
			CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), 8 * gHC);
			
			worldIn.setBlock(pos, Pantry_Blocks.BOX_H_EMPTY3.get().defaultBlockState()
					.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
					.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
					.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } 
		
		return InteractionResult.SUCCESS;
	}
	
	private Item takeItem() {
		if (this == Pantry_Blocks.BOX_H_COCO.get()) { return Items.COCOA_BEANS; }
		if (this == Pantry_Blocks.BOX_H_FLOUR.get()) { return Items.WHEAT; }
		
		if (this == Pantry_Blocks.BOX_H_AZUKI.get()) { return Items_Teatime.SEEDS_AZUKI.get(); }
		if (this == Pantry_Blocks.BOX_H_RICE.get()) { return Items_Teatime.SEEDS_RICE.get(); }
		if (this == Pantry_Blocks.BOX_H_SOY.get()) { return Items_Teatime.SEEDS_SOY.get(); }
		if (this == Pantry_Blocks.BOX_H_TGREEN.get()) { return Items_Teatime.CHADUTSU.get(); }
		if (this == Pantry_Blocks.BOX_H_TRED.get()) { return Items_Teatime.CANTEA.get(); }
		if (this == Pantry_Blocks.BOX_H_KURI.get()) { return Items_Seasonal.KURI.get(); }
		
		if (this == Pantry_Blocks.BOX_H_BPEPPER.get()) { return Items_Teatime.PEPPER_DRY.get(); }
		if (this == Pantry_Blocks.BOX_H_CHILI.get()) { return Items_Teatime.CHILIPEPPER.get(); }
		if (this == Pantry_Blocks.BOX_H_CUMIN.get()) { return Items_Teatime.SEEDS_CUMIN.get(); }
		else { return Items_Teatime.SEEDS_TURMERIC.get(); }
	}
	
	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state)) { tick.scheduleTick(pos, this, 60); }

		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);

			SlabType slabType = state.getValue(TYPE);
			int takeDOUBLE = (slabType == SlabType.DOUBLE)? 2 : 1;
			CMEvents.dropN_ROTTENFOOD(takeDOUBLE, worldIn, pos);
			
			worldIn.setBlock(pos, Pantry_Blocks.BOX_H_EMPTY3.get().defaultBlockState()
					.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
					.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
					.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }

		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		SlabType slabType = state.getValue(TYPE);
		switch (slabType) {
		case DOUBLE:
			return Shapes.block();
		case TOP:
			return SACK_TOP;
		default:
			return SACK_BOTTOM;
		}
	}
	
	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }
	
}
