package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Nabe2_TakeSude extends BaseNabe_2Cook {

	public Nabe2_TakeSude(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_2);

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), this.takeInt());
				
				if (this == Dish_Blocks.NABESHIO_nama) {
					ItemStack takeStack = new ItemStack(Items_Teatime.NIGARI, 1);
					if (!playerIn.inventory.add(takeStack)) { playerIn.drop(takeStack, false); } }

				worldIn.setBlock(pos, Dish_Blocks.NABE_kara.defaultBlockState()
						.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
						.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.NABEAZUKI_nama) { return Items_Teatime.YUDEAZUKI; }
		if (this == Dish_Blocks.NABETOUFU_nama) { return Items_Teatime.TOUFU; }
		else { return Items_Teatime.SHIO; }
	}
	
	private int takeInt() {
		if (this == Dish_Blocks.NABESHIO_nama) { return 3; }
		else { return 4; }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterOUT(state)) {
			int i = state.getValue(STAGE_1_2);
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.getBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlock(pos, state.setValue(STAGE_1_2, Integer.valueOf(2)), 3); }
			
			else { } }

		if (waterIn(state)) {
			worldIn.getBlockTicks().scheduleTick(pos, this, 60);
			
			if (this == Dish_Blocks.NABESHIO_nama) { CMEvents.soundBubble(worldIn, pos); }
			else { CMEvents.drop1_ROTTENFOOD(worldIn, pos); }
			
			worldIn.setBlock(pos, Dish_Blocks.NABE_kara.defaultBlockState()
					.setValue(Nabe_kara.H_FACING, state.getValue(H_FACING))
					.setValue(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.setValue(Nabe_kara.WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		super.appendHoverText(stack, worldIn, blockTip, tipFlag);
		blockTip.add(new TranslationTextComponent("tips.take_emptyhand").withStyle(TextFormatting.GRAY));
	}
}
