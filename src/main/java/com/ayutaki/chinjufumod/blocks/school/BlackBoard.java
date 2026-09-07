package com.ayutaki.chinjufumod.blocks.school;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Base_Chalk;
import com.ayutaki.chinjufumod.items.color.Board_Eraser;
import com.ayutaki.chinjufumod.network.OpenUI_BlackBoard;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.PacketDistributor;

public class BlackBoard extends AbstractBlackBoard {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.5D);
	private static final VoxelShape AABB_WEST = Block.box(14.5D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 14.5D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 1.5D, 16.0D, 16.0D);

	public BlackBoard(BlockBehaviour.Properties props) {
		super(props);
	}
	
	public String getDescriptionId() {
		return this.asItem().getDescriptionId();
	}
	
	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		if (playerIn != null) {
			Level world = playerIn.getLevel();
			
			BlockEntity tileEntity = world.getBlockEntity(pos);
			if (tileEntity instanceof BlackBoard_TileEntity board) {
				
				ItemStack hStack = playerIn.getItemInHand(hand);
				Item hItem = hStack.getItem();
				boolean mode = playerIn.getAbilities().instabuild;
				
				if(board.isWaxed()) {
					CMEvents.textIsWaxed(worldIn, pos, playerIn);
					return InteractionResult.PASS; }
				
				else { //!board.isWaxed()
					/** Chalk **/
					if (hItem instanceof Base_Chalk) {
						playerIn.playSound(SoundEvents_CM.WRITE_CHALK.get(), 1.0F, 1.0F); 
						board.setTxtColor(TakeValue_CM.chalkColor(hItem));
						
						if(!world.isClientSide()) {
							board.setAllowedPlayerEditor(playerIn.getUUID());
							ChinjufuMod.CHANNEL.send(PacketDistributor.ALL.noArg(), new OpenUI_BlackBoard(playerIn.getUUID(), board.getBlockPos()));
							hStack.hurtAndBreak(mode? 0 : 1, playerIn, user -> { hStack.shrink(1); }); }

						return InteractionResult.SUCCESS; }
					
					/** Glowi Text **/
					boolean hasGlow = board.hasGlowText();
					if (!hasGlow && hItem == Items.GLOWSTONE_DUST) {
						board.setGlowText(true);
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						return InteractionResult.SUCCESS; }
					
					if (hasGlow && hItem == Items.BONE_MEAL) {
						board.setGlowText(false);
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						return InteractionResult.SUCCESS; }
					
					/** Waxed **/
					if (hItem == Items.HONEYCOMB) {
						board.setWaxed(true);
						CMEvents.Wax_Particle(worldIn, pos, playerIn, hand);
						return InteractionResult.SUCCESS; }
					
					/** Eraser **/
					if (hItem instanceof Board_Eraser) {
						int life = hStack.getMaxDamage() - hStack.getDamageValue();
						if (life <= 1) { 
							CMEvents.textNeedClean(worldIn, pos, playerIn);
							return InteractionResult.PASS; }
						
						else {
							board.clearText();
							hStack.hurtAndBreak(mode? 0 : 1, playerIn, user -> { hStack.shrink(1); } );
							playerIn.playSound(SoundEvents_CM.USE_ERASER.get(), 1.0F, 1.0F);
							return InteractionResult.SUCCESS; } 
					}
				} //!board.isWaxed()
				
			}//tileEntity
		}//playerIn != null
		return InteractionResult.PASS;
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
}
