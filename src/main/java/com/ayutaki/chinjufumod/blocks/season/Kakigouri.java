package com.ayutaki.chinjufumod.blocks.season;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.dish.BaseFood_Stage4Water;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Seasonal_Blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Kakigouri extends BaseFood_Stage4Water {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(6.4D, 0.0D, 6.4D, 9.6D, 5.0D, 9.6D);
	private static final VoxelShape AABB_DOWN = Block.box(6.4D, -8.0D, 6.4D, 9.6D, 0.1D, 9.6D);

	public Kakigouri(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);

		if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 4
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
	
				/** add Potion Effect. **/
				if (!worldIn.isClientSide) {
					boolean PLANE = (this == Seasonal_Blocks.KAKIGOURI_block.get());
					
					if (i == 1) {
						if (PLANE) { playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0)); }
						else { playerIn.addEffect(new MobEffectInstance(this.takeEffect(), 600, 0));} }
		
					if (i == 2) {
						if (PLANE) { playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 0)); }
						else { playerIn.addEffect(new MobEffectInstance(this.takeEffect(), 780, 0)); } }
		
					if (i == 3) {
						if (PLANE) { playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600, 0)); }
						else { playerIn.addEffect(new MobEffectInstance(this.takeEffect(), 900, 0)); } }
				}
				
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ItemInteractionResult.SUCCESS;
	}

	private Holder<MobEffect> takeEffect() { //for 1.20.6
		if (this == Seasonal_Blocks.KAKIGOURI_apple.get()) { return MobEffects.DAMAGE_RESISTANCE; }
		if (this == Seasonal_Blocks.KAKIGOURI_cherry.get()) { return MobEffects.DAMAGE_BOOST; }
		if (this == Seasonal_Blocks.KAKIGOURI_citrus.get()) { return MobEffects.FIRE_RESISTANCE; }
		if (this == Seasonal_Blocks.KAKIGOURI_grape.get()) { return MobEffects.NIGHT_VISION; }
		if (this == Seasonal_Blocks.KAKIGOURI_tea.get()) { return MobEffects.DIG_SPEED; }
		else { return MobEffects.DIG_SPEED; }
	}
	
	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		int i = state.getValue(STAGE_1_4);

		if (waterIn(state, worldIn, pos) && i != 4) {
			worldIn.scheduleTick(pos, this, 60);
			CMEvents.soundSnowBreak(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_kakigouri").withStyle(ChatFormatting.GRAY));
	}
}
