package com.ayutaki.chinjufumod.blocks.harbor;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Truss_Amp extends AbstractAmp {
	/* Collision */
	private static final VoxelShape UD = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private static final VoxelShape NS = Block.makeCuboidShape(1.0D, 0.0D, 0.0D, 15.0D, 15.0D, 16.0D);
	private static final VoxelShape WE = Block.makeCuboidShape(0.0D, 1.0D, 1.0D, 16.0D, 15.0D, 15.0D);
	
	public Truss_Amp(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH)
				.with(WATERLOGGED, Boolean.valueOf(false))
				.with(POWERED, Boolean.valueOf(false)));
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED, POWERED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(FACING);
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
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1.0F;
	}

	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_amp").applyTextStyle(TextFormatting.GRAY));
	}
}
