package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Taru_Hakkou extends Base_Stage05 {
	/* Collision */
	private static final VoxelShape AABB_BOX = VoxelShapes.or(Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.25D, 8.0D, 0.25D, 15.75D, 12.0D, 15.75D),
			Block.box(0.5D, 4.0D, 0.5D, 15.5D, 8.0D, 15.5D),
			Block.box(0.75D, 0.0D, 0.75D, 15.25D, 4.0D, 15.25D));
	private static final VoxelShape AABB_TANA = VoxelShapes.or(Block.box(0.0D, 2.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 2.0D),
			Block.box(0.0D, 0.0D, 14.0D, 2.0D, 2.0D, 16.0D),
			Block.box(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D),
			Block.box(14.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D));

	/** 0=空, 1=麹の空棚, 2=味噌の空樽, 3=紅茶の空棚, 4=浅漬けの空樽, 5=白菜漬の空樽 **/
	public Taru_Hakkou(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_5);

		int gHC = hStack.getCount();

		if (hStack.isEmpty()) {
			if (i == 1) {
				CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, Items_Seasonal.TANMONO, 4);
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
						if (hItem == Items_Teatime.SAKEBOT) {
							/** Bottle into the Barrel. **/
							CMEvents.consume1_seDish(worldIn, pos, playerIn, hand);
							CMEvents.soundSAKEBottleFill(worldIn, pos);
							worldIn.setBlock(pos, Hakkou_Blocks.JUKUSEI_TARU.defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem != Items_Teatime.SAKEBOT) { CMEvents.textNotHave(worldIn, pos, playerIn); }
					} // i == 0
					
					/* Dry shelf */
					if (i == 3) {
						if (hItem == Items_Teatime.CHADUTSU) {
							CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.KOUCHA_TARU.defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items_Teatime.CHABA_GREEN && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.KOUCHA_TARU.defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items.BROWN_MUSHROOM && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.KINOKO_TARU.defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items.KELP && gHC >= 4) {
							/** Consume 4 Items. **/
							CMEvents.consumeN_seSnowP(4, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.KONBU_TARU.defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items_Teatime.NORI_N && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.NORI_TARU.defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items_Teatime.PEPPER_RAW && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.PEPPER_TARU.defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if (hItem == Items_Teatime.VANILLA_RAW && gHC >= 8) {
							/* Consume 8 Items. */
							CMEvents.consumeN_seSnowP(8, worldIn, pos, playerIn, hand);
							worldIn.setBlock(pos, Hakkou_Blocks.VANILLA_TARU.defaultBlockState()
									.setValue(Base_Stage05.STAGE_0_5, Integer.valueOf(0)), 3); }
						
						if ((hItem == Items_Teatime.CHABA_GREEN && gHC < 8) || (hItem == Items.BROWN_MUSHROOM && gHC < 8) ||
								(hItem == Items.KELP && gHC < 4) || (hItem == Items_Teatime.NORI_N && gHC < 8) || 
								(hItem == Items_Teatime.PEPPER_RAW && gHC < 8) || (hItem == Items_Teatime.VANILLA_RAW && gHC < 8)) {
							CMEvents.textNotEnough_Items(worldIn, pos, playerIn); }
						
						if (hItem != Items_Teatime.CHADUTSU && hItem != Items_Teatime.CHABA_GREEN && hItem != Items.BROWN_MUSHROOM && 
								hItem != Items.KELP && hItem != Items_Teatime.NORI_N && 
								hItem != Items_Teatime.PEPPER_RAW && hItem != Items_Teatime.VANILLA_RAW) {
							CMEvents.textNotHave(worldIn, pos, playerIn); }
					} //i == 3
				} //It is not Waterlogged.
			}
			
			else { //i != 0 && i != 3
				CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Gives a value when placed. */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Update BlockState. */
	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		int i = state.getValue(STAGE_0_5);
		return (i == 1 || i == 3)? AABB_TANA : AABB_BOX;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_taru_hakkou").withStyle(TextFormatting.GRAY));
	}
}
