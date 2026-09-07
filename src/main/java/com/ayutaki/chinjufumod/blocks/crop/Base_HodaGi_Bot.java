package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Base_HodaGi_Bot extends BaseStage4_FaceWater {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(5.0D, 0.0D, 4.0D, 11.0D, 12.0D, 15.0D);
	private static final VoxelShape AABB_WEST = Block.box(1.0D, 0.0D, 5.0D, 12.0D, 12.0D, 11.0D);
	private static final VoxelShape AABB_NORTH = Block.box(5.0D, 0.0D, 1.0D, 11.0D, 12.0D, 12.0D);
	private static final VoxelShape AABB_EAST = Block.box(4.0D, 0.0D, 5.0D, 15.0D, 12.0D, 11.0D);

	public Base_HodaGi_Bot(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* TickRandom */
	private boolean inShade(ServerLevel worldIn, BlockPos pos) {
		return ((worldIn.canSeeSky(pos) && !worldIn.isDay()) || (!worldIn.canSeeSky(pos) && worldIn.getRawBrightness(pos, 0) <= 11));
	}
	
	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		int i = state.getValue(STAGE_1_4);
		if (!state.getValue(WATERLOGGED)) {
			if (inShade(worldIn, pos) && i != 4) {
				if (rand.nextInt(8) == 0) {
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(i + 1)), 3); } }
			
			else { }
		}
		
		if (state.getValue(WATERLOGGED)) {
			if (i == 1) { }
			
			else { //i != 1
				if (rand.nextInt(2) == 0) {
					CMEvents.drop1_ROTTENFOOD(worldIn, pos);
					worldIn.setBlock(pos, state.setValue(STAGE_1_4, Integer.valueOf(1)), 3); } }
		}
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(H_FACING);
		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}
	
	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(Items_Teatime.HODAGI.get());
	}

	/* Destroy a DoubleBlock from DoublePlantBlock.class */
	@Override
	public BlockState playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		BlockState upState = worldIn.getBlockState(pos.above());
		/** False is not Drop. **/
		if (upState.getBlock() instanceof Base_HodaGi_Top) {
			worldIn.destroyBlock(pos.above(), false); }
		
		return super.playerWillDestroy(worldIn, pos, state, playerIn);
	}

	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_hodagi_a_bot").withStyle(ChatFormatting.GRAY));
	}
}
