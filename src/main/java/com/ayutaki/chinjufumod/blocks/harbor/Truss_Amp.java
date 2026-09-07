package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.List;

import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Truss_Amp extends AbstractAmp {
	public static final MapCodec<Truss_Amp> CODEC = simpleCodec(Truss_Amp::new);
	@Override
	public MapCodec<? extends Truss_Amp> codec() { return CODEC; }
	
	/* Collision */
	private static final VoxelShape UD = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private static final VoxelShape NS = Block.box(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 16.0D);
	private static final VoxelShape WE = Block.box(0.0D, 1.0D, 1.0D, 16.0D, 15.0D, 15.0D);

	public Truss_Amp(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH)
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(POWERED, Boolean.valueOf(false)));
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, POWERED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		Direction direction = state.getValue(FACING);
		switch (direction) {
		default: 
		case UP: return UD;
		case DOWN: return UD;
		case NORTH: return NS;
		case SOUTH: return NS;
		case WEST: return WE;
		case EAST: return WE;
		}
	}
	
	/* Solid 0.0F -> 1.0F Transparent */
	@Override
	public float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
		return 1.0F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_amp").withStyle(ChatFormatting.GRAY));
	}
}
