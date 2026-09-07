package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.BaseNabe;
import com.ayutaki.chinjufumod.blocks.dish.Nabe_kara;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
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

public class NabeAmazake extends BaseNabe {

	public NabeAmazake(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_4);

		if (hItem == Items_Teatime.YUNOMI) {
			/** Collect with an Item **/
			CMEvents.changeDish_seTea(worldIn, pos, playerIn, hand, Items_Teatime.AMAZAKEGLASS);

			if (i == 4) {
				worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
						.with(Nabe_kara.H_FACING, state.get(H_FACING))
						.with(Nabe_kara.COOK, state.get(COOK))
						.with(Nabe_kara.DOWN, state.get(DOWN))
						.with(Nabe_kara.STAGE_1_4, Integer.valueOf(2))); }
			else { //i != 4
				worldIn.setBlockState(pos, state.with(BaseNabe.STAGE_1_4, Integer.valueOf(i + 1))); }
		}
		
		if (hItem != Items_Teatime.YUNOMI) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterIn(state, worldIn, pos)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
					.with(Nabe_kara.H_FACING, state.get(H_FACING))
					.with(Nabe_kara.COOK, state.get(COOK))
					.with(Nabe_kara.DOWN, state.get(DOWN))
					.with(Nabe_kara.STAGE_1_4, Integer.valueOf(2))
					.with(Nabe_kara.WATERLOGGED, state.get(WATERLOGGED)), 3); }

		else { }
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_food_nabeaz_1").applyTextStyle(TextFormatting.GRAY));
	}
}
