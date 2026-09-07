package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Ice_Pudding extends BaseFood_Stage4Water {
	/* Collision */
	private static final VoxelShape ICE_BOX = Block.box(6.4D, 0.0D, 6.4D, 9.6D, 4.0D, 9.6D);
	private static final VoxelShape ICE_DOWN = Block.box(6.4D, -8.0D, 6.4D, 9.6D, 0.1D, 9.6D);
	private static final VoxelShape PUDDING_BOX = Block.box(6.7D, 0.0D, 6.7D, 9.3D, 2.1D, 9.3D);
	private static final VoxelShape PUDDING_DOWN = Block.box(6.7D, -8.0D, 6.7D, 9.3D, 0.1D, 9.3D);

	public Ice_Pudding(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_1_4);

		if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 4
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
			
				/** add Potion Effect. **/
				if (!worldIn.isClientSide) {
					boolean icecream = (this == Dish_Blocks.ICECREAM.get() || this == Dish_Blocks.ICECREAM_GREEN.get() || 
							this == Dish_Blocks.ICECREAM_RED.get() || this == Dish_Blocks.ICECREAM_CACAO.get());
					
					int iTIME = (i == 1)? 1300 : ((i == 2)? 1440 : 1580); //3600*1.2
					int pTIME = (i == 1)? 1450 : ((i == 2)? 1600 : 1750); //4000*1.2
					int eTIME = icecream? iTIME : pTIME;
					playerIn.addEffect(new MobEffectInstance(MobEffects.LUCK, eTIME, 1));
				}
				
				worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_4);
		
		if (waterIn(state, worldIn, pos) && i != 4) {
			worldIn.scheduleTick(pos, this, 60);

			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(4)), 3); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		boolean icecream = (this == Dish_Blocks.ICECREAM.get() || this == Dish_Blocks.ICECREAM_GREEN.get() || 
				this == Dish_Blocks.ICECREAM_RED.get() || this == Dish_Blocks.ICECREAM_CACAO.get());
		
		if (icecream) { return notDown? ICE_BOX : ICE_DOWN; }
		else { return notDown? PUDDING_BOX : PUDDING_DOWN; }
	}
}
