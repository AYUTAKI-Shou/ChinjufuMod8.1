package com.ayutaki.chinjufumod.blocks.amado;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class Tobukuro extends Base_Tobukuro {
	/** 1=4 sheets, 2=3 sheets, 3=2 sheets, 4=1 sheet, 5=Zero **/
	public Tobukuro(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player playerIn, BlockHitResult hit) {
		/** 1=4 sheets, 2=3 sheets, 3=2 sheets, 4=1 sheet, 5=Zero **/
		int i = state.getValue(STAGE_1_5);
		Direction direction = state.getValue(H_FACING);
		DoubleBlockHalf half = state.getValue(HALF);

		int x = (int) pos.getX();
		int y = (int) pos.getY();
		int z = (int) pos.getZ();

		BlockState AMADO_FACEUP_1 = this.takeBlock().defaultBlockState().setValue(Amado.H_FACING, state.getValue(H_FACING))
				.setValue(Amado.HALF, DoubleBlockHalf.UPPER).setValue(Amado.STAGE_1_4, Integer.valueOf(1));
		BlockState AMADO_FACELO_1 = this.takeBlock().defaultBlockState().setValue(Amado.H_FACING, state.getValue(H_FACING))
				.setValue(Amado.HALF, DoubleBlockHalf.LOWER).setValue(Amado.STAGE_1_4, Integer.valueOf(1));
		
		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			CMEvents.soundAmado(worldIn, pos);
			
			switch (half) {
			case LOWER:
			default:

				switch (direction) {
				case NORTH:
				default:
					if (worldIn.getBlockState(new BlockPos(x - 1, y, z)).canBeReplaced() &&
							worldIn.getBlockState(new BlockPos(x - 1, y + 1, z)).canBeReplaced()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x - 1, y, z), AMADO_FACELO_1, 3);
						worldIn.setBlock(new BlockPos(x - 1, y + 1, z), AMADO_FACEUP_1, 3); }
					
					else { } //!Replaceable
					break;

				case SOUTH:
					if (worldIn.getBlockState(new BlockPos(x + 1, y, z)).canBeReplaced() &&
							worldIn.getBlockState(new BlockPos(x + 1, y + 1, z)).canBeReplaced()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x + 1, y, z), AMADO_FACELO_1, 3);
						worldIn.setBlock(new BlockPos(x + 1, y + 1, z), AMADO_FACEUP_1, 3); }
					
					else { } //!Replaceable
					break;

				case EAST:
					if (worldIn.getBlockState(new BlockPos(x, y, z - 1)).canBeReplaced() &&
							worldIn.getBlockState(new BlockPos(x, y + 1, z - 1)).canBeReplaced()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x, y, z - 1), AMADO_FACELO_1, 3);
						worldIn.setBlock(new BlockPos(x, y + 1, z - 1), AMADO_FACEUP_1, 3); }
					
					else { } //!Replaceable
					break;
					
				case WEST:
					if (worldIn.getBlockState(new BlockPos(x, y, z + 1)).canBeReplaced() &&
							worldIn.getBlockState(new BlockPos(x, y + 1, z + 1)).canBeReplaced()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x, y, z + 1), AMADO_FACELO_1, 3);
						worldIn.setBlock(new BlockPos(x, y + 1, z + 1), AMADO_FACEUP_1, 3); }
					
					else { } //!Replaceable
					break;
				} // switch
				break;


			case UPPER:
				switch (direction) {
				case NORTH:
				default:
					if (worldIn.getBlockState(new BlockPos(x - 1, y, z)).canBeReplaced() &&
							worldIn.getBlockState(new BlockPos(x - 1, y - 1, z)).canBeReplaced()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x - 1, y, z), AMADO_FACEUP_1, 3);
						worldIn.setBlock(new BlockPos(x - 1, y - 1, z), AMADO_FACELO_1, 3); }
					
					else { } //!Replaceable
					break;

				case SOUTH:
					if (worldIn.getBlockState(new BlockPos(x + 1, y, z)).canBeReplaced() &&
							worldIn.getBlockState(new BlockPos(x + 1, y - 1, z)).canBeReplaced()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x + 1, y, z), AMADO_FACEUP_1, 3);
						worldIn.setBlock(new BlockPos(x + 1, y - 1, z), AMADO_FACELO_1, 3); }
					
					else { } //!Replaceable
					break;

				case EAST:
					if (worldIn.getBlockState(new BlockPos(x, y, z - 1)).canBeReplaced() &&
							worldIn.getBlockState(new BlockPos(x, y - 1, z - 1)).canBeReplaced()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x, y, z - 1), AMADO_FACEUP_1, 3);
						worldIn.setBlock(new BlockPos(x, y - 1, z - 1), AMADO_FACELO_1, 3); }
					
					else { } //!Replaceable
					break;
					
				case WEST:
					if (worldIn.getBlockState(new BlockPos(x, y, z + 1)).canBeReplaced() &&
							worldIn.getBlockState(new BlockPos(x, y - 1, z + 1)).canBeReplaced()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x, y, z + 1), AMADO_FACEUP_1, 3);
						worldIn.setBlock(new BlockPos(x, y - 1, z + 1), AMADO_FACELO_1, 3); }
					
					else { } //!Replaceable
					break;
				} // switch
				break;
			} // switch LOWER-UPPER
		} //i != 5
		return InteractionResult.SUCCESS;
	}

	private void updateTOBUKURO_UP(BlockState state, Level worldIn, BlockPos pos) {
		int i = state.getValue(STAGE_1_5);
		BlockState this_FACEUP = this.defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)).setValue(HALF, DoubleBlockHalf.UPPER);

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3);
		worldIn.setBlock(pos.above(), this_FACEUP.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3);
	}
	
	private void updateTOBUKURO_DOWN(BlockState state, Level worldIn, BlockPos pos) {
		int i = state.getValue(STAGE_1_5);
		BlockState this_FACELO = this.defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)).setValue(HALF, DoubleBlockHalf.LOWER);

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3);
		worldIn.setBlock(pos.below(), this_FACELO.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3);
	}
	
	private Block takeBlock() {
		if (this == Slidedoor_Blocks.TOBUKURO.get()) { return Slidedoor_Blocks.AMADO.get(); }
		else { return Slidedoor_Blocks.AMADO_S.get(); }
	}
	
	private Block takeLBlock() {
		if (this == Slidedoor_Blocks.TOBUKURO.get()) { return Slidedoor_Blocks.TOBUKURO_L.get(); }
		else { return Slidedoor_Blocks.TOBUKURO_SL.get(); }
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		Player playerIn = context.getPlayer();

		/** pos.up() = Replaceable block. **/
		if (pos.getY() < worldIn.getMaxBuildHeight() - 1 && worldIn.getBlockState(pos.above()).canBeReplaced(context)) {
			if (playerIn.isCrouching()) {
				return this.takeLBlock().defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
						.setValue(STAGE_1_5, Integer.valueOf(1))
						.setValue(HALF, DoubleBlockHalf.LOWER)
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			else {
				return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite())
						.setValue(STAGE_1_5, Integer.valueOf(1))
						.setValue(HALF, DoubleBlockHalf.LOWER)
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
		}

		else { return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack stack) {
		FluidState fluidUp = worldIn.getFluidState(pos.above());

		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)
				.setValue(H_FACING, state.getValue(H_FACING))
				.setValue(STAGE_1_5, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidUp.getType() == Fluids.WATER)), 3);
	}
}
