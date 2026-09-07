package com.ayutaki.chinjufumod.blocks.jpdeco;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
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
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class Andon extends Abstract_WaterLogged {
	public static final MapCodec<Andon> CODEC = simpleCodec(Andon::new);
	@Override
	public MapCodec<? extends Andon> codec() { return CODEC; }
	
	/* Property */
	public static final EnumProperty<Direction> H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 12.0D, 11.0D);
	
	public Andon(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(LIT, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Base_Hake) { return InteractionResult.FAIL; }

		else {
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
						CMEvents.textNotHave(worldIn, pos, playerIn); }
				}
			}
			/** SUCCESS to not put anything on top. **/
			return InteractionResult.SUCCESS;
		}
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
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if ((Boolean)state.getValue(WATERLOGGED)) { tick.scheduleTick(pos, this, 10); }

		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
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
		return AABB_BOX;
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.3D;
		double d2 = (double)pos.getZ() + 0.5D;

		if (state.getValue(LIT)) {
			if (rand.nextDouble() < 0.03D) {
				/** which, position x y z, speed x y z **/
				worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D); }
		}
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_andon").withStyle(ChatFormatting.GRAY));
	} // for 1.20.6
}
