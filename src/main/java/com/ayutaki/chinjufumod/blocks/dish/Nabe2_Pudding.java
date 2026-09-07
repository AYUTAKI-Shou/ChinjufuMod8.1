package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_NoTabLater;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
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

public class Nabe2_Pudding extends BaseNabe_2Cook {

	public Nabe2_Pudding(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_2);

		if (i == 1) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		else { //i != 1
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, this.takeItem(), 1);
				ItemStack take = new ItemStack(Items_Seasonal.TANMONO, 2);
				if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }

				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState()); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.NABEPUDDING_green) { return Items_NoTabLater.RAW_PUDDING_GREEN; }
		if (this == Dish_Blocks.NABEPUDDING_red) { return Items_NoTabLater.RAW_PUDDING_RED; }
		if (this == Dish_Blocks.NABEPUDDING_cacao) { return Items_NoTabLater.RAW_PUDDING_CACAO; }
		else { return Items_NoTabLater.RAW_PUDDING; }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {

		if (waterOUT(state)) {
			int i = state.get(STAGE_1_2);
			if (cookingIn(worldIn, pos) && i == 1) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
				worldIn.setBlockState(pos, state.with(STAGE_1_2, Integer.valueOf(2))); }
			
			else { } }

		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			this.dropTANMONO(worldIn, pos);
			this.dropGlass(worldIn, pos);
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			
			worldIn.setBlockState(pos, Dish_Blocks.NABE_kara.getDefaultState()
					.with(Nabe_kara.H_FACING, state.get(H_FACING))
					.with(Nabe_kara.STAGE_1_4, Integer.valueOf(4))
					.with(Nabe_kara.WATERLOGGED, state.get(WATERLOGGED)), 3);
		}
	}

	private void dropTANMONO(ServerWorld worldIn, BlockPos pos) {
		ItemStack stack = new ItemStack(Items_Seasonal.TANMONO, 2);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}
	
	private void dropGlass(ServerWorld worldIn, BlockPos pos) {
		ItemStack stack = new ItemStack(Items_Teatime.DRINKGLASS, 3);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, blockTip, tipFlag);
		blockTip.add(new TranslationTextComponent("tips.take_emptyhand").applyTextStyle(TextFormatting.GRAY));
	}
}
