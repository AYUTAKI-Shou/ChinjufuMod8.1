package com.ayutaki.chinjufumod.blocks.hakkou;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Glass_Cider extends Base_Glass {
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(6.8D, 0.0D, 6.8D, 9.2D, 3.2D, 9.2D);
	private static final VoxelShape AABB_DOWN = Block.box(6.8D, -8.0D, 6.8D, 9.2D, 0.1D, 9.2D);
	
	public Glass_Cider(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
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
		return InteractionResult.SUCCESS;
	}
	
	private void takeEffects(Player playerIn, BlockState state) {
		boolean base = (this == Hakkou_Blocks.CIDERGLASS.get());
		int i = state.getValue(STAGE_0_2);
		
		int eTIME = (i == 0)? 1200 : 1500;
		double eTIME2 = base? 1.15 : 1;
		int eLEVEL = base? 0 : 1;
		
		playerIn.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, eTIME, eLEVEL));
		playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, (int) (eTIME * eTIME2), 0));
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		return notDown? AABB_BOX : AABB_DOWN;
	}
}
