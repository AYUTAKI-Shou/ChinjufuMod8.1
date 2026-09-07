package com.ayutaki.chinjufumod.blocks.season;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.BaseFood_Stage4WP;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Kakigouri extends BaseFood_Stage4WP {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(6.4D, 0.0D, 6.4D, 9.6D, 5.0D, 9.6D);
	private static final VoxelShape AABB_DOWN = Block.makeCuboidShape(6.4D, -8.0D, 6.4D, 9.6D, 0.1D, 9.6D);

	public Kakigouri(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = state.get(STAGE_1_4);

		if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 4
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
	
				/** add Potion Effect. **/
				if (!worldIn.isRemote) {
					boolean PLANE = (this == Seasonal_Blocks.KAKIGOURI_block);
					
					if (i == 1) {
						if (PLANE) { playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 400, 0)); }
						else { playerIn.addPotionEffect(new EffectInstance(takeEffect(), 600, 0));} }
		
					if (i == 2) {
						if (PLANE) { playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 500, 0)); }
						else { playerIn.addPotionEffect(new EffectInstance(takeEffect(), 780, 0)); } }
		
					if (i == 3) {
						if (PLANE) { playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 600, 0)); }
						else { playerIn.addPotionEffect(new EffectInstance(takeEffect(), 900, 0)); } }
				}
				
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Effect takeEffect() {
		if (this == Seasonal_Blocks.KAKIGOURI_apple) { return Effects.RESISTANCE; }
		if (this == Seasonal_Blocks.KAKIGOURI_cherry) { return Effects.STRENGTH; }
		if (this == Seasonal_Blocks.KAKIGOURI_citrus) { return Effects.FIRE_RESISTANCE; }
		if (this == Seasonal_Blocks.KAKIGOURI_grape) { return Effects.NIGHT_VISION; }
		if (this == Seasonal_Blocks.KAKIGOURI_tea) { return Effects.HASTE; }
		else { return Effects.HASTE; }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_4);
		
		if (i == 4) { }
		else { //i != 4
			if (waterIn(state, worldIn, pos)) {
				worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
				CMEvents.soundSnowBreak(worldIn, pos);
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(4))); }

			else { } }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_kakigouri").applyTextStyle(TextFormatting.GRAY));
	}
}
