package com.ayutaki.chinjufumod.blocks.amado;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class Tobukuro_L extends Base_Tobukuro {
	/** 1=4 sheets, 2=3 sheets, 3=2 sheets, 4=1 sheet, 5=Zero **/
	public Tobukuro_L(Block.Properties props) {
		super(props);
	}

	/* RightClick Action */
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		/** 1=4 sheets, 2=3 sheets, 3=2 sheets, 4=1 sheet, 5=Zero **/
		int i = state.get(STAGE_1_5);
		Direction direction = state.get(H_FACING);
		DoubleBlockHalf half = state.get(HALF);

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
	
		BlockState AMADO_FACEUP_4 = this.takeBlock().getDefaultState().with(Amado.H_FACING, state.get(H_FACING))
				.with(Amado.HALF, DoubleBlockHalf.UPPER).with(Amado.STAGE_1_4, Integer.valueOf(4));
		BlockState AMADO_FACELO_4 = this.takeBlock().getDefaultState().with(Amado.H_FACING, state.get(H_FACING))
				.with(Amado.HALF, DoubleBlockHalf.LOWER).with(Amado.STAGE_1_4, Integer.valueOf(4));

		if (i == 5) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 5
			CMEvents.soundAmado(worldIn, pos);
			
			switch (half) {
			case LOWER:
			default:

				switch (direction) {
				case NORTH:
				default:
					if (worldIn.getBlockState(new BlockPos(x + 1, y, z)).getMaterial().isReplaceable() && 
							worldIn.getBlockState(new BlockPos(x + 1, y + 1, z)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlockState(new BlockPos(x + 1, y, z), AMADO_FACELO_4);
						worldIn.setBlockState(new BlockPos(x + 1, y + 1, z), AMADO_FACEUP_4); }
					
					else { } //!Replaceable
					break;

				case SOUTH:
					if (worldIn.getBlockState(new BlockPos(x - 1, y, z)).getMaterial().isReplaceable() && 
							worldIn.getBlockState(new BlockPos(x - 1, y + 1, z)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlockState(new BlockPos(x - 1, y, z), AMADO_FACELO_4);
						worldIn.setBlockState(new BlockPos(x - 1, y + 1, z), AMADO_FACEUP_4); }
					else { } //!Replaceable
					break;

				case EAST:
					if (worldIn.getBlockState(new BlockPos(x, y, z + 1)).getMaterial().isReplaceable() && 
							worldIn.getBlockState(new BlockPos(x, y + 1, z + 1)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlockState(new BlockPos(x, y, z + 1), AMADO_FACELO_4);
						worldIn.setBlockState(new BlockPos(x, y + 1, z + 1), AMADO_FACEUP_4); }
					
					else { } //!Replaceable
					break;
					
				case WEST:
					if (worldIn.getBlockState(new BlockPos(x, y, z - 1)).getMaterial().isReplaceable() && 
							worldIn.getBlockState(new BlockPos(x, y + 1, z - 1)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_UP(state, worldIn, pos);
	
						worldIn.setBlockState(new BlockPos(x, y, z - 1), AMADO_FACELO_4);
						worldIn.setBlockState(new BlockPos(x, y + 1, z - 1), AMADO_FACEUP_4); }
					
					else { } //!Replaceable
					break;
				} // switch
				break;

				
			case UPPER:
				switch (direction) {
				case NORTH:
				default:
					if (worldIn.getBlockState(new BlockPos(x + 1, y, z)).getMaterial().isReplaceable() && 
							worldIn.getBlockState(new BlockPos(x + 1, y - 1, z)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlockState(new BlockPos(x + 1, y, z), AMADO_FACEUP_4);
						worldIn.setBlockState(new BlockPos(x + 1, y - 1, z), AMADO_FACELO_4); }
					
					else { } //!Replaceable
					break;

				case SOUTH:
					if (worldIn.getBlockState(new BlockPos(x - 1, y, z)).getMaterial().isReplaceable() && 
							worldIn.getBlockState(new BlockPos(x - 1, y - 1, z)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlockState(new BlockPos(x - 1, y, z), AMADO_FACEUP_4);
						worldIn.setBlockState(new BlockPos(x - 1, y - 1, z), AMADO_FACELO_4); }
					
					else { } //!Replaceable
					break;

				case EAST:
					if (worldIn.getBlockState(new BlockPos(x, y, z + 1)).getMaterial().isReplaceable() && 
							worldIn.getBlockState(new BlockPos(x, y - 1, z + 1)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlockState(new BlockPos(x, y, z + 1), AMADO_FACEUP_4);
						worldIn.setBlockState(new BlockPos(x, y - 1, z + 1), AMADO_FACELO_4); }
					
					else { } //!Replaceable
					break;
					
				case WEST:
					if (worldIn.getBlockState(new BlockPos(x, y, z - 1)).getMaterial().isReplaceable() && 
							worldIn.getBlockState(new BlockPos(x, y - 1, z - 1)).getMaterial().isReplaceable()) {
						this.updateTOBUKURO_DOWN(state, worldIn, pos);
	
						worldIn.setBlockState(new BlockPos(x, y, z - 1), AMADO_FACEUP_4);
						worldIn.setBlockState(new BlockPos(x, y - 1, z - 1), AMADO_FACELO_4); }
					
					else { } //!Replaceable
					break;
				} // switch
				break;
			} // switch LOWER-UPPER
		} // i != 5
		return ActionResultType.SUCCESS;
	}

	private void updateTOBUKURO_UP(BlockState state, World worldIn, BlockPos pos) {
		int i = state.get(STAGE_1_5);
		BlockState this_FACEUP = this.getDefaultState().with(H_FACING, state.get(H_FACING)).with(HALF, DoubleBlockHalf.UPPER);

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(i + 1)));
		worldIn.setBlockState(pos.up(), this_FACEUP.with(STAGE_1_5, Integer.valueOf(i + 1)));
	}
	
	private void updateTOBUKURO_DOWN(BlockState state, World worldIn, BlockPos pos) {
		int i = state.get(STAGE_1_5);
		BlockState this_FACELO = this.getDefaultState().with(H_FACING, state.get(H_FACING)).with(HALF, DoubleBlockHalf.LOWER);

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlockState(pos, state.with(STAGE_1_5, Integer.valueOf(i + 1)));
		worldIn.setBlockState(pos.down(), this_FACELO.with(STAGE_1_5, Integer.valueOf(i + 1)));
	}
	
	private Block takeBlock() {
		if (this == Slidedoor_Blocks.TOBUKURO_L) { return Slidedoor_Blocks.AMADO; }
		else { return Slidedoor_Blocks.AMADO_S; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		IFluidState fluidUp = worldIn.getFluidState(pos.up());

		worldIn.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER)
				.with(H_FACING, state.get(H_FACING))
				.with(STAGE_1_5, Integer.valueOf(1))
				.with(WATERLOGGED, Boolean.valueOf(fluidUp.isTagged(FluidTags.WATER))), 3);
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		if (this == Slidedoor_Blocks.TOBUKURO_SL) { return new ItemStack(Items_Wadeco.TOBUKURO_S); }
		return new ItemStack(Items_Wadeco.TOBUKURO);
	}
}
