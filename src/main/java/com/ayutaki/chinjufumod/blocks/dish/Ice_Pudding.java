package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Ice_Pudding extends BaseFood_Stage4WP {
	/* Collision */
	private static final VoxelShape ICE_BOX = Block.makeCuboidShape(6.4D, 0.0D, 6.4D, 9.6D, 4.0D, 9.6D);
	private static final VoxelShape ICE_DOWN = Block.makeCuboidShape(6.4D, -8.0D, 6.4D, 9.6D, 0.1D, 9.6D);
	private static final VoxelShape PUDDING_BOX = Block.makeCuboidShape(6.7D, 0.0D, 6.7D, 9.3D, 2.1D, 9.3D);
	private static final VoxelShape PUDDING_DOWN = Block.makeCuboidShape(6.7D, -8.0D, 6.7D, 9.3D, 0.1D, 9.3D);
	
	public Ice_Pudding(Block.Properties props) {
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
					boolean icecream = (this == Dish_Blocks.ICECREAM || this == Dish_Blocks.ICECREAM_GREEN || 
							this == Dish_Blocks.ICECREAM_RED || this == Dish_Blocks.ICECREAM_CACAO);
					
					int iTIME = (i == 1)? 1300 : ((i == 2)? 1440 : 1580); //3600*1.2
					int pTIME = (i == 1)? 1450 : ((i == 2)? 1600 : 1750); //4000*1.2
					int eTIME = icecream? iTIME : pTIME;
					playerIn.addPotionEffect(new EffectInstance(Effects.LUCK, eTIME, 1)); }
	
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_4);
		
		if (waterIn(state, worldIn, pos) && i != 4) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(4))); }
		
		else { }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();
		boolean icecream = (this == Dish_Blocks.ICECREAM || this == Dish_Blocks.ICECREAM_GREEN || 
				this == Dish_Blocks.ICECREAM_RED || this == Dish_Blocks.ICECREAM_CACAO);

		if (icecream) { return notDown? ICE_BOX : ICE_DOWN; }
		else { return notDown? PUDDING_BOX : PUDDING_DOWN; }
	}
}
