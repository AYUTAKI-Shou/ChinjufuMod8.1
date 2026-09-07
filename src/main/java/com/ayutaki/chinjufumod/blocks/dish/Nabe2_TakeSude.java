package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class Nabe2_TakeSude extends BaseNabe_2Cook {
	
	public Nabe2_TakeSude(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_2);

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), this.takeInt());
	
				if (this == Dish_Blocks.NABESHIO_nama.get()) {
					ItemStack takeStack = new ItemStack(Items_Teatime.NIGARI.get(), 1);
					if (!playerIn.getInventory().add(takeStack)) { playerIn.drop(takeStack, false); } }
				
				worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
						.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.NABEAZUKI_nama.get()) { return Items_Teatime.YUDEAZUKI.get(); }
		if (this == Dish_Blocks.NABETOUFU_nama.get()) { return Items_Teatime.TOUFU.get(); }
		else { return Items_Teatime.SHIO.get(); }
	}
	
	private int takeInt() {
		if (this == Dish_Blocks.NABESHIO_nama.get()) { return 3; }
		else { return 4; }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		
		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_2);
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlock(pos, state.setValue(STAGE_1_2, Integer.valueOf(2)), 3); }
			
			else { } }

		if (waterIn(state)) {
			worldIn.scheduleTick(pos, this, 60);

			if (this == Dish_Blocks.NABESHIO_nama.get()) { CMEvents.soundBubble(worldIn, pos); }
			else { CMEvents.drop1_ROTTENFOOD(worldIn, pos); }
			
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.get().defaultBlockState()
					.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		super.appendHoverText(stack, worldIn, blockTip, tipFlag);
		blockTip.add(new TranslatableComponent("tips.take_emptyhand").withStyle(ChatFormatting.GRAY));
	}
}
