package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class NabeGohan_Other extends BaseNabe {

	public NabeGohan_Other(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_1_4);

		if (hItem == Items_Teatime.CHAWAN.get()) {
			/** Collect with an Item **/
			CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takeChawan());

			if (i == 4) { worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
						.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.setValue(Nabe_kara.COOK, state.getValue(COOK))
						.setValue(Nabe_kara.DOWN, state.getValue(DOWN))
						.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(3)), 3); }
			else { //i != 4
				worldIn.setBlock(pos, state.setValue(BaseNabe.STAGE_1_4, Integer.valueOf(i + 1)), 3); }
		}
		
		if (hItem == Items.BOWL) {
			if (i == 1) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takeBowl());

				worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
						.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.setValue(Nabe_kara.COOK, state.getValue(COOK))
						.setValue(Nabe_kara.DOWN, state.getValue(DOWN))
						.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(3)), 3); }
			
			if (i != 1) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (hItem != Items_Teatime.CHAWAN.get() && hItem != Items.BOWL) { 
			CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeChawan() {
		if (this == Dish_Blocks.NABEGOHAN_TAKE.get()) { return Items_Teatime.GOHAN_TAKE.get(); }
		if (this == Dish_Blocks.NABEGOHAN_KURI.get()) { return Items_Teatime.GOHAN_KURI.get(); }
		else { return Items_Teatime.SEKIHAN.get(); }
	}
	
	private Item takeBowl() {
		if (this == Dish_Blocks.NABEGOHAN_TAKE.get()) { return Items_Teatime.MUSHIGOME_TAKE.get(); }
		if (this == Dish_Blocks.NABEGOHAN_KURI.get()) { return Items_Teatime.MUSHIGOME_KURI.get(); }
		else { return Items_Teatime.MUSHI_SEKIHAN.get(); }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		
		if (waterIn(state, worldIn, pos)) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
					.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
					.setValue(Nabe_kara.COOK, state.getValue(COOK))
					.setValue(Nabe_kara.DOWN, state.getValue(DOWN))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(3))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
		
		else { }
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_food_nabegohantake_1").withStyle(ChatFormatting.GRAY));
	}
}
