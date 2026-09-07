package com.ayutaki.chinjufumod.blocks.dish;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Kanten extends BaseFood_Stage4WP {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(5.5D, 0.0D, 6.0D, 10.5D, 2.0D, 10.0D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(6.0D, 0.0D, 5.5D, 10.0D, 2.0D, 10.5D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(5.5D, 0.0D, 6.0D, 10.5D, 2.0D, 10.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(6.0D, 0.0D, 5.5D, 10.0D, 2.0D, 10.5D);

	private static final VoxelShape DOWN_SOUTH = Block.makeCuboidShape(5.5D, -8.0D, 6.0D, 10.5D, 0.1D, 10.0D);
	private static final VoxelShape DOWN_WEST = Block.makeCuboidShape(6.0D, -8.0D, 5.5D, 10.0D, 0.1D, 10.5D);
	private static final VoxelShape DOWN_NORTH = Block.makeCuboidShape(5.5D, -8.0D, 6.0D, 10.5D, 0.1D, 10.0D);
	private static final VoxelShape DOWN_EAST = Block.makeCuboidShape(6.0D, -8.0D, 5.5D, 10.0D, 0.1D, 10.5D);

	public Kanten(Block.Properties props) {
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
					boolean MILK = (this == Dish_Blocks.KANTEN_MILK);
					int MATCHA = (this == Dish_Blocks.YOKAN_MATCHA)? 80 : 0; /// (4560-4320)/3
					
					if (i == 1) {
						if (MILK) { playerIn.curePotionEffects(new ItemStack(Items.MILK_BUCKET));}
						else { playerIn.addPotionEffect(new EffectInstance(this.takeEffect(), 1300 + MATCHA, 0));} }
		
					if (i == 2) {
						if (MILK) { playerIn.curePotionEffects(new ItemStack(Items.MILK_BUCKET)); }
						else { playerIn.addPotionEffect(new EffectInstance(this.takeEffect(), 1440 + MATCHA, 0)); } }
		
					if (i == 3) {
						if (MILK) { playerIn.curePotionEffects(new ItemStack(Items.MILK_BUCKET)); }
						else { playerIn.addPotionEffect(new EffectInstance(this.takeEffect(), 1580 + MATCHA, 0)); } }
				}
				
				worldIn.setBlockState(pos, state.with(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Effect takeEffect() {
		if (this == Dish_Blocks.KANTEN_APPLE) { return Effects.RESISTANCE; }
		if (this == Dish_Blocks.KANTEN_CHERRY) { return Effects.STRENGTH; }
		if (this == Dish_Blocks.KANTEN_CITRUS) { return Effects.FIRE_RESISTANCE; }
		if (this == Dish_Blocks.KANTEN_GRAPE) { return Effects.NIGHT_VISION; }
		else { return Effects.HASTE; }
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
		Direction direction = state.get(H_FACING);
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return notDown? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return notDown? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return notDown? AABB_WEST : DOWN_WEST;
		case EAST:
			return notDown? AABB_EAST : DOWN_EAST;
		}
	}
}
