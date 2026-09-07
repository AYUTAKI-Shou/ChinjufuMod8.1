package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Taru_Hakkou extends Base_Stage05 {
	/* Collision */
	private static final VoxelShape AABB_BOX = Shapes.or(Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.25D, 8.0D, 0.25D, 15.75D, 12.0D, 15.75D),
			Block.box(0.5D, 4.0D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.75D, 0.0D, 0.75D, 15.25D, 4.0D, 15.25D));
	private static final VoxelShape AABB_TANA = Shapes.or(Block.box(0.0D, 2.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 2.0D),
			Block.box(0.0D, 0.0D, 14.0D, 2.0D, 2.0D, 16.0D),
			Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D),
			Block.box(14.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D));

	/** 0=空, 1=麹の空棚, 2=味噌の空樽, 3=紅茶の空棚, 4=浅漬けの空樽, 5=白菜漬の空樽 **/
	public Taru_Hakkou(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_5);

		int gHC = hStack.getCount();

		if (hStack.isEmpty()) {
			if (i == 1) {
				CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, Items_Seasonal.TANMONO.get(), 4);
				worldIn.setBlock(pos, state.setValue(STAGE_0_5, Integer.valueOf(3)), 3); }
			
			if (i == 0 || i == 3) { CMEvents.textNotHave(worldIn, pos, playerIn); }
			
			if (i != 0 && i != 1 && i != 3) { //It needs to be made clear.
				CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		}

		else { //!empty
			if (i == 0 || i == 3) {
				if (state.getValue(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
				
				else { //!WATERLOGGED
					/* Empty barrel */
					if (i == 0) {
						if (hItem == Items_Teatime.SAKEBOT.get()) {
							/** Collect with an Item **/
							CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
							CMEvents.soundSAKEBottleFill(worldIn, pos);
							worldIn.setBlock(pos, Hakkou_Blocks.JUKUSEI_TARU.get().defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem != Items_Teatime.SAKEBOT.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					} // i == 0
					
					/* Dry shelf */
					if (i == 3) {
						if (hItem == Items_Teatime.CHADUTSU.get()) {
							CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.KOUCHA_TARU.get().defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items_Teatime.CHABA_GREEN.get() && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.KOUCHA_TARU.get().defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items.BROWN_MUSHROOM && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.KINOKO_TARU.get().defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items.KELP && gHC >= 4) {
							/** Consume 4 Items. **/
							CMEvents.consumeN_seSnowP(4, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.KONBU_TARU.get().defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items_Teatime.NORI_N.get() && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.NORI_TARU.get().defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items_Teatime.PEPPER_RAW.get() && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.PEPPER_TARU.get().defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items_Teatime.VANILLA_RAW.get() && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.VANILLA_TARU.get().defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if ((hItem == Items_Teatime.CHABA_GREEN.get() && gHC < 8) || (hItem == Items.BROWN_MUSHROOM && gHC < 8) ||
								(hItem == Items.KELP && gHC < 4) || (hItem == Items_Teatime.NORI_N.get() && gHC < 8) || 
								(hItem == Items_Teatime.PEPPER_RAW.get() && gHC < 8) || (hItem == Items_Teatime.VANILLA_RAW.get() && gHC < 8)) {
							CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items_Teatime.CHADUTSU.get() && hItem != Items_Teatime.CHABA_GREEN.get() && hItem != Items.BROWN_MUSHROOM && 
								hItem != Items.KELP && hItem != Items_Teatime.NORI_N.get() && 
								hItem != Items_Teatime.PEPPER_RAW.get() && hItem != Items_Teatime.VANILLA_RAW.get()) {
							CMEvents.textNotHave(worldIn, pos, playerIn); }
					} //i == 3
				} //It is not Waterlogged.
			}
			
			else { //i != 0 && i != 3
				CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));		
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		int i = state.getValue(STAGE_0_5);
		return (i == 1 || i == 3)? AABB_TANA : AABB_BOX;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_taru_hakkou").withStyle(ChatFormatting.GRAY));
	}
}
