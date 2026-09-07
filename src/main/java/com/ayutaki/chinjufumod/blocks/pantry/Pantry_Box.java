package com.ayutaki.chinjufumod.blocks.pantry;

import java.util.Random;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Pantry_Box extends BaseFacingSlab_Water {
	/* Collision */
	private static final VoxelShape BOX_BOTTOM = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 6.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 6.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 6.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape BOX_TOP = Shapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 14.0D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 14.0D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.5D, 7.5D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 0.5D),
			Block.box(0.0D, 0.0D, 15.5D, 16.0D, 8.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 0.5D, 8.0D, 16.0D),
			Block.box(15.5D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D));
	private static final VoxelShape BOX_DOUBLE = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
			Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 0.5D),
			Block.box(0.0D, 14.0D, 15.5D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 14.0D, 0.0D, 0.5D, 16.0D, 16.0D),
			Block.box(15.5D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D));

	public Pantry_Box(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		if (hItem instanceof BlockItem) { return InteractionResult.PASS; }
		
		if (hStack.isEmpty()) {
			int amount = (this == Pantry_Blocks.BOX_H_SQUID.get() || this == Pantry_Blocks.BOX_H_HAKUSAI.get())? 2 : 
				((this == Pantry_Blocks.BOX_H_CABBAGE.get() || this == Pantry_Blocks.BOX_H_GREENONION.get())? 4 : 8);
			
			SlabType slabType = state.getValue(TYPE);
			boolean stateW = (slabType == SlabType.DOUBLE);
			int gHC = stateW? 2 : 1;
			CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), amount * gHC);
			
			boolean BLOCK1 = (this != Pantry_Blocks.BOX_H_BEEF.get() && this != Pantry_Blocks.BOX_H_CHICKEN.get() && this != Pantry_Blocks.BOX_H_COD.get() && 
					this != Pantry_Blocks.BOX_H_FISH.get() && this != Pantry_Blocks.BOX_H_EGG.get() && this != Pantry_Blocks.BOX_H_MUTTON.get() && 
					this != Pantry_Blocks.BOX_H_PORK.get() && this != Pantry_Blocks.BOX_H_RABBIT.get() && this != Pantry_Blocks.BOX_H_SALMON.get() && 
					this != Pantry_Blocks.BOX_H_SWBERRY.get() && this != Pantry_Blocks.BOX_H_CHERRY.get() && this != Pantry_Blocks.BOX_H_SQUID.get());
			
			Block takeType = (BLOCK1? Pantry_Blocks.BOX_H_EMPTY.get() : Pantry_Blocks.BOX_H_EMPTY2.get());
			worldIn.setBlock(pos, takeType.defaultBlockState()
					.setValue(BaseFacingSlab_Water.H_FACING, state.getValue(H_FACING))
					.setValue(BaseFacingSlab_Water.TYPE, state.getValue(TYPE))
					.setValue(BaseFacingSlab_Water.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } 
		
		return InteractionResult.SUCCESS;
	}
	
	private Item takeItem() {
		if (this == Pantry_Blocks.BOX_H_APPLE.get()) { return Items.APPLE; }
		if (this == Pantry_Blocks.BOX_H_BEEF.get()) { return Items.BEEF; }
		if (this == Pantry_Blocks.BOX_H_BEETROOT.get()) { return Items.BEETROOT; }
		if (this == Pantry_Blocks.BOX_H_BREAD.get()) { return Items.BREAD; }
		if (this == Pantry_Blocks.BOX_H_CARROT.get()) { return Items.CARROT; }
		if (this == Pantry_Blocks.BOX_H_CHICKEN.get()) { return Items.CHICKEN; }
		if (this == Pantry_Blocks.BOX_H_CHORUS.get()) { return Items.CHORUS_FRUIT; }
		if (this == Pantry_Blocks.BOX_H_EGG.get()) { return Items.EGG; }
		if (this == Pantry_Blocks.BOX_H_COD.get()) { return Items.COD; }
		if (this == Pantry_Blocks.BOX_H_FISH.get()) { return Items.COD; }
		if (this == Pantry_Blocks.BOX_H_MUTTON.get()) { return Items.MUTTON; }
		if (this == Pantry_Blocks.BOX_H_PORK.get()) { return Items.PORKCHOP; }
		if (this == Pantry_Blocks.BOX_H_POTATO.get()) { return Items.POTATO; }
		if (this == Pantry_Blocks.BOX_H_RABBIT.get()) { return Items.RABBIT; }
		if (this == Pantry_Blocks.BOX_H_SALMON.get()) { return Items.SALMON; }
		if (this == Pantry_Blocks.BOX_H_SWBERRY.get()) { return Items.SWEET_BERRIES; }

		if (this == Pantry_Blocks.BOX_H_CABBAGE.get()) { return Items_Teatime.FOOD_CABBAGE.get(); }
		if (this == Pantry_Blocks.BOX_H_HAKUSAI.get()) { return Items_Teatime.FOOD_HAKUSAI.get(); }
		if (this == Pantry_Blocks.BOX_H_CHERRY.get()) { return Items_Teatime.FOOD_CHERRY.get(); }
		if (this == Pantry_Blocks.BOX_H_CITRUS.get()) { return Items_Teatime.FOOD_MIKAN.get(); }
		if (this == Pantry_Blocks.BOX_H_CORN.get()) { return Items_Teatime.FOOD_CORN.get(); }
		if (this == Pantry_Blocks.BOX_H_GREENONION.get()) { return Items_Teatime.FOOD_GREENONION.get(); }
		if (this == Pantry_Blocks.BOX_H_GRAPE.get()) { return Items_Teatime.FOOD_GRAPE.get(); }
		if (this == Pantry_Blocks.BOX_H_ONION.get()) { return Items_Teatime.FOOD_ONION.get(); }
		if (this == Pantry_Blocks.BOX_H_ORIENTCLAM.get()) { return Items_Teatime.HAMAGURI.get(); }
		if (this == Pantry_Blocks.BOX_H_SPINACH.get()) { return Items_Teatime.FOOD_SPINACH.get(); }
		if (this == Pantry_Blocks.BOX_H_SQUID.get()) { return Items_Teatime.IKA.get(); }
		if (this == Pantry_Blocks.BOX_H_TOMATO.get()) { return Items_Teatime.FOOD_TOMATO.get(); }
		else { return Items_Seasonal.TAKENOKO.get(); }
	}
	
	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);
			
			SlabType slabType = state.getValue(TYPE);
			int takeDOUBLE = (slabType == SlabType.DOUBLE)? 2 : 1;
			CMEvents.dropN_ROTTENFOOD(takeDOUBLE, worldIn, pos);
			
			boolean BLOCK1 = (this != Pantry_Blocks.BOX_H_BEEF.get() && this != Pantry_Blocks.BOX_H_CHICKEN.get() && this != Pantry_Blocks.BOX_H_COD.get() && 
					this != Pantry_Blocks.BOX_H_FISH.get() && this != Pantry_Blocks.BOX_H_EGG.get() && this != Pantry_Blocks.BOX_H_MUTTON.get() && 
					this != Pantry_Blocks.BOX_H_PORK.get() && this != Pantry_Blocks.BOX_H_RABBIT.get() && this != Pantry_Blocks.BOX_H_SALMON.get() && 
					this != Pantry_Blocks.BOX_H_SWBERRY.get() && this != Pantry_Blocks.BOX_H_CHERRY.get() && this != Pantry_Blocks.BOX_H_SQUID.get());

			Block takeType = (BLOCK1? Pantry_Blocks.BOX_H_EMPTY.get() : Pantry_Blocks.BOX_H_EMPTY2.get());
			worldIn.setBlock(pos, takeType.defaultBlockState()
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
			return BOX_DOUBLE;
		case TOP:
			return BOX_TOP;
		default:
			return BOX_BOTTOM;
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
