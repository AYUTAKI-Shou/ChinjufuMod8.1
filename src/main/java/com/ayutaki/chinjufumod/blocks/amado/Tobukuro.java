package com.ayutaki.chinjufumod.blocks.amado;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class Tobukuro extends Base_Tobukuro {

	/** 1=4 sheets, 2=3 sheets, 3=2 sheets, 4=1 sheet, 5=Zero **/
	public Tobukuro(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		/** 1=4 sheets, 2=3 sheets, 3=2 sheets, 4=1 sheet, 5=Zero **/
		int i = state.getValue(STAGE_1_5);
		Direction direction = state.getValue(H_FACING);
		DoubleBlockHalf half = state.getValue(HALF);

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();

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
					if (worldIn.getBlockState(new BlockPos(x - 1, y, z)).getMaterial().isReplaceable() &&
							worldIn.getBlockState(new BlockPos(x - 1, y + 1, z)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x - 1, y, z), AMADO_FACELO_1, 3);
						worldIn.setBlock(new BlockPos(x - 1, y + 1, z), AMADO_FACEUP_1, 3); }
					
					else { } //!Replaceable
					break;

				case SOUTH:
					if (worldIn.getBlockState(new BlockPos(x + 1, y, z)).getMaterial().isReplaceable() &&
							worldIn.getBlockState(new BlockPos(x + 1, y + 1, z)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x + 1, y, z), AMADO_FACELO_1, 3);
						worldIn.setBlock(new BlockPos(x + 1, y + 1, z), AMADO_FACEUP_1, 3); }
					
					else { } //!Replaceable
					break;

				case EAST:
					if (worldIn.getBlockState(new BlockPos(x, y, z - 1)).getMaterial().isReplaceable() &&
							worldIn.getBlockState(new BlockPos(x, y + 1, z - 1)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x, y, z - 1), AMADO_FACELO_1, 3);
						worldIn.setBlock(new BlockPos(x, y + 1, z - 1), AMADO_FACEUP_1, 3); }
					
					else { } //!Replaceable
					break;
					
				case WEST:
					if (worldIn.getBlockState(new BlockPos(x, y, z + 1)).getMaterial().isReplaceable() &&
							worldIn.getBlockState(new BlockPos(x, y + 1, z + 1)).getMaterial().isReplaceable()) {
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
					if (worldIn.getBlockState(new BlockPos(x - 1, y, z)).getMaterial().isReplaceable() &&
							worldIn.getBlockState(new BlockPos(x - 1, y - 1, z)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x - 1, y, z), AMADO_FACEUP_1, 3);
						worldIn.setBlock(new BlockPos(x - 1, y - 1, z), AMADO_FACELO_1, 3); }
					
					else { } //!Replaceable
					break;

				case SOUTH:
					if (worldIn.getBlockState(new BlockPos(x + 1, y, z)).getMaterial().isReplaceable() &&
							worldIn.getBlockState(new BlockPos(x + 1, y - 1, z)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x + 1, y, z), AMADO_FACEUP_1, 3);
						worldIn.setBlock(new BlockPos(x + 1, y - 1, z), AMADO_FACELO_1, 3); }
					
					else { } //!Replaceable
					break;

				case EAST:
					if (worldIn.getBlockState(new BlockPos(x, y, z - 1)).getMaterial().isReplaceable() &&
							worldIn.getBlockState(new BlockPos(x, y - 1, z - 1)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x, y, z - 1), AMADO_FACEUP_1, 3);
						worldIn.setBlock(new BlockPos(x, y - 1, z - 1), AMADO_FACELO_1, 3); }
					
					else { } //!Replaceable
					break;
					
				case WEST:
					if (worldIn.getBlockState(new BlockPos(x, y, z + 1)).getMaterial().isReplaceable() &&
							worldIn.getBlockState(new BlockPos(x, y - 1, z + 1)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlock(new BlockPos(x, y, z + 1), AMADO_FACEUP_1, 3);
						worldIn.setBlock(new BlockPos(x, y - 1, z + 1), AMADO_FACELO_1, 3); }
					
					else { } //!Replaceable
					break;
				} // switch
				break;
			} // switch LOWER-UPPER
		} //i != 5
		return ActionResultType.SUCCESS;
	}

	private void updateTOBUKURO_UP(BlockState state, World worldIn, BlockPos pos) {
		int i = state.getValue(STAGE_1_5);
		BlockState this_FACEUP = this.defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)).setValue(HALF, DoubleBlockHalf.UPPER);

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3);
		worldIn.setBlock(pos.above(), this_FACEUP.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3);
	}
	
	private void updateTOBUKURO_DOWN(BlockState state, World worldIn, BlockPos pos) {
		int i = state.getValue(STAGE_1_5);
		BlockState this_FACELO = this.defaultBlockState().setValue(H_FACING, state.getValue(H_FACING)).setValue(HALF, DoubleBlockHalf.LOWER);

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlock(pos, state.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3);
		worldIn.setBlock(pos.below(), this_FACELO.setValue(STAGE_1_5, Integer.valueOf(i + 1)), 3);
	}
	
	private Block takeBlock() {
		if (this == Slidedoor_Blocks.TOBUKURO) { return Slidedoor_Blocks.AMADO; }
		else { return Slidedoor_Blocks.AMADO_S; }
	}
	
	private Block takeLBlock() {
		if (this == Slidedoor_Blocks.TOBUKURO) { return Slidedoor_Blocks.TOBUKURO_L; }
		else { return Slidedoor_Blocks.TOBUKURO_SL; }
	}
	
	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		PlayerEntity playerIn = context.getPlayer();

		/** pos.up() = Replaceable block. **/
		if (pos.getY() < 255 && worldIn.getBlockState(pos.above()).canBeReplaced(context)) {
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
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		FluidState fluidUp = worldIn.getFluidState(pos.above());

		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)
				.setValue(H_FACING, state.getValue(H_FACING))
				.setValue(STAGE_1_5, Integer.valueOf(1))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidUp.getType() == Fluids.WATER)), 3);
	}
}
