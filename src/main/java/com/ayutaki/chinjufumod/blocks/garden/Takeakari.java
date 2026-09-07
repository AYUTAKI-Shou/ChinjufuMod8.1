package com.ayutaki.chinjufumod.blocks.garden;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Takeakari extends Abstract_WaterLogged {
	public static final MapCodec<Takeakari> CODEC = simpleCodec(Takeakari::new);
	@Override
	public MapCodec<? extends Takeakari> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(6.5D, 0.0D, 1.5D, 9.5D, 8.0D, 4.5D);
	private static final VoxelShape AABB_WEST = Block.box(11.5D, 0.0D, 6.5D, 14.5D, 8.0D, 9.5D);
	private static final VoxelShape AABB_NORTH = Block.box(6.5D, 0.0D, 11.5D, 9.5D, 8.0D, 14.5D);
	private static final VoxelShape AABB_EAST = Block.box(1.5D, 0.0D, 6.5D, 4.5D, 8.0D, 9.5D);
	
	public Takeakari(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(LIT, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (state.getValue(LIT)) {
			if (hStack.isEmpty()) {
				CMEvents.soundFireExting(worldIn, pos);
				worldIn.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); } }
		
		else { //!LIT
			if (state.getValue(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
			
			else { //!WATERLOGGED
				if (hItem == Items.FLINT_AND_STEEL) {
					CMEvents.soundFlint(worldIn, pos);
					CMEvents.toolDamege(1, playerIn, hStack);
					worldIn.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 3); }
		
				if (hItem == Items_Teatime.MATCH.get()) {
					CMEvents.consume1_seFlint(worldIn, pos, playerIn, hand);	
					worldIn.setBlock(pos, state.setValue(LIT, Boolean.valueOf(true)), 3); }
				
				if (hItem != Items.FLINT_AND_STEEL && hItem != Items_Teatime.MATCH.get()) { 
					CMEvents.textNotHave(worldIn, pos, playerIn); } }
		}
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER))
				.setValue(H_FACING, context.getHorizontalDirection().getOpposite());
	}
	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) { worldIn.scheduleTick(pos, this, 10); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if ((Boolean)state.getValue(WATERLOGGED)) { worldIn.scheduleTick(pos, this, 10); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (state.getValue(LIT) && state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, this, 10);
			CMEvents.soundFireExting(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(LIT, Boolean.valueOf(false)), 3); }

		else { }
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, LIT, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		boolean lit = state.getValue(LIT);

		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.3D;
		double d2 = (double)pos.getZ() + 0.5D;

		if (lit == true) {
			if (rand.nextDouble() < 0.03D) {
				/** which, position x y z, speed x y z **/
				Direction direction = state.getValue(H_FACING);
				Direction.Axis direction$axis = direction.getAxis();
				double d4 = rand.nextDouble() * 0.05D;
				double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.35D : d4;
				double d6 = rand.nextDouble() * 6.0D / 16.0D;
				double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.35D : d4;
				worldIn.addParticle(ParticleTypes.SMOKE, d0 - d5, d1 + d6 +0.2D, d2 - d7, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_takeakari").withStyle(ChatFormatting.GRAY));
	}
}
