package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Report_Box extends Block {
	public static final MapCodec<Report_Box> CODEC = simpleCodec(Report_Box::new);
	@Override
	public MapCodec<? extends Report_Box> codec() { return CODEC; }
	
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_15 = IntegerProperty.create("stage", 1, 15);
	/* 1=E, 2=B, 3=R, 4=P, 5=N, 6=-1, 7=N, 8=-2, 9=N, 10=-3, 11=N, 12=-4, 13=N, 14=-5, 15=N */

	public Report_Box(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(STAGE_1_15, Integer.valueOf(1)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
				.setValue(STAGE_1_15, Integer.valueOf(1));
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_15);
		int gHC = hStack.getCount();
	
		Direction direction = state.getValue(H_FACING);
		BlockState northState = worldIn.getBlockState(pos.north());
		BlockState southState = worldIn.getBlockState(pos.south());
		BlockState eastState = worldIn.getBlockState(pos.east());
		BlockState westState = worldIn.getBlockState(pos.west());
		
		boolean wait = (i == 4 || i == 6 || i == 8 || i == 10 || i == 12 || i == 14);
		boolean repo = (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15);
		
		switch (direction) {
		case NORTH:
		default:
			if (northState.canBeReplaced()) {
				if (northState.getBlock() instanceof LiquidBlock) {
					CMEvents.textIsBlocked(worldIn, pos, playerIn); }
				
				else { //!Fluid
					if (i == 1) {
						if (hItem == Items.BLACK_DYE) {
							this.dye_seBottle(state, worldIn, pos, playerIn, hand); }
						
						if (hItem != Items.BLACK_DYE && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (i == 2) {
						if (hItem == Items.RED_DYE) {
							this.dye_seBottle(state, worldIn, pos, playerIn, hand); }
						
						if (hItem != Items.RED_DYE && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (i == 3) {
						if (hItem == Items.PAPER && gHC >= 30) { 
							this.paper30_seWool(state, worldIn, pos, playerIn, hand); }
						
						if (hItem == Items.PAPER && gHC < 30) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items.PAPER && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (wait) {
						if (hItem == Items.ROTTEN_FLESH && gHC >= 16) { 
							this.flesh16_seWool(state, worldIn, pos, playerIn, hand); }
						
						if (hItem == Items.ROTTEN_FLESH && gHC < 16) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items.ROTTEN_FLESH && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					} 
					
					if (repo && hStack.isEmpty()) {
						this.take_5WorkOrder(worldIn, pos, playerIn);
						if (i == 15) { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(1)), 3); }
						else { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(i + 1)), 3); } //i != 15
					} } }
			
			else { //!Replaceable
				CMEvents.textIsBlocked(worldIn, pos, playerIn); }
			break;

		case SOUTH:
			if (southState.canBeReplaced()) {
				if (southState.getBlock() instanceof LiquidBlock) {
					CMEvents.textIsBlocked(worldIn, pos, playerIn); }
				
				else { //!Fluid
					if (i == 1) {
						if (hItem == Items.BLACK_DYE) {
							this.dye_seBottle(state, worldIn, pos, playerIn, hand); }
						
						if (hItem != Items.BLACK_DYE && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (i == 2) {
						if (hItem == Items.RED_DYE) {
							this.dye_seBottle(state, worldIn, pos, playerIn, hand); }
						
						if (hItem != Items.RED_DYE && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (i == 3) {
						if (hItem == Items.PAPER && gHC >= 30) { 
							this.paper30_seWool(state, worldIn, pos, playerIn, hand); }
						
						if (hItem == Items.PAPER && gHC < 30) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items.PAPER && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (wait) {
						if (hItem == Items.ROTTEN_FLESH && gHC >= 16) { 
							this.flesh16_seWool(state, worldIn, pos, playerIn, hand); }
						
						if (hItem == Items.ROTTEN_FLESH && gHC < 16) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items.ROTTEN_FLESH && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					} 
					
					if (repo && hStack.isEmpty()) {
						this.take_5WorkOrder(worldIn, pos, playerIn);
						if (i == 15) { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(1)), 3); }
						else { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(i + 1)), 3); } //i != 15
					} } }
			
			else { //!Replaceable
				CMEvents.textIsBlocked(worldIn, pos, playerIn); }
			break;

		case EAST:
			if (eastState.canBeReplaced()) {
				if (eastState.getBlock() instanceof LiquidBlock) {
					CMEvents.textIsBlocked(worldIn, pos, playerIn); }
				
				else { //!Fluid
					if (i == 1) {
						if (hItem == Items.BLACK_DYE) {
							this.dye_seBottle(state, worldIn, pos, playerIn, hand); }
						
						if (hItem != Items.BLACK_DYE && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (i == 2) {
						if (hItem == Items.RED_DYE) {
							this.dye_seBottle(state, worldIn, pos, playerIn, hand); }
						
						if (hItem != Items.RED_DYE && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (i == 3) {
						if (hItem == Items.PAPER && gHC >= 30) { 
							this.paper30_seWool(state, worldIn, pos, playerIn, hand); }
						
						if (hItem == Items.PAPER && gHC < 30) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items.PAPER && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (wait) {
						if (hItem == Items.ROTTEN_FLESH && gHC >= 16) { 
							this.flesh16_seWool(state, worldIn, pos, playerIn, hand); }
						
						if (hItem == Items.ROTTEN_FLESH && gHC < 16) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items.ROTTEN_FLESH && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					} 
					
					if (repo && hStack.isEmpty()) {
						this.take_5WorkOrder(worldIn, pos, playerIn);
						if (i == 15) { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(1)), 3); }
						else { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(i + 1)), 3); } //i != 15
					} } }
			
			else { //!Replaceable
				CMEvents.textIsBlocked(worldIn, pos, playerIn); }
			break;
			
		case WEST:
			if (westState.canBeReplaced()) {
				if (westState.getBlock() instanceof LiquidBlock) {
					CMEvents.textIsBlocked(worldIn, pos, playerIn); }
				
				else { //!Fluid
					if (i == 1) {
						if (hItem == Items.BLACK_DYE) {
							this.dye_seBottle(state, worldIn, pos, playerIn, hand); }
						
						if (hItem != Items.BLACK_DYE && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (i == 2) {
						if (hItem == Items.RED_DYE) {
							this.dye_seBottle(state, worldIn, pos, playerIn, hand); }
						
						if (hItem != Items.RED_DYE && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (i == 3) {
						if (hItem == Items.PAPER && gHC >= 30) { 
							this.paper30_seWool(state, worldIn, pos, playerIn, hand); }
						
						if (hItem == Items.PAPER && gHC < 30) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items.PAPER && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					}
					
					if (wait) {
						if (hItem == Items.ROTTEN_FLESH && gHC >= 16) { 
							this.flesh16_seWool(state, worldIn, pos, playerIn, hand); }
						
						if (hItem == Items.ROTTEN_FLESH && gHC < 16) { CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items.ROTTEN_FLESH && !hStack.isEmpty()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					} 
					
					if (repo && hStack.isEmpty()) {
						this.take_5WorkOrder(worldIn, pos, playerIn);
						if (i == 15) { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(1)), 3); }
						else { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(i + 1)), 3); } //i != 15
					} } }
			
			else { //!Replaceable
				CMEvents.textIsBlocked(worldIn, pos, playerIn); }
			break;
		} // switch
		
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	private void dye_seBottle(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 0.7F, 1.0F);
		worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(state.getValue(STAGE_1_15) + 1)), 3); }
	
	private void paper30_seWool(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(30, playerIn, hand);
		CMEvents.soundClothPlace(worldIn, pos);
		worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(state.getValue(STAGE_1_15) + 1)), 3); }
	
	private void flesh16_seWool(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.consumeN_Hand(16, playerIn, hand);
		CMEvents.soundClothPlace(worldIn, pos);
		worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(state.getValue(STAGE_1_15) + 1)), 3); }
	
	private void take_5WorkOrder(Level worldIn, BlockPos pos, Player playerIn) {
		worldIn.playSound(null, pos, SoundEvents.NOTE_BLOCK_BELL.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
		CMEvents.emptyTake_NItem(worldIn, pos, playerIn, Items_Chinjufu.WORK_ORDER.get(), 5); }

	
	/* HORIZONTAL Property */
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
		worldIn.scheduleTick(pos, Chinjufu_Blocks.REPORT_BOX.get(), 120);
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.getValue(STAGE_1_15);
		boolean repo = (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15);
		if (repo) { worldIn.scheduleTick(pos, Chinjufu_Blocks.REPORT_BOX.get(), 120); }
	}

	private void dropOrder(BlockState state, ServerLevel worldIn, BlockPos pos) {
		ItemStack stack = new ItemStack(Items_Chinjufu.WORK_ORDER.get(), 5);

		Direction direction = state.getValue(H_FACING);
		double sn = (direction == Direction.SOUTH)? 1.2D : ((direction == Direction.NORTH)? - 0.7D : 0.0D); 
		double ew = (direction == Direction.EAST)? 1.2D : ((direction == Direction.WEST)? - 0.7D : 0.0D);
		
		Containers.dropItemStack(worldIn, pos.getX() + ew, pos.getY() + 0.5D, pos.getZ() + sn, stack);
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_15);
		boolean repo = (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15);
		
		if (repo) {
			worldIn.scheduleTick(pos, Chinjufu_Blocks.REPORT_BOX.get(), 120);
			worldIn.playSound(null, pos, SoundEvents.NOTE_BLOCK_BELL.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
			this.dropOrder(state, worldIn, pos);
			
			if (i != 15) { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(i + 1)), 3); }
			if (i == 15) { worldIn.setBlock(pos, state.setValue(STAGE_1_15, Integer.valueOf(1)), 3); }
		 }
		
		else { }
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_15);
		boolean repo = (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15);
		
		if (repo) {
			for(int k = 0; k < 5; ++k) {
				double x = pos.getX();
				double y = pos.getY();
				double z = pos.getZ();
				worldIn.playLocalSound(x, y, z, SoundEvents_CM.NOISE.get(), SoundSource.BLOCKS, 0.5F, 1.0F, false); } }
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, STAGE_1_15);
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_report_box").withStyle(ChatFormatting.GRAY));
	} // for 1.20.6
}
