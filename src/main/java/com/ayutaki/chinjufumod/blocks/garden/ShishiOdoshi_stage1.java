package com.ayutaki.chinjufumod.blocks.garden;

import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShishiOdoshi_stage1 extends Base_ShishiOdoshi {

	public ShishiOdoshi_stage1(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			ItemStack hStack = playerIn.getItemInHand(hand);
			
			if (hStack.isEmpty()) {
				int i = state.getValue(STAGE_1_4);
				
				if (i == 1) {
					CMEvents.soundStoneButton_On(worldIn, pos);

					worldIn.setBlock(pos, state.setValue(Base_ShishiOdoshi.STAGE_1_4, Integer.valueOf(2)), 3);
					worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(Base_ShishiOdoshi.H_FACING, state.getValue(H_FACING))
							.setValue(Base_ShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
							.setValue(Base_ShishiOdoshi.WHICH, state.getValue(WHICH))
							.setValue(Base_ShishiOdoshi.STAGE_1_4, Integer.valueOf(2)), 3); }

				else { //i != 1
					CMEvents.soundStoneButton_Off(worldIn, pos);

					worldIn.setBlock(pos, state.setValue(Base_ShishiOdoshi.STAGE_1_4, Integer.valueOf(1)), 3);
					worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(Base_ShishiOdoshi.H_FACING, state.getValue(H_FACING))
							.setValue(Base_ShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
							.setValue(Base_ShishiOdoshi.WHICH, state.getValue(WHICH))
							.setValue(Base_ShishiOdoshi.STAGE_1_4, Integer.valueOf(1)), 3); } }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		worldIn.getBlockTicks().scheduleTick(pos, Garden_Blocks.SHISHIODOSHI, 150);
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_1_4);
		DoubleBlockHalf half = state.getValue(HALF);
		
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		switch (half) {
		case LOWER:
		default:
			
			switch (i) {
			case 1:
			default:
				break;

			case 2:
			case 3:
				worldIn.setBlock(pos, state.setValue(Base_ShishiOdoshi.STAGE_1_4, Integer.valueOf(i + 1)), 3);
				worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(Base_ShishiOdoshi.H_FACING, state.getValue(H_FACING))
							.setValue(Base_ShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
							.setValue(Base_ShishiOdoshi.WHICH, state.getValue(WHICH))
							.setValue(Base_ShishiOdoshi.STAGE_1_4, Integer.valueOf(i + 1)), 3);
				
				worldIn.getBlockTicks().scheduleTick(pos, Garden_Blocks.SHISHIODOSHI, 150);
				break;
				
			case 4:
				worldIn.setBlock(pos, Garden_Blocks.SHISHIODOSHI2.defaultBlockState()
						.setValue(Base_ShishiOdoshi.H_FACING, state.getValue(H_FACING))
						.setValue(Base_ShishiOdoshi.HALF, DoubleBlockHalf.LOWER)
						.setValue(Base_ShishiOdoshi.WHICH, state.getValue(WHICH))
						.setValue(Base_ShishiOdoshi.STAGE_1_4, Integer.valueOf(1)), 3);
				worldIn.setBlock(pos.above(), Garden_Blocks.SHISHIODOSHI2.defaultBlockState()
						.setValue(Base_ShishiOdoshi.H_FACING, state.getValue(H_FACING))
						.setValue(Base_ShishiOdoshi.HALF, DoubleBlockHalf.UPPER)
						.setValue(Base_ShishiOdoshi.WHICH, state.getValue(WHICH))
						.setValue(Base_ShishiOdoshi.STAGE_1_4, Integer.valueOf(1)), 3);
				
				worldIn.getBlockTicks().scheduleTick(pos, Garden_Blocks.SHISHIODOSHI, 150);
				break;
			} // STAGE_1_4
			break;

		case UPPER:
			break;
		} // HALF
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.5D;
		double d2 = (double)pos.getZ() + 0.5D;
		
		int i = state.getValue(STAGE_1_4);
		if (i == 1) { }
		else { //i != 1
			worldIn.playLocalSound(d0, d1, d2, SoundEvents.WATER_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F, rand.nextFloat() + 0.75F, false); }
	}
}
