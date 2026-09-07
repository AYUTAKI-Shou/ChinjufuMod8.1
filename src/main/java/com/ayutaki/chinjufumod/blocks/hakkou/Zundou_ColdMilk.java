package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.blocks.dish.BaseZundou_4Stage;
import com.ayutaki.chinjufumod.blocks.dish.Zundou;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Zundou_ColdMilk extends BaseZundou_4Stage {
	/** 1=COLD_MILK, 2=NYUSAN, 3=RENNET, 4=CURD **/
	public Zundou_ColdMilk(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);

		if (i == 1) {
			if (hItem == Items_Teatime.NYUSAN.get()) {
				CMEvents.changeBottle_seSplash(worldIn, pos, playerIn, hand, Items.GLASS_BOTTLE);
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(2)), 3); }
			
			if (hItem != Items_Teatime.NYUSAN.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		if (i == 2) {
			if (hItem == Items_Teatime.RENNET.get()) {
				CMEvents.consume1_seSplash(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(3)), 3); }
			
			if (hItem != Items_Teatime.RENNET.get()) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}

		if (i == 3) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 4) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.CHEESE_CURD.get());
				CMEvents.addEXP(1, worldIn, pos);
	
				worldIn.setBlock(pos, Dish_Blocks.ZUNDOU.get().defaultBlockState()
						.setValue(Zundou.H_FACING, state.getValue(H_FACING))
						.setValue(Zundou.STAGE_1_2, Integer.valueOf(2)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		int i = state.getValue(STAGE_1_4);
		if (waterOUT(state) && i == 3) {
			tick.scheduleTick(pos, Hakkou_Blocks.COLD_MILK.get(), 300 + (50 * rand.nextInt(5))); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.getValue(STAGE_1_4);
		if (waterOUT(state) && i == 3) { 
			worldIn.scheduleTick(pos, Hakkou_Blocks.COLD_MILK.get(), 300 + (50 * worldIn.getRandom().nextInt(5))); }
	}
	
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		int i = state.getValue(STAGE_1_4);
		if (waterOUT(state) && i == 3) { 
			worldIn.scheduleTick(pos, Hakkou_Blocks.COLD_MILK.get(), 300 + (50 * rand.nextInt(5)));
			worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }

		if (waterIn(state)) {
			worldIn.scheduleTick(pos, Hakkou_Blocks.COLD_MILK.get(), 60);
			CMEvents.soundSnowBreak(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.ZUNDOU.get().defaultBlockState()
					.setValue(Zundou.H_FACING, state.getValue(H_FACING))
					.setValue(Zundou.STAGE_1_2, Integer.valueOf(2)), 3); }
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(Items_Teatime.ZUNDOU.get());
	}
}
