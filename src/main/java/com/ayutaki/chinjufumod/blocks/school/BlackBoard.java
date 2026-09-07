package com.ayutaki.chinjufumod.blocks.school;

import java.util.Arrays;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Base_Chalk;
import com.ayutaki.chinjufumod.items.color.Board_Eraser;
import com.ayutaki.chinjufumod.network.OpenUI_BlackBoard;
import com.ayutaki.chinjufumod.tileentity.BlackBoardText;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.contents.PlainTextContents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.neoforged.neoforge.network.PacketDistributor;

public class BlackBoard extends AbstractBlackBoard {
	public static final MapCodec<BlackBoard> CODEC = simpleCodec(BlackBoard::new);
	@Override
	public MapCodec<? extends BlackBoard> codec() { return CODEC; }
	
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.5D);
	private static final VoxelShape AABB_WEST = Block.box(14.5D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_NORTH = Block.box(0.0D, 0.0D, 14.5D, 16.0D, 16.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 0.0D, 0.0D, 1.5D, 16.0D, 16.0D);

	public BlackBoard(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		if (playerIn != null) {
			Level world = playerIn.level();
			
			BlockEntity tileEntity = world.getBlockEntity(pos);
			if (tileEntity instanceof BlackBoard_TileEntity board) {
				
				ItemStack hStack = playerIn.getItemInHand(hand);
				Item hItem = hStack.getItem();
				boolean mode = playerIn.getAbilities().instabuild;
				
				if(board.isWaxed()) {
					CMEvents.textIsWaxed(world, pos, playerIn);
					return InteractionResult.PASS; }
				
				else {
					/** Chalk **/
					if (hItem instanceof Base_Chalk && this.hasEditableText(playerIn, board)) {
						playerIn.playSound(SoundEvents_CM.WRITE_CHALK.get(), 1.0F, 1.0F);
						
						if (this.colorBlackBoard(world, board, playerIn, hItem)) {
							board.executeClickCommandsIfPresent(playerIn, world, pos);
							board.markUpdated(); }
						
						if(playerIn.level() instanceof ServerLevel server) { 
							board.setAllowedPlayerEditor(playerIn.getUUID());
							PacketDistributor.sendToPlayer((ServerPlayer)playerIn, new OpenUI_BlackBoard(playerIn.getUUID(), board.getBlockPos()));
							hStack.hurtAndBreak(mode? 0 : 1, server, playerIn instanceof ServerPlayer user ? user : null,
									consumer -> { hStack.shrink(1); });
						}

						return InteractionResult.SUCCESS; }
		
					/** Glowi Text **/
					boolean hasGlow = board.getBoardText().hasGlowText();
					if (!hasGlow && hItem == Items.GLOWSTONE_DUST) {

						if (this.glowBlackBoard(world, board, playerIn)) {
							board.executeClickCommandsIfPresent(playerIn, world, pos);
							board.markUpdated(); 
							CMEvents.consume1_seSnowP(world, pos, playerIn, hand); }
						
						return InteractionResult.SUCCESS; }
					
					if (hasGlow && hItem == Items.BONE_MEAL) {
						
						if (this.unglowBlackBoard(world, board, playerIn)) {
							board.executeClickCommandsIfPresent(playerIn, world, pos);
							board.markUpdated(); 
							CMEvents.consume1_seSnowP(world, pos, playerIn, hand); }
						
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
							CMEvents.textNeedClean(world, pos, playerIn);
							return InteractionResult.PASS; }
						
						else {
							board.clearText();
							if(playerIn.level() instanceof ServerLevel server) { 
								hStack.hurtAndBreak(mode? 0 : 1, server, playerIn instanceof ServerPlayer user ? user : null,
										consumer -> { hStack.shrink(1); }); }
							playerIn.playSound(SoundEvents_CM.USE_ERASER.get(), 1.0F, 1.0F);
							return InteractionResult.SUCCESS; } 
					}
				}//!board.isWaxed()
			}//tileEntity
		}//playerIn != null
		return InteractionResult.PASS;
	}
	
	private boolean hasEditableText(Player playerIn, BlackBoard_TileEntity tileEntity) {
		BlackBoardText boardText = tileEntity.getBoardText();
		return Arrays.stream(boardText.getMessages(true))
			.allMatch(predicate -> predicate.equals(CommonComponents.EMPTY) || predicate.getContents() instanceof PlainTextContents);
	}
	
	public boolean colorBlackBoard(Level worldIn, BlackBoard_TileEntity tileEntity, Player playerIn, Item hItem) {
		if (tileEntity.updateText(boardText -> boardText.setColor(TakeValue_CM.chalkColor(hItem)))) {
			return true; }
		
		else { return false; }
	}
	
	public boolean glowBlackBoard(Level worldIn, BlackBoard_TileEntity tileEntity, Player playerIn) {
		if (tileEntity.updateText(boardText -> boardText.setGlowText(true))) {
			return true; }
		
		else { return false; }
	}
	
	public boolean unglowBlackBoard(Level worldIn, BlackBoard_TileEntity tileEntity, Player playerIn) {
		if (tileEntity.updateText(boardText -> boardText.setGlowText(false))) {
			return true; }
		
		else { return false; }
	}
	
	public float getYRotationDegrees(BlockState state) {
		return state.getValue(H_FACING).toYRot();
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
