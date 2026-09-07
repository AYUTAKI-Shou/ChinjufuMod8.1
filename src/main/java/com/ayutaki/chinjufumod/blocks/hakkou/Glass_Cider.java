package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;

import net.minecraft.block.AbstractBlock;
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

public class Glass_Cider extends Base_Glass {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(6.8D, 0.0D, 6.8D, 9.2D, 3.2D, 9.2D);
	private static final VoxelShape AABB_DOWN = Block.box(6.8D, -8.0D, 6.8D, 9.2D, 0.1D, 9.2D);

	public Glass_Cider(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		int i = state.getValue(STAGE_0_2);

		if (i == 2) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 2
			if (hStack.isEmpty()) {
				CMEvents.soundDrink(worldIn, pos);
				/** add Potion Effect. **/
				if (!worldIn.isClientSide) { this.takeEffects(playerIn, state); }
				worldIn.setBlock(pos, state.setValue(Base_Glass.STAGE_0_2, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private void takeEffects(PlayerEntity playerIn, BlockState state) {
		boolean base = (this == Hakkou_Blocks.CIDERGLASS);
		int i = state.getValue(STAGE_0_2);
		
		int eTIME = (i == 0)? 1200 : 1500;
		double eTIME2 = base? 1.15 : 1;
		int eLEVEL = base? 0 : 1;
		
		playerIn.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, eTIME, eLEVEL));
		playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, (int) (eTIME * eTIME2), 0));
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
	}
}
