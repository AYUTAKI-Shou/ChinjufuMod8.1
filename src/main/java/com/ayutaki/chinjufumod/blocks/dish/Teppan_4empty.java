package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Teppan_4empty extends Abstract_Teppan {
	public static final MapCodec<Teppan_4empty> CODEC = simpleCodec(Teppan_4empty::new);
	@Override
	public MapCodec<? extends Teppan_4empty> codec() { return CODEC; }
	
	protected static final int COOK_TIME = 1200;
	/* Property */
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(2.5D, 0.0D, 2.5D, 13.5D, 1.0D, 13.5D);
	private static final VoxelShape EMPTY_BOX = Block.box(2.5D, 0.0D, 2.5D, 13.5D, 0.5D, 13.5D);
	
	public Teppan_4empty(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);
		/** 1=raw, 2=re, 3=sauce, 4=empty **/
		
		if (i < 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 3) {
			if (hItem == Items_Teatime.SARA.get()) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takeItem());
				CMEvents.addEXP(1, worldIn, pos);
				
				worldIn.setBlock(pos, Dish_Blocks.OKONOMIYAKI_nama.get().defaultBlockState()
						.setValue(H_FACING, state.getValue(H_FACING))
						.setValue(DOWN, state.getValue(DOWN))
						.setValue(WATERLOGGED, state.getValue(WATERLOGGED))
						.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }
				
				if (hItem != Items_Teatime.SARA.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}
	
	private Item takeItem() {
		if (this == Dish_Blocks.OKONOMIYAKI_nama.get()) { return Items_Teatime.OKONOMIYAKI.get(); }
		if (this == Dish_Blocks.OKONOMIS_nama.get()) { return Items_Teatime.OKONOMIS.get(); }
		else { return Items_Teatime.OKONOMIC.get(); }
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		BlockState downState = worldIn.getBlockState(pos.below());
		Block downBlock = downState.getBlock();
		boolean teppan = (downBlock instanceof AbstractOvenBlock || downBlock instanceof Kit_Cooktop);

		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(STAGE_1_4, Integer.valueOf(1))
				.setValue(DOWN, Boolean.valueOf(teppan))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		int i = state.getValue(STAGE_1_4);
		if(waterOUT(state)) {
			if (i < 3 && cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(3))); }
			
			else { } }
	
		if (waterIn(state)) {
			if (i == 4) { }
			else { //i != 4
				worldIn.scheduleTick(pos, this, 60); } }

		boolean down = connectTeppan(worldIn, pos, Direction.DOWN);
		return state.setValue(DOWN, down);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.getValue(STAGE_1_4);
		if(waterOUT(state)) {
			if (i < 3 && cookingIn(worldIn, pos)) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(3))); }
			
			else { } }
	
		if (waterIn(state)) {
			if (i == 4) { }
			else { //i != 4
				worldIn.scheduleTick(pos, this, 60); } }
	}

	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_4);
		
		if(waterOUT(state)) {
			if (i < 3 && cookingIn(worldIn, pos)) {
				worldIn.setBlock(pos, this.toClick().defaultBlockState()
						.setValue(Teppan_4emptyC.H_FACING, state.getValue(H_FACING))
						.setValue(Teppan_4emptyC.DOWN, state.getValue(DOWN))
						.setValue(Teppan_4emptyC.WATERLOGGED, state.getValue(WATERLOGGED))
						.setValue(Teppan_4emptyC.STAGE_1_2, Integer.valueOf(i)), 3);
				CMEvents.soundKotePlace(worldIn, pos);
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(3))); }
			
			else { } }
	
		if (waterIn(state)) {
			if (i == 4) { }
			else { //i != 4
				worldIn.scheduleTick(pos, this, 60);

				CMEvents.drop1_ROTTENFOOD(worldIn, pos);
				worldIn.setBlock(pos, Dish_Blocks.OKONOMIYAKI_nama.get().defaultBlockState()
						.setValue(H_FACING, state.getValue(H_FACING))
						.setValue(DOWN, state.getValue(DOWN))
						.setValue(WATERLOGGED, state.getValue(WATERLOGGED))
						.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }
		}
	}
	
	private Block toClick() {
		if (this == Dish_Blocks.OKONOMIYAKI_nama.get()) { return Dish_Blocks.OKONOMIYAKI_click.get(); }
		if (this == Dish_Blocks.OKONOMIS_nama.get()) { return Dish_Blocks.OKONOMIS_click.get(); }
		else { return Dish_Blocks.OKONOMIC_click.get(); }
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, STAGE_1_4, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		int i = state.getValue(STAGE_1_4);
		return (i == 4)? EMPTY_BOX : AABB_BOX;
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		int i = state.getValue(STAGE_1_4);
		if (i == 4) { }
		
		else { //i != 4
			if (cookingIn(worldIn, pos)) {
				if (rand.nextDouble() < 0.1D) {
					worldIn.playLocalSound(d0, d1, d2, SoundEvents_CM.JUU.get(), SoundSource.BLOCKS, 0.2F, 1.0F, false); }

				if (i != 1 && rand.nextDouble() < 0.25D) {
					/** which, position x y z, speed x y z **/
					worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6 - 0.3D, d2 + d4, 0.0D, 0.0D, 0.0D); } }
			
			else { }
		}
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, context, blockTip, tipFlag);
		blockTip.add(Component.translatable("tips.take_plate").withStyle(ChatFormatting.GRAY));
	}
}
