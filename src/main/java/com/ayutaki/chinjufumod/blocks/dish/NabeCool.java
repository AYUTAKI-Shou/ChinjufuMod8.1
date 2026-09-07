package com.ayutaki.chinjufumod.blocks.dish;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class NabeCool extends Base_Cool {

	protected static final int COOK_TIME = 800;
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 4.0D, 12.5D);
	
	public NabeCool(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);
		
		if (i == 3) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake_NItem(worldIn, pos, playerIn, Items.BONE_MEAL, 2);
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}

		if (i <= 2) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		boolean north = isICE(worldIn, pos, Direction.NORTH);
		boolean east = isICE(worldIn, pos, Direction.EAST);
		boolean south = isICE(worldIn, pos, Direction.SOUTH);
		boolean west = isICE(worldIn, pos, Direction.WEST);
		boolean up = isICE(worldIn, pos, Direction.UP);
		boolean down = isICE(worldIn, pos, Direction.DOWN);
		
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west).setValue(UP, up).setValue(DOWN, down)
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				tick.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5))); }
			
			else { } }

		if (waterIn(state)) { tick.scheduleTick(pos, this, 60); }

		boolean north = isICE(worldIn, pos, Direction.NORTH);
		boolean east = isICE(worldIn, pos, Direction.EAST);
		boolean south = isICE(worldIn, pos, Direction.SOUTH);
		boolean west = isICE(worldIn, pos, Direction.WEST);
		boolean up = isICE(worldIn, pos, Direction.UP);
		boolean down = isICE(worldIn, pos, Direction.DOWN);
		return state.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west).setValue(UP, up).setValue(DOWN, down);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterOUT(state)) {
			if (cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
			
			else { } }

		if (waterIn(state)) { worldIn.scheduleTick(pos, this, 60); }
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_4);
			if (i <= 2 && cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
 
			else { } }
		
		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
					.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (cookingIn(worldIn, pos)) {
			int i = state.getValue(STAGE_1_4);
			if (i != 1) {
				if (rand.nextDouble() < 0.25D) {
					/** which, position x y z, speed x y z **/
					worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
			}
			
			if (rand.nextDouble() < 0.1D) {
				worldIn.playLocalSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU.get(), SoundSource.BLOCKS, 0.5F, 0.7F, false); }
		}
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}
}
