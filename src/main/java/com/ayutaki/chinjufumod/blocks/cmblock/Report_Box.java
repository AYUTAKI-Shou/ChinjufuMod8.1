package com.ayutaki.chinjufumod.blocks.cmblock;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_Harvest;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class Report_Box extends Abstract_Harvest {
	/* Property */
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final IntegerProperty STAGE_1_15 = IntegerProperty.create("stage", 1, 15);
	/* 1=E, 2=B, 3=R, 4=P, 5=N, 6=-1, 7=N, 8=-2, 9=N, 10=-3, 11=N, 12=-4, 13=N, 14=-5, 15=N */
	public Report_Box(Block.Properties props) {
		super(props);
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_15, Integer.valueOf(1)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(STAGE_1_15, Integer.valueOf(1));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_15);
		int gHC = hStack.getCount();

		Direction direction = state.get(H_FACING);
		BlockState northState = worldIn.getBlockState(pos.north());
		BlockState southState = worldIn.getBlockState(pos.south());
		BlockState eastState = worldIn.getBlockState(pos.east());
		BlockState westState = worldIn.getBlockState(pos.west());
		
		boolean wait = (i == 4 || i == 6 || i == 8 || i == 10 || i == 12 || i == 14);
		boolean repo = (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15);
		
		switch (direction) {
		case NORTH:
		default:
			if (northState.getMaterial().isReplaceable()) {
				if (northState.getBlock() instanceof FlowingFluidBlock) {
					CMEvents.textIsBlocked(worldIn, pos, playerIn); }
				
				else { //!north_Fluid
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
						if (i != 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(i + 1)), 3); }
						if (i == 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(1)), 3); }
					} } }
			
			else { //!north_Replaceable
				CMEvents.textIsBlocked(worldIn, pos, playerIn); }
			break;

		case SOUTH:
			if (southState.getMaterial().isReplaceable()) {
				if (southState.getBlock() instanceof FlowingFluidBlock) {
					CMEvents.textIsBlocked(worldIn, pos, playerIn); }
				
				else { //!south_Fluid
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
						if (i != 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(i + 1)), 3); }
						if (i == 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(1)), 3); }
					} } }
			
			else { //!south_Replaceable
				CMEvents.textIsBlocked(worldIn, pos, playerIn); }
			break;

		case EAST:
			if (eastState.getMaterial().isReplaceable()) {
				if (eastState.getBlock() instanceof FlowingFluidBlock) {
					CMEvents.textIsBlocked(worldIn, pos, playerIn); }
				
				else { //!east_Fluid
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
						if (i != 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(i + 1)), 3); }
						if (i == 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(1)), 3); }
					} } }
			
			else { //!east_Replaceable
				CMEvents.textIsBlocked(worldIn, pos, playerIn); }
			break;
			
		case WEST:
			if (westState.getMaterial().isReplaceable()) {
				if (westState.getBlock() instanceof FlowingFluidBlock) {
					CMEvents.textIsBlocked(worldIn, pos, playerIn); }
				
				else { //!west_Fluid
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
						if (i != 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(i + 1)), 3); }
						if (i == 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(1)), 3); }
					} } }
			
			else { //!west_Replaceable
				CMEvents.textIsBlocked(worldIn, pos, playerIn); }
			break;
		} // switch
		
		return ActionResultType.SUCCESS;
	}
	
	private void dye_seBottle(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(1, playerIn, hand);
		worldIn.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 0.7F, 1.0F);
		worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(state.get(STAGE_1_15) + 1)), 3); }
	
	private void paper30_seWool(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(30, playerIn, hand);
		CMEvents.soundClothPlace(worldIn, pos);
		worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(state.get(STAGE_1_15) + 1)), 3); }
	
	private void flesh16_seWool(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		CMEvents.consumeN_Hand(16, playerIn, hand);
		CMEvents.soundClothPlace(worldIn, pos);
		worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(state.get(STAGE_1_15) + 1)), 3); }
	
	private void take_5WorkOrder(World worldIn, BlockPos pos, PlayerEntity playerIn) {
		worldIn.playSound(null, pos, SoundEvents.BLOCK_NOTE_BLOCK_BELL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		CMEvents.emptyTake_NItem(worldIn, pos, playerIn, Items_Chinjufu.WORK_ORDER, 5); }

	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(H_FACING, rotation.rotate(state.get(H_FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(H_FACING)));
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		worldIn.getPendingBlockTicks().scheduleTick(pos, Chinjufu_Blocks.REPORT_BOX, 120);
		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.get(STAGE_1_15);
		boolean repo = (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15);
		if (repo) { worldIn.getPendingBlockTicks().scheduleTick(pos, Chinjufu_Blocks.REPORT_BOX, 120); }
	}

	private void dropOrder(BlockState state, ServerWorld worldIn, BlockPos pos) {
		ItemStack stack = new ItemStack(Items_Chinjufu.WORK_ORDER, 5);
		
		Direction direction = state.get(H_FACING);
		double sn = (direction == Direction.SOUTH)? 1.2D : ((direction == Direction.NORTH)? - 0.7D : 0.0D); 
		double ew = (direction == Direction.EAST)? 1.2D : ((direction == Direction.WEST)? - 0.7D : 0.0D);
		
		InventoryHelper.spawnItemStack(worldIn, pos.getX() + ew, pos.getY() + 0.5D, pos.getZ() + sn, stack);
	}
	
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_15);
		boolean repo = (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15);
		
		if (repo) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, Chinjufu_Blocks.REPORT_BOX, 120);
			worldIn.playSound(null, pos, SoundEvents.BLOCK_NOTE_BLOCK_BELL, SoundCategory.BLOCKS, 1.0F, 1.0F);
			this.dropOrder(state, worldIn, pos); 
			
			if (i == 15) { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(1)), 3); }
			else { worldIn.setBlockState(pos, state.with(STAGE_1_15, Integer.valueOf(i + 1)), 3); }
		}

		else { }
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_15);
		boolean repo = (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15);
		
		if (repo) {
			for(int k = 0; k < 5; ++k) {
				double x = pos.getX();
				double y = pos.getY();
				double z = pos.getZ();
				worldIn.playSound(x, y, z, SoundEvents_CM.NOISE, SoundCategory.BLOCKS, 0.5F, 1.0F, false); } }
	}
	
	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(H_FACING, STAGE_1_15);
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
		return ToolType.PICKAXE;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_report_box").applyTextStyle(TextFormatting.GRAY));
	}
}
